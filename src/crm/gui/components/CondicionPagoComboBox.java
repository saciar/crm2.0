package crm.gui.components;

import java.util.Vector;

import crm.client.managers.PrtPptoCondPagoManager;
import crm.libraries.abm.entities.PrtPptoCondPago;

public class CondicionPagoComboBox extends ABMComboBox{
	public CondicionPagoComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	public void loadItems(){
		this.addItem(new String("Seleccione una condición de pago"));
		try{
			

			PrtPptoCondPago[] headers = PrtPptoCondPagoManager.instance().getAll();
			
			for (int i = 0; i < headers.length; i++) {				
				this.addItem(headers[i].getTitulo());
				m_codigoForeign.add(headers[i].getCodigo());
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
