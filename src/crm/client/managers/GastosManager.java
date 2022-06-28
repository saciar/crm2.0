package crm.client.managers;

import java.rmi.RemoteException;

import org.apache.wsif.WSIFException;

import crm.libraries.abm.entities.Subcontratado;
import crm.services.wsdl2.sei.GastosManagerSEI;

public class GastosManager extends CongressCRMServiceWSDL2 implements GastosManagerSEI{

    private GastosManagerSEI stub;
    private static GastosManager instance;

    public static GastosManager instance() {
		try {
			if (instance == null) {
				instance = new GastosManager();
			}
		} catch (WSIFException e) {
			e.printStackTrace();
		}
		return instance;
	}

    public GastosManager() throws WSIFException{
        super("GastosManagerSEI");
    }

    protected void registerTypes() throws WSIFException{
        try{
            addType("java.util.Date", Class.forName("java.util.Date"));
            addType("Subcontratado", Class.forName("crm.libraries.abm.entities.Subcontratado"));
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        stub=(GastosManagerSEI)service.getStub(GastosManagerSEI.class);
    }

    public Object[] buscarPorNumero(long nro) throws RemoteException {
        return stub.buscarPorNumero(nro);
    }
    public int haveSubcontratados(long nro) throws RemoteException{
        return stub.haveSubcontratados(nro);
    }

    public Object[] buscarPorFecha(String startDate, String endDate) throws RemoteException {
        return stub.buscarPorFecha(startDate, endDate);
    }

    public Object[] getServiciosSucontratados(long nro, long nropto) throws RemoteException {
        return stub.getServiciosSucontratados(nro, nropto);
    }
    public boolean grabarGastoSubcontratacion(long codGasto, double costo, long codProv, String estado) throws RemoteException{
        return stub.grabarGastoSubcontratacion(codGasto, costo, codProv, estado);
    }
    public void guardarServicioSubcontratado(Subcontratado subc, long codSala) throws RemoteException{
        stub.guardarServicioSubcontratado(subc,codSala);
    }
    public Object[] getCostosXServicio(long codServicio) throws RemoteException{
        return stub.getCostosXServicio(codServicio);
    }

	@Override
	public Object[] getSubcontratadoByServ(long cosServ) throws RemoteException {
		// TODO Auto-generated method stub
		return stub.getSubcontratadoByServ(cosServ);
	}


}
