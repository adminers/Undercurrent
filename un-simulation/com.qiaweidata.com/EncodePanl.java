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
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.commons.io.FileUtils;

/**
 * 
 * 编码转换
 * 
 * @author 00fly
 * @version [版本号, 2018年10月1日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class EncodePanl extends JPanel
{
    private static final long serialVersionUID = 4050369107824321460L;
    
    // 界面组件
    JLabel sourceLabel = new JLabel("项目源代码目录:");
    
    JTextField tsourcePath = new JTextField(null, 45);
    
    JButton sourceBrowse = new JButton("选择");
    
    JLabel bakeLabel = new JLabel("转换后保存目录:");
    
    JTextField tbakPath = new JTextField(null, 45);
    
    JButton bakBrowse = new JButton("选择");
    
    JLabel fileTypeLabel = new JLabel("转换文件类型:");
    
    JCheckBox jb1 = new JCheckBox(".java", true);
    
    JCheckBox jb2 = new JCheckBox(".jsp", true);
    
    JCheckBox jb3 = new JCheckBox(".xml", true);
    
    JCheckBox jb4 = new JCheckBox(".css", true);
    
    JCheckBox jb5 = new JCheckBox(".js", true);
    
    JCheckBox jb6 = new JCheckBox(".properties");
    
    JCheckBox jb7 = new JCheckBox(".sql");
    
    JCheckBox jb8 = new JCheckBox(".htm");
    
    JCheckBox jb9 = new JCheckBox(".html");
    
    JCheckBox jb10 = new JCheckBox(".vm");
    
    JLabel encodeLabel = new JLabel("将项目编码:  从");
    
    JComboBox<String> oldEncode = new JComboBox<>();
    
    JLabel newLabel = new JLabel("转换到");
    
    JComboBox<String> newEncode = new JComboBox<>();
    
    JButton runButton = new JButton("开 始 转 换");
    
    JTextArea view = new JTextArea();
    
    public EncodePanl()
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
        
        // 备份目录
        bakeLabel.setBounds(20, 45, 120, 18);
        add(bakeLabel);
        tbakPath.setBounds(120, 45, 300, 20);
        tbakPath.setFocusable(false);
        tbakPath.setText(new File(" ").getAbsolutePath().trim());
        add(tbakPath);
        bakBrowse.setBounds(430, 45, 70, 20);
        bakBrowse.addActionListener(event -> {
            String text = tbakPath.getText();
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
                tbakPath.setText(f.getAbsolutePath());
            }
            else
            {
                tbakPath.setText(f.getAbsolutePath() + "\\");
            }
        });
        add(bakBrowse);
        
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
        
        // 项目编码
        encodeLabel.setBounds(20, 130, 120, 18);
        add(encodeLabel);
        oldEncode.setBounds(120, 130, 70, 20);
        oldEncode.addItem("UTF-8");
        oldEncode.addItem("GB2312");
        oldEncode.addItem("GBK");
        add(oldEncode);
        
        newLabel.setBounds(200, 130, 120, 18);
        add(newLabel);
        newEncode.setBounds(250, 130, 70, 20);
        newEncode.addItem("UTF-8");
        newEncode.addItem("GB2312");
        newEncode.addItem("GBK");
        newEncode.setSelectedIndex(2);
        add(newEncode);
        
        runButton.setBounds(360, 130, 120, 25);
        runButton.addActionListener(event -> {
            final String sourcePath = tsourcePath.getText();
            if ("".equals(sourcePath))
            {
                JOptionPane.showMessageDialog(null, "项目源代码目录不可为空!", "警告", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            final String bakPath = tbakPath.getText();
            if ("".equals(bakPath))
            {
                JOptionPane.showMessageDialog(null, "转换后保存目录不可为空!", "警告", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            final List<String> list = new ArrayList<>();
            if (jb1.isSelected())
            {
                list.add("java");
            }
            if (jb2.isSelected())
            {
                list.add("jsp");
            }
            if (jb3.isSelected())
            {
                list.add("xml");
            }
            if (jb4.isSelected())
            {
                list.add("css");
            }
            if (jb5.isSelected())
            {
                list.add("js");
            }
            if (jb6.isSelected())
            {
                list.add("properties");
            }
            if (jb7.isSelected())
            {
                list.add("sql");
            }
            if (jb8.isSelected())
            {
                list.add("htm");
            }
            if (jb9.isSelected())
            {
                list.add("html");
            }
            if (jb10.isSelected())
            {
                list.add("vm");
            }
            if (list.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "请选择转换文件类型!", "警告", JOptionPane.WARNING_MESSAGE);
                return;
            }
            final String oldE = (String)oldEncode.getSelectedItem();
            final String newE = (String)newEncode.getSelectedItem();
            if (oldE.equals(newE))
            {
                JOptionPane.showMessageDialog(null, "请选择不同的编码!", "警告", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try
            {
                view.setText(null);
                runButton.setText("转换中。。。");
                runButton.setEnabled(false);
                Thread worker = new Thread()
                {
                    @Override
                    public void run()
                    {
                        Collection<File> files = FileUtils.listFiles(new File(sourcePath), (String[])list.toArray(new String[0]), true);
                        String dirName = new File(sourcePath).getName();
                        String newPath = bakPath + dirName + "\\";
                        for (File file : files)
                        {
                            String srcFilePath = newPath + file.getAbsolutePath().substring(sourcePath.length());
                            try
                            {
                                FileUtils.writeLines(new File(srcFilePath), newE, FileUtils.readLines(file, oldE));
                            }
                            catch (IOException e)
                            {
                                view.setText(file.getAbsolutePath() + "------ 转换失败 ---- \n" + view.getText());
                            }
                            view.setText(file.getAbsolutePath() + " 转换完成 \n" + view.getText());
                        }
                        SwingUtilities.invokeLater(() -> {
                            runButton.setText("开 始 转 换");
                            runButton.setEnabled(true);
                            if (JOptionPane.showConfirmDialog(null, "处理完成，是否直接查看生成的代码?", "查看项目代码", 0) == 0)
                            {
                                try
                                {
                                    java.awt.Desktop.getDesktop().open(new File(bakPath));
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
                JOptionPane.showMessageDialog(null, "Java项目编码转换失败！", "错误", JOptionPane.ERROR_MESSAGE);
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