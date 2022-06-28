package crm.gui.pantalla.solapa;

import crm.client.validacion.Validador;
import crm.gui.pantalla.Pantalla;
import crm.libraries.abm.entities.Presupuesto;

public abstract class PantallaComercialAbstracta extends Pantalla implements Validador {
	public PantallaComercialAbstracta(){
		super();
	}
	
	/**
	 * Asigna el presupuesto para pre establecer los datos en el formulario
	 * @param p
	 */
	public abstract void setPresupuesto(Presupuesto p);
	
	/**
	 * Es para inicializar los datos de la pantalla
	 *
	 */
	abstract void init();
	
	/**
	 * Se dispara cuando el usuario entra en la solapa, es util para actualizar
	 * datos en la pantalla
	 *
	 */
	public void entraSolapa(){}
	
	/**
	 * Precarga los elementos de los combos
	 *
	 */
	public void loadItems(){}
}
