package crm.gui.tablerenderer.gastos;

import java.text.NumberFormat;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GastosSubContratacionesGeneralItem {
	private static final Log log = LogFactory.getLog(GastosSubContratacionesGeneralItem.class);
	
	private String detalle;
	private String proveedor;
	private String proveedorId;
	private double costo;
	private double precio;
	private double neto;

	
	public GastosSubContratacionesGeneralItem(){
	}


	public static Log getLog() {
		return log;
	}

	public double getCosto() {
		return costo;
	}


	public void setCosto(double costo) {
		this.costo = costo;
	}


	public String getDetalle() {
		return detalle;
	}


	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}


	public double getNeto() {
		return neto;
	}


	public void setNeto(double neto) {
		this.neto = neto;
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
