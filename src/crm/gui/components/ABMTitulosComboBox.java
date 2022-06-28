package crm.gui.components;

import java.util.Vector;

import crm.client.managers.TituloManager;
import crm.libraries.abm.entities.Titulo;
public class ABMTitulosComboBox extends ABMComboBox{
	
	public ABMTitulosComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	
	public void loadItems(){	
		
		try{			

			Titulo[] titulos = TituloManager.instance().getAllTitulos();

			resetFields();
			
			for (int i = 0; i < titulos.length; i++) {				
				this.addItem(titulos[i].getDescripcion());
				m_codigoForeign.add(titulos[i].getCodigo());
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
			this.addItem(new String("Seleccione un Titulo"));
		}
	}	
		
}
