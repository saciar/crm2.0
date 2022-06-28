package crm.gui.abms;

import java.net.URL;

import crm.gui.Main;

public class ABMGeneral {

	public URL getUrlImagen(String imagen){
	    
    	URL url =Main.class.getResource("imagenes/"+imagen); 

    	return url;
    }

}
