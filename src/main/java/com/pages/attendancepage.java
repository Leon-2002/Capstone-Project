/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.pages;
//import javax.swing.JFrame;

import java.util.regex.*;
import com.DataHandler.attendanceHandler;
import com.DatabaseConnector.DatabaseConnector;
import com.notification.WifiAlert;
import com.notification.NetworkUtil;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import java.sql.Time;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import static com.notification.NotificationFunctions.addNotification;
import com.Custombutton.RoundedButton;


public class attendancepage extends javax.swing.JFrame {

    int counter;

    public attendancepage() {
        initComponents();
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        
        AttendanceTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD,12));
        AttendanceTable.getTableHeader().setOpaque(false);
        AttendanceTable.getTableHeader().setBackground(new Color (19,4,46));
        AttendanceTable.getTableHeader().setForeground(new Color (255,255,255));
        
      // Assuming AttendanceTable is your JTable
        JTableHeader header = AttendanceTable.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));
        

        
        handleQRCodeScan("");
        attendanceqrtf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lrn = attendanceqrtf.getText();
                AttendanceShowTable(AttendanceTable);

                checkLRNInDatabase(lrn);
                // To get the Id based on lrn

            }
        });
        //wif

        AttendanceShowTable(AttendanceTable);
    }

    private void handleQRCodeScan(String qrCodeData) {
        // Set the text of the Qrtf text field
        attendanceqrtf.setText(qrCodeData);

        // Manually trigger the ActionListener
        ActionEvent event = new ActionEvent(attendanceqrtf, ActionEvent.ACTION_PERFORMED, null);
        for (ActionListener listener : attendanceqrtf.getActionListeners()) {
            listener.actionPerformed(event);
        }
    }

    private void checkLRNInDatabase(String lrn) {

        try (Connection conn = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM Studentstbl WHERE lrn = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, lrn);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // LRN found in the database
                String Id = rs.getString("id");
                String fname = rs.getString("firstname");
                 String lname = rs.getString("lastname");
                System.out.println("LRN found: " + lrn);

                int studentId = attendanceHandler.getStudentIdByLRN(lrn);

                // to record the attendance
                attendanceHandler.insertAttendanceRecord(studentId);

                AttendanceShowTable(AttendanceTable);
                attendanceqrtf.setText(""); // Clear the text field after processing
                
                 addNotification("Attendance marked for " + fname  + " "+ fname );
                // to close the login form and direct to dashboard
            } else {
                // LRN not found in the database
                System.out.println("LRN not found: " + lrn);
                JOptionPane.showMessageDialog(null, "Lrn not found");
                attendanceqrtf.setText(""); // Clear the text field after processing
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
        jLabel5 = new javax.swing.JLabel();
        attendanceqrtf = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        AttendanceTable = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new com.Custombutton.RoundedButton("back");
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(19, 4, 46));

        jPanel1.setBackground(new java.awt.Color(51, 0, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(48, 24, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/QRScan.png"))); // NOI18N
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 260, -1));

        attendanceqrtf.setBackground(new java.awt.Color(255, 255, 255));
        attendanceqrtf.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        attendanceqrtf.setForeground(new java.awt.Color(0, 0, 0));
        attendanceqrtf.setBorder(null);
        attendanceqrtf.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        attendanceqrtf.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                attendanceqrtfHierarchyChanged(evt);
            }
        });
        attendanceqrtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attendanceqrtfActionPerformed(evt);
            }
        });
        jPanel3.add(attendanceqrtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 260, 40));

        jLabel15.setFont(new java.awt.Font("Poppins", 3, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("SCAN YOUR  QR CODE");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 230, 40));

        jLabel16.setFont(new java.awt.Font("Poppins", 2, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Click here before scanning!");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 370, 250, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 440, 450));

        AttendanceTable.setBackground(new java.awt.Color(255, 255, 255));
        AttendanceTable.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        AttendanceTable.setForeground(new java.awt.Color(0, 0, 0));
        AttendanceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Last name", "Last name", "TIme"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        AttendanceTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        AttendanceTable.setDragEnabled(true);
        AttendanceTable.setFocusable(false);
        AttendanceTable.setGridColor(new java.awt.Color(0, 0, 0));
        AttendanceTable.setIntercellSpacing(new java.awt.Dimension(0, 0));
        AttendanceTable.setRowHeight(25);
        AttendanceTable.setSelectionBackground(new java.awt.Color(255, 255, 255));
        AttendanceTable.setShowVerticalLines(false);
        AttendanceTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(AttendanceTable);
        if (AttendanceTable.getColumnModel().getColumnCount() > 0) {
            AttendanceTable.getColumnModel().getColumn(0).setResizable(false);
            AttendanceTable.getColumnModel().getColumn(1).setResizable(false);
            AttendanceTable.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, 490, 310));

        jLabel14.setFont(new java.awt.Font("Poppins", 2, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("<html>"
            + "<div style='text-align: justify; font-size: 14px; line-height: 1.5; margin: 10px;'>"
            + "<strong>Welcome to the Student Attendance Page</strong><br>"
            + "This page is designed to ensure accurate and efficient monitoring of student attendance at<br>"
            + "the Elias Science Laboratory."
            + "</div>"
            + "</html>");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, 460, 120));

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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(230, 230, 230)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(31, 31, 31)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1031, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
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

        setSize(new java.awt.Dimension(1139, 658));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
        Frontpage frame = new Frontpage();
        frame.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void attendanceqrtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attendanceqrtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_attendanceqrtfActionPerformed

    private void attendanceqrtfHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_attendanceqrtfHierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_attendanceqrtfHierarchyChanged

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                attendancepage frame = new attendancepage();
                frame.setVisible(true);

            }
        });
    }

    private void checkInternetConnection() {
        WifiAlert noWifiAlert = new WifiAlert(this, true);

        new Thread(() -> {
            while (true) {
                boolean isConnected = NetworkUtil.isInternetAvailable();
                if (!isConnected) {
                    SwingUtilities.invokeLater(() -> {
                        if (!noWifiAlert.isVisible()) {
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

    public static void AttendanceShowTable(javax.swing.JTable attendanceTable) {
        String query = "SELECT * FROM attendancetbl WHERE attendance_date = CURRENT_DATE";

        try (Connection conn = DatabaseConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {

            DefaultTableModel tblModel = (DefaultTableModel) attendanceTable.getModel();
            tblModel.setRowCount(0); // Clear existing rows

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); // Define the desired format

            while (rs.next()) {
                String name = rs.getString("firstname");
                String surname = rs.getString("lastname");

                // Retrieve attendance_time as Time object
                Time attendanceTime = rs.getTime("Attendance_time");
                String formattedTime = (attendanceTime != null) ? sdf.format(attendanceTime) : "";

                // String array to store data into JTable
                String tbData[] = {name, surname, formattedTime};

                // Add String array data into table
                tblModel.addRow(tbData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable AttendanceTable;
    private javax.swing.JTextField attendanceqrtf;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
