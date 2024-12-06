
package com.CustomPanel;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class menuPanel extends JPanel {
    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create the gradient from top (#302b63) to bottom (#24243e)
        GradientPaint gradient = new GradientPaint(0, 0, Color.decode("#13072E"), 0, getHeight(), Color.decode("#3F2182"));
        g2.setPaint(gradient);

        // Fill the panel with the gradient
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Call super to paint child components
        super.paintChildren(grphcs);
    }
}

