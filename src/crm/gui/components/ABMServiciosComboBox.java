package crm.gui.components;

import java.util.Vector;

import crm.client.managers.IdiomaManager;
import crm.client.managers.ServicioIdiomaManager;
import crm.client.managers.ServicioManager;
import crm.client.managers.VistaFamiliaServicioIdiomaManager;
import crm.libraries.abm.entities.Servicio;
import crm.libraries.abm.entities.ServicioIdioma;

public class ABMServiciosComboBox extends ABMComboBox{
	public ABMServiciosComboBox(){
		super(400,20);
		m_codigoForeign = new Vector();
		resetFields();
	}
	
	public void loadItemsForFamily (String codFamServicio){
		try{			
			resetFields();
			
			Object[] servicios = ServicioManager.instance().getServiciosByFamiliaAndPlaceReport(codFamServicio);

			
			for (int i = 0; i < servicios.length; i++) {
				Object[] row = (Object[])servicios[i];
				
				String cod = row[0].toString();
				
				addItem(row[1].toString());
				m_codigoForeign.add(cod);
			}

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	public void loadItems(){			
		try{
			addItem(new String("Seleccione un servicio"));
			Object[] servicios = ServicioManager.instance().getAllServiciosReport();			
			for (int i = 0; i < servicios.length; i++) {
				
				Object[] row = (Object[])servicios[i];
				
				String cod = row[0].toString();
				
				addItem(row[1].toString());
				m_codigoForeign.add(cod);								
			}
			
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	*/
	public void resetFields(){
		m_codigoForeign.clear();
		removeAllItems();	
		//if(this.getItemCount() >= 0){
		addItem(new String("Seleccione un servicio"));
		setForeign(null);
		//}
	}
}
