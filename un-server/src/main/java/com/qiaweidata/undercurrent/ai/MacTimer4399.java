package com.qiaweidata.undercurrent.ai;

import java.util.Timer;
import java.util.TimerTask;

import static com.qiaweidata.undercurrent.ai.AutoMouseEvent.pressDown;

public class MacTimer4399 {
    public static void main(String[] args) {

        taskRun();
    }

    private static void taskRun() {

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
                timer.schedule(closeTabAdTask, 5 * 1000);
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
        pressDown(813, 584);

        pressDown(813, 584);
    }

    /**
     * 点查看按钮,广告详细
     */
    private static void detailAdOk() {

        System.out.println("点广告详细");
        pressDown(1014, 911);
    }

    /**
     * 关闭新打开的广告tab页
     */
    private static void closeTabAd() {

        System.out.println("关闭新打开的广告tab页");
        pressDown(934, 44);
    }

    /**
     * 点击关闭广告按钮,预计重来
     */
    private static void clickCloseButton() {

        System.out.println("点击关闭广告按钮,预计重来");
        pressDown(1366, 236);
    }
}
