package com.company;

import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PasswordDialog extends JDialog  {
    private JTextField titleField;
    private JLabel loginLabel;
    private JTextField username;
    private JPasswordField password;
    private JLabel errorMsg;

    private JPanel panel;
    private JButton okButton;
    private JButton cancelButton;

    // Interface to transfer data back to parent
    public interface JDialogPasswordResponse{
        void getResponse(PasswordInformation pw);
    }

    public PasswordDialog(JDialogPasswordResponse response) {
        initFrame(response);
    }

    private void initFrame(JDialogPasswordResponse response) {
        // Release all resources when JDialog is closed
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setTitle("Create new login");
        this.setModal(true); /* this is important**/
        this.setResizable(false);
        //////////////////////////////////////////////////
        panel = new JPanel(new GridBagLayout());
        titleField = new JTextField();
        loginLabel = new JLabel("Login Details");
        username = new JTextField();
        password = new JPasswordField();
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        errorMsg = new JLabel();
        GridBagConstraints c;
        //=================================Title Field =====================
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(20,20,20,20);
        Font font = new Font("Arial", Font.BOLD,12);
        titleField.putClientProperty("JTextField.placeholderText", "Title");
        titleField.setBackground(Color.WHITE);
        titleField.setForeground(Color.BLACK);
        panel.add(titleField,c);

        //=================================== Login Details =============================
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,20,10,20);
        font = new Font("Arial", Font.BOLD,12);
        loginLabel.setFont(font);
        loginLabel.setForeground(Color.BLACK);
        panel.add(loginLabel,c);


        //================================== Username =====================================
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0,20,10,20);
        username.putClientProperty("JTextField.placeholderText", "Email or username");
        username.setBackground(Color.WHITE);
        username.setForeground(Color.BLACK);
        panel.add(username,c);

        //================================== Password =====================================
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0,20,20,20);
        password.setBackground(Color.WHITE);
        password.putClientProperty("JTextField.placeholderText", "Password");
        password.putClientProperty( FlatClientProperties.STYLE, "showRevealButton: true" );
        password.setForeground(Color.BLACK);
        panel.add(password,c);

        //================================== Ok btn =====================================
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 4;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.insets = new Insets(0,20,10,20);
        okButton.putClientProperty("JButton.buttonType", "roundRect");
        okButton.setForeground(Color.WHITE);
        okButton.setBackground(Color.BLACK);
        panel.add(okButton,c);

        // Add an action listener to the OK button
        okButton.addActionListener(e -> {
            if(titleField.getText().isEmpty()
                    || username.getText().isEmpty()
                    || String.valueOf(password.getPassword()).isEmpty()) {
                errorMsg.setVisible(true);
            }else{
                okBtn_response(response);
                dispose();
            }
        });


        //================================== Cancel btn =====================================
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 4;
        c.weightx = 1;
        c.insets = new Insets(0,20,10,20);
        c.fill = GridBagConstraints.HORIZONTAL;
        cancelButton.setForeground(Color.BLACK);
        cancelButton.setBackground(Color.WHITE);
        cancelButton.putClientProperty("JButton.buttonType", "roundRect");
        panel.add(cancelButton,c);

        // Add an action listener to the Cancel button
        cancelButton.addActionListener(e -> {
            setVisible(false);
            dispose();
        });

        //==================================Error Msg=================================
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weightx = 1;
        c.insets = new Insets(0,20,0,20);
        c.fill = GridBagConstraints.HORIZONTAL;
        errorMsg.setVisible(false);
        errorMsg.setForeground(Color.RED);
        errorMsg.setText("*Error: One of the filed is empty.");
        panel.add(errorMsg,c);


        //================================== End =====================================
        panel.setBorder( new EmptyBorder(10,10,10,10)); //top left bottom right
        panel.setBackground(Color.WHITE);
        this.add(panel);

        // Center dialog to screen
        this.setLocationRelativeTo(null);
    }

    private void okBtn_response(JDialogPasswordResponse response){
        //Get the fields and assign it to the obj to return
        PasswordInformation pw = new PasswordInformation();
        pw.setTitle(titleField.getText());
        pw.setUsername(username.getText());
        pw.setPassword(String.valueOf(password.getPassword()));

        response.getResponse(pw);
    }

}
