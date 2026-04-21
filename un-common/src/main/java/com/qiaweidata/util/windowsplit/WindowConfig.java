package com.qiaweidata.util.windowsplit;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class WindowConfig {
    
    // 窗口尺寸
    public static final Dimension WINDOW_SIZE = new Dimension(450, 220);
    
    // 窗口标题
    public static final String WINDOW_TITLE = "自动关闭窗口";
    
    // 消息文字配置
    public static final Font MESSAGE_FONT = new Font("微软雅黑", Font.PLAIN, 15);
    public static final Color MESSAGE_COLOR = new Color(33, 33, 33);
    
    // 倒计时文字配置
    public static final Font COUNTDOWN_FONT = new Font("微软雅黑", Font.PLAIN, 12);
    public static final Color COUNTDOWN_COLOR = new Color(100, 100, 100);
    
    // 边框边距
    public static final int BORDER_TOP = 20;
    public static final int BORDER_LEFT = 20;
    public static final int BORDER_BOTTOM = 20;
    public static final int BORDER_RIGHT = 20;
    
    // 默认倒计时秒数
    public static final int DEFAULT_COUNTDOWN_SECONDS = 3;
    
    // 定时器延迟（毫秒）
    public static final int TIMER_DELAY_MS = 1000;
    public static final int CLOSE_DELAY_MS = 3000;
    
    private WindowConfig() {
        // 工具类，禁止实例化
    }
}