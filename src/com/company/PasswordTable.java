package com.company;

import com.company.FormPanels.PasswordPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PasswordTable extends JPanel {

    private JTable table;
    private JLabel label;
    private JButton button;
    private JScrollPane scrollPane;
    private JPanel sub1;
    private List<PasswordInformation> passwords;
    DefaultTableModel model;

    public PasswordTable(){
//        this.passwords = passwords;
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new GridBagLayout());
        table = new JTable();
        label = new JLabel("Items");
        button = new JButton("Add new item");
        sub1 = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Sub1
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        sub1.setBackground(Color.WHITE);
        this.add(sub1,c);
        //============================Label===============================
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        label.setForeground(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD, 22);
        label.setFont(font);
        sub1.add(label,c);

        //==========================Button===================================
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.anchor = GridBagConstraints.EAST;

        button.setForeground(Color.WHITE);
        button.putClientProperty("JButton.buttonType", "roundRect");
        button.setBackground(Color.BLACK);
        sub1.add(button,c);
        button.addActionListener(e -> {
            // Get Password Dialog
            PasswordDialog pd = new PasswordDialog(new JDialogPasswordResponseImpl());
            pd.pack(); // to resize
            pd.setVisible(true);
        });


        //====================================Table=========================================
        model = new DefaultTableModel();
        model.addColumn("Title");
        model.addColumn("Username");
        model.addColumn("Password");
        table.setModel(model);


        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        scrollPane = new JScrollPane(table);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        this.setBackground(Color.WHITE);
        this.add(scrollPane,c);

        List<PasswordInformation> list = JDBC.queryLoginFromDB();
        for(PasswordInformation pw : list){
            model.addRow(new Object[]{
                    pw.getTitle(),
                    pw.getUsername(),
                    pw.getPassword(),
            });
        }
    }

    private void fillDataToTable(PasswordInformation pw){
        model.addRow(new Object[]{
                pw.getTitle(),
                pw.getUsername(),
                pw.getPassword(),
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
            fillDataToTable(pw);
        }
    }
}
