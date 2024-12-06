package com.JTable;

import com.DatabaseConnector.DatabaseConnector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;

public class AttendancetblHandler {

    /**
     * Populates the given JTable with attendance data from the database.
     *
     * @param attendanceTable the JTable to be populated
     */
    public static void AttendanceShowTable(JTable attendanceTable) {
        // SQL query to fetch attendance data
        String query = "SELECT lastname, firstname, attendance_date, attendance_time FROM attendancetbl";

        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {

            // Ensure the JTable's model is of the correct type
            DefaultTableModel tblModel = (DefaultTableModel) attendanceTable.getModel();

            // Clear existing rows in the table model
            tblModel.setRowCount(0);

            // Iterate over the result set and add rows to the table model
            while (rs.next()) {
                String lastname = rs.getString("lastname");
                String firstname = rs.getString("firstname");
                Date attendanceDate = rs.getDate("attendance_date");
                Time attendanceTime = rs.getTime("attendance_time");

                // Format the Date and Time to strings
                String formattedDate = (attendanceDate != null) ? attendanceDate.toString() : "";
                String formattedTime = (attendanceTime != null) ? attendanceTime.toString() : "";

                // Create an array of data to add to the table
                Object[] tbData = {lastname, firstname, formattedDate, formattedTime};

                // Add the data to the table model
                tblModel.addRow(tbData);
            }

            // Print the number of rows fetched for debugging
            System.out.println("AttendancETBL Rows fetched: " + tblModel.getRowCount());

        } catch (SQLException e) {
            // Print the stack trace for any SQL exceptions
            e.printStackTrace();
        }
    }

public static void updateTableWithDate(JTable attendanceTable, java.util.Date selectedDate) {
    // SQL query to fetch attendance data for the selected date
    String query = "SELECT lastname, firstname, attendance_date, attendance_time FROM attendancetbl WHERE attendance_date = ?";

    // Convert java.util.Date to java.sql.Date
    java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());

    try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {

        pstmt.setDate(1, sqlDate);

        try (ResultSet rs = pstmt.executeQuery()) {

            DefaultTableModel tblModel = (DefaultTableModel) attendanceTable.getModel();

            // Clear existing rows in the table model
            tblModel.setRowCount(0);

            // Iterate over the result set and add rows to the table model
            while (rs.next()) {
                String lastname = rs.getString("lastname");
                String firstname = rs.getString("firstname");
                Date attendanceDate = rs.getDate("attendance_date");
                Time attendanceTime = rs.getTime("attendance_time");

                // Format the Date and Time to strings
                String formattedDate = (attendanceDate != null) ? attendanceDate.toString() : "";
                String formattedTime = (attendanceTime != null) ? attendanceTime.toString() : "";

                // Create an array of data to add to the table
                String[] tbData = {lastname, firstname, formattedDate, formattedTime};

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

public static void searchUpdateTable(JTable attendanceTable,JTextField searchtf) {
        String searchQuery = searchtf.getText();

        // SQL query to fetch attendance data based on search input
        String query = "SELECT lastname, firstname, attendance_date, attendance_time FROM attendancetbl WHERE lastname LIKE ? OR firstname LIKE ?";

        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, "%" + searchQuery + "%");
            pstmt.setString(2, "%" + searchQuery + "%");

            try (ResultSet rs = pstmt.executeQuery()) {

                DefaultTableModel tblModel = (DefaultTableModel) attendanceTable.getModel();

                // Clear existing rows in the table model
                tblModel.setRowCount(0);

                // Iterate over the result set and add rows to the table model
                while (rs.next()) {
                    String lastname = rs.getString("lastname");
                    String firstname = rs.getString("firstname");
                    Date attendanceDate = rs.getDate("attendance_date");
                    Time attendanceTime = rs.getTime("attendance_time");

                    // Format the Date and Time to strings
                    String formattedDate = (attendanceDate != null) ? attendanceDate.toString() : "";
                    String formattedTime = (attendanceTime != null) ? attendanceTime.toString() : "";

                    // Create an array of data to add to the table
                    String[] tbData = {lastname, firstname, formattedDate, formattedTime};

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
