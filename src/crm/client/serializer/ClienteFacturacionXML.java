package crm.client.serializer;

import java.io.Serializable;

import org.jdom.Element;

public class ClienteFacturacionXML extends Element implements Serializable{
	
	private String codigoClienteFacturacion;
	
	public ClienteFacturacionXML() {
		super("ClienteFacturacion");
	}
	
	public String getCodigoClienteFacturacion() {
		return codigoClienteFacturacion;
	}

	public void setCodigoClienteFacturacion(String codigoClienteFacturacion) {
		this.codigoClienteFacturacion = codigoClienteFacturacion;
		addContent(new Element("CodigoCliente").setText(this.codigoClienteFacturacion));
	}

}
