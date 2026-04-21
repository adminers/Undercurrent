package com.qiaweidata.util.windowsplit;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerController {
    
    private final WindowModel model;
    private final AutoCloseWindow view;
    private final WindowController windowController;
    private final TimerModel timerModel;
    
    public TimerController(WindowModel model, AutoCloseWindow view, WindowController windowController) {
        this.model = model;
        this.view = view;
        this.windowController = windowController;
        this.timerModel = new TimerModel();
        setupWindowListener();
    }
    
    private void setupWindowListener() {
        view.addWindowListener(new WindowCloseListener(windowController));
    }
    
    public void startTimers() {
        startCountdownTimer();
        startCloseTimer();
    }
    
    private void startCountdownTimer() {
        Timer countdownTimer = new Timer(WindowConfig.TIMER_DELAY_MS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.decrementSecond();
                windowController.onCountdownTick(model.getCurrentSecond());
                
                if (model.isTimeUp()) {
                    stopCountdownTimer();
                }
            }
        });
        
        timerModel.setCountdownTimer(countdownTimer);
        timerModel.setCountdownRunning(true);
        countdownTimer.start();
    }
    
    private void startCloseTimer() {
        Timer closeTimer = new Timer(WindowConfig.CLOSE_DELAY_MS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowController.onTimeUp();
                stopCloseTimer();
            }
        });
        
        closeTimer.setRepeats(false);
        timerModel.setCloseTimer(closeTimer);
        timerModel.setCloseTimerRunning(true);
        closeTimer.start();
    }
    
    private void stopCountdownTimer() {
        if (timerModel.getCountdownTimer() != null) {
            timerModel.getCountdownTimer().stop();
            timerModel.setCountdownRunning(false);
        }
    }
    
    private void stopCloseTimer() {
        if (timerModel.getCloseTimer() != null) {
            timerModel.getCloseTimer().stop();
            timerModel.setCloseTimerRunning(false);
        }
    }
    
    public void stopAllTimers() {
        timerModel.stopAllTimers();
    }
}