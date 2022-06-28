package crm.gui.components;

import java.util.Vector;

import crm.client.managers.SucursalManager;
import crm.libraries.abm.entities.Sucursal;

public class ABMSucursalesComboBox extends ABMComboBox{
	public ABMSucursalesComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	
	
	public void loadItems(){	
		
		try{			

			Sucursal[] sucursales = SucursalManager.instance().getAllSucursales();

			resetFields();
			
			for (int i = 0; i < sucursales.length; i++) {				
				this.addItem(sucursales[i].getDescripcion());
				m_codigoForeign.add(sucursales[i].getCodigo());				
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
			this.addItem(new String("Seleccione una Sucursal"));
		}
	}
}
