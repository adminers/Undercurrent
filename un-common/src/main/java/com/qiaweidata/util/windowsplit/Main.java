package com.qiaweidata.util.windowsplit;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WindowModel model = new WindowModel("这是一段提示文字", 3);
            AutoCloseWindow view = new AutoCloseWindow(model);
            WindowController controller = new WindowController(model, view);
            controller.initialize();
        });
    }
}