package crm.gui.components;

import java.util.Vector;

import crm.client.managers.ComisionManager;
import crm.libraries.abm.entities.Comision;

public class ABMComisionesComboBox extends ABMComboBox {
	public ABMComisionesComboBox() {
		super(200, 20);
		m_codigoForeign = new Vector();
	}
	
	
	
	public void loadItems(){	
		
		try{			

			ComisionManager manager = new ComisionManager();
			Comision[] comisiones = manager.getAllComisiones();

			resetFields();
			
			for (int i = 0; i < comisiones.length; i++) {				
				this.addItem(comisiones[i].getPorcentaje());
				m_codigoForeign.add(comisiones[i].getCodigo());
				
			}
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void resetFields(){
		this.m_codigoForeign.clear();
		this.removeAllItems();	
		if(this.getItemCount() >= 0){
			this.addItem(new String("Seleccione una Comision"));
		}
	}
}
