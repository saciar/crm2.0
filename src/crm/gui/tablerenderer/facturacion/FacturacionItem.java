package crm.gui.tablerenderer.facturacion;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FacturacionItem {
	
	private long numeroDePresupuesto;
	private String razonSocial;
	private String fechaConfirmacion;
	private String importeTotal;
	private String estado;
	private String vendedor;
	private String facturas;
	
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
	 * @return Returns the estado.
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado The estado to set.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public FacturacionItem(){
	}

	public String getFechaConfirmacion() {
		return fechaConfirmacion;
	}


	public void setFechaConfirmacion(String fechaConfirmacion) {
		this.fechaConfirmacion = fechaConfirmacion;
	}


	public String getRazonSocial() {
		return razonSocial;
	}


	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getImporteTotal() {
		return importeTotal;
	}


	public void setImporteTotal(String importeTotal) {
		this.importeTotal = importeTotal;
	}


	public long getNumeroDePresupuesto() {
		return numeroDePresupuesto;
	}


	public void setNumeroDePresupuesto(long numeroDePresupuesto) {
		this.numeroDePresupuesto = numeroDePresupuesto;
	}

	/**
	 * @return Returns the factura.
	 */
	public String getFacturas() {
		return facturas;
	}

	/**
	 * @param factura The factura to set.
	 */
	public void setFacturas(String facturas) {
		this.facturas = facturas;
	}
}
