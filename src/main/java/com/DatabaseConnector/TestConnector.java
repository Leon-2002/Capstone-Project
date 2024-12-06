/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DatabaseConnector;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author loena
 */
public class TestConnector {
    
    
    
    
    public static void main(String[]args){
        
    }
            
//    public static void reservation(){
//       // Insert new user
//            try (Connection conn = DatabaseConnector.getConnection()) {
//                String insertQuery = "INSERT INTO users (username,password, email) VALUES (?,?,?)";
//                PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
//                insertStmt.setString(1, "LOen");
//                insertStmt.setString(2, "Acuna");
//                insertStmt.setString(3, "loenacuna@gmail.com");
//                int executeUpdate = insertStmt.executeUpdate();
//            
//    } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("reservation Failed");
//    }
//    }

    public static void insertStudent() {    

        String insertQuery = "INSERT INTO StudentsTbl (lastname, firstname, email, grade_level, section, status, student_password, qr_code) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

            // Set parameters for the prepared statement
            pstmt.setString(1, "Doe");
            pstmt.setString(2, "John");
            pstmt.setString(3, "john.doe@example.com");
            pstmt.setString(4, "10");
            pstmt.setString(5, "A");
            pstmt.setString(6, "Active");
            pstmt.setString(7, "password1");
            pstmt.setString(8, "qweeqwewwqeweew");

            // Execute the insert operation
            pstmt.executeUpdate();
            System.out.println("Student data inserted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Student data Failed!");
        }
    
    }
}
    



    

