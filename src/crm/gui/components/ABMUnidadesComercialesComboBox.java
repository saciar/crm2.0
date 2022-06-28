package crm.gui.components;

import java.util.Vector;

import crm.client.managers.UnidadComercialManager;
import crm.libraries.abm.entities.UnidadComercial;

public class ABMUnidadesComercialesComboBox extends ABMComboBox {
	public ABMUnidadesComercialesComboBox() {
		super(200, 20);
		m_codigoForeign = new Vector();
		resetFields();
	}
	
	
	
	public void loadItems(){	
		
		try{			

			UnidadComercial[] unidadComerciales = UnidadComercialManager.instance().getAllUnidadComerciales();

			resetFields();
			
			for (int i = 0; i < unidadComerciales.length; i++) {				
				this.addItem(unidadComerciales[i].getDescripcion());
				m_codigoForeign.add(unidadComerciales[i].getCodigo());
			}
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void loadItemsForSupervisores(){	
		
		try{			

			UnidadComercial[] unidadComerciales = UnidadComercialManager.instance().getAllUnidadComerciales();

			resetFields();
			
			for (int i = 0; i < unidadComerciales.length; i++) {				
				this.addItem(unidadComerciales[i].getDescripcion());
				m_codigoForeign.add(unidadComerciales[i].getCodigo());
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
			this.addItem(new String("Seleccione una Unidad Comercial"));
		}
	}
}
