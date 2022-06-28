package crm.gui;

import java.rmi.RemoteException;

import crm.client.managers.FeriadosManager;

public class pruebaFeriados {
	public static void main ( String[] args ) {
		try
		{
			//preloadWebServices();
			System.out.println("Codigo ="+FeriadosManager.instance().getIdPorFecha("2016-02-08"));
			System.out.println("Codigo ="+FeriadosManager.instance().getIdPorFecha("2016-02-09"));
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	};
	}
}
