package com.qiaweidata.util.windowsplit;


public class WindowController {
    
    private final WindowModel model;
    private final AutoCloseWindow view;
    private TimerController timerController;
    
    public WindowController(WindowModel model, AutoCloseWindow view) {
        this.model = model;
        this.view = view;
        this.timerController = new TimerController(model, view, this);
    }
    
    public void initialize() {
        view.setVisible(true);
        timerController.startTimers();
    }
    
    public void onWindowClosed() {
        model.setClosed(true);
        timerController.stopAllTimers();
    }
    
    public void onCountdownTick(int currentSecond) {
        view.updateCountdownDisplay(currentSecond);
    }
    
    public void onTimeUp() {
        view.updateClosingDisplay();
        view.closeWindow();
    }
}