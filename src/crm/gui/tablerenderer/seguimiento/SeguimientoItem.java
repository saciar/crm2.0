package crm.gui.tablerenderer.seguimiento;

import java.io.Serializable;

public class SeguimientoItem implements Serializable, Cloneable {
	
	private String fecha;
	
	private String usuario;
	
	private String accion;
	
	private String resultado;
	
	public SeguimientoItem(){
	}
	
	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
	
}
