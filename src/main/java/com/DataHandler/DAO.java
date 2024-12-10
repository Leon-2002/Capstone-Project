/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DataHandler;

import com.DatabaseConnector.DatabaseConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author loena
 */
public class DAO {

    public static int getTotalNumberOfStudents() {
        int totalStudents = 0;
        String query = "SELECT COUNT(*) FROM attendancetbl";

        try (Connection conn = DatabaseConnector.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                totalStudents = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while fetching the total number of students.");
        }

        return totalStudents;
    }

    public static int getTotalNumberOfEquipments() {
        int totalEquipments = 0;
        String query = "SELECT COUNT(*) FROM inventorytable";

        try (Connection conn = DatabaseConnector.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                totalEquipments = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while fetching the total number of equipment items.");
        }

        return totalEquipments;
    }

   public static int getTotalNumberOfBorrowings() {
    int totalBorrowings = 0;
    String query = "SELECT * FROM borrowingtable WHERE status = 'Borrowed';";

    try (Connection conn = DatabaseConnector.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

        while (rs.next()) {
            totalBorrowings++; // Increment the count for each row found
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error occurred while fetching the total number of borrowings.");
    }

    return totalBorrowings;
}

}
