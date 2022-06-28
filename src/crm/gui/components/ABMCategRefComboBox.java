package crm.gui.components;

import java.util.Vector;

import crm.client.managers.CategReferenciaManager;
import crm.libraries.abm.entities.CategReferencia;

public class ABMCategRefComboBox extends ABMComboBox{
	public ABMCategRefComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	public void loadItems(){	
		
		try{				
			CategReferencia[] categReferencias = CategReferenciaManager.instance().getAllCategReferencias();

			resetFields();
			
			for (int i = 0; i < categReferencias.length; i++) {				
				this.addItem(categReferencias[i].getDescripcion());
				m_codigoForeign.add(categReferencias[i].getCodigo());
				
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
			this.addItem(new String("Seleccione una Categoria"));
		}
	}
}
