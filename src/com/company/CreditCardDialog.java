package com.company;

import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreditCardDialog extends JDialog {
    private JTextField titleField;
    private JLabel loginLabel;
    private JTextField cardHolder;
    private JTextField cardNumber;
    private JTextField cardExpDate;
    private JTextField cardZipCode;
    private JPasswordField cardCVV;
    private JPasswordField cardPIN;
    private JLabel errorMsg;
    private JPanel sub1;
    private JPanel sub2;


    private JPanel panel;
    private JButton okButton;
    private JButton cancelButton;

    // Interface to transfer data back to parent
    public interface JDialogCreditCardResponse {
        void getResponse(CreditCardInformation creditCard);
    }


    public CreditCardDialog(JDialogCreditCardResponse response){
        initComponents(response);
    }



    private void initComponents(JDialogCreditCardResponse response) {
        // Release all resources when JDialog is closed
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setTitle("Create new card");
        this.setModal(true); /* this is important**/
        this.setResizable(false);
        //////////////////////////////////////////////////
        panel = new JPanel(new GridBagLayout());
        titleField = new JTextField();
        loginLabel = new JLabel("Card Details");
        cardHolder = new JTextField();
        cardNumber = new JTextField();
        cardExpDate = new JTextField(8);//"Expiration Date".length()
        cardCVV = new JPasswordField();
        cardPIN = new JPasswordField();
        cardZipCode = new JTextField();
        sub1 = new JPanel(new GridLayout(1,2,5,0));
        sub2=new JPanel(new GridLayout(1,2,5,0));
        okButton = new JButton("Save");
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

        //=================================== Card Details Label =============================
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


        //================================== Cardholder Name =====================================
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0,20,10,20);
        cardHolder.putClientProperty("JTextField.placeholderText", "Cardholder Name");
        cardHolder.setName("Cardholder Name");
        cardHolder.setBackground(Color.WHITE);
        cardHolder.setForeground(Color.BLACK);
        panel.add(cardHolder,c);

        //================================== Card Number =====================================
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0,20,10,20);
        cardNumber.setBackground(Color.WHITE);
        cardNumber.putClientProperty("JTextField.placeholderText", "Card Number");
        cardNumber.setForeground(Color.BLACK);
        panel.add(cardNumber,c);
        //=========================================Sub1 Panel===========================
        //Expiration Date
        cardExpDate.setBackground(Color.WHITE);
        cardExpDate.putClientProperty("JTextField.placeholderText", "Expiration Date");
        cardExpDate.setForeground(Color.BLACK);
        // add filter
        sub1.add(cardExpDate);

        //==================CVV===============================
        cardCVV.setBackground(Color.WHITE);
        cardCVV.putClientProperty("JTextField.placeholderText", "CVV");
        cardCVV.putClientProperty( FlatClientProperties.STYLE, "showRevealButton: true" );
        cardCVV.setForeground(Color.BLACK);
        PlainDocument doc = (PlainDocument) cardCVV.getDocument();
        doc.setDocumentFilter(new CVV_Filter());
        sub1.add(cardCVV);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 4;
        c.weightx = 1;
        c.insets = new Insets(0,20,10,20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = GridBagConstraints.REMAINDER;
        sub1.setBackground(Color.WHITE);
        panel.add(sub1,c);

        //=======================================Sub2 panel================================
        cardPIN.setBackground(Color.WHITE);
        cardPIN.setForeground(Color.BLACK);
        cardPIN.putClientProperty("JTextField.placeholderText", "Card PIN");
        cardPIN.putClientProperty( FlatClientProperties.STYLE, "showRevealButton: true" );
        // add filter
        PlainDocument doc1 = (PlainDocument) cardPIN.getDocument();
        doc1.setDocumentFilter(new PIN_Filter());
        sub2.add(cardPIN);

        //==================ZIP===============================
        cardZipCode.setBackground(Color.WHITE);
        cardZipCode.setForeground(Color.BLACK);
        cardZipCode.putClientProperty("JTextField.placeholderText", "Zip code");
        PlainDocument doc2 =  (PlainDocument) cardZipCode.getDocument();
        doc2.setDocumentFilter(new ZIP_Filter());
        sub2.add(cardZipCode);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 5;
        c.weightx = 1;
        c.insets = new Insets(0,20,10,20);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = GridBagConstraints.REMAINDER;
        sub2.setBackground(Color.WHITE);
        panel.add(sub2,c);



        //================================== Ok btn =====================================
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 6;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.insets = new Insets(0,20,10,20);
        okButton.putClientProperty("JButton.buttonType", "roundRect");
        okButton.setForeground(Color.WHITE);
        okButton.setBackground(Color.BLACK);

        // Add an action listener to the OK button
        okButton.addActionListener(e -> {
                if(titleField.getText().isEmpty()
                        || cardHolder.getText().isEmpty()
                        || cardNumber.getText().isEmpty()
                        || cardExpDate.getText().isEmpty()
                        || String.valueOf(cardCVV.getPassword()).isEmpty()
                        || String.valueOf(cardPIN.getPassword()).isEmpty()
                        || cardZipCode.getText().isEmpty()){
                    errorMsg.setText("*Error: One of the filed is empty.");
                    errorMsg.setVisible(true);
                }else if(!this.validateExpireFormat(cardExpDate.getText())){
                    errorMsg.setText("*Please follow format MM/yy for date");
                    errorMsg.setVisible(true);
                }else if (String.valueOf(cardCVV.getPassword()).length() <3){
                    errorMsg.setText("*Please enter 3 numbers for CVV");
                    errorMsg.setVisible(true);
                }else if (cardZipCode.getText().length() <5){
                    errorMsg.setText("*Please enter 5 numbers for ZIP");
                    errorMsg.setVisible(true);
                }else if (String.valueOf(cardPIN.getPassword()).length() <4){
                    errorMsg.setText("*Please enter 4 numbers for PIN");
                    errorMsg.setVisible(true);
                }
                else{
                    System.out.println("Success");
                    okBtn_response(response);
                    dispose();
                }
        });
        panel.add(okButton,c);


        //================================== Cancel btn =====================================
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 6;
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
        c.gridy = 7;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weightx = 1;
        c.insets = new Insets(0,20,10,20);
        c.fill = GridBagConstraints.HORIZONTAL;
        errorMsg.setVisible(false);
        errorMsg.setForeground(Color.RED);
        panel.add(errorMsg,c);


        //================================== End =====================================
        panel.setBorder( new EmptyBorder(10,10,10,10)); //top left bottom right
        panel.setBackground(Color.WHITE);
        this.add(panel);

        // Center dialog to screen
        this.setLocationRelativeTo(null);
    }

    private void okBtn_response(JDialogCreditCardResponse response){
        //Get the fields and assign it to the obj to return
//        PasswordInformation pw = new PasswordInformation();
//        pw.setTitle(titleField.getText());
//        pw.setUsername(username.getText());
//        pw.setPassword(String.valueOf(password.getPassword()));
//
//        response.getResponse(pw);
    }
    private  boolean validateExpireFormat(String expiryDate) {
        return expiryDate.matches("(?:0[1-9]|1[0-2])/[0-9]{2}");
    }
}

//TODO Make this more robust instead of using 3 different methods

// 3 Digits
class CVV_Filter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string,
                             AttributeSet attr) throws BadLocationException {

        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.insert(offset, string);
        if (test(sb.toString())) {
            super.insertString(fb, offset, string, attr);
        }
    }

    private boolean test(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text,
                        AttributeSet attrs) throws BadLocationException {
        String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.replace(offset, offset + length, text);

        if (string.length() <= 3 && test(sb.toString()) ) {
            super.replace(fb, offset, length, text, attrs); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length)
            throws BadLocationException {
        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength())); // get the original text
        sb.delete(offset, offset + length); // remove the text to be deleted

        if(sb.toString().length() == 0){
            super.replace(fb, offset, length, "", null);
        }else{
            if (test(sb.toString())) { // if it is a string then remove it
                super.remove(fb, offset, length);
            }
        }



    }
}

// 4 Digits
class PIN_Filter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string,
                             AttributeSet attr) throws BadLocationException {

        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.insert(offset, string);
        if (test(sb.toString()) && string.length() <= 4) {
            super.insertString(fb, offset, string, attr);
        }
    }

    private boolean test(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text,
                        AttributeSet attrs) throws BadLocationException {
        String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.replace(offset, offset + length, text);

        if (string.length() <= 4 && test(sb.toString()) ) {
            super.replace(fb, offset, length, text, attrs); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length)
            throws BadLocationException {
        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength())); // get the original text
        sb.delete(offset, offset + length); // remove the text to be deleted

        if(sb.toString().length() == 0){
            super.replace(fb, offset, length, "", null);
        }else{
            if (test(sb.toString())) { // if it is a string then remove it
                super.remove(fb, offset, length);
            }
        }



    }
}


// 5 Digits
class ZIP_Filter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string,
                             AttributeSet attr) throws BadLocationException {

        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.insert(offset, string);
        if (test(sb.toString()) && string.length() <= 4) {
            super.insertString(fb, offset, string, attr);
        }
    }

    private boolean test(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text,
                        AttributeSet attrs) throws BadLocationException {
        String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.replace(offset, offset + length, text);

        if (string.length() <= 5 && test(sb.toString()) ) {
            super.replace(fb, offset, length, text, attrs); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length)
            throws BadLocationException {
        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength())); // get the original text
        sb.delete(offset, offset + length); // remove the text to be deleted

        if(sb.toString().length() == 0){
            super.replace(fb, offset, length, "", null);
        }else{
            if (test(sb.toString())) { // if it is a string then remove it
                super.remove(fb, offset, length);
            }
        }



    }
}



