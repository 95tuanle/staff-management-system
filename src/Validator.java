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
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Validator
 *
 * @author Swapnil Roy Chowdhury & Nguyen Anh Tuan Le
 */
public record Validator(Border defaultJTextFieldBorder, StaffController staffController) {

//    Validate user input base on conditions, this will trigger UI elements to let user know which fields that they entered invalid inputs
    public boolean validate(boolean isInserting, JTextField idJTextField, JTextField lastNameJTextField, JTextField firstNameJTextField, JTextField miJTextField, JTextField addressJTextField, JTextField cityJTextField, JTextField stateJTextField, JTextField telephoneJTextField, JTextField emailJTextField) {
        List<String> errorMessages = new ArrayList<>();

        if (idJTextField.getText().isEmpty()) {
            throwException("ID is missing", errorMessages, idJTextField);
        } else if (idJTextField.getText().length() > 9) {
            throwException("ID must be under 10 characters long", errorMessages, idJTextField);
        } else if (isInserting) {
            if (staffController.isStaffExisted(idJTextField.getText())) {
                throwException("ID is existed", errorMessages, idJTextField);
            }
        } else {
            if (!staffController.isStaffExisted(idJTextField.getText())) {
                throwException("ID is not existed", errorMessages, idJTextField);
            }
        }

        if (!lastNameJTextField.getText().isEmpty()) {
            if (lastNameJTextField.getText().length() > 15) {
                throwException("Last name must be under 16 characters long", errorMessages, lastNameJTextField);
            } else if (lastNameJTextField.getText().isBlank()) {
                throwException("Last name cannot contain only whitespaces", errorMessages, lastNameJTextField);
            }
        }

        if (!firstNameJTextField.getText().isEmpty()) {
            if (firstNameJTextField.getText().length() > 15) {
                throwException("First name must be under 16 characters long", errorMessages, firstNameJTextField);
            } else if (firstNameJTextField.getText().isBlank()) {
                throwException("First name cannot contain only whitespaces", errorMessages, firstNameJTextField);
            }
        }

        if (!miJTextField.getText().isEmpty()) {
            if (miJTextField.getText().length() > 1) {
                throwException("Middle name initial must be under 2 characters long", errorMessages, miJTextField);
            } else if (!miJTextField.getText().matches("([A-Z])")) {
                throwException("Middle name initial is invalid", errorMessages, miJTextField);
            }
        }

        if (!addressJTextField.getText().isEmpty()) {
            if (addressJTextField.getText().length() > 20) {
                throwException("Address must be under 21 characters long", errorMessages, addressJTextField);
            } else if (addressJTextField.getText().isBlank()) {
                throwException("Address cannot contain only whitespaces", errorMessages, addressJTextField);
            }
        }

        if (!cityJTextField.getText().isEmpty()) {
            if (cityJTextField.getText().length() > 20) {
                throwException("City must be under 21 characters long", errorMessages, cityJTextField);
            } else if (!cityJTextField.getText().matches("^[a-zA-Z]+(?:(?:\\s+|-)[a-zA-Z]+)*$")) {
                throwException("City is invalid", errorMessages, cityJTextField);
            }
        }

        if (!stateJTextField.getText().isEmpty()) {
            if (!stateJTextField.getText().matches("^(?-i:A[LKSZRAEP]|C[AOT]|D[EC]|F[LM]|G[AU]|HI|I[ADLN]|K[SY]|LA|M[ADEHINOPST]|N[CDEHJMVY]|O[HKR]|P[ARW]|RI|S[CD]|T[NX]|UT|V[AIT]|W[AIVY])$")) {
                throwException("State is invalid", errorMessages, stateJTextField);
            }
//            TODO for canada
//            if (!stateJTextField.getText().matches("^(?-i:AB|BC|MB|N[BLS]|ON|PE|QC|SK)$")) {
//                throwException("Province is invalid", errorMessages, stateJTextField);
//            }
        }

        if (!telephoneJTextField.getText().isEmpty()) {
            if (!telephoneJTextField.getText().matches("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$")) {
                throwException("Telephone is invalid", errorMessages, telephoneJTextField);
            }
        }

        if (!emailJTextField.getText().isEmpty()) {
            if (emailJTextField.getText().length() > 40) {
                throwException("Email must be under 41 characters long", errorMessages, emailJTextField);
            } else if (!emailJTextField.getText().matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
                throwException("Email is invalid", errorMessages, emailJTextField);
            }
        }

        if (errorMessages.size() > 0) {
            displayErrorMessages(errorMessages);
            return false;
        } else {
            return true;
        }
    }

//    Throw exception according the procedure
    private void throwException(String message, List<String> errorMessages, JTextField jTextField) {
        try {
            throw new InputException(message);
        } catch (InputException e) {
            errorMessages.add(e.getMessage());
            triggerInvalidJTextField(jTextField);
        }
    }

//    This will set up the UI elements to let the user know about the input errors
    public void triggerInvalidJTextField(JTextField jTextField) {
        jTextField.setBorder(new LineBorder(Color.RED, 1));
//            The UI elements will be reverted whenever the user make changes to them
        jTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                jTextField.setBorder(defaultJTextFieldBorder);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                jTextField.setBorder(defaultJTextFieldBorder);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                jTextField.setBorder(defaultJTextFieldBorder);
            }
        });
    }

//    Display the
    public void displayErrorMessages(List<String> errorMessages) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String errorMessage :
                errorMessages) {
            stringBuilder.append(errorMessage).append("\n");
        }
        JOptionPane.showMessageDialog(null, stringBuilder);
    }
}