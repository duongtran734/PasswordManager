package com.company;

import javax.swing.*;
import java.awt.*;

public class TrashPanel extends JPanel {
    private JLabel title;
    public TrashPanel(){
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new GridBagLayout());
        title = new JLabel();
        title.setForeground(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD,22);
        title.setFont(font);

        title.setVerticalTextPosition(SwingConstants.BOTTOM);
        title.setHorizontalTextPosition(SwingConstants.CENTER);
        title.setIcon(new ImageIcon("src/com/company/Icons/empty-trash.png"));
        title.setText("Trash is Empty");

        this.add(title);
        this.setBackground(new Color(255,255,255));
    }
}
