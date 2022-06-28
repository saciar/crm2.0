package crm.gui.pantalla.reportes.tablerenderer;

public class BuscadorReportesItem {

	private Long numeroPpto;
	private String estado;
	private String vendedor;
	private String cliente;
	private String nombreEvento;
	private String fechaInicio;
	private double total;
	private String fechaFinal;
	private String fechaCreacion;
	private String lugar;
	private String codLugar;
	private String tipoEvento;
	
	public String getTipoEvento() {
		return tipoEvento;
	}
	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	public String getCodLugar() {
		return codLugar;
	}
	public void setCodLugar(String codLugar) {
		this.codLugar = codLugar;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Long getNumeroPpto() {
		return numeroPpto;
	}
	public void setNumeroPpto(Long numeroPpto) {
		this.numeroPpto = numeroPpto;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getVendedor() {
		return vendedor;
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
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
	public String getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}	
}
