package crm.gui.components;

import java.rmi.RemoteException;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import crm.client.managers.SalaLugarManager;

public class ABMSalasComboBox extends ABMComboBox{
	private static final Log log = LogFactory.getLog(ABMSalasComboBox.class);
	public ABMSalasComboBox(){
		super(200,20);		
	}
	
	public void loadEmpty(){	
		this.removeAllItems();
		this.addItem(new String("Seleccione un lugar de evento"));
	}
	
	public void setSalasComboBox(String codLugar){
		
		removeAllItems();
		addItem(new String("Seleccione una sala"));
		
		m_codigoForeign = new Vector();
		
		try {
			Object[] salas = SalaLugarManager.instance().getSalaLugarReportByLugar(codLugar);
			for (int i = 0; i < salas.length; i++) {
				Object[] row = (Object[]) salas[i];
				this.addItem(row[1].toString());
				m_codigoForeign.add(row[0].toString());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
