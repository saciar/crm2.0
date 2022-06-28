package crm.gui.tablerenderer.buscadorPptoOperador;

import java.io.Serializable;

public class BuscadorPptosOperadorItem implements Serializable, Cloneable {
	private Long numeroPpto;
	private String lugarEvento;
	private String nombreEvento;
	
	public Long getNumeroPpto() {
		return numeroPpto;
	}
	public void setNumeroPpto(Long numeroPpto) {
		this.numeroPpto = numeroPpto;
	}
	public String getLugarEvento() {
		return lugarEvento;
	}
	public void setLugarEvento(String lugarEvento) {
		this.lugarEvento = lugarEvento;
	}
	public String getNombreEvento() {
		return nombreEvento;
	}
	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}

}
