/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DataHandler;

import com.DataHandler.InventoryHandler;
import com.DatabaseConnector.DatabaseConnector;
import com.notification.NetworkUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author loena
 */
public class InventoryHandler {
    
    
    
public static void AddInventory(String equipment_name, int quantity, String category) {
    try (Connection conn = DatabaseConnector.getConnection()) {
        // Check if the equipment already exists in the database
        String checkQuery = "SELECT COUNT(*) FROM inventorytable WHERE equipment_name = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
        checkStmt.setString(1, equipment_name);
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next() && rs.getInt(1) > 0) {
            // Equipment already exists
            System.out.println("Equipment already exists: " + equipment_name);
            JOptionPane.showMessageDialog(null, "Equipment already exists in the inventory.");
        } 
        if (!NetworkUtil.isInternetAvailable1()) {
       JOptionPane.showMessageDialog(null, "No internet connection. Please connect to the internet to register.");
        return;
            }
        else {
            // Equipment does not exist, proceed with the insertion
            String insertQuery = "INSERT INTO inventorytable (equipment_name, quantity, category) VALUES (?,?,?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setString(1, equipment_name);
            insertStmt.setInt(2, quantity);
            insertStmt.setString(3, category);

            insertStmt.executeUpdate();
            System.out.println("Equipment added successfully: " + equipment_name);
             JOptionPane.showMessageDialog(null, "Equipment added successfully: " + equipment_name);
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Inventory addition failed");
    }
}
     
     public static void UpdateInventory(int id,String equipment_name, int quantity, String category){
       // update inventory to database
          try (Connection conn = DatabaseConnector.getConnection()) {
            String updateQuery = "UPDATE inventorytable SET equipment_name=?, quantity=?, category=? WHERE id = ?"; 
             PreparedStatement insertStmt = conn.prepareStatement(updateQuery);
                insertStmt.setString(1, equipment_name);
                 insertStmt.setInt(2, quantity);
                insertStmt.setString(3, category);
                insertStmt.setInt(4, id);
                insertStmt.executeUpdate();
             
            
    } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("update Failed");
    }
            
    }
     
   
}

