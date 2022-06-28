package crm.gui.components;

import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import crm.client.managers.TipoCobradorManager;

public class ABMTipoCobradoresComboBox extends ABMComboBox{
	private static final Log log = LogFactory.getLog(ABMTiposEventosComboBox.class);
	public ABMTipoCobradoresComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	
	public void loadItems(){	
		this.addItem(new String("Seleccione un tipo de cobrador"));
		try {
			Object[] eventos = TipoCobradorManager.instance().getTipoCobradoresReport();
			
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
