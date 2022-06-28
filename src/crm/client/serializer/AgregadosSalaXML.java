package crm.client.serializer;

import java.io.Serializable;

import org.jdom.Element;

public class AgregadosSalaXML extends Element implements Serializable{
	private String fechaDesarme;
	private String fechaPrueba;
	private Integer codigoTipoArmado;
	
	public AgregadosSalaXML(){
		super("AgregadosSala");
	}
	
	public Integer getCodigoTipoArmado() {
		return codigoTipoArmado;
	}
	public void setCodigoTipoArmado(Integer codigoTipoDesarme) {
		this.codigoTipoArmado = codigoTipoDesarme;
		addContent(new Element("CodigoTipoDesarme").setText(this.codigoTipoArmado.toString()));
	}
	public String getFechaDesarme() {
		return fechaDesarme;
	}
	public void setFechaDesarme(String fechaDesarme) {
		this.fechaDesarme = fechaDesarme;
		addContent(new Element("FechaDesarme").setText(this.fechaDesarme));
	}
	public String getFechaPrueba() {
		return fechaPrueba;
	}
	public void setFechaPrueba(String fechaPrueba) {
		this.fechaPrueba = fechaPrueba;
		addContent(new Element("FechaPrueba").setText(this.fechaPrueba));
	}
	
	
}
