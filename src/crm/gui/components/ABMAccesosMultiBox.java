package crm.gui.components;

import java.util.Vector;

import crm.client.managers.AccesoManager;
import crm.libraries.abm.entities.Acceso;

public class ABMAccesosMultiBox extends ABMMultiBox{
	public ABMAccesosMultiBox(){
		super(200,400);	
		m_codigoForeign = new Vector();
	}
	
	public void loadItems(){	
		try{			
			Acceso[] accesos = AccesoManager.instance().getAllAccesos();
			
			this.m_codigoForeign.clear();
			
			
			String listData[] = new String[accesos.length];		
			for (int i = 0; i < accesos.length; i++) {
				listData[i] = accesos[i].getDescripcion();
				m_codigoForeign.add(accesos[i].getCodigo());
			}
			setListData(listData);
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
