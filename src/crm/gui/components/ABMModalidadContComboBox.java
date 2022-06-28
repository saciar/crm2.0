package crm.gui.components;

import java.util.Vector;

import crm.client.managers.ModalidadContratManager;
import crm.libraries.abm.entities.ModalidadContrat;

public class ABMModalidadContComboBox extends ABMComboBox{
	public ABMModalidadContComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	
	
	public void loadItems(){	
		
		try{			
			ModalidadContrat[] modalidadContrat = ModalidadContratManager.instance().getAllModalidadContrats();

			resetFields();
			
			for (int i = 0; i < modalidadContrat.length; i++) {				
				this.addItem(modalidadContrat[i].getDescripcion());
				m_codigoForeign.add(modalidadContrat[i].getCodigo());
				
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
			this.addItem(new String("Seleccione una Modalidad"));
		}
	}

}
