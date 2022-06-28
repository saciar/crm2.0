package crm.gui.pantalla;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import crm.gui.pantalla.reportes.TabRentabilidad;

public class PantallaEmergenteGastoOperativoGral extends JDialog{
		
		protected JTextArea m_txtObservaciones;
		
		protected PantallaInterface m_interface;
		
		protected final int DIALOG_X = 500;
		
		protected final int DIALOG_Y = 720;
		
		protected boolean isNew = true;
		
	    private boolean cancel;

	    private TabRentabilidad pantallaRent;
	    
		public void changeNew(){
			isNew = false;
		}
		
		public PantallaEmergenteGastoOperativoGral(Frame owner, TabRentabilidad t){
			super(owner);
			pantallaRent=t;
			cargarPantalla("Detalles de costo operativo total");
		}
		
		public void cargarPantalla(String titulo){
			this.setTitle(titulo);
			this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
			this.setModal(true);
			this.setMaximumSize(new Dimension(DIALOG_X,DIALOG_Y));
	        this.setPreferredSize(new Dimension(DIALOG_X,DIALOG_Y));        
	        this.setSize(new Dimension(DIALOG_X,DIALOG_Y));
	        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	        this.setEnabled(true);
	        m_interface = new Pantalla(); 
	        this.getContentPane().add(setupDialog(titulo));
	        this.pack();
	        
	        JDialog.setDefaultLookAndFeelDecorated(true);
	        updatePosition();
		}

		public JPanel setupDialog(String titulo){

			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			
			panel.add(Box.createRigidArea(new Dimension(0,5)));	
			
			JLabel cartel = m_interface.createFieldLabel(titulo);
			
			panel.add(m_interface.createLineOfComponents(new JComponent []{cartel}, 
											new int[]{5}, null, new Dimension(DIALOG_X, 22)));
			
			panel.add(Box.createRigidArea(new Dimension(0,5)));
			
	        m_txtObservaciones = new JTextArea();
			
			panel.add(m_interface.createPanelWithTextArea(m_txtObservaciones, new Dimension(DIALOG_X-15, DIALOG_Y-37), true));
			
			panel.add(Box.createRigidArea(new Dimension(0,5)));
			
			JButton btaceptar = new JButton("Aceptar");
	        //JButton btcancel = new JButton("Cancelar");
			
			btaceptar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					btnAceptarActionPerformed(evt);
				}
			});

			
			panel.add(m_interface.createLineOfComponents(new JComponent []{btaceptar}, 
					new int[]{200,5}, null, new Dimension(DIALOG_X, 22)));
			
			return panel;
		}
		
	    public final void btnAceptarActionPerformed(java.awt.event.ActionEvent evt){
	        cancel = false;

	            dispose();

	    }

	    
	    protected boolean validate(String value){
	        return true;
	    }
			
		public void updatePosition(){
			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			
			this.setLocation((screenSize.width - this.getWidth())/2,
					(screenSize.height - this.getHeight())/2);
			
		}
		
		public void setObservacionesText(String text){
			m_txtObservaciones.setText(text);
		}
		
		public String getObservacionesText(){
			return m_txtObservaciones.getText();
		}

	    public boolean isCancel() {
	        return cancel;
	    }
}
