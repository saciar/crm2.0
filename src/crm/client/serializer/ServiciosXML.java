package crm.client.serializer;

import java.io.Serializable;

import org.jdom.Element;

public class ServiciosXML extends Element implements Serializable{
	private String cantidad;
	private String codigoServicio;
	private String precioSinDescuento;
	private String precioConDescuento;
	private String modalidadContratacion;
	private String detalle;
	private String descuento;
	private String fechaAlta;
	private String orden;
	private String modificado;
	
	private DescripcionDetalladaXML[] descripcionDetallada;
	private String dias;
	
	public ServiciosXML(){
		super("Servicios");
	}
	
	public String getModificado() {
		return modificado;
	}

	public void setModificado(String modificado) {
		this.modificado = modificado;
	}

	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
		addContent(new Element("Cantidad").setText(this.cantidad));
	}
	public String getCodigoServicio() {
		return codigoServicio;
	}
	public void setCodigoServicio(String codigoServicio) {
		this.codigoServicio = codigoServicio;
		addContent(new Element("CodigoServicio").setText(this.codigoServicio));
	}
	public DescripcionDetalladaXML[] getDescripcionDetallada() {
		return descripcionDetallada;
	}

	public void setDescripcionDetallada(DescripcionDetalladaXML[] descripcionDetallada) {
		this.descripcionDetallada = descripcionDetallada;
		//addContent(descripcionDetallada);
	}

	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
		addContent(new Element("Detalle").setText(this.detalle));
	}
	public String getDias() {
		return dias;
	}
	public void setDias(String dias) {
		this.dias = dias;
		addContent(new Element("Dias").setText(this.dias));
	}
	public String getModalidadContratacion() {
		return modalidadContratacion;
	}
	public void setModalidadContratacion(String modalidadContratacion) {
		this.modalidadContratacion = modalidadContratacion;
		addContent(new Element("ModalidadContratacion").setText(this.modalidadContratacion));
	}
	public String getPrecioConDescuento() {
		return precioConDescuento;
	}
	public void setPrecioConDescuento(String precioConDescuento) {
		this.precioConDescuento = precioConDescuento;
		addContent(new Element("PrecioConDescuento").setText(this.precioConDescuento));
	}
	public String getPrecioSinDescuento() {
		return precioSinDescuento;
	}
	public void setPrecioSinDescuento(String precioSinDescuento) {
		this.precioSinDescuento = precioSinDescuento;
		addContent(new Element("PrecioSinDescuento").setText(this.precioSinDescuento));
	}

	public String getDescuento() {
		return descuento;
	}

	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}
	
	
	
}
