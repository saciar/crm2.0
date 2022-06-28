package crm.gui.pantalla.solapa;

import java.rmi.RemoteException;
import java.util.Iterator;

import crm.client.managers.VendedorManager;

public class MainPanelGerenciaControl {
	public String getNombreVendedor(String codigo){		
		try {
			return VendedorManager.instance().getVendedorById(codigo).getApellidoYNombre();
		} catch (RemoteException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	
}
