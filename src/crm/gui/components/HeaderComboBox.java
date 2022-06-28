package crm.gui.components;

import java.util.Vector;

import crm.client.managers.PrtPptoHeaderManager;
import crm.libraries.abm.entities.PrtPptoHeader;

public class HeaderComboBox extends ABMComboBox{
	public HeaderComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	public void loadItems(){
		this.addItem(new String("Seleccione un encabezado"));
		try{
			

			PrtPptoHeader[] headers = PrtPptoHeaderManager.instance().getAll();
			
			for (int i = 0; i < headers.length; i++) {				
				this.addItem(headers[i].getTitulo());
				m_codigoForeign.add(headers[i].getCodigo());
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
