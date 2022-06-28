package crm.gui.components;

import java.util.Vector;

import crm.client.managers.IdiomaManager;
import crm.libraries.abm.entities.Idioma;

public class ABMIdiomasComboBox extends ABMComboBox{
	public ABMIdiomasComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	
	
	public void loadItems(){	
		
		try{			

			Idioma[] idiomas = IdiomaManager.instance().getAllIdiomas();

			resetFields();
			
			for (int i = 0; i < idiomas.length; i++) {				
				this.addItem(idiomas[i].getDescripcion());
				m_codigoForeign.add(idiomas[i].getCodigo());
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
			this.addItem(new String("Seleccione un Idioma"));
		}
	}
}
