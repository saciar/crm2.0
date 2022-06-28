package crm.client.serializer;

import java.io.Serializable;

import org.jdom.Element;

public class SalasXML extends Element implements Serializable{
	
	private String orden;
	private String codigoSalaLugar;
	private String fechaDeInstalacion;
	private String fechaDeInicio;
	private String fechaDeFinalizacion;
	private String totalPersonas;
	private String observaciones;
	private ServiciosXML[] servicios;
	//private AgregadosSalaXML agregados;
	
	private String fechaPrueba;
	private String fechaDesarme;
	private String tipoArmado;
	
	public String getFechaDesarme() {
		return fechaDesarme;
	}

	public void setFechaDesarme(String fechaDesarme) {
		this.fechaDesarme = fechaDesarme;
		addContent(new Element("FechaDesarme").setText(this.fechaDesarme));
	}

	public String getFechaPrueba() {
		return fechaPrueba;
	}

	public void setFechaPrueba(String fechaPrueba) {
		this.fechaPrueba = fechaPrueba;
		addContent(new Element("FechaPrueba").setText(this.fechaPrueba));
	}

	public String getTipoArmado() {
		return tipoArmado;
	}

	public void setTipoArmado(String tipoArmado) {
		this.tipoArmado = tipoArmado;
		addContent(new Element("CodigoTipoDesarme").setText(this.tipoArmado));
	}

	public SalasXML(){
		super("Salas");		
	}

	/*public AgregadosSalaXML getAgregados() {
		return agregados;
	}

	public void setAgregados(AgregadosSalaXML agregados) {
		this.agregados = agregados;
		addContent(agregados);
	}*/

	public String getCodigoSalaLugar() {
		return codigoSalaLugar;
	}

	public void setCodigoSalaLugar(String codigoSalaLugar) {
		this.codigoSalaLugar = codigoSalaLugar;
		addContent(new Element("CodigoLugarSala").setText(this.codigoSalaLugar));
	}

	public String getFechaDeFinalizacion() {
		return fechaDeFinalizacion;
	}

	public void setFechaDeFinalizacion(String fechaDeFinalizacion) {
		this.fechaDeFinalizacion = fechaDeFinalizacion;
		addContent(new Element("FechaFinalizacionSala").setText(this.fechaDeFinalizacion));
	}

	public String getFechaDeInicio() {
		return fechaDeInicio;
	}

	public void setFechaDeInicio(String fechaDeInicio) {
		this.fechaDeInicio = fechaDeInicio;
		addContent(new Element("FechaInicioSala").setText(this.fechaDeInicio));
	}

	public String getFechaDeInstalacion() {
		return fechaDeInstalacion;
	}

	public void setFechaDeInstalacion(String fechaDeInstalacion) {
		this.fechaDeInstalacion = fechaDeInstalacion;
		addContent(new Element("FechaInstalacionSala").setText(this.fechaDeInstalacion));
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
		addContent(new Element("ObservacionSala").setText(this.observaciones));
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
		addContent(new Element("Orden").setText(this.orden));
	}

	public ServiciosXML[] getServicios() {
		return servicios;
	}

	public void setServicios(ServiciosXML[] servicios) {
		this.servicios = servicios;
		//addContent(servicios);
	}

	public String getTotalPersonas() {
		return totalPersonas;
	}

	public void setTotalPersonas(String totalPersonas) {
		this.totalPersonas = totalPersonas;
		addContent(new Element("TotalPersonasSala").setText(this.totalPersonas));
	}
	
	
}
