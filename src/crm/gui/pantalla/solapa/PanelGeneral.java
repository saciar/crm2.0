package crm.gui.pantalla.solapa;

import java.net.MalformedURLException;
import java.net.URL;

import crm.gui.Main;

public class PanelGeneral {
	
	private static final String URL_IMAGENES = "file:/C:\\workspace\\CongressCrmClient\\imagenes\\";
	
	/*public URL getUrlImagen(String imagen){
    	URL url = null;
		try {
			url = new URL(URL_IMAGENES+imagen);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    	return url;
    }*/
	
	public URL getUrlImagen(String imagen){
	    
    	URL url =Main.class.getResource("imagenes/"+imagen); 

    	return url;
    }
}
