package crm.gui.components;

import java.util.Vector;

import crm.client.managers.PrtPptoTipoPresupuestoManager;
import crm.libraries.abm.entities.PrtPptoTipoPresupuesto;

public class TipoPresupuestoComboBox extends ABMComboBox{
	public TipoPresupuestoComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	public void loadItems(){
		this.addItem(new String("Seleccione un tipo de presupuesto"));
		try{
			

			PrtPptoTipoPresupuesto[] headers = PrtPptoTipoPresupuestoManager.instance().getAll();
			
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