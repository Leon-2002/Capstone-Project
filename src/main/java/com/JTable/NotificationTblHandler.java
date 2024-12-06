/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.JTable;

import com.DatabaseConnector.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author loena
 */
public class NotificationTblHandler {
    
    public static void fetchNotifications(JTable notificationTable) {
    // Query to fetch message and order by date (latest first)
    String query = "SELECT description FROM notifications ORDER BY timestamp DESC";
    
    // DefaultTableModel for managing table data
    DefaultTableModel model = (DefaultTableModel) notificationTable.getModel();
    
    // Clear any existing rows in the table
    model.setRowCount(0);

    // Establish the database connection and fetch data
    try (Connection conn = DatabaseConnector.getConnection(); 
         PreparedStatement pstmt = conn.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {
        
        // Check if the table model has at least one column
        if (model.getColumnCount() == 0) {
            // Initialize the table with the required columns (in this case, one column for the message)
            model.addColumn("description");
        }
        
        // Iterate through the result set and add data to the table
        while (rs.next()) {
            String message = rs.getString("description");
            model.addRow(new Object[]{message});  // Add the row to the table model
        }

        // Optionally, print the row count for verification
        System.out.println(" Notification Rows fetched: " + model.getRowCount());
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error fetching notifications: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

}
