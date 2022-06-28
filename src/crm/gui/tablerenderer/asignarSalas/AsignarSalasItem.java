package crm.gui.tablerenderer.asignarSalas;

import java.io.Serializable;

public class AsignarSalasItem implements Serializable, Cloneable {
	private String salaOrigen;
	private String salaDestino;
	
	public String getSalaDestino() {
		return salaDestino;
	}
	public void setSalaDestino(String salaDEstino) {
		this.salaDestino = salaDEstino;
	}
	public String getSalaOrigen() {
		return salaOrigen;
	}
	public void setSalaOrigen(String salaOrigen) {
		this.salaOrigen = salaOrigen;
	}

}
