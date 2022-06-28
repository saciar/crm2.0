package crm.gui.components;

import java.util.Vector;

import crm.client.managers.MonedaExtranjeraManager;
import crm.libraries.abm.entities.MonedaExtranjera;

public class MonedaExtranjeraComboBox extends ABMComboBox{
	public MonedaExtranjeraComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	public void loadItems(){
		this.addItem(new String("Seleccione una moneda"));
		try{
			

			MonedaExtranjera[] headers = MonedaExtranjeraManager.instance().getAll();
			
			for (int i = 0; i < headers.length; i++) {				
				this.addItem(headers[i].getDescripcion());
				m_codigoForeign.add(headers[i].getCodigo());
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
