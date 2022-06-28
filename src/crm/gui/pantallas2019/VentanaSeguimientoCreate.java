package crm.gui.pantallas2019;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.event.ChangeListener;

import crm.gui.pantalla.solapa.MainPanelComercial;

import javax.swing.event.ChangeEvent;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JSeparator;

public class VentanaSeguimientoCreate extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private HashSet<String> resultados = new HashSet<String>();
	
	public HashSet<String> getResultados() {
		return resultados;
	}

	public void setResultados(HashSet<String> resultados) {
		this.resultados = resultados;
	}

	private String codSeguimiento;
	private String codResultado;
	private String observaciones;
	private boolean seGraba = false;

	private JToggleButton tglbtnAgregar;

	private JToggleButton tglbtnOS;

	private JToggleButton tglbtnOF;

	private JToggleButton tglbtnCompletDatos;

	private JToggleButton tglbtnAgreguAdicionales;

	private JToggleButton tglbtnApliquDescuento;

	private JTextArea textArea;

	private JButton okButton;

	private JButton cancelButton;
	private Component verticalStrut_1;
	private Component verticalStrut_2;
	private Component verticalStrut_3;
	private Component verticalStrut_4;
	private Component verticalStrut_5;
	private Component verticalStrut_6;
	private JSeparator separator;
	
	public boolean isSeGraba() {
		return seGraba;
	}

	public void setSeGraba(boolean seGraba) {
		this.seGraba = seGraba;
	}
	
	public String getCodSeguimiento() {
		return codSeguimiento;
	}

	public void setCodSeguimiento(String codSeguimiento) {
		this.codSeguimiento = codSeguimiento;
	}

	public String getCodResultado() {
		return codResultado;
	}

	public void setCodResultado(String codResultado) {
		this.codResultado = codResultado;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaSeguimientoCreate dialog = new VentanaSeguimientoCreate(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			//dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaSeguimientoCreate(MainPanelComercial parent) {
		setLocationRelativeTo(parent);
		setModal(true);
		setTitle("Seguimiento");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaSeguimientoCreate.class.getResource("/crm/gui/imagenes/LogoCongressPeque.png")));
		setBounds(100, 100, 562, 373);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 211, 226);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		verticalStrut_6 = Box.createVerticalStrut(4);
		contentPanel.add(verticalStrut_6);
		
		JPanel pnlTitulo = new JPanel();
		pnlTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPanel.add(pnlTitulo);
		pnlTitulo.setLayout(new BoxLayout(pnlTitulo, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel_1 = new JLabel("\u00BFQu\u00E9 tarea realiz\u00F3?");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnlTitulo.add(lblNewLabel_1);
		
		Component verticalStrut = Box.createVerticalStrut(15);
		contentPanel.add(verticalStrut);
		{
			JPanel pnlBotonera = new JPanel();
			contentPanel.add(pnlBotonera);
			pnlBotonera.setLayout(new BoxLayout(pnlBotonera, BoxLayout.Y_AXIS));
			
			tglbtnAgregar = new JToggleButton("Agregue servicios");			
			tglbtnAgregar.addItemListener(new AgregarItemListener());
			tglbtnAgregar.setFont(new Font("Dialog", Font.PLAIN, 12));
			tglbtnAgregar.setForeground(Color.BLACK);

			tglbtnAgregar.setIcon(new ImageIcon(VentanaSeguimientoCreate.class.getResource("/crm/gui/imagenes/add.png")));
			pnlBotonera.add(tglbtnAgregar);
			
			verticalStrut_1 = Box.createVerticalStrut(5);
			pnlBotonera.add(verticalStrut_1);
			
			tglbtnOS = new JToggleButton("Envi\u00E9 OS");
			tglbtnOS.addItemListener(new OsItemListener());
			tglbtnOS.setFont(new Font("Dialog", Font.PLAIN, 12));
			tglbtnOS.setIcon(new ImageIcon(VentanaSeguimientoCreate.class.getResource("/crm/gui/imagenes/attach.png")));
			pnlBotonera.add(tglbtnOS);
			
			verticalStrut_2 = Box.createVerticalStrut(5);
			pnlBotonera.add(verticalStrut_2);
			
			tglbtnOF = new JToggleButton("Envi\u00E9 OF");
			tglbtnOF.addItemListener(new OfItemListener());
			tglbtnOF.setFont(new Font("Dialog", Font.PLAIN, 12));
			tglbtnOF.setIcon(new ImageIcon(VentanaSeguimientoCreate.class.getResource("/crm/gui/imagenes/money_dollar.png")));
			pnlBotonera.add(tglbtnOF);
			
			verticalStrut_4 = Box.createVerticalStrut(5);
			pnlBotonera.add(verticalStrut_4);
			
			tglbtnCompletDatos = new JToggleButton("Complet\u00E9 Datos");
			tglbtnCompletDatos.addItemListener(new CompleteItemListener());
			tglbtnCompletDatos.setFont(new Font("Dialog", Font.PLAIN, 12));
			tglbtnCompletDatos.setIcon(new ImageIcon(VentanaSeguimientoCreate.class.getResource("/crm/gui/imagenes/application_form_edit.png")));
			pnlBotonera.add(tglbtnCompletDatos);
			
			verticalStrut_3 = Box.createVerticalStrut(5);
			pnlBotonera.add(verticalStrut_3);
			
			tglbtnAgreguAdicionales = new JToggleButton("Agregu\u00E9 adicionales");
			tglbtnAgreguAdicionales.addItemListener(new AgregueItemListener());
			tglbtnAgreguAdicionales.setFont(new Font("Dialog", Font.PLAIN, 12));
			tglbtnAgreguAdicionales.setIcon(new ImageIcon(VentanaSeguimientoCreate.class.getResource("/crm/gui/imagenes/application_form_add.png")));
			pnlBotonera.add(tglbtnAgreguAdicionales);
			
			verticalStrut_5 = Box.createVerticalStrut(5);
			pnlBotonera.add(verticalStrut_5);
			
			tglbtnApliquDescuento = new JToggleButton("Apliqu\u00E9 descuento");
			tglbtnApliquDescuento.addItemListener(new DescuentoItemListener());
			tglbtnApliquDescuento.setFont(new Font("Dialog", Font.PLAIN, 12));
			tglbtnApliquDescuento.setIcon(new ImageIcon(VentanaSeguimientoCreate.class.getResource("/crm/gui/imagenes/application_form_delete.png")));
			pnlBotonera.add(tglbtnApliquDescuento);
		}
		
		JPanel panel = new JPanel();
		panel.setBounds(221, 0, 326, 260);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				observaciones= textArea.getText();
			}
		});
		textArea.setBounds(10, 37, 306, 211);
		textArea.setLineWrap(true);
		panel.add(textArea);
		
		JLabel lblNewLabel = new JLabel("Detalle");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 11, 94, 14);
		panel.add(lblNewLabel);
		
		separator = new JSeparator();
		separator.setBounds(12, 272, 535, 5);
		getContentPane().add(separator);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 289, 547, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				okButton = new JButton("Guardar");
				okButton.setIcon(new ImageIcon(VentanaSeguimientoCreate.class.getResource("/crm/gui/imagenes/tick.png")));
				okButton.addActionListener(new OkActionListener());
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("No guardar");
				cancelButton.setIcon(new ImageIcon(VentanaSeguimientoCreate.class.getResource("/crm/gui/imagenes/cross.png")));
				cancelButton.addActionListener(new CancelarActionListener());
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		updatePosition();
	}
	
	private void updatePosition() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		this.setLocation((screenSize.width - this.getWidth()) / 2,
				(screenSize.height - this.getHeight()) / 2);

	}
	
	private final class DescuentoItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (tglbtnApliquDescuento.isSelected()){
				tglbtnApliquDescuento.setFont(new Font("Tahoma", Font.BOLD, 11));
				resultados.add("19");	
			}
			else{
				tglbtnApliquDescuento.setFont(new Font("Tahoma", Font.PLAIN, 11));
				if(resultados.contains("19")){
					resultados.remove("19");
				}
			}
		}
	}

	private final class AgregueItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (tglbtnAgreguAdicionales.isSelected()){
				tglbtnAgreguAdicionales.setFont(new Font("Tahoma", Font.BOLD, 11));
				resultados.add("18");	
			}
			else{
				tglbtnAgreguAdicionales.setFont(new Font("Tahoma", Font.PLAIN, 11));
				if(resultados.contains("18")){
					resultados.remove("18");
				}
			}
		}
	}

	private final class CompleteItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (tglbtnCompletDatos.isSelected()){
				tglbtnCompletDatos.setFont(new Font("Tahoma", Font.BOLD, 11));
				resultados.add("17");	
			}
			else{
				tglbtnCompletDatos.setFont(new Font("Tahoma", Font.PLAIN, 11));
				if(resultados.contains("17")){
					resultados.remove("17");
				}
			}
		}
	}

	private final class OfItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (tglbtnOF.isSelected()){
				tglbtnOF.setFont(new Font("Tahoma", Font.BOLD, 11));
				resultados.add("13");	
			}
			else{
				tglbtnOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				if(resultados.contains("13")){
					resultados.remove("13");
				}
			}
		}
	}

	private final class OsItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (tglbtnOS.isSelected()){
				tglbtnOS.setFont(new Font("Tahoma", Font.BOLD, 11));
				resultados.add("12");	
			}
			else{
				tglbtnOS.setFont(new Font("Tahoma", Font.PLAIN, 11));
				if(resultados.contains("12")){
					resultados.remove("12");
				}
			}
		}
	}

	private final class AgregarItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent arg0) {
			if (tglbtnAgregar.isSelected()){
				tglbtnAgregar.setFont(new Font("Tahoma", Font.BOLD, 11));
				resultados.add("16");	
			}
			else{
				tglbtnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 11));
				if(resultados.contains("16")){
					resultados.remove("16");
				}
			}
		}
	}

	private final class CancelarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			seGraba = false;
			setVisible(false);
		}
	}

	private final class OkActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			seGraba = true;
			setVisible(false);
		}
	}

}
