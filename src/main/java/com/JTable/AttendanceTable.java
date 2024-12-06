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
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author loena
 */
public class AttendanceTable {
    
     public static void AttendanceShowTable(javax.swing.JTable Attendancetable1) {
        String query = "SELECT * FROM attendancetbl where attendance_date = CURRENT_DATE";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            
            DefaultTableModel tblModel = (DefaultTableModel) Attendancetable1.getModel();
            
            while (rs.next()) {
               
                String name = rs.getString("firstname");
                String surname = rs.getString("last rname");
//                int time = (int) rs.getLong("Attendance_time");
                long attendanceTimeMillis = rs.getLong("Attendance_time");
                
                // Convert long to LocalDateTime
                LocalDateTime attendanceTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(attendanceTimeMillis), ZoneId.systemDefault());
                
                // Format the LocalDateTime to a string
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedTime = attendanceTime.format(formatter);
                
                // String array to store data into JTable
                String tbData[] = {name, surname, formattedTime};
                
                // Add String array data into table
                tblModel.addRow(tbData);
                    
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


