/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.pages;
//import javax.swing.JFrame;

import java.util.regex.*;
import com.DataHandler.UserHandler;
import com.notification.WifiAlert;
import com.notification.NetworkUtil;
import com.DatabaseConnector.DatabaseConnector;
import com.DatabaseConnector.TestConnector;
import com.DatabaseConnector.DatabaseConnector;
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
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class RegisterAdmin extends javax.swing.JFrame {


    public RegisterAdmin() {
        initComponents();
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        
        //wifi alert
       
       
    }
    
        class jPanelGradient extends JPanel {
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();

                // Set the rendering hints for anti-aliasing
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Create a rounded rectangle shape
                Shape shape = new RoundRectangle2D.Float(0, 0, width, height, 40, 40);

                // Set the clip to the rounded rectangle shape
                g2d.clip(shape);

                // Create the gradient colors
                Color color1 = new Color(19, 7, 46);
                Color color2 = new Color(63, 33, 130);
                GradientPaint gp = new GradientPaint(0, 0, color1, width, 0, color2);
                g2d.setPaint(gp);

                // Fill the rounded rectangle with the gradient
                g2d.fillRect(0, 0, width, height);
            }
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
        jPanel1 = new jPanelGradient();
        jPanel3 = new RoundedPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        firstnametf = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lastnametf = new javax.swing.JTextField();
        confirmpassword = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        emailtf = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        password1 = new javax.swing.JPasswordField();
        jLabel13 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel14 = new javax.swing.JLabel();
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
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 100, 30));

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
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 350, 100, 30));

        firstnametf.setBackground(new java.awt.Color(255, 255, 255));
        firstnametf.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        firstnametf.setForeground(new java.awt.Color(0, 0, 0));
        firstnametf.setBorder(null);
        firstnametf.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        firstnametf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstnametfActionPerformed(evt);
            }
        });
        jPanel3.add(firstnametf, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 240, 40));

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("FIRSTNAME:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, 80, 20));

        lastnametf.setBackground(new java.awt.Color(255, 255, 255));
        lastnametf.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        lastnametf.setForeground(new java.awt.Color(0, 0, 0));
        lastnametf.setBorder(null);
        lastnametf.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lastnametf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastnametfActionPerformed(evt);
            }
        });
        jPanel3.add(lastnametf, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 240, 40));

        confirmpassword.setBackground(new java.awt.Color(255, 255, 255));
        confirmpassword.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        confirmpassword.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(confirmpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 240, 40));

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("LASTNAME:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 80, 20));

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
        jPanel3.add(emailtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 240, 40));

        jLabel10.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("EMAIL:");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 80, 20));

        jLabel11.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("CONFIRM PASSWORD");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, 20));

        password1.setBackground(new java.awt.Color(255, 255, 255));
        password1.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        password1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(password1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 240, 40));

        jLabel13.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("PASSWORD:");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 80, 20));

        jCheckBox1.setBackground(new java.awt.Color(48, 24, 102));
        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("SHOW PASSWORD");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel3.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, -1));

        jCheckBox2.setBackground(new java.awt.Color(48, 24, 102));
        jCheckBox2.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox2.setText("SHOW PASSWORD");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        jPanel3.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, -1, -1));

        jLabel14.setFont(new java.awt.Font("Poppins", 3, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("REGISTER HERE!");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 190, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 580, 440));

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
        AdminDashBoard frame = new AdminDashBoard("loeacuna14@gmail.com");
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
        
        String fname = firstnametf.getText().toLowerCase();
        String uname = lastnametf.getText().toLowerCase();
        String email = emailtf.getText();
        String password = String.valueOf(password1.getPassword());
        String confirmPass = String.valueOf(confirmpassword.getPassword());
        String status = "Active";
        
        String qr_code = "ewewewew";
        
        // Condition when the user click the submit without providing an input
        if (fname.equals("") || uname.equals("") || email.equals("") || password.equals("") || confirmPass.equals("")){
            JOptionPane.showMessageDialog(null, "Please provide an input.");
            return;
        }
         if((email.contains("..")) || (password.contains(".."))){
                JOptionPane.showMessageDialog(null, "Input not acceptable.");
                return;
            }
           // Email validation
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(null, "Invalid email format.");
            return;
        }
            if((email.contains(" ")) || (password.contains(" "))){
                JOptionPane.showMessageDialog(null, "Invalid Sign up. Username, Password can't contains white space");
                return;
            }
            if(password.length() < 8){
                    
                    JOptionPane.showMessageDialog(null, "Password must be atleast 8 character");
                    return;
                }
            if (!fname.matches("^[a-zA-Z\\s]+$")) {
                JOptionPane.showMessageDialog(null, "Firstname Must Not Have A Number Or Special Characters");
                return;
            }
            if (!uname.matches("^[a-zA-Z\\s]+$")) {
                JOptionPane.showMessageDialog(null, "Lastname Must Not Have A Number Or Special Characters");
                return;
            }
            if (password.equals(confirmPass)) {
            boolean isExist = UserHandler.registerUserAdmin(uname, fname, email, status, password, qr_code );
                    dispose();
                    Frontpage frame = new Frontpage();
                    frame.setVisible(true);
            if (isExist) {
                JOptionPane.showMessageDialog(null, "Data Exists Already");
            } else {
                JOptionPane.showMessageDialog(null, "Account created Successfully.");
               
               

           
            }
            
            }
        
        
        
    }//GEN-LAST:event_jButton3ActionPerformed
    
      private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private void firstnametfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstnametfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstnametfActionPerformed

    private void lastnametfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastnametfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastnametfActionPerformed

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

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
          if(jCheckBox1.isSelected()){
            confirmpassword.setEchoChar((char)0);
        }
        else{
            confirmpassword.setEchoChar('•');
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {               
                RegisterAdmin frame = new RegisterAdmin();
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
    private javax.swing.JPasswordField confirmpassword;
    private javax.swing.JTextField emailtf;
    private javax.swing.JTextField firstnametf;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField lastnametf;
    private javax.swing.JPasswordField password1;
    // End of variables declaration//GEN-END:variables
}
