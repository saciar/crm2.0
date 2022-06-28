package crm.gui.tablerenderer.gastosSubcontratados;

import java.text.NumberFormat;
import java.util.Locale;

public class SubcontratadosTableItem {

	private int cantidad;
	private String sala;
	private String servicio; 
	private String proveedor;
	private String proveedorId;
	private double costo;
	private double precio;
	private double neto;

	
	public SubcontratadosTableItem(){
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getNeto() {
		return neto;
	}


	public void setNeto(double neto) {
		this.neto = neto;
	}


	public double getCosto() {
		return costo;
	}


	public void setCosto(double costo) {
		this.costo = costo;
	}

	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public String getProveedor() {
		return proveedor;
	}


	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	
	
	public String getProveedorId() {
		return proveedorId;
	}


	public void setProveedorId(String proveedorId) {
		this.proveedorId = proveedorId;
	}


	public String getServicio() {
		return servicio;
	}


	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	
	
	public String getSala() {
		return sala;
	}


	public void setSala(String sala) {
		this.sala = sala;
	}

	private static NumberFormat currencyFormat;
	
	public String getCostoFormateado(){
		return getCurrencyFormat().format(costo);
	}
	
	public String getPrecioFormateado(){
		return getCurrencyFormat().format(precio);
	}
	
	public String getNetoFormateado(){
		return getCurrencyFormat().format(neto);
	}
	
	private NumberFormat getCurrencyFormat() {
		if (currencyFormat == null){
			Locale l = new Locale("es","AR");
			currencyFormat = NumberFormat.getCurrencyInstance(l);
			//numberFormat.setMaximumFractionDigits(2);
			//numberFormat.setCurrency(Currency.getInstance(l));
		}
		
		return currencyFormat;
	}

}
