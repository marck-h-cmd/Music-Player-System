/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.colorCellTable;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author jeanm
 */
public class ColorInterleavedCell extends DefaultTableCellRenderer{
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column){
        super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
        if(evaluarPar(row)){
            this.setBackground(Color.LIGHT_GRAY);
            this.setForeground(Color.white);
        }else{
            this.setBackground(Color.cyan);
            this.setForeground(Color.WHITE);
        }
        return this;
    }

    public boolean evaluarPar(int numero) {
        return (numero%2==0);
    }
    
    public final void HeaderTableColor(JTable table) {
        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(Color.BLUE); 
                c.setForeground(Color.YELLOW);   
                c.setFont(new Font("Times New Roman", Font.BOLD, 20)); 
                return c;
            }
        });
    }
}
