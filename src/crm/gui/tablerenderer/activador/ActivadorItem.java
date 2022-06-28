package crm.gui.tablerenderer.activador;

import java.io.Serializable;

public class ActivadorItem implements Serializable, Cloneable {
	private String numeroPpto;
	private String estado;
	private String vendedor;
	private String cliente;
	private String nombreEvento;
	private String activo;
	
	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public ActivadorItem(){
		
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNombreEvento() {
		return nombreEvento;
	}

	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}

	public String getNumeroPpto() {
		return numeroPpto;
	}

	public void setNumeroPpto(String numeroPpto) {
		this.numeroPpto = numeroPpto;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	
}
