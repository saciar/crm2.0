package crm.gui.tablerenderer.cobranzas;

import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

import crm.gui.Main;

public class TieneAgendaCellRender extends JLabel implements TableCellRenderer {
	
	Border unselectedBorder = null;
    Border selectedBorder = new BevelBorder(BevelBorder.RAISED);
    CobranzasItem itemSeleccionado;
    
    public TieneAgendaCellRender(){
    	setOpaque(true);
    	setHorizontalAlignment(JLabel.CENTER);
    }
	
	public Component getTableCellRendererComponent(JTable table, Object value,
									boolean isSelected, boolean hasFocus, 
									int row, int col) {		
		if (value != null){
			if((Integer)value==1){				

					URL url =Main.class.getResource("imagenes/report.png");
					setIcon(new javax.swing.ImageIcon(url));

				setToolTipText("Con Agenda");
			}
			else {
				setIcon(null);
				setToolTipText("Sin Agenda");
			}
		}
		return this;
	}

}
