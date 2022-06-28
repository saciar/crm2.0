package crm.gui.tablerenderer.alertasCobranzas;

import java.io.Serializable;

public class AlertaCobranzaItem implements Serializable, Cloneable {
	private long numeroPresupuesto;
	private String asunto;
	private String creado;
	private String cliente;
	private String evento;
	private int tipoIcono;
	
	public int getTipoIcono() {
		return tipoIcono;
	}
	public void setTipoIcono(int tipoIcono) {
		this.tipoIcono = tipoIcono;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getCreado() {
		return creado;
	}
	public void setCreado(String creado) {
		this.creado = creado;
	}
	public long getNumeroPresupuesto() {
		return numeroPresupuesto;
	}
	public void setNumeroPresupuesto(long numeroPresupuesto) {
		this.numeroPresupuesto = numeroPresupuesto;
	}
	
	

}
