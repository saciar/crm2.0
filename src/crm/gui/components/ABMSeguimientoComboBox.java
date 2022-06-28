package crm.gui.components;

import java.util.Vector;

import crm.client.managers.SeguimientoManager;
import crm.libraries.abm.entities.Seguimiento;

public class ABMSeguimientoComboBox extends ABMComboBox {
	public ABMSeguimientoComboBox() {
		super(200, 20);
		m_codigoForeign = new Vector();
	}
	
	
	
	public void loadItems(){	
		
		try{			

			Seguimiento[] seguimientos = SeguimientoManager.instance().getAllSeguimientos();

			resetFields();
			
			for (int i = 0; i < seguimientos.length; i++) {				
				this.addItem(seguimientos[i].getDescripcion());
				m_codigoForeign.add(seguimientos[i].getCodigo());
				
			}
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void resetFields(){
		this.m_codigoForeign.clear();
		this.removeAllItems();	
		if(this.getItemCount() >= 0){
			this.addItem(new String("Seleccione un Seguimiento"));
		}
	}
}
