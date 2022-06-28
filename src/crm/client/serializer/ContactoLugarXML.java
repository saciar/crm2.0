package crm.client.serializer;

import java.io.Serializable;

import org.jdom.Element;

public class ContactoLugarXML extends Element implements Serializable{
	
	private String codigoContactoLugar;
	
	public ContactoLugarXML() {
		super("ContactoLugar");
		// TODO Auto-generated constructor stub
	}
	
	public String getCodigoContactoLugar() {
		return codigoContactoLugar;
	}

	public void setCodigoContactoLugar(String codigoContactoCliente) {
		this.codigoContactoLugar = codigoContactoCliente;
		addContent(new Element("CodigoContacto").setText(this.codigoContactoLugar));
	}

}
