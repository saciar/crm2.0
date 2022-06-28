package crm.gui.components;

import java.util.Vector;

import crm.client.managers.CategVendedorManager;
import crm.libraries.abm.entities.CategVendedor;

public class ABMCategVendComboBox extends ABMComboBox {
	public ABMCategVendComboBox() {
		super(200, 20);
		m_codigoForeign = new Vector();
	}
	
	
	public void loadItems(){	
		
		try{			

			CategVendedor[] categVendedores = CategVendedorManager.instance().getAllCategVendedores();

			resetFields();
			
			for (int i = 0; i < categVendedores.length; i++) {				
				this.addItem(categVendedores[i].getDescripcion());
				m_codigoForeign.add(categVendedores[i].getCodigo());
				
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
			this.addItem(new String("Seleccione una Categoria"));
		}
	}
}
