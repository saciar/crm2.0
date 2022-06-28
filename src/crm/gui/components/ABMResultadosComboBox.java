package crm.gui.components;

import java.util.List;
import java.util.Vector;

import crm.client.managers.ResultadoSeguimientoManager;
import crm.client.managers.ServicioManager;


public class ABMResultadosComboBox extends ABMComboBox {
	public ABMResultadosComboBox() {
		super(200, 20);
		m_codigoForeign = new Vector();
		resetFields();
	}
	
	public void loadItemsForAction (String codAccion){
		try{			
			resetFields();
			
			Object[] resultados = ResultadoSeguimientoManager.instance().getResultadosReportByAccion(codAccion);
			
			for (int i = 0; i < resultados.length; i++) {
				Object[] row = (Object[])resultados[i];
				
				String cod = row[0].toString();
				
				addItem(row[1].toString());
				m_codigoForeign.add(cod);
			}

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void resetFields(){
		m_codigoForeign.clear();
		removeAllItems();	
		//if(this.getItemCount() >= 0){
		addItem(new String("Seleccione un servicio"));
		setForeign(null);
		//}
	}
}
