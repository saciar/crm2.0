package crm.gui.components;

import java.util.Vector;

import crm.client.managers.PrtPptoSignatureManager;
import crm.libraries.abm.entities.PrtPptoSignature;

public class FirmaComboBox extends ABMComboBox{
	public FirmaComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	public void loadItems(){
		this.addItem(new String("Seleccione una firma"));
		try{
			

			PrtPptoSignature[] headers = PrtPptoSignatureManager.instance().getAll();
			
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
