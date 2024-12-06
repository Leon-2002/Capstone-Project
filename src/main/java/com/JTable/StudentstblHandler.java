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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author loena
 */
public class StudentstblHandler {

    public static void updateStudents(int id, String lrn, String lastname, String firstname, String email, String gradeLevel, String section, String status) {
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement("UPDATE StudentsTbl SET lrn = ?, lastname = ?, firstname = ?, email = ?, grade_level = ?, section = ?, status = ? WHERE ID = ?")) {

            pstmt.setString(1, lrn);
            pstmt.setString(2, lastname);
            pstmt.setString(3, firstname);
            pstmt.setString(4, email);
            pstmt.setString(5, gradeLevel);
            pstmt.setString(6, section);
            pstmt.setString(7, status);
            pstmt.setInt(8, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "StudentsTbl updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "No changes made to the StudentsTbl.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while updating table.");
        }
    }

    public static void showStudentsTable(JTable studentsTable) {
        String query = "SELECT ID, lastname, firstname, lrn, email, grade_level, section, status FROM StudentsTbl";

        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {

            // Ensure the JTable's model is of the correct type
            DefaultTableModel tblModel = (DefaultTableModel) studentsTable.getModel();

            // Clear existing rows in the table model
            tblModel.setRowCount(0);

            // Iterate over the result set and add rows to the table model
            while (rs.next()) {
                int id = rs.getInt("ID");
                String lastname = rs.getString("lastname");
                String firstname = rs.getString("firstname");
                String lrn = rs.getString("lrn");
                String email = rs.getString("email");
                String gradeLevel = rs.getString("grade_level");
                String section = rs.getString("section");
                String status = rs.getString("status");

                // Create an array of data to add to the table
                Object[] tbData = {id, lastname, firstname, lrn, email, gradeLevel, section, status, ""};

                // Add the data to the table model
                tblModel.addRow(tbData);
            }

            // Print the number of rows fetched for debugging
            System.out.println(" studentsTbl Rows fetched: " + tblModel.getRowCount());

        } catch (SQLException e) {
            // Log or display error message
            e.printStackTrace();
            System.out.println("Error occurred while fetching data from StudentsTbl.");
        }
    }

    public static void deleteStudent(int id) {
        String query = "DELETE FROM StudentsTbl WHERE ID = ?";

        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            System.out.println("Student with ID " + id + " deleted successfully.");

        } catch (SQLException e) {
            // Log or display error message
            e.printStackTrace();
            System.out.println("Error occurred while deleting student with ID " + id);
        }
    }

    public static void searchStudentsTable(JTable studentsTable, JTextField searchtf) {
        String searchQuery = searchtf.getText().toLowerCase(); // Convert search query to lowercase

        // SQL query to fetch students data based on search input
        String query = "SELECT ID, lrn, lastname, firstname, email, grade_level, section, status FROM StudentsTbl WHERE LOWER(lrn) LIKE ? OR LOWER(lastname) LIKE ? OR LOWER(firstname) LIKE ? OR LOWER(email) LIKE ? OR LOWER(grade_level) LIKE ? OR LOWER(section) LIKE ? OR LOWER(status) LIKE ?";

        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, "%" + searchQuery + "%");
            pstmt.setString(2, "%" + searchQuery + "%");
            pstmt.setString(3, "%" + searchQuery + "%");
            pstmt.setString(4, "%" + searchQuery + "%");
            pstmt.setString(5, "%" + searchQuery + "%");
            pstmt.setString(6, "%" + searchQuery + "%");
            pstmt.setString(7, "%" + searchQuery + "%");

            try (ResultSet rs = pstmt.executeQuery()) {

                DefaultTableModel tblModel = (DefaultTableModel) studentsTable.getModel();

                // Clear existing rows in the table model
                tblModel.setRowCount(0);

                // Iterate over the result set and add rows to the table model
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String lrn = rs.getString("lrn");
                    String lastname = rs.getString("lastname");
                    String firstname = rs.getString("firstname");
                    String email = rs.getString("email");
                    String gradeLevel = rs.getString("grade_level");
                    String section = rs.getString("section");
                    String status = rs.getString("status");

                    // Create an array of data to add to the table
                    Object[] tbData = {id, lrn, lastname, firstname, email, gradeLevel, section, status};

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
}
