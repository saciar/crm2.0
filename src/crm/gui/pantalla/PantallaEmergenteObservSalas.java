package crm.gui.pantalla;

import java.awt.Frame;

public class PantallaEmergenteObservSalas extends PantallaEmergenteObserv{
	
	public PantallaEmergenteObservSalas(Frame owner){
		super(owner);
		cargarPantalla("Observaciones de las salas");
	}

    /**
     * valida que se hayan ingresado los datos
     *//*
	public boolean validate(String value){
        if (!StringUtils.isBlank(value)){
            JOptionPane.showMessageDialog( null, "Complete todos los campos",
                    "Atención", JOptionPane.WARNING_MESSAGE );
            
            return false;
        }
        
        return true;
	}*/
}
