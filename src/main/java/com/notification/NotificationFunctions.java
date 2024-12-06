/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notification;

import com.DatabaseConnector.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author loena
 */
public class NotificationFunctions {
    public static void addNotification(String description) {
    String query = "INSERT INTO Notifications (description) VALUES (?)";
    try (Connection conn = DatabaseConnector.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setString(1, description);
        pstmt.executeUpdate();
        System.out.println("Notification added: " + description);
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error occurred while adding the notification.");
    }
}

}
