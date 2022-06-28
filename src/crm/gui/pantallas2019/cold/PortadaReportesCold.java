package crm.gui.pantallas2019.cold;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import crm.gui.pantallas2019.gerencia.PortadaReportesGerencia;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JSeparator;
import javax.swing.ImageIcon;

public class PortadaReportesCold extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PortadaReportesCold dialog = new PortadaReportesCold(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PortadaReportesCold(JFrame padre) {
		super(padre);
		setTitle("Reportes Cold");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		setIconImage(Toolkit.getDefaultToolkit().getImage(PortadaReportesGerencia.class.getResource("/crm/gui/imagenes/LogoCongressPeque.png")));
		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("Seleccione el reporte a visualizar");
		label.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		label.setBounds(12, 12, 414, 20);
		contentPanel.add(label);
		
		JPanel panel = new JPanel();
		panel.setBounds(121, 44, 196, 149);
		contentPanel.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));
		
		JButton btnDiaryPlanner = new JButton("Diary Planner");
		btnDiaryPlanner.setIcon(new ImageIcon(PortadaReportesCold.class.getResource("/crm/gui/imagenes/table.png")));
		btnDiaryPlanner.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		panel.add(btnDiaryPlanner);
		
		JButton btnOrdenDeServicio = new JButton("Orden de servicio");
		btnOrdenDeServicio.setIcon(new ImageIcon(PortadaReportesCold.class.getResource("/crm/gui/imagenes/page_white_acrobat.png")));
		btnOrdenDeServicio.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		panel.add(btnOrdenDeServicio);
		
		JButton btnOperadores = new JButton("Operadores");
		btnOperadores.setIcon(new ImageIcon(PortadaReportesCold.class.getResource("/crm/gui/imagenes/user.png")));
		btnOperadores.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		panel.add(btnOperadores);
		
		JButton button_3 = new JButton("Salir");
		button_3.setIcon(new ImageIcon(PortadaReportesCold.class.getResource("/crm/gui/imagenes/cross.png")));
		button_3.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		button_3.setBounds(328, 224, 98, 26);
		contentPanel.add(button_3);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 35, 414, 2);
		contentPanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 210, 414, 2);
		contentPanel.add(separator_1);
	}
}
