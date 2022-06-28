package crm.gui.components;

import java.util.Vector;

import crm.client.managers.PerfilManager;
import crm.libraries.abm.entities.Perfil;

public class ABMPerfilesComboBox extends ABMComboBox{
	public ABMPerfilesComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();	
	}
	
	
	public void loadItems(){	
		
		try{			

			Perfil[] perfiles = PerfilManager.instance().getAllPerfiles();

			resetFields();
			
			for (int i = 0; i < perfiles.length; i++) {				
				this.addItem(perfiles[i].getDescripcion());
				m_codigoForeign.add(perfiles[i].getCodigo());
				
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
			this.addItem(new String("Seleccione un Perfil"));
		}
	}
	
}
