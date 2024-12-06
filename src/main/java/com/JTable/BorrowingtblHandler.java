/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.JTable;

import com.DatabaseConnector.DatabaseConnector;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author loena
 */
public class BorrowingtblHandler {

    public static void populateBorrowingTable(JTable jTable) {
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        model.setRowCount(0); // Clear the table

        String query = "SELECT bt.id, st.lastname, st.firstname, it.equipment_name , bt. quantity_borrowed, bt.borrow_date, bt.return_date, bt.status "
                + "FROM BorrowingTable bt "
                + "JOIN StudentsTbl st ON bt.student_id = st.id "
                + "JOIN InventoryTable it ON bt.equipment_id = it.id ORDER BY bt.status ASC, st.lastname ASC";

        try (Connection conn = DatabaseConnector.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Object[] row = new Object[8];
                row[0] = rs.getInt("id");
                row[1] = rs.getString("lastname") + " " + rs.getString("firstname"); 
                row[2] = rs.getString("equipment_name");
                row[3] =  rs.getString("quantity_borrowed");   
                row[4] = rs.getTimestamp("borrow_date");
                row[5] = rs.getTimestamp("return_date");
                row[6] = rs.getString("status");
                row[7] = "Action"; // This will be replaced with a button later

                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while populating borrowing table.");
        }
          TableColumn idColumn = jTable.getColumnModel().getColumn(0);
          TableColumn quantitycolumn = jTable.getColumnModel().getColumn(3);

            System.out.println("Borrowingtbl Rows fetched: " + model.getRowCount());
  // Set the preferred width of the "ID" column
  idColumn.setPreferredWidth(10);
  quantitycolumn.setPreferredWidth(30);
    }
    
    public static void updateBorrowingTable(int id, String status) {
    try (Connection conn = DatabaseConnector.getConnection(); 
         PreparedStatement pstmt = conn.prepareStatement("UPDATE borrowingtable SET status = ?, return_date = CURRENT_DATE WHERE id = ?")) {

        pstmt.setString(1, status);
        pstmt.setInt(2, id);

        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "borrowingtable updated successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "No changes made to the borrowingtable.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error occurred while updating table.");
    }
}
    
    
public static void updateBorrowingTableNULL(int id, String status, int lost_quantity) {
    try (Connection conn = DatabaseConnector.getConnection(); 
         PreparedStatement pstmt = conn.prepareStatement("UPDATE borrowingtable SET status = ?, lost_quantity = ? WHERE id = ?")) {

        pstmt.setString(1, status);
         pstmt.setInt(2, lost_quantity);
        pstmt.setInt(3, id);

        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "borrowingtable updated successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "No changes made to the borrowingtable.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error occurred while updating table.");
    }
}
}
