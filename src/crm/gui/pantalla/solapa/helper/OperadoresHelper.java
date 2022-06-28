package crm.gui.pantalla.solapa.helper;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import crm.client.managers.OperadorManager;
import crm.gui.components.ABMOperadoresComboBox;
import crm.gui.components.ABMOperadoresMultiBox;
import crm.libraries.abm.entities.Operador;

public class OperadoresHelper {
	private Operador[] operadores;
	private List usedOperadores;
	private List validators;
	private List combos;
	private List multiples;
	
	public OperadoresHelper(){
		this.usedOperadores = new ArrayList();
		this.validators = new ArrayList();
		this.combos = new ArrayList();
		this.multiples = new ArrayList();		
		try {
			this.operadores = OperadorManager.instance().getAllOperadores();
		} catch (RemoteException e) {			
			e.printStackTrace();
		}
	}
	
	public void addValidator(OperadorValidator validator){
		this.validators.add(validator);
	}
	
	public void addCombo(ABMOperadoresComboBox combo){
		this.combos.add(combo);
	}
	
	public void addMultiple(ABMOperadoresMultiBox multi){
		this.multiples.add(multi);
	}
	
	public void processComponents(){
		
		this.checkUseds();
		this.reloadComponents();
	}

	
	private void checkUseds(){
		this.usedOperadores.clear();
		
		Iterator it = this.validators.iterator();
		while(it.hasNext()){
			OperadorValidator validator = (OperadorValidator)it.next();
			String[] operadorIds = validator.getSelecteds();
			for(int i = 0;i < operadorIds.length;i++){
				addOperador(operadorIds[i]);
			}	
		}
		it = this.multiples.iterator();
		while(it.hasNext()){
			ABMOperadoresMultiBox multi = (ABMOperadoresMultiBox)it.next();
			String[] operadorIds = multi.searchForeignsArray();
			for(int i = 0;i < operadorIds.length;i++){
				addOperador(operadorIds[i]);
			}		
		}
	}
	
	private void reloadComponents(){
		List operadores = new ArrayList();
		for(int i = 0;i < this.operadores.length;i++){
			if(!isUsed(this.operadores[i].getCodigo())){
				operadores.add(this.operadores[i]);
			}
		}
		for(int i = 0;i < combos.size();i++){
			ABMOperadoresComboBox combo = (ABMOperadoresComboBox)this.combos.get(i);			
			combo.loadItems(operadores);
		}
		
		
		//---------multiples--------------------------------------------------
		for(int i = 0;i < multiples.size();i++){
			ABMOperadoresMultiBox multi = (ABMOperadoresMultiBox)multiples.get(i);
			operadores.clear();
			String[] selecteds = multi.searchForeignsArray();
			for(int j = 0;j < this.operadores.length;j++){
				boolean isSelected = false;
				for(int k = 0;k < selecteds.length;k++){
					if(selecteds[k].equals(this.operadores[j].getCodigo())){
						isSelected = true;
					}
				}
				if(isSelected || !isUsed(this.operadores[j].getCodigo())){
					operadores.add(this.operadores[j]);
				}
			}
			multi.setItems(operadores);
			multi.setForeigns(selecteds);
		}
	}
	
	
	
	public void addForEdit(ABMOperadoresComboBox combo,String id){
		List operadores = new ArrayList();
		for(int i = 0;i < this.operadores.length;i++){
			if(this.operadores[i].getCodigo().equals(id) || !isUsed(this.operadores[i].getCodigo())){
				operadores.add(this.operadores[i]);
			}
		}
		combo.loadItems(operadores);
	}
	
	private boolean isUsed(String operadorId){
		boolean exist = false;
		if(!StringUtils.isBlank(operadorId)){		
			
			Iterator it = this.usedOperadores.iterator();
			while (it.hasNext()) {
				String id = (String) it.next();
				if(id.equals(operadorId)){
					exist = true;
				}
			}
		}
		return exist;
	}
	
	private void addOperador(String operadorId){
		if(!StringUtils.isBlank(operadorId)){		
			boolean exist = false;
			Iterator it = this.usedOperadores.iterator();
			while (it.hasNext()) {
				String id = (String) it.next();
				if(id.equals(operadorId)){
					exist = true;
				}
			}
			if(!exist){
				this.usedOperadores.add(operadorId);
			}
		}
	}
}
