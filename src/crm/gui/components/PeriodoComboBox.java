package crm.gui.components;

import java.util.Vector;

import crm.client.managers.PrtPptoPeriodoManager;
import crm.libraries.abm.entities.PrtPptoPeriodo;

public class PeriodoComboBox extends ABMComboBox{
	public PeriodoComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	public void loadItems(){
		this.addItem(new String("Seleccione un período"));
		try{
			

			PrtPptoPeriodo[] headers = PrtPptoPeriodoManager.instance().getAll();
			
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
