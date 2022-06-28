package crm.gui.pantallas2019;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class VentanaSeguimientoViewer extends JDialog {

	private final class OkActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			setVisible(false);
		}
	}

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaSeguimientoViewer dialog = new VentanaSeguimientoViewer();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaSeguimientoViewer() {
		setTitle("Seguimiento");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaSeguimientoViewer.class.getResource("/crm/gui/imagenes/LogoCongressPeque.png")));
		setBounds(100, 100, 562, 373);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 211, 226);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		{
			Component verticalStrut = Box.createVerticalStrut(4);
			contentPanel.add(verticalStrut);
		}
		{
			JPanel panelTitulo = new JPanel();
			panelTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
			contentPanel.add(panelTitulo);
			panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.X_AXIS));
			{
				JLabel lblNewLabel = new JLabel("Tarea realizada");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				panelTitulo.add(lblNewLabel);
			}
		}
		{
			Component verticalStrut = Box.createVerticalStrut(15);
			contentPanel.add(verticalStrut);
		}
		
		JPanel panelTareas = new JPanel();
		contentPanel.add(panelTareas);
		panelTareas.setLayout(new BoxLayout(panelTareas, BoxLayout.Y_AXIS));
		{
			JCheckBox chckbxAgregar = new JCheckBox("Agregue Servicios");
			chckbxAgregar.setEnabled(false);
			panelTareas.add(chckbxAgregar);
		}
		{
			Component verticalStrut = Box.createVerticalStrut(5);
			panelTareas.add(verticalStrut);
		}
		{
			JCheckBox chckbxOS = new JCheckBox("Envie OS");
			chckbxOS.setEnabled(false);
			panelTareas.add(chckbxOS);
		}
		{
			Component verticalStrut = Box.createVerticalStrut(5);
			panelTareas.add(verticalStrut);
		}
		{
			JCheckBox chckbxOF = new JCheckBox("Envie OF");
			chckbxOF.setEnabled(false);
			panelTareas.add(chckbxOF);
		}
		{
			Component verticalStrut = Box.createVerticalStrut(5);
			panelTareas.add(verticalStrut);
		}
		{
			JCheckBox chckbxNewComplete = new JCheckBox("Complete Datos");
			chckbxNewComplete.setEnabled(false);
			panelTareas.add(chckbxNewComplete);
		}
		{
			Component verticalStrut = Box.createVerticalStrut(5);
			panelTareas.add(verticalStrut);
		}
		{
			JCheckBox chckbxAdicionales = new JCheckBox("Agregue Adicionales");
			chckbxAdicionales.setEnabled(false);
			panelTareas.add(chckbxAdicionales);
		}
		{
			Component verticalStrut = Box.createVerticalStrut(5);
			panelTareas.add(verticalStrut);
		}
		{
			JCheckBox chckbxDescuento = new JCheckBox("Aplique descuento");
			chckbxDescuento.setEnabled(false);
			panelTareas.add(chckbxDescuento);
		}
		{
			JPanel panel = new JPanel();
			panel.setBounds(221, 0, 326, 260);
			getContentPane().add(panel);
			panel.setLayout(null);
			
			JTextArea textArea = new JTextArea();
			textArea.setEditable(false);
			textArea.setLineWrap(true);
			textArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			textArea.setBounds(10, 37, 306, 211);
			panel.add(textArea);
			
			JLabel lblNewLabel_1 = new JLabel("Detalle");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel_1.setBounds(10, 11, 94, 14);
			panel.add(lblNewLabel_1);
		}
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 275, 522, 2);
		getContentPane().add(separator);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 289, 547, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new OkActionListener());
				okButton.setIcon(new ImageIcon(VentanaSeguimientoViewer.class.getResource("/crm/gui/imagenes/tick.png")));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
