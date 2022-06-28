package crm.gui.components;

import java.util.Vector;

import crm.client.managers.EstadoEventoManager;
import crm.libraries.abm.entities.EstadoEvento;

public class ABMEstadosComboBox extends ABMComboBox {
	public ABMEstadosComboBox() {
		super(200,20);
		m_codigoForeign = new Vector();
		resetFields();
	}
	
	public void loadItems(){
		
		try{
			EstadoEvento[] estados = EstadoEventoManager.instance().getAllEstadoEventos();
			
			resetFields();
			
			for (int i = 0; i < estados.length; i++) {				
				if(!estados[i].getDescripcion().equals("Actualizado")){
					this.addItem(estados[i].getDescripcion().equals("Nuevo")?"Pendiente":estados[i].getDescripcion());
					m_codigoForeign.add(estados[i].getCodigo());
				}
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
			this.addItem(new String("Seleccione un estado"));
		}
	}
	
}
