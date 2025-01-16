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
import com.notification.BreakBorrowing;
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
import javax.swing.SwingUtilities;


public class BorrowingLogin extends javax.swing.JFrame {
    int counter;

    public BorrowingLogin() {
        initComponents();
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        
        //wifi alert
          String Qr = QrTf1.getText();
        handleQRCodeScan(Qr);
        QrTf1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String Qr = QrTf1.getText();
               // Example QR code data format
                
                checkQR(Qr);
                 QrTf1.setText(""); // Clear the text field after processing
            }
           });
       
    }
    
   private void handleQRCodeScan(String qrCodeData) {
    // Set the text of the Qrtf text field
    QrTf1.setText(qrCodeData);

    // Manually trigger the ActionListener
    ActionEvent event = new ActionEvent(QrTf1, ActionEvent.ACTION_PERFORMED, null);
    for (ActionListener listener : QrTf1.getActionListeners()) {
        listener.actionPerformed(event);
    }
}

    
  private void checkQR(String lrn) {
    try (Connection conn = DatabaseConnector.getConnection();
         PreparedStatement pstmt = conn.prepareStatement("SELECT id, email FROM StudentsTbl WHERE lrn = ?")) {
        
        pstmt.setString(1, lrn); 
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            // LRN found in the database
            System.out.println("LRN found: " + lrn);

            // Fetch the 'id' and 'email' from the result set
            int id = rs.getInt("id"); // Retrieve the 'id' of the student
            String email = rs.getString("email"); // Retrieve the 'email' of the student
            
            // Close the current login form and open the Borrowing page
            this.dispose();  // Assuming this refers to the current login form
            
            // Pass email and id to the Borrowing page
            Borrowing page = new Borrowing(email, String.valueOf(id)); 
            page.setVisible(true);
            page.pack();
        } else {
            // LRN not found in the database
            System.out.println("LRN not found: " + lrn);
            JOptionPane.showMessageDialog(null, "LRN not found");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    
  
     

    // Dummy method to simulate LRN check, replace with actual database check
  
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
        jLabel14 = new javax.swing.JLabel();
        emailtf = new javax.swing.JTextField();
        QrTf1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(16, 3, 40));

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
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 100, 30));

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
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 100, 30));

        jLabel10.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("STUDENT EMAIL:");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 130, 20));

        jLabel14.setFont(new java.awt.Font("Poppins", 3, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Student Borrowing Sign in!");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 300, 40));

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
        jPanel3.add(emailtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 320, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 400, 250));

        QrTf1.setBackground(new java.awt.Color(255, 255, 255));
        QrTf1.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        QrTf1.setForeground(new java.awt.Color(0, 0, 0));
        QrTf1.setBorder(null);
        QrTf1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        QrTf1.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                QrTf1HierarchyChanged(evt);
            }
        });
        QrTf1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QrTf1ActionPerformed(evt);
            }
        });
        jPanel1.add(QrTf1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 420, 260, 40));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/QRScan.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 210, 260, -1));

        jLabel6.setFont(new java.awt.Font("Poppins", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("OR");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 310, 60, 50));

        jLabel2.setFont(new java.awt.Font("Poppins", 2, 16));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("<html>"
            + "<div>"
            + "<strong>Welcome to the Student Borrowing Sign-In Page</strong><br>"
            + "This page is designed to streamline the borrowing process, ensuring efficient tracking and management<br>"
            + "of resources at the Elias National High School Laboratory. Students can borrow equipment by signing in<br>"
            + "with their details, making the process more organized and reliable. Signing in ensures that each borrowing<br>"
            + "record is accurately associated with the correct borrower, promoting accountability and proper resource management."
            + "</div>"
            + "</html>");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 890, 160));

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ELIAS SCIENCE LABORATORY SYSTEM");

        jLabel3.setFont(new java.awt.Font("Poppins", 1, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Admin");

        jLabel4.setFont(new java.awt.Font("Poppins", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("About");

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
                        .addGap(146, 146, 146)
                        .addComponent(jLabel3)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1031, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)
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

        setSize(new java.awt.Dimension(1116, 658));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
        Frontpage frame = new Frontpage();
        frame.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed
    
      private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private void QrTf1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QrTf1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_QrTf1ActionPerformed

    private void QrTf1HierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_QrTf1HierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_QrTf1HierarchyChanged

    private void emailtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailtfActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //         checkInternetConnection();

        String email = emailtf.getText();

        boolean isValid = UserHandler.BorrowingLogin(email);

        if((email.isEmpty())){
            JOptionPane.showMessageDialog(null, "Complete the input");
        }
        else{
            if(isValid){

                //                String role = UserCredentials.GetRoleUser(username);

                //reset the counter
                counter = 0;

                // to close the login form and direct to dashboard
                BorrowingLogin nF = new BorrowingLogin();
                nF.setVisible(false);
                dispose();

                String id = UserCredentials.Getid(email);
                //Display the Dashboard Form
                Borrowing page = new Borrowing(email, id);
                page.setVisible(true);
                page.pack();

            }
            else{
                counter+= 1;
                if(counter==3){
                    try{
                        //JOptionPane.showMessageDialog(null,"3 invalid input. \n1 minute break");
                        JOptionPane.showMessageDialog(null,"Input error");
                        dispose();
                        BreakBorrowing b = new BreakBorrowing();
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
        Frontpage frame = new Frontpage();
        frame.setVisible(true);

    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {               
                BorrowingLogin frame = new BorrowingLogin();
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
    private javax.swing.JTextField QrTf1;
    private javax.swing.JTextField emailtf;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
