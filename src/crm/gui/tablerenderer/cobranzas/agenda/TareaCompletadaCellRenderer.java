package crm.gui.tablerenderer.cobranzas.agenda;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

public class TareaCompletadaCellRenderer extends JCheckBox implements TableCellRenderer {
	
	Border unselectedBorder = null;
    Border selectedBorder = new BevelBorder(BevelBorder.RAISED);
	
    public TareaCompletadaCellRenderer(){
    	setOpaque(true);
    	setHorizontalAlignment(JCheckBox.CENTER);
    }
	
	public Component getTableCellRendererComponent(JTable table, Object value,
									boolean isSelected, boolean hasFocus, 
									int row, int col) {		
		setSelected((Boolean)table.getModel().getValueAt(row, col));		
		if (isSelected){
			setBackground(table.getSelectionBackground());
			setBorder(selectedBorder);
			setToolTipText("Tarea Completada");
		}
		else {
			setBackground(Color.WHITE);
			setBorder(unselectedBorder);
			setToolTipText("Tarea incompleta");
		}

		return this;
	}
}
