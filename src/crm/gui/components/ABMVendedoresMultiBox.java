package crm.gui.components;

import java.util.Vector;

import crm.client.managers.ServicioManager;
import crm.client.managers.UnidadVendedorManager;
import crm.client.managers.VendedorManager;
import crm.libraries.abm.entities.Vendedor;
import crm.services.sei.CategVendedorManagerSEI;

public class ABMVendedoresMultiBox extends ABMMultiBox{
	public ABMVendedoresMultiBox(){
		super(200,400);	
		m_codigoForeign = new Vector();
	}
	
	public void loadItems(){	
		try{			
			Vendedor[] vendedores = VendedorManager.instance().findByField("categoria",CategVendedorManagerSEI.CATEGORY_VENDEDOR);
			
			
			this.m_codigoForeign.clear();
			
			
			String listData[] = new String[vendedores.length];		
			for (int i = 0; i < vendedores.length; i++) {
				listData[i] = vendedores[i].getApellidoYNombre();
				m_codigoForeign.add(vendedores[i].getCodigo());
			}
			setListData(listData);			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void loadItemsAllVendedores(){	
		try{			
			Vendedor[] vendedores = VendedorManager.instance().getAllVendedores();

			this.m_codigoForeign.clear();
			
			String listData[] = new String[vendedores.length];
			for (int i = 0; i < vendedores.length; i++) {
				if(vendedores[i].getCategoria().equals("1") || vendedores[i].getCategoria().equals("2")){
					listData[i] = vendedores[i].getApellidoYNombre();
					m_codigoForeign.add(vendedores[i].getCodigo());
				}
			}
			setListData(listData);	
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void loadItemsForUnidadesComerciales(){	
		try{	

			Object[] vendedores = VendedorManager.instance().getAllVendedoresNotInUnidadesVendedores(CategVendedorManagerSEI.CATEGORY_VENDEDOR);
			this.m_codigoForeign.clear();
			String listData[] = new String[vendedores.length];	
			for (int i = 0; i < vendedores.length; i++) {
				Object[] row = (Object[])vendedores[i];
				
				String cod = row[0].toString();
				String apynom = row[1].toString();

				listData[i] = apynom;
				m_codigoForeign.add(cod);
			}
			setListData(listData);			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void loadItemsForVendoresSinUnidad(){
		try{	

			Object[] vendedores = VendedorManager.instance().getVendedoresSinUnidadComercial(CategVendedorManagerSEI.CATEGORY_VENDEDOR);
			this.m_codigoForeign.clear();
			String listData[] = new String[vendedores.length];	
			for (int i = 0; i < vendedores.length; i++) {
				Object[] row = (Object[])vendedores[i];
				
				String cod = row[0].toString();
				String apynom = row[1].toString();

				listData[i] = apynom;
				m_codigoForeign.add(cod);
			}
			setListData(listData);			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void loadItemsForUnidadesComercialesEditMode(String vendedoresArray){	
		try{	

			Object[] vendedores = VendedorManager.instance().getAllVendedoresNotInUnidadesVendedoresByVendedores(CategVendedorManagerSEI.CATEGORY_VENDEDOR,vendedoresArray);
			this.m_codigoForeign.clear();
			String listData[] = new String[vendedores.length];	
			for (int i = 0; i < vendedores.length; i++) {
				Object[] row = (Object[])vendedores[i];
				
				String cod = row[0].toString();
				String apynom = row[1].toString();

				listData[i] = apynom;
				m_codigoForeign.add(cod);
			}
			setListData(listData);			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
