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
public class StudentsDAO {

    public static String getLrnById(int id) {
        String lrn = null;
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement("SELECT lrn FROM StudentsTbl WHERE ID = ?")) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    lrn = rs.getString("lrn");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while retrieving data.");
        }
        return lrn;
    }

    public static String getLastnameById(int id) {
        String lastname = null;
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement("SELECT lastname FROM StudentsTbl WHERE ID = ?")) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    lastname = rs.getString("lastname");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while retrieving data.");
        }
        return lastname;
    }

    public static String getFirstnameById(int id) {
        String firstname = null;
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement("SELECT firstname FROM StudentsTbl WHERE ID = ?")) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    firstname = rs.getString("firstname");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while retrieving data.");
        }
        return firstname;
    }

    public static String getEmailById(int id) {
        String email = null;
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement("SELECT email FROM StudentsTbl WHERE ID = ?")) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    email = rs.getString("email");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while retrieving data.");
        }
        return email;
    }

    public static String getGradeLevelById(int id) {
        String gradeLevel = null;
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement("SELECT grade_level FROM StudentsTbl WHERE ID = ?")) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    gradeLevel = rs.getString("grade_level");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while retrieving data.");
        }
        return gradeLevel;
    }

    public static String getSectionById(int id) {
        String section = null;
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement("SELECT section FROM StudentsTbl WHERE ID = ?")) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    section = rs.getString("section");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while retrieving data.");
        }
        return section;
    }

    public static String getStatusById(int id) {
        String status = null;
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement("SELECT status FROM StudentsTbl WHERE ID = ?")) {

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

    public static String getQrCodeById(int id) {
        String qrCode = null;
        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement("SELECT qr_code FROM StudentsTbl WHERE ID = ?")) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    qrCode = rs.getString("qr_code");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while retrieving data.");
        }
        return qrCode;
    }
}
