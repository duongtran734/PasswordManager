package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

public class MenuItemRenderer extends DefaultListCellRenderer  {

    private JLabel label;
    public MenuItemRenderer(){
        label = new JLabel();
        label.setOpaque(true);
    }
//
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean expanded) {
        MenuModel item = (MenuModel) value;

        label.setText(item.getName());
        label.setIcon(new ImageIcon("src/com/company/Icons/" + item.getIcon() + ".png"));
        Font font = new Font("Courier", Font.BOLD,12);
        label.setFont(font);
        label.setIconTextGap(25);
        // For Padding
        label.setBorder(new EmptyBorder(20,10,20,10));//top,left,bottom,right
        if(isSelected){
            label.setForeground(list.getSelectionForeground());
            label.setBackground(list.getSelectionBackground());
        }else{
            label.setForeground(list.getForeground());
            label.setBackground(list.getBackground());
        }
        return label;
    }






}
