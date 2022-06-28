package crm.gui.components;

import java.util.Vector;

import crm.client.managers.ProveedorManager;
import crm.libraries.abm.entities.Proveedor;

public class ABMProveedoresComboBox extends ABMComboBox{
	public ABMProveedoresComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();	
		this.resetFields();
	}
	
	
	public void loadItems(){	
		try{			
			
			Proveedor[] proveedores = ProveedorManager.instance().getAllProveedores();

			resetFields();
			
			for (int i = 0; i < proveedores.length; i++) {				
				this.addItem(proveedores[i].getNombre());
				m_codigoForeign.add(proveedores[i].getCodigo());
				
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
			this.addItem(new String("Seleccione un Proveedor"));
		}
	}
	
}
