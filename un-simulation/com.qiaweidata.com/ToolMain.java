package com.fly.code;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 * 项目编码，空行清除工具
 * 
 * @author 00fly
 * @version [版本号, 2018年8月29日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ToolMain extends JFrame
{
    private static final long serialVersionUID = 965289280779529771L;
    
    // 设置窗口风格的菜单
    JCheckBoxMenuItem windows = new JCheckBoxMenuItem("Windows", true);
    
    JCheckBoxMenuItem metal = new JCheckBoxMenuItem("Metal");
    
    // 构造
    public ToolMain()
    {
        // 加载图标
        URL imgURL = this.getClass().getResource("/icon.gif");
        if (imgURL != null)
        {
            Image image = getToolkit().createImage(imgURL);
            setIconImage(image);
        }
        setTitle("Java项目工具 V1.0");
        // 设置宽高
        setSize(560, 600);
        // 得到当前系统screenSize
        Dimension screenSize = getToolkit().getScreenSize();
        // 得到当前frameSize
        Dimension frameSize = this.getSize();
        // 自适应处理
        frameSize.height = Math.min(screenSize.height, frameSize.height);
        frameSize.width = Math.min(screenSize.width, frameSize.width);
        
        setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        addMenu();
        add(new TabPanel());
        setWindowStyle("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    // Menu set
    private void addMenu()
    {
        JMenuBar mb = new JMenuBar();
        JMenu windowStyle = new JMenu("窗口风格");
        windows.addActionListener((actionevent) -> {
            String plaf = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
            setWindowStyle(plaf);
            windows.setState(true);
            metal.setState(false);
        });
        
        metal.addActionListener((actionevent) -> {
            String plaf = "javax.swing.plaf.metal.MetalLookAndFeel";
            setWindowStyle(plaf);
            windows.setState(false);
            metal.setState(true);
        });
        JMenuItem exit = new JMenuItem("退出");
        exit.addActionListener(event -> System.exit(0));
        
        windowStyle.add(windows);
        windowStyle.add(metal);
        windowStyle.addSeparator();
        windowStyle.add(exit);
        mb.add(windowStyle);
        
        // 菜单2
        JMenu help = new JMenu("帮助");
        JMenuItem use = new JMenuItem("使用指南");
        use.addActionListener(event -> JOptionPane.showMessageDialog(null, "请按以下顺序操作：\n 1. 选择项目源代码目录。\n 2. 选择编码转换后保存目录。\n 3. 选择需要转换的文件类型。\n 4. 选择项目编码。\n 5. 开始转换。", "使用指南", JOptionPane.INFORMATION_MESSAGE));
        
        JMenuItem about = new JMenuItem("关于工具");
        about.addActionListener(event -> JOptionPane.showMessageDialog(null, "Java项目编码转换工具，00fly 于2014年11月。", "关于本工具", JOptionPane.INFORMATION_MESSAGE));
        help.add(use);
        help.addSeparator();
        help.add(about);
        mb.add(help);
        setJMenuBar(mb);
    }
    
    // 根据字串设置窗口外观
    private void setWindowStyle(String plaf)
    {
        try
        {
            // 设定用户界面的外观
            UIManager.setLookAndFeel(plaf);
            // 将用户界面改成当前设定的外观
            SwingUtilities.updateComponentTreeUI(this);
        }
        catch (Exception e)
        {
        }
    }
    
    // 卡片
    class TabPanel extends JPanel implements ChangeListener
    {
        private static final long serialVersionUID = 7226393795721621635L;
        
        JTabbedPane jtp = new JTabbedPane();
        
        EncodePanl encodePanl = new EncodePanl();
        
        ClearPanl clearPanl = new ClearPanl();
        
        ToFtlPanl toFtlPanl = new ToFtlPanl();
        
        ToUnixPanl toUnixPanl = new ToUnixPanl();
        
        TabPanel()
        {
            encodePanl.setVisible(true);
            clearPanl.setVisible(true);
            toFtlPanl.setVisible(true);
            toUnixPanl.setVisible(true);
            jtp.addTab(" 编码转换 ", encodePanl);
            jtp.addTab(" 清除空白行 ", clearPanl);
            jtp.addTab(" 转换为FTL ", toFtlPanl);
            jtp.addTab(" 转换为unix换行符", toUnixPanl);
            jtp.addChangeListener(this);
            setLayout(new BorderLayout());
            add(jtp, BorderLayout.CENTER);
        }
        
        @Override
        public void stateChanged(ChangeEvent e)
        {
            if (e.getSource() == jtp)
            {
                int i = ((JTabbedPane)e.getSource()).getSelectedIndex();
                if (i == 0)
                {
                    encodePanl.setVisible(true);
                    clearPanl.setVisible(false);
                    toFtlPanl.setVisible(false);
                    toUnixPanl.setVisible(false);
                }
                else if (i == 1)
                {
                    encodePanl.setVisible(false);
                    clearPanl.setVisible(true);
                    toFtlPanl.setVisible(false);
                    toUnixPanl.setVisible(false);
                }
                else if (i == 2)
                {
                    encodePanl.setVisible(false);
                    clearPanl.setVisible(false);
                    toFtlPanl.setVisible(true);
                    toUnixPanl.setVisible(false);
                }
                else
                {
                    encodePanl.setVisible(false);
                    clearPanl.setVisible(false);
                    toFtlPanl.setVisible(false);
                    toUnixPanl.setVisible(true);
                }
            }
        }
    }
    
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            new ToolMain();
        });
    }
}
