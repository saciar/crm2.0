package crm.client.serializer;

import java.io.Serializable;

import org.jdom.Element;

public class DescripcionDetalladaXML extends Element implements Serializable{
	private String descripcion;
	
	public DescripcionDetalladaXML(){
		super("DescripcionDetallada");
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
		addContent(new Element("Descripcion").setText(this.descripcion));
	}	
	
}
