package crm.gui.tablerenderer.cobranzas.agenda;

import java.util.Date;

public class AgendaCobranzasItem {
	
	private String codigo;
	private long numeroDePresupuesto;
	private String tarea;
	private String fechaTarea;
	private Integer tipoIcono;	
	private boolean completada; 
	private String monto;
	private String factura;
	private int tipoFactura;
	
	public boolean isCompletada() {
		return completada;
	}
	public void setCompletada(boolean completada) {
		this.completada = completada;
	}
	public String getFechaTarea() {
		return fechaTarea;
	}
	public void setFechaTarea(String fechaTarea) {
		this.fechaTarea = fechaTarea;
	}
	public long getNumeroDePresupuesto() {
		return numeroDePresupuesto;
	}
	public void setNumeroDePresupuesto(long numeroDePresupuesto) {
		this.numeroDePresupuesto = numeroDePresupuesto;
	}
	public String getTarea() {
		return tarea;
	}
	public void setTarea(String tarea) {
		this.tarea = tarea;
	}
	public Integer getTipoIcono() {
		return tipoIcono;
	}
	public void setTipoIcono(Integer tipoIcono) {
		this.tipoIcono = tipoIcono;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getFactura() {
		return factura;
	}
	public void setFactura(String factura) {
		this.factura = factura;
	}
	public int getTipoFactura() {
		return tipoFactura;
	}
	public void setTipoFactura(int tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

}
