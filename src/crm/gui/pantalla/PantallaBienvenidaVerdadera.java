package crm.gui.pantalla;

import crm.gui.pantalla.solapa.MainPanelComercial;
import crm.gui.pantalla.solapa.MainPanelGastos;

public class PantallaBienvenidaVerdadera extends Pantalla{
	
	public static final int TYPE_CONFIRMADOS_UC = 0;
	public static final int TYPE_CONFIRMADOS_USER = 1;
	public static final int TYPE_PENDIENTES_UC = 2;
	public static final int TYPE_PENDIENTES_USER = 3;
	public static final int TYPE_DE_HOY_UC = 4;
	public static final int TYPE_DE_HOY_USER = 5;
	public static final int TYPE_A_VENCER_UC = 6;
	public static final int TYPE_A_VENCER_USER = 7;
	
	private static MainPanelComercial mainPanel2;
	
	private static MainPanelGastos mainPanelGastos;
	
	protected String codigoUC;

	public String getCodigoUC() {
		return codigoUC;
	}

	public void setCodigoUC(String codigoUC) {
		this.codigoUC = codigoUC;
	}

	public static MainPanelComercial getMainPanel2() {
		return mainPanel2;
	}
	
	public static MainPanelGastos getMainPanelGastos() {
		return mainPanelGastos;
	}
	
	public static void setMainPanel2(MainPanelComercial mainPanel) {
		if(PantallaBienvenidaVerdadera.mainPanel2 == null)
			PantallaBienvenidaVerdadera.mainPanel2 = mainPanel;
	}
	
	public static void setMainPanelGastos(MainPanelGastos mainPanel) {
		if(PantallaBienvenidaVerdadera.mainPanelGastos == null)
			PantallaBienvenidaVerdadera.mainPanelGastos = mainPanel;
	}
}
