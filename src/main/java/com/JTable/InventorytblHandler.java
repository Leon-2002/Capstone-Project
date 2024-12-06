package com.JTable;

import com.DataHandler.UserCredentials;
import com.DataHandler.UserHandler;
import com.DatabaseConnector.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author loena
 */
public class InventorytblHandler {

    public static void searchInventoryTable(JTable inventoryTable, JTextField searchtf) {
        String searchQuery = searchtf.getText().toLowerCase(); // Convert search query to lowercase

        // SQL query to fetch inventory data based on search input
        String query = "SELECT * FROM InventoryTable WHERE LOWER(equipment_name) LIKE ? OR LOWER(category) LIKE ?";

        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, "%" + searchQuery + "%");
            pstmt.setString(2, "%" + searchQuery + "%");

            try (ResultSet rs = pstmt.executeQuery()) {

                DefaultTableModel tblModel = (DefaultTableModel) inventoryTable.getModel();

                // Clear existing rows in the table model
                tblModel.setRowCount(0);

                // Iterate over the result set and add rows to the table model
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String equipmentName = rs.getString("equipment_name");
                    String category = rs.getString("category");
                    int quantity = rs.getInt("quantity");
                    int availableQuantity = rs.getInt("available_quantity");

                    // Create an array of data to add to the table
                    Object[] tbData = {id, equipmentName, category, quantity, availableQuantity, ""}; // Add an empty string for the Action column

                    // Add the data to the table model
                    tblModel.addRow(tbData);
                }

                // Print the number of rows fetched for debugging
                System.out.println("Rows fetched: " + tblModel.getRowCount());

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  public static void InventoryShowTable(JTable inventoryTable) {
    // SQL query to fetch inventory data sorted by equipment_name and then by category
    String query = "SELECT id, equipment_name, category, quantity, available_quantity FROM InventoryTable ORDER BY equipment_name ASC, category ASC";

    try (Connection conn = DatabaseConnector.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {

        // Ensure the JTable's model is of the correct type
        DefaultTableModel tblModel = (DefaultTableModel) inventoryTable.getModel();

        // Clear existing rows in the table model
        tblModel.setRowCount(0);
        
        // Iterate over the result set and add rows to the table model
        while (rs.next()) {
            int id = rs.getInt("id");
            String equipmentName = rs.getString("equipment_name");
            String category = rs.getString("category");
            int quantity = rs.getInt("quantity");
            int availableQuantity = rs.getInt("available_quantity");

            // Create an array of data to add to the table
            Object[] tbData = {id, equipmentName, category, quantity, availableQuantity, ""}; // Add an empty string for the Action column

            // Add the data to the table model
            tblModel.addRow(tbData);
        }

        // Print the number of rows fetched for debugging
        System.out.println("InventoryTbl Rows fetched: " + tblModel.getRowCount());
         conn.close();
         pstmt.close();
         rs.close();

    } catch (SQLException e) {
        // Print the stack trace for any SQL exceptions
        e.printStackTrace();
    }
}

    public static void updateInventoryTable(int id, String equipmentName, int quantity, int availableQuantity, String category) {
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement("UPDATE InventoryTable SET equipment_name = ?, quantity = ?, available_quantity = ?, category = ? WHERE id = ?")) {

            pstmt.setString(1, equipmentName);
            pstmt.setInt(2, quantity);
            pstmt.setInt(3, availableQuantity);
            pstmt.setString(4, category);
            pstmt.setInt(5, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "InventoryTable updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "No changes made to the InventoryTable.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while updating table.");
        }
    }

    public static void updateInventoryTable(int id, String equipmentName, int quantity, String category) {
    try (Connection conn = DatabaseConnector.getConnection(); 
         PreparedStatement pstmt = conn.prepareStatement("SELECT available_quantity, quantity FROM InventoryTable WHERE id = ?")) {

        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            int oldAvailableQuantity = rs.getInt("available_quantity");
            int oldQuantity = rs.getInt("quantity");
            int borrowedQuantity = oldQuantity - oldAvailableQuantity;

            // Calculate the new available quantity based on the new quantity and borrowed quantity
            int newAvailableQuantity = quantity - borrowedQuantity;

            // If the new available quantity is greater than the new quantity, set it to the new quantity
            if (newAvailableQuantity > quantity) {
                newAvailableQuantity = quantity;
            }

            // Ensure available quantity is not negative
            newAvailableQuantity = Math.max(newAvailableQuantity, 0);

            try (PreparedStatement updateStmt = conn.prepareStatement("UPDATE InventoryTable SET equipment_name = ?, quantity = ?, available_quantity = ?, category = ? WHERE id = ?")) {
                updateStmt.setString(1, equipmentName);
                updateStmt.setInt(2, quantity);
                updateStmt.setInt(3, newAvailableQuantity);
                updateStmt.setString(4, category);
                updateStmt.setInt(5, id);

                int rowsAffected = updateStmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "InventoryTable updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "No changes made to the InventoryTable.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No record found with the given ID.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error occurred while updating table.");
    }
}

    public static void deleteInventoryRow(int id) {
        // SQL query to delete the row
        String query = "DELETE FROM InventoryTable WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Set the id parameter
            pstmt.setInt(1, id);

            // Execute the delete query
            int rowsAffected = pstmt.executeUpdate();

            // Print the number of rows affected for debugging
            System.out.println("Rows deleted: " + rowsAffected);

        } catch (SQLException e) {
            // Print the stack trace for any SQL exceptions
            e.printStackTrace();
        }
    }

   public static void addInventory(String equipmentName, int quantity, String category) {
    try (Connection conn = DatabaseConnector.getConnection(); 
         PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM InventoryTable WHERE equipment_name = ?")) {
        
        pstmt.setString(1, equipmentName);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            // equipment name already exists in the table
            JOptionPane.showMessageDialog(null, "Equipment '" + equipmentName + "' is already in the table.");
        } else {
            // add new equipment to the table
            try (PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO InventoryTable (equipment_name, quantity, available_quantity, category) VALUES (?, ?, ?, ?)")) {
                pstmt2.setString(1, equipmentName);
                pstmt2.setInt(2, quantity);
                pstmt2.setInt(3, quantity);
                pstmt2.setString(4, category);
                
                pstmt2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Inventory added successfully!");
            }
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error occurred while adding inventory.");
    }
}
   
 
   
public static final String QR_CODE_PATH = "C:\\Users\\loena\\OneDrive\\Documents\\NetBeansProjects\\capstoneproject\\src\\main\\java\\QrEquipment\\";

   
 public static void generateQRCode(String equipmentName, String email) {
    try (Connection conn = DatabaseConnector.getConnection(); 
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT id FROM inventorytable WHERE equipment_name = '" + equipmentName + "'")) {

        if (rs.next()) {
            String equipmentId = rs.getString("id");
            System.out.println("Equipment ID: " + equipmentId);

            // Generate QR code with the equipment ID
            String qrCodeData =  equipmentId;
            int width = 200;
            int height = 200;
            String filePath = QR_CODE_PATH + equipmentName + ".png";

            Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new EnumMap<>(EncodeHintType.class);
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

            BitMatrix matrix = null;
            try {
                matrix = new MultiFormatWriter().encode(qrCodeData, BarcodeFormat.QR_CODE, width, height, hintMap);
            } catch (WriterException ex) {
                Logger.getLogger(InventorytblHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

            File file = new File(filePath);
            try {
                MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf('.') + 1), file);
            } catch (IOException ex) {
                Logger.getLogger(InventorytblHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("QR code generated successfully and saved to " + filePath);

            // Insert the file path to the qr_path column
            
           
            stmt.executeUpdate("UPDATE inventorytable SET qr_path = '" + filePath + "' WHERE id = '" + qrCodeData + "'");
             
            System.out.println("QR code path updated successfully in the database.");
            UserHandler.sendEquipmentWithAttachment(email, filePath, equipmentName);
        } else {
            System.out.println("No equipment found with the specified name.");
        }

    } catch (SQLException e) {
        System.err.println("Error fetching equipment ID: " + e.getMessage());
    }
}

 public static boolean isEquipmentNameExists(String equipmentname) {
    String query = "SELECT COUNT(*) FROM inventorytable WHERE equipment_name = ?";
    try (Connection conn = DatabaseConnector.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        
        pstmt.setString(1, equipmentname);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0; // Returns true if count > 0
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error checking equipment name: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
    return false;
}

   
}

