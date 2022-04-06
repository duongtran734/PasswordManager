package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class CreditCardTable extends JPanel {

    private JTable table;
    private JLabel label;
    private JButton button;
    private JScrollPane scrollPane;
    private JPanel sub1;
    DefaultTableModel model;

    public CreditCardTable(){
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
            CreditCardDialog pd = new CreditCardDialog(new CreditCardTable.JDialogCreditCardResponseImpl());
            pd.pack(); // to resize
            pd.setVisible(true);
        });


        //====================================Table=========================================
        model = new DefaultTableModel();
        model.addColumn("Title");
        model.addColumn("Holder");
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

        List<CreditCardInformation> list = JDBC.queryCreditCardFromDB();
        for(CreditCardInformation card : list){
            model.addRow(new Object[]{
                    card.getTitle(),
                    card.getCardName(),
            });
        }
    }

    private void fillDataToTable(CreditCardInformation card){
        model.addRow(new Object[]{
                card.getTitle(),
                card.getCardName(),
        });
    }

    private class JDialogCreditCardResponseImpl implements CreditCardDialog.JDialogCreditCardResponse {
        @Override
        public void getResponse(CreditCardInformation card)
        {
            System.out.println(card.toString());
            try {
                JDBC.insertToCreditCard(card);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            fillDataToTable(card);
        }
    }

}
