package com.fly.ui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.fly.utils.QRCodeUtil;

/*****
 * 界面操作展示类
 */
public class QrCodeUI extends JFrame
{
    private static final long serialVersionUID = -9154321945329564644L;
    
    // 界面组件
    JPanel panel = new JPanel();
    
    JTextArea textArea = new JTextArea();
    
    JTextField fileText = new JTextField(null, 40);
    
    JButton fileDirBrowse = new JButton("请选择");
    
    JButton removeButton = new JButton("清除空白行及空格");
    
    JButton qrButton = new JButton(" 生 成 二 维 码 ");
    
    JButton clearButton = new JButton(" 清 除 内 容 ");
    
    // 构造函数
    public QrCodeUI()
    {
        // 加载图标
        URL imgURL = getClass().getResource("/img/icon.gif");
        if (imgURL != null)
        {
            Image image = getToolkit().createImage(imgURL);
            setIconImage(image);
        }
        setTitle("二维码应用工具 V1.0");
        setSize(900, 550);
        Dimension screenSize = getToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        frameSize.height = Math.min(screenSize.height, frameSize.height);
        frameSize.width = Math.min(screenSize.width, frameSize.width);
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        addMenu();
        addButton();
        try
        {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        }
        catch (Exception e)
        {
        }
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        fileText.setFocusable(false);
    }
    
    // Menu set
    private void addMenu()
    {
        JMenuBar mb = new JMenuBar();
        // 一级菜单
        JMenu conf = new JMenu(" 系 统 ");
        // 子菜单
        JMenuItem exit = new JMenuItem("退出");
        exit.addActionListener(event -> System.exit(0));
        conf.add(exit);
        mb.add(conf);
        JMenu help = new JMenu(" 帮 助 ");
        JMenuItem about = new JMenuItem("关于工具");
        about.addActionListener((ActionEvent event) -> JOptionPane.showMessageDialog(null, "二维码应用工具 V1.0，00fly 于2023年3月。\n", "关于本工具", JOptionPane.INFORMATION_MESSAGE));
        help.add(about);
        mb.add(help);
        setJMenuBar(mb);
    }
    
    // JButton set
    private void addButton()
    {
        panel.setLayout(null);
        getContentPane().add(panel);
        
        JLabel textLabel = new JLabel("文件内容");
        textLabel.setBounds(20, 10, 120, 18);
        panel.add(textLabel);
        
        JLabel fileLabel = new JLabel(" 待生成QR原始文件");
        fileLabel.setBounds(30, 410, 240, 18);
        panel.add(fileLabel);
        
        textArea.setEditable(false);
        
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(20, 30, 850, 360);
        panel.add(scroll);
        
        fileText.setBounds(150, 410, 450, 24);
        fileText.setText(new File(" ").getAbsolutePath().trim());
        fileText.setToolTipText("选择需要生成二维码图片的原始文本文件，包含xml、java、yaml、md等");
        panel.add(fileText);
        fileDirBrowse.setBounds(610, 410, 80, 25);
        fileDirBrowse.setToolTipText("选择需要生成二维码图片的原始文本文件，包含xml、java、yaml、md等");
        fileDirBrowse.addActionListener(event -> {
            String path = fileText.getText();
            File xmlfile = new File(path);
            if (new File(fileText.getText()).exists())
            {
                xmlfile = new File(path).getParentFile();
            }
            JFileChooser fc = new JFileChooser(xmlfile);
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fc.setDialogTitle("原始文件选择");
            if (fc.showOpenDialog(null) == JFileChooser.CANCEL_OPTION)
            {
                return;
            }
            fc.setFileFilter(new FileFilter()
            {
                @Override
                public boolean accept(File file)
                {
                    String name = file.getName().toLowerCase();
                    return name.endsWith(".xml") || name.endsWith(".java") || name.endsWith(".yml") || name.endsWith(".yaml") || name.endsWith(".md");
                }
                
                @Override
                public String getDescription()
                {
                    return "";
                }
            });
            File f = fc.getSelectedFile();
            if (f != null && f.isFile())
            {
                fileText.setText(f.getAbsolutePath());
                try
                {
                    textArea.setText(FileUtils.readFileToString(f, "UTF-8"));
                    qrButton.setEnabled(true);
                }
                catch (IOException e)
                {
                }
            }
        });
        panel.add(fileDirBrowse);
        
        removeButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                String content = textArea.getText();
                if (StringUtils.isNotBlank(content))
                {
                    content = content.replaceAll("\t", " ").replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1");
                    textArea.setText(content);
                }
            }
        });
        removeButton.setBounds(100, 450, 160, 30);
        removeButton.add(qrButton);
        panel.add(removeButton);
        
        qrButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (!qrButton.isEnabled())
                {
                    return;
                }
                try
                {
                    String content = textArea.getText();
                    BufferedImage image = QRCodeUtil.createImage(content, "", false);
                    new ShowDialog(image);
                }
                catch (Exception e1)
                {
                    JOptionPane.showMessageDialog(null, "失败原因： " + e1.getMessage(), "二维码生成失败", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        qrButton.setBounds(350, 450, 160, 30);
        qrButton.setEnabled(false);
        panel.add(qrButton);
        
        clearButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                textArea.setText(null);
                qrButton.setEnabled(false);
            }
        });
        clearButton.setBounds(600, 450, 160, 30);
        panel.add(clearButton);
    }
    
    // Run
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new QrCodeUI());
    }
}
