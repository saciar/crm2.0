package crm.gui.components;

import java.util.Vector;

import crm.client.managers.VendedorManager;
import crm.libraries.abm.entities.Vendedor;
import crm.services.sei.CategVendedorManagerSEI;

public class ABMContactoComisionableComboBox extends ABMComboBox{
	public ABMContactoComisionableComboBox() {
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	public void loadItems(){	
		
		try{			

			Vendedor[] vendedores = VendedorManager.instance().findByField("categoria",CategVendedorManagerSEI.CATEGORY_REFERENCIA);

			resetFields();
			
			for (int i = 0; i < vendedores.length; i++) {				
				this.addItem(vendedores[i].getApellidoYNombre());
				m_codigoForeign.add(vendedores[i].getCodigo());
				
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
			this.addItem(new String("Seleccione Referencia"));
		}
	}	
}
