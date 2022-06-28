package crm.gui.pantallas2019.gerencia;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jvnet.substance.SubstanceLookAndFeel;

import javax.swing.border.BevelBorder;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import java.awt.Font;
import crm.gui.components.ABMVendedoresComboBox;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesItem;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesTableModel;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesVendedoresItem;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesVendedoresTable;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesVendedoresTableModel;
import crm.libraries.abm.entities.Vendedor;
import crm.client.managers.PresupuestosManager;
import crm.client.managers.VendedorManager;
import crm.client.util.DateConverter;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.ABMTiposEventoMultibox;
import crm.gui.components.ABMTiposEventosComboBox;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import com.toedter.calendar.JMonthChooser;
import javax.swing.JRadioButton;
import com.toedter.calendar.JYearChooser;
import java.awt.Toolkit;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.border.EtchedBorder;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class VendedoresReportesGerencia extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JMonthChooser monthChooser;
	private JYearChooser yearChooser;
	private JRadioButton rdAnual;
	private JRadioButton rdMensual;
	private JComboBox cmbEstado;
	private JButton btnBuscar;
	private BuscadorReportesVendedoresTable tblVendedores;
	private JScrollPane scrollPane;
	private JYearChooser yearMonthChooser;
	private JLabel lblTotal;
	private JLabel lblCant;
	private ChartPanel chartPanel;
	private ABMTiposEventoMultibox lstTipoEvento;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JFrame.setDefaultLookAndFeelDecorated(true); 
			SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.NebulaSkin");
			VendedoresReportesGerencia dialog = new VendedoresReportesGerencia(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VendedoresReportesGerencia(JFrame padre) {
		super(padre);
		setResizable(false);
		setTitle("Reporte de Vendedores");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VendedoresReportesGerencia.class.getResource("/crm/gui/imagenes/LogoCongressPeque.png")));
		setBounds(100, 100, 1280, 720);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(128, 128, 128)));
		panel.setBounds(10, 40, 1242, 124);
		contentPanel.add(panel);
		
		JLabel label_3 = new JLabel("Tipo de evento");
		label_3.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		label_3.setBounds(429, 20, 117, 25);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Estado");
		label_4.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		label_4.setBounds(734, 24, 47, 16);
		panel.add(label_4);
		
		cmbEstado = new JComboBox();
		cmbEstado.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		cmbEstado.setBounds(785, 20, 217, 25);
		cmbEstado.setModel(new DefaultComboBoxModel(new String[] {"Seleccione un estado", "Confirmado", "Pendiente", "Orden de Servicio", "Orden de Facturacion", "Facturado", "Cobrado", "Cancelado", "Rechazado"}));
		panel.add(cmbEstado);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(1006, 20, 224, 56);
		panel.add(panel_1);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new BuscarActionListener());
		btnBuscar.setIcon(new ImageIcon(VendedoresReportesGerencia.class.getResource("/crm/gui/imagenes/magnifier.png")));
		btnBuscar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnBuscar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBuscar.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
		btnBuscar.setBounds(72, 0, 100, 56);
		panel_1.add(btnBuscar);
		
		monthChooser = new JMonthChooser();
		monthChooser.getComboBox().setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		monthChooser.setBounds(145, 21, 143, 20);
		panel.add(monthChooser);
		
		rdMensual = new JRadioButton("Mensual");
		
		rdMensual.setSelected(true);
		rdMensual.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		rdMensual.setBounds(26, 20, 111, 24);
		panel.add(rdMensual);
		
		rdAnual = new JRadioButton("Anual");
		rdAnual.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		rdAnual.setBounds(26, 52, 90, 24);
		panel.add(rdAnual);
		
		ButtonGroup bt = new ButtonGroup();
		
		bt.add(rdAnual);
		bt.add(rdMensual);
	
		yearChooser = new JYearChooser();
		yearChooser.setEnabled(false);
		yearChooser.setStartYear(2000);
		yearChooser.getSpinner().setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		yearChooser.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		yearChooser.setBounds(145, 54, 82, 20);
		panel.add(yearChooser);
		
		yearMonthChooser = new JYearChooser();
		yearMonthChooser.getSpinner().setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		yearMonthChooser.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		yearMonthChooser.setBounds(295, 21, 82, 20);
		panel.add(yearMonthChooser);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(546, 20, 176, 91);
		panel.add(scrollPane_1);
		
		lstTipoEvento = new ABMTiposEventoMultibox();		
		scrollPane_1.setViewportView(lstTipoEvento);
		lstTipoEvento.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lstTipoEvento.setLayoutOrientation(JList.VERTICAL_WRAP);
		lstTipoEvento.setVisibleRowCount(-1);
		lstTipoEvento.loadItems();
		//lstTipoEvento.setSelectedIndices(new int[] {0});
		
		JLabel lblVendedores = new JLabel("Facturacion por vendedores");
		lblVendedores.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		lblVendedores.setBounds(10, 11, 431, 20);
		contentPanel.add(lblVendedores);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "Resultados", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		panel_3.setBounds(10, 172, 410, 442);
		contentPanel.add(panel_3);
		panel_3.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 23, 386, 369);
		panel_3.add(scrollPane);
		
		tblVendedores = new BuscadorReportesVendedoresTable();
		scrollPane.setViewportView(tblVendedores);
		{
			JButton btnExportar = new JButton("Exportar");
			btnExportar.setBounds(292, 404, 106, 27);
			panel_3.add(btnExportar);
			btnExportar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tblVendedores.openSavePopUp();
				}
			});
			btnExportar.setIcon(new ImageIcon(VendedoresReportesGerencia.class.getResource("/crm/gui/imagenes/page_excel.png")));
			btnExportar.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			btnExportar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnExportar);
		}
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "Grafica", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(128, 128, 128)));
		panel_2.setBounds(432, 172, 821, 442);
		contentPanel.add(panel_2);
		
		chartPanel = new ChartPanel((JFreeChart) null);
		chartPanel.setBounds(28, 33, 761, 397);
		panel_2.add(chartPanel);
		
		lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Gill Sans MT", Font.BOLD, 18));
		lblTotal.setBounds(10, 626, 227, 16);
		contentPanel.add(lblTotal);
		
		lblCant = new JLabel("Cantidad de vendedores");
		lblCant.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		lblCant.setBounds(249, 626, 250, 16);
		contentPanel.add(lblCant);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				
				btnNewButton = new JButton("Comparar");
				btnNewButton.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
				btnNewButton.addActionListener(new CompararActionListener());
				btnNewButton.setIcon(new ImageIcon(VendedoresReportesGerencia.class.getResource("/crm/gui/imagenes/chart_bar.png")));
				buttonPane.add(btnNewButton);
				btnSalir.setIcon(new ImageIcon(VendedoresReportesGerencia.class.getResource("/crm/gui/imagenes/cross.png")));
				btnSalir.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
		rdMensual.addChangeListener(new RdButtonItemListener());
	}
	
	private JFreeChart createPie(DefaultPieDataset dataset){

			JFreeChart chart = ChartFactory.createPieChart("Facturacion por vendedor", dataset, true, true, false);
			return chart;

			
	}
	
	private final class RdButtonItemListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			if(rdMensual.isSelected()){
				yearChooser.setEnabled(false);
				yearMonthChooser.setEnabled(true);
				monthChooser.setEnabled(true);
			}
			else{
				yearChooser.setEnabled(true);
				yearMonthChooser.setEnabled(false);
				monthChooser.setEnabled(false);
			}
			
		}
	}

	private final class CompararActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				VendedorXmesReportesGerencia dialog = new VendedorXmesReportesGerencia(Main.getVentana());
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private final class BuscarActionListener implements ActionListener {
		
		private String fechaDesde;
		private String fechaHasta;
		private String estado;
		private String codTipoEvento;
		
		private int getCantDiasXMes(int mes, int anyo){
			int numDias;
			switch (mes+1) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                numDias = 31;
                break;
            case 4: case 6: case 9: case 11:
                numDias = 30;
                break;
            case 2:
                if((anyo%4==0 && anyo%100!=0) || anyo%400==0){
                    numDias = 29;
                }
                else{
                    numDias = 28;
                }
                break;
            default:
                System.out.println("\nEl mes " + mes + " es incorrecto.");
                numDias=0;
                break;
			}
			return numDias;
		}
		
		private void setCriteria(){
			Date dateDesde;
			Date dateHasta;
			if(rdMensual.isSelected()){
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.set(yearMonthChooser.getYear(), monthChooser.getMonth(), 1);				
				dateDesde = calendar.getTime();
				
				calendar.set(yearMonthChooser.getYear(), monthChooser.getMonth(), getCantDiasXMes(monthChooser.getMonth(), yearMonthChooser.getYear()));
				dateHasta = calendar.getTime();
			}
			else{
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.set(yearChooser.getYear(), 0, 1);				
				dateDesde = calendar.getTime();
				
				calendar.set(yearChooser.getYear(), 11, 31);
				dateHasta = calendar.getTime();
			}
			fechaDesde = DateConverter.convertDateToString(dateDesde, "yyyy-MM-dd");
			fechaHasta = DateConverter.convertDateToString(dateHasta, "yyyy-MM-dd");

			if(cmbEstado.getSelectedIndex()>0){
				estado= (String)cmbEstado.getSelectedItem();
			}
			else
				estado=null;

			if(lstTipoEvento.getSelectedIndices().length>0){
				codTipoEvento= lstTipoEvento.searchForeigns();
			}
			else
				codTipoEvento=null;			
			
		}
		
		private void buscarReportes(){
			try {
				Object[] presupuestos = null;
				BuscadorReportesVendedoresTableModel model = new BuscadorReportesVendedoresTableModel();		 

				setCriteria();				
				presupuestos = PresupuestosManager.instance().buscarParaReportesGerenciaVendedores(null,fechaDesde,fechaHasta,null,estado,codTipoEvento,null);
				
				DefaultPieDataset dataset = new DefaultPieDataset();
				
				double tot=0;
				int cant=0;
				if(presupuestos != null){
					for(int i=0; i<presupuestos.length; i++){
						BuscadorReportesVendedoresItem item = new BuscadorReportesVendedoresItem();

						Object[] p = (Object[]) presupuestos;
						Object[] presupuestoDato = (Object[])p[i];

						cant++;
						item.setVendedor((String)presupuestoDato[0]);
						item.setTotal(((Double)presupuestoDato[1]).doubleValue());
						item.setCantidad(((Integer)presupuestoDato[2]).intValue());
						if(((Integer)presupuestoDato[2]).intValue() >0)
							item.setPromedio(((Double)presupuestoDato[1]).doubleValue()/((Integer)presupuestoDato[2]).intValue());
						
						dataset.setValue(item.getVendedor(), item.getTotal());
						
						tot=tot+(Double)presupuestoDato[1];
						model.addRow(item);
						lblCant.setText("Cantidad de vendedores "+cant);
						lblTotal.setText("Total "+getTotalFormateado(tot));
					}	

				}
				tblVendedores.getTable().setModel(model);
				tblVendedores.refreshTable();
				
				chartPanel.setChart(createPie(dataset));
								
				if(model.getRowCount() <= 0){
					Util.alertMsg(Main.getVentana(), "No se encontraron presupuestos");					
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		
		public void actionPerformed(ActionEvent e) {
			scrollPane.setViewportView(tblVendedores.getTable());
			buscarReportes();
		}
		
		public String getTotalFormateado(double tot) {
			return getCurrencyFormat().format(tot);
		}
		
		private NumberFormat getCurrencyFormat() {
			NumberFormat currencyFormat;
			Locale l = new Locale("es","AR");
			currencyFormat = NumberFormat.getCurrencyInstance(l);
			return currencyFormat;
		}
	}
}
