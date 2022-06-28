package crm.gui.pantalla.reportes.tablerenderer;

public class BuscadorReportesRentabilidadItem {
	
	private Long numeroPpto;
	private double totalFacturado;
	private double costoOperativo;
	private double GastosSubcontratados;
	private double otros;
	private double comisionLugar;
	private double comisionTerceros;
	private double comisionComercial;
	private double regalias;
	private double costoOperativoReal;
	public double getCostoOperativoReal() {
		return costoOperativoReal;
	}
	public void setCostoOperativoReal(double costoOperativoReal) {
		this.costoOperativoReal = costoOperativoReal;
	}
	private String nombreEvento;
	private String vendedor;
	private double rentabilidad;
	public double getRentabilidadReal() {
		return rentabilidadReal;
	}
	public void setRentabilidadReal(double rentabilidadReal) {
		this.rentabilidadReal = rentabilidadReal;
	}
	public double getMargenReal() {
		return margenReal;
	}
	public void setMargenReal(double margenReal) {
		this.margenReal = margenReal;
	}
	private double rentabilidadReal;
	private double margen;
	private double margenReal;
	
	public double getMargen() {
		return margen;
	}
	public void setMargen(double margen) {
		this.margen = margen;
	}
	public Long getNumeroPpto() {
		return numeroPpto;
	}
	public void setNumeroPpto(Long numeroPpto) {
		this.numeroPpto = numeroPpto;
	}
	public double getTotalFacturado() {
		return totalFacturado;
	}
	public void setTotalFacturado(double totalFacturado) {
		this.totalFacturado = totalFacturado;
	}
	public double getCostoOperativo() {
		return costoOperativo;
	}
	public void setCostoOperativo(double costoOperativo) {
		this.costoOperativo = costoOperativo;
	}
	public double getGastosSubcontratados() {
		return GastosSubcontratados;
	}
	public void setGastosSubcontratados(double gastosSubcontratados) {
		GastosSubcontratados = gastosSubcontratados;
	}
	public double getOtros() {
		return otros;
	}
	public void setOtros(double otros) {
		this.otros = otros;
	}
	public double getComisionLugar() {
		return comisionLugar;
	}
	public void setComisionLugar(double comisionLugar) {
		this.comisionLugar = comisionLugar;
	}
	public double getComisionTerceros() {
		return comisionTerceros;
	}
	public void setComisionTerceros(double comisionTerceros) {
		this.comisionTerceros = comisionTerceros;
	}
	public double getComisionComercial() {
		return comisionComercial;
	}
	public void setComisionComercial(double comisionComercial) {
		this.comisionComercial = comisionComercial;
	}
	public double getRegalias() {
		return regalias;
	}
	public void setRegalias(double regalias) {
		this.regalias = regalias;
	}
	public String getNombreEvento() {
		return nombreEvento;
	}
	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}
	public String getVendedor() {
		return vendedor;
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	public double getRentabilidad() {
		return rentabilidad;
	}
	public void setRentabilidad(double rentabilidad) {
		this.rentabilidad = rentabilidad;
	}	
	
}
