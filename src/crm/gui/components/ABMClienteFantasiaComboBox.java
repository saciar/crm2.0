package crm.gui.components;

import java.util.Vector;

import crm.client.managers.ClienteManager;
import crm.libraries.abm.entities.Cliente;

public class ABMClienteFantasiaComboBox extends ABMComboBox{
	public ABMClienteFantasiaComboBox(){
		super(200, 20);
		m_codigoForeign = new Vector();
	}
	
	public void loadItems(){	
		
		try{			
			//Cliente[] clientes = manager.getAllClientes();
			Cliente[] clientes = ClienteManager.instance().getClientesReport();
			System.out.println("**** "+clientes.length+" clientes encontrados");
			resetFields();
			
			for (int i = 0; i < clientes.length; i++) {				
				this.addItem(clientes[i].getNombreFantasia());
				m_codigoForeign.add(clientes[i].getCodigo());
				
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
			this.addItem(new String("Seleccione un cliente"));
		}
	}
}
