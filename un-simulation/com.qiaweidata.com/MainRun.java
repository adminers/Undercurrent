package com.fly;

import javax.swing.SwingUtilities;

import org.apache.commons.lang3.RandomUtils;

import com.fly.ui.QrCodeUI;
import com.fly.ui.SimpleQrCodeUI;

/**
 * 
 * MainRun
 * 
 * @author 00fly
 * @version [版本号, 2023年3月5日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MainRun
{
    public static void main(String[] args)
    {
        // 随机运行
        boolean input = RandomUtils.nextBoolean();
        if (input)
        {
            SwingUtilities.invokeLater(() -> new QrCodeUI());
            return;
        }
        SwingUtilities.invokeLater(() -> new SimpleQrCodeUI());
        
    }
}
