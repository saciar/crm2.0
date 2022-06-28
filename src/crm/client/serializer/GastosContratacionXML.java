package crm.client.serializer;

import java.io.Serializable;

import org.jdom.Element;

public class GastosContratacionXML extends Element implements Serializable{
	
	private String servicio;
	private String codigoProveedor;
	private String proveedor;
	private String costo;
	private String precio;	
	private String detalle;
	private String sala;
	private int cantidad;

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public GastosContratacionXML() {
		super("GastosContratacion");
	}
	
	public String getCodigoProveedor() {
		return codigoProveedor;
	}

	public void setCodigoProveedor(String codigoProveedor) {
		this.codigoProveedor = codigoProveedor;
		addContent(new Element("CodigoProveedor").setText(this.codigoProveedor));
	}

	public String getCosto() {
		return costo;
	}

	public void setCosto(String costo) {
		this.costo = costo;
		addContent(new Element("Costo").setText(this.costo));
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
		addContent(new Element("Precio").setText(this.precio));
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
		addContent(new Element("Proveedor").setText(this.proveedor));
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
		addContent(new Element("Servicio").setText(this.servicio));
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

}
