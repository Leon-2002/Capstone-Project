package com.DataHandler;


import com.DatabaseConnector.DatabaseConnector;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;


import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Session;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;





public class UserHandler {
          private static final String QR_CODE_PATH = "C:\\Users\\loena\\OneDrive\\Documents\\NetBeansProjects\\capstoneproject\\src\\main\\java\\Qrfolder\\"; // Adjust path as needed
          
             
      public static boolean registerUser1(String lrn, String lastname, String firstname, String email, String grade_level, String section, String status) {
            String qrCodePath = generateQRCode(lrn, firstname, lastname);
        try (Connection conn = DatabaseConnector.getConnection()) {
            // Check if username exists
            String checkQuery = "SELECT * FROM studentstbl WHERE email = ? ";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, email);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                
                  sendEmailWithAttachment(email, qrCodePath);    
                // Insert new user
                String insertQuery = "INSERT INTO studentstbl (lrn, lastname, firstname, email, grade_level, section, status, qr_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                insertStmt.setString(1, lrn);
                insertStmt.setString(2, lastname);
                insertStmt.setString(3, firstname);
                insertStmt.setString(4, email);
                insertStmt.setString(5, grade_level);
                insertStmt.setString(6, section);
                insertStmt.setString(7, status);
                insertStmt.setString(8, qrCodePath);
                insertStmt.executeUpdate();
                return false;  
                          
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }   
      
      
    public static boolean registerUser12(String lrn, String lastname, String firstname, String email, String grade_level, String section, String status) {
            String qrCodePath = generateQRCode(lrn, firstname, lastname);
        try (Connection conn = DatabaseConnector.getConnection()) {
            // Check if username exists
            String checkQuery = "SELECT * FROM studentstbl WHERE email = ? ";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, email);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                
                  sendEmailWithAttachment(email, qrCodePath);    
                // Insert new user
                String insertQuery = "INSERT INTO studentstbl (lrn, firstname, lastname, email, grade_level, section, status, qr_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                insertStmt.setString(1, lrn);
                insertStmt.setString(2, lastname);
                insertStmt.setString(3, firstname);
                insertStmt.setString(4, email);
                insertStmt.setString(5, grade_level);
                insertStmt.setString(6, section);
                insertStmt.setString(7, status);
                insertStmt.setString(8, qrCodePath);
                insertStmt.executeUpdate();
                return false;  
                          
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }     
      
      
      
    public static boolean registerUserAdmin(String lastname, String firstname, String email, String status, String student_password, String qr_code) {
            String qrCodePath = generateQRCodeAdmin(firstname, lastname);
        try (Connection conn = DatabaseConnector.getConnection()) {
            // Check if username exists
            String checkQuery = "SELECT * FROM studentstbl WHERE email = ? ";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, email);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                
                  sendEmailWithAttachment(email, qrCodePath);    
                // Insert new user
                String insertQuery = "INSERT INTO AdminTbl (firstname, lastname, email, status, student_password, qr_code) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
               
                insertStmt.setString(1, lastname);
                insertStmt.setString(2, firstname);
                insertStmt.setString(3, email);
                insertStmt.setString(4, status);
                insertStmt.setString(5, student_password);
                insertStmt.setString(6, qrCodePath);
                insertStmt.executeUpdate();
                return false;  
                          
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }  
      
      
      
    private static String generateQRCodeAdmin(String firstname, String lastname) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String filePath = QR_CODE_PATH + firstname + " "+ lastname + ".png";
        String Qr =  firstname + " "+ lastname;
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(Qr, BarcodeFormat.QR_CODE, 250, 250);
            Path path = FileSystems.getDefault().getPath(filePath);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }
      
    private static String generateQRCode(String lrn, String firstname, String lastname) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String filePath = QR_CODE_PATH + firstname + " "+ lastname + ".png";
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(lrn, BarcodeFormat.QR_CODE, 250, 250);
            Path path = FileSystems.getDefault().getPath(filePath);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }
    
    
    
    
   
    //For login
     public static boolean loginUser(String email, String pass) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM StudentsTbl WHERE email = ? AND student_password = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, pass);   
            ResultSet rs = pstmt.executeQuery();
              

            if (rs.next()) {
                // User exists with the provided username and password
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // No user found with the provided username and password
        return false;
    }
     
     //For login
     public static boolean loginAdmin(String email, String pass) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM admintbl WHERE email = ? AND student_password = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, pass);   
            ResultSet rs = pstmt.executeQuery();
              

            if (rs.next()) {
                // User exists with the provided username and password
                int id = rs.getInt("id");
                String emailFromDb = rs.getString("email");
                String lastname = rs.getString("lastname");
                String firstname = rs.getString("firstname");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // No user found with the provided username and password
        return false;
    }
     
     public static boolean BorrowingLogin(String email) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM Studentstbl WHERE email = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
              

            if (rs.next()) {
                // User exists with the provided username and password
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // No user found with the provided username and password
        return false;
    }
     
     
     // for Qr scan login
       public boolean checkLRN(String lrn) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT COUNT(*) FROM studentstbl WHERE lrn = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, lrn);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
     
        public boolean checkQRAdmin(String firstname, String lastname) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT COUNT(*) FROM AdminTbl WHERE firstname = ? AND lastname = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, firstname);  // Set the firstname parameter
                stmt.setString(2, lastname); 
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
       
       
       
       
       
     public static void sendEmailWithAttachment(String toEmail, String qrFilePath) {
        String fromEmail = "easnhscomlab@gmail.com";
        String password = "sjbu csol ijin fsgx"; // Use an app-specific password for security
        char[] passwordCharArray = password.toCharArray();

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com"); // Replace with your SMTP host
        properties.put("mail.smtp.port", "587"); // Port for TLS
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        
        
        

             // creating session object to get properties 
             Session session = Session.getInstance(properties,
          new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
          });

                try {
    // Create a default MimeMessage object
    Message message= new MimeMessage (session); // This is where the error occurs

    // Set From: header field
    message.setFrom(new InternetAddress(fromEmail)); 

    // Set To: header field
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

    // Set Subject: header field
    message.setSubject("Welcome! Here is your QR Code");

    // Now set the actual message body
    BodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setText("Dear User,\n\nThank you for registering! Please find your QR code attached, which you can use for attendance and borrowing monitoring in the Science laboratory.\n\nWe remind you to save this QR code or print it out, as it is essential for accessing the Science lab.\n\nBest Regards,\nEASNHS Science Laboratory");



    // Create a multipart message for attachment
    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(messageBodyPart);

    // Add attachment part
    messageBodyPart = new MimeBodyPart();
    DataSource source = new FileDataSource(qrFilePath);
    messageBodyPart.setDataHandler(new DataHandler(source));
    messageBodyPart.setFileName(new File(qrFilePath).getName());
    multipart.addBodyPart(messageBodyPart);

    // Send the complete message parts
    message.setContent(multipart);

    // Send message
    Transport.send(message);

    System.out.println("Email sent successfully!");

} catch (MessagingException e) {
    e.printStackTrace(); // Print stack trace to understand the cause of the error
}
    }
       
 
     
   public static void sendEquipmentWithAttachment(String toEmail, String qrFilePath, String equipment_name) {
        String fromEmail = "easnhscomlab@gmail.com";
        String password = "sjbu csol ijin fsgx"; // Use an app-specific password for security
        char[] passwordCharArray = password.toCharArray();

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com"); // Replace with your SMTP host
        properties.put("mail.smtp.port", "587"); // Port for TLS
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        
        
        

             // creating session object to get properties 
             Session session = Session.getInstance(properties,
          new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
          });

                try {
    // Create a default MimeMessage object
    Message message= new MimeMessage (session); // This is where the error occurs

    // Set From: header field
    message.setFrom(new InternetAddress(fromEmail)); 

    // Set To: header field
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

    // Set Subject: header field
    message.setSubject("Welcome! Here is your QR Code");

    // Now set the actual message body
    BodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setText("Dear User,\n\nHere is your new inventory equipment: " + equipment_name + ".\n\nBest Regards,\nEASNHS Science Laboratory");



    // Create a multipart message for attachment
    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(messageBodyPart);

    // Add attachment part
    messageBodyPart = new MimeBodyPart();
    DataSource source = new FileDataSource(qrFilePath);
    messageBodyPart.setDataHandler(new DataHandler(source));
    messageBodyPart.setFileName(new File(qrFilePath).getName());
    multipart.addBodyPart(messageBodyPart);

    // Send the complete message parts
    message.setContent(multipart);

    // Send message
    Transport.send(message);

    System.out.println("Email sent successfully!");

} catch (MessagingException e) {
    e.printStackTrace(); // Print stack trace to understand the cause of the error
}
    }
   
   
}
