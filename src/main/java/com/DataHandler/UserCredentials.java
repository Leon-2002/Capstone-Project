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

/**
 *
 * @author loena
 */
public class UserCredentials {
     private static String id;
    private static String role;
    public static void main(String []args){
//        UserCredentials userCredentials = new UserCredentials();
//        userCredentials.GetCredentials();
           System.out.println( UserCredentials.getID());
    }

    public static String getID() {
        return id;
    }
     public static String getRole() {
        return role;
    }
     
     
    public static String Getid( String email){
    
//     String username = user1;
     try (Connection conn = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM studentstbl WHERE email = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
             pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            

           if (rs.next()) {
                // User exists with the provided username and password
                UserCredentials.getID();
                 String id = rs.getString("id");  
                 
                 return id;
            }
           return "NO user";
        } catch (SQLException e) {
            e.printStackTrace();
           return "NO user";
        }
        // No user found with the provided username and password
        

}
    
    public static String GetId( String lrn){
    
//     String username = user1;
     try (Connection conn = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM studentstbl WHERE lrn = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
             pstmt.setString(1, lrn);
            ResultSet rs = pstmt.executeQuery();
            

           if (rs.next()) {
                // User exists with the provided username and password
                UserCredentials.getID();
                 String id1 = rs.getString("lrn");  
                 
                 return id1;
            }
           return "NO user";
        } catch (SQLException e) {
            e.printStackTrace();
           return "NO user";
        }
        // No user found with the provided username and password
        

}
 
    public static String GetEmail( String lrn){
    
//     String username = user1;
     try (Connection conn = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM studentstbl WHERE lrn = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
             pstmt.setString(1, lrn);
            ResultSet rs = pstmt.executeQuery();
            

           if (rs.next()) {
                // User exists with the provided username and password
                UserCredentials.getID();
                 String email = rs.getString("email");  
                 
                 return email;
            }
           return "NO user";
        } catch (SQLException e) {
            e.printStackTrace();
           return "NO user";
        }
        // No user found with the provided username and password
        

}    
    
   public static String getEmailAdmin(String firstname, String lastname) {
    String query = "SELECT email FROM admintbl WHERE firstname = ? AND lastname = ?";

    try (Connection conn = DatabaseConnector.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {

        pstmt.setString(1, firstname);
        pstmt.setString(2, lastname);

        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                // User exists with the provided firstname and lastname
                return rs.getString("email");
            } else {
                // No user found with the provided firstname and lastname
                return "NO user";
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return "Error occurred";
    }
}

}
