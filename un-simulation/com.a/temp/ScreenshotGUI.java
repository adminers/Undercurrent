package com.qiaweidata.un.codegee.temp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ScreenshotGUI extends JFrame implements ActionListener {
    private JButton screenshotButton;
    private JButton okButton;
    private JButton resetButton;
    private Rectangle rect;
    private boolean selected;

    public ScreenshotGUI() {
        super("Screenshot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        screenshotButton = new JButton("Screenshot");
        screenshotButton.addActionListener(this);
        add(screenshotButton, BorderLayout.NORTH);

        okButton = new JButton("OK");
        okButton.addActionListener(this);
        okButton.setVisible(false);
        add(okButton, BorderLayout.SOUTH);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        resetButton.setVisible(false);
        add(resetButton, BorderLayout.EAST);

        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == screenshotButton) {
            selected = false;
            getContentPane().removeAll();
            getContentPane().add(new ScreenshotPanel());
            getContentPane().revalidate();
            getContentPane().repaint();
        } else if (event.getSource() == okButton) {
            if (selected) {
                try {
                    Robot robot = new Robot();
                    BufferedImage image = robot.createScreenCapture(rect);
                    ImageIO.write(image, "png", new File("F:/temp/screenshot.png"));
                    JOptionPane.showMessageDialog(this, "Saved screenshot to F:/temp/screenshot.png");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error saving screenshot: " + ex.getMessage());
                }
            }
            selected = false;
            rect = null;
            getContentPane().removeAll();
            getContentPane().add(screenshotButton, BorderLayout.NORTH);
            getContentPane().add(resetButton, BorderLayout.EAST);
            getContentPane().revalidate();
            getContentPane().repaint();
        } else if (event.getSource() == resetButton) {
            selected = false;
            rect = null;
            getContentPane().removeAll();
            getContentPane().add(screenshotButton, BorderLayout.NORTH);
            getContentPane().revalidate();
            getContentPane().repaint();
        }
    }

    private class ScreenshotPanel extends JPanel implements MouseListener, MouseMotionListener {
        private Point startPoint;

        public ScreenshotPanel() {
            super();
            setPreferredSize(new Dimension(500, 500));
            addMouseListener(this);
            addMouseMotionListener(this);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (startPoint != null && rect != null) {
                ((Graphics2D) g).draw(rect);
                g.setColor(new Color(255, 255, 255, 150));
                g.fillRect(rect.x, rect.y, rect.width, rect.height);
            }
        }

        public void mouseClicked(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
            startPoint = e.getPoint();
        }

        public void mouseReleased(MouseEvent e) {
            rect = new Rectangle(startPoint, new Dimension(e.getX() - startPoint.x, e.getY() - startPoint.y));
            selected = true;
            repaint();
            okButton.setVisible(true);
            resetButton.setVisible(true);
        }

        public void mouseDragged(MouseEvent e) {
            rect = new Rectangle(startPoint, new Dimension(e.getX() - startPoint.x, e.getY() - startPoint.y));
            repaint();
        }

        public void mouseMoved(MouseEvent e) {
        }
    }

    public static void main(String[] args) {
        new ScreenshotGUI();
    }
}