package crm.gui.components;

import java.util.Vector;

import crm.client.managers.FamiliaServManager;
import crm.client.managers.TipoEventoManager;
import crm.client.managers.UsuarioManager;
import crm.libraries.abm.entities.FamiliaServ;
import crm.libraries.abm.entities.Usuario;

public class ABMUsuariosMultiBox extends ABMMultiBox{
	
	
	public ABMUsuariosMultiBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}	
	
	public void loadItems(){	
		try{			
			Usuario[] usuarios = UsuarioManager.instance().findByField("activo", "S");
			
			this.m_codigoForeign.clear();
			
			
			String listData[] = new String[usuarios.length];		
			for (int i = 0; i < usuarios.length; i++) {
				listData[i] = usuarios[i].getApellidoYNombre();
				m_codigoForeign.add(usuarios[i].getCodigo());
			}
			setListData(listData);
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
