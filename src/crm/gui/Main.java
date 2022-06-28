package crm.gui;

import java.io.IOException;

import javax.swing.JFrame;

import org.jvnet.substance.SubstanceLookAndFeel;

import crm.client.managers.SucursalManager;
import crm.client.managers.UnidadComercialManager;
import crm.client.managers.UsuarioManager;
import crm.client.util.SystemConfig;
import crm.gui.pantalla.PantallaEmergenteOS;
import crm.gui.pantalla.Ventana;

public class Main {
	
	private static Ventana vent;
	
	
	public Main() {
		vent = new Ventana();

	}
    
    public static Ventana getVentana(){
    	return vent;
    }
    
    public static void closeWindow(){
    	vent.dispose();
    	System.exit(0);
    }
    
    public static void main ( String[] args ) {

    	/*JFrame.setDefaultLookAndFeelDecorated(true); 
		SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.MistSilverSkin");*/
		new Main();
	}
}
