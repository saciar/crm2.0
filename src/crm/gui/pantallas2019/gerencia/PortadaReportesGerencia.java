package crm.gui.pantallas2019.gerencia;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import javax.swing.JSeparator;

import crm.gui.Main;

public class PortadaReportesGerencia extends JDialog {
		
	private JButton btnPresupuestos;
	private JButton btnSalir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PortadaReportesGerencia dialog = new PortadaReportesGerencia(null);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public PortadaReportesGerencia(JFrame padre) {
		super(padre);
		setTitle("Reportes Gerencia");
		setIconImage(Toolkit.getDefaultToolkit().getImage(PortadaReportesGerencia.class.getResource("/crm/gui/imagenes/LogoCongressPeque.png")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione el reporte a visualizar");
		lblNewLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 12, 414, 20);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(119, 44, 196, 149);
		getContentPane().add(panel);
		
		btnPresupuestos = new JButton("Presupuestos");
		btnPresupuestos.setIcon(new ImageIcon(PortadaReportesGerencia.class.getResource("/crm/gui/imagenes/report.png")));
		btnPresupuestos.addActionListener(new PresupuestosActionListener());
		FlowLayout fl_panel = new FlowLayout(FlowLayout.CENTER, 5, 15);
		panel.setLayout(fl_panel);
		btnPresupuestos.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		panel.add(btnPresupuestos);
		
		JButton btnVendedores = new JButton("Vendedores");
		btnVendedores.addActionListener(new VendedoresActionListener());
		btnVendedores.setIcon(new ImageIcon(PortadaReportesGerencia.class.getResource("/crm/gui/imagenes/user.png")));
		btnVendedores.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		panel.add(btnVendedores);
		
		JButton btnNewButton_2 = new JButton("Venues");
		btnNewButton_2.setIcon(new ImageIcon(PortadaReportesGerencia.class.getResource("/crm/gui/imagenes/building.png")));
		btnNewButton_2.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		panel.add(btnNewButton_2);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new SalirActionListener());
		btnSalir.setIcon(new ImageIcon(PortadaReportesGerencia.class.getResource("/crm/gui/imagenes/cross.png")));
		btnSalir.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		btnSalir.setBounds(326, 224, 98, 26);
		getContentPane().add(btnSalir);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 35, 414, 2);
		getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 210, 414, 2);
		getContentPane().add(separator_1);

	}
	
	private final class SalirActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

	private final class VendedoresActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				VendedoresReportesGerencia dialog = new VendedoresReportesGerencia(Main.getVentana());
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private final class PresupuestosActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				BuscadorReportesGerencia dialog = new BuscadorReportesGerencia(Main.getVentana());
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
