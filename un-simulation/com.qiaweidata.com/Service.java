package com.fly.procode.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.fly.procode.process.RunProgress;

/**
 * 核心业务逻辑类
 */
public class Service extends Observable
{
    // 项目源代码目录
    private String sourcePath;
    
    private RunProgress runProcess;
    
    // 构造函数
    public Service()
    {
        super();
    }
    
    public RunProgress getRunProcess()
    {
        return runProcess;
    }
    
    public void setRunProcess(RunProgress runProcess)
    {
        this.runProcess = runProcess;
    }
    
    public String getSourcePath()
    {
        return sourcePath;
    }
    
    public void setSourcePath(String sourcePath)
    {
        this.sourcePath = sourcePath;
    }
    
    // 创建备份文件
    public void createBakFile(String bootPath, String bakFileName, List<String> fileFilter, String pwdtext)
    {
        // InputStream,OutputStream 并不负责文件创建或删除
        // 这部分功能由File来实现
        File bakfile = new File(bakFileName);
        if (bakfile.exists())
        {
            bakfile.delete();
        }
        if (!"".equals(pwdtext))
        {
            // new FileOutputStream(File file,boolean append)
            try (OutputStream fos = new FileOutputStream(bakFileName, false); BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, "ISO-8859-1")))
            {
                writer.write("It is a encrypt backup File");
                writer.newLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        // 备份文件
        bakFile(bootPath, bakFileName, fileFilter, pwdtext);
    }
    
    // 递归备份文件
    private void bakFile(String bootPath, String bakFileName, List<String> fileFilter, String pwdtext)
    {
        File file = new File(sourcePath);
        if (file.exists() && file.isDirectory() && !file.getName().startsWith("."))
        {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++)
            {
                File f1 = files[i];
                if (f1.isDirectory())
                {
                    Service p = new Service();
                    p.setRunProcess(runProcess);
                    p.addObserver(runProcess);
                    p.setSourcePath(f1.getPath());
                    p.bakFile(bootPath, bakFileName, fileFilter, pwdtext);
                }
                else if (f1.isFile())
                {
                    if (isExtraFile(f1.getName(), fileFilter))
                    {
                        setChanged();
                        notifyObservers("开始处理文件： " + f1.getName());
                        List<String> list = new ArrayList<String>();
                        String text = "//goto " + f1.getPath().substring(bootPath.length());
                        list.add(text);
                        list.addAll(getFiletext(f1.getPath()));
                        writeFile(list, bakFileName, pwdtext);
                    }
                }
            }
        }
    }
    
    // 以append 方式将text 写入 bakFile
    private void writeFile(List<String> list, String bakFileName, String pwdtext)
    {
        // 设置缓冲区大小为8192 bytes
        try (OutputStream os = new FileOutputStream(bakFileName, true); BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "ISO-8859-1"), 8192))
        {
            for (String text : list)
            {
                writer.write(text);
                writer.newLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    // 获取文件内容
    private List<String> getFiletext(String filePath)
    {
        // 设置缓冲区大小为8192 bytes
        List<String> list = new ArrayList<String>();
        try (InputStream is = new FileInputStream(filePath); BufferedReader in = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"), 8192))
        {
            String text;
            while ((text = in.readLine()) != null)
            {
                list.add(text);
            }
            return list;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    // 是否为需要备份的文件类型
    private boolean isExtraFile(String fileName, List<String> fileFilter)
    {
        for (String postfix : fileFilter)
        {
            if (fileName.endsWith(postfix))
            {
                return true;
            }
        }
        return false;
    }
    
    // 从备份文件恢复文件至dir
    public void createSourceFile(String bakFile, String dir)
    {
        File f = new File(bakFile);
        String separator = System.getProperty("file.separator");
        int beginIndex = bakFile.lastIndexOf(separator) + 1;
        int endIndex = bakFile.indexOf("_");
        String t;
        if (endIndex > 0)
        {
            t = bakFile.substring(beginIndex, endIndex) + separator;
        }
        else
        {
            t = bakFile.substring(beginIndex, bakFile.indexOf(".")) + separator;
        }
        dir = dir + t;
        List<String> list = getFiletext(f.getPath());
        BufferedWriter writer = null;
        for (String text : list)
        {
            try
            {
                if (text.trim().startsWith("//goto "))
                {
                    // close old file
                    if (writer != null)
                    {
                        writer.close();
                    }
                    // creat new file
                    int pos = text.indexOf("//goto ") + 7;
                    File file = new File(dir + text.substring(pos));
                    file.getParentFile().mkdirs();
                    OutputStream os = new FileOutputStream(file);
                    writer = new BufferedWriter(new OutputStreamWriter(os, "8859_1"), 8192);
                }
                else
                {
                    if (writer != null)
                    {
                        writer.write(text);
                        writer.newLine();
                    }
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        try
        {
            if (writer != null)
            {
                writer.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}