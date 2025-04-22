package com.qiaweidata.undercurrent.ai;


import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @Title: AutoMouseEvent
 * @Description: AutoMouseEvent
 * @date: 2023-11-23
 * @version: V1.0
 */
public class AutoMouseEvent {

    public static void pressDown(int x, int y)  {

        try {
            Robot myRobot = new Robot();

            // 移动鼠标到坐标（x,y)处，并点击左键
            myRobot.mouseMove(x, y);

            // 模拟按下鼠标左键
            myRobot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);

            // 模拟释放鼠标左键
            myRobot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);

            // 设置每次输入的延迟为200ms
            myRobot.setAutoDelay(200);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
