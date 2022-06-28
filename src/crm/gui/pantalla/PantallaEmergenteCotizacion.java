package crm.gui.pantalla;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLEditorKit;

import crm.client.managers.PresupuestosManager;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.GradientButton;
import crm.gui.tablerenderer.activador.ActivadorItem;
import crm.gui.tablerenderer.activador.ActivadorTableModel;
import crm.gui.tablerenderer.activador.TableRenderActivador;
import crm.libraries.util.MessageUtil;

public class PantallaEmergenteCotizacion extends JDialog{
	
	private JButton salir;
	private PantallaInterface m_interface;
	private final int DIALOG_X = 280;
	private final int DIALOG_Y = 240;
	private JEditorPane editor;
	public static final String URL_HTML = "http://200.80.201.51:8888/Noticias2.html"; 
	
	public PantallaEmergenteCotizacion(Frame owner){
		
		super(owner);
		this.setTitle("Cotización del dólar");
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setModal(true);
        //setPreferredSize(new Dimension(DIALOG_X,DIALOG_Y));
        //setMaximumSize(new Dimension(DIALOG_X,DIALOG_Y));
        //setSize(new Dimension(DIALOG_X,DIALOG_Y));       
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setEnabled(true);
        m_interface = new Pantalla();
	}
	
	//Este era el init antes de utilizar el layout que usa el netbeans 5.0 en templates de JFrame
	/*public void initc(){
		JPanel panel = m_interface.createTitlePanel("Cotización del dólar");
		try{
			HTMLEditorKit kit = new HTMLEditorKit( ); 
			editor = new JEditorPane();
			//editor.setPreferredSize(new Dimension(1000,720));
			//editor.setMaximumSize(new Dimension(1000,720));
			//editor.setSize(new Dimension(1000,720));
			editor.setBorder(new EtchedBorder(1));
			editor.setEditorKitForContentType("text/html", kit );
			editor.setEditable( false );
			editor.setPage (URL_HTML);
			
			editor.addHyperlinkListener(new HLinkListener());
			
			panel.add(editor);
			
			kit.install( editor );
			
		 }
		 catch(IOException e){
			 Util.errMsg(Main.getVentana(), "Error código 000001. Comuníquese con el soporte técnico",e);
		 }
		//panel.add(Box.createRigidArea(new Dimension(0,5)));
		
        salir = new JButton("Salir");
        
        //panel.add(Box.createRigidArea(new Dimension(0,10)));
        
        panel.add(salir);
        
        //panel.add(Box.createRigidArea(new Dimension(0,10)));
        
        this.getContentPane().add(panel);
        //this.pack();
        
        createListener();        
        JDialog.setDefaultLookAndFeelDecorated(true);
        updatePosition();
	}*/
	
	public void updatePosition(){
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setLocation((screenSize.width - this.getWidth())/2,
				(screenSize.height - this.getHeight())/2);
		
	}
	
	private void createListener(){
		//salir.addActionListener(new SalirActionListener());
		jButton1.addActionListener(new SalirActionListener());
	}
	
	public void setupTableActivador(long codVendedor){
		
	}
	
	private class SalirActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			//if (MessageUtil.showYesNoMessage(PantallaEmergenteCotizacion.this, "¿Desea salir de mis presupuestos?", "Salir")){
				setVisible(false);
			//}
		}
		
	}
	
	private class HLinkListener implements HyperlinkListener{

		public void hyperlinkUpdate( HyperlinkEvent e ) {
			try{
				//Se comprueba si se ha hecho click
				if( e.getEventType() == HyperlinkEvent.EventType.ACTIVATED ) {
					//se obtiene la url a la que apunta el hiperlink
					if( e.getURL() != null ) {
						//Se pasa la página al editor
						editor.setPage( e.getURL());						
					}
				}
			}
			catch( Exception e2 ) {
				Util.errMsg(Main.getVentana(), "Error código 000002. Comuníquese con el soporte técnico ", e2);
			}
		}			
		
	}
	
	public void init() {
		jScrollPane1 = new javax.swing.JScrollPane();
		try{
			HTMLEditorKit kit = new HTMLEditorKit( );
			jEditorPane1 = new javax.swing.JEditorPane();
			jEditorPane1.setEditorKitForContentType("text/html", kit );
			jEditorPane1.setEditable( false );
			jEditorPane1.setPage (URL_HTML);
			
			jEditorPane1.addHyperlinkListener(new HLinkListener());
			
			jLabel1 = new javax.swing.JLabel();
	        jScrollPane1 = new javax.swing.JScrollPane();
	        jButton1 = new GradientButton("Salir", Color.white);
	        jLabel2 = new javax.swing.JLabel();

	        setTitle("Cotizaci\u00f3n de divisas");
	        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
	        jLabel1.setForeground(new java.awt.Color(163, 184, 204));
	        jLabel1.setText("Cotizaciones ");

	        jEditorPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
	        jScrollPane1.setViewportView(jEditorPane1);

	        jButton1.setText("Salir");

	        jLabel2.setIcon(new javax.swing.JLabel() {
	            public javax.swing.Icon getIcon() {
	                try {
	                    return new javax.swing.ImageIcon(
	                        new java.net.URL("http://200.80.201.51:8888/app_files/money07.gif")
	                    );
	                } catch (java.net.MalformedURLException e) {
	                }
	                return null;
	            }
	        }.getIcon());
	        jLabel2.setDisabledIcon(new javax.swing.JLabel() {
	            public javax.swing.Icon getIcon() {
	                try {
	                    return new javax.swing.ImageIcon(
	                        new java.net.URL("http://200.80.201.51:8888/money07.gif")
	                    );
	                } catch (java.net.MalformedURLException e) {
	                }
	                return null;
	            }
	        }.getIcon());

	        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
	            .add(layout.createSequentialGroup()
	                .addContainerGap()
	                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
	                    .add(layout.createSequentialGroup()
	                        .add(jLabel2)
	                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
	                        .add(jLabel1)
	                        .addContainerGap())
	                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
	                        .add(jButton1)
	                        .add(116, 116, 116))
	                    .add(layout.createSequentialGroup()
	                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 269, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
	                        .addContainerGap())))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
	            .add(layout.createSequentialGroup()
	                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
	                    .add(jLabel1)
	                    .add(jLabel2))
	                .add(8, 8, 8)
	                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 127, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
	                .add(jButton1)
	                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        pack();
			kit.install( jEditorPane1 );
			
		}
		catch(IOException e){
			Util.errMsg(Main.getVentana(), "Error código 000001. Comuníquese con el soporte técnico",e);
		}
		createListener();        
        JDialog.setDefaultLookAndFeelDecorated(true);
        updatePosition();
	}// </editor-fold>

    // Variables declaration - do not modify
    private GradientButton jButton1;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
}
