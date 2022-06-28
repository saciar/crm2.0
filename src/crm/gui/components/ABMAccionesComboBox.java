package crm.gui.components;

import java.util.List;
import java.util.Vector;

import crm.client.managers.LugarEventoManager;
import crm.client.managers.SeguimientoManager;


public class ABMAccionesComboBox extends ABMComboBox{
	public ABMAccionesComboBox(){
		super(200,22);
		m_codigoForeign = new Vector();
	}
	
	public void loadItems(){	
		try{
			Object[] acciones = SeguimientoManager.instance().getAccionesReport();
			
			resetFields();
			
			for (int i = 0; i < acciones.length; i++) {
				Object[] accion = (Object[])acciones[i];
				this.addItem(accion[1].toString());
				m_codigoForeign.add(accion[0].toString());
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void resetFields(){
		this.m_codigoForeign.clear();
		this.removeAllItems();	
		if(this.getItemCount() >= 0){
			this.addItem(new String("Seleccione una acción"));
		}
	}	
}
