package crm.gui.tablerenderer.gastos;

import java.text.NumberFormat;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GastosOperadoresItem {
	private static final Log log = LogFactory.getLog(GastosOperadoresItem.class);
	private String operadorId;
	private String operador;
	private String cargo;
	private String horario;
	private double costo;

	
	public GastosOperadoresItem(){
	}


	public static Log getLog() {
		return log;
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


	public void setCosto(double costo) {
		this.costo = costo;
	}


	public String getHorario() {
		return horario;
	}


	public void setHorario(String horario) {
		this.horario = horario;
	}


	public String getOperador() {
		return operador;
	}


	public void setOperador(String operador) {
		this.operador = operador;
	}


	public String getOperadorId() {
		return operadorId;
	}


	public void setOperadorId(String operadorId) {
		this.operadorId = operadorId;
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
