package crm.gui.components;

import java.rmi.RemoteException;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import crm.client.managers.TipoLugarEventoManager;

public class ABMTiposLugarEvtComboBox extends ABMComboBox{
	private static final Log log = LogFactory.getLog(ABMTiposLugarEvtComboBox.class);
	
	public ABMTiposLugarEvtComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();		
	}
	
	public void loadItems(){
		this.addItem(new String("Seleccione un tipo de lugar"));

		if (log.isDebugEnabled())
			log.debug("Obteniendo tipos de lugar ...");
		
		try {
			Object[] lugares = TipoLugarEventoManager.instance().getTipoLugarEventosReport();
			
			for (int i = 0; i < lugares.length; i++) {
				Object[] lugar = (Object[])lugares[i];
				this.addItem(lugar[1].toString());
				m_codigoForeign.add(lugar[0].toString());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
