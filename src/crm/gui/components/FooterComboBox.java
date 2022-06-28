package crm.gui.components;

import java.util.Vector;

import crm.client.managers.PrtPptoFooterManager;
import crm.libraries.abm.entities.PrtPptoFooter;

public class FooterComboBox extends ABMComboBox{
	public FooterComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	public void loadItems(){
		this.addItem(new String("Seleccione un pie"));
		try{
			

			PrtPptoFooter[] headers = PrtPptoFooterManager.instance().getAll();
			
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
