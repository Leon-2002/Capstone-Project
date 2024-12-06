/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.CustomCards;

import com.DataHandler.DAO;
import javax.swing.ImageIcon;

/**
 *
 * @author loena
 */
public class CardForm extends javax.swing.JPanel {

    public CardForm() {
        initComponents();
        setOpaque(false);
//        int attendance = DAO.getTotalNumberOfEquipments();
//        String attendanceStr = Integer.toString(attendance);
//        
//        int inventory = DAO.getTotalNumberOfEquipments();
//        String inventoryStr = Integer.toString(inventory);
//        
//        int borrowing = DAO.getTotalNumberOfEquipments();
//        String borrowingStr = Integer.toString(borrowing);
int totalStudents = DAO.getTotalNumberOfStudents();     // Total number of students
int totalEquipments = DAO.getTotalNumberOfEquipments(); // Total number of equipments
int totalBorrowings = DAO.getTotalNumberOfBorrowings(); // Total number of borrowings

// Convert integers to strings for displaying on the cards
String attendanceStr = Integer.toString(totalStudents);
String inventoryStr = Integer.toString(totalEquipments);
String borrowingStr = Integer.toString(totalBorrowings);
        
        
        

        cards1.setData(new Model_Cards(new ImageIcon(getClass().getResource("/images/attendance.png")), "Attendance", attendanceStr, "Total Attendance"));
        cards2.setData(new Model_Cards(new ImageIcon(getClass().getResource("/images/inventory.png")), "Inventory", inventoryStr, "Total Inventory"));
        cards3.setData(new Model_Cards(new ImageIcon(getClass().getResource("/images/borrowing.png")), "Borrowing", borrowingStr, "Total Borrowing"));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cards1 = new com.CustomCards.Cards();
        cards2 = new com.CustomCards.Cards();
        cards3 = new com.CustomCards.Cards();

        setLayout(new java.awt.GridLayout(1, 0, 15, 0));

        cards1.setColor1(new java.awt.Color(255, 0, 255));
        cards1.setColor2(new java.awt.Color(255, 51, 255));
        cards1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cards1MouseClicked(evt);
            }
        });
        add(cards1);

        cards2.setColor1(new java.awt.Color(0, 153, 153));
        cards2.setColor2(new java.awt.Color(0, 204, 204));
        add(cards2);

        cards3.setColor1(new java.awt.Color(0, 153, 51));
        cards3.setColor2(new java.awt.Color(51, 255, 51));
        add(cards3);
    }// </editor-fold>//GEN-END:initComponents

    private void cards1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cards1MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cards1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.CustomCards.Cards cards1;
    private com.CustomCards.Cards cards2;
    private com.CustomCards.Cards cards3;
    // End of variables declaration//GEN-END:variables
}
