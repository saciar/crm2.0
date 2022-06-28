package crm.gui.tablerenderer.gastosSubcontratados;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

public class ProveedorSubcontratadoCellRenderer extends JLabel implements TableCellRenderer {
	
	Border unselectedBorder = null;
    Border selectedBorder = new BevelBorder(BevelBorder.RAISED);
	
    public ProveedorSubcontratadoCellRenderer(){
    	setOpaque(true);
    	setHorizontalAlignment(JLabel.LEFT);    	
    	//setBackground(Color.WHITE);
    }
	
	public Component getTableCellRendererComponent(JTable table, Object value,
									boolean isSelected, boolean hasFocus, 
									int row, int col) {		
		
		if (isSelected){
			setBackground(table.getSelectionBackground());
			setForeground(Color.WHITE);
		}
		else {
			setBackground(Color.WHITE);
			setForeground(Color.BLACK);
		}
		if(value !=null){
			setText(value.toString());
			setForeground(Color.BLACK);
		}
		else{
			setText("Seleccione un proveedor");
			setForeground(Color.RED);
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
