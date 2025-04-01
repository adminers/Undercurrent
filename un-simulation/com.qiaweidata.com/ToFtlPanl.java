package com.fly.code;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;

/**
 * 
 * 转换为FTL文件
 * 
 * @author 00fly
 * @version [版本号, 2018年10月1日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ToFtlPanl extends JPanel
{
    private static final long serialVersionUID = 4050369107824321460L;
    
    // 界面组件
    JLabel sourceLabel = new JLabel("项目源代码目录:");
    
    JTextField tsourcePath = new JTextField(null, 45);
    
    JButton sourceBrowse = new JButton("选择");
    
    JButton bakBrowse = new JButton("选择");
    
    JLabel fileTypeLabel = new JLabel("转换文件类型:");
    
    JCheckBox jb1 = new JCheckBox(".java", true);
    
    JCheckBox jb2 = new JCheckBox(".jsp", true);
    
    JCheckBox jb3 = new JCheckBox(".xml", true);
    
    JCheckBox jb4 = new JCheckBox(".css");
    
    JCheckBox jb5 = new JCheckBox(".js");
    
    JCheckBox jb6 = new JCheckBox(".properties", true);
    
    JCheckBox jb7 = new JCheckBox(".sql");
    
    JCheckBox jb8 = new JCheckBox(".htm");
    
    JCheckBox jb9 = new JCheckBox(".html");
    
    JCheckBox jb10 = new JCheckBox(".vm");
    
    JButton runButton = new JButton("开 始 处 理");
    
    JTextArea view = new JTextArea();
    
    public ToFtlPanl()
    {
        super();
        setLayout(null);
        setBackground(Color.WHITE);
        // 项目源文件路径
        sourceLabel.setBounds(20, 10, 120, 18);
        add(sourceLabel);
        tsourcePath.setFocusable(false);
        tsourcePath.setBounds(120, 10, 300, 20);
        add(tsourcePath);
        sourceBrowse.setBounds(430, 10, 70, 20);
        
        sourceBrowse.addActionListener(event -> {
            String text = tsourcePath.getText();
            if (text.length() == 0)
            {
                text = new File(" ").getAbsolutePath().trim();
            }
            JFileChooser fc = new JFileChooser(text);
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fc.setVisible(true);
            if (fc.showOpenDialog(null) == 1)
            {
                return;
            }
            File f = fc.getSelectedFile();
            if (f.getPath().endsWith("\\"))
            {
                tsourcePath.setText(f.getAbsolutePath());
            }
            else
            {
                tsourcePath.setText(f.getAbsolutePath() + "\\");
            }
        });
        add(sourceBrowse);
        
        // 文件类型
        fileTypeLabel.setBounds(20, 80, 120, 18);
        add(fileTypeLabel);
        
        jb1.setBounds(100, 80, 100, 18);
        jb1.setBackground(Color.WHITE);
        jb2.setBounds(200, 80, 80, 18);
        jb2.setBackground(Color.WHITE);
        jb3.setBounds(280, 80, 80, 18);
        jb3.setBackground(Color.WHITE);
        jb4.setBounds(360, 80, 80, 18);
        jb4.setBackground(Color.WHITE);
        jb5.setBounds(440, 80, 80, 18);
        jb5.setBackground(Color.WHITE);
        
        jb6.setBounds(100, 100, 100, 18);
        jb6.setBackground(Color.WHITE);
        jb7.setBounds(200, 100, 80, 18);
        jb7.setBackground(Color.WHITE);
        jb8.setBounds(280, 100, 80, 18);
        jb8.setBackground(Color.WHITE);
        jb9.setBounds(360, 100, 80, 18);
        jb9.setBackground(Color.WHITE);
        jb10.setBounds(440, 100, 80, 18);
        jb10.setBackground(Color.WHITE);
        add(jb1);
        add(jb2);
        add(jb3);
        add(jb4);
        add(jb5);
        add(jb6);
        add(jb7);
        add(jb8);
        add(jb9);
        add(jb10);
        
        runButton.setBounds(360, 130, 120, 25);
        runButton.addActionListener(event -> {
            final String sourcePath = tsourcePath.getText();
            if ("".equals(sourcePath))
            {
                JOptionPane.showMessageDialog(null, "项目源代码目录不可为空!", "警告", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            final List<String> suffixList = new ArrayList<>();
            if (jb1.isSelected())
            {
                suffixList.add("java");
            }
            if (jb2.isSelected())
            {
                suffixList.add("jsp");
            }
            if (jb3.isSelected())
            {
                suffixList.add("xml");
            }
            if (jb4.isSelected())
            {
                suffixList.add("css");
            }
            if (jb5.isSelected())
            {
                suffixList.add("js");
            }
            if (jb6.isSelected())
            {
                suffixList.add("properties");
            }
            if (jb7.isSelected())
            {
                suffixList.add("sql");
            }
            if (jb8.isSelected())
            {
                suffixList.add("htm");
            }
            if (jb9.isSelected())
            {
                suffixList.add("html");
            }
            if (jb10.isSelected())
            {
                suffixList.add("vm");
            }
            if (suffixList.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "请选择转换文件类型!", "警告", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try
            {
                view.setText(null);
                runButton.setText("处理中。。。");
                runButton.setEnabled(false);
                Thread worker = new Thread()
                {
                    @Override
                    public void run()
                    {
                        String path = sourcePath;
                        for (String suffix : suffixList)
                        {
                            Collection<?> listFiles = FileUtils.listFiles(new File(path), FileFilterUtils.suffixFileFilter(suffix), DirectoryFileFilter.INSTANCE);
                            for (Object object : listFiles)
                            {
                                if (object instanceof File)
                                {
                                    File file = (File)object;
                                    try
                                    {
                                        String content = FileUtils.readFileToString(file, "UTF-8");
                                        content = content.replace("${", "$\\{");
                                        File newFile = new File(file.getAbsolutePath() + ".ftl");
                                        FileUtils.writeStringToFile(newFile, content, "UTF-8");
                                        view.setText(file.getAbsolutePath() + " 处理完成 \n" + view.getText());
                                    }
                                    catch (IOException e)
                                    {
                                        view.setText(file.getAbsolutePath() + "------ 处理失败 ---- \n" + view.getText());
                                    }
                                }
                            }
                        }
                        SwingUtilities.invokeLater(() -> {
                            runButton.setText("开 始 处 理");
                            runButton.setEnabled(true);
                            if (JOptionPane.showConfirmDialog(null, "处理完成，是否查看?", "查看项目代码", 0) == 0)
                            {
                                try
                                {
                                    java.awt.Desktop.getDesktop().open(new File(sourcePath));
                                }
                                catch (IOException e)
                                {
                                }
                            }
                        });
                    }
                };
                worker.start();
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, "Java项目代码处理失败！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(runButton);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 170, 526, 320);
        add(scrollPane);
        
        view.setEditable(false);
        scrollPane.setViewportView(view);
        
        JLabel label = new JLabel();
        label.setText("Java项目编码转换工具  By 00fly");
        label.setBounds(348, 500, 200, 18);
        add(label);
        
        JLabel httpLabel = new JLabel();
        httpLabel.setText("<html><a href='http://00fly.online'>http://00fly.online</a></html>");
        httpLabel.setToolTipText("单击直达主页~~");
        httpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        httpLabel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                try
                {
                    Runtime.getRuntime().exec("cmd.exe /c start http://00fly.online");
                }
                catch (Exception ex)
                {
                }
            }
        });
        httpLabel.setBounds(10, 500, 146, 18);
        add(httpLabel);
    }
}