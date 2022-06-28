package crm.gui.tablerenderer.salas.cellsEditors;

import java.awt.Color;
import java.awt.Component;
import java.util.EventObject;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

public class ComboBoxCellRenderer extends JComboBox implements TableCellRenderer {
	
	Border unselectedBorder = null;
    Border selectedBorder = new BevelBorder(BevelBorder.RAISED);
	
    public ComboBoxCellRenderer(){
    	super();
    	//setOpaque(true);

    }
	
	public Component getTableCellRendererComponent(JTable table, Object value,
									boolean isSelected, boolean hasFocus, 
									int row, int col) {
		if(table !=null){
			if(value != null){
				setToolTipText(table.getColumnName(col)+": "+value.toString());

				setSelectedItem(value);	
				}	
			if (isSelected){
				setForeground(Color.BLACK);
				setBackground(table.getSelectionBackground());
				setBorder(selectedBorder);			
			}
			else {
				setBackground(Color.WHITE);
				setBorder(unselectedBorder);
				setForeground(Color.BLACK);
			}
		}

		return this;
	}

}
