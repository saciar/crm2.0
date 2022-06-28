package crm.gui.components;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;

import crm.gui.Main;

import java.io.*;
import java.net.*;


public class PanelImagen extends JPanel{
	private Image imagen;
    private int anchoImagen;        
    private int altoImagen;
    
    private String fileName;
    private BufferedImage bufferEsp;
    
    private boolean mosaico;
    
    public PanelImagen(){
    	super();
    }
    
    public PanelImagen(String localizadorImagen, boolean m) throws IOException {
    	setImage(localizadorImagen);
    }
    
    public PanelImagen(String localizadorImagen) throws IOException {
        setImage2(localizadorImagen);
    }
    
    public void setImage2(String localizadorImagen) throws IOException  {
        if (localizadorImagen != null) {
                ImageIcon ii = new ImageIcon(Main.class.getResource("imagenes/"+localizadorImagen));
                imagen = ii.getImage();
        	
                anchoImagen = imagen.getWidth(this);        
                altoImagen  = imagen.getHeight(this);                        
        }
}
    
    public void setImage(String localizadorImagen) throws IOException  {
            if (localizadorImagen != null) {
            		
                    ImageIcon ii = new ImageIcon(new URL(localizadorImagen));
                    imagen = ii.getImage();
            		
                    anchoImagen = imagen.getWidth(this);        
                    altoImagen  = imagen.getHeight(this);                        
            }
    }

    public void setMosaico(boolean m) {
            mosaico = m;
    }
    
    public void paintComponent(Graphics g) {                
            super.paintComponent(g);
            if (imagen != null) 
            	pintar(g);

    }
    
    private void pintar(Graphics g) {
            
            Dimension d = getSize();
            int ancho = d.width;
            int alto  = d.height;
            if (mosaico) {
                    for (int y=0; y<alto; y+=altoImagen) {
                            for (int x=0; x<ancho; x+=anchoImagen) {
                                    g.drawImage(imagen,x,y,this);        
            
                            }                                
                    }                        
            }
            else {
                    int x = (ancho-anchoImagen)/2;
                    int y = (alto-altoImagen)/2;
                    g.drawImage(imagen,x,y,this);
            }
    }

}
