package com.company;

import com.company.FormPanels.AllItemsPanel;
import com.company.FormPanels.CreditCardPanel;
import com.company.FormPanels.PasswordPanel;
import com.company.FormPanels.TrashPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class MenuPanel extends JPanel {
    private JList<MenuModel> menuList;
    private JPanel menuListPanel;
    private JLabel categories;
    private DefaultListModel<MenuModel> model;
    private Main main;

    public MenuPanel(Main main){
        this.model = new DefaultListModel<>();
        this.main = main;
        initData();
        initComponents();
    }

    private void initData(){
        model.addElement(new MenuModel("All items","all", MenuModel.MenuType.MENU));
        model.addElement(new MenuModel("Passwords","password", MenuModel.MenuType.MENU));
        model.addElement(new MenuModel("Credit cards","credit_card", MenuModel.MenuType.MENU));
        model.addElement(new MenuModel("Trash","trash", MenuModel.MenuType.MENU));
    }

    private void initComponents() {
        this.setLayout(new GridBagLayout());
        menuListPanel = new JPanel(new BorderLayout());
        categories = new JLabel();
        menuList = new JList<>(model);
        add(new JScrollPane(menuList));

        //=============================Categories====================================
        categories.setText("Categories");
        categories.setBorder(new EmptyBorder(10,5,0,5));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        this.add(categories,c);




        //================================Categories items==============================
        menuList.setCellRenderer(new MenuItemRenderer());
        menuList.setBackground(new Color(55,60,67,255));
        menuListPanel.add(menuList);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.weighty=1;
        c.weightx = 1;

        //=======================Action Click============================================
        menuList.getSelectionModel().addListSelectionListener(e->{
            MenuModel item = menuList.getSelectedValue();
            if(item.getName().equalsIgnoreCase("all items")){
                if(JDBC.queryLoginFromDB().size() + JDBC.queryCreditCardFromDB().size() >0){
                    main.showForm(new AllItemsTable());
                }else{
                    main.showForm(new AllItemsPanel());
                }
            }else if(item.getName().equalsIgnoreCase("passwords")){
                if(JDBC.queryLoginFromDB().size() > 0){
                    main.showForm(new PasswordTable());
                }else {
                    main.showForm(new PasswordPanel(main));
                }
            }else if(item.getName().equalsIgnoreCase("credit cards")){
                if(JDBC.queryCreditCardFromDB().size() > 0){

                    main.showForm(new CreditCardTable());
                }else{
                    main.showForm(new CreditCardPanel(main));
                }

            }else if(item.getName().equalsIgnoreCase("trash")){
                main.showForm(new TrashPanel());
            }
        });

        //======================================Menu Panel================================
        this.setBackground(new Color(55,60,67,255));
        this.add(menuListPanel,c);


    }




}
