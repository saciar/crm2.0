package crm.gui.tablerenderer.gastos;

import java.text.NumberFormat;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GastosHoteleriaItem {
	private static final Log log = LogFactory.getLog(GastosHoteleriaItem.class);
	private String detalle;
	private double costo;

	
	public GastosHoteleriaItem(){
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
	
	private static NumberFormat currencyFormat;
	
	public String getCostoFormateado(){
		return getCurrencyFormat().format(costo);
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
