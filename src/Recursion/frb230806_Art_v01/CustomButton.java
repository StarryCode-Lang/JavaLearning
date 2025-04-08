package Recursion.frb230806_Art_v01;

import javax.swing.*;
import java.awt.*;

class CustomButton extends JButton {
    private static final int BORDER_RADIUS = 15;

    public CustomButton(String text) {
        super(text);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);

        // Set the font and text color
        setFont(new Font("Arial", Font.BOLD, 30));
        setForeground(Color.WHITE);

        // Set the button gradient background
        setBackground(new Color(40, 130, 220));
        setPreferredSize(new Dimension(120, 40));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();

        // Create the gradient for the background
        GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(40, 130, 220), 0, height, new Color(30, 100, 180));
        g2d.setPaint(gradientPaint);

        // Create a rounded rectangle for the button
        g2d.fillRoundRect(0, 0, width, height, BORDER_RADIUS, BORDER_RADIUS);
        g2d.dispose();

        super.paintComponent(g);
    }
}
