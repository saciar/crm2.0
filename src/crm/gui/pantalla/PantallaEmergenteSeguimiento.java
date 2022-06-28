package crm.gui.pantalla;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import crm.gui.components.ABMAccionesComboBox;
import crm.gui.components.ABMResultadosComboBox;
import crm.libraries.util.MessageUtil;

public class PantallaEmergenteSeguimiento extends JDialog {

	private ABMAccionesComboBox m_cmbAccion;

	private ABMResultadosComboBox m_cmbResultados;

	private JTextArea m_txtObservaciones;

	private PantallaInterface m_interface;

	private final int DIALOG_X = 640;

	private final int DIALOG_Y = 200;

	private String lastCodigoAccion;

	public String getCodigoAccion() {
		return m_cmbAccion.searchForeign();
	}

	public String getCodigoResultado() {
		return m_cmbResultados.searchForeign();
	}

	public String getObservaciones() {
		return m_txtObservaciones.getText();
	}
	
	public String getFecha(){
		return m_interface.getCurrentDate();
	}
	
	public PantallaEmergenteSeguimiento(Frame owner) {
		super(owner);
		this.setTitle("Seguimiento del presupuesto");
		this.getContentPane().setLayout(
				new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		this.setModal(true);
		this.setMaximumSize(new Dimension(DIALOG_X, DIALOG_Y));
		this.setPreferredSize(new Dimension(DIALOG_X, DIALOG_Y));
		this.setSize(new Dimension(DIALOG_X, DIALOG_Y));
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.setEnabled(true);
		m_interface = new Pantalla();
		this.getContentPane().add(setupDialog());
		this.pack();

		JDialog.setDefaultLookAndFeelDecorated(true);
		updatePosition();
		// this.setVisible(true);
	}

	public JPanel setupDialog() {

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panel.add(Box.createRigidArea(new Dimension(0, 5)));

		JLabel action = m_interface.createFieldLabel("Acción: ");
		m_cmbAccion = new ABMAccionesComboBox();
		m_cmbAccion.loadItems();

		m_cmbAccion.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cmbAccionActionPerformed(evt);
			}
		});

		JLabel result = m_interface.createFieldLabel("Resultado: ");
		m_cmbResultados = new ABMResultadosComboBox();

		panel.add(m_interface.createLineOfComponents(new JComponent[] { action,
				m_cmbAccion, result, m_cmbResultados },
				new int[] { 5, 5, 10, 5 }, null, new Dimension(DIALOG_X, 22)));

		panel.add(Box.createRigidArea(new Dimension(0, 5)));

		m_txtObservaciones = new JTextArea();

		panel.add(m_interface.createPanelWithTextArea(m_txtObservaciones,
				new Dimension(DIALOG_X - 15, 88), true));

		panel.add(Box.createRigidArea(new Dimension(0, 5)));

		JButton aceptar = new JButton("Aceptar");

		//JButton cancelar = new JButton("Cancelar");

		aceptar.addActionListener(new SeguimientoAceptarAccion());

		//cancelar.addActionListener(new SeguimientoCancelarAccion());

		panel.add(m_interface.createLineOfComponents(new JComponent[] {
				aceptar/*, cancelar*/ }, new int[] { 250 }, null, new Dimension(
				DIALOG_X, 22)));

		return panel;
	}

	public void cmbAccionActionPerformed(java.awt.event.ActionEvent evt) {
		String s = m_cmbAccion.searchForeign();
		// System.out.println(codigoLugar);

		if (lastCodigoAccion == null || !lastCodigoAccion.equals(s)) {
			lastCodigoAccion = s;
			m_cmbResultados.loadItemsForAction(lastCodigoAccion); // ,
																	// codigoLugar);
		}
	}

	private void updatePosition() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		this.setLocation((screenSize.width - this.getWidth()) / 2,
				(screenSize.height - this.getHeight()) / 2);

	}
	
	private class SeguimientoAceptarAccion implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			if ((m_cmbAccion.getSelectedIndex() != 0 && m_cmbResultados
					.getSelectedIndex() != 0)
					&& m_txtObservaciones.getText().length() != 0) {
				/*
				 * try { TxSeguimientoManager.instance().update(new
				 * TxSeguimiento("1", "1", m_interface.getCurrentDate(), "1",
				 * m_cmbAccion.searchForeign(), m_cmbResultados.searchForeign(),
				 * m_txtObservaciones.getText())); } catch (RemoteException e) {
				 * e.printStackTrace(); }
				 */

				setVisible(false);
			} else
				JOptionPane.showMessageDialog(null,
						"Complete todos los campos", "Warning",
						JOptionPane.WARNING_MESSAGE);

		}
	}
		
		private class SeguimientoCancelarAccion implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				if(MessageUtil.showYesNoMessage(" ¿Desea salir del seguimiento?","Salir"));
				setVisible(false);
				
			}
			
		}
}
