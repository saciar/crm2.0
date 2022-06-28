package crm.gui.components;

import java.util.Vector;

import crm.client.managers.PrtPptoValidezManager;
import crm.libraries.abm.entities.PrtPptoValidez;

public class ValidezComboBox extends ABMComboBox{
	public ValidezComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	public void loadItems(){
		this.addItem(new String("Seleccione una validez"));
		try{
			

			PrtPptoValidez[] headers = PrtPptoValidezManager.instance().getAll();
			
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
