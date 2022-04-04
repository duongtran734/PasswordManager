package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CreditCardPanel extends JPanel {
    private JLabel title;
    private JPanel icons;
    private JLabel icon1Label;
    private JLabel icon2Label;
    private JLabel icon3Label;
    public CreditCardPanel() {
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new GridBagLayout());
        title = new JLabel();
        icons = new JPanel();
        icon1Label = new JLabel();
        icon2Label = new JLabel();
        icon3Label = new JLabel();
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
        c.gridx = 0;
        c.gridy = 0;
        icons.setBackground(new Color(255,255,255));
        icons.setBorder(new EmptyBorder(0,0,20,0));//top,left,bottom,right
        this.add(icons,c);


        //========================== Message =========================
        title.setForeground(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD,22);
        title.setFont(font);
        title.setText("No credit cards found");

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        this.add(title,c);

        this.setBackground(new Color(255,255,255));
    }
}
