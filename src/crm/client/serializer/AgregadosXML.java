package crm.client.serializer;

import java.io.Serializable;

import org.jdom.Element;

public class AgregadosXML extends Element implements Serializable{
	private String codigoModoIngreso;
	private String codigoSeguridadIngreso;	
	private String codigoCategoriaEvento;
	
	public AgregadosXML(){
		super("Agregados");
	}
	
	public String getCodigoCategoriaEvento() {
		return codigoCategoriaEvento;
	}
	public void setCodigoCategoriaEvento(String codigoCategoriaEvento) {
		this.codigoCategoriaEvento = codigoCategoriaEvento;
		addContent(new Element("CategoriaEvento").setText(this.codigoCategoriaEvento));
	}
	public String getCodigoModoIngreso() {
		return codigoModoIngreso;
	}
	public void setCodigoModoIngreso(String codigoModoIngreso) {
		this.codigoModoIngreso = codigoModoIngreso;
		addContent(new Element("ModoIngreso").setText(this.codigoModoIngreso));
	}
	public String getCodigoSeguridadIngreso() {
		return codigoSeguridadIngreso;
	}
	public void setCodigoSeguridadIngreso(String codigoSeguridadIngreso) {
		this.codigoSeguridadIngreso = codigoSeguridadIngreso;
		addContent(new Element("SeguridadIngreso").setText(this.codigoSeguridadIngreso));
	}	
	
}
