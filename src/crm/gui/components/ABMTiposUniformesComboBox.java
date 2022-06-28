package crm.gui.components;

import java.rmi.RemoteException;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import crm.client.managers.TipoUniformeManager;

public class ABMTiposUniformesComboBox extends ABMComboBox{
	private static final Log log = LogFactory.getLog(ABMTiposUniformesComboBox.class);
	
	public ABMTiposUniformesComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
		
	}
	
	public void loadItems(){	
		this.addItem(new String("Seleccione un tipo de uniforme"));
		
		

		if (log.isDebugEnabled()){
			log.debug("Obteniendo tipos de uniformes ...");
		}

		try {
			Object[] uniformes = TipoUniformeManager.instance().getTiposUniformeReport();
			
			for (int i = 0; i < uniformes.length; i++) {
				Object[] unif = (Object[])uniformes[i];
				this.addItem(unif[1].toString());
				m_codigoForeign.add(unif[0].toString());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
