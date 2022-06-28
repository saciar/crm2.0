package crm.gui.pantalla;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import crm.client.util.ProgressDialogUtil;
import crm.gui.Main;
import crm.gui.components.GradientButton;
import crm.gui.components.PanelImagen;
import crm.gui.pantalla.solapa.ABM;
import crm.gui.pantallas2019.gerencia.PortadaReportesGerencia;
import crm.libraries.util.MessageUtil;

public class PantallaBienvenidaAdmin extends PantallaBienvenidaVerdadera{
	
    private javax.swing.JButton abms;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton reportes;
    private javax.swing.JButton salir;
    private javax.swing.JLabel user;
    private javax.swing.JSeparator jSeparator1;
    
    private PanelImagen middlePanel;
    
	public PantallaBienvenidaAdmin(){
		super();
	}
	
	public void initComponents() {			
		middlePanel = null;
		try{
			middlePanel = new PanelImagen("Abstract_4220.jpg");
		}
		catch(Exception e){
			System.out.println("Error");
			middlePanel = new PanelImagen();
		}
		
		jLabel1 = new javax.swing.JLabel();
        user = new javax.swing.JLabel();
        abms = new GradientButton("", Color.blue);
        reportes = new GradientButton("", Color.blue);
        salir = new GradientButton("", Color.blue);
        jSeparator1 = new javax.swing.JSeparator();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20));
        jLabel1.setText("Bienvenido/a");

        user.setFont(new java.awt.Font("Tahoma", 1, 20));
        user.setForeground(new java.awt.Color(163, 184, 204));

        abms.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_cascade.png")));
        abms.setText("Cargar ABM's");
        abms.setDoubleBuffered(true);
        abms.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        abms.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        abms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abmsActionPerformed(evt);
            }
        });

        reportes.setIcon(new javax.swing.ImageIcon(getUrlImagen("layout_sidebar.png")));
        reportes.setText("Reportes");
        reportes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        reportes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        reportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportesActionPerformed(evt);
            }
        });

        salir.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
        salir.setText("Salir");
        salir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        salir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        
        completePantalla();
}
	
    public void createLayout() {
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(middlePanel);
        middlePanel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 999, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                            .add(362, 362, 362)
                            .add(abms)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(reportes)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(salir))
                        //.add(362, 362, 362))
                    .add(layout.createSequentialGroup()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(user)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(27, 27, 27)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(user))
                .add(21, 21, 21)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 480, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(reportes)
                    .add(salir)
                    .add(abms))
                .add(71, 71, 71))
        );

        layout.linkSize(new java.awt.Component[] {abms, reportes, salir}, org.jdesktop.layout.GroupLayout.VERTICAL);
        this.add(middlePanel);
	}
	
	private void completePantalla(){
		user.setText(getNombreUsuario());
	}
	
	private void salirActionPerformed(ActionEvent evt) {
		// sale del sistema
		if (MessageUtil.showYesNoMessage(Main.getVentana(),"Seguro que desea salir de la aplicación","Salir"))
			Main.closeWindow();
	}	
	
	private void reportesActionPerformed(ActionEvent evt) {
		BuscadorReportesAdmin buscador = new BuscadorReportesAdmin(Main.getVentana());
		buscador.init();
		buscador.setVisible(true);
		this.repaint();

		
	}
	
	private ABM pantallaABM;
	
	private void abmsActionPerformed(ActionEvent evt) {

		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				Main.getVentana().getGlassPane().start();
				Thread performer = new Thread(new Runnable(){
					public void run(){
						if(pantallaABM == null){
							pantallaABM = new ABM(Main.getVentana());	
							pantallaABM.setUser(getUsuario());
							pantallaABM.initComponent();
							pantallaABM.initLayout();	
				    	}
						//ProgressDialogUtil.closeProcessDialog();
						pantallaABM.setVisible(true);
						Main.getVentana().getGlassPane().stop();
					}
				},"Performer");
				performer.start();
			}
		});		
	}
}
