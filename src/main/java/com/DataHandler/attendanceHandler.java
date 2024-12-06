/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DataHandler;

import com.DatabaseConnector.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author loena
 */
public class attendanceHandler {
    public static void insertAttendanceRecord(int studentId) {
    try (Connection conn = DatabaseConnector.getConnection();
         Statement stmt = conn.createStatement()) {
            
        
         // Check if an attendance record for the given student ID already exists for the current date
            String query = "SELECT * FROM attendancetbl WHERE student_id = " + studentId + " AND attendance_date = CURRENT_DATE";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Attendance record already exists for today!");
                return;
            }
            
        // Retrieve lastname and firstname from StudentsTbl
        String query1= "SELECT lastname, firstname FROM studentstbl WHERE id = " + studentId;
        ResultSet rs1 = stmt.executeQuery(query1);
        String lastname = "";
        String firstname = "";
        if (rs1.next()) {
            lastname = rs1.getString("lastname");
            firstname = rs1.getString("firstname");
        }

        // Insert data into AttendanceTbl
        String insertQuery = "INSERT INTO attendancetbl (student_id, lastname, firstname, attendance_date, attendance_time) "
                + "VALUES (" + studentId + ", '" + lastname + "', '" + firstname + "', CURRENT_DATE, CURRENT_TIME)";
        stmt.executeUpdate(insertQuery);
        JOptionPane.showMessageDialog(null, "Attendance record inserted successfully!");
        
        

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Attendance Failed!");
    }
}
    
    public static int getStudentIdByLRN(String lrn) {
    try (Connection conn = DatabaseConnector.getConnection();
         PreparedStatement pstmt = conn.prepareStatement("SELECT id FROM studentstbl WHERE lrn = ?")) {

        // Set the LRN for the prepared statement
        pstmt.setString(1, lrn);

        // Execute the prepared statement
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        } else {
            System.out.println("No student found with LRN: " + lrn);
            return -1; // Return -1 if no student is found
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error occurred while retrieving student ID.");
        return -1; // Return -1 if an error occurs
    }
}
}
