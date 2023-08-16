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
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * Staff Create View
 * This class forms the GUI for Staff Master View panel.
 *
 * @author Swapnil Roy Chowdhury & Nguyen Anh Tuan Le
 */
public class StaffMasterView {
    //    All panels are grouped into a single panel with Border layout.
    private final JPanel staffMasterViewJPanel = new JPanel(new BorderLayout());
    private final Border defaultJTextFieldBorder = new JTextField().getBorder();

    //    Main panel
    public StaffMasterView(StaffController staffController) {
//        All fields and respective labels are grouped in a panel with flow layout
        Font boldFont = new Font("", Font.BOLD, 13);

        JPanel staffInfoJPanel = new JPanel(new GridLayout(6, 1));
        TitledBorder titledBorder = new TitledBorder(new EtchedBorder(), "Staff Information");
        titledBorder.setTitleFont(boldFont);
        staffInfoJPanel.setBorder(titledBorder);

        JLabel idJLabel = new JLabel("ID");
        JLabel lastNameJLabel = new JLabel("Last Name");
        JLabel firstNameJLabel = new JLabel("First Name");
        JLabel miJLabel = new JLabel("mi");
        JLabel addressJLabel = new JLabel("Address");
        JLabel cityJLabel = new JLabel("City");
        JLabel stateJLabel = new JLabel("State");
//        JLabel stateJLabel = new JLabel("Province"); // TODO for Canada
        JLabel telephoneJLabel = new JLabel("Telephone");
        JLabel emailJLabel = new JLabel("Email");

        idJLabel.setFont(boldFont);
        lastNameJLabel.setFont(boldFont);
        firstNameJLabel.setFont(boldFont);
        miJLabel.setFont(boldFont);
        addressJLabel.setFont(boldFont);
        cityJLabel.setFont(boldFont);
        stateJLabel.setFont(boldFont);
        telephoneJLabel.setFont(boldFont);
        emailJLabel.setFont(boldFont);

        JTextField idJTextField = new JTextField(9);
        JTextField lastNameJTextField = new JTextField(15);
        JTextField firstNameJTextField = new JTextField(15);
        JTextField miJTextField = new JTextField(1);
        JTextField addressJTextField = new JTextField(20);
        JTextField cityJTextField = new JTextField(20);
        JTextField stateJTextField = new JTextField(2);
        JTextField telephoneJTextField = new JTextField(10);
        JTextField emailJTextField = new JTextField(40);

        JPanel idJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        idJPanel.add(idJLabel);
        idJPanel.add(idJTextField);

        JPanel lastNameFirstNameMiJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lastNameFirstNameMiJPanel.add(lastNameJLabel);
        lastNameFirstNameMiJPanel.add(lastNameJTextField);
        lastNameFirstNameMiJPanel.add(firstNameJLabel);
        lastNameFirstNameMiJPanel.add(firstNameJTextField);
        lastNameFirstNameMiJPanel.add(miJLabel);
        lastNameFirstNameMiJPanel.add(miJTextField);

        JPanel addressJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addressJPanel.add(addressJLabel);
        addressJPanel.add(addressJTextField);

        JPanel cityStateJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cityStateJPanel.add(cityJLabel);
        cityStateJPanel.add(cityJTextField);
        cityStateJPanel.add(stateJLabel);
        cityStateJPanel.add(stateJTextField);

        JPanel telephoneJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        telephoneJPanel.add(telephoneJLabel);
        telephoneJPanel.add(telephoneJTextField);

        JPanel emailJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        emailJPanel.add(emailJLabel);
        emailJPanel.add(emailJTextField);

        staffInfoJPanel.add(idJPanel);
        staffInfoJPanel.add(lastNameFirstNameMiJPanel);
        staffInfoJPanel.add(addressJPanel);
        staffInfoJPanel.add(cityStateJPanel);
        staffInfoJPanel.add(telephoneJPanel);
        staffInfoJPanel.add(emailJPanel);

        staffMasterViewJPanel.add(staffInfoJPanel, BorderLayout.NORTH);

//        All button are grouped into a panel with flow layout
        JPanel buttonsFlowJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton viewJButton = new JButton("View");
        JButton insertJButton = new JButton("Insert");
        JButton updateJButton = new JButton("Update");
        JButton clearJButton = new JButton("Clear");

        viewJButton.setFont(boldFont);
        insertJButton.setFont(boldFont);
        updateJButton.setFont(boldFont);
        clearJButton.setFont(boldFont);

        Validator validator = new Validator(defaultJTextFieldBorder, staffController);

//        When the user clicks the clear button all field values are set to blank
        clearJButton.addActionListener(e -> {
            resetTexts(idJTextField, lastNameJTextField, firstNameJTextField, miJTextField, addressJTextField, cityJTextField, stateJTextField, telephoneJTextField, emailJTextField);
            resetBorders(idJTextField, lastNameJTextField, firstNameJTextField, miJTextField, addressJTextField, cityJTextField, stateJTextField, telephoneJTextField, emailJTextField);
        });

//        When the user clicks view button, the staff is display after checking with the database
        viewJButton.addActionListener(e -> {
            resetBorders(idJTextField, lastNameJTextField, firstNameJTextField, miJTextField, addressJTextField, cityJTextField, stateJTextField, telephoneJTextField, emailJTextField);
            idJTextField.setText(idJTextField.getText().replaceAll("\s", ""));
            if (!idJTextField.getText().isEmpty() && staffController.isStaffExisted(idJTextField.getText())) {
                Staff staff = staffController.viewStaff(idJTextField.getText());
                lastNameJTextField.setText(staff.getLastName());
                firstNameJTextField.setText(staff.getFirstName());
                miJTextField.setText(staff.getMi());
                addressJTextField.setText(staff.getAddress());
                cityJTextField.setText(staff.getCity());
                stateJTextField.setText(staff.getState());
                telephoneJTextField.setText(staff.getTelephone());
                emailJTextField.setText(staff.getEmail());
                resetBorders(idJTextField, lastNameJTextField, firstNameJTextField, miJTextField, addressJTextField, cityJTextField, stateJTextField, telephoneJTextField, emailJTextField);
            } else {
                validator.triggerInvalidJTextField(idJTextField);
                List<String> messages = staffController.getAllIds();
                if (idJTextField.getText().isEmpty()) {
                    if (messages.size() == 0) {
                        messages.add(0, "ID is missing. There is currently no staff in the database.");
                    } else {
                        messages.add(0, "ID is missing. Available IDs");
                    }
                } else {
                    if (messages.size() == 0) {
                        messages.add(0, "Staff does not exist. There is currently no staff in the database.");
                    } else {
                        messages.add(0, "Staff does not exist. Available IDs");
                    }
                }
                validator.displayErrorMessages(messages);
            }
        });

//        When the user clicks insert button, the staff is added after performing a set of checks
        insertJButton.addActionListener(e -> {
            resetBorders(idJTextField, lastNameJTextField, firstNameJTextField, miJTextField, addressJTextField, cityJTextField, stateJTextField, telephoneJTextField, emailJTextField);
            idJTextField.setText(idJTextField.getText().replaceAll("\s", ""));
            if (validator.validate(true, idJTextField, lastNameJTextField, firstNameJTextField, miJTextField, addressJTextField, cityJTextField, stateJTextField, telephoneJTextField, emailJTextField)) {
                staffController.insertStaff(new Staff(idJTextField.getText(), lastNameJTextField.getText(), firstNameJTextField.getText(), miJTextField.getText(), addressJTextField.getText(), cityJTextField.getText(), stateJTextField.getText(), telephoneJTextField.getText().replaceAll("[^0-9]+", ""), emailJTextField.getText()));
                resetTexts(idJTextField, lastNameJTextField, firstNameJTextField, miJTextField, addressJTextField, cityJTextField, stateJTextField, telephoneJTextField, emailJTextField);
                resetBorders(idJTextField, lastNameJTextField, firstNameJTextField, miJTextField, addressJTextField, cityJTextField, stateJTextField, telephoneJTextField, emailJTextField);
                JOptionPane.showMessageDialog(null, "Staff has been inserted successfully.");
            }
        });

//        When the user clicks update button, the staff is updated after performing a set of checks
        updateJButton.addActionListener(e -> {
            resetBorders(idJTextField, lastNameJTextField, firstNameJTextField, miJTextField, addressJTextField, cityJTextField, stateJTextField, telephoneJTextField, emailJTextField);
            idJTextField.setText(idJTextField.getText().replaceAll("\s", ""));
            if (validator.validate(false, idJTextField, lastNameJTextField, firstNameJTextField, miJTextField, addressJTextField, cityJTextField, stateJTextField, telephoneJTextField, emailJTextField)) {
                staffController.updateStaff(new Staff(idJTextField.getText(), lastNameJTextField.getText(), firstNameJTextField.getText(), miJTextField.getText(), addressJTextField.getText(), cityJTextField.getText(), stateJTextField.getText(), telephoneJTextField.getText().replaceAll("[^0-9]+", ""), emailJTextField.getText()));
                resetTexts(idJTextField, lastNameJTextField, firstNameJTextField, miJTextField, addressJTextField, cityJTextField, stateJTextField, telephoneJTextField, emailJTextField);
                resetBorders(idJTextField, lastNameJTextField, firstNameJTextField, miJTextField, addressJTextField, cityJTextField, stateJTextField, telephoneJTextField, emailJTextField);
                JOptionPane.showMessageDialog(null, "Staff has been updated successfully.");
            }
        });

        buttonsFlowJPanel.add(viewJButton);
        buttonsFlowJPanel.add(insertJButton);
        buttonsFlowJPanel.add(updateJButton);
        buttonsFlowJPanel.add(clearJButton);

        staffMasterViewJPanel.add(buttonsFlowJPanel, BorderLayout.CENTER);
        JLabel databaseStatusJLabel = new JLabel("Database connected");
        databaseStatusJLabel.setFont(boldFont);
        if (staffController.getConnection() != null) {
            databaseStatusJLabel.setText("Database is connected");
        } else {
            databaseStatusJLabel.setText("Database is not connected");
        }
        staffMasterViewJPanel.add(databaseStatusJLabel, BorderLayout.SOUTH);
    }

    //    reset borders of text fields
    private void resetBorders(JTextField idJTextField, JTextField lastNameJTextField, JTextField firstNameJTextField, JTextField miJTextField, JTextField addressJTextField, JTextField cityJTextField, JTextField stateJTextField, JTextField telephoneJTextField, JTextField emailJTextField) {
        for (JTextField jTextField : Arrays.asList(idJTextField, lastNameJTextField, firstNameJTextField, miJTextField, addressJTextField, cityJTextField, stateJTextField, telephoneJTextField, emailJTextField)) {
            jTextField.setBorder(defaultJTextFieldBorder);
        }
    }

    //    reset texts of text fields
    private void resetTexts(JTextField idJTextField, JTextField lastNameJTextField, JTextField firstNameJTextField, JTextField miJTextField, JTextField addressJTextField, JTextField cityJTextField, JTextField stateJTextField, JTextField telephoneJTextField, JTextField emailJTextField) {
        for (JTextField jTextField : Arrays.asList(idJTextField, lastNameJTextField, firstNameJTextField, miJTextField, addressJTextField, cityJTextField, stateJTextField, telephoneJTextField, emailJTextField)) {
            jTextField.setText("");
        }
    }

    /**
     * @return JPanel
     */
    public JPanel getStaffMasterView() {
        return staffMasterViewJPanel;
    }
}