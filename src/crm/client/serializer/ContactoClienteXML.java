package crm.client.serializer;

import java.io.Serializable;

import org.jdom.Element;

public class ContactoClienteXML extends Element implements Serializable{
	
	private String codigoContactoCliente;
	
	public ContactoClienteXML() {
		super("ContactoCliente");
	}
	
	public String getCodigoContactoCliente() {
		return codigoContactoCliente;
	}

	public void setCodigoContactoCliente(String codigoContactoCliente) {
		this.codigoContactoCliente = codigoContactoCliente;
		addContent(new Element("CodigoContacto").setText(this.codigoContactoCliente));
	}
	
}
