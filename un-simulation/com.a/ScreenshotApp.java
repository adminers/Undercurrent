package com.qiaweidata.un.codegee;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ScreenshotApp {

    private JFrame frame;
    private JButton screenshotButton;
    private JButton saveButton;
    private File outputFile;
    private Rectangle selectionRectangle;

    public ScreenshotApp() {
        frame = new JFrame("Screenshot App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        screenshotButton = new JButton("Screenshot");
        screenshotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectScreenArea();
            }
        });

        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveScreenshot();
            }
        });
        saveButton.setEnabled(false);

        JPanel panel = new JPanel();
        panel.add(screenshotButton);
        panel.add(saveButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void selectScreenArea() {
        try {
            Robot robot = new Robot();
            BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            SelectionPanel selectionPanel = new SelectionPanel(screenShot);
            frame.getContentPane().removeAll();
            frame.add(selectionPanel);
            frame.revalidate();
            frame.repaint();

            selectionPanel.registerCallback(new SelectionCallback() {
                @Override
                public void onSelection(Rectangle rectangle) {
                    selectionRectangle = rectangle;
                    saveButton.setEnabled(true);
                    frame.getContentPane().removeAll();
                    frame.add(new JPanel());
                    frame.revalidate();
                    frame.repaint();
                }
            });
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private void saveScreenshot() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                outputFile = fileChooser.getSelectedFile();
                Robot robot = new Robot();
                BufferedImage screenshot = robot.createScreenCapture(selectionRectangle);
                ImageIO.write(screenshot, "png", outputFile);
                saveButton.setEnabled(false);
            }
        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ScreenshotApp();
    }

    private interface SelectionCallback {
        void onSelection(Rectangle rectangle);
    }

    private class SelectionPanel extends JPanel {

        private BufferedImage image;
        private Rectangle selection;
        private SelectionCallback callback;

        public SelectionPanel(BufferedImage image) {
            this.image = image;
            setLayout(null);
            setOpaque(false);
            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    selection = new Rectangle(evt.getX(), evt.getY(), 0, 0);
                    repaint();
                }
                @Override
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    callback.onSelection(selection);
                }
            });
            addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                @Override
                public void mouseDragged(java.awt.event.MouseEvent evt) {
                    int x = evt.getX();
                    int y = evt.getY();
                    int width = x - selection.x;
                    int height = y - selection.y;
                    selection.setSize(width, height);
                    repaint();
                }
            });
        }

        public void registerCallback(SelectionCallback callback) {
            this.callback = callback;
        }

        @Override
        public void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, null);
            if (selection != null) {
                g.drawRect(selection.x, selection.y, selection.width, selection.height);
            }
        }

    }

}