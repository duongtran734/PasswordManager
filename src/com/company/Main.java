package com.company;


import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.ui.FlatDropShadowBorder;
import com.formdev.flatlaf.ui.*;

import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {
    private JButton btn;
    private JPanel panel;
    private JPanel menuPanel;
    private JPanel contentPanel;



    public Main(){
        this.setTitle("My Title");
        this.setSize(420,420);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();

    }

    private void initComponents(){
        panel = new JPanel();
        menuPanel = new JPanel();
        contentPanel = new JPanel();
        GridBagConstraints c = new GridBagConstraints();

        // Entire window panel
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.BLACK);

        //menu panel
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.2;
        c.weighty=1;
        panel.add(menuPanel,c);


        //menu panel
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.weighty=1;
        c.weightx = 1;
        contentPanel.setBackground(new Color(255,255,255));
        panel.add(contentPanel,c);

        Image img = Toolkit.getDefaultToolkit().getImage("src/Icons/favicon-16x16.png");
        this.setTitle("Password Manager");
        this.setIconImage(img);
        this.add(panel);
        this.setPreferredSize(new Dimension( 1660, 880 ));
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }


    public static void main(String[] args){
//        FlatLightLaf.setup();
        try {
            UIManager.setLookAndFeel( new FlatDarkLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}