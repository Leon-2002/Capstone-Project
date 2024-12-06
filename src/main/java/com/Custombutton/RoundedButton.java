/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Custombutton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;

/**
 *
 * @author loena
 */
public class RoundedButton extends JButton {

    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);  // To prevent the default button fill
        setFocusPainted(false);       // To prevent the focus paint on click
        setBorderPainted(false);      // To remove the border
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        // Set the rendering hints for anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create a rounded rectangle shape
        Shape shape = new RoundRectangle2D.Float(0, 0, width, height, 20, 20);

        // Set the clip to the rounded rectangle shape
        g2d.clip(shape);

        // Fill the rounded rectangle with a solid color
        g2d.setColor(new Color(255, 255, 255));
        g2d.fill(shape);

        // Call the parent paintComponent to draw the button's text
        super.paintComponent(g);
    }
}
