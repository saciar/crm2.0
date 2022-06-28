package crm.gui.tablerenderer.cobranzas;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CobranzasItem {
	private static final Log log = LogFactory.getLog(CobranzasItem.class);
	
	private long numeroDePresupuesto;
	private String razonSocial;
	private String clienteFacturacion;
	private String factura;
	private String facturaAdelanto;
	private String facturaAdicional;
	private String fechaFacturado;
	private String importeTotal;
	private Integer tipoIcono;	
	
	private String fechaConfirmacion;	
	private String condicionPago;
	private String observaciones;	
	private String lugar;
	private String contacto;
	private String codClienteFacturacion;
	private String comercial;
	private String adelanto;
	private String fechaFacturaAdelanto;
	private String fechaFacturaAdicional;
	
	private String estado;
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFechaFacturaAdelanto() {
		return fechaFacturaAdelanto;
	}

	public void setFechaFacturaAdelanto(String fechaFacturaAdelanto) {
		this.fechaFacturaAdelanto = fechaFacturaAdelanto;
	}

	public String getFechaFacturaAdicional() {
		return fechaFacturaAdicional;
	}

	public void setFechaFacturaAdicional(String fechaFacturaAdicional) {
		this.fechaFacturaAdicional = fechaFacturaAdicional;
	}

	public String getAdelanto() {
		return adelanto;
	}

	public void setAdelanto(String adelanto) {
		this.adelanto = adelanto;
	}

	public String getCodClienteFacturacion() {
		return codClienteFacturacion;
	}

	public void setCodClienteFacturacion(String codClienteFacturacion) {
		this.codClienteFacturacion = codClienteFacturacion;
	}

	public String getComercial() {
		return comercial;
	}

	public void setComercial(String comercial) {
		this.comercial = comercial;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	/**
	 * @return Returns the factura.
	 */
	public String getFactura() {
		return factura;
	}

	/**
	 * @param factura The factura to set.
	 */
	public void setFactura(String factura) {
		this.factura = factura;
	}

	public CobranzasItem(){
	}

	public static Log getLog() {
		return log;
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


	public String getFechaFacturado() {
		return fechaFacturado;
	}


	public void setFechaFacturado(String fechaFacturado) {
		this.fechaFacturado = fechaFacturado;
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
	 * @return Returns the clienteFacturacion.
	 */
	public String getClienteFacturacion() {
		return clienteFacturacion;
	}

	/**
	 * @param clienteFacturacion The clienteFacturacion to set.
	 */
	public void setClienteFacturacion(String clienteFacturacion) {
		this.clienteFacturacion = clienteFacturacion;
	}

	public String getCondicionPago() {
		return condicionPago;
	}

	public void setCondicionPago(String condicionPago) {
		this.condicionPago = condicionPago;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getFacturaAdelanto() {
		return facturaAdelanto;
	}

	public void setFacturaAdelanto(String facturaAdelanto) {
		this.facturaAdelanto = facturaAdelanto;
	}

	public String getFacturaAdicional() {
		return facturaAdicional;
	}

	public void setFacturaAdicional(String facturaAdicional) {
		this.facturaAdicional = facturaAdicional;
	}

	public Integer getTipoIcono() {
		return tipoIcono;
	}

	public void setTipoIcono(Integer tipoIcono) {
		this.tipoIcono = tipoIcono;
	}

}
