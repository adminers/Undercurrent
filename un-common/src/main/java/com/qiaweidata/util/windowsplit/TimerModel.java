package com.qiaweidata.util.windowsplit;

import javax.swing.Timer;

public class TimerModel {
    private Timer countdownTimer;
    private Timer closeTimer;
    private boolean isCountdownRunning;
    private boolean isCloseTimerRunning;
    
    public TimerModel() {
        this.isCountdownRunning = false;
        this.isCloseTimerRunning = false;
    }
    
    public Timer getCountdownTimer() {
        return countdownTimer;
    }
    
    public void setCountdownTimer(Timer countdownTimer) {
        this.countdownTimer = countdownTimer;
    }
    
    public Timer getCloseTimer() {
        return closeTimer;
    }
    
    public void setCloseTimer(Timer closeTimer) {
        this.closeTimer = closeTimer;
    }
    
    public boolean isCountdownRunning() {
        return isCountdownRunning;
    }
    
    public void setCountdownRunning(boolean countdownRunning) {
        isCountdownRunning = countdownRunning;
    }
    
    public boolean isCloseTimerRunning() {
        return isCloseTimerRunning;
    }
    
    public void setCloseTimerRunning(boolean closeTimerRunning) {
        isCloseTimerRunning = closeTimerRunning;
    }
    
    public void stopAllTimers() {
        if (countdownTimer != null && countdownTimer.isRunning()) {
            countdownTimer.stop();
        }
        if (closeTimer != null && closeTimer.isRunning()) {
            closeTimer.stop();
        }
        isCountdownRunning = false;
        isCloseTimerRunning = false;
    }
}