/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author loena
 */
public class TableCreator {

    public static void main(String[] args) {

        //tables creator
//        createUpdateAvailableQuantityTrigger();
        
//        createUpdateAvailableQuantityTrigger();
//            createUpdateAvailableQuantityTrigger();
                InventoryTable();
                BorrowingTable();

        }
    
    public static void NotificationsTable() {
    try (Connection conn = DatabaseConnector.getConnection(); Statement stmt = conn.createStatement()) {

        // Create the Notifications table
        String createTableSQL = "CREATE TABLE Notifications ("
                + "ID SERIAL PRIMARY KEY, "
                + "description VARCHAR(255) NOT NULL, "
                + "timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
        stmt.executeUpdate(createTableSQL);
        System.out.println("Notifications table created successfully!");

        // Insert a default notification record
        String insertQuery = "INSERT INTO Notifications (description) "
                + "VALUES ('Default user registration record added')";
        stmt.executeUpdate(insertQuery);
        System.out.println("Default record inserted into Notifications table successfully!");

    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error occurred while creating the table or inserting data.");
    }
}


    public static void StudentsTable() {
        try (Connection conn = DatabaseConnector.getConnection(); Statement stmt = conn.createStatement()) {

            // Create the table
            String createTableSQL = "CREATE TABLE StudentsTbl ("
                    + "ID SERIAL PRIMARY KEY, "
                    + "lrn VARCHAR(50), "
                    + "lastname VARCHAR(50), "
                    + "firstname VARCHAR(50), "
                    + "email VARCHAR(100), "
                    + "grade_level VARCHAR(20), "
                    + "section VARCHAR(20), "
                    + "status VARCHAR(20), "
                    + "qr_code VARCHAR(225))";
            stmt.executeUpdate(createTableSQL);
            System.out.println("StudentsTbl created successfully!");

            // Insert a default record
            String insertQuery = "INSERT INTO StudentsTbl (lrn, lastname, firstname, email, grade_level, section, status, qr_code) "
                    + "VALUES ('123123123','Doe', 'John', 'john.doe@example.com', '10', 'A', 'Active', '1233123123123')";
            stmt.executeUpdate(insertQuery);
            System.out.println("Default record inserted into StudentsTbl successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while creating table or inserting data.");
        }
    }

//   public static void InventoryTable() {
//    try (Connection conn = DatabaseConnector.getConnection();
//         Statement stmt = conn.createStatement()) {
//
//        // Create the table
//        String createTableSQL = "CREATE TABLE InventoryTable ("
//                + "id SERIAL PRIMARY KEY, "
//                + "equipment_name VARCHAR(100), "
//                + "quantity INTEGER, "
//                + "category VARCHAR(50))";
//        stmt.executeUpdate(createTableSQL);
//        System.out.println("InventoryTable created successfully!");
//
//    } catch (SQLException e) {
//        e.printStackTrace();
//        System.out.println("Error occurred while creating table.");
//    }
//   }
    public static void InventoryTable() {
        try (Connection conn = DatabaseConnector.getConnection(); Statement stmt = conn.createStatement()) {

            // Create the table
            String createTableSQL = "CREATE TABLE InventoryTable ("
                    + "id SERIAL PRIMARY KEY, "
                    + "equipment_name VARCHAR(100), "
                    + "quantity INTEGER, "
                    + "available_quantity INTEGER, "
                    + "category VARCHAR(50),"
                    + "qr_path VARCHAR(255))";
            stmt.executeUpdate(createTableSQL);
            System.out.println("InventoryTable created successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while creating table.");
        }
    }

    public static void AttendanceTable() {
        try (Connection conn = DatabaseConnector.getConnection(); Statement stmt = conn.createStatement()) {

            // Create the table
            String createTableSQL = "CREATE TABLE AttendanceTbl ("
                    + "id SERIAL PRIMARY KEY, "
                    + "student_id INTEGER, "
                    + "lastname VARCHAR(50), "
                    + "firstname VARCHAR(50), "
                    + "attendance_date DATE, "
                    + "attendance_time TIME, "
                    + "FOREIGN KEY (student_id) REFERENCES StudentsTbl(id))";
            stmt.executeUpdate(createTableSQL);
            System.out.println("AttendanceTbl created successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while creating table or inserting data.");
        }
    }

    public static void AdminTable() {
        try (Connection conn = DatabaseConnector.getConnection(); Statement stmt = conn.createStatement()) {

            // Create the table
            String createTableSQL = "CREATE TABLE AdminTbl ("
                    + "ID SERIAL PRIMARY KEY, "
                    + "lastname VARCHAR(50), "
                    + "firstname VARCHAR(50), "
                    + "email VARCHAR(100), "
                    + "status VARCHAR(20), "
                    + "student_password VARCHAR(100), "
                    + "qr_code VARCHAR(225))";
            stmt.executeUpdate(createTableSQL);
            System.out.println("AdminTbl created successfully!");

            // Insert a default record
            String insertQuery = "INSERT INTO StudentsTbl ( lastname, firstname, email, status, student_password, qr_code) "
                    + "VALUES ('Doe', 'John', 'john.doe@example.com', 'Active', 'hashed_password_123', '1233123123123')";
            stmt.executeUpdate(insertQuery);
            System.out.println("Default record inserted into StudentsTbl successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while creating table or inserting data.");
        }
    }

    public static void BorrowingTable() {
        try (Connection conn = DatabaseConnector.getConnection(); Statement stmt = conn.createStatement()) {

            // Create the table
            String createTableSQL = "CREATE TABLE BorrowingTable ("
                    + "id SERIAL PRIMARY KEY, "
                    + "student_id INTEGER, "
                    + "equipment_id INTEGER, "
                    + "borrow_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
                    + "return_date TIMESTAMP, "
                    + "quantity_borrowed INTEGER, "
                    + "lost_quantity INTEGER, "
                    + "status VARCHAR(20), " // Add a status column with a maximum length of 20 characters
                    + "FOREIGN KEY (student_id) REFERENCES StudentsTbl(id), "
                    + "FOREIGN KEY (equipment_id) REFERENCES InventoryTable(id))";

            stmt.executeUpdate(createTableSQL);
            System.out.println("BorrowingTable created successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while creating BorrowingTable.");
        }
    }

    public static void createUpdateAvailableQuantityTrigger() {
    try (Connection conn = DatabaseConnector.getConnection(); Statement stmt = conn.createStatement()) {

        // Create a function to update available_quantity on borrow
        String createFunctionSQL = 
                "CREATE OR REPLACE FUNCTION update_available_quantity_on_borrow() " +
                "RETURNS TRIGGER AS $$ " +
                "BEGIN " +
                "    IF NEW.status = 'Completed' THEN " +
                "        UPDATE InventoryTable SET available_quantity = available_quantity + NEW.quantity_borrowed " +
                "        WHERE id = NEW.equipment_id; " +
//                "UPDATE BorrowingTable SET lost_quantity = 0 " +
//                "        WHERE id = NEW.id; " +
                "    ELSIF NEW.status = 'Lost' THEN " +
                "        UPDATE InventoryTable SET available_quantity = available_quantity + (NEW.quantity_borrowed - NEW.lost_quantity) " +
                "        WHERE id = NEW.equipment_id; " +
                "        UPDATE BorrowingTable SET quantity_borrowed = NEW.lost_quantity " +
                "        WHERE id = NEW.id; " +
                "    ELSIF NEW.status = 'Borrowed' THEN " +
                "        UPDATE InventoryTable SET available_quantity = available_quantity - NEW.quantity_borrowed " +
                "        WHERE id = NEW.equipment_id; " +
                "    ELSE " +
                "        RAISE NOTICE 'Unknown status %', NEW.status; " +
                "    END IF; " +
                "    RETURN NEW; " +
                "END; " +
                "$$ LANGUAGE plpgsql;";

        System.out.println("Function SQL:\n" + createFunctionSQL); // Print the function SQL

        stmt.executeUpdate(createFunctionSQL);

        // Create triggers as before...

    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error occurred while creating trigger: " + e.getMessage());
    }
}

//   public static void createUpdateAvailableQuantityTrigger() {
//    try (Connection conn = DatabaseConnector.getConnection(); Statement stmt = conn.createStatement()) {
//
//        // Create a function to update available_quantity on borrow
//        String createFunctionSQL = 
//                "CREATE OR REPLACE FUNCTION update_available_quantity_on_borrow() " +
//                "RETURNS TRIGGER AS $$ " +
//                "BEGIN " +
//                "    IF TG_OP = 'UPDATE' AND NEW.status = OLD.status THEN " +
//                "        RETURN NEW; -- No change in status, exit early " +
//                "    END IF; " +
//                "    " +
//                "    IF NEW.status = 'Completed' THEN " +
//                "        UPDATE InventoryTable SET available_quantity = available_quantity + NEW.quantity_borrowed " +
//                "        WHERE id = NEW.equipment_id; " +
//                "        UPDATE BorrowingTable SET lost_quantity = 0 " +
//                "        WHERE id = NEW.id; " +
//                "    ELSIF NEW.status = 'Lost' THEN " +
//                "        UPDATE InventoryTable SET available_quantity = available_quantity + (NEW.quantity_borrowed - NEW.lost_quantity) " +
//                "        WHERE id = NEW.equipment_id; " +
//                "        UPDATE BorrowingTable SET quantity_borrowed = NEW.lost_quantity " +
//                "        WHERE id = NEW.id; " +
//                "    ELSIF NEW.status = 'Borrowed' THEN " +
//                "        UPDATE InventoryTable SET available_quantity = available_quantity - NEW.quantity_borrowed " +
//                "        WHERE id = NEW.equipment_id; " +
//                "    ELSE " +
//                "        RAISE NOTICE 'Unknown status: %', NEW.status; " +
//                "    END IF; " +
//                "    RETURN NEW; " +
//                "END; " +
//                "$$ LANGUAGE plpgsql;";
//
//        System.out.println("Function SQL:\n" + createFunctionSQL); // Print the function SQL
//
//        // Execute the function creation
//        stmt.executeUpdate(createFunctionSQL);
//
//        // Create the trigger
//        String createTriggerSQL = 
//                "CREATE TRIGGER update_available_quantity_trigger " +
//                "AFTER INSERT OR UPDATE ON BorrowingTable " +
//                "FOR EACH ROW EXECUTE FUNCTION update_available_quantity_on_borrow();";
//
//        // Execute the trigger creation
//        stmt.executeUpdate(createTriggerSQL);
//
//    } catch (SQLException e) {
//        e.printStackTrace();
//        System.out.println("Error occurred while creating trigger: " + e.getMessage());
//    }
//}


    public static void createUpdateStatusTrigger() {
        try (Connection conn = DatabaseConnector.getConnection(); Statement stmt = conn.createStatement()) {

            // Create a function to update status to Lost after 3 days
            String createFunctionSQL = "CREATE OR REPLACE FUNCTION update_status_to_lost() "
                    + "RETURNS VOID AS $$ "
                    + "BEGIN "
                    + "UPDATE BorrowingTable SET status = 'Lost' "
                    + "WHERE status = 'Ongoing' AND (CURRENT_DATE - borrow_date) >= 3; "
                    + "END; "
                    + "$$ LANGUAGE plpgsql;";

            stmt.executeUpdate(createFunctionSQL);

            // Create a scheduled task to run the function daily
            String createScheduledTaskSQL = "CREATE SCHEDULED TASK update_status_to_lost_task "
                    + "RUN update_status_to_lost() "
                    + "DAILY AT '00:00';";

            // Note: The above SQL is for demonstration purposes only. The actual SQL to create a scheduled task may vary depending on the database management system being used.
            // For PostgreSQL, you can use the following SQL to create a scheduled task using the pg_cron extension:
            String createScheduledTaskSQLPostgreSQL = "INSERT INTO cron.job (command, node_name, run_count, active) "
                    + "VALUES ('CALL update_status_to_lost()', 'node_name', 0, TRUE);";

            stmt.executeUpdate(createScheduledTaskSQLPostgreSQL);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while creating trigger.");
        }
    }

}
