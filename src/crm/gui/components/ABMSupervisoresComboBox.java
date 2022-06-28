package crm.gui.components;

import java.util.Vector;

import crm.client.managers.VendedorManager;
import crm.libraries.abm.entities.Vendedor;
import crm.services.sei.CategVendedorManagerSEI;

public class ABMSupervisoresComboBox extends ABMComboBox{
	public ABMSupervisoresComboBox() {
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	
	
	
	
	
	public void loadItems(){	
		
		try{			

			Vendedor[] vendedores = VendedorManager.instance().findByField("categoria",CategVendedorManagerSEI.CATEGORY_SUPERVISOR);

			resetFields();
			
			for (int i = 0; i < vendedores.length; i++) {				
				this.addItem(vendedores[i].getApellidoYNombre());
				m_codigoForeign.add(vendedores[i].getCodigo());
				
			}
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	


	public void loadItemsForUnidadesComerciales(){	
		try{	

			Object[] vendedores = VendedorManager.instance().getAllVendedoresNotInUnidadesComerciales(CategVendedorManagerSEI.CATEGORY_SUPERVISOR);
			resetFields();
			for (int i = 0; i < vendedores.length; i++) {
				Object[] row = (Object[])vendedores[i];
				
				String cod = row[0].toString();
				
				addItem(row[1].toString());
				m_codigoForeign.add(cod);
			}		
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void loadItemsForUnidadesComercialesEditMode(String vendedor){	
		try{	

			Object[] vendedores = VendedorManager.instance().getAllVendedoresNotInUnidadesComercialesByVendedores(CategVendedorManagerSEI.CATEGORY_SUPERVISOR,vendedor);
			resetFields();
			for (int i = 0; i < vendedores.length; i++) {
				Object[] row = (Object[])vendedores[i];
				
				String cod = row[0].toString();
				
				addItem(row[1].toString());
				m_codigoForeign.add(cod);
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
			this.addItem(new String("Seleccione Supervisor"));
		}
	}	
}
