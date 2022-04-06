package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AllItemsTable extends JPanel{
    private JTable table;
    private JLabel label;
    private JButton button;
    private JScrollPane scrollPane;
    private JPanel sub1;
    DefaultTableModel model;

    public AllItemsTable(){
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new GridBagLayout());
        table = new JTable();
        label = new JLabel("Items");
        button = new JButton("Add new item");
        sub1 = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //====================================Table=========================================
        model = new DefaultTableModel();
        model.addColumn("Title");
        table.setModel(model);


        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        scrollPane = new JScrollPane(table);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        this.setBackground(Color.WHITE);
        this.add(scrollPane,c);

        List<CreditCardInformation> list = JDBC.queryCreditCardFromDB();
        for(CreditCardInformation card : list){
            model.addRow(new Object[]{
                    card.getTitle(),
            });
        }

        List<PasswordInformation> list2 = JDBC.queryLoginFromDB();
        for(PasswordInformation pw : list2){
            model.addRow(new Object[]{
                    pw.getTitle(),
            });
        }

    }
}
