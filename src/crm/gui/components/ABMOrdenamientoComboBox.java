package crm.gui.components;

import javax.swing.JComboBox;

public class ABMOrdenamientoComboBox extends JComboBox{
	public final static String ORDEN_X_NRO_PPTO = "";
	
	public ABMOrdenamientoComboBox(){
		super();
		setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Numero de Ppto", "Cliente", 
				"Lugar de evento", "Nombre de evento", "Fecha de creación", "Fecha de inicio", "Fecha de finalización" }));
	}
	
	public String getItem(){
		int itemId = getSelectedIndex();
		switch (itemId) {
		case 0:
			return "numeroPresupuesto";
		case 1:
			return "nombre_fantasia";		
		case 2:
			return "lugarEvento";
		case 3:
			return "nombreEvento";
		case 4:
			return "fechaCreacion";		
		case 5:
			return "fechaInicialEvento";
		case 6:
			return "fechaFinalEvento";		
		default:
			return "";
		}
	}
}
