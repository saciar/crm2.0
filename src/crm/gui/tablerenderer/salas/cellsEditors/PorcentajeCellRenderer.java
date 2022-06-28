package crm.gui.tablerenderer.salas.cellsEditors;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

public class PorcentajeCellRenderer extends JLabel implements TableCellRenderer {
	
	Border unselectedBorder = null;
    Border selectedBorder = new BevelBorder(BevelBorder.RAISED);
	
    public PorcentajeCellRenderer(){
    	setOpaque(true);
    	setHorizontalAlignment(RIGHT);
    	//setBackground(Color.WHITE);
    }
	
	public Component getTableCellRendererComponent(JTable table, Object value,
									boolean isSelected, boolean hasFocus, 
									int row, int col) {
		if(table !=null){
		if(value != null){
		setToolTipText(table.getColumnName(col)+": "+value.toString());
		//setFont(table.getFont());
		setText(value.toString()+" %");	}	
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
		// setHorizontalAlignment(JLabel.CENTER);
		/*
		 * Color color = Color.black; if(hasFocus) color = Color.blue; else
		 * if(isSelected) color = Color.green.darker(); else if(row == specRow &&
		 * col == specCol) color = color.red; setForeground(color);
		 * setText((String)value);
		 */
		return this;
	}

}
