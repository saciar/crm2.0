package crm.client.serializer;

import java.io.Serializable;

import org.jdom.Element;

public class PagoXML extends Element implements Serializable{
	
	private String codigoMedioPago;
	private String codigoCondicionPago;
	
	public PagoXML() {
		super("Pago");
	}

	public String getCodigoCondicionPago() {
		return codigoCondicionPago;
	}

	public void setCodigoCondicionPago(String codigoCondicionPago) {
		this.codigoCondicionPago = codigoCondicionPago;
		addContent(new Element("CodigoCondicion").setText(this.codigoCondicionPago));
	}

	public String getCodigoMedioPago() {
		return codigoMedioPago;
	}

	public void setCodigoMedioPago(String codigoMedioPago) {
		this.codigoMedioPago = codigoMedioPago;
		addContent(new Element("CodigoMedio").setText(this.codigoMedioPago));
	}
	
}
