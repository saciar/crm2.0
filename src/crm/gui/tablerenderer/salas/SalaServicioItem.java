package crm.gui.tablerenderer.salas;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import crm.client.managers.ServicioManager;
import crm.client.managers.VariacionFechaManager;
import crm.client.managers.VariacionMesManager;
import crm.client.managers.VistaPrecioServicioIdiomaManager;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.pantalla.PantallaBienvenidaVerdadera;
import crm.libraries.abm.entities.Servicio;
import crm.libraries.abm.entities.VariacionFecha;
import crm.libraries.abm.helper.ServicioIdiomaHelper;

public class SalaServicioItem {
	private int cantidad;
	private String familia;
	private String servicio;
	private String familiaCodigo;
	private String servicioCodigo;
	private int dias;
	private int descuento;
	private boolean subContratado;
	private boolean opcional;
	private boolean unicoDia;
	private double total;
	private double precioLista;	
	private String fechaAlta;
	
	private long editingId;
	private List<ServicioIdiomaHelper> descDetallada;
	private String nroOrden;
	private String codigoLugar;
	private long tableItemId;
	private static long counter=0;
	
	private String modificado="N";
	
	public String getModificado() {
		return modificado;
	}

	public void setModificado(String modificado) {
		this.modificado = modificado;
	}

	public long getTableItemId() {
		return tableItemId;
	}

	public void setTableItemId(long tableItemId) {
		this.tableItemId = tableItemId;
	}

	public String getCodigoLugar() {
		return codigoLugar;
	}

	public void setCodigoLugar(String codigoLugar) {
		this.codigoLugar = codigoLugar;
	}

	public SalaServicioItem(){
		tableItemId = (++counter);
		descDetallada = new ArrayList<ServicioIdiomaHelper>();
	}

	/**
	 * @return Returns the cantidad.
	 */
	public int getCantidad() {
		return cantidad;
	}
	/**
	 * @param cantidad The cantidad to set.
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return Returns the dias.
	 */
	public int getDias() {
		return dias;
	}
	/**
	 * @param dias The dias to set.
	 */
	public void setDias(int dias) {
		this.dias = dias;
	}
	/**
	 * @return Returns the familia.
	 */
	public String getFamilia() {
		return familia;
	}
	/**
	 * @param familia The familia to set.
	 */
	public void setFamilia(String familia) {
		this.familia = familia;
	}
	/**
	 * @return Returns the familiaCodigo.
	 */
	public String getFamiliaCodigo() {
		return familiaCodigo;
	}
	/**
	 * @param familiaCodigo The familiaCodigo to set.
	 */
	public void setFamiliaCodigo(String familiaCodigo) {
		this.familiaCodigo = familiaCodigo;
	}
	/**
	 * @return Returns the opcional.
	 */
	public boolean isOpcional() {
		return opcional;
	}
	/**
	 * @param opcional The opcional to set.
	 */
	public void setOpcional(boolean opcional) {
		this.opcional = opcional;
	}
	/**
	 * @return Returns the servicio.
	 */
	public String getServicio() {
		return servicio;
	}
	/**
	 * @param servicio The servicio to set.
	 */
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	/**
	 * @return Returns the servicioCodigo.
	 */
	public String getServicioCodigo() {
		return servicioCodigo;
	}
	/**
	 * @param servicioCodigo The servicioCodigo to set.
	 */
	public void setServicioCodigo(String servicioCodigo) {
		this.servicioCodigo = servicioCodigo;
	}
	/**
	 * @return Returns the subContratado.
	 */
	public boolean isSubContratado() {
		return subContratado;
	}
	/**
	 * @param subContratado The subContratado to set.
	 */
	public void setSubContratado(boolean subContratado) {
		this.subContratado = subContratado;
	}

	/**
	 * @return Returns the totalSinDescuento.
	 */
	public double getTotal() {
		return total;
	}
	/**
	 * @param totalSinDescuento The totalSinDescuento to set.
	 */
	public void setTotal(double totalSinDescuento) {
		this.total = totalSinDescuento;
	}
	
	public List<ServicioIdiomaHelper> getDescDetallada() {
		return descDetallada;
	}
	
	public void setDescDetallada(List<ServicioIdiomaHelper> descDetallada) {
		this.descDetallada = descDetallada;
	}
	
	/**
	 * @return Returns the nroOrden.
	 */
	public String getNroOrden() {
		return nroOrden;
	}
	
	/**
	 * @param nroOrden The nroOrden to set.
	 */
	public void setNroOrden(String nroOrden) {
		this.nroOrden = nroOrden;
	}
	
	public long getEditingId() {
		return editingId;
	}
	
	public void setEditingId(long editingId) {
		this.editingId = editingId;
	}
	
	/**
	 * @return Returns the unicoDia.
	 */
	public boolean isUnicoDia() {
		return unicoDia;
	}
	
	/**
	 * @param unicoDia The unicoDia to set.
	 */
	public void setUnicoDia(boolean unicoDia) {
		this.unicoDia = unicoDia;
	}
	
	public Object clone(){
		SalaServicioItem clon = new SalaServicioItem();
		clon.tableItemId= tableItemId;
		clon.cantidad = cantidad;
		clon.familia = familia;
		clon.servicio = servicio;
		clon.familiaCodigo = familiaCodigo;
		clon.servicioCodigo = servicioCodigo;
		clon.dias = dias;
		clon.subContratado = subContratado;
		clon.opcional = opcional;
		clon.total = total;
		clon.descDetallada = descDetallada;
		clon.precioLista = precioLista;
		clon.descuento = descuento;
		return clon;
	}
	//-----------------------------------------------------------------------------------------------------------------------

	private String mesEvento;
	private String fechaEvt;
	
	public String getFechaEvt() {
		return fechaEvt;
	}

	public void setFechaEvt(String fechaEvt) {
		this.fechaEvt = fechaEvt;
	}

	public void calcularTotales(){
		calcularSinDescuento();
	}
	
	/**
	 * Calcula el total para este item.
	 * 
	 * @return
	 */
	private void calcularSinDescuento() {
		
		Double precio = 0.0;

		if (getCantLugaresEnVista(codigoLugar) > 0) {
			precio = getPrecioPorServicioYLugar(codigoLugar);
		} else if (codigoLugar != "0") {
			precio = getPrecioPorServicio();
		}

		double valor;
		if (isFijoPorEvento()) {
			valor = precio * cantidad;;
		} else {
			if (isUnicoDia())
				valor = precio * cantidad;
			else {
				valor = precio * cantidad * dias;
				Double descuento = 0.0;

				int codUnidad = getCodigoUnidad(servicioCodigo);
				if (isServicioComputers(servicioCodigo, codUnidad)) {
					/*
					 * if(dias > 1 && dias<=10) valor = cantidad (precio
					 * (1+((dias-1) 0.2))); else if(dias == 1) valor = cantidad
					 * precio; else if(dias>10) valor = cantidad (precio (1+(9
					 * 0.2)));
					 */
					double acum = 0.0d;
					for (int i = 1; i <= dias; i++) {
						if (i == 1) {
							acum = precio;
						} else if (i <= 5) {
							acum = acum * (1 + 0.2);
						} else if (i >= 10) {
							acum = acum * (1 + 0.2);
						}
					}
					valor = cantidad * acum;
				} else if (isServicioRural(servicioCodigo, codUnidad)) {
					if (dias >= 1 && dias <= 5)
						valor = cantidad * precio * dias;
					else if (dias > 5)
						valor = cantidad * precio * 5;
				} else {
					double descpordia = getDescuentoSegunDias(servicioCodigo);
					if (descpordia != 0.0) {
						descuento = (valor * descpordia) / 100;
						valor = valor - descuento;
					}
				}
			}
		}
		VariacionFecha[] variaciones = getDescuentosFecha(fechaEvt);
		if (variaciones != null) {
			for (int i = 0; i < variaciones.length; i++) {
				int valorAdescontar = (int) ((valor * Integer
						.parseInt(variaciones[i].getVariacion())) / 100);
				valor = valor + valorAdescontar;
			}
		}

		// this.total = valor;
		//redonder esta valor
		valor = Math.round(valor);
		
		this.precioLista = valor;
	}
	
	/**
	 * Busca el precio por servicio
	 * 
	 * @return
	 */
	private double getPrecioPorServicio() {
		// busco el precio en el cache
		Double precio = 0d;
		
		try {
			// traigo el precio de la base
			precio = ServicioManager.instance().getPrecioVtaById(servicioCodigo);
			
		} catch (RemoteException e) {
			precio = 0d;
			Util.errMsg(Main.getVentana(), "Error al obtener precio de servicio segun servicio", e);
		}
		
		return precio;
	}
	
	/**
	 * Busca si el servicio es de valor fijo por evento
	 * 
	 * @return
	 */
	private boolean isFijoPorEvento() {
		
		try {
			// traigo el precio de la base
			Servicio serv = ServicioManager.instance().getServicioById(servicioCodigo);
			if(serv.getAdmiteSinCargo().equals("S")){
				return true;
			}
			else return false;
			
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al obtener precio de servicio segun servicio", e);
			return false;
		}
	}
	
	/**
	 * Busca si el servicio es de valor fijo por evento
	 * 
	 * @return
	 */
	private boolean isSinDescuento() {
		
		try {
			// traigo el precio de la base
			Servicio serv = ServicioManager.instance().getServicioById(servicioCodigo);
			if(serv.getAdmiteDescuento().equals("N")){
				return true;
			}
			else return false;
			
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al obtener precio de servicio segun servicio", e);
			return false;
		}
	}
	
	private int getCodigoUnidad(String codServ){
		try{
		return ServicioManager.instance().getUnidadDeNegocio(codServ);
		}
		catch (Exception e) {
			Util.errMsg(Main.getVentana(), "Error al buscar unidad de negocio del servicio selelccionado", e);
			return 0;
		}
	}
	
	private boolean isServicioComputers(String cod, int unidad){		
			if(unidad == 2)
				return true;
			else return false;
		
	}
	
	private boolean isServicioRural(String cod, int unidad){
		if(unidad == 3)
			return true;
		else return false;
	}
	
	/**
	 * devuelve la cantidad de lugares en vista.
	 * 
	 * @param codLugar
	 * @return
	 */
	private int getCantLugaresEnVista(String codLugar){
		Integer count = 0;
		try {
			count = VistaPrecioServicioIdiomaManager.instance().getCountVistaPrecioServicioByLugar(codLugar);
		}
		catch (Exception e) {
			Util.errMsg(Main.getVentana(), "Error al contar lugares en vista precioServicios", e);
		}
		return count;
	}
	
	private int getDescuentosSegunMes(String mes){
		try {
			int m = Integer.parseInt(mes);
			return VariacionMesManager.instance().getVariacionByMes(m);
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al obtener precio de servicio segun mes", e);
			return 0;
		}	
	}
	
	private int getDescuentosSegunFecha(String fecha){
		try {
			return VariacionFechaManager.instance().getVariacionFecha(fecha);
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al obtener precio de servicio segun fecha", e);
			return 0;
		}
	}
	
	private VariacionFecha[] getDescuentosFecha(String fecha){
		try {
			return VariacionFechaManager.instance().getVariacionesFecha(fecha);
		} catch (RemoteException e) {
			Util.errMsg(Main.getVentana(), "Error al obtener precio de servicio segun fecha", e);
			return null;
		}
	}
	
	/**
	 * Devuelve un descuento segun la cantidad de dias.
	 * 
	 * @param codServicio
	 * @return
	 */
	private double getDescuentoSegunDias(String codServicio){
		
		double desc = 0;		
		try{
			double n = ServicioManager.instance().getDescuentoByServicioAndTechoDias(codServicio, dias);
			desc = n;
		}
		catch (Exception e) {
			Util.errMsg(Main.getVentana(), "Error al obtener precio de servicio segun lugar", e);
		}
		return desc;
	}
	
	/**
	 * Busca los precios por servicios y lugar
	 * 
	 * @param codLugar
	 * @return
	 */
	private double getPrecioPorServicioYLugar(String codLugar) {
		// busco el precio en el cache
		Double precio = 0d;
		try {
			// traigo el precio de la base
			precio = VistaPrecioServicioIdiomaManager.instance().getVistaPrecioServicioIdiomaByServicioYLugar(servicioCodigo,codLugar);
			
		} catch (RemoteException e) {
			precio = 0d;
			Util.errMsg(Main.getVentana(), "Error al obtener precio de servicio segun lugar", e);
		}
		return precio;
	}

	public String getMesEvento() {
		return mesEvento;
	}
	
	public void setMesEvento(String mesEvento) {
		this.mesEvento = mesEvento;
	}
	
	public double getPrecioLista() {
		return precioLista;
	}
	
	public void setPrecioLista(double precioLista) {
		this.precioLista = precioLista;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

}
