package crm.gui.components;

import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import crm.client.managers.TipoFacturaManager;

public class ABMTipoFacturaComboBox extends ABMComboBox{
	private static final Log log = LogFactory.getLog(ABMTipoFacturaComboBox.class);
	public ABMTipoFacturaComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	
	public void loadItems(){	
		this.addItem(new String("Seleccione un tipo de factura a cobrar"));
		if (log.isDebugEnabled())
			log.debug("Obteniendo tipos de facturas ...");
		
		try {
			Object[] fafcturas = TipoFacturaManager.instance().getTipoFacturasReport();
			
			for (int i = 0; i < fafcturas.length; i++) {
				Object[] evento = (Object[])fafcturas[i];
				this.addItem(evento[1].toString());
				m_codigoForeign.add(evento[0].toString());
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
