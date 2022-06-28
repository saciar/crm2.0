package crm.gui.components;

import java.rmi.RemoteException;
import java.util.Vector;

import crm.client.managers.MedioPagoManager;

public class ABMMediosPagoComboBox extends ABMComboBox{
	
	public ABMMediosPagoComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	public void loadItems(){
		this.addItem(new String("Seleccione un medio de pago"));
		try {
			Object[] medios = MedioPagoManager.instance().getMedioPagosReport();
			
			for (int i = 0; i < medios.length; i++) {
				Object[] medio = (Object[])medios[i];
				this.addItem(medio[1].toString());
				m_codigoForeign.add(medio[0].toString());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
}
