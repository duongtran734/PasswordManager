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
    private MenuPanel menuPanel;
    private JPanel contentPanel;



    public Main(){
        this.setTitle("My Title");
        this.setSize(420,420);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();

    }

    private void initComponents(){
        panel = new JPanel();
        contentPanel = new JPanel(new GridBagLayout());
        menuPanel = new MenuPanel(this);
        GridBagConstraints c = new GridBagConstraints();

        // Entire window panel
        panel.setLayout(new GridBagLayout());

        //content panel
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.weighty=1;
        c.weightx = 1;
        contentPanel.setBackground(new Color(255,255,255));
        panel.add(contentPanel,c);

        //menu panel
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.1;
        c.weighty=1;
        panel.add(menuPanel,c);




        Image img = Toolkit.getDefaultToolkit().getImage("src/com/company/Icons/favicon-16x16.png");
        this.setTitle("Password Manager");
        this.setIconImage(img);
        this.add(panel);
        this.setMinimumSize(new Dimension( 1180, 600));
        this.setPreferredSize(new Dimension( 1180, 600));
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public void showForm(Component com){
        contentPanel.removeAll();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.weighty=1;
        c.weightx = 1;
        contentPanel.setBackground(new Color(255,255,255));
        contentPanel.add(com,c);
        contentPanel.repaint();
        contentPanel.revalidate();
    }


    public static void main(String[] args){
        FlatLightLaf.setup();
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