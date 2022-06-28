package crm.gui.tablerenderer.salas.cellsEditors;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

public class SubcontratadoCellRenderer extends JCheckBox implements TableCellRenderer {
	
	Border unselectedBorder = null;
    Border selectedBorder = new BevelBorder(BevelBorder.RAISED);
	
    public SubcontratadoCellRenderer(){
    	setOpaque(true);
    	setHorizontalAlignment(JCheckBox.CENTER);    	
    	//setBackground(Color.WHITE);
    }
	
	public Component getTableCellRendererComponent(JTable table, Object value,
									boolean isSelected, boolean hasFocus, 
									int row, int col) {		
		setSelected((Boolean)table.getModel().getValueAt(row, col));		
		if (isSelected){
			setBackground(table.getSelectionBackground());
			setBorder(selectedBorder);
			setToolTipText("Servicio subcontratado");
		}
		else {
			setBackground(Color.WHITE);
			setBorder(unselectedBorder);
			setToolTipText("Servicio propio");
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
