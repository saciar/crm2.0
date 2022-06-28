package crm.gui.components;

import java.util.Vector;

import crm.client.managers.TipoContactoManager;
import crm.libraries.abm.entities.TipoContacto;

public class ABMTipoContactoComboBox extends ABMComboBox{
	
	public ABMTipoContactoComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	
	public void loadItems(){	
		
		try{			

			TipoContacto[] tipoContactos = TipoContactoManager.instance().getAllTipoContactos();

			resetFields();
			
			for (int i = 0; i < tipoContactos.length; i++) {				
				this.addItem(tipoContactos[i].getDescripcion());
				m_codigoForeign.add(tipoContactos[i].getCodigo());				
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
			this.addItem(new String("Seleccione un Tipo de Contacto"));
		}
	}	
		
}
