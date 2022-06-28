package crm.gui.components;

import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import crm.client.managers.CondicionPagoManager;

public class ABMCondicionesPagoComboBox extends ABMComboBox{
	private static final Log log = LogFactory.getLog(ABMCondicionesPagoComboBox.class);
	
	public ABMCondicionesPagoComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	public void loadItems(){
		this.addItem(new String("Seleccione una condicion de pago"));
		try{
			
			if (log.isDebugEnabled())
				log.debug("Buscando condiciones de pagos ...");
			
			Object[] condPago = CondicionPagoManager.instance().getCondicionPagosReport();

			for (int i = 0; i < condPago.length; i++) {
				//CondicionPago c = (CondicionPago) condPago.get(i);
				//this.addItem(c.getDescripcion());
				//m_codigoForeign.add(c.getCodigo());
				Object[] categ = (Object[])condPago[i];
				
				this.addItem(categ[1].toString());
				m_codigoForeign.add(categ[0].toString());
			}
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
