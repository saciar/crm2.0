package crm.gui.tablerenderer.cobranzas.agenda;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

import crm.gui.custom.DoubleVerifier;

public class MontoCellRender extends JTextField implements TableCellRenderer {
	
	Border unselectedBorder = null;
    Border selectedBorder = new BevelBorder(BevelBorder.RAISED);
	
    public MontoCellRender(){
    	setOpaque(true);
    	setHorizontalAlignment(JCheckBox.CENTER);
    }
	
	public Component getTableCellRendererComponent(JTable table, Object value,
									boolean isSelected, boolean hasFocus, 
									int row, int col) {		
		//setSelected((Boolean)table.getModel().getValueAt(row, col));
		setInputVerifier(new DoubleVerifier());
		if (isSelected){
			setBackground(table.getSelectionBackground());
			setBorder(selectedBorder);
			setToolTipText("Monto a cobrar");
		}
		else {
			setBackground(Color.WHITE);
			setBorder(unselectedBorder);
			setToolTipText("Tarea incompleta");
		}

		return this;
	}

}
