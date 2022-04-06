package com.company.FormPanels;

import javax.swing.*;
import java.awt.*;

public class TrashPanel extends JPanel {
    private JLabel title;
    public TrashPanel(){
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();



        title = new JLabel();
        title.setForeground(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD,22);
        title.setFont(font);

//        title.setVerticalTextPosition(SwingConstants.BOTTOM);
//        title.setHorizontalTextPosition(SwingConstants.CENTER);
        title.setIcon(new ImageIcon("src/com/company/Icons/empty-trash.png"));
        title.setText("Trash is Empty");
        title.setMinimumSize(new Dimension(900, 500));// to have consistence size in layout
        c.gridx = 0;
        c.gridy = 0;
        this.add(title,c);
        this.setBackground(new Color(255,255,255));
    }
}
