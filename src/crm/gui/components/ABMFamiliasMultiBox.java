package crm.gui.components;

import java.util.Vector;

import crm.client.managers.FamiliaServManager;
import crm.libraries.abm.entities.FamiliaServ;

public class ABMFamiliasMultiBox extends ABMMultiBox{
	public ABMFamiliasMultiBox(int width, int height){
		super(width,height);	
		m_codigoForeign = new Vector();
	}
	
	public ABMFamiliasMultiBox(){
		this(10,10);	
	}
	
	public void loadItems(){	
		try{			
			FamiliaServ[] familias = FamiliaServManager.instance().getAllFamiliaServs();
			
			this.m_codigoForeign.clear();
			
			
			String listData[] = new String[familias.length];		
			for (int i = 0; i < familias.length; i++) {
				listData[i] = familias[i].getDescripcion();
				m_codigoForeign.add(familias[i].getCodigo());
			}
			setListData(listData);
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
