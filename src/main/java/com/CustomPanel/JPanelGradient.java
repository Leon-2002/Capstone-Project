package com.CustomPanel;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class JPanelGradient extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        // Set the rendering hints for anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create a rounded rectangle shape
        Shape shape = new RoundRectangle2D.Float(0, 0, width, height, 40, 40);

        // Set the clip to the rounded rectangle shape
        g2d.clip(shape);

        // Create the gradient colors
        Color color1 = new Color(19, 7, 46);
        Color color2 = new Color(63, 33, 130);
        GradientPaint gp = new GradientPaint(0, 0, color1, width, 0, color2);
        g2d.setPaint(gp);

        // Fill the rounded rectangle with the gradient
        g2d.fillRect(0, 0, width, height);
    }
}
