package crm.gui.tablerenderer.historialCliente;

import java.io.Serializable;

public class HistorialClienteItem implements Serializable, Cloneable {
	private long numeroPresupuesto;
	private String fechaEvento;
	private String numeroFacturas;
	private String numeroFacturas2;
	public String getNumeroFacturas2() {
		return numeroFacturas2;
	}
	public void setNumeroFacturas2(String numeroFacturas2) {
		this.numeroFacturas2 = numeroFacturas2;
	}
	private String importeTotal;
	private String vendedor;
	private String evento;
	/**
	 * @return Returns the vendedor.
	 */
	public String getVendedor() {
		return vendedor;
	}
	/**
	 * @param vendedor The vendedor to set.
	 */
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	/**
	 * @return Returns the fechaEvento.
	 */
	public String getFechaEvento() {
		return fechaEvento;
	}
	/**
	 * @param fechaEvento The fechaEvento to set.
	 */
	public void setFechaEvento(String fechaEvento) {
		this.fechaEvento = fechaEvento;
	}
	/**
	 * @return Returns the importeTotal.
	 */
	public String getImporteTotal() {
		return importeTotal;
	}
	/**
	 * @param importeTotal The importeTotal to set.
	 */
	public void setImporteTotal(String importeTotal) {
		this.importeTotal = importeTotal;
	}
	/**
	 * @return Returns the numeroFacturas.
	 */
	public String getNumeroFacturas() {
		return numeroFacturas;
	}
	/**
	 * @param numeroFacturas The numeroFacturas to set.
	 */
	public void setNumeroFacturas(String numeroFacturas) {
		this.numeroFacturas = numeroFacturas;
	}
	/**
	 * @return Returns the numeroPresupuesto.
	 */
	public long getNumeroPresupuesto() {
		return numeroPresupuesto;
	}
	/**
	 * @param numeroPresupuesto The numeroPresupuesto to set.
	 */
	public void setNumeroPresupuesto(long numeroPresupuesto) {
		this.numeroPresupuesto = numeroPresupuesto;
	}
	/**
	 * @return Returns the evento.
	 */
	public String getEvento() {
		return evento;
	}
	/**
	 * @param evento The evento to set.
	 */
	public void setEvento(String evento) {
		this.evento = evento;
	}

}
