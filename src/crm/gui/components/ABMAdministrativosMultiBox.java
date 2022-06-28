package crm.gui.components;

import java.util.Vector;

import crm.client.managers.AdministradorManager;
import crm.client.managers.UnidadAdministradorManager;
import crm.libraries.abm.entities.Administrador;
import crm.services.sei.CategVendedorManagerSEI;

public class ABMAdministrativosMultiBox extends ABMMultiBox{
	public ABMAdministrativosMultiBox(){
		super(200,400);	
		m_codigoForeign = new Vector();
	}
	
	public void loadItems(){	
		try{			
			Administrador[] vendedores = AdministradorManager.instance().findByField("categoria",CategVendedorManagerSEI.CATEGORY_VENDEDOR);
			
			
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

	
	public void loadItemsForUnidadesAdministrativas(String codUnidad){	
		try{	

			//Object[] vendedores = UnidadAdministradorManager.instance().getAdministradoresByUnidadAdministrativa(codUnidad);
			Object[] vendedores = AdministradorManager.instance().getAdministradoresSinUnidadAdministrativaPorUnidad(codUnidad);
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
	
	public void loadItemsForAdministrativosSinUnidad(){
		try{	

			Object[] vendedores = AdministradorManager.instance().getAdministradoresSinUnidadAdministrativa();
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
