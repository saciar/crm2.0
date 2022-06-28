package crm.gui.tablerenderer.salas.cellsEditors;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;



public class CheckBoxCellRenderer extends JCheckBox implements TableCellRenderer {
	private TablaColoreada tablemodel;

    public CheckBoxCellRenderer(TablaColoreada tablemodel){
    	this.tablemodel = tablemodel;
    }
    
    public Component getTableCellRendererComponent(JTable table,
                                                   Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus,
                                                   int row, int col)
    {
    	setBackground(tablemodel.getColorFondo(row,col));
    	setHorizontalAlignment(JCheckBox.CENTER);
    	setSelected((Boolean)tablemodel.getValueAt(row, col));
    	
    	//setText((String)value);
        //setHorizontalAlignment(JLabel.CENTER);
       /* Color color = Color.black;
        if(hasFocus)
            color = Color.blue;
        else if(isSelected)
            color = Color.green.darker();
        else if(row == specRow && col == specCol)
            color = color.red;
        setForeground(color);
        setText((String)value);*/
        return this;
    }

}