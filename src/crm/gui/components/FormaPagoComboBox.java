package crm.gui.components;

import java.util.Vector;

import crm.client.managers.PrtPptoFPagoManager;
import crm.libraries.abm.entities.PrtPptoFPago;

public class FormaPagoComboBox extends ABMComboBox{
	public FormaPagoComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	public void loadItems(){
		this.addItem(new String("Seleccione una forma de pago"));
		try{
			

			PrtPptoFPago[] headers = PrtPptoFPagoManager.instance().getAll();
			
			for (int i = 0; i < headers.length; i++) {				
				this.addItem(headers[i].getTitulo());
				m_codigoForeign.add(headers[i].getCodigo());
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String searchForeign(){
		Object returnValue = null;
		
		if (this.getSelectedIndex() > 0){
			 Object o = m_codigoForeign.get(this.getSelectedIndex()-1);
			 if (o instanceof String)
				 returnValue = o;
			 else if (o instanceof Long)
				 returnValue = ((Long)o).toString();
		}		
		else if (this.getSelectedIndex()==0){
			returnValue="100";
		}
		return (returnValue != null ? ((String)returnValue) : null);
	}

}
