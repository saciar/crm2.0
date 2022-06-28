package crm.gui.components;

import java.util.Vector;

import crm.client.managers.AsistenteManager;
import crm.client.managers.OperadorManager;
import crm.libraries.abm.entities.Asistente;
import crm.libraries.abm.entities.Operador;

public class ABMAsistentesComboBox extends ABMComboBox{
	public ABMAsistentesComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	public void loadItems(){	
		
		try{			

			Asistente[] asistentes = AsistenteManager.instance().getAllAsistentes();

			resetFields();
			
			for (int i = 0; i < asistentes.length; i++) {				
				this.addItem(asistentes[i].getApellidoYNombre());
				m_codigoForeign.add(asistentes[i].getCodigo());
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
			this.addItem(new String("Seleccione un Asistente"));
		}
	}
}
