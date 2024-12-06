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
public class InventoryDAO {

    public static String getEquipmentNameById(int id) {
        String equipmentName = null;
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement("SELECT equipment_name FROM InventoryTable WHERE id = ?")) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    equipmentName = rs.getString("equipment_name");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while retrieving data.");
        }
        return equipmentName;
    }

    public static int getQuantityById(int id) {
        int quantity = 0;
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement("SELECT quantity FROM InventoryTable WHERE id = ?")) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while retrieving data.");
        }
        return quantity;
    }

    public static String getCategoryById(int id) {
        String category = null;
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement("SELECT category FROM InventoryTable WHERE id = ?")) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    category = rs.getString("category");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while retrieving data.");
        }
        return category;
    }

    public static int getEquipmentAvailableQuantityById(int id) {
        int availableQuantity = 0;
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement("SELECT available_quantity FROM InventoryTable WHERE id = ?")) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    availableQuantity = rs.getInt("available_quantity");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while retrieving data.");
        }
        return availableQuantity;
    }
}
