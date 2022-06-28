package crm.gui.components;

import java.util.List;
import java.util.Vector;

import crm.client.managers.OperadorManager;
import crm.libraries.abm.entities.Operador;

public class ABMOperadoresMultiBox extends ABMMultiBox{
	public ABMOperadoresMultiBox(){
		super(200,400);	
		m_codigoForeign = new Vector();
	}
	
	public void loadItems(){	
		try{			
			Operador[] operadores = OperadorManager.instance().getAllOperadores();
			
			this.m_codigoForeign.clear();
			
			
			String listData[] = new String[operadores.length];		
			for (int i = 0; i < operadores.length; i++) {
				listData[i] = operadores[i].getApellidoYNombre();
				m_codigoForeign.add(operadores[i].getCodigo());
			}
			setListData(listData);
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void setItems(Operador[] operadores){
		this.m_codigoForeign.clear();

		String listData[] = new String[operadores.length];		
		for (int i = 0; i < operadores.length; i++) {
			listData[i] = operadores[i].getApellidoYNombre();
			m_codigoForeign.add(operadores[i].getCodigo());
		}
		setListData(listData);
	}
	
	public void setItems(List operadores){
		this.m_codigoForeign.clear();

		String listData[] = new String[operadores.size()];		
		for (int i = 0; i < operadores.size(); i++) {
			Operador operador = (Operador)operadores.get(i);
			listData[i] = operador.getApellidoYNombre();
			m_codigoForeign.add(operador.getCodigo());
		}
		setListData(listData);
	}

	
}
