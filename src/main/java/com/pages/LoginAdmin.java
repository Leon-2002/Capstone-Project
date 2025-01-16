/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.pages;
//import javax.swing.JFrame;

import com.DataHandler.UserCredentials;
import java.util.regex.*;
import com.DataHandler.UserHandler;
import com.DatabaseConnector.DatabaseConnector;
import com.notification.WifiAlert;
import com.notification.NetworkUtil;
import com.notification.Break;
import com.notification.loading;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class LoginAdmin extends javax.swing.JFrame {
    int counter;

    public LoginAdmin() {
        initComponents();
        
        String name = Qrtf.getText();
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        
        //wifi alert
       
        handleQRCodeScan("");
        Qrtf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Qr = Qrtf.getText();
               // Example QR code data format
                        if (Qr.matches("[a-zA-Z\\s]*")) {
                 String[] nameParts = Qr.split("\\s+", 2);
                 if (nameParts.length >= 2) {
                     String firstName = nameParts[0];  
                     String lastName = nameParts[1];  
                     checkQRAdmin(firstName, lastName);
                     Qrtf.setText(""); // Clear the text field after processing
                 } else {
                     JOptionPane.showMessageDialog(null, "Invalid input. Please enter both first and last names.");
                     Qrtf.setText("");
                 }
             } else {
                 JOptionPane.showMessageDialog(null, "Not valid");
                 Qrtf.setText("");
             }
             
            }
           });
       
    }


    public LoginAdmin(JTextField textField) {
        this.Qrtf = textField;
    }

    // Instance method to return the text from the Qrtf text field
    public String returnAdmin() {
        if (Qrtf != null) {
            return Qrtf.getText();
        }
        return ""; // Return an empty string if Qrtf is null
    }
    
   private void handleQRCodeScan(String qrCodeData) {
    // Set the text of the Qrtf text field
    Qrtf.setText(qrCodeData);

    // Manually trigger the ActionListener
    ActionEvent event = new ActionEvent(Qrtf, ActionEvent.ACTION_PERFORMED, null);
    for (ActionListener listener : Qrtf.getActionListeners()) {
        listener.actionPerformed(event);
    }
}

    
    private void checkQRAdmin(String firstname, String lastname) {
    

    try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {
        String query = "SELECT COUNT(*) FROM admintbl WHERE firstname = ? AND lastname = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, firstname);
       pstmt.setString(2, lastname);
        ResultSet rs = pstmt.executeQuery();
        
          String Qr =  firstname + " "+ lastname;
        if (rs.next()) {
            // LRN found in the database
            System.out.println("LRN found: " + Qr);
            // to close the login form and direct to dashboard
                LoginAdmin nF = new LoginAdmin();
                nF.setVisible(false);
                dispose();
                
                
                String email = UserCredentials.getEmailAdmin(firstname, lastname );
                //Display the Dashboard Form
                loading loadingFrame = new loading(email);
                loadingFrame.setVisible(true);
                loadingFrame.pack();
            // Perform any additional actions here (e.g., navigate to dashboard)
        } else {
            // LRN not found in the database
            System.out.println("LRN not found: " + Qr);
            JOptionPane.showMessageDialog(null, "Lrn not found");
            // Handle the case where LRN is not found
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
  
     

    // Dummy method to simulate LRN check, replace with actual database check
    private boolean checkLRN(String lrn) {
        // Replace with actual LRN checking logic
        return "123214323452".equals(lrn); // Dummy check
    }
        class RoundedPanel extends JPanel {
            private int borderRadius = 40;

            public RoundedPanel() {
                setOpaque(false); // To make the panel transparent
                setBackground(new Color(48, 24, 102)); // Set the background color
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw the rounded border
                g2d.setColor(getBackground());
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);

                // Create a dashed stroke for the border
                float dash[] = {2.0f};
                BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
                g2d.setStroke(dashed);

                // Draw the border with a dotted white line
                g2d.setColor(Color.WHITE);
                g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, borderRadius, borderRadius);
            }

            @Override
            public void setBackground(Color bg) {
                super.setBackground(bg);
                repaint();
            }
        }
               

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new com.CustomPanel.JPanelGradient();
        jPanel3 = new RoundedPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        password1 = new javax.swing.JPasswordField();
        jLabel13 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel14 = new javax.swing.JLabel();
        emailtf = new javax.swing.JTextField();
        Qrtf = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(19, 4, 46));

        jPanel1.setBackground(new java.awt.Color(51, 0, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(48, 24, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("cancel");
        jButton2.setBorder(null);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.SE_RESIZE_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 330, 100, 30));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("save");
        jButton3.setBorder(null);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.SE_RESIZE_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, 100, 30));

        jLabel10.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("EMAIL:");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 110, 20));

        password1.setBackground(new java.awt.Color(255, 255, 255));
        password1.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        password1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(password1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 320, 40));

        jLabel13.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("PASSWORD:");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 110, 20));

        jCheckBox2.setBackground(new java.awt.Color(48, 24, 102));
        jCheckBox2.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox2.setText("SHOW PASSWORD");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        jPanel3.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 130, -1));

        jLabel14.setFont(new java.awt.Font("Poppins", 3, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("ADMIN LOGIN!");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 190, 40));

        emailtf.setBackground(new java.awt.Color(255, 255, 255));
        emailtf.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        emailtf.setForeground(new java.awt.Color(0, 0, 0));
        emailtf.setBorder(null);
        emailtf.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        emailtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailtfActionPerformed(evt);
            }
        });
        jPanel3.add(emailtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 320, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 420, 390));

        Qrtf.setBackground(new java.awt.Color(255, 255, 255));
        Qrtf.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        Qrtf.setForeground(new java.awt.Color(0, 0, 0));
        Qrtf.setBorder(null);
        Qrtf.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        Qrtf.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                QrtfHierarchyChanged(evt);
            }
        });
        Qrtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QrtfActionPerformed(evt);
            }
        });
        jPanel1.add(Qrtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 350, 270, 40));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/QRScan.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 130, 280, 190));

        jLabel6.setFont(new java.awt.Font("Poppins", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("OR");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 220, 60, 50));

        jLabel15.setFont(new java.awt.Font("Poppins", 3, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("SCAN YOUR  QR CODE");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 70, 230, 40));

        jLabel16.setFont(new java.awt.Font("Poppins", 2, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Click here before scanning!");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 410, 250, 40));

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ELIAS SCIENCE LABORATORY SYSTEM");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("back");
        jButton1.setBorder(null);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.SE_RESIZE_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Elias_logo1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1031, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(47, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1139, 658));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
        Frontpage frame = new Frontpage();
        frame.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
        Frontpage frame = new Frontpage();
        frame.setVisible(true);
                
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
//         checkInternetConnection();
        
        
            String email = emailtf.getText();
            String password =  String.valueOf(password1.getPassword());
            

            boolean isValid = UserHandler.loginAdmin(email,password);
            
            if((email.isEmpty() || password.isEmpty())){
                 JOptionPane.showMessageDialog(null, "Complete the input");
            }
            else{
            if(isValid){
                
//                String role = UserCredentials.GetRoleUser(username);
               
                    //reset the counter
                counter = 0;
                          
                // to close the login form and direct to dashboard
                LoginAdmin nF = new LoginAdmin();
                nF.setVisible(false);
                dispose();
                
                //Display the Dashboard Form
                loading loadingFrame = new loading(email);
                loadingFrame.setVisible(true);
                loadingFrame.pack();
                      
            }
            else{
                counter+= 1;
                if(counter==3){
                    try{
                        //JOptionPane.showMessageDialog(null,"3 invalid input. \n1 minute break");
                        dispose();
                        Break b = new Break();
                        b.setVisible(true);
                        
                        //program will stop for a minute
                        //Thread.sleep(60000);
                         //JOptionPane.showMessageDialog(null,"1 minute done, input again");
                        // reset the counter
                        counter = 0;
                    }
                    catch(Exception e){
                        System.out.println("Interrupted");
                    }
                    
                   
                }
                else{
                    JOptionPane.showMessageDialog(null, "Invalid user");
                }
            }
    }  
        
        
    }//GEN-LAST:event_jButton3ActionPerformed
    
      private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private void emailtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailtfActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
          if(jCheckBox2.isSelected()){
            password1.setEchoChar((char)0);
        }
        else{
            password1.setEchoChar('•');
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void QrtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QrtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_QrtfActionPerformed

    private void QrtfHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_QrtfHierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_QrtfHierarchyChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {               
                LoginAdmin frame = new LoginAdmin();
                frame.setVisible(true);
                

                
            }
        });
    }
    
    private void checkInternetConnection() {
       WifiAlert noWifiAlert  = new WifiAlert(this, true);

        new Thread(() -> {
            while (true) {
                boolean isConnected = NetworkUtil.isInternetAvailable();
                if (!isConnected) {
                    SwingUtilities.invokeLater(() -> {
                        if (!noWifiAlert .isVisible()) {
                            noWifiAlert.setVisible(true);
                        }
                    });
                } else {
                    SwingUtilities.invokeLater(() -> {
                        if (noWifiAlert.isVisible()) {
                            noWifiAlert.setVisible(false);
                        }
                    });
                }

                // Sleep for a while before checking again
                try {
                    Thread.sleep(5000); // Check every 5 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Qrtf;
    private javax.swing.JTextField emailtf;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField password1;
    // End of variables declaration//GEN-END:variables
}
