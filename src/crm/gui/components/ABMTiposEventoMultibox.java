package crm.gui.components;

import java.util.Vector;

import crm.client.managers.TipoEventoManager;

public class ABMTiposEventoMultibox extends ABMMultiBox{
	
	
	public ABMTiposEventoMultibox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}	
	
	public void loadItems(){				
		try {
			Object[] eventos = TipoEventoManager.instance().getTipoEventosReport();
			
			this.m_codigoForeign.clear();
			
			String listData[] = new String[eventos.length];
			
			for (int i = 0; i < eventos.length; i++) {
				Object[] evento = (Object[])eventos[i];
				listData[i] = evento[1].toString();
				m_codigoForeign.add(evento[0].toString());
			}
			setListData(listData);	
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
