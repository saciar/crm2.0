package crm.gui.pantallas2019.gerencia;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;

import org.jvnet.substance.SubstanceLookAndFeel;

import javax.swing.border.BevelBorder;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.border.EtchedBorder;

public class VenuesReporteGerencia extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JList lst_tipos;
	private JList lst_vendedores;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JFrame.setDefaultLookAndFeelDecorated(true); 
			SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.SaharaSkin");
			
			VenuesReporteGerencia dialog = new VenuesReporteGerencia(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VenuesReporteGerencia(JFrame padre) {		
		super(padre);
		setTitle("Venues");
		setIconImage(Toolkit.getDefaultToolkit().getImage(BuscadorReportesGerencia.class.getResource("/crm/gui/imagenes/LogoCongressPeque.png")));
		setBounds(100, 100, 1280, 720);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("Presupuestos");
		label.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		label.setBounds(10, 11, 196, 20);
		contentPanel.add(label);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(128, 128, 128)));
		panel.setBounds(10, 40, 1242, 157);
		contentPanel.add(panel);
		
		dateChooser = new JDateChooser();
		dateChooser.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		dateChooser.setBounds(76, 20, 217, 20);
		panel.add(dateChooser);
		
		JLabel label_1 = new JLabel("Desde");
		label_1.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		label_1.setBounds(28, 22, 47, 16);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Hasta");
		label_2.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		label_2.setBounds(334, 22, 47, 16);
		panel.add(label_2);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		dateChooser_1.setBounds(378, 20, 217, 20);
		panel.add(dateChooser_1);
		
		JLabel label_3 = new JLabel("Vendedor");
		label_3.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		label_3.setBounds(28, 56, 64, 16);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Tipo de evento");
		label_4.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		label_4.setBounds(463, 56, 96, 16);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Estado");
		label_5.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		label_5.setBounds(704, 56, 47, 16);
		panel.add(label_5);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		comboBox.setBounds(754, 52, 217, 25);
		panel.add(comboBox);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(1006, 49, 224, 56);
		panel.add(panel_1);
		
		JButton button = new JButton("Buscar");
		button.setVerticalTextPosition(SwingConstants.BOTTOM);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		button.setBounds(72, 0, 100, 56);
		panel_1.add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(561, 56, 112, 85);
		panel.add(scrollPane);
		
		lst_tipos = new JList();
		lst_tipos.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setViewportView(lst_tipos);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(98, 56, 347, 89);
		panel.add(scrollPane_1);
		
		lst_vendedores = new JList();
		lst_vendedores.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_1.setViewportView(lst_vendedores);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "Resultados", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		panel_2.setBounds(10, 209, 410, 424);
		contentPanel.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "Grafica", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		panel_3.setBounds(432, 209, 820, 424);
		contentPanel.add(panel_3);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
