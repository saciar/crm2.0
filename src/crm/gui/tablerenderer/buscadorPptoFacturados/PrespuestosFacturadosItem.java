package crm.gui.tablerenderer.buscadorPptoFacturados;

public class PrespuestosFacturadosItem {
	private long nroPpto;
	private String nombreEvento;
	private String cliente;
	private String nroFactura;
	private String nroFacturaAdelanto;
	private String fechaFactura;
	private String fechaFacturaAdelanto;
	private long id_factura1;
	private long id_factura2;
	private String facturadoPor;
		
	public String getFacturadoPor() {
		return facturadoPor;
	}
	public void setFacturadoPor(String facturadoPor) {
		this.facturadoPor = facturadoPor;
	}
	public long getId_factura1() {
		return id_factura1;
	}
	public void setId_factura1(long id_factura1) {
		this.id_factura1 = id_factura1;
	}
	public long getId_factura2() {
		return id_factura2;
	}
	public void setId_factura2(long id_factura2) {
		this.id_factura2 = id_factura2;
	}
	public long getNroPpto() {
		return nroPpto;
	}
	public void setNroPpto(long nroPpto) {
		this.nroPpto = nroPpto;
	}
	public String getNombreEvento() {
		return nombreEvento;
	}
	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getNroFactura() {
		return nroFactura;
	}
	public void setNroFactura(String nroFactura) {
		this.nroFactura = nroFactura;
	}
	public String getNroFacturaAdelanto() {
		return nroFacturaAdelanto;
	}
	public void setNroFacturaAdelanto(String nroFacturaAdelanto) {
		this.nroFacturaAdelanto = nroFacturaAdelanto;
	}
	public String getFechaFactura() {
		return fechaFactura;
	}
	public void setFechaFactura(String fechaFactura) {
		this.fechaFactura = fechaFactura;
	}
	public String getFechaFacturaAdelanto() {
		return fechaFacturaAdelanto;
	}
	public void setFechaFacturaAdelanto(String fechaFacturaAdelanto) {
		this.fechaFacturaAdelanto = fechaFacturaAdelanto;
	}
	
	
	
}
