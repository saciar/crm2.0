package crm.gui.components;

import java.util.Vector;

import crm.client.managers.TipoReciboManager;

public class ABMTipoReciboComboBox extends ABMComboBox{
	public ABMTipoReciboComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	
	public void loadItems(){	
		this.addItem(new String("Seleccione un tipo de recibo"));
		
		try {
			Object[] eventos = TipoReciboManager.instance().getTipoReciboReport();
			
			for (int i = 0; i < eventos.length; i++) {
				Object[] evento = (Object[])eventos[i];
				this.addItem(evento[1].toString());
				m_codigoForeign.add(evento[0].toString());
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
