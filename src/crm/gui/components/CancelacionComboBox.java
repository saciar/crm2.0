package crm.gui.components;

import java.util.Vector;

import crm.client.managers.PrtPptoCancelacionManager;
import crm.libraries.abm.entities.PrtPptoCancelacion;

public class CancelacionComboBox extends ABMComboBox{
	public CancelacionComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	public void loadItems(){
		this.addItem(new String("Seleccione una cancelacion"));
		try{
			

			PrtPptoCancelacion[] headers = PrtPptoCancelacionManager.instance().getAll();
			
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