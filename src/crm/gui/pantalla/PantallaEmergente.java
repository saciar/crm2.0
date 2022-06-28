package crm.gui.pantalla;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JMenu;

import crm.gui.Main;

public class PantallaEmergente extends JDialog{
	
	private static final String URL_IMAGENES = "file:/C:\\workspace\\CRM 2.0\\imagenes\\";
	private javax.swing.JMenuBar jMenuBar1;
	private Frame owner;
	
	public PantallaEmergente(String title, Frame owner){
		super(owner);
		this.owner = owner;
		this.setTitle(title);
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		//this.setModal(true);       
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setEnabled(true);      
	}
	
	public void setMyMenu(JMenu[] titles){
		jMenuBar1 = new javax.swing.JMenuBar();
		for(int i=0; i<titles.length; i++){
			jMenuBar1.add(titles[i]);
		}		
        this.setJMenuBar(jMenuBar1);
	}
	
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
	
	public void updatePosition(){		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();		
		this.setLocation((owner.getWidth() - this.getWidth())/2,
				(owner.getHeight() - this.getHeight())/2);		
	}

}
