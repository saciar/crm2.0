package crm.gui.pantalla;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.custom.InfiniteProgressPanel;

public class Ventana extends JFrame {
	
	protected JPanel header;
	protected JPanel footer;
	protected JPanel middlePanel;
	
	private InfiniteProgressPanel glassPane;
	
    private javax.swing.JMenuBar jMenuBar1;
	
    //private List pantallas;
    
	public Ventana() {
		super("CRM Congress Rental");
		
		try{
			if((System.getProperties().getProperty("os.name").toLowerCase()).equals("windows xp"))
        		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch(Exception e){
        	System.out.println(e.getMessage());
        }
		
        glassPane = new InfiniteProgressPanel();
        this.setGlassPane(glassPane);
        
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setMinimumSize(new Dimension(424,200));
		this.setMaximumSize(new Dimension(424,200));
		this.setPreferredSize(new Dimension(424,200));
		this.setLocationRelativeTo(null);
        PantallaLogin pan = new PantallaLogin(this);
        pan.initComponents();
        pan.showComponent();
		getContentPane().add(pan);        
		pack();
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		this.addWindowListener(new windowListener());
		this.addKeyListener(new keyListener());
        this.setVisible(true);	
	}
	
	public void setMyMenu(JMenu[] titles){
		jMenuBar1 = new javax.swing.JMenuBar();
		for(int i=0; i<titles.length; i++){
			jMenuBar1.add(titles[i]);
		}
		
        this.setJMenuBar(jMenuBar1);
	}
	
	public void clearMyMenu(){
		jMenuBar1.removeAll();
		jMenuBar1 = null;
	}
	
	private class keyListener implements KeyListener{

		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void keyReleased(KeyEvent arg0) {
			if(arg0.getKeyCode() == KeyEvent.VK_CONTROL){
				Util.alertMsg(Main.getVentana(),"Se forzara la Recuperacion de la interfaz grafica");
				Main.getVentana().getGlassPane().stop();
			}
			
		}

		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class windowListener implements WindowListener{

		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void windowClosing(WindowEvent arg0) {
			if (Util.confirm(Main.getVentana(),"Seguro que desea salir de CRM Congress Rental?\nSe perderan todos los datos no guardados."))
				Main.closeWindow();	
			else
				Main.getVentana().getGlassPane().stop();
				
		}

		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

	public InfiniteProgressPanel getGlassPane() {
		return glassPane;
	}

}

