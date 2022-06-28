package crm.client.serializer;

import java.io.Serializable;

import org.jdom.Element;

public class EstadoXML extends Element implements Serializable{
	
	private String nuevo;
	private String actualizado;
	private String confirmado;
	private String cancelado;    
	private String rechazado;
	private String ordenDeServicio;
	private String ordenDeFacturacion;
	private String ordenDeCompra;
	private String facturado;
	private String cobrado;
	private String adelanto;
	private String adelantado;
	
	public EstadoXML() {
		super("Estado");
	}

	public String getActualizado() {
		return actualizado;
	}

	public void setActualizado(String actualizado) {
		this.actualizado = actualizado;
		addContent(new Element("Actualizado").setText(this.actualizado));
	}

	public String getAdelantado() {
		return adelantado;
	}

	public void setAdelantado(String adelantado) {
		this.adelantado = adelantado;
		addContent(new Element("Adelantado").setText(this.adelantado));
	}

	public String getAdelanto() {
		return adelanto;
	}

	public void setAdelanto(String adelanto) {
		this.adelanto = adelanto;
		addContent(new Element("Adelanto").setText(this.adelanto));
	}

	public String getCancelado() {
		return cancelado;
	}

	public void setCancelado(String cancelado) {
		this.cancelado = cancelado;
		addContent(new Element("Cancelado").setText(this.cancelado));
	}

	public String getCobrado() {
		return cobrado;
	}

	public void setCobrado(String cobrado) {
		this.cobrado = cobrado;
		addContent(new Element("Cobrado").setText(this.cobrado));
	}

	public String getConfirmado() {
		return confirmado;
	}

	public void setConfirmado(String confirmado) {
		this.confirmado = confirmado;
		addContent(new Element("Confirmado").setText(this.confirmado));
	}

	public String getFacturado() {
		return facturado;
	}

	public void setFacturado(String facturado) {
		this.facturado = facturado;
		addContent(new Element("Facturado").setText(this.facturado));
	}

	public String getNuevo() {
		return nuevo;
	}

	public void setNuevo(String nuevo) {
		this.nuevo = nuevo;
		addContent(new Element("Nuevo").setText(this.nuevo));
	}

	public String getOrdenDeCompra() {
		return ordenDeCompra;
	}

	public void setOrdenDeCompra(String ordenDeCompra) {
		this.ordenDeCompra = ordenDeCompra;
		addContent(new Element("OrdenDeCompra").setText(this.ordenDeCompra));
	}

	public String getOrdenDeFacturacion() {
		return ordenDeFacturacion;
	}

	public void setOrdenDeFacturacion(String ordenDeFacturacion) {
		this.ordenDeFacturacion = ordenDeFacturacion;
		addContent(new Element("OrdenDeFacturacion").setText(this.ordenDeFacturacion));
	}

	public String getOrdenDeServicio() {
		return ordenDeServicio;
	}

	public void setOrdenDeServicio(String ordenDeServicio) {
		this.ordenDeServicio = ordenDeServicio;
		addContent(new Element("OrdenDeServicio").setText(this.ordenDeServicio));
	}

	public String getRechazado() {
		return rechazado;
	}

	public void setRechazado(String rechazado) {
		this.rechazado = rechazado;
		addContent(new Element("Rechazado").setText(this.rechazado));
	}
	
	
}
