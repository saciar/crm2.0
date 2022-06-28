package crm.gui.pantallas2019.cold.componentes;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import crm.client.managers.OperadorManager;
import crm.libraries.abm.entities.Operador;

public class OperadoresListModel extends AbstractListModel<Operador>{

	private ArrayList lista = new ArrayList<Operador>();
	
	@Override
	public int getSize() {
		return lista.size();
	}

	@Override
	public Operador getElementAt(int index) {
		Operador o = (Operador)lista.get(index);
		return o;
	}

	public void loadItems(){	
		try{			
			Operador[] operadores = OperadorManager.instance().getAllOperadores();

			String listData[] = new String[operadores.length];		
			for (int i = 0; i < operadores.length; i++) {
				addOperador(operadores[i]);
				this.fireIntervalAdded(this, getSize(), getSize()+1);
			}		
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void addOperador(Operador p){
		lista.add(p);
		this.fireIntervalAdded(this, getSize(), getSize()+1);
	}
	
	public void eliminarOperador(int index0){
		lista.remove(index0);
		this.fireIntervalRemoved(this, index0, index0);
	}

	public void eliminarTodosOperador(){
		lista.removeAll(lista);
		this.fireIntervalRemoved(this, 0, lista.size());
	}
	
	public Operador getOperador(int index){
		  return (Operador)lista.get(index);
		}
}
