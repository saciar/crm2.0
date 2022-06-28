package crm.gui.components;

import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import crm.client.managers.LugarEventoManager;

public class ABMLugarEvtComboBox extends ABMComboBox{
	private static final Log log = LogFactory.getLog(ABMLugarEvtComboBox.class);
	public ABMLugarEvtComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
		resetFields();
	}
	
	public void loadItems(){		
		try{				
			Object[] lugares = LugarEventoManager.instance().getLugarEventosReport();

			resetFields();
			
			for (int i = 0; i < lugares.length; i++) {
				Object[] lugar = (Object[])lugares[i];
				this.addItem(lugar[1].toString());
				m_codigoForeign.add(lugar[0].toString());
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void resetFields(){
		this.m_codigoForeign.clear();
		this.removeAllItems();	
		if(this.getItemCount() >= 0){
			this.addItem(new String("Seleccione un lugar de evento"));
		}
	}	
}

