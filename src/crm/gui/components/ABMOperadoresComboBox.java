package crm.gui.components;

import java.util.List;
import java.util.Vector;

import crm.client.managers.OperadorManager;
import crm.libraries.abm.entities.Operador;

public class ABMOperadoresComboBox extends ABMComboBox{
	public ABMOperadoresComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	public void loadItems(){			
		try{			
			Operador[] operadores = OperadorManager.instance().getAllOperadores();
			loadItems(operadores);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void loadItems(Operador[] operadores){
		resetFields();
		
		for (int i = 0; i < operadores.length; i++) {				
			this.addItem(operadores[i].getApellidoYNombre());
			m_codigoForeign.add(operadores[i].getCodigo());
		}
	}
	
	public void loadItems(List operadores){
		resetFields();
		
		for (int i = 0; i < operadores.size(); i++) {	
			Operador operador = (Operador)operadores.get(i);
			this.addItem(operador.getApellidoYNombre());
			m_codigoForeign.add(operador.getCodigo());
		}
	}
	
	public void resetFields(){
		this.m_codigoForeign.clear();
		this.removeAllItems();	
		if(this.getItemCount() >= 0){
			this.addItem(new String("Seleccione un Operador"));
		}
	}
}
