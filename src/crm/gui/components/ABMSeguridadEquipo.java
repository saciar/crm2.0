package crm.gui.components;

import java.util.Vector;

import crm.client.managers.SeguridadEquiposManager;
import crm.libraries.abm.entities.SeguridadEquipos;

public class ABMSeguridadEquipo extends ABMComboBox{
	public ABMSeguridadEquipo(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	public void loadItems(){
		this.addItem(new String("Seleccione la seguridad"));
		try{
			

			SeguridadEquipos[] segEquipos = SeguridadEquiposManager.instance().getAll();
			
			for (int i = 0; i < segEquipos.length; i++) {				
				this.addItem(segEquipos[i].getDescripcion());
				m_codigoForeign.add(segEquipos[i].getCodigo());
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
