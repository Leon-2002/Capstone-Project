/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.pages;

import com.JTable.BorrowingtblHandler;
import com.CustomJTable.TableActionCellEditor;
import com.CustomJTable.TableActionCellRender;
import com.CustomJTable.TableActionEvent;
import com.DataHandler.InventoryDAO;
import com.DataHandler.InventoryHandler;
import com.DataHandler.StudentsDAO;
import com.DataHandler.UserHandler;
import com.DatabaseConnector.DatabaseConnector;
import com.JTable.AttendancetblHandler;
import com.JTable.BorrowingtblHandler;
import com.JTable.InventorytblHandler;
import com.JTable.StudentstblHandler;
import com.JTable.NotificationTblHandler;
import com.google.zxing.WriterException;
import com.notification.NetworkUtil;
import static com.pages.attendancepage.AttendanceShowTable;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.apache.poi.hssf.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;


import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;






/**
 *
 * @author loena
 */
public class AdminDashBoard extends javax.swing.JFrame {
    private static String email1;
    
    
    /**s
     * Creates new form StudentDashBoard
     */
    public AdminDashBoard(String email) {
        initComponents();
      
        lostItemCountField.addChangeListener(new ChangeListener() {
    @Override
    public void stateChanged(ChangeEvent e) {
        int value = (int) lostItemCountField.getValue();
        if (value < 1) {
            lostItemCountField.setValue(1);
        }
    }
});
        
        this.email1 = email;
        
        AdminEmail.setText(email);
        setBackground(new Color(0, 0, 0, 0));

        scilab.setForeground(Color.WHITE);
        dashboard.setForeground(Color.WHITE);
        inventory.setForeground(Color.WHITE);
        borrowing.setForeground(Color.WHITE);
        attendance.setForeground(Color.WHITE);
        logout.setForeground(Color.WHITE);
        students.setForeground(Color.WHITE);

        updateinventory.setForeground(Color.WHITE);
        equipmentid.setForeground(Color.WHITE);
        equipment.setForeground(Color.WHITE);
        categoryequipment.setForeground(Color.WHITE);
        quantityequipment.setForeground(Color.WHITE);
        cancelupdateinventory.setForeground(Color.WHITE);
        saveupdateinventory.setForeground(Color.WHITE);

        equipmentidTF.setForeground(Color.BLACK);
        equipmentTF.setForeground(Color.BLACK);
        quantityequipmenttF.setForeground(Color.BLACK);

        dashboardbtn.addMouseListener(dashboardmouseExitedListener);
        inventorybtn.addMouseListener(inventorymouseExitedListener);
        attendancebtn.addMouseListener(attendancemouseExitedListener);
        borrowingbtn.addMouseListener(borrowingmouseExitedListener);
        logoutbtn.addMouseListener(logoutmouseExitedListener);
        studentsbtn.addMouseListener(studentsmouseExitedListener);

        dashboardbtn.addMouseListener(dashboardmouseEnteredListener);
        attendancebtn.addMouseListener(attendancemouseEnteredListener);
        inventorybtn.addMouseListener(inventorymouseEnteredListener);
        borrowingbtn.addMouseListener(borrowingmouseEnteredListener);
        logoutbtn.addMouseListener(logoutmouseEnteredListener);
        studentsbtn.addMouseListener(studentsmouseEnteredListener);

        ShowAllbtn.addMouseListener(showAllExitedListener);
        ShowAllbtn.addMouseListener(showAllEnteredListener);

        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                System.out.println("Edit row : " + row);
                UpdateInventory.pack();
                UpdateInventory.setLocationRelativeTo(inventorypanel);
                UpdateInventory.setVisible(true);

                int selectedRow = InventoryTable.getSelectedRow();
                int id = (int) InventoryTable.getValueAt(selectedRow, 0); // int value
                String stringid = InventoryTable.getValueAt(selectedRow, 0).toString();

                String equipmentname = InventoryDAO.getEquipmentNameById(id);
                int equipmentquantity = InventoryDAO.getQuantityById(id);
                String equipmentquantityStr = String.valueOf(equipmentquantity);
                String equipmentcategory = InventoryDAO.getCategoryById(id);

                System.out.println(String.format("Equipment Name: %s", equipmentname));
                System.out.println(String.format("Equipment Quantity: %s", equipmentquantityStr));
                System.out.println(String.format("Equipment Category: %s", equipmentcategory));
                System.out.println(String.format("Equipment ID: %s", stringid));

                equipmentidTF.setText(stringid);
                equipmentTF.setText(equipmentname);
                quantityequipmenttF.setText(equipmentquantityStr);
                categoryequipmentCB.setSelectedItem(equipmentcategory);
            }

            @Override
            public void onDelete(int row) {
               if (InventoryTable.isEditing()) {
    InventoryTable.getCellEditor().stopCellEditing();
}

int selectedRow = InventoryTable.getSelectedRow();
if (selectedRow == -1) {
    JOptionPane.showMessageDialog(null, "Please select an inventory item to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
    return;
}

int id = (int) InventoryTable.getValueAt(selectedRow, 0); // 0 is the column index of the ID column

// Show confirmation dialog
int choice = JOptionPane.showConfirmDialog(
        null,
        "Do you want to delete this inventory item?",
        "Confirm Deletion",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE
);

if (choice == JOptionPane.YES_OPTION) {
    InventorytblHandler.deleteInventoryRow(id);
    InventorytblHandler.InventoryShowTable(InventoryTable);
    JOptionPane.showMessageDialog(null, "Inventory item deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
} else {
    JOptionPane.showMessageDialog(null, "Deletion cancelled.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
}

            }
        };

        TableActionEvent studentsevent = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                System.out.println("Edit row : " + row);

                UpdateStudents.pack();
                UpdateStudents.setLocationRelativeTo(studentspanel);
                UpdateStudents.setVisible(true);

                int selectedRow = StudentsTable.getSelectedRow();
                int id = (int) StudentsTable.getValueAt(selectedRow, 0); // int value
                String stringid = StudentsTable.getValueAt(selectedRow, 0).toString();

                String lastname = StudentsDAO.getLastnameById(id);
                String firstname = StudentsDAO.getFirstnameById(id);
                String lrn = StudentsDAO.getLrnById(id);
                String email = StudentsDAO.getEmailById(id);
                String grade = StudentsDAO.getGradeLevelById(id);
                String status = StudentsDAO.getStatusById(id);

                studentidTF.setText(stringid);
                lastnameTF.setText(lastname);
                firstnameTF.setText(firstname);
                lrnTF.setText(lrn);
                emailTF.setText(email);
                yearcb.setSelectedItem(grade);
                statuscb.setSelectedItem(status);

            }

            @Override
            public void onDelete(int row) {
               if (StudentsTable.isEditing()) {
    StudentsTable.getCellEditor().stopCellEditing();
}

int selectedRow = StudentsTable.getSelectedRow();
if (selectedRow == -1) {
    JOptionPane.showMessageDialog(null, "Please select a student to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
    return;
}

int id = (int) StudentsTable.getValueAt(selectedRow, 0);

// Show confirmation dialog
int choice = JOptionPane.showConfirmDialog(
        null,
        "Do you want to delete this student?",
        "Confirm Deletion",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE
);

if (choice == JOptionPane.YES_OPTION) {
    StudentstblHandler.deleteStudent(id);
    StudentstblHandler.showStudentsTable(StudentsTable);
    JOptionPane.showMessageDialog(null, "Student deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
} else {
    JOptionPane.showMessageDialog(null, "Deletion cancelled.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
}

            }
        };
        
        lostItemCountField.setVisible(false);
        DamagedLabel.setVisible(false);
        
        
         TableActionEvent borrowingevent = new TableActionEvent() {
    @Override
    public void onEdit(int row) {
        // Get the status of the selected row
        String status = BorrowingTable.getValueAt(row, 6).toString();

        // Check if the status is "completed"
        if ("Completed".equals(status)) {
            // If the status is "completed", show a message and return
            JOptionPane.showMessageDialog(
                null, 
                "You can't edit this borrowing record because it is already completed.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
            return;  // Prevent further processing of the edit action
        }

        // Proceed with edit if status is not "completed"
        System.out.println("Edit row : " + row);

        UpdateBorrow.pack();
        UpdateBorrow.setLocationRelativeTo(borrowingpanel);
        UpdateBorrow.setVisible(true);

        int selectedRow = BorrowingTable.getSelectedRow();
        String stringid = BorrowingTable.getValueAt(selectedRow, 0).toString();
        String name = BorrowingTable.getValueAt(selectedRow, 1).toString();
        String equipment_name = BorrowingTable.getValueAt(selectedRow, 2).toString();
        String quantity = BorrowingTable.getValueAt(selectedRow, 3).toString();
        String statusText = BorrowingTable.getValueAt(selectedRow, 6).toString();

        // Populate the fields in the UpdateBorrow form
        equipmentidTF1.setText(stringid);
        equipmentTF3.setText(name);
        statusTf.setSelectedItem(statusText);
        EquiptmentTf.setText(equipment_name);
        quantityTf.setText(quantity);
    }

    @Override
    public void onDelete(int row) {
        if (BorrowingTable.isEditing()) {
            BorrowingTable.getCellEditor().stopCellEditing();
        }

        int selectedRow = BorrowingTable.getSelectedRow();
        int id = (int) BorrowingTable.getValueAt(selectedRow, 0);

        // Proceed with deletion logic (currently commented out)
        // StudentstblHandler.deleteStudent(id);
        // StudentstblHandler.showStudentsTable(StudentsTable);
    }
};



        InventoryTable.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        InventoryTable.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event));

        StudentsTable.getColumnModel().getColumn(8).setCellRenderer(new TableActionCellRender());
        StudentsTable.getColumnModel().getColumn(8).setCellEditor(new TableActionCellEditor(studentsevent));
        
        BorrowingTable.getColumnModel().getColumn(7).setCellRenderer(new TableActionCellRender());
        BorrowingTable.getColumnModel().getColumn(7).setCellEditor(new TableActionCellEditor(borrowingevent));

//        NotificationsTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
//        NotificationsTable.getTableHeader().setOpaque(false);7
//        NotificationsTable.getTableHeader().setBackground(new Color(19, 4, 6));
//        NotificationsTable.getTableHeader().setForeground(new Color(255, 255, 255));
//
//        AttendanceTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
//        AttendanceTable.getTableHeader().setOpaque(false);
//        AttendanceTable.getTableHeader().setBackground(new Color(19, 4, 46));
//        AttendanceTable.getTableHeader().setForeground(new Color(255, 255, 255));

        // Populate the JTable with attendance data
        AttendancetblHandler.AttendanceShowTable(AttendanceTable);
        InventorytblHandler.InventoryShowTable(InventoryTable);
        StudentstblHandler.showStudentsTable(StudentsTable);
        BorrowingtblHandler.populateBorrowingTable(BorrowingTable);
        NotificationTblHandler.fetchNotifications(notiicationTable);
        // Assuming AttendanceTable is your JTable
//        JTableHeader header = NotificationsTable.getTableHeader();
//        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));

        dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    java.util.Date selectedDate = (java.util.Date) evt.getNewValue(); // Use getNewValue() for PropertyChangeEvent
                    if (selectedDate != null) {
                        AttendancetblHandler.updateTableWithDate(AttendanceTable, selectedDate);
                    }
                }
            }
        });
        
        dateChooser1.addPropertyChangeListener(new PropertyChangeListener() {
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("date".equals(evt.getPropertyName())) {
            java.util.Date selectedDate = (java.util.Date) evt.getNewValue(); // Use getNewValue() for PropertyChangeEvent
            if (selectedDate != null) {
                BorrowingtblHandler.updateTableWithDate(BorrowingTable, selectedDate); // Call the updated method
            }
        }
    }
});


        searchtf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTable();
            }

            private void updateTable() {
                AttendancetblHandler.searchUpdateTable(AttendanceTable, searchtf);
            }
        });

        inventorysearchtf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTable();
            }

            private void updateTable() {
                InventorytblHandler.searchInventoryTable(InventoryTable, inventorysearchtf);
            }
        });

        studentssearchtf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTable();
            }

            private void updateTable() {
                StudentstblHandler.searchStudentsTable(StudentsTable, studentssearchtf);
            }
        });
        
        
        searchtf1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTable();
            }

            private void updateTable() {
                BorrowingtblHandler.searchBorrowingTable(BorrowingTable, searchtf1);
            }
        });

        // Set the visibility of the panels
        dashboardpanel.setVisible(true);
        attendancepanel.setVisible(false);
        inventorypanel.setVisible(false);
        borrowingpanel.setVisible(false);
        studentspanel.setVisible(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        UpdateInventory = new javax.swing.JDialog();
        left = new com.CustomPanel.menuPanel();
        updateinventory = new javax.swing.JLabel();
        equipmentid = new javax.swing.JLabel();
        equipmentidTF = new javax.swing.JTextField();
        equipmentTF = new javax.swing.JTextField();
        equipment = new javax.swing.JLabel();
        categoryequipment = new javax.swing.JLabel();
        quantityequipmenttF = new javax.swing.JTextField();
        quantityequipment = new javax.swing.JLabel();
        categoryequipmentCB = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        cancelupdateinventory = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        saveupdateinventory = new javax.swing.JLabel();
        UpdateStudents = new javax.swing.JDialog();
        left1 = new com.CustomPanel.menuPanel();
        updateinventory1 = new javax.swing.JLabel();
        equipmentid1 = new javax.swing.JLabel();
        studentidTF = new javax.swing.JTextField();
        lastnameTF = new javax.swing.JTextField();
        equipment1 = new javax.swing.JLabel();
        categoryequipment1 = new javax.swing.JLabel();
        lrnTF = new javax.swing.JTextField();
        quantityequipment1 = new javax.swing.JLabel();
        yearcb = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        cancelupdateinventory1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        saveupdateinventory1 = new javax.swing.JLabel();
        quantityequipment2 = new javax.swing.JLabel();
        categoryequipment2 = new javax.swing.JLabel();
        equipment2 = new javax.swing.JLabel();
        equipmentid2 = new javax.swing.JLabel();
        firstnameTF = new javax.swing.JTextField();
        emailTF = new javax.swing.JTextField();
        sectioncb = new javax.swing.JComboBox<>();
        statuscb = new javax.swing.JComboBox<>();
        AddInventory = new javax.swing.JDialog();
        left2 = new com.CustomPanel.menuPanel();
        updateinventory2 = new javax.swing.JLabel();
        equipmentTF1 = new javax.swing.JTextField();
        equipment3 = new javax.swing.JLabel();
        categoryequipment3 = new javax.swing.JLabel();
        quantityequipmenttF1 = new javax.swing.JTextField();
        quantityequipment3 = new javax.swing.JLabel();
        categoryequipmentCB1 = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        cancelupdateinventory2 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        saveupdateinventory2 = new javax.swing.JLabel();
        AddStudents = new javax.swing.JDialog();
        left3 = new com.CustomPanel.menuPanel();
        updateinventory3 = new javax.swing.JLabel();
        lastnametf = new javax.swing.JTextField();
        equipment4 = new javax.swing.JLabel();
        categoryequipment4 = new javax.swing.JLabel();
        lrntf = new javax.swing.JTextField();
        quantityequipment4 = new javax.swing.JLabel();
        yearcb1 = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        cancelupdateinventory3 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        saveupdateinventory3 = new javax.swing.JLabel();
        categoryequipment5 = new javax.swing.JLabel();
        equipment5 = new javax.swing.JLabel();
        equipmentid4 = new javax.swing.JLabel();
        firstnametf = new javax.swing.JTextField();
        emailtf = new javax.swing.JTextField();
        sectioncb1 = new javax.swing.JComboBox<>();
        AddBorrowing = new javax.swing.JDialog();
        left4 = new com.CustomPanel.menuPanel();
        updateinventory4 = new javax.swing.JLabel();
        equipmentTF2 = new javax.swing.JTextField();
        equipment6 = new javax.swing.JLabel();
        categoryequipment6 = new javax.swing.JLabel();
        quantityequipmenttF2 = new javax.swing.JTextField();
        quantityequipment5 = new javax.swing.JLabel();
        categoryequipmentCB2 = new javax.swing.JComboBox<>();
        jPanel14 = new javax.swing.JPanel();
        cancelupdateinventory4 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        saveupdateinventory4 = new javax.swing.JLabel();
        UpdateBorrow = new javax.swing.JDialog();
        left5 = new com.CustomPanel.menuPanel();
        updateinventory5 = new javax.swing.JLabel();
        equipmentid3 = new javax.swing.JLabel();
        equipmentidTF1 = new javax.swing.JTextField();
        equipmentTF3 = new javax.swing.JTextField();
        equipment7 = new javax.swing.JLabel();
        categoryequipment7 = new javax.swing.JLabel();
        statusTf = new javax.swing.JComboBox<>();
        jPanel16 = new javax.swing.JPanel();
        cancelupdateinventory5 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        saveupdateinventory5 = new javax.swing.JLabel();
        equipment8 = new javax.swing.JLabel();
        EquiptmentTf = new javax.swing.JTextField();
        lostItemCountField = new javax.swing.JSpinner();
        DamagedLabel = new javax.swing.JLabel();
        quantityTf = new javax.swing.JTextField();
        quantitylbl = new javax.swing.JLabel();
        lostItemCountLabel = new javax.swing.JLabel();
        AddInventory_multiple = new javax.swing.JDialog();
        left6 = new com.CustomPanel.menuPanel();
        updateinventory6 = new javax.swing.JLabel();
        equipment9 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        cancelupdateinventory6 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        saveupdateinventory6 = new javax.swing.JLabel();
        AddAdmin = new javax.swing.JDialog();
        left7 = new com.CustomPanel.menuPanel();
        updateinventory7 = new javax.swing.JLabel();
        lastnameadmin = new javax.swing.JTextField();
        equipment10 = new javax.swing.JLabel();
        categoryequipment8 = new javax.swing.JLabel();
        quantityequipment6 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        cancelupdateinventory7 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        saveupdateinventory7 = new javax.swing.JLabel();
        equipment11 = new javax.swing.JLabel();
        equipmentid5 = new javax.swing.JLabel();
        firstnameAdmin = new javax.swing.JTextField();
        emailtfadmin = new javax.swing.JTextField();
        passwordAdmin = new javax.swing.JPasswordField();
        ConfirmPAsswordAdmin = new javax.swing.JPasswordField();
        showPasswordCheckbox = new javax.swing.JCheckBox();
        confirmpassword = new javax.swing.JCheckBox();
        ChangePasswordAdmin = new javax.swing.JDialog();
        left8 = new com.CustomPanel.menuPanel();
        equipment12 = new javax.swing.JLabel();
        categoryequipment9 = new javax.swing.JLabel();
        quantityequipment7 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        cancelupdateinventory8 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        saveupdateinventory8 = new javax.swing.JLabel();
        emailLbl = new javax.swing.JLabel();
        oldPaswword = new javax.swing.JPasswordField();
        NewPassword = new javax.swing.JPasswordField();
        Confirmpassword = new javax.swing.JPasswordField();
        equipment13 = new javax.swing.JLabel();
        AdminEmail = new javax.swing.JTextField();
        jPanel2 = new com.CustomPanel.menuPanel();
        scilab = new javax.swing.JLabel();
        dashboardbtn = new javax.swing.JPanel();
        dashboard = new javax.swing.JLabel();
        attendancebtn = new javax.swing.JPanel();
        attendance = new javax.swing.JLabel();
        borrowingbtn = new javax.swing.JPanel();
        borrowing = new javax.swing.JLabel();
        inventorybtn = new javax.swing.JPanel();
        inventory = new javax.swing.JLabel();
        logoutbtn = new javax.swing.JPanel();
        logout = new javax.swing.JLabel();
        studentsbtn = new javax.swing.JPanel();
        students = new javax.swing.JLabel();
        overlaypanel = new javax.swing.JPanel();
        borrowingpanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        searchtf1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        dateChooser1 = new com.toedter.calendar.JDateChooser();
        ShowAllbtn3 = new javax.swing.JPanel();
        showAll3 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        BorrowingTable = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        attendancepanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        searchtf = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        dateChooser = new com.toedter.calendar.JDateChooser();
        ShowAllbtn = new javax.swing.JPanel();
        showAll = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        AttendanceTable = new javax.swing.JTable();
        inventorypanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        inventorysearchtf = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        ShowAllbtn1 = new javax.swing.JPanel();
        showAll1 = new javax.swing.JLabel();
        AddBtn = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        InventoryTable = new javax.swing.JTable();
        studentspanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        StudentsTable = new javax.swing.JTable();
        studentssearchtf = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        ShowAllbtn2 = new javax.swing.JPanel();
        showAll2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        dashboardpanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cardForm1 = new com.CustomCards.CardForm();
        jScrollPane1 = new javax.swing.JScrollPane();
        notiicationTable = new javax.swing.JTable();
        AddAdminbtn = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        left.setBackground(new java.awt.Color(51, 51, 51));

        updateinventory.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        updateinventory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/inventory.png"))); // NOI18N
        updateinventory.setText("UPDATE INVENTORY");

        equipmentid.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        equipmentid.setText("EQUIPMENT ID");

        equipmentidTF.setEditable(false);
        equipmentidTF.setBackground(new java.awt.Color(255, 255, 255));
        equipmentidTF.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        equipmentidTF.setForeground(new java.awt.Color(204, 204, 204));
        equipmentidTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equipmentidTFActionPerformed(evt);
            }
        });

        equipmentTF.setBackground(new java.awt.Color(255, 255, 255));
        equipmentTF.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        equipmentTF.setForeground(new java.awt.Color(204, 204, 204));
        equipmentTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equipmentTFActionPerformed(evt);
            }
        });

        equipment.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        equipment.setText("EQUIPMENT ");

        categoryequipment.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        categoryequipment.setText("CATEGORY");

        quantityequipmenttF.setBackground(new java.awt.Color(255, 255, 255));
        quantityequipmenttF.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        quantityequipmenttF.setForeground(new java.awt.Color(204, 204, 204));
        quantityequipmenttF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityequipmenttFActionPerformed(evt);
            }
        });

        quantityequipment.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        quantityequipment.setText(" QUANTITY");

        categoryequipmentCB.setBackground(new java.awt.Color(255, 255, 255));
        categoryequipmentCB.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        categoryequipmentCB.setForeground(new java.awt.Color(204, 204, 204));
        categoryequipmentCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default", "Chemistry", "Biology", "Earth Science", "Physics" }));
        categoryequipmentCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryequipmentCBActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(118, 44, 235));

        cancelupdateinventory.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        cancelupdateinventory.setText("CANCEL");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(cancelupdateinventory, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelupdateinventory)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(118, 44, 235));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
        });

        saveupdateinventory.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        saveupdateinventory.setText("SAVE");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(saveupdateinventory)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveupdateinventory)
                .addContainerGap())
        );

        javax.swing.GroupLayout leftLayout = new javax.swing.GroupLayout(left);
        left.setLayout(leftLayout);
        leftLayout.setHorizontalGroup(
            leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(equipmentidTF)
                    .addComponent(updateinventory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(equipmentid)
                    .addComponent(equipmentTF)
                    .addComponent(equipment)
                    .addComponent(categoryequipment)
                    .addComponent(quantityequipmenttF)
                    .addComponent(quantityequipment)
                    .addComponent(categoryequipmentCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftLayout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        leftLayout.setVerticalGroup(
            leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(updateinventory)
                .addGap(39, 39, 39)
                .addComponent(equipmentid)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(equipmentidTF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(equipment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(equipmentTF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(categoryequipment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(categoryequipmentCB, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(quantityequipment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quantityequipmenttF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout UpdateInventoryLayout = new javax.swing.GroupLayout(UpdateInventory.getContentPane());
        UpdateInventory.getContentPane().setLayout(UpdateInventoryLayout);
        UpdateInventoryLayout.setHorizontalGroup(
            UpdateInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(left, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        UpdateInventoryLayout.setVerticalGroup(
            UpdateInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(left, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        left1.setBackground(new java.awt.Color(51, 51, 51));

        updateinventory1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        updateinventory1.setForeground(new java.awt.Color(255, 255, 255));
        updateinventory1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/attendance.png"))); // NOI18N
        updateinventory1.setText("UPDATE STUDENTS");

        equipmentid1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        equipmentid1.setForeground(new java.awt.Color(255, 255, 255));
        equipmentid1.setText("STUDENT ID");

        studentidTF.setEditable(false);
        studentidTF.setBackground(new java.awt.Color(255, 255, 255));
        studentidTF.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        studentidTF.setForeground(new java.awt.Color(0, 0, 0));
        studentidTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentidTFActionPerformed(evt);
            }
        });

        lastnameTF.setBackground(new java.awt.Color(255, 255, 255));
        lastnameTF.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        lastnameTF.setForeground(new java.awt.Color(0, 0, 0));
        lastnameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastnameTFActionPerformed(evt);
            }
        });

        equipment1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        equipment1.setForeground(new java.awt.Color(255, 255, 255));
        equipment1.setText("LASTNAME");

        categoryequipment1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        categoryequipment1.setForeground(new java.awt.Color(255, 255, 255));
        categoryequipment1.setText("FIRSTNAME");

        lrnTF.setEditable(false);
        lrnTF.setBackground(new java.awt.Color(255, 255, 255));
        lrnTF.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        lrnTF.setForeground(new java.awt.Color(0, 0, 0));
        lrnTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lrnTFActionPerformed(evt);
            }
        });

        quantityequipment1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        quantityequipment1.setForeground(new java.awt.Color(255, 255, 255));
        quantityequipment1.setText("LRN");

        yearcb.setBackground(new java.awt.Color(255, 255, 255));
        yearcb.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        yearcb.setForeground(new java.awt.Color(0, 0, 0));
        yearcb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default", "Grade 7", "Grade 8", "Grade 9", "Grade 10" }));
        yearcb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearcbActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(118, 44, 235));

        cancelupdateinventory1.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        cancelupdateinventory1.setForeground(new java.awt.Color(255, 255, 255));
        cancelupdateinventory1.setText("CANCEL");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(cancelupdateinventory1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelupdateinventory1)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(118, 44, 235));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
        });

        saveupdateinventory1.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        saveupdateinventory1.setForeground(new java.awt.Color(255, 255, 255));
        saveupdateinventory1.setText("SAVE");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(saveupdateinventory1)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveupdateinventory1)
                .addContainerGap())
        );

        quantityequipment2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        quantityequipment2.setForeground(new java.awt.Color(255, 255, 255));
        quantityequipment2.setText("STATUS");

        categoryequipment2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        categoryequipment2.setForeground(new java.awt.Color(255, 255, 255));
        categoryequipment2.setText("SECTION");

        equipment2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        equipment2.setForeground(new java.awt.Color(255, 255, 255));
        equipment2.setText("GRADE ");

        equipmentid2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        equipmentid2.setForeground(new java.awt.Color(255, 255, 255));
        equipmentid2.setText("EMAIL");

        firstnameTF.setBackground(new java.awt.Color(255, 255, 255));
        firstnameTF.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        firstnameTF.setForeground(new java.awt.Color(0, 0, 0));
        firstnameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstnameTFActionPerformed(evt);
            }
        });

        emailTF.setEditable(false);
        emailTF.setBackground(new java.awt.Color(255, 255, 255));
        emailTF.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        emailTF.setForeground(new java.awt.Color(0, 0, 0));
        emailTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTFActionPerformed(evt);
            }
        });

        sectioncb.setBackground(new java.awt.Color(255, 255, 255));
        sectioncb.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        sectioncb.setForeground(new java.awt.Color(0, 0, 0));
        sectioncb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sectioncbActionPerformed(evt);
            }
        });

        statuscb.setBackground(new java.awt.Color(255, 255, 255));
        statuscb.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        statuscb.setForeground(new java.awt.Color(0, 0, 0));
        statuscb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default", "Active", "Inactive" }));
        statuscb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statuscbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout left1Layout = new javax.swing.GroupLayout(left1);
        left1.setLayout(left1Layout);
        left1Layout.setHorizontalGroup(
            left1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, left1Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(left1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(studentidTF, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                    .addComponent(equipmentid1)
                    .addComponent(lastnameTF)
                    .addComponent(equipment1)
                    .addComponent(categoryequipment1)
                    .addComponent(lrnTF)
                    .addComponent(quantityequipment1)
                    .addComponent(firstnameTF))
                .addGap(59, 59, 59)
                .addGroup(left1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(equipmentid2)
                    .addComponent(equipment2)
                    .addComponent(categoryequipment2)
                    .addComponent(quantityequipment2)
                    .addComponent(emailTF, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearcb, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sectioncb, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statuscb, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(left1Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50))
            .addGroup(left1Layout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(updateinventory1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        left1Layout.setVerticalGroup(
            left1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(left1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(updateinventory1)
                .addGap(44, 44, 44)
                .addGroup(left1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(left1Layout.createSequentialGroup()
                        .addComponent(equipmentid1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(studentidTF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(equipment1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lastnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(categoryequipment1)
                        .addGap(16, 16, 16)
                        .addComponent(firstnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(quantityequipment1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lrnTF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(left1Layout.createSequentialGroup()
                        .addComponent(equipmentid2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emailTF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(equipment2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yearcb, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(categoryequipment2)
                        .addGap(16, 16, 16)
                        .addComponent(sectioncb, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(quantityequipment2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statuscb, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addGroup(left1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout UpdateStudentsLayout = new javax.swing.GroupLayout(UpdateStudents.getContentPane());
        UpdateStudents.getContentPane().setLayout(UpdateStudentsLayout);
        UpdateStudentsLayout.setHorizontalGroup(
            UpdateStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(left1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        UpdateStudentsLayout.setVerticalGroup(
            UpdateStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(left1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        left2.setBackground(new java.awt.Color(51, 51, 51));

        updateinventory2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        updateinventory2.setForeground(new java.awt.Color(255, 255, 255));
        updateinventory2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/inventory.png"))); // NOI18N
        updateinventory2.setText("ADD EQUIPMENT");

        equipmentTF1.setBackground(new java.awt.Color(255, 255, 255));
        equipmentTF1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        equipmentTF1.setForeground(new java.awt.Color(0, 0, 0));
        equipmentTF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equipmentTF1ActionPerformed(evt);
            }
        });

        equipment3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        equipment3.setForeground(new java.awt.Color(255, 255, 255));
        equipment3.setText("EQUIPMENT ");

        categoryequipment3.setBackground(new java.awt.Color(255, 255, 255));
        categoryequipment3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        categoryequipment3.setForeground(new java.awt.Color(255, 255, 255));
        categoryequipment3.setText("CATEGORY");

        quantityequipmenttF1.setBackground(new java.awt.Color(255, 255, 255));
        quantityequipmenttF1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        quantityequipmenttF1.setForeground(new java.awt.Color(0, 0, 0));
        quantityequipmenttF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityequipmenttF1ActionPerformed(evt);
            }
        });

        quantityequipment3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        quantityequipment3.setForeground(new java.awt.Color(255, 255, 255));
        quantityequipment3.setText(" QUANTITY");

        categoryequipmentCB1.setBackground(new java.awt.Color(255, 255, 255));
        categoryequipmentCB1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        categoryequipmentCB1.setForeground(new java.awt.Color(0, 0, 0));
        categoryequipmentCB1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default", "Chemistry", "Biology", "Earth Science", "Physics" }));
        categoryequipmentCB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryequipmentCB1ActionPerformed(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(118, 44, 235));

        cancelupdateinventory2.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        cancelupdateinventory2.setForeground(new java.awt.Color(255, 255, 255));
        cancelupdateinventory2.setText("CANCEL");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(cancelupdateinventory2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelupdateinventory2)
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(118, 44, 235));
        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel11MouseClicked(evt);
            }
        });

        saveupdateinventory2.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        saveupdateinventory2.setForeground(new java.awt.Color(255, 255, 255));
        saveupdateinventory2.setText("SAVE");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(saveupdateinventory2)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveupdateinventory2)
                .addContainerGap())
        );

        javax.swing.GroupLayout left2Layout = new javax.swing.GroupLayout(left2);
        left2.setLayout(left2Layout);
        left2Layout.setHorizontalGroup(
            left2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(left2Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(left2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(updateinventory2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(equipmentTF1)
                    .addComponent(equipment3)
                    .addComponent(categoryequipment3)
                    .addComponent(quantityequipmenttF1)
                    .addComponent(quantityequipment3)
                    .addComponent(categoryequipmentCB1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, left2Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(36, 36, 36)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        left2Layout.setVerticalGroup(
            left2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(left2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(updateinventory2)
                .addGap(26, 26, 26)
                .addComponent(equipment3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(equipmentTF1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(categoryequipment3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(categoryequipmentCB1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(quantityequipment3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quantityequipmenttF1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(left2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout AddInventoryLayout = new javax.swing.GroupLayout(AddInventory.getContentPane());
        AddInventory.getContentPane().setLayout(AddInventoryLayout);
        AddInventoryLayout.setHorizontalGroup(
            AddInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(left2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        AddInventoryLayout.setVerticalGroup(
            AddInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(left2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        left3.setBackground(new java.awt.Color(51, 51, 51));

        updateinventory3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        updateinventory3.setForeground(new java.awt.Color(255, 255, 255));
        updateinventory3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/attendance.png"))); // NOI18N
        updateinventory3.setText("ADD STUDENTS");

        lastnametf.setBackground(new java.awt.Color(255, 255, 255));
        lastnametf.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        lastnametf.setForeground(new java.awt.Color(0, 0, 0));
        lastnametf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastnametfActionPerformed(evt);
            }
        });

        equipment4.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        equipment4.setForeground(new java.awt.Color(255, 255, 255));
        equipment4.setText("First name");

        categoryequipment4.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        categoryequipment4.setForeground(new java.awt.Color(255, 255, 255));
        categoryequipment4.setText("email");

        lrntf.setBackground(new java.awt.Color(255, 255, 255));
        lrntf.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        lrntf.setForeground(new java.awt.Color(0, 0, 0));
        lrntf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lrntfActionPerformed(evt);
            }
        });

        quantityequipment4.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        quantityequipment4.setForeground(new java.awt.Color(255, 255, 255));
        quantityequipment4.setText("LRN");

        yearcb1.setBackground(new java.awt.Color(255, 255, 255));
        yearcb1.setEditable(true);
        yearcb1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        yearcb1.setForeground(new java.awt.Color(0, 0, 0));
        yearcb1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default", "Grade 7", "Grade 8", "Grade 9", "Grade 10" }));
        yearcb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearcb1ActionPerformed(evt);
            }
        });

        jPanel12.setBackground(new java.awt.Color(118, 44, 235));

        cancelupdateinventory3.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        cancelupdateinventory3.setForeground(new java.awt.Color(255, 255, 255));
        cancelupdateinventory3.setText("CANCEL");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(cancelupdateinventory3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelupdateinventory3)
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(118, 44, 235));
        jPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel13MouseClicked(evt);
            }
        });

        saveupdateinventory3.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        saveupdateinventory3.setForeground(new java.awt.Color(255, 255, 255));
        saveupdateinventory3.setText("SAVE");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(saveupdateinventory3)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveupdateinventory3)
                .addContainerGap())
        );

        categoryequipment5.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        categoryequipment5.setForeground(new java.awt.Color(255, 255, 255));
        categoryequipment5.setText("SECTION");

        equipment5.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        equipment5.setForeground(new java.awt.Color(255, 255, 255));
        equipment5.setText("GRADE ");

        equipmentid4.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        equipmentid4.setForeground(new java.awt.Color(255, 255, 255));
        equipmentid4.setText("Last name");

        firstnametf.setBackground(new java.awt.Color(255, 255, 255));
        firstnametf.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        firstnametf.setForeground(new java.awt.Color(0, 0, 0));
        firstnametf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstnametfActionPerformed(evt);
            }
        });

        emailtf.setBackground(new java.awt.Color(255, 255, 255));
        emailtf.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        emailtf.setForeground(new java.awt.Color(0, 0, 0));
        emailtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailtfActionPerformed(evt);
            }
        });

        sectioncb1.setBackground(new java.awt.Color(255, 255, 255));
        sectioncb1.setEditable(true);
        sectioncb1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        sectioncb1.setForeground(new java.awt.Color(0, 0, 0));
        sectioncb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sectioncb1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout left3Layout = new javax.swing.GroupLayout(left3);
        left3.setLayout(left3Layout);
        left3Layout.setHorizontalGroup(
            left3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(left3Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(left3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, left3Layout.createSequentialGroup()
                        .addGroup(left3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(categoryequipment4)
                            .addComponent(lrntf, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(quantityequipment4)
                            .addComponent(equipment4)
                            .addComponent(emailtf, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(firstnametf, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)
                        .addGroup(left3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(equipmentid4)
                            .addComponent(equipment5)
                            .addComponent(categoryequipment5)
                            .addComponent(yearcb1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sectioncb1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(left3Layout.createSequentialGroup()
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lastnametf, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, left3Layout.createSequentialGroup()
                        .addComponent(updateinventory3)
                        .addGap(229, 229, 229))))
        );
        left3Layout.setVerticalGroup(
            left3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(left3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(updateinventory3)
                .addGap(44, 44, 44)
                .addGroup(left3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(left3Layout.createSequentialGroup()
                        .addComponent(equipment4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(firstnametf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(categoryequipment4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emailtf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(quantityequipment4)
                        .addGap(18, 18, 18)
                        .addComponent(lrntf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120))
                    .addGroup(left3Layout.createSequentialGroup()
                        .addComponent(equipmentid4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lastnametf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(equipment5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yearcb1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(categoryequipment5)
                        .addGap(16, 16, 16)
                        .addComponent(sectioncb1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122)))
                .addGroup(left3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout AddStudentsLayout = new javax.swing.GroupLayout(AddStudents.getContentPane());
        AddStudents.getContentPane().setLayout(AddStudentsLayout);
        AddStudentsLayout.setHorizontalGroup(
            AddStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(left3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        AddStudentsLayout.setVerticalGroup(
            AddStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(left3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        left4.setBackground(new java.awt.Color(51, 51, 51));

        updateinventory4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        updateinventory4.setForeground(new java.awt.Color(255, 255, 255));
        updateinventory4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/inventory.png"))); // NOI18N
        updateinventory4.setText("ADD EQUIPMENT");

        equipmentTF2.setBackground(new java.awt.Color(255, 255, 255));
        equipmentTF2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        equipmentTF2.setForeground(new java.awt.Color(0, 0, 0));
        equipmentTF2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equipmentTF2ActionPerformed(evt);
            }
        });

        equipment6.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        equipment6.setText("EQUIPMENT ");

        categoryequipment6.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        categoryequipment6.setText("CATEGORY");

        quantityequipmenttF2.setBackground(new java.awt.Color(255, 255, 255));
        quantityequipmenttF2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        quantityequipmenttF2.setForeground(new java.awt.Color(0, 0, 0));
        quantityequipmenttF2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityequipmenttF2ActionPerformed(evt);
            }
        });

        quantityequipment5.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        quantityequipment5.setText(" QUANTITY");

        categoryequipmentCB2.setBackground(new java.awt.Color(255, 255, 255));
        categoryequipmentCB2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        categoryequipmentCB2.setForeground(new java.awt.Color(0, 0, 0));
        categoryequipmentCB2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default", "Chemistry", "Biology", "Earth Science", "Physics" }));
        categoryequipmentCB2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryequipmentCB2ActionPerformed(evt);
            }
        });

        jPanel14.setBackground(new java.awt.Color(118, 44, 235));

        cancelupdateinventory4.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        cancelupdateinventory4.setForeground(new java.awt.Color(255, 255, 255));
        cancelupdateinventory4.setText("CANCEL");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(cancelupdateinventory4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelupdateinventory4)
                .addContainerGap())
        );

        jPanel15.setBackground(new java.awt.Color(118, 44, 235));
        jPanel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel15MouseClicked(evt);
            }
        });

        saveupdateinventory4.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        saveupdateinventory4.setForeground(new java.awt.Color(255, 255, 255));
        saveupdateinventory4.setText("SAVE");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(saveupdateinventory4)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveupdateinventory4)
                .addContainerGap())
        );

        javax.swing.GroupLayout left4Layout = new javax.swing.GroupLayout(left4);
        left4.setLayout(left4Layout);
        left4Layout.setHorizontalGroup(
            left4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(left4Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(left4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(updateinventory4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(equipmentTF2)
                    .addComponent(equipment6)
                    .addComponent(categoryequipment6)
                    .addComponent(quantityequipmenttF2)
                    .addComponent(quantityequipment5)
                    .addComponent(categoryequipmentCB2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, left4Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(36, 36, 36)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        left4Layout.setVerticalGroup(
            left4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(left4Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(updateinventory4)
                .addGap(26, 26, 26)
                .addComponent(equipment6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(equipmentTF2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(categoryequipment6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(categoryequipmentCB2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(quantityequipment5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quantityequipmenttF2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(left4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout AddBorrowingLayout = new javax.swing.GroupLayout(AddBorrowing.getContentPane());
        AddBorrowing.getContentPane().setLayout(AddBorrowingLayout);
        AddBorrowingLayout.setHorizontalGroup(
            AddBorrowingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(left4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        AddBorrowingLayout.setVerticalGroup(
            AddBorrowingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(left4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        UpdateBorrow.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        left5.setBackground(new java.awt.Color(51, 51, 51));
        left5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        updateinventory5.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        updateinventory5.setForeground(new java.awt.Color(255, 255, 255));
        updateinventory5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/inventory.png"))); // NOI18N
        updateinventory5.setText("UPDATE BORROW");
        left5.add(updateinventory5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 338, -1));

        equipmentid3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        equipmentid3.setForeground(new java.awt.Color(255, 255, 255));
        equipmentid3.setText("EQUIPMENT ID");
        left5.add(equipmentid3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 111, -1, -1));

        equipmentidTF1.setEditable(false);
        equipmentidTF1.setBackground(new java.awt.Color(255, 255, 255));
        equipmentidTF1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        equipmentidTF1.setForeground(new java.awt.Color(0, 0, 0));
        equipmentidTF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equipmentidTF1ActionPerformed(evt);
            }
        });
        left5.add(equipmentidTF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 139, 320, 40));

        equipmentTF3.setBackground(new java.awt.Color(255, 255, 255));
        equipmentTF3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        equipmentTF3.setForeground(new java.awt.Color(0, 0, 0));
        equipmentTF3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equipmentTF3ActionPerformed(evt);
            }
        });
        left5.add(equipmentTF3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 231, 320, 40));
        equipmentTF3.setEditable(false);

        equipment7.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        equipment7.setForeground(new java.awt.Color(255, 255, 255));
        equipment7.setText("Borrower");
        left5.add(equipment7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 203, -1, -1));

        categoryequipment7.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        categoryequipment7.setForeground(new java.awt.Color(255, 255, 255));
        categoryequipment7.setText("Status");
        left5.add(categoryequipment7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 393, -1, -1));

        statusTf.setBackground(new java.awt.Color(255, 255, 255));
        statusTf.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        statusTf.setForeground(new java.awt.Color(0, 0, 0));
        statusTf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Borrowed", "Completed", "Lost", "Damaged", " " }));
        statusTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusTfActionPerformed(evt);
            }
        });
        statusTf.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                statusTfPropertyChange(evt);
            }
        });
        left5.add(statusTf, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 421, 120, 40));

        jPanel16.setBackground(new java.awt.Color(118, 44, 235));

        cancelupdateinventory5.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        cancelupdateinventory5.setText("CANCEL");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(cancelupdateinventory5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelupdateinventory5)
                .addContainerGap())
        );

        left5.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 563, 120, -1));

        jPanel17.setBackground(new java.awt.Color(118, 44, 235));
        jPanel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel17MouseClicked(evt);
            }
        });

        saveupdateinventory5.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        saveupdateinventory5.setText("SAVE");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(saveupdateinventory5)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveupdateinventory5)
                .addContainerGap())
        );

        left5.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(263, 563, -1, -1));

        equipment8.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        equipment8.setForeground(new java.awt.Color(255, 255, 255));
        equipment8.setText("Equipment");
        left5.add(equipment8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 289, -1, -1));

        EquiptmentTf.setBackground(new java.awt.Color(255, 255, 255));
        EquiptmentTf.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        EquiptmentTf.setForeground(new java.awt.Color(0, 0, 0));
        EquiptmentTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EquiptmentTfActionPerformed(evt);
            }
        });
        left5.add(EquiptmentTf, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 329, 340, 40));
        EquiptmentTf.setEditable(false);

        lostItemCountField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                lostItemCountFieldPropertyChange(evt);
            }
        });
        left5.add(lostItemCountField, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 510, 60, 30));

        DamagedLabel.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        DamagedLabel.setForeground(new java.awt.Color(255, 255, 255));
        DamagedLabel.setText("Number of Damage Items:");
        left5.add(DamagedLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, -1, -1));

        quantityTf.setBackground(new java.awt.Color(255, 255, 255));
        quantityTf.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        quantityTf.setForeground(new java.awt.Color(0, 0, 0));
        quantityTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityTfActionPerformed(evt);
            }
        });
        left5.add(quantityTf, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 420, 168, 40));
        quantityTf.setEditable(false);

        quantitylbl.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        quantitylbl.setForeground(new java.awt.Color(255, 255, 255));
        quantitylbl.setText("Quantity");
        left5.add(quantitylbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 393, -1, -1));

        lostItemCountLabel.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        lostItemCountLabel.setForeground(new java.awt.Color(255, 255, 255));
        lostItemCountLabel.setText("Number of Lost Items:");
        left5.add(lostItemCountLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, -1, -1));

        UpdateBorrow.getContentPane().add(left5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 654));

        left6.setBackground(new java.awt.Color(51, 51, 51));

        updateinventory6.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        updateinventory6.setForeground(new java.awt.Color(255, 255, 255));
        updateinventory6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/inventory.png"))); // NOI18N
        updateinventory6.setText("ADD MULTIPLE");

        equipment9.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        equipment9.setText("CHOOSE FILE");

        jPanel18.setBackground(new java.awt.Color(118, 44, 235));

        cancelupdateinventory6.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        cancelupdateinventory6.setForeground(new java.awt.Color(255, 255, 255));
        cancelupdateinventory6.setText("CANCEL");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(cancelupdateinventory6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelupdateinventory6)
                .addContainerGap())
        );

        jPanel19.setBackground(new java.awt.Color(118, 44, 235));
        jPanel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel19MouseClicked(evt);
            }
        });

        saveupdateinventory6.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        saveupdateinventory6.setForeground(new java.awt.Color(255, 255, 255));
        saveupdateinventory6.setText("SAVE");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(saveupdateinventory6)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveupdateinventory6)
                .addContainerGap())
        );

        javax.swing.GroupLayout left6Layout = new javax.swing.GroupLayout(left6);
        left6.setLayout(left6Layout);
        left6Layout.setHorizontalGroup(
            left6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(left6Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(left6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(updateinventory6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(equipment9)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, left6Layout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        left6Layout.setVerticalGroup(
            left6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(left6Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(updateinventory6)
                .addGap(26, 26, 26)
                .addComponent(equipment9)
                .addGap(126, 126, 126)
                .addGroup(left6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout AddInventory_multipleLayout = new javax.swing.GroupLayout(AddInventory_multiple.getContentPane());
        AddInventory_multiple.getContentPane().setLayout(AddInventory_multipleLayout);
        AddInventory_multipleLayout.setHorizontalGroup(
            AddInventory_multipleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(left6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        AddInventory_multipleLayout.setVerticalGroup(
            AddInventory_multipleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(left6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        left7.setBackground(new java.awt.Color(51, 51, 51));

        updateinventory7.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        updateinventory7.setForeground(new java.awt.Color(255, 255, 255));
        updateinventory7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/attendance.png"))); // NOI18N
        updateinventory7.setText("Add Admin");

        lastnameadmin.setBackground(new java.awt.Color(255, 255, 255));
        lastnameadmin.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        lastnameadmin.setForeground(new java.awt.Color(0, 0, 0));
        lastnameadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastnameadminActionPerformed(evt);
            }
        });

        equipment10.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        equipment10.setForeground(new java.awt.Color(255, 255, 255));
        equipment10.setText("LASTNAME");

        categoryequipment8.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        categoryequipment8.setForeground(new java.awt.Color(255, 255, 255));
        categoryequipment8.setText("FIRSTNAME");

        quantityequipment6.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        quantityequipment6.setForeground(new java.awt.Color(255, 255, 255));
        quantityequipment6.setText("Confirm Password");

        jPanel20.setBackground(new java.awt.Color(118, 44, 235));

        cancelupdateinventory7.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        cancelupdateinventory7.setForeground(new java.awt.Color(255, 255, 255));
        cancelupdateinventory7.setText("CANCEL");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(cancelupdateinventory7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelupdateinventory7)
                .addContainerGap())
        );

        jPanel21.setBackground(new java.awt.Color(118, 44, 235));
        jPanel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel21MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel21MouseEntered(evt);
            }
        });

        saveupdateinventory7.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        saveupdateinventory7.setForeground(new java.awt.Color(255, 255, 255));
        saveupdateinventory7.setText("SAVE");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(saveupdateinventory7)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(saveupdateinventory7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        equipment11.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        equipment11.setForeground(new java.awt.Color(255, 255, 255));
        equipment11.setText("Password");

        equipmentid5.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        equipmentid5.setForeground(new java.awt.Color(255, 255, 255));
        equipmentid5.setText("EMAIL");

        firstnameAdmin.setBackground(new java.awt.Color(255, 255, 255));
        firstnameAdmin.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        firstnameAdmin.setForeground(new java.awt.Color(0, 0, 0));
        firstnameAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstnameAdminActionPerformed(evt);
            }
        });

        emailtfadmin.setBackground(new java.awt.Color(255, 255, 255));
        emailtfadmin.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        emailtfadmin.setForeground(new java.awt.Color(0, 0, 0));
        emailtfadmin.setText("   ");
        emailtfadmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailtfadminActionPerformed(evt);
            }
        });

        showPasswordCheckbox.setBackground(new java.awt.Color(255, 255, 255));
        showPasswordCheckbox.setForeground(new java.awt.Color(255, 255, 255));
        showPasswordCheckbox.setText("Show paswword");
        showPasswordCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPasswordCheckboxActionPerformed(evt);
            }
        });

        confirmpassword.setBackground(new java.awt.Color(255, 255, 255));
        confirmpassword.setForeground(new java.awt.Color(255, 255, 255));
        confirmpassword.setText("Show password");
        confirmpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmpasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout left7Layout = new javax.swing.GroupLayout(left7);
        left7.setLayout(left7Layout);
        left7Layout.setHorizontalGroup(
            left7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(left7Layout.createSequentialGroup()
                .addGroup(left7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(left7Layout.createSequentialGroup()
                        .addGap(259, 259, 259)
                        .addComponent(updateinventory7))
                    .addGroup(left7Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(categoryequipment8)
                        .addGap(221, 221, 221)
                        .addComponent(equipment10))
                    .addGroup(left7Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(firstnameAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(lastnameadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(left7Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(equipmentid5)
                        .addGap(262, 262, 262)
                        .addComponent(equipment11))
                    .addGroup(left7Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(emailtfadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(passwordAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(left7Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(quantityequipment6)
                        .addGap(172, 172, 172)
                        .addComponent(showPasswordCheckbox))
                    .addGroup(left7Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(ConfirmPAsswordAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(left7Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(confirmpassword))
                    .addGroup(left7Layout.createSequentialGroup()
                        .addGap(330, 330, 330)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        left7Layout.setVerticalGroup(
            left7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(left7Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(updateinventory7)
                .addGap(40, 40, 40)
                .addGroup(left7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(categoryequipment8)
                    .addGroup(left7Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(equipment10)))
                .addGroup(left7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(firstnameAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(left7Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lastnameadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(left7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(equipmentid5)
                    .addGroup(left7Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(equipment11)))
                .addGap(3, 3, 3)
                .addGroup(left7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(emailtfadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(left7Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(passwordAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(left7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(left7Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(quantityequipment6))
                    .addComponent(showPasswordCheckbox))
                .addGap(7, 7, 7)
                .addComponent(ConfirmPAsswordAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(confirmpassword)
                .addGap(27, 27, 27)
                .addGroup(left7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        AddAdmin.getContentPane().add(left7, java.awt.BorderLayout.CENTER);

        left8.setBackground(new java.awt.Color(51, 51, 51));

        equipment12.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        equipment12.setForeground(new java.awt.Color(255, 255, 255));
        equipment12.setText("Old Password");

        categoryequipment9.setBackground(new java.awt.Color(255, 255, 255));
        categoryequipment9.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        categoryequipment9.setForeground(new java.awt.Color(255, 255, 255));
        categoryequipment9.setText("Password");

        quantityequipment7.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        quantityequipment7.setForeground(new java.awt.Color(255, 255, 255));
        quantityequipment7.setText("Confirm password");

        jPanel22.setBackground(new java.awt.Color(118, 44, 235));

        cancelupdateinventory8.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        cancelupdateinventory8.setForeground(new java.awt.Color(255, 255, 255));
        cancelupdateinventory8.setText("CANCEL");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(cancelupdateinventory8, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelupdateinventory8)
                .addContainerGap())
        );

        jPanel23.setBackground(new java.awt.Color(118, 44, 235));
        jPanel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel23MouseClicked(evt);
            }
        });

        saveupdateinventory8.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        saveupdateinventory8.setForeground(new java.awt.Color(255, 255, 255));
        saveupdateinventory8.setText("SAVE");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(saveupdateinventory8)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveupdateinventory8)
                .addContainerGap())
        );

        emailLbl.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        emailLbl.setText("CHANGE PASSWORD");

        NewPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewPasswordActionPerformed(evt);
            }
        });

        equipment13.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        equipment13.setForeground(new java.awt.Color(255, 255, 255));
        equipment13.setText("email");

        AdminEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminEmailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout left8Layout = new javax.swing.GroupLayout(left8);
        left8.setLayout(left8Layout);
        left8Layout.setHorizontalGroup(
            left8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(left8Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(left8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(equipment13)
                    .addGroup(left8Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(left8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(NewPassword)
                            .addComponent(equipment12)
                            .addComponent(categoryequipment9)
                            .addComponent(quantityequipment7)
                            .addGroup(left8Layout.createSequentialGroup()
                                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Confirmpassword)
                            .addComponent(oldPaswword)
                            .addComponent(AdminEmail))))
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, left8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(emailLbl)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        left8Layout.setVerticalGroup(
            left8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(left8Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(emailLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(equipment13)
                .addGap(18, 18, 18)
                .addComponent(AdminEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(equipment12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(oldPaswword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(categoryequipment9)
                .addGap(18, 18, 18)
                .addComponent(NewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(quantityequipment7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Confirmpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(left8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        AdminEmail.setEditable(false);

        javax.swing.GroupLayout ChangePasswordAdminLayout = new javax.swing.GroupLayout(ChangePasswordAdmin.getContentPane());
        ChangePasswordAdmin.getContentPane().setLayout(ChangePasswordAdminLayout);
        ChangePasswordAdminLayout.setHorizontalGroup(
            ChangePasswordAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(left8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ChangePasswordAdminLayout.setVerticalGroup(
            ChangePasswordAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(left8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        scilab.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        scilab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Scilab.png"))); // NOI18N
        scilab.setText("SCIENCE LAB");

        dashboardbtn.setOpaque(false);
        dashboardbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboardbtnMouseClicked(evt);
            }
        });

        dashboard.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dashboard.png"))); // NOI18N
        dashboard.setText("Dashboard");
        dashboard.setIconTextGap(20);

        javax.swing.GroupLayout dashboardbtnLayout = new javax.swing.GroupLayout(dashboardbtn);
        dashboardbtn.setLayout(dashboardbtnLayout);
        dashboardbtnLayout.setHorizontalGroup(
            dashboardbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardbtnLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(dashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        dashboardbtnLayout.setVerticalGroup(
            dashboardbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dashboardbtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        attendancebtn.setOpaque(false);
        attendancebtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                attendancebtnMouseClicked(evt);
            }
        });

        attendance.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        attendance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/attendance.png"))); // NOI18N
        attendance.setText("Attendance");
        attendance.setIconTextGap(20);

        javax.swing.GroupLayout attendancebtnLayout = new javax.swing.GroupLayout(attendancebtn);
        attendancebtn.setLayout(attendancebtnLayout);
        attendancebtnLayout.setHorizontalGroup(
            attendancebtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(attendancebtnLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(attendance, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addContainerGap())
        );
        attendancebtnLayout.setVerticalGroup(
            attendancebtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(attendancebtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(attendance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        borrowingbtn.setOpaque(false);
        borrowingbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                borrowingbtnMouseClicked(evt);
            }
        });

        borrowing.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        borrowing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/borrowing.png"))); // NOI18N
        borrowing.setText("Borrowing");
        borrowing.setIconTextGap(20);

        javax.swing.GroupLayout borrowingbtnLayout = new javax.swing.GroupLayout(borrowingbtn);
        borrowingbtn.setLayout(borrowingbtnLayout);
        borrowingbtnLayout.setHorizontalGroup(
            borrowingbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borrowingbtnLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(borrowing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        borrowingbtnLayout.setVerticalGroup(
            borrowingbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, borrowingbtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(borrowing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        inventorybtn.setOpaque(false);
        inventorybtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inventorybtnMouseClicked(evt);
            }
        });

        inventory.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        inventory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/inventory.png"))); // NOI18N
        inventory.setText("Inventory");
        inventory.setIconTextGap(20);

        javax.swing.GroupLayout inventorybtnLayout = new javax.swing.GroupLayout(inventorybtn);
        inventorybtn.setLayout(inventorybtnLayout);
        inventorybtnLayout.setHorizontalGroup(
            inventorybtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventorybtnLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(inventory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        inventorybtnLayout.setVerticalGroup(
            inventorybtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inventorybtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inventory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        logoutbtn.setOpaque(false);
        logoutbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutbtnMouseClicked(evt);
            }
        });

        logout.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        logout.setText("Logout");
        logout.setIconTextGap(20);

        javax.swing.GroupLayout logoutbtnLayout = new javax.swing.GroupLayout(logoutbtn);
        logoutbtn.setLayout(logoutbtnLayout);
        logoutbtnLayout.setHorizontalGroup(
            logoutbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logoutbtnLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        logoutbtnLayout.setVerticalGroup(
            logoutbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logoutbtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        studentsbtn.setOpaque(false);
        studentsbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentsbtnMouseClicked(evt);
            }
        });

        students.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        students.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/borrowing.png"))); // NOI18N
        students.setText("Students");
        students.setIconTextGap(20);

        javax.swing.GroupLayout studentsbtnLayout = new javax.swing.GroupLayout(studentsbtn);
        studentsbtn.setLayout(studentsbtnLayout);
        studentsbtnLayout.setHorizontalGroup(
            studentsbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentsbtnLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(students, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        studentsbtnLayout.setVerticalGroup(
            studentsbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, studentsbtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(students, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scilab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dashboardbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(attendancebtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(borrowingbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inventorybtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logoutbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(studentsbtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(scilab)
                .addGap(18, 18, 18)
                .addComponent(dashboardbtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(attendancebtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(borrowingbtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(inventorybtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(studentsbtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(logoutbtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        overlaypanel.setBackground(new java.awt.Color(0, 0, 0));
        overlaypanel.setMaximumSize(new java.awt.Dimension(1098, 750));
        overlaypanel.setLayout(new java.awt.CardLayout());

        borrowingpanel.setBackground(new java.awt.Color(255, 255, 255));
        borrowingpanel.setPreferredSize(new java.awt.Dimension(947, 672));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        searchtf1.setBackground(new java.awt.Color(204, 204, 204));
        searchtf1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        searchtf1.setForeground(new java.awt.Color(51, 51, 51));
        searchtf1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchtf1ActionPerformed(evt);
            }
        });
        jPanel3.add(searchtf1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 199, 48));

        jLabel10.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Search :");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 30, 78, -1));

        jButton3.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jButton3.setText("Download");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 590, -1, 40));

        dateChooser1.setBackground(new java.awt.Color(255, 255, 255));
        dateChooser1.setForeground(new java.awt.Color(0, 0, 0));
        dateChooser1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jPanel3.add(dateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 12, 163, 48));

        ShowAllbtn3.setBackground(new java.awt.Color(204, 204, 204));
        ShowAllbtn3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ShowAllbtn3MouseClicked(evt);
            }
        });

        showAll3.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        showAll3.setForeground(new java.awt.Color(0, 0, 0));
        showAll3.setText("List of All Borrowing");

        javax.swing.GroupLayout ShowAllbtn3Layout = new javax.swing.GroupLayout(ShowAllbtn3);
        ShowAllbtn3.setLayout(ShowAllbtn3Layout);
        ShowAllbtn3Layout.setHorizontalGroup(
            ShowAllbtn3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(showAll3, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
        );
        ShowAllbtn3Layout.setVerticalGroup(
            ShowAllbtn3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ShowAllbtn3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(showAll3))
        );

        jPanel3.add(ShowAllbtn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 12, -1, 48));

        BorrowingTable.setAutoCreateRowSorter(true);
        BorrowingTable.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        BorrowingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Borrow ID", "Lastname", "Equipment", "quantity", "Borrow Date", "Return Date", "status", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        BorrowingTable.setRowHeight(35);
        BorrowingTable.setSelectionBackground(new java.awt.Color(99, 11, 213));
        BorrowingTable.setShowGrid(false);
        BorrowingTable.setSurrendersFocusOnKeystroke(true);
        jScrollPane8.setViewportView(BorrowingTable);
        if (BorrowingTable.getColumnModel().getColumnCount() > 0) {
            BorrowingTable.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel3.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 910, 490));

        jLabel11.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(27, 27, 27));
        jLabel11.setText("borrowing Page");

        javax.swing.GroupLayout borrowingpanelLayout = new javax.swing.GroupLayout(borrowingpanel);
        borrowingpanel.setLayout(borrowingpanelLayout);
        borrowingpanelLayout.setHorizontalGroup(
            borrowingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, borrowingpanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(borrowingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 936, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        borrowingpanelLayout.setVerticalGroup(
            borrowingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, borrowingpanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        overlaypanel.add(borrowingpanel, "card4");

        attendancepanel.setBackground(new java.awt.Color(255, 255, 255));
        attendancepanel.setPreferredSize(new java.awt.Dimension(947, 672));

        jLabel4.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(27, 27, 27));
        jLabel4.setText("Attendance Page");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(956, 548));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        searchtf.setBackground(new java.awt.Color(204, 204, 204));
        searchtf.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        searchtf.setForeground(new java.awt.Color(51, 51, 51));
        searchtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchtfActionPerformed(evt);
            }
        });
        jPanel1.add(searchtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, 200, 40));

        jLabel1.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Search :");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 78, -1));

        jButton1.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jButton1.setText("Download");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 570, -1, 40));

        dateChooser.setBackground(new java.awt.Color(255, 255, 255));
        dateChooser.setForeground(new java.awt.Color(0, 0, 0));
        dateChooser.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jPanel1.add(dateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 12, 159, 40));

        ShowAllbtn.setBackground(new java.awt.Color(204, 204, 204));
        ShowAllbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ShowAllbtnMouseClicked(evt);
            }
        });

        showAll.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        showAll.setForeground(new java.awt.Color(0, 0, 0));
        showAll.setText("List of All Attendance");

        javax.swing.GroupLayout ShowAllbtnLayout = new javax.swing.GroupLayout(ShowAllbtn);
        ShowAllbtn.setLayout(ShowAllbtnLayout);
        ShowAllbtnLayout.setHorizontalGroup(
            ShowAllbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(showAll, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
        );
        ShowAllbtnLayout.setVerticalGroup(
            ShowAllbtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShowAllbtnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(showAll, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(ShowAllbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 12, 190, 40));

        AttendanceTable.setAutoCreateRowSorter(true);
        AttendanceTable.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        AttendanceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "lastname", "firstname", "attendance_date", "attendace_time"
            }
        ));
        AttendanceTable.setRowHeight(35);
        AttendanceTable.setSelectionBackground(new java.awt.Color(99, 11, 213));
        AttendanceTable.setShowGrid(false);
        AttendanceTable.setSurrendersFocusOnKeystroke(true);
        jScrollPane7.setViewportView(AttendanceTable);
        if (AttendanceTable.getColumnModel().getColumnCount() > 0) {
            AttendanceTable.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel1.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 900, 480));

        javax.swing.GroupLayout attendancepanelLayout = new javax.swing.GroupLayout(attendancepanel);
        attendancepanel.setLayout(attendancepanelLayout);
        attendancepanelLayout.setHorizontalGroup(
            attendancepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, attendancepanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(attendancepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        attendancepanelLayout.setVerticalGroup(
            attendancepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(attendancepanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        overlaypanel.add(attendancepanel, "card5");

        inventorypanel.setBackground(new java.awt.Color(255, 255, 255));
        inventorypanel.setPreferredSize(new java.awt.Dimension(947, 672));

        jLabel6.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(27, 27, 27));
        jLabel6.setText("Inventory Page");

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inventorysearchtf.setBackground(new java.awt.Color(204, 204, 204));
        inventorysearchtf.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        inventorysearchtf.setForeground(new java.awt.Color(51, 51, 51));
        inventorysearchtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventorysearchtfActionPerformed(evt);
            }
        });
        jPanel4.add(inventorysearchtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 20, 161, 39));

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Search :");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 78, -1));

        ShowAllbtn1.setBackground(new java.awt.Color(204, 204, 204));
        ShowAllbtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ShowAllbtn1MouseClicked(evt);
            }
        });

        showAll1.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        showAll1.setForeground(new java.awt.Color(0, 0, 0));
        showAll1.setText("List of All Inventory");

        javax.swing.GroupLayout ShowAllbtn1Layout = new javax.swing.GroupLayout(ShowAllbtn1);
        ShowAllbtn1.setLayout(ShowAllbtn1Layout);
        ShowAllbtn1Layout.setHorizontalGroup(
            ShowAllbtn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ShowAllbtn1Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(showAll1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        ShowAllbtn1Layout.setVerticalGroup(
            ShowAllbtn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ShowAllbtn1Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(showAll1)
                .addContainerGap())
        );

        jPanel4.add(ShowAllbtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, -1));

        AddBtn.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        AddBtn.setForeground(new java.awt.Color(0, 0, 0));
        AddBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        AddBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddBtnMouseClicked(evt);
            }
        });
        jPanel4.add(AddBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, -1, 39));

        jButton4.setText("Download");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 580, 105, 39));

        jButton5.setText("Print Qr");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 580, 96, 39));

        InventoryTable.setAutoCreateRowSorter(true);
        InventoryTable.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        InventoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Equipment", "Category", "quantity", "Available", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        InventoryTable.setRowHeight(35);
        InventoryTable.setSelectionBackground(new java.awt.Color(99, 11, 213));
        InventoryTable.setShowGrid(false);
        InventoryTable.setSurrendersFocusOnKeystroke(true);
        jScrollPane9.setViewportView(InventoryTable);
        if (InventoryTable.getColumnModel().getColumnCount() > 0) {
            InventoryTable.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel4.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 910, 490));

        javax.swing.GroupLayout inventorypanelLayout = new javax.swing.GroupLayout(inventorypanel);
        inventorypanel.setLayout(inventorypanelLayout);
        inventorypanelLayout.setHorizontalGroup(
            inventorypanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inventorypanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(inventorypanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, inventorypanelLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        inventorypanelLayout.setVerticalGroup(
            inventorypanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventorypanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        overlaypanel.add(inventorypanel, "card6");

        studentspanel.setBackground(new java.awt.Color(255, 255, 255));
        studentspanel.setPreferredSize(new java.awt.Dimension(947, 746));

        jLabel5.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(27, 27, 27));
        jLabel5.setText("Students Account");

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setPreferredSize(new java.awt.Dimension(956, 548));
        jPanel5.setVerifyInputWhenFocusTarget(false);
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        StudentsTable.setAutoCreateRowSorter(true);
        StudentsTable.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        StudentsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Lastname", "Firstname", "Lrn", "Email", "Grade", "Section", "Status", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        StudentsTable.setRowHeight(35);
        StudentsTable.setSelectionBackground(new java.awt.Color(99, 11, 213));
        StudentsTable.setShowGrid(false);
        StudentsTable.setSurrendersFocusOnKeystroke(true);
        jScrollPane5.setViewportView(StudentsTable);
        if (StudentsTable.getColumnModel().getColumnCount() > 0) {
            StudentsTable.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel5.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 910, 490));

        studentssearchtf.setBackground(new java.awt.Color(204, 204, 204));
        studentssearchtf.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        studentssearchtf.setForeground(new java.awt.Color(51, 51, 51));
        studentssearchtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentssearchtfActionPerformed(evt);
            }
        });
        studentssearchtf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                studentssearchtfKeyTyped(evt);
            }
        });
        jPanel5.add(studentssearchtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 200, 40));

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Search :");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, 78, -1));

        ShowAllbtn2.setBackground(new java.awt.Color(204, 204, 204));
        ShowAllbtn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ShowAllbtn2MouseClicked(evt);
            }
        });

        showAll2.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        showAll2.setForeground(new java.awt.Color(0, 0, 0));
        showAll2.setText("List of All Students");

        javax.swing.GroupLayout ShowAllbtn2Layout = new javax.swing.GroupLayout(ShowAllbtn2);
        ShowAllbtn2.setLayout(ShowAllbtn2Layout);
        ShowAllbtn2Layout.setHorizontalGroup(
            ShowAllbtn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(showAll2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        ShowAllbtn2Layout.setVerticalGroup(
            ShowAllbtn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShowAllbtn2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(showAll2)
                .addContainerGap(527, Short.MAX_VALUE))
        );

        jPanel5.add(ShowAllbtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 10, -1, -1));

        jButton2.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jButton2.setText("Download");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 580, -1, 40));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 10, -1, 45));

        jButton6.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jButton6.setText("Print Qr ");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(701, 580, 100, 40));

        javax.swing.GroupLayout studentspanelLayout = new javax.swing.GroupLayout(studentspanel);
        studentspanel.setLayout(studentspanelLayout);
        studentspanelLayout.setHorizontalGroup(
            studentspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentspanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(studentspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(studentspanelLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(studentspanelLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
                        .addGap(30, 30, 30))))
        );
        studentspanelLayout.setVerticalGroup(
            studentspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentspanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        overlaypanel.add(studentspanel, "card2");

        dashboardpanel.setBackground(new java.awt.Color(255, 255, 255));
        dashboardpanel.setPreferredSize(new java.awt.Dimension(947, 672));

        jLabel2.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(27, 27, 27));
        jLabel2.setText("Dashboard");

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(27, 27, 27));
        jLabel3.setText("System Record: ");

        notiicationTable.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        notiicationTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Notifications"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        notiicationTable.setRowHeight(35);
        jScrollPane1.setViewportView(notiicationTable);

        AddAdminbtn.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        AddAdminbtn.setForeground(new java.awt.Color(0, 0, 0));
        AddAdminbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        AddAdminbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddAdminbtnMouseClicked(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/settings.png"))); // NOI18N
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout dashboardpanelLayout = new javax.swing.GroupLayout(dashboardpanel);
        dashboardpanel.setLayout(dashboardpanelLayout);
        dashboardpanelLayout.setHorizontalGroup(
            dashboardpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardpanelLayout.createSequentialGroup()
                .addGroup(dashboardpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dashboardpanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dashboardpanelLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dashboardpanelLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(dashboardpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dashboardpanelLayout.createSequentialGroup()
                                .addComponent(AddAdminbtn)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cardForm1, javax.swing.GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addGap(35, 35, 35))
        );
        dashboardpanelLayout.setVerticalGroup(
            dashboardpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dashboardpanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cardForm1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dashboardpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(AddAdminbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        overlaypanel.add(dashboardpanel, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(overlaypanel, javax.swing.GroupLayout.DEFAULT_SIZE, 992, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(overlaypanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    MouseListener showAllExitedListener = new MouseAdapter() {
        @Override
        public void mouseExited(MouseEvent e) {
            showAll.setForeground(Color.BLACK);
        }
    };

    MouseListener showAllEnteredListener = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            showAll.setForeground(Color.BLUE);
        }
    };

    MouseListener dashboardmouseExitedListener = new MouseAdapter() {
        @Override
        public void mouseExited(MouseEvent e) {
            dashboard.setForeground(Color.WHITE);
            dashboard.setFont(dashboard.getFont().deriveFont(14f));
        }
    };

    MouseListener inventorymouseExitedListener = new MouseAdapter() {
        @Override
        public void mouseExited(MouseEvent e) {
            inventory.setForeground(Color.WHITE);
            inventory.setFont(attendance.getFont().deriveFont(14f));

        }
    };

    MouseListener attendancemouseExitedListener = new MouseAdapter() {
        @Override
        public void mouseExited(MouseEvent e) {
            attendance.setForeground(Color.WHITE);
            attendance.setFont(attendance.getFont().deriveFont(14f));
        }
    };

    MouseListener borrowingmouseExitedListener = new MouseAdapter() {
        @Override
        public void mouseExited(MouseEvent e) {
            borrowing.setForeground(Color.WHITE);
            borrowing.setFont(borrowing.getFont().deriveFont(14f));
        }
    };

    MouseListener studentsmouseExitedListener = new MouseAdapter() {
        @Override
        public void mouseExited(MouseEvent e) {
            students.setForeground(Color.WHITE);
            students.setFont(students.getFont().deriveFont(14f));
            // Your code here
        }
    };

    MouseAdapter studentsmouseEnteredListener = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            students.setForeground(Color.decode("#F0A8D0"));
            students.setFont(students.getFont().deriveFont(16f));
        }
    };
    MouseListener logoutmouseExitedListener = new MouseAdapter() {
        @Override
        public void mouseExited(MouseEvent e) {
            logout.setForeground(Color.WHITE);
            logout.setFont(logout.getFont().deriveFont(14f));
            // Your code here
        }
    };

    MouseAdapter dashboardmouseEnteredListener = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            dashboard.setForeground(Color.decode("#F0A8D0"));
            dashboard.setFont(dashboard.getFont().deriveFont(16f));
        }
    };

    MouseAdapter attendancemouseEnteredListener = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            attendance.setForeground(Color.decode("#F0A8D0"));
            attendance.setFont(dashboard.getFont().deriveFont(16f));
        }
    };

    MouseAdapter inventorymouseEnteredListener = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            inventory.setForeground(Color.decode("#F0A8D0"));
            inventory.setFont(dashboard.getFont().deriveFont(16f));
        }
    };

    MouseAdapter borrowingmouseEnteredListener = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            borrowing.setForeground(Color.decode("#F0A8D0"));
            borrowing.setFont(dashboard.getFont().deriveFont(16f));
        }
    };
    MouseAdapter logoutmouseEnteredListener = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            logout.setForeground(Color.RED);
            logout.setFont(dashboard.getFont().deriveFont(16f));
        }
    };


    private void dashboardbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardbtnMouseClicked
        dashboardbtn.removeMouseListener(dashboardmouseEnteredListener);
        attendancebtn.addMouseListener(attendancemouseEnteredListener);
        inventorybtn.addMouseListener(inventorymouseEnteredListener);
        borrowingbtn.addMouseListener(borrowingmouseEnteredListener);
        logoutbtn.addMouseListener(logoutmouseEnteredListener);
        studentsbtn.addMouseListener(studentsmouseEnteredListener);

        dashboardbtn.removeMouseListener(dashboardmouseExitedListener);
        inventorybtn.addMouseListener(inventorymouseExitedListener);
        attendancebtn.addMouseListener(attendancemouseExitedListener);
        borrowingbtn.addMouseListener(borrowingmouseExitedListener);
        logoutbtn.addMouseListener(logoutmouseExitedListener);
        studentsbtn.addMouseListener(studentsmouseExitedListener);

        dashboard.setForeground(Color.BLACK);
        dashboard.setFont(dashboard.getFont().deriveFont(16f));

        dashboardbtn.setOpaque(true);
        dashboardbtn.setBackground(new Color(204, 204, 255));

        attendance.setForeground(Color.WHITE);
        inventory.setForeground(Color.WHITE);
        logout.setForeground(Color.WHITE);
        borrowing.setForeground(Color.WHITE);
        students.setForeground(Color.WHITE);

        attendancebtn.setOpaque(false);
        attendancebtn.setBackground(new Color(0, 0, 0, 0));
        inventorybtn.setOpaque(false);
        inventorybtn.setBackground(new Color(0, 0, 0, 0));
        borrowingbtn.setOpaque(false);
        borrowingbtn.setBackground(new Color(0, 0, 0, 0));
        logoutbtn.setOpaque(false);
        logoutbtn.setBackground(new Color(0, 0, 0, 0));
        studentsbtn.setOpaque(false);
        studentsbtn.setBackground(new Color(0, 0, 0, 0));

        // Set the visibility of the panels
        dashboardpanel.setVisible(true);
        attendancepanel.setVisible(false);
        inventorypanel.setVisible(false);
        borrowingpanel.setVisible(false);
        studentspanel.setVisible(false);
    }//GEN-LAST:event_dashboardbtnMouseClicked

    private void attendancebtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_attendancebtnMouseClicked

        dashboardbtn.addMouseListener(dashboardmouseEnteredListener);
        attendancebtn.removeMouseListener(attendancemouseEnteredListener);
        inventorybtn.addMouseListener(inventorymouseEnteredListener);
        borrowingbtn.addMouseListener(borrowingmouseEnteredListener);
        logoutbtn.addMouseListener(logoutmouseEnteredListener);
        studentsbtn.addMouseListener(studentsmouseEnteredListener);

        dashboardbtn.addMouseListener(dashboardmouseExitedListener);
        inventorybtn.addMouseListener(inventorymouseExitedListener);
        attendancebtn.removeMouseListener(attendancemouseExitedListener);
        borrowingbtn.addMouseListener(borrowingmouseExitedListener);
        logoutbtn.addMouseListener(logoutmouseExitedListener);
        studentsbtn.addMouseListener(studentsmouseExitedListener);

        attendance.setForeground(Color.BLACK);
        attendance.setFont(attendance.getFont().deriveFont(16f));

        attendancebtn.setOpaque(true);
        attendancebtn.setBackground(new Color(204, 204, 255));

        dashboard.setForeground(Color.WHITE);
        inventory.setForeground(Color.WHITE);
        logout.setForeground(Color.WHITE);
        borrowing.setForeground(Color.WHITE);
        students.setForeground(Color.WHITE);

        dashboardbtn.setOpaque(false);
        dashboardbtn.setBackground(new Color(0, 0, 0, 0));
        inventorybtn.setOpaque(false);
        inventorybtn.setBackground(new Color(0, 0, 0, 0));
        borrowingbtn.setOpaque(false);
        borrowingbtn.setBackground(new Color(0, 0, 0, 0));
        logoutbtn.setOpaque(false);
        logoutbtn.setBackground(new Color(0, 0, 0, 0));
        studentsbtn.setOpaque(false);
        studentsbtn.setBackground(new Color(0, 0, 0, 0));

        // Set the visibility of the panels
        dashboardpanel.setVisible(false);
        attendancepanel.setVisible(true);
        inventorypanel.setVisible(false);
        borrowingpanel.setVisible(false);
        studentspanel.setVisible(false);


    }//GEN-LAST:event_attendancebtnMouseClicked

    private void borrowingbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_borrowingbtnMouseClicked
        dashboardbtn.addMouseListener(dashboardmouseEnteredListener);
        attendancebtn.addMouseListener(attendancemouseEnteredListener);
        inventorybtn.addMouseListener(inventorymouseEnteredListener);
        borrowingbtn.removeMouseListener(borrowingmouseEnteredListener);
        logoutbtn.addMouseListener(logoutmouseEnteredListener);
        studentsbtn.addMouseListener(studentsmouseEnteredListener);

        dashboardbtn.addMouseListener(dashboardmouseExitedListener);
        inventorybtn.addMouseListener(inventorymouseExitedListener);
        attendancebtn.addMouseListener(attendancemouseExitedListener);
        borrowingbtn.removeMouseListener(borrowingmouseExitedListener);
        logoutbtn.addMouseListener(logoutmouseExitedListener);
        studentsbtn.addMouseListener(studentsmouseExitedListener);

        borrowing.setForeground(Color.BLACK);
        borrowing.setFont(borrowing.getFont().deriveFont(16f));

        borrowingbtn.setOpaque(true);
        borrowingbtn.setBackground(new Color(204, 204, 255));

        dashboard.setForeground(Color.WHITE);
        inventory.setForeground(Color.WHITE);
        logout.setForeground(Color.WHITE);
        attendance.setForeground(Color.WHITE);
        students.setForeground(Color.WHITE);

        dashboardbtn.setOpaque(false);
        dashboardbtn.setBackground(new Color(0, 0, 0, 0));
        inventorybtn.setOpaque(false);
        inventorybtn.setBackground(new Color(0, 0, 0, 0));
        attendancebtn.setOpaque(false);
        attendancebtn.setBackground(new Color(0, 0, 0, 0));
        logoutbtn.setOpaque(false);
        logoutbtn.setBackground(new Color(0, 0, 0, 0));
        studentsbtn.setOpaque(false);
        studentsbtn.setBackground(new Color(0, 0, 0, 0));

        borrowingpanel.setVisible(true);
        attendancepanel.setVisible(false);
        dashboardpanel.setVisible(false);
        inventorypanel.setVisible(false);
        studentspanel.setVisible(false);


    }//GEN-LAST:event_borrowingbtnMouseClicked

    private void inventorybtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventorybtnMouseClicked
        dashboardbtn.addMouseListener(dashboardmouseEnteredListener);
        attendancebtn.addMouseListener(attendancemouseEnteredListener);
        inventorybtn.removeMouseListener(inventorymouseEnteredListener);
        borrowingbtn.addMouseListener(borrowingmouseEnteredListener);
        logoutbtn.addMouseListener(logoutmouseEnteredListener);
        studentsbtn.addMouseListener(studentsmouseEnteredListener);

        dashboardbtn.addMouseListener(dashboardmouseExitedListener);
        inventorybtn.removeMouseListener(inventorymouseExitedListener);
        attendancebtn.addMouseListener(attendancemouseExitedListener);
        borrowingbtn.addMouseListener(borrowingmouseExitedListener);
        logoutbtn.addMouseListener(logoutmouseExitedListener);
        studentsbtn.addMouseListener(studentsmouseExitedListener);

        inventory.setForeground(Color.BLACK);
        inventory.setFont(inventory.getFont().deriveFont(16f));

        inventorybtn.setOpaque(true);
        inventorybtn.setBackground(new Color(204, 204, 255));

        dashboard.setForeground(Color.WHITE);
        borrowing.setForeground(Color.WHITE);
        logout.setForeground(Color.WHITE);
        attendance.setForeground(Color.WHITE);
        students.setForeground(Color.WHITE);

        dashboardbtn.setOpaque(false);
        dashboardbtn.setBackground(new Color(0, 0, 0, 0));
        borrowingbtn.setOpaque(false);
        borrowingbtn.setBackground(new Color(0, 0, 0, 0));
        attendancebtn.setOpaque(false);
        attendancebtn.setBackground(new Color(0, 0, 0, 0));
        logoutbtn.setOpaque(false);
        logoutbtn.setBackground(new Color(0, 0, 0, 0));
        studentsbtn.setOpaque(false);
        studentsbtn.setBackground(new Color(0, 0, 0, 0));

        dashboardpanel.setVisible(false);
        attendancepanel.setVisible(false);
        inventorypanel.setVisible(true);
        borrowingpanel.setVisible(false);
        studentspanel.setVisible(false);
    }//GEN-LAST:event_inventorybtnMouseClicked

    private void logoutbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutbtnMouseClicked
        dashboardbtn.addMouseListener(dashboardmouseEnteredListener);
        attendancebtn.addMouseListener(attendancemouseEnteredListener);
        inventorybtn.addMouseListener(inventorymouseEnteredListener);
        borrowingbtn.addMouseListener(borrowingmouseEnteredListener);
        logoutbtn.removeMouseListener(logoutmouseEnteredListener);
        studentsbtn.addMouseListener(studentsmouseEnteredListener);

        dashboardbtn.addMouseListener(dashboardmouseExitedListener);
        inventorybtn.addMouseListener(inventorymouseExitedListener);
        attendancebtn.addMouseListener(attendancemouseExitedListener);
        borrowingbtn.addMouseListener(borrowingmouseExitedListener);
        logoutbtn.removeMouseListener(logoutmouseExitedListener);
        studentsbtn.addMouseListener(studentsmouseExitedListener);

        logout.setForeground(Color.BLACK);
        logout.setFont(logout.getFont().deriveFont(16f));

        logoutbtn.setOpaque(true);
        logoutbtn.setBackground(new Color(204, 204, 255));

        dashboard.setForeground(Color.WHITE);
        borrowing.setForeground(Color.WHITE);
        inventory.setForeground(Color.WHITE);
        attendance.setForeground(Color.WHITE);
        students.setForeground(Color.WHITE);

        dashboardbtn.setOpaque(false);
        dashboardbtn.setBackground(new Color(0, 0, 0, 0));
        borrowingbtn.setOpaque(false);
        borrowingbtn.setBackground(new Color(0, 0, 0, 0));
        attendancebtn.setOpaque(false);
        attendancebtn.setBackground(new Color(0, 0, 0, 0));
        inventorybtn.setOpaque(false);
        inventorybtn.setBackground(new Color(0, 0, 0, 0));
        studentsbtn.setOpaque(false);
        studentsbtn.setBackground(new Color(0, 0, 0, 0));
        // TODO add your handling code here:

        dispose();
        Frontpage page = new Frontpage();
        page.setVisible(true);
    }//GEN-LAST:event_logoutbtnMouseClicked

    private void searchtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchtfActionPerformed

    private void ShowAllbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShowAllbtnMouseClicked
        // Populate the JTable with attendance data
        AttendancetblHandler.AttendanceShowTable(AttendanceTable);
    }//GEN-LAST:event_ShowAllbtnMouseClicked

    private void studentsbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentsbtnMouseClicked
        dashboardbtn.addMouseListener(dashboardmouseEnteredListener);
        attendancebtn.addMouseListener(attendancemouseEnteredListener);
        inventorybtn.addMouseListener(inventorymouseEnteredListener);
        borrowingbtn.addMouseListener(borrowingmouseEnteredListener);
        logoutbtn.addMouseListener(logoutmouseEnteredListener);
        studentsbtn.removeMouseListener(studentsmouseEnteredListener);

        dashboardbtn.addMouseListener(dashboardmouseExitedListener);
        inventorybtn.addMouseListener(inventorymouseExitedListener);
        attendancebtn.addMouseListener(attendancemouseExitedListener);
        borrowingbtn.addMouseListener(borrowingmouseExitedListener);
        logoutbtn.addMouseListener(logoutmouseExitedListener);
        studentsbtn.removeMouseListener(studentsmouseExitedListener);

        // Set the appearance of the students button
        students.setForeground(Color.BLACK);
        students.setFont(students.getFont().deriveFont(16f));

        studentsbtn.setOpaque(true);
        studentsbtn.setBackground(new Color(204, 204, 255));  // Highlight the clicked button

        // Reset the appearance of the other buttons
        dashboard.setForeground(Color.WHITE);
        borrowing.setForeground(Color.WHITE);
        logout.setForeground(Color.WHITE);
        attendance.setForeground(Color.WHITE);
        inventory.setForeground(Color.WHITE);

        dashboardbtn.setOpaque(false);
        dashboardbtn.setBackground(new Color(0, 0, 0, 0));
        inventorybtn.setOpaque(false);
        inventorybtn.setBackground(new Color(0, 0, 0, 0));
        borrowingbtn.setOpaque(false);
        borrowingbtn.setBackground(new Color(0, 0, 0, 0));
        attendancebtn.setOpaque(false);
        attendancebtn.setBackground(new Color(0, 0, 0, 0));
        logoutbtn.setOpaque(false);
        logoutbtn.setBackground(new Color(0, 0, 0, 0));

        // Set the visibility of the panels
        dashboardpanel.setVisible(false);
        attendancepanel.setVisible(false);
        inventorypanel.setVisible(false);
        borrowingpanel.setVisible(false);
        studentspanel.setVisible(true);
    }//GEN-LAST:event_studentsbtnMouseClicked

    private void equipmentidTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equipmentidTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_equipmentidTFActionPerformed

    private void equipmentTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equipmentTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_equipmentTFActionPerformed

    private void quantityequipmenttFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityequipmenttFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityequipmenttFActionPerformed

    private void categoryequipmentCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryequipmentCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoryequipmentCBActionPerformed

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        String equipmentidstr = equipmentidTF.getText();
        int equipmentid = Integer.parseInt(equipmentidstr);
        String equipmentname = equipmentTF.getText();
        String equipmentquantitystr = quantityequipmenttF.getText();
        int equipmentquantity = Integer.parseInt(equipmentquantitystr);
        String equipmentcategory = (String) categoryequipmentCB.getSelectedItem();

        InventorytblHandler.updateInventoryTable(equipmentid, equipmentname, equipmentquantity, equipmentcategory);
        InventorytblHandler.InventoryShowTable(InventoryTable);
         UpdateInventory.setVisible(false); 
    }//GEN-LAST:event_jPanel7MouseClicked

    private void inventorysearchtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventorysearchtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inventorysearchtfActionPerformed

    private void ShowAllbtn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShowAllbtn1MouseClicked
        // TODO add your handling code here:
        InventorytblHandler.InventoryShowTable(InventoryTable);
    }//GEN-LAST:event_ShowAllbtn1MouseClicked

    private void studentssearchtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentssearchtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studentssearchtfActionPerformed

    private void ShowAllbtn2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShowAllbtn2MouseClicked
        // TODO add your handling code here:
          StudentstblHandler.showStudentsTable(StudentsTable);
    }//GEN-LAST:event_ShowAllbtn2MouseClicked

    private void studentidTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentidTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studentidTFActionPerformed

    private void lastnameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastnameTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastnameTFActionPerformed

    private void lrnTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lrnTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lrnTFActionPerformed

    private void yearcbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearcbActionPerformed
        if (yearcb.getSelectedItem().equals("Default")) {
            sectioncb.removeAllItems();
            sectioncb.setSelectedItem("Select year level first");
            sectioncb.addItem("Select year level first");
        } else if (yearcb.getSelectedItem().equals("Grade 7")) {
            sectioncb.removeAllItems();
            sectioncb.setSelectedItem(null);
            sectioncb.addItem("7a");
            sectioncb.addItem("7b");
            sectioncb.addItem("7c");
        } else if (yearcb.getSelectedItem().equals("Grade 8")) {
            sectioncb.removeAllItems();
            sectioncb.setSelectedItem(null);
            sectioncb.addItem("8a");
            sectioncb.addItem("8b");
            sectioncb.addItem("8c");
        } else if (yearcb.getSelectedItem().equals("Grade 9")) {
            sectioncb.removeAllItems();
            sectioncb.setSelectedItem(null);
            sectioncb.addItem("9a");
            sectioncb.addItem("9b");
            sectioncb.addItem("9c");
        } else if (yearcb.getSelectedItem().equals("Grade 10")) {
            sectioncb.removeAllItems();
            sectioncb.setSelectedItem(null);
            sectioncb.addItem("10a");
            sectioncb.addItem("10b");
            sectioncb.addItem("10c");
        }
    }//GEN-LAST:event_yearcbActionPerformed

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked

        String studentidstr = studentidTF.getText();
        int studentid = Integer.parseInt(studentidstr);
        String lastname = lastnameTF.getText();
        String firstname = firstnameTF.getText();
        String email = emailTF.getText();
        String lrn = lrnTF.getText();
        String grade = (String) yearcb.getSelectedItem();
        String section = (String) sectioncb.getSelectedItem();
        String status = (String) statuscb.getSelectedItem();

        StudentstblHandler.updateStudents(studentid, lrn, lastname, firstname, email, grade, section, status);
        StudentstblHandler.showStudentsTable(StudentsTable);
    }//GEN-LAST:event_jPanel9MouseClicked

    private void firstnameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstnameTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstnameTFActionPerformed

    private void emailTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTFActionPerformed

    private void sectioncbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sectioncbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sectioncbActionPerformed

    private void statuscbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statuscbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statuscbActionPerformed

    private void AddBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddBtnMouseClicked
        AddInventory.pack();
        AddInventory.setLocationRelativeTo(studentspanel);
        AddInventory.setVisible(true);


    }//GEN-LAST:event_AddBtnMouseClicked

    private void equipmentTF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equipmentTF1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_equipmentTF1ActionPerformed

    private void quantityequipmenttF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityequipmenttF1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityequipmenttF1ActionPerformed

    private void categoryequipmentCB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryequipmentCB1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoryequipmentCB1ActionPerformed

    private void jPanel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseClicked
  
     String equipmentname = equipmentTF1.getText();
String equipmentquantitystr = quantityequipmenttF1.getText();
int equipmentquantity = Integer.parseInt(equipmentquantitystr);
String equipmentcategory = (String) categoryequipmentCB1.getSelectedItem();

// Check if the category is "Default"
if ( equipmentcategory.equals("Default")) {
    JOptionPane.showMessageDialog(null, "Please fill up the category part!", "Input Error", JOptionPane.ERROR_MESSAGE);
    return; // Stop further execution
}if (InventorytblHandler.isEquipmentNameExists(equipmentname)) {
    JOptionPane.showMessageDialog(null, "The equipment name already exists in the database. Please use a unique name.", "Duplicate Entry", JOptionPane.ERROR_MESSAGE);
    return; // Stop further execution
}else{

InventorytblHandler.addInventory(equipmentname, equipmentquantity, equipmentcategory);
InventorytblHandler.InventoryShowTable(InventoryTable);
AddInventory.setVisible(false);

// Clear input fields
equipmentTF1.setText("");
quantityequipmenttF1.setText("");
categoryequipmentCB1.setSelectedItem("Default");

InventorytblHandler.generateQRCode(equipmentname, email1);
InventorytblHandler.InventoryShowTable(InventoryTable);
AddInventory.setVisible(false);
}
    }//GEN-LAST:event_jPanel11MouseClicked

    private void lastnametfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastnametfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastnametfActionPerformed

    private void lrntfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lrntfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lrntfActionPerformed

    private void yearcb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearcb1ActionPerformed
        // TODO add your handling code here:
           if (yearcb1.getSelectedItem().equals("Default")) {
            sectioncb1.removeAllItems();
            sectioncb1.setSelectedItem("Select year level first");
            sectioncb1.addItem("Select year level first");
        } else if (yearcb1.getSelectedItem().equals("Grade 7")) {
            sectioncb1.removeAllItems();
            sectioncb1.setSelectedItem(null);
            sectioncb1.addItem("El nido");
            sectioncb1.addItem("Cebu");
            sectioncb1.addItem("Siargao");
            sectioncb1.addItem("sagada");
            sectioncb1.addItem("Aurora");
        } else if (yearcb1.getSelectedItem().equals("Grade 8")) {
            sectioncb1.removeAllItems();
            sectioncb1.setSelectedItem(null);
            sectioncb1.addItem("Panagbenga");
            sectioncb1.addItem("Moriones");
            sectioncb1.addItem("Pahiyas");
            sectioncb1.addItem("Pintados");
            sectioncb1.addItem("Magayon");
            
        } else if (yearcb1.getSelectedItem().equals("Grade 9")) {
            sectioncb1.removeAllItems();
            sectioncb1.setSelectedItem(null);
            sectioncb1.addItem("Leo");
            sectioncb1.addItem("Sagittarius");
            sectioncb1.addItem("libra");
            sectioncb1.addItem("Aries");
            sectioncb1.addItem("Gemini");
        } else if (yearcb1.getSelectedItem().equals("Grade 10")) {
            sectioncb1.removeAllItems();
            sectioncb1.setSelectedItem(null);
            sectioncb1.addItem("Conficius");
            sectioncb1.addItem("Aurelius");
            sectioncb1.addItem("Socrates");
            sectioncb1.addItem("Voltaire");
            sectioncb1.addItem("Aristotle");
        }
    }//GEN-LAST:event_yearcb1ActionPerformed

    private void jPanel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseClicked
        // TODO add your handling code here:
        
        String fname = firstnametf.getText().toLowerCase();
    String uname = lastnametf.getText().toLowerCase();
    String email = emailtf.getText();
    String status = "Active";
    String lrn = lrntf.getText();
    String grade_level = (String) yearcb1.getSelectedItem();
    String section = (String) sectioncb1.getSelectedItem();
//    String qr_code = "ewewewew";
    
    // Condition when the user clicks submit without providing an input
    if (fname.equals("") || uname.equals("") || email.equals("")) {
        JOptionPane.showMessageDialog(null, "Please provide an input.");
        return;
    }

    if (email.contains("..")) {
        JOptionPane.showMessageDialog(null, "Input not acceptable.");
        return;
    } if (!NetworkUtil.isInternetAvailable1()) {
       JOptionPane.showMessageDialog(null, "No internet connection. Please connect to the internet to register.");
        return;
            }
    
    // Email validation
    if (!isValidEmail(email)) {
        JOptionPane.showMessageDialog(null, "Invalid email format.");
        return;
    }

    if (email.contains(" ")) {
        JOptionPane.showMessageDialog(null, "Invalid Sign up. Email can't contain white space");
        return;
    }

    if (!fname.matches("^[a-zA-Z\\s]+$")) {
        JOptionPane.showMessageDialog(null, "Firstname must not contain numbers or special characters.");
        return;
    }

    if (!uname.matches("^[a-zA-Z\\s]+$")) {
        JOptionPane.showMessageDialog(null, "Lastname must not contain numbers or special characters.");
        return;
    }
        if (!isLrnUnique(lrn)) {
    JOptionPane.showMessageDialog(null, "LRN already exists.");
    return; // Stop the registration process
}
    boolean isExist = UserHandler.registerUser12(lrn, uname, fname, email, grade_level, section, status);
    AddStudents.setVisible(false);
//    Frontpage frame = new Frontpage();
//    frame.setVisible(true);

    if (isExist) {
        JOptionPane.showMessageDialog(null, "Data already exists.");
         AddStudents.setVisible(false); 
    } else {
        JOptionPane.showMessageDialog(null, "Account created successfully.");
         AddStudents.setVisible(false); 
          StudentstblHandler.showStudentsTable(StudentsTable);
    }
        
        
    }//GEN-LAST:event_jPanel13MouseClicked
    
    public static boolean isLrnUnique(String lrn) {
    String query = "SELECT COUNT(*) FROM studentstbl WHERE lrn = ?";
    try (Connection conn = DatabaseConnector.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setString(1, lrn);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) == 0; // Returns true if no rows exist with the given LRN
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // Default to false if any error occurs
}
    private void firstnametfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstnametfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstnametfActionPerformed

    private void emailtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailtfActionPerformed

    private void sectioncb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sectioncb1ActionPerformed
        // TODO add your handling code here:
       yearcb1.addActionListener(e -> {
    String selectedYear = (String) yearcb1.getSelectedItem();
    sectioncb1.removeAllItems(); // Clear the section combo box first

    if ("Default".equals(selectedYear)) {
        sectioncb1.addItem("Select year level first");
        sectioncb1.setSelectedItem("Select year level first");
    } else if ("Grade 7".equals(selectedYear)) {
        sectioncb1.addItem("El Nido");
        sectioncb1.addItem("Cebu");
        sectioncb1.addItem("Siargao");
        sectioncb1.addItem("Sagada");
        sectioncb1.addItem("Aurora");
    } else if ("Grade 8".equals(selectedYear)) {
        sectioncb1.addItem("Panagbenga");
        sectioncb1.addItem("Moriones");
        sectioncb1.addItem("Pahiyas");
        sectioncb1.addItem("Pintados");
        sectioncb1.addItem("Magayon");
    } else if ("Grade 9".equals(selectedYear)) {
        sectioncb1.addItem("Leo");
        sectioncb1.addItem("Sagittarius");
        sectioncb1.addItem("Libra");
        sectioncb1.addItem("Aries");
        sectioncb1.addItem("Gemini");
    } else if ("Grade 10".equals(selectedYear)) {
        sectioncb1.addItem("Confucius");
        sectioncb1.addItem("Aurelius");
        sectioncb1.addItem("Socrates");
        sectioncb1.addItem("Voltaire");
        sectioncb1.addItem("Aristotle");
    } 
});
    }//GEN-LAST:event_sectioncb1ActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        AddStudents.pack();
        AddStudents.setLocationRelativeTo(studentspanel);
        AddStudents.setVisible(true);
        
    }//GEN-LAST:event_jLabel9MouseClicked

    private void searchtf1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchtf1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchtf1ActionPerformed

    private void ShowAllbtn3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ShowAllbtn3MouseClicked
        // TODO add your handling code here:
        BorrowingtblHandler.populateBorrowingTable(BorrowingTable);
    }//GEN-LAST:event_ShowAllbtn3MouseClicked

    private void equipmentTF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equipmentTF2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_equipmentTF2ActionPerformed

    private void quantityequipmenttF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityequipmenttF2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityequipmenttF2ActionPerformed

    private void categoryequipmentCB2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryequipmentCB2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoryequipmentCB2ActionPerformed

    private void jPanel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel15MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel15MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
            JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Save Excel File");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    int returnValue = fileChooser.showSaveDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        String filePath = file.getAbsolutePath() + ".xls";

        try {
            exportJTableToExcel(InventoryTable, filePath);
            System.out.println("Excel file saved successfully to: " + filePath);
                 JOptionPane.showMessageDialog(null, "Excel file saved successfully");
            
        } catch (IOException ex) {
            System.out.println("Error exporting Excel file: " + ex.getMessage());
              JOptionPane.showMessageDialog(null, "Excel file Saving Failed");
        }
    
}
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        
                                                
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Save Excel File");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    int returnValue = fileChooser.showSaveDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        String filePath = file.getAbsolutePath() + ".xls";

        try {
            exportJTableToExcel(StudentsTable, filePath);
            System.out.println("Excel file saved successfully to: " + filePath);
            JOptionPane.showMessageDialog(null, "Excel file saved successfully");
        } catch (IOException ex) {
            System.out.println("Error exporting Excel file: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Excel file saved successfully");
        }
    
}
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Save Excel File");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    int returnValue = fileChooser.showSaveDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        String filePath = file.getAbsolutePath() + ".xls";

        try {
            exportJTableToExcel(InventoryTable, filePath);
            System.out.println("Excel file saved successfully to: " + filePath);
                 JOptionPane.showMessageDialog(null, "Excel file saved successfully");
            
        } catch (IOException ex) {
            System.out.println("Error exporting Excel file: " + ex.getMessage());
              JOptionPane.showMessageDialog(null, "Excel file Saving Failed");
        }
    
}
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
           JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Save Excel File");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    int returnValue = fileChooser.showSaveDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        String filePath = file.getAbsolutePath() + ".xls";

        try {
            exportJTableToExcel(AttendanceTable, filePath);
            System.out.println("Excel file saved successfully to: " + filePath);
                 JOptionPane.showMessageDialog(null, "Excel file saved successfully");
            
        } catch (IOException ex) {
            System.out.println("Error exporting Excel file: " + ex.getMessage());
              JOptionPane.showMessageDialog(null, "Excel file Saving Failed");
        }
    
}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void equipmentTF3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equipmentTF3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_equipmentTF3ActionPerformed

    private void statusTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusTfActionPerformed
        // TODO add your handling code here:
                                              
    // Get the selected item from the JComboBox
    String selectedItem = (String) statusTf.getSelectedItem();
    
    // Check if the selected item is "Lost"
    if (selectedItem.equals("Lost")) {
        lostItemCountField.setVisible(true);
        lostItemCountLabel.setVisible(true);
        DamagedLabel.setVisible(false);
        
    }else if(selectedItem.equals("Damaged")){
       
        lostItemCountField.setVisible(true);
        lostItemCountLabel.setVisible(false);
        DamagedLabel.setVisible(true);
    }
    else {
        lostItemCountField.setVisible(false);
        lostItemCountLabel.setVisible(false);
        DamagedLabel.setVisible(false);
       
    
}
        
    }//GEN-LAST:event_statusTfActionPerformed

    private void jPanel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseClicked
        // TODO add your handling code here:
        
         String idStr = equipmentidTF1.getText();
         int id = Integer.parseInt(idStr);
         String status = (String) statusTf.getSelectedItem();
         String quantity =  quantityTf.getText();
         int quantityStr = Integer.parseInt(quantity);
          int lostQuantity = (int) lostItemCountField.getValue();
         
         if(lostQuantity >quantityStr){
             JOptionPane.showMessageDialog(null, "Lost quantity can't be higher than the quantity", "Error", JOptionPane.ERROR_MESSAGE);
        } 
          else if (status == "Lost") {
            BorrowingtblHandler.updateBorrowingTableNULL(id, status, lostQuantity);
             BorrowingtblHandler.populateBorrowingTable(BorrowingTable);
             InventorytblHandler.InventoryShowTable(InventoryTable);
            UpdateBorrow.setVisible(false); 
        }  
          else if (status == "Damaged") {
            BorrowingtblHandler.updateBorrowingTableNULL(id, status, lostQuantity);
             BorrowingtblHandler.populateBorrowingTable(BorrowingTable);
             InventorytblHandler.InventoryShowTable(InventoryTable);
            UpdateBorrow.setVisible(false); 
        }  
        else {
            BorrowingtblHandler.updateBorrowingTable(id, status);
             BorrowingtblHandler.populateBorrowingTable(BorrowingTable);
               InventorytblHandler.InventoryShowTable(InventoryTable);
              UpdateBorrow.setVisible(false); 
        }
         
        
    }//GEN-LAST:event_jPanel17MouseClicked

    private void equipmentidTF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equipmentidTF1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_equipmentidTF1ActionPerformed

    private void EquiptmentTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EquiptmentTfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EquiptmentTfActionPerformed

    private void statusTfPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_statusTfPropertyChange
        // TODO add your handling code here:
        // Get the selected item from the JComboBox
    String selectedItem = (String) statusTf.getSelectedItem();
    
        if ("Lost".equals(selectedItem)) {
        
        lostItemCountField.setVisible(true);
        DamagedLabel.setVisible(true);
       
    }else {
        // Hide all specific fields if neither "Lost" nor "Damaged" is selected
        
        lostItemCountField.setVisible(false);
        DamagedLabel.setVisible(false);
        
    }

    }//GEN-LAST:event_statusTfPropertyChange

    private void lostItemCountFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_lostItemCountFieldPropertyChange
        // TODO add your handling code here:
         int value = (int) lostItemCountField.getValue();
      
   if (value < 1) {
        lostItemCountField.setValue(1);
    }
   
    }//GEN-LAST:event_lostItemCountFieldPropertyChange

    private void quantityTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityTfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityTfActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        generateWordDocument(InventoryTable);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        generateStudentListDocument(StudentsTable);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jPanel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel19MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel19MouseClicked

    private void studentssearchtfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentssearchtfKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_studentssearchtfKeyTyped

    private void AddAdminbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddAdminbtnMouseClicked
        // TODO add your handling code here:
         AddAdmin.pack();
        AddAdmin.setLocationRelativeTo(dashboardpanel);
        AddAdmin.setVisible(true);
    }//GEN-LAST:event_AddAdminbtnMouseClicked

    private void lastnameadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastnameadminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastnameadminActionPerformed

    private void jPanel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel21MouseClicked
        // TODO add your handling code here:
         
        String fname = firstnameAdmin.getText().toLowerCase();
        String uname = lastnameadmin.getText().toLowerCase();
        String email = emailtfadmin.getText();
        String password = String.valueOf(passwordAdmin.getPassword());
        String confirmPass = String.valueOf(ConfirmPAsswordAdmin.getPassword());
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
            if (!NetworkUtil.isInternetAvailable1()) {
       JOptionPane.showMessageDialog(null, "No internet connection. Please connect to the internet to register.");
        return;
            }
            if (!uname.matches("^[a-zA-Z\\s]+$")) {
                JOptionPane.showMessageDialog(null, "Lastname Must Not Have A Number Or Special Characters");
                return;
            }
            if (password.equals(confirmPass)) {
            boolean isExist = UserHandler.registerUserAdmin(uname, fname, email, status, password, qr_code );
//                    dispose();
                    AddAdmin.setVisible(false);
            if (isExist) {
                JOptionPane.showMessageDialog(null, "Data Exists Already");
            } else {
                JOptionPane.showMessageDialog(null, "Account created Successfully.");
               
               

           
            }
            
            }
    }//GEN-LAST:event_jPanel21MouseClicked

    private void firstnameAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstnameAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstnameAdminActionPerformed

    private void emailtfadminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailtfadminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailtfadminActionPerformed

    private void showPasswordCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPasswordCheckboxActionPerformed
        // TODO add your handling code here:
        if (showPasswordCheckbox.isSelected()) {
                    // Show password
                    passwordAdmin.setEchoChar((char) 0);
                } else {
                    // Hide password
                    passwordAdmin.setEchoChar('*'); // Default echo char
                }
    }//GEN-LAST:event_showPasswordCheckboxActionPerformed

    private void confirmpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmpasswordActionPerformed
        // TODO add your handling code here:
        
         if (confirmpassword.isSelected()) {
                    // Show password
                    ConfirmPAsswordAdmin.setEchoChar((char) 0);
                } else {
                    // Hide password
                    ConfirmPAsswordAdmin.setEchoChar('*'); // Default echo char
                }
        
    }//GEN-LAST:event_confirmpasswordActionPerformed

    private void jPanel21MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel21MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel21MouseEntered

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
         ChangePasswordAdmin.pack();
        ChangePasswordAdmin.setLocationRelativeTo(dashboardpanel);
        ChangePasswordAdmin.setVisible(true);
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jPanel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel23MouseClicked
        // TODO add your handling code here:
        

           

        
        String adminemail = AdminEmail.getText();
        String oldPassword = String.valueOf(oldPaswword.getPassword());
        String newPassword = String.valueOf(NewPassword.getPassword());
        String confirmPassword = String.valueOf(Confirmpassword.getPassword());
      
        // Check for empty inputs
        if (oldPassword.equals("") || newPassword.equals("") || confirmPassword.equals("")) {
            JOptionPane.showMessageDialog(null, "Please provide an input.");
            oldPaswword.setText("");
            NewPassword.setText("");
            Confirmpassword.setText("");
            return;
        }

        // Check for consecutive periods in the new password or confirm password
        if (newPassword.contains("..") || confirmPassword.contains("..")) {
            JOptionPane.showMessageDialog(null, "Input not acceptable.");
            NewPassword.setText("");
            Confirmpassword.setText("");
            return;
        }

        // Check if new password and confirm password match
        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "New password and confirm password do not match.");
            NewPassword.setText("");
            Confirmpassword.setText("");
            return;
        }

        // Check if passwords contain only letters and numbers
        if (!newPassword.matches("^[a-zA-Z0-9]+$") || !confirmPassword.matches("^[a-zA-Z0-9]+$")) {
            JOptionPane.showMessageDialog(null, "Password must contain only letters and numbers.");
            NewPassword.setText("");
            Confirmpassword.setText("");
            return;
        }

        // Check if the old password is correct (assuming `isCorrectOldPassword` method)
        if (!isCorrectOldPassword(adminemail, oldPassword)) {
            JOptionPane.showMessageDialog(null, "Old password is incorrect.");
            oldPaswword.setText("");
            return;
        }

        // Password length check (minimum 8 characters)
        if (newPassword.length() < 8) {
            JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long.");
            NewPassword.setText("");
            Confirmpassword.setText("");
            return;
        }

        // If all conditions are met, update the password
        if (updateAdminPassword(adminemail, newPassword)) {
            JOptionPane.showMessageDialog(null, "Password updated successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update password. Please try again.");
        }

        // Clear all password fields after updating
        oldPaswword.setText("");
        NewPassword.setText("");
        Confirmpassword.setText("");

        
    }//GEN-LAST:event_jPanel23MouseClicked
private boolean isCorrectOldPassword(String email, String oldPassword) {
    String query = "SELECT student_password FROM admintbl WHERE email = ?";
    try (Connection conn = DatabaseConnector.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {

        pstmt.setString(1, email); // Set email parameter
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                String storedPassword = rs.getString("student_password");
                return storedPassword.equals(oldPassword); // Check if passwords match
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error verifying old password: " + e.getMessage());
    }
    return false; // Return false if the email doesn't exist or an error occurs
}
public boolean updateAdminPassword(String email, String newPassword) {
    String updateQuery = "UPDATE admintbl SET student_password = ? WHERE email = ?";

    try (Connection conn = DatabaseConnector.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {

        // Set the parameters for the query
        pstmt.setString(1, newPassword);
        pstmt.setString(2, email);

        // Execute the update query
        int rowsUpdated = pstmt.executeUpdate();

        // Return true if the update was successful
        return rowsUpdated > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error updating password: " + e.getMessage());
        return false;
    }
}

    private void NewPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NewPasswordActionPerformed

    private void AdminEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AdminEmailActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminDashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminDashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminDashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminDashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminDashBoard(email1).setVisible(true);
            }
        });
    }
 private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
 
public void exportJTableToExcel(JTable Jtable, String filePath) throws IOException {
    // Create a new Excel file
    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet sheet = workbook.createSheet("Sheet1");

    // Get the JTable's data
    DefaultTableModel model = (DefaultTableModel) Jtable.getModel();
    int rows = model.getRowCount();
    int cols = model.getColumnCount();

    // Define the column names
    String[] columnNames = new String[cols];
    for (int j = 0; j < cols; j++) {
        columnNames[j] = model.getColumnName(j);
    }

    // Create a header row with the column names
    HSSFRow headerRow = sheet.createRow(0);
    for (int j = 0; j < cols; j++) {
        HSSFCell cell = headerRow.createCell(j);
        cell.setCellValue(columnNames[j]);
    }

    // Iterate through the JTable's rows and columns
    for (int i = 1; i <= rows; i++) {
        HSSFRow row = sheet.createRow(i);
        for (int j = 0; j < cols; j++) {
            HSSFCell cell = row.createCell(j);
            Object value = model.getValueAt(i - 1, j); // Get the cell value
            // Check for null and set the value
            cell.setCellValue(value != null ? value.toString() : "");
        }
    }

    // Save the Excel file
    File file = new File(filePath);
    try (FileOutputStream fileOut = new FileOutputStream(file)) {
        workbook.write(fileOut);
    }

    workbook.close(); // Close the workbook
    System.out.println("Data exported successfully to " + file.getAbsolutePath());
}
 
public void generateWordDocument(JTable table) {
    XWPFDocument document = new XWPFDocument();

    try {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if (model == null || model.getRowCount() == 0) {
            throw new NullPointerException("Table model is null or contains no data");
        }

        int itemsPerPage = 9; // 3x3 grid per table
        int currentItem = 0;

        XWPFTable wordTable = null;

        for (int i = 0; i < model.getRowCount(); i++) {
            // Create a new 3x3 table if this is the first item or after every 9 items
            if (currentItem % itemsPerPage == 0) {
                wordTable = document.createTable();
                wordTable.removeRow(0); // Remove the default empty row

                // Initialize a 3x3 grid
                for (int row = 0; row < 3; row++) {
                    XWPFTableRow tableRow = wordTable.createRow();
                    for (int col = 0; col < 3; col++) {
                        tableRow.addNewTableCell(); // Ensure 3 cells per row
                    }
                }
            }

            String itemId = model.getValueAt(i, 0).toString(); // Assuming the ID is in column 0

            String query = "SELECT equipment_name, qr_path FROM inventorytable WHERE id = ?";

            try (Connection conn = DatabaseConnector.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

                pstmt.setInt(1, Integer.parseInt(itemId)); // Convert itemId to integer
                try (ResultSet rs = pstmt.executeQuery()) {

                    if (rs.next()) {
                        String equipmentName = rs.getString("equipment_name");
                        String qrPath = rs.getString("qr_path");

                        // Ensure the image file path is valid and file exists
                        File imageFile = new File(qrPath);
                        if (!imageFile.exists()) {
                            JOptionPane.showMessageDialog(null, "Image file not found: " + qrPath);
                            continue; // Skip this entry if the image does not exist
                        }

                        int row = (currentItem % itemsPerPage) / 3; // Determine the row index (0-2)
                        int col = (currentItem % itemsPerPage) % 3; // Determine the column index (0-2)

                        XWPFTableRow tableRow = wordTable.getRow(row);
                        XWPFTableCell cell = tableRow.getCell(col);

                        // Add QR code and equipment name in the same cell
                        XWPFParagraph paragraph = cell.addParagraph();
                        XWPFRun run = paragraph.createRun();

                        // Add QR code (image) first
                        addPictureToRun(document, run, qrPath, 144, 144); // 2x2 inch image
                        run.addBreak(); // Add a line break

                        // Add equipment name below the QR code
                        run.setText(equipmentName);
                    }
                }
            }
            currentItem++;
        }

        // Open JFileChooser to let the user choose the file path and name
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Word Document");
        fileChooser.setSelectedFile(new File("EquipmentList.docx")); // Default file name

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (FileOutputStream out = new FileOutputStream(fileToSave)) {
                document.write(out);
                JOptionPane.showMessageDialog(null, "Word document saved successfully at: " + fileToSave.getAbsolutePath());
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




private void addPictureToRun(XWPFDocument document, XWPFRun run, String qr_path, int width, int height) {
    File imageFile = new File(qr_path);
    if (!imageFile.exists()) {
        throw new NullPointerException("Image file not found: " + qr_path);
    }

    try (FileInputStream is = new FileInputStream(imageFile)) {
        run.addPicture(is, XWPFDocument.PICTURE_TYPE_PNG, qr_path, Units.toEMU(width), Units.toEMU(height));
    } catch (InvalidFormatException | IOException e) {
        e.printStackTrace();
    }
}

// for printing students list

public void generateStudentListDocument(JTable table) {
    XWPFDocument document = new XWPFDocument();

    try {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if (model == null || model.getRowCount() == 0) {
            throw new NullPointerException("Table model is null or contains no data");
        }

        // Define rows and columns for the 3x3 grid
        final int rowsPerPage = 3;
        final int colsPerPage = 3;

        XWPFTable tableDoc = document.createTable(rowsPerPage, colsPerPage);
        tableDoc.setWidth("100%"); // Set table width to 100% of page

        int studentIndex = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            String studentId = model.getValueAt(i, 0).toString(); // Assuming the ID is in column 0

            String query = "SELECT firstname, lastname, qr_code FROM studentstbl WHERE id = ?";

            try (Connection conn = DatabaseConnector.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {

                pstmt.setInt(1, Integer.parseInt(studentId)); // Convert studentId to integer
                try (ResultSet rs = pstmt.executeQuery()) {

                    if (rs.next()) {
                        String firstname = rs.getString("firstname");
                        String lastname = rs.getString("lastname");
                        String qrPath = rs.getString("qr_code");

                        // Ensure the image file path is valid and file exists
                        File imageFile = new File(qrPath);
                        if (!imageFile.exists()) {
                            JOptionPane.showMessageDialog(null, "Image file not found: " + qrPath);
                            continue; // Skip this entry if the image does not exist
                        }

                        // Calculate current row and column in the grid
                        int row = studentIndex / colsPerPage;
                        int col = studentIndex % colsPerPage;

                        // Add QR code and name to the current cell
                        XWPFTableCell cell = tableDoc.getRow(row).getCell(col);
                        XWPFParagraph paragraph = cell.addParagraph();
                        XWPFRun run = paragraph.createRun();

                        // Add picture to the document
                        addStudentPictureToRun(document, run, qrPath, 144, 144); // 2x2 inch image
                        run.addBreak();

                        // Add student name below the image
                        run.setText("Student Name: " + firstname + " " + lastname);

                        // Move to the next cell
                        studentIndex++;

                        // If the grid is full (3x3), start a new table on the next page
                        if (studentIndex % (rowsPerPage * colsPerPage) == 0) {
                            XWPFParagraph pageBreak = document.createParagraph();
                            pageBreak.setPageBreak(true); // Add a page break
                            tableDoc = document.createTable(rowsPerPage, colsPerPage); // Create a new table
                            tableDoc.setWidth("100%");
                            studentIndex = 0; // Reset index for new page
                        }
                    }
                }
            }
        }

        // Open JFileChooser to let the user choose the file path and name
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Student List Document");
        fileChooser.setSelectedFile(new File("StudentList.docx")); // Default file name

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (FileOutputStream out = new FileOutputStream(fileToSave)) {
                document.write(out);
                JOptionPane.showMessageDialog(null, "Word document saved successfully at: " + fileToSave.getAbsolutePath());
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




private void addStudentPictureToRun(XWPFDocument document, XWPFRun run, String qr_path, int width, int height) {
    File imageFile = new File(qr_path);
    if (!imageFile.exists()) {
        throw new NullPointerException("Image file not found: " + qr_path);
    }

    try (FileInputStream is = new FileInputStream(imageFile)) {
        run.addPicture(is, XWPFDocument.PICTURE_TYPE_PNG, qr_path, Units.toEMU(width), Units.toEMU(height));
    } catch (InvalidFormatException | IOException e) {
        e.printStackTrace();
    }
}



 public static void fetchNotifications(JTable notificationTable) {
        String query = "SELECT description FROM notifications ORDER BY timestamp DESC";
        
        // Get the table's model
        DefaultTableModel model = (DefaultTableModel) notificationTable.getModel();
        
        // Clear any existing rows in the table
        model.setRowCount(0);

        // Establish the database connection and fetch data
        try (Connection conn = DatabaseConnector.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            
            // Iterate through the result set and add data to the table
            while (rs.next()) {
                String message = rs.getString("description");
                model.addRow(new Object[]{message});  // Add the message to the table
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching notifications: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog AddAdmin;
    private javax.swing.JLabel AddAdminbtn;
    private javax.swing.JDialog AddBorrowing;
    private javax.swing.JLabel AddBtn;
    private javax.swing.JDialog AddInventory;
    private javax.swing.JDialog AddInventory_multiple;
    private javax.swing.JDialog AddStudents;
    private javax.swing.JTextField AdminEmail;
    private javax.swing.JTable AttendanceTable;
    private javax.swing.JTable BorrowingTable;
    private javax.swing.JDialog ChangePasswordAdmin;
    private javax.swing.JPasswordField ConfirmPAsswordAdmin;
    private javax.swing.JPasswordField Confirmpassword;
    private javax.swing.JLabel DamagedLabel;
    private javax.swing.JTextField EquiptmentTf;
    private javax.swing.JTable InventoryTable;
    private javax.swing.JPasswordField NewPassword;
    private javax.swing.JPanel ShowAllbtn;
    private javax.swing.JPanel ShowAllbtn1;
    private javax.swing.JPanel ShowAllbtn2;
    private javax.swing.JPanel ShowAllbtn3;
    private javax.swing.JTable StudentsTable;
    private javax.swing.JDialog UpdateBorrow;
    private javax.swing.JDialog UpdateInventory;
    private javax.swing.JDialog UpdateStudents;
    private javax.swing.JLabel attendance;
    private javax.swing.JPanel attendancebtn;
    private javax.swing.JPanel attendancepanel;
    private javax.swing.JLabel borrowing;
    private javax.swing.JPanel borrowingbtn;
    private javax.swing.JPanel borrowingpanel;
    private javax.swing.JLabel cancelupdateinventory;
    private javax.swing.JLabel cancelupdateinventory1;
    private javax.swing.JLabel cancelupdateinventory2;
    private javax.swing.JLabel cancelupdateinventory3;
    private javax.swing.JLabel cancelupdateinventory4;
    private javax.swing.JLabel cancelupdateinventory5;
    private javax.swing.JLabel cancelupdateinventory6;
    private javax.swing.JLabel cancelupdateinventory7;
    private javax.swing.JLabel cancelupdateinventory8;
    private com.CustomCards.CardForm cardForm1;
    private javax.swing.JLabel categoryequipment;
    private javax.swing.JLabel categoryequipment1;
    private javax.swing.JLabel categoryequipment2;
    private javax.swing.JLabel categoryequipment3;
    private javax.swing.JLabel categoryequipment4;
    private javax.swing.JLabel categoryequipment5;
    private javax.swing.JLabel categoryequipment6;
    private javax.swing.JLabel categoryequipment7;
    private javax.swing.JLabel categoryequipment8;
    private javax.swing.JLabel categoryequipment9;
    private javax.swing.JComboBox<String> categoryequipmentCB;
    private javax.swing.JComboBox<String> categoryequipmentCB1;
    private javax.swing.JComboBox<String> categoryequipmentCB2;
    private javax.swing.JCheckBox confirmpassword;
    private javax.swing.JLabel dashboard;
    private javax.swing.JPanel dashboardbtn;
    private javax.swing.JPanel dashboardpanel;
    private com.toedter.calendar.JDateChooser dateChooser;
    private com.toedter.calendar.JDateChooser dateChooser1;
    private javax.swing.JLabel emailLbl;
    private javax.swing.JTextField emailTF;
    private javax.swing.JTextField emailtf;
    private javax.swing.JTextField emailtfadmin;
    private javax.swing.JLabel equipment;
    private javax.swing.JLabel equipment1;
    private javax.swing.JLabel equipment10;
    private javax.swing.JLabel equipment11;
    private javax.swing.JLabel equipment12;
    private javax.swing.JLabel equipment13;
    private javax.swing.JLabel equipment2;
    private javax.swing.JLabel equipment3;
    private javax.swing.JLabel equipment4;
    private javax.swing.JLabel equipment5;
    private javax.swing.JLabel equipment6;
    private javax.swing.JLabel equipment7;
    private javax.swing.JLabel equipment8;
    private javax.swing.JLabel equipment9;
    private javax.swing.JTextField equipmentTF;
    private javax.swing.JTextField equipmentTF1;
    private javax.swing.JTextField equipmentTF2;
    private javax.swing.JTextField equipmentTF3;
    private javax.swing.JLabel equipmentid;
    private javax.swing.JLabel equipmentid1;
    private javax.swing.JLabel equipmentid2;
    private javax.swing.JLabel equipmentid3;
    private javax.swing.JLabel equipmentid4;
    private javax.swing.JLabel equipmentid5;
    private javax.swing.JTextField equipmentidTF;
    private javax.swing.JTextField equipmentidTF1;
    private javax.swing.JTextField firstnameAdmin;
    private javax.swing.JTextField firstnameTF;
    private javax.swing.JTextField firstnametf;
    private javax.swing.JLabel inventory;
    private javax.swing.JPanel inventorybtn;
    private javax.swing.JPanel inventorypanel;
    private javax.swing.JTextField inventorysearchtf;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextField lastnameTF;
    private javax.swing.JTextField lastnameadmin;
    private javax.swing.JTextField lastnametf;
    private javax.swing.JPanel left;
    private javax.swing.JPanel left1;
    private javax.swing.JPanel left2;
    private javax.swing.JPanel left3;
    private javax.swing.JPanel left4;
    private javax.swing.JPanel left5;
    private javax.swing.JPanel left6;
    private javax.swing.JPanel left7;
    private javax.swing.JPanel left8;
    private javax.swing.JLabel logout;
    private javax.swing.JPanel logoutbtn;
    private javax.swing.JSpinner lostItemCountField;
    private javax.swing.JLabel lostItemCountLabel;
    private javax.swing.JTextField lrnTF;
    private javax.swing.JTextField lrntf;
    private javax.swing.JTable notiicationTable;
    private javax.swing.JPasswordField oldPaswword;
    private javax.swing.JPanel overlaypanel;
    private javax.swing.JPasswordField passwordAdmin;
    private javax.swing.JTextField quantityTf;
    private javax.swing.JLabel quantityequipment;
    private javax.swing.JLabel quantityequipment1;
    private javax.swing.JLabel quantityequipment2;
    private javax.swing.JLabel quantityequipment3;
    private javax.swing.JLabel quantityequipment4;
    private javax.swing.JLabel quantityequipment5;
    private javax.swing.JLabel quantityequipment6;
    private javax.swing.JLabel quantityequipment7;
    private javax.swing.JTextField quantityequipmenttF;
    private javax.swing.JTextField quantityequipmenttF1;
    private javax.swing.JTextField quantityequipmenttF2;
    private javax.swing.JLabel quantitylbl;
    private javax.swing.JLabel saveupdateinventory;
    private javax.swing.JLabel saveupdateinventory1;
    private javax.swing.JLabel saveupdateinventory2;
    private javax.swing.JLabel saveupdateinventory3;
    private javax.swing.JLabel saveupdateinventory4;
    private javax.swing.JLabel saveupdateinventory5;
    private javax.swing.JLabel saveupdateinventory6;
    private javax.swing.JLabel saveupdateinventory7;
    private javax.swing.JLabel saveupdateinventory8;
    private javax.swing.JLabel scilab;
    private javax.swing.JTextField searchtf;
    private javax.swing.JTextField searchtf1;
    private javax.swing.JComboBox<String> sectioncb;
    private javax.swing.JComboBox<String> sectioncb1;
    private javax.swing.JLabel showAll;
    private javax.swing.JLabel showAll1;
    private javax.swing.JLabel showAll2;
    private javax.swing.JLabel showAll3;
    private javax.swing.JCheckBox showPasswordCheckbox;
    private javax.swing.JComboBox<String> statusTf;
    private javax.swing.JComboBox<String> statuscb;
    private javax.swing.JTextField studentidTF;
    private javax.swing.JLabel students;
    private javax.swing.JPanel studentsbtn;
    private javax.swing.JPanel studentspanel;
    private javax.swing.JTextField studentssearchtf;
    private javax.swing.JLabel updateinventory;
    private javax.swing.JLabel updateinventory1;
    private javax.swing.JLabel updateinventory2;
    private javax.swing.JLabel updateinventory3;
    private javax.swing.JLabel updateinventory4;
    private javax.swing.JLabel updateinventory5;
    private javax.swing.JLabel updateinventory6;
    private javax.swing.JLabel updateinventory7;
    private javax.swing.JComboBox<String> yearcb;
    private javax.swing.JComboBox<String> yearcb1;
    // End of variables declaration//GEN-END:variables
}
