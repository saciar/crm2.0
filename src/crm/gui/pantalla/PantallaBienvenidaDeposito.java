package crm.gui.pantalla;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

import javax.swing.JFrame;

import crm.client.managers.PresupuestosManager;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.cold.PantallaReportesCold;
import crm.gui.components.GradientButton;
import crm.gui.components.PanelImagen;
import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.util.MessageUtil;

public class PantallaBienvenidaDeposito extends PantallaBienvenidaVerdadera{
	
//	 Variables declaration - do not modify
	private javax.swing.JLabel jLabel1;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JButton reportes;
	private javax.swing.JButton salir;
	private javax.swing.JLabel user;
	
	public PantallaBienvenidaDeposito(){
		super();
	}
	
	protected void setupMiddle() {	
		PanelImagen middlePanel = null;
		try{
			middlePanel = new PanelImagen("Abstract_4220.jpg");
		}
		catch(Exception e){
			System.out.println("Error");
			middlePanel = new PanelImagen();
		}
		
		jLabel1 = new javax.swing.JLabel();
		user = new javax.swing.JLabel();
		reportes = new GradientButton("", Color.blue);
		salir = new GradientButton("", Color.blue);
		jSeparator1 = new javax.swing.JSeparator();
		
		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20));
		jLabel1.setText("Bienvenido/a");
		
		user.setFont(new java.awt.Font("Tahoma", 1, 20));
		user.setForeground(new java.awt.Color(163, 184, 204));
		
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
		
		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(middlePanel);
		middlePanel.setLayout(layout);
		layout.setHorizontalGroup(
	            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
	            .add(layout.createSequentialGroup()
	                .add(10, 10, 10)
	                .add(jLabel1)
	                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
	                .add(user)
	                .addContainerGap(966, Short.MAX_VALUE))
	            .add(layout.createSequentialGroup()
	                .add(10, 10, 10)
	                .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1099, Short.MAX_VALUE)
	                .addContainerGap(10, Short.MAX_VALUE))
	            .add(layout.createSequentialGroup()
	                .add(433, 433, 433)
	                .add(reportes, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
	                .add(salir, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .add(550, 550, 550))
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
	                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 568, Short.MAX_VALUE)
	                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
	                    .add(salir)
	                    .add(reportes))
	                .add(74, 74, 74))
	        );

	        layout.linkSize(new java.awt.Component[] {reportes, salir}, org.jdesktop.layout.GroupLayout.VERTICAL);
		this.add(middlePanel);
		
	}// </editor-fold>

	private void completePantalla(){
		user.setText(getNombreUsuario());
	}
	
	private void salirActionPerformed(ActionEvent evt) {
		// sale del sistema
		if (MessageUtil.showYesNoMessage(Main.getVentana(),"Seguro que desea salir de la aplicaci�n","Salir"))
			Main.closeWindow();
	}	
	
	private void reportesActionPerformed(ActionEvent evt) {
		/*BuscadorReportesCold buscador = new BuscadorReportesCold(Main.getVentana());
		buscador.init();
		buscador.setVisible(true);
		this.repaint();*/
            java.awt.EventQueue.invokeLater(new Runnable() {

                    public void run() {
                        PantallaReportesCold dialog = new PantallaReportesCold(new javax.swing.JFrame(), true);
                        dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                            /*public void windowClosing(java.awt.event.WindowEvent e) {
                                System.exit(0);
                            }*/
                        });
                        dialog.setVisible(true);
                    }
                });
	}

}
