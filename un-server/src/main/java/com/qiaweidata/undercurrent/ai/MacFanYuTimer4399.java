package com.qiaweidata.undercurrent.ai;

import java.util.Timer;
import java.util.TimerTask;

import static com.qiaweidata.undercurrent.ai.AutoMouseEvent.pressDown;

/**
 * 星河防御，窗口在左边，最左，不能缩小为止
 * 导航条弄到，基本看不见即可
 */
public class MacFanYuTimer4399 {
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
        pressDown(345, 629);

        pressDown(345, 629);
    }

    /**
     * 点查看按钮,广告详细
     */
    private static void detailAdOk() {

        System.out.println("点广告详细");
        pressDown(532, 1007);
    }

    /**
     * 关闭新打开的广告tab页
     */
    private static void closeTabAd() {

        System.out.println("关闭新打开的广告tab页");
        pressDown(500, 45);
    }

    /**
     * 点击关闭广告按钮,预计重来
     */
    private static void clickCloseButton() {

        System.out.println("点击关闭广告按钮,预计重来");
        pressDown(549, 191);

        Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {

            /**
             * The action to be performed by this timer task.
             */
            @Override
            public void run() {
                clickRestartButton();
            }
        }, 2 * 1000);
    }

    /**
     * 点击关闭广告按钮,预计重来
     */
    private static void clickRestartButton() {

        System.out.println("点击关闭广告按钮,预计重来");
        pressDown(352, 606);

        Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {

            /**
             * The action to be performed by this timer task.
             */
            @Override
            public void run() {
                clickAddStrongButton();
            }
        }, 2 * 1000);
    }

    private static void clickAddStrongButton() {

        System.out.println("点击关闭广告按钮,预计重来");
        pressDown(547, 448);

    }
}
