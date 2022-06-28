package crm.client.serializer;

import java.io.Serializable;

import org.jdom.Element;

public class RentabilidadXML extends Element implements Serializable{
	
	private String facturacionOriginal;
	private String facturacionExtra;
	private String costoOperativo;
	private String gastosAsistentes;
	private String gastosContrataciones;
	private String gastosOperadores;
	private String gastosOtros;
	private String comisionesLugar;
	private String comisionesTerceros;	
	
	public RentabilidadXML() {
		super("Rentabilidad");
	}

	public String getComisionesLugar() {
		return comisionesLugar;
	}

	public void setComisionesLugar(String comisionesLugar) {
		this.comisionesLugar = comisionesLugar;
		addContent(new Element("ComisionesLugar").setText(this.comisionesLugar));
	}

	public String getComisionesTerceros() {
		return comisionesTerceros;
	}

	public void setComisionesTerceros(String comisionesTerceros) {
		this.comisionesTerceros = comisionesTerceros;
		addContent(new Element("ComisionesTerceros").setText(this.comisionesTerceros));
	}

	public String getCostoOperativo() {
		return costoOperativo;
	}

	public void setCostoOperativo(String costoOperativo) {
		this.costoOperativo = costoOperativo;
		addContent(new Element("CostoOperativo").setText(this.costoOperativo));
	}

	public String getFacturacionExtra() {
		return facturacionExtra;
	}

	public void setFacturacionExtra(String facturacionExtra) {
		this.facturacionExtra = facturacionExtra;
		addContent(new Element("FacturacionExtra").setText(this.facturacionExtra));
	}

	public String getFacturacionOriginal() {
		return facturacionOriginal;
	}

	public void setFacturacionOriginal(String facturacionOriginal) {
		this.facturacionOriginal = facturacionOriginal;
		addContent(new Element("FacturacionOriginal").setText(this.facturacionOriginal));
	}

	public String getGastosAsistentes() {
		return gastosAsistentes;
	}

	public void setGastosAsistentes(String gastosAsistentes) {
		this.gastosAsistentes = gastosAsistentes;
		addContent(new Element("GastosAsistentes").setText(this.gastosAsistentes));
	}

	public String getGastosContrataciones() {
		return gastosContrataciones;
	}

	public void setGastosContrataciones(String gastosContrataciones) {
		this.gastosContrataciones = gastosContrataciones;
		addContent(new Element("GastosContrataciones").setText(this.gastosContrataciones));
	}

	public String getGastosOperadores() {
		return gastosOperadores;
	}

	public void setGastosOperadores(String gastosOperadores) {
		this.gastosOperadores = gastosOperadores;
		addContent(new Element("GastosOperadores").setText(this.gastosOperadores));
	}

	public String getGastosOtros() {
		return gastosOtros;
	}

	public void setGastosOtros(String gastosOtros) {
		this.gastosOtros = gastosOtros;
		addContent(new Element("GastosOtros").setText(this.gastosOtros));
	}
	
	
}
