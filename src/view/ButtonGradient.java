package view;

import java.awt.Font;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class ButtonGradient extends JButton {

    public float getSizeSpeed() {
        return sizeSpeed;
    }

    public void setSizeSpeed(float sizeSpeed) {
        this.sizeSpeed = sizeSpeed;
    }

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    private Color color1 = Color.decode("#DF83FF");
    private Color color2 = Color.decode("#8C64FF");
    private final Timer timer;
    private final Timer timerPressed;
    private float alpha = 0.3f;
    private boolean mouseOver;
    private boolean pressed;
    private Point pressedLocation;
    private float pressedSize;
    private float sizeSpeed = 1f;
    private float alphaPressed = 0.5f;
    private float alignX = 0.5f;
    private float alignY = 0.5f;
    
    private int cornerRadius = 20; // Default corner radius
    private int borderThickness = 2; // Default border thickness
    private Color borderColor = Color.BLACK; // Default border color

    // Getter and setter for corner radius
    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        repaint(); // Repaint to reflect changes
    }

    // Getter and setter for border thickness
    public int getBorderThickness() {
        return borderThickness;
    }

    public void setBorderThickness(int borderThickness) {
        this.borderThickness = borderThickness;
        repaint(); // Repaint to reflect changes
    }

    // Getter and setter for border color
    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint(); // Repaint to reflect changes
    }
    
    @Override
    public Insets getInsets() { 
        return new Insets(15, 10, 10, 10); // top, left, bottom, right
            }
            
    @Override
        public float getAlignmentX() {
            return alignX; // Center alignment (0.0 for left, 1.0 for right)
        }
            @Override
        public float getAlignmentY() {
            return alignY; // Center alignment (0.0 for left, 1.0 for right)
        }
       

    public ButtonGradient() {
        setContentAreaFilled(false);
        setForeground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(new EmptyBorder(10, 20, 10, 20));
        setFont(new Font("Gotham Medium", Font.PLAIN, 14));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                mouseOver = true;
                timer.start();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                mouseOver = false;
                timer.start();
            }

            @Override
            public void mousePressed(MouseEvent me) {
                pressedSize = 0;
                alphaPressed = 0.5f;
                pressed = true;
                pressedLocation = me.getPoint();
                timerPressed.setDelay(0);
                timerPressed.start();
            }
        });
        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (mouseOver) {
                    if (alpha < 0.6f) {
                        alpha += 0.05f;
                        repaint();
                    } else {
                        alpha = 0.6f;
                        timer.stop();
                        repaint();
                    }
                } else {
                    if (alpha > 0.3f) {
                        alpha -= 0.05f;
                        repaint();
                    } else {
                        alpha = 0.3f;
                        timer.stop();
                        repaint();
                    }
                }
            }
        });
        timerPressed = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                pressedSize += getSizeSpeed();
                if (alphaPressed <= 0) {
                    pressed = false;
                    timerPressed.stop();
                } else {
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        int width = getWidth();
        int height = getHeight();
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //  Create Gradients Color
        GradientPaint gra = new GradientPaint(0, height * -1, color1, 0, height, color2);
        g2.setPaint(gra);
        g2.fillRoundRect(0, 0, width, height, 20, 20);
        //  Add Style
 
        if (pressed) {
            paintPressed(g2);
        }
        g2.dispose();
        grphcs.drawImage(img, 0, 0, null);
        super.paintComponent(grphcs);
    }

   

    private void paintPressed(Graphics2D g2) {
        if (pressedLocation.x - (pressedSize / 2) < 0 && pressedLocation.x + (pressedSize / 2) > getWidth()) {
            timerPressed.setDelay(20);
            alphaPressed -= 0.05f;
            if (alphaPressed < 0) {
                alphaPressed = 0;
            }
        }
        g2.setColor(Color.WHITE);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alphaPressed));
        float x = pressedLocation.x - (pressedSize / 2);
        float y = pressedLocation.y - (pressedSize / 2);
        g2.fillOval((int) x, (int) y, (int) pressedSize, (int) pressedSize);
    }
}
