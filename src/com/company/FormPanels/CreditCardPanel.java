package com.company.FormPanels;

import com.company.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;


public class CreditCardPanel extends JPanel  {
    private JLabel title;
    private JPanel icons;
    private JLabel icon1Label;
    private JLabel icon2Label;
    private JLabel icon3Label;
    private JButton addBtn;
    private Main main;

    public CreditCardPanel(Main main) {
        this.main = main;
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new GridBagLayout());
        title = new JLabel();
        icons = new JPanel();
        icon1Label = new JLabel();
        icon2Label = new JLabel();
        icon3Label = new JLabel();
        addBtn = new JButton();
        GridBagConstraints c = new GridBagConstraints();

        //=============================Icons===============================
        ImageIcon visa = new ImageIcon("src/com/company/Icons/visa.png");
        ImageIcon masterCard = new ImageIcon("src/com/company/Icons/mastercard.png");
        ImageIcon amex = new ImageIcon("src/com/company/Icons/amex.png");
        icon1Label.setIcon(visa);
        icon2Label.setIcon(masterCard);
        icon3Label.setIcon(amex);
        icons.add(icon1Label);
        icons.add(icon2Label);
        icons.add(icon3Label);
        icons.setMinimumSize(new Dimension(1000, 500));// to have consistence size in layout
        c.gridx = 0;
        c.gridy = 0;
        icons.setBackground(new Color(255, 255, 255));
        icons.setBorder(new EmptyBorder(0, 0, 20, 0));//top,left,bottom,right
        this.add(icons, c);


        //========================== Message =========================
        title.setForeground(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD, 22);
        title.setFont(font);
        title.setText("No credit cards found");
        title.setBorder(new EmptyBorder(0, 0, 20, 0));//top,left,bottom,right
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        this.add(title, c);
        this.setBackground(new Color(255, 255, 255));

        //======================Add Button============================
        font = new Font("Arial", Font.BOLD, 18);
        addBtn.setFont(font);
        addBtn.setForeground(Color.WHITE);
        addBtn.setText("Add Credit Card");
        addBtn.putClientProperty("JButton.buttonType", "roundRect");
        addBtn.setBackground(Color.BLACK);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        this.add(addBtn, c);


        addBtn.addActionListener(e -> {
            CreditCardDialog cd = new CreditCardDialog(new JDialogCreditCardResponseImpl());
            cd.pack();
            cd.setVisible(true);
//            System.out.println(students.size());
        });
    }

    private class JDialogCreditCardResponseImpl implements CreditCardDialog.JDialogCreditCardResponse {

        @Override
        public void getResponse(CreditCardInformation creditCard) {
            System.out.println(creditCard.toString());
            try {
                JDBC.insertToCreditCard(creditCard);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            main.showForm(new CreditCardTable());
        }
    }


}
