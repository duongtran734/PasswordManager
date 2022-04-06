package com.company.FormPanels;

import com.company.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PasswordPanel extends JPanel {
    private JLabel title;
    private JPanel icons;
    private JLabel icon1Label;
    private JLabel icon2Label;
    private JLabel icon3Label;
    private JButton addBtn;
    private JTable table;
    private JScrollPane scrollPane;
    private Main main;

    public PasswordPanel(Main main){
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
        table = new JTable();
        scrollPane = new JScrollPane();
        GridBagConstraints c = new GridBagConstraints();
        //================Icons panel==================================================
        ImageIcon fb = new ImageIcon("src/com/company/Icons/facebook.png");
        ImageIcon insta = new ImageIcon("src/com/company/Icons/instagram.png");
        ImageIcon paypal = new ImageIcon("src/com/company/Icons/paypal.png");
        icon1Label.setIcon(fb);
        icon2Label.setIcon(insta);
        icon3Label.setIcon(paypal);
        icons.add(icon1Label);
        icons.add(icon2Label);
        icons.add(icon3Label);
        icons.setMinimumSize(new Dimension(1000, 500));// to have consistence size in layout
        icons.setBackground(new Color(255, 255, 255));
        c.gridx = 0;
        c.gridy = 0;
        icons.setBorder(new EmptyBorder(0, 0, 20, 0));//top,left,bottom,right
        this.add(icons, c);


        //=================Message====================================================
        title.setForeground(Color.BLACK);
        title.setBorder(new EmptyBorder(0, 0, 20, 0));//top,left,bottom,right
        Font font = new Font("Arial", Font.BOLD, 22);
        title.setFont(font);
        title.setText("No passwords found");
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        this.add(title, c);
        this.setBackground(new Color(255, 255, 255));


        //======================Add Button============================
        font = new Font("Arial", Font.BOLD, 18);
        addBtn.setFont(font);
        addBtn.setForeground(Color.WHITE);
        addBtn.setText("Add Password");
        addBtn.putClientProperty("JButton.buttonType", "roundRect");
        addBtn.setBackground(Color.BLACK);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        this.add(addBtn, c);

        addBtn.addActionListener(e -> {
            // Get Password Dialog
            PasswordDialog pd = new PasswordDialog(new JDialogPasswordResponseImpl());
            pd.pack(); // to resize
            pd.setVisible(true);
        });
    }

    private class JDialogPasswordResponseImpl implements PasswordDialog.JDialogPasswordResponse {

        @Override
        public void getResponse(PasswordInformation pw)
        {
            System.out.println(pw.toString());
            try {
                JDBC.insertToLogin(pw);
            } catch (SQLException e) {
                e.printStackTrace();
            }
//            showTable();
            main.showForm(new PasswordTable());
        }
    }
}
