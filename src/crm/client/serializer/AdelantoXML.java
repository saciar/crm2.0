package crm.client.serializer;

import java.io.Serializable;

import org.jdom.Element;

public class AdelantoXML extends Element implements Serializable{

	private String valor;
	private String porcentaje;
	
	public AdelantoXML() {
		super("Adelanto");
	}

	public String getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(String porcentaje) {
		this.porcentaje = porcentaje;
		addContent(new Element("Porcentaje").setText(this.porcentaje));
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
		addContent(new Element("Valor").setText(this.valor));
	}
	
	
}
