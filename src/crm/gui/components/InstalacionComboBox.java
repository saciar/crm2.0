package crm.gui.components;

import java.util.Vector;

import crm.client.managers.PrtPptoInstalacionManager;
import crm.libraries.abm.entities.PrtPptoInstalacion;

public class InstalacionComboBox extends ABMComboBox{
	public InstalacionComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	public void loadItems(){
		this.addItem(new String("Seleccione un item"));
		try{
			

			PrtPptoInstalacion[] headers = PrtPptoInstalacionManager.instance().getAll();
			
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
