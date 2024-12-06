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
public class borrowingDAO {
    public static String getStatusById(int id) {
    String status = null;
    try (Connection conn = DatabaseConnector.getConnection(); 
         PreparedStatement pstmt = conn.prepareStatement("SELECT status FROM borrowingtable WHERE id = ?")) {

        pstmt.setInt(1, id);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                status = rs.getString("status");
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error occurred while retrieving data.");
    }
    return status;
}
}
