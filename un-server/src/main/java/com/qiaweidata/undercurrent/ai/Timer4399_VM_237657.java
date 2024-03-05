package com.qiaweidata.undercurrent.ai;

import java.util.Timer;
import java.util.TimerTask;

import static com.qiaweidata.undercurrent.ai.AutoMouseEvent.pressDown;

/**
 * @Title: 数字魔力之韵
 * @Description:
 * @Company: www.fineplug.top
 * @author: shenshilong[shilong_shen@163.com]
 * @date: 2024-3-05
 * @version: v1.0
 */
public class Timer4399_VM_237657 {
    public static void main(String[] args) {

        Timer4399_VM_237657.taskRun();
        Timer4399_VM_238556_4.taskRun();
    }

    public static void taskRun() {

        Timer timer = new Timer();

        TimerTask timerCancelTask = new TimerTask() {
            @Override
            public void run() {
//              getMail();
                timer.cancel(); // 取消定时器
                taskRun();
            }
        };

        TimerTask clickCloseButtonTask = new TimerTask() {
            @Override
            public void run() {
                clickCloseButton();
                timer.schedule(timerCancelTask, 100);
            }
        };

        TimerTask closeTabAdTask = new TimerTask() {
            @Override
            public void run() {
                closeTabAd();
                timer.schedule(clickCloseButtonTask, 10 * 1000);
            }
        };

        TimerTask detailAdOkTask = new TimerTask() {
            @Override
            public void run() {
                detailAdOk();
                timer.schedule(closeTabAdTask, 10 * 1000);
            }
        };

        TimerTask lookAdOkTask = new TimerTask() {
            @Override
            public void run() {
                lookAdOk();
                timer.schedule(detailAdOkTask, 20 * 1000);
            }
        };
        timer.schedule(lookAdOkTask, 10 * 1000);
    }

    /**
     * 看广告
     */
    private static void lookAdOk() {

        System.out.println("执行点击中间按钮,开始看广告");
        pressDown(1235, 600);

        pressDown(1235, 600);
    }

    /**
     * 点查看按钮,广告详细
     */
    private static void detailAdOk() {

        System.out.println("点广告详细");
        pressDown(1439, 751);
    }

    /**
     * 关闭新打开的广告tab页
     */
    private static void closeTabAd() {

        System.out.println("关闭新打开的广告tab页");
        pressDown(1315, 210);
    }

    /**
     * 点击关闭广告按钮,预计重来
     */
    private static void clickCloseButton() {

        System.out.println("点击关闭广告按钮,预计重来");
        pressDown(1509, 430);
    }
}