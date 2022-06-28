package crm.gui.components;

import java.util.Vector;

import crm.client.managers.ModoIngEquiposManager;
import crm.libraries.abm.entities.ModoIngEquipos;

public class ABMModoIngreso extends ABMComboBox{
	public ABMModoIngreso(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	public void loadItems(){
		this.addItem(new String("Seleccione un modo"));
		try{
			

			ModoIngEquipos[] modo = ModoIngEquiposManager.instance().getAll();
			
			for (int i = 0; i < modo.length; i++) {				
				this.addItem(modo[i].getDescripcion());
				m_codigoForeign.add(modo[i].getCodigo());
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
