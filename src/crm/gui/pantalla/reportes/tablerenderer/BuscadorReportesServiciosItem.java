package crm.gui.pantalla.reportes.tablerenderer;

public class BuscadorReportesServiciosItem {

	private Long numeroPpto;
	private String servicio;
	private int cantidad;
	private int dias;
	private String cliente;
	private String nombreEvento;
	private String fechaInicio;
	private double totalServicio;
	private double totalEvento;
	private int descuento;	
	private String vendedor;
	private String fechaFin;
	private Double totalCompra;	
	
	public String getVendedor() {
		return vendedor;
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Double getTotalCompra() {
		return totalCompra;
	}
	public void setTotalCompra(Double totalCompra) {
		this.totalCompra = totalCompra;
	}
	public int getDescuento() {
		return descuento;
	}
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	public Long getNumeroPpto() {
		return numeroPpto;
	}
	public void setNumeroPpto(Long numeroPpto) {
		this.numeroPpto = numeroPpto;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getDias() {
		return dias;
	}
	public void setDias(int dias) {
		this.dias = dias;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getNombreEvento() {
		return nombreEvento;
	}
	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public double getTotalServicio() {
		return totalServicio;
	}
	public void setTotalServicio(double totalServicio) {
		this.totalServicio = totalServicio;
	}
	public double getTotalEvento() {
		return totalEvento;
	}
	public void setTotalEvento(double totalEvento) {
		this.totalEvento = totalEvento;
	}
	
	
}
