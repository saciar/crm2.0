package crm.gui.pantalla.solapa;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import crm.gui.tablerenderer.horarios.HorariosItem;

public class SalaModel extends Observable {

	private String codigoSala; // codigo de la sala
	private String nombreSala;	// nombre de la sala
	private String fechaDeInicio; // fecha de inicio de uso de la sala
	private String fechaDeFinalizacion; // fecha de fin de uso de la sala 
	private String horaDeInicio; // hora de inicio de uso de la sala
	private String horaDeFinalizacion;	// hora de fin de uso de la sala 
	private String totalDePersonas; // total de personas q acudirán a esta sala
	private String observaciones; // observaciones de la sala
	private int descuento;
	
	private double total = 0d; // total de una sala, o bien, de la grilla con descuentos
	private double totalSinDecuento = 0d;
	
	private String fechaPrueba;
	private String horaPrueba;
	private String fechaDesarme;
	private String horaDesarme;
	private String codigoTipoArmado;	
	private String nroOrden;
	
	private List<HorariosItem> horarios;
	private Object[] servicios;
	private String codigoLugar;
	private String nombreSalaUnica;
	
	public SalaModel() {
		
	}

	public String toString() {
		return nombreSala;
	}
	
	public String getNombreSalaUnica() {
		return nombreSalaUnica;
	}

	public void setNombreSalaUnica(String nombreSalaUnica) {
		this.nombreSalaUnica = nombreSalaUnica;
		setChanged();
	    notifyObservers();
	}

	public String getCodigoSala() {
		return codigoSala;
	}
	public void setCodigoSala(String codigoSala) {
		this.codigoSala = codigoSala;
		setChanged();
	    notifyObservers();
	}
	public String getCodigoTipoArmado() {
		return codigoTipoArmado;
	}
	public void setCodigoTipoArmado(String codigoTipoArmado) {
		this.codigoTipoArmado = codigoTipoArmado;
		setChanged();
	    notifyObservers();
	}
	public String getFechaDeFinalizacion() {
		return fechaDeFinalizacion;
	}
	public void setFechaDeFinalizacion(String fechaDeFinalizacion) {
		this.fechaDeFinalizacion = fechaDeFinalizacion;
		setChanged();
	    notifyObservers();
	}
	public String getFechaDeInicio() {
		return fechaDeInicio;
	}
	public void setFechaDeInicio(String fechaDeInicio) {
		this.fechaDeInicio = fechaDeInicio;
		setChanged();
	    notifyObservers();
	}
	public String getFechaDesarme() {
		return fechaDesarme;
	}
	public void setFechaDesarme(String fechaDesarme) {
		this.fechaDesarme = fechaDesarme;
		setChanged();
	    notifyObservers();
	}
	public String getFechaPrueba() {
		return fechaPrueba;
	}
	public void setFechaPrueba(String fechaPrueba) {
		this.fechaPrueba = fechaPrueba;
		setChanged();
	    notifyObservers();
	}
	public String getHoraDeFinalizacion() {
		return horaDeFinalizacion;
	}
	public void setHoraDeFinalizacion(String horaDeFinalizacion) {
		this.horaDeFinalizacion = horaDeFinalizacion;
		setChanged();
	    notifyObservers();
	}
	public String getHoraDeInicio() {
		return horaDeInicio;
	}
	public void setHoraDeInicio(String horaDeInicio) {
		this.horaDeInicio = horaDeInicio;
		setChanged();
	    notifyObservers();
	}
	public String getHoraDesarme() {
		return horaDesarme;
	}
	public void setHoraDesarme(String horaDesarme) {
		this.horaDesarme = horaDesarme;
		setChanged();
	    notifyObservers();
	}
	public String getHoraPrueba() {
		return horaPrueba;
	}
	public void setHoraPrueba(String horaPrueba) {
		this.horaPrueba = horaPrueba;
		setChanged();
	    notifyObservers();
	}
	public String getNombreSala() {
		return nombreSala;
	}
	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
		setChanged();
	    notifyObservers();
	}
	public String getNroOrden() {
		return nroOrden;
	}
	public void setNroOrden(String nroOrden) {
		this.nroOrden = nroOrden;
		setChanged();
	    notifyObservers();
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
		setChanged();
	    notifyObservers();
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	//	setChanged();
	   // notifyObservers();
	}
	public String getTotalDePersonas() {
		return totalDePersonas;
	}
	public void setTotalDePersonas(String totalDePersonas) {
		this.totalDePersonas = totalDePersonas;
		setChanged();
	    notifyObservers();
	}
	public double getTotalSinDecuento() {
		return totalSinDecuento;
	}
	public void setTotalSinDecuento(double totalSinDecuento) {
		this.totalSinDecuento = totalSinDecuento;
		//setChanged();
	   // notifyObservers();
	}

	public Object[] getServicios() {
		return servicios;
	}

	public void setServicios(Object[] servicios) {
		this.servicios = servicios;
		setChanged();
	    notifyObservers();
	}

	public List<HorariosItem> getHorarios(){
		return horarios;
	}
	
	public void setHorarios(List<HorariosItem> servicios) {
		this.horarios = servicios;
		setChanged();
	    notifyObservers();
	}
	
	public String getCodigoLugar() {
		return codigoLugar;
	}

	public void setCodigoLugar(String codigoLugar) {
		this.codigoLugar = codigoLugar;
		//setChanged();
	    //notifyObservers();
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
		setChanged();
	    notifyObservers();
	}
}
