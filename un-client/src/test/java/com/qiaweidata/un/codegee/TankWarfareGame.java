package com.qiaweidata.un.codegee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serial;

public class TankWarfareGame extends JFrame {
    @Serial
    private static final long serialVersionUID = -717026255213192071L;
    private JPanel gamePanel;
    private CustomPanel customPanel;
    private Tank tank1, tank2;

    public TankWarfareGame() {
        super("Tank Warfare Game");
        setSize(500, 500);

        gamePanel = new JPanel(new BorderLayout());
        customPanel = new CustomPanel();
        gamePanel.add(customPanel);

        getContentPane().add(gamePanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Create two tanks at random positions
        tank1 = new Tank(new Point(50, 50), 100);
        tank2 = new Tank(new Point(400, 400), 100);

        // Add controls for player 1 and player 2
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        tank1.move(Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        tank1.move(Direction.RIGHT);
                        break;
                    case KeyEvent.VK_UP:
                        tank1.move(Direction.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        tank1.move(Direction.DOWN);
                        break;
                    case KeyEvent.VK_SPACE:
                        tank1.fire();
                        break;
                }
                customPanel.repaint();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_A:
                        tank2.move(Direction.LEFT);
                        break;
                    case KeyEvent.VK_D:
                        tank2.move(Direction.RIGHT);
                        break;
                    case KeyEvent.VK_W:
                        tank2.move(Direction.UP);
                        break;
                    case KeyEvent.VK_S:
                        tank2.move(Direction.DOWN);
                        break;
                    case KeyEvent.VK_SHIFT:
                        tank2.fire();
                        break;
                }
                customPanel.repaint();
            }
        });

        // Start the game loop
        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tank1.update();
                tank2.update();
                customPanel.repaint();
            }
        });
        timer.start();
    }

    private class CustomPanel extends JPanel {
        @Serial
        private static final long serialVersionUID = 8821347562428317689L;

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Render tanks, bullets, and other game objects
            tank1.render(g2d);
            tank2.render(g2d);
        }
    }

    private class Tank {
        private Point position;
        private int health;
        private Bullet bullet;

        public Tank(Point position, int health) {
            this.position = position;
            this.health = health;
            this.bullet = new Bullet(new Point(position.x + 20, position.y + 20),
                    new Point(0, 0), 10, 10, 10);
        }

        public void move(Direction direction) {
            Point newPosition = new Point(position.x, position.y);
            switch(direction) {
                case LEFT:
                    newPosition.x -= 5;
                    break;
                case RIGHT:
                    newPosition.x += 5;
                    break;
                case UP:
                    newPosition.y -= 5;
                    break;
                case DOWN:
                    newPosition.y += 5;
                    break;
            }
            if (newPosition.x >= 0 && newPosition.x <= 480 &&
                    newPosition.y >= 0 && newPosition.y <= 480) {
                position = newPosition;
                bullet.setPosition(new Point(position.x + 20, position.y + 20));
            }
        }

        public void fire() {
            bullet.setVelocity(new Point(1, 1));
            // Play bullet sound effect
        }

        public void update() {
            bullet.update();
        }

        public void render(Graphics2D g2d) {
            g2d.setColor(Color.RED);
            g2d.fillRect(position.x, position.y, 40, 40);
            bullet.render(g2d);
        }
    }

    private class Bullet {
        private Point position;
        private Point velocity;
        private int damage;
        private int width;
        private int height;

        public Bullet(Point position, Point velocity, int damage, int width, int height) {
            this.position = position;
            this.velocity = velocity;
            this.damage = damage;
            this.width = width;
            this.height = height;
        }

        public void setPosition(Point position) {
            this.position = position;
        }

        public void setVelocity(Point velocity) {
            this.velocity = velocity;
        }

        public void update() {
            position.x += velocity.x;
            position.y += velocity.y;
        }

        public void render(Graphics2D g2d) {
            g2d.setColor(Color.BLACK);
            g2d.fillRect(position.x, position.y, width, height);
        }
    }

    private enum Direction {
        LEFT, RIGHT, UP, DOWN
    }
}