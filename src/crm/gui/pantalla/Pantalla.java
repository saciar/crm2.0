package crm.gui.pantalla;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import crm.client.util.ClockDinamico;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.PanelImagen;
import crm.libraries.abm.entities.Usuario;

public class Pantalla extends JPanel implements PantallaInterface{ 

	private static Usuario usuario;
    
    private static String codigoVendedor;
    
    private static String codigoSucursal;
    
    private static String objetivo;
    
    private static String sucursal;
    
    private static String codigoUC;
    
    private static PantallaBienvenidaVerdadera pantallaBienvenida;
    
    public URL getUrlImagen(String imagen){
    
    	URL url =Main.class.getResource("imagenes/"+imagen); 

    	return url;
    }
	
	protected void setupMiddle(){}
	
    protected void setupFooter(){}
	
    protected void setupHeader() {
	}
    
	public String getNombreUsuario() {
		return usuario.getApellidoYNombre();
	}

	public String getCurrentDate() {
  	  
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
	    
	    String DATE_FORMAT = "yyyy-MM-dd ";
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	    
	    sdf.setTimeZone(TimeZone.getDefault());          
	          
	   return sdf.format(cal.getTime());
    	    
    }
	
    public JPanel createLineOfComponents(JComponent[] c, int []rigidsLenght, 
    										Color color, Dimension dim) {	
    	JPanel panel = new JPanel();
    	if (color != null)
    		panel.setBackground(color);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setPreferredSize(new Dimension(dim));
		panel.setMaximumSize(new Dimension(dim));
		panel.setSize(new Dimension(dim));
		for (int i = 0; i < c.length; i++) {
			panel.add(Box.createRigidArea(new Dimension(rigidsLenght[i], 0)));
			panel.add(c[i]);		
		}
		return panel;
	}
	
	protected String getCurrentMonth(){
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
	    
	    String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	    
	    sdf.setTimeZone(TimeZone.getDefault());          
	          
	    return (sdf.format(cal.getTime())).substring(3,5);
	}
	
	public Color getFieldLabelColor(){
		return Color.gray;//new Color(163,184,204);
	}
	
	
	public JLabel createFieldLabel (String cont){
		JLabel lab = new JLabel(cont);
    	lab.setFont(new Font(null, Font.BOLD + Font.ITALIC, 12));
    	lab.setForeground(/*Color.DARK_GRAY*/getFieldLabelColor());
    	return lab;
	}
	
	public JScrollPane createPanelWithTextArea(JTextArea area, Dimension dim, boolean isEditable){
		JScrollPane panel = new JScrollPane();
		panel.setPreferredSize(dim);
		panel.setMaximumSize(dim);
		area.setLineWrap(true);
		area.setMargin(new Insets(0,10,0,10));
		area.setEditable(isEditable);
		panel.getViewport().add(area);
		return panel;
	}
	
	public String getCodigoUsuario() {
		return usuario.getCodigo();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	/**
	 * Limpia la ventana principal
	 */
	
	/**
     * Cambia de pantalla a la pantalla indicada
     *  
     * @param screen
	 */
	public void cambiarPantallaA(Pantalla screen){	
		Main.getVentana().setCursor(new Cursor(Cursor.WAIT_CURSOR));		
		Main.getVentana().getContentPane().removeAll();	
		Main.getVentana().getContentPane().add(screen);		
		Main.getVentana().pack();
		//Main.getVentana().setSize(1024,768);
		Main.getVentana().setMinimumSize(new Dimension(1024,768));
		Main.getVentana().setMaximumSize(new Dimension(1024,768));
		Main.getVentana().setPreferredSize(new Dimension(1024,768));
		Main.getVentana().setLocationRelativeTo(null);
		Main.getVentana().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));		
	}
	
	public static String getCodigoVendedor() {
		return codigoVendedor;
	}

    public String getCodigoSucursal() {
		return codigoSucursal;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public String getSucursal() {
		return sucursal;
	}

	public static void setCodigoVendedor(String codigo) {
		codigoVendedor = codigo;
	}

    public void setCodigoSucursal(String codigoSucursal) {
        Pantalla.codigoSucursal = codigoSucursal;
    }

    public void setObjetivo(String objetivo) {
        Pantalla.objetivo = objetivo;
    }
    
    public String getCodigoUC() {
		return codigoUC;
	}

	public void setCodigoUC(String codigoUC) {
		this.codigoUC = codigoUC;
	}

	/**
	 * @return Returns the pantallaBienvenida.
	 */
	public static PantallaBienvenidaVerdadera getPantallaBienvenida() {
		return pantallaBienvenida;
	}

	/**
	 * @param pantallaBienvenida The pantallaBienvenida to set.
	 */
	public static void setPantallaBienvenida(
			PantallaBienvenidaVerdadera pantallaBienvenida) {
		Pantalla.pantallaBienvenida = pantallaBienvenida;
	}

	

}
