package com.company;

import com.formdev.flatlaf.ui.FlatButtonUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MenuPanel extends JPanel {
    private JList<MenuModel> menuList;
    private JPanel menuListPanel;
    private JLabel categories;
    private DefaultListModel<MenuModel> model;

    public MenuPanel(){
        model = new DefaultListModel<>();
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


        //======================================Menu Panel================================
        this.setBackground(new Color(55,60,67,255));
        this.add(menuListPanel,c);
    }



}
