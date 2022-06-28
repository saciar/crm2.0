package crm.gui.components;

import java.util.Vector;

import crm.client.managers.UnidadNegocioManager;
import crm.libraries.abm.entities.UnidadNegocio;
public class ABMUnidadNegocioComboBox extends ABMComboBox{
	
	public ABMUnidadNegocioComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();			
	}
	
	
	public void loadItems(){	
		
		try{			

			UnidadNegocio[] unidadNegocios = UnidadNegocioManager.instance().getAllUnidadNegocios();

			resetFields();
			
			for (int i = 0; i < unidadNegocios.length; i++) {				
				this.addItem(unidadNegocios[i].getDescripcion());
				m_codigoForeign.add(unidadNegocios[i].getCodigo());
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
			this.addItem(new String("Seleccione una Unidad de Negocio"));
		}
	}
		
}
