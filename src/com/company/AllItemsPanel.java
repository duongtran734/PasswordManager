package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AllItemsPanel extends JPanel {
    private JLabel title;
    private JPanel icons;
    JLabel icon1Label;
    JLabel icon2Label;
    JLabel icon3Label;

    public AllItemsPanel(){
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

        //================Icons panel==================================================
        ImageIcon gmail = new ImageIcon("src/com/company/Icons/gmail.png");
        ImageIcon card_all = new ImageIcon("src/com/company/Icons/card_all.png");
        ImageIcon bitcoin = new ImageIcon("src/com/company/Icons/bitcoin.png");
        icon1Label.setIcon(gmail);
        icon2Label.setIcon(card_all);
        icon3Label.setIcon(bitcoin);
        icons.add(icon1Label);
        icons.add(icon2Label);
        icons.add(icon3Label);
        icons.setBackground(new Color(255,255,255));
        c.gridx = 0;
        c.gridy = 0;
        icons.setBorder(new EmptyBorder(0,0,20,0));//top,left,bottom,right
        this.add(icons,c);


        //=================Message====================================================
        title.setForeground(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD,22);
        title.setFont(font);
        title.setText("No items found");
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        this.add(title,c);
        this.setBackground(new Color(255,255,255));
    }
}
