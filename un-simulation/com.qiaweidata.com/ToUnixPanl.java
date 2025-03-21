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
import org.apache.commons.lang3.StringUtils;

/**
 * 
 * 转换为unix换行符
 * 
 * @author 00fly
 * @version [版本号, 2022年4月1日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ToUnixPanl extends JPanel
{
    private static final long serialVersionUID = 4050369107824321460L;
    
    // 界面组件
    JLabel sourceLabel = new JLabel("项目源代码目录:");
    
    JTextField tsourcePath = new JTextField(null, 45);
    
    JButton sourceBrowse = new JButton("选择");
    
    JButton bakBrowse = new JButton("选择");
    
    JLabel fileTypeLabel = new JLabel("转换文件类型:");
    
    JCheckBox sh = new JCheckBox(".sh", true);
    
    JCheckBox yml = new JCheckBox(".yml", true);
    
    JCheckBox yaml = new JCheckBox(".yaml", true);
    
    JButton runButton = new JButton("开 始 处 理");
    
    JTextArea view = new JTextArea();
    
    public ToUnixPanl()
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
        
        sh.setBounds(100, 80, 100, 18);
        sh.setBackground(Color.WHITE);
        yml.setBounds(200, 80, 80, 18);
        yml.setBackground(Color.WHITE);
        yaml.setBounds(280, 80, 80, 18);
        yaml.setBackground(Color.WHITE);
        add(sh);
        add(yml);
        add(yaml);
        
        runButton.setBounds(360, 130, 120, 25);
        runButton.addActionListener(event -> {
            final String sourcePath = tsourcePath.getText();
            if ("".equals(sourcePath))
            {
                JOptionPane.showMessageDialog(null, "项目源代码目录不可为空!", "警告", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            final List<String> suffixList = new ArrayList<>();
            if (sh.isSelected())
            {
                suffixList.add("sh");
            }
            if (yml.isSelected())
            {
                suffixList.add("yml");
            }
            if (yaml.isSelected())
            {
                suffixList.add("yaml");
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
                                        content = StringUtils.trimToEmpty(content);
                                        // dos2unix
                                        content = content.replaceAll("\\r", "");
                                        FileUtils.writeStringToFile(file, content, "UTF-8");
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