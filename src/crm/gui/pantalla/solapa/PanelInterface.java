package crm.gui.pantalla.solapa;

import crm.libraries.abm.entities.Presupuesto;

public interface PanelInterface {
	
	public void init();
	
	public void initLayout();
	
	public void setPresupuesto(Presupuesto presupuesto);
}
