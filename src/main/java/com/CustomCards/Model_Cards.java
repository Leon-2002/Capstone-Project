/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CustomCards;

import javax.swing.Icon;

/**
 *
 * @author loena
 */
public class Model_Cards {
        
    private Icon icon;
    private String title;
    private String values;
    private String description;
    
    public Model_Cards() {
        
    }
    
    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Model_Cards(Icon icon, String title, String values, String description) {
        this.icon = icon;
        this.title = title;
        this.values = values;
        this.description = description;
    }
}
