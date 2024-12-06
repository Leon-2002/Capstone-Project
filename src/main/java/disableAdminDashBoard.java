
import com.pages.AdminDashBoard;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author loena
 */
public class disableAdminDashBoard {
    private void disableAdminDashBoard(AdminDashBoard adminDashBoard, JDialog updateInventory) {
    // Disable the entire AdminDashBoard
    for (Component component : adminDashBoard.getComponents()) {
        component.setEnabled(false);
    }
    
    // Add a window listener to re-enable the AdminDashBoard when the UpdateInventory window is closed
    updateInventory.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosed(WindowEvent e) {
            // Re-enable the entire AdminDashBoard
            for (Component component : adminDashBoard.getComponents()) {
                component.setEnabled(true);
            }
        }
    });
}
}
