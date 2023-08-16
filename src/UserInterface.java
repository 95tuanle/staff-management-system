/*************************************************************************************************
 * Database Pgm Using Java - ITC-5201-RNB – Assignment 4
 * We declare that this assignment is our own work in accordance with Humber Academic Policy.
 * No part of this assignment has been copied manually or electronically from any other source
 * (including websites) or distributed to other students/social media.
 * Name: Swapnil Roy Chowdhury	Student ID: N01469281
 * Name: Nguyen Anh Tuan Le	Student ID: N01414195
 * Date: Sun Mar 13 2022
 **************************************************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * User Interface
 *
 * @author Swapnil Roy Chowdhury & Nguyen Anh Tuan Le
 */
public class UserInterface extends JFrame {
    //	Main frame
    public UserInterface() {
        StaffController staffController = new StaffController();
        StaffMasterView staffMasterView = new StaffMasterView(staffController);
        this.setLayout(new BorderLayout());
        this.setSize(610, 320);
        this.setTitle("Staff Management System");
        this.setLocationRelativeTo(null);
        this.add(staffMasterView.getStaffMasterView(), BorderLayout.CENTER);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                staffController.closeConnection();
            }
        });
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * The point of entry for the application.
     */
    public static void main(String[] args) {
        new UserInterface();
    }
}