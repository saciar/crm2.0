package crm.gui.tablerenderer.gastos;

import java.text.NumberFormat;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GastosAsistentesItem {
	private static final Log log = LogFactory.getLog(GastosAsistentesItem.class);
	private String asistente;
	private String asistenteId;
	private String cargo;
	private String jornada;
	private double costo;
	private static NumberFormat currencyFormat;
	
	public GastosAsistentesItem(){
	}


	public static Log getLog() {
		return log;
	}


	public String getAsistente() {
		return asistente;
	}


	public void setAsistente(String asistente) {
		this.asistente = asistente;
	}


	public String getCargo() {
		return cargo;
	}


	public void setCargo(String cargo) {
		this.cargo = cargo;
	}


	public double getCosto() {
		return costo;
	}

	public String getCostoFormateado(){
		return getCurrencyFormat().format(costo);
	}
	
	public void setCosto(double costo) {
		this.costo = costo;
	}


	public String getJornada() {
		return jornada;
	}


	public void setJornada(String jornada) {
		this.jornada = jornada;
	}


	public String getAsistenteId() {
		return asistenteId;
	}


	public void setAsistenteId(String asistenteId) {
		this.asistenteId = asistenteId;
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
