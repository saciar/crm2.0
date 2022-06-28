package crm.gui.components;

import java.util.Vector;

import crm.client.managers.LugarEventoContactoManager;
import crm.client.util.Util;
import crm.gui.Main;
import crm.libraries.abm.entities.LugarEventoContacto;

public class ABMLugarEventoContactoComboBox extends ABMComboBox{
	public ABMLugarEventoContactoComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	public void loadItemsForCliente (String codLugar){
		try{	
			resetFields();
			
			Object[] lugar = LugarEventoContactoManager.instance().getLugarContactoByClienteCodeReport(codLugar);
			for (int i = 0; i < lugar.length; i++) {
				Object[] row = (Object[])lugar[i];
				
				String cod = row[0].toString();
				addItem(row[1].toString());
				
				m_codigoForeign.add(cod);
			}
			
		} 
		catch (Exception e) {
			//Util.errMsg(Main.getVentana(),"Error al cargar la lista de Contactos",e);
			loadItemsForCliente(codLugar);
		}
	}
	
	public void loadEmpty(){	
		this.removeAllItems();
		this.addItem(new String("Seleccione un Contacto"));
	}
	
	public void resetFields(){
		this.m_codigoForeign.clear();
		this.removeAllItems();	
		if(this.getItemCount() >= 0){
			this.addItem(new String("Seleccione un Contacto"));
		}
	}
}
