package crm.gui.tablerenderer.cobranzas.agenda;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

public class TipoCellRenderer extends JLabel implements TableCellRenderer {
	
	Border unselectedBorder = null;
    Border selectedBorder = new BevelBorder(BevelBorder.RAISED);
	AgendaCobranzasItem itemSeleccionado;
    
    public TipoCellRenderer(){
    	setOpaque(true);
    	setHorizontalAlignment(JLabel.CENTER);
    }
	
	public Component getTableCellRendererComponent(JTable table, Object value,
									boolean isSelected, boolean hasFocus, 
									int row, int col) {		
		if (value != null){
			if((Integer)value==0){				
				try {
					setIcon(  new javax.swing.ImageIcon(
							new java.net.URL("http://200.80.201.51:8888/app_files/bell.png")
					));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				setToolTipText("Con Alarma");
			}
			else if((Integer)value==2){				
				try {
					setIcon(  new javax.swing.ImageIcon(
							new java.net.URL("http://200.80.201.51:8888/app_files/money.png")
					));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				setToolTipText("Cobranza a Confirmar");
			}
			else {
				setIcon(null);
				setToolTipText("Sin Alarma");
			}
		}
		return this;
	}


}
