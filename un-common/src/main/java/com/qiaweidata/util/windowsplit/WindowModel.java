package com.qiaweidata.util.windowsplit;

public class WindowModel {
    private String message;
    private int countdownSeconds;
    private boolean isClosed;
    private int currentSecond;
    
    public WindowModel(String message, int countdownSeconds) {
        this.message = message;
        this.countdownSeconds = countdownSeconds;
        this.currentSecond = countdownSeconds;
        this.isClosed = false;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public int getCountdownSeconds() {
        return countdownSeconds;
    }
    
    public void setCountdownSeconds(int countdownSeconds) {
        this.countdownSeconds = countdownSeconds;
    }
    
    public boolean isClosed() {
        return isClosed;
    }
    
    public void setClosed(boolean closed) {
        isClosed = closed;
    }
    
    public int getCurrentSecond() {
        return currentSecond;
    }
    
    public void setCurrentSecond(int currentSecond) {
        this.currentSecond = currentSecond;
    }
    
    public void decrementSecond() {
        if (currentSecond > 0) {
            currentSecond--;
        }
    }
    
    public void resetCurrentSecond() {
        this.currentSecond = countdownSeconds;
    }
    
    public boolean isTimeUp() {
        return currentSecond <= 0;
    }
}