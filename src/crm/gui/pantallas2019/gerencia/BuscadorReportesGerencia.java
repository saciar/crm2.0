package crm.gui.pantallas2019.gerencia;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

import crm.client.managers.PresupuestosManager;
import crm.client.util.DateConverter;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.ABMTiposEventoMultibox;
import crm.gui.components.ABMTiposEventosComboBox;
import crm.gui.components.ABMVendedoresComboBox;
import crm.gui.components.ABMVendedoresMultiBox;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesItem;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesTableModel;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesTableRender;

import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import javax.swing.JList;

public class BuscadorReportesGerencia extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel pnlFiltros;
	private BuscadorReportesTableRender tblResultados;
	private JDateChooser dateDesde;
	private JDateChooser dateHasta;
	private JComboBox cmbEstados;
	private JButton btnBuscar;
	private JScrollPane scrollPane;
	private JLabel lblTotal;
	private JLabel lblCant;
	private ABMTiposEventoMultibox lst_tipos;
	private ABMVendedoresMultiBox lst_vendedores;
	private JComboBox cmb_unidades;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscadorReportesGerencia dialog = new BuscadorReportesGerencia(null);
			dialog.setLocationRelativeTo(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BuscadorReportesGerencia(JFrame padre) {
		super(padre);
		setResizable(false);		
		setTitle("Reporte de Presupuestos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(BuscadorReportesGerencia.class.getResource("/crm/gui/imagenes/LogoCongressPeque.png")));
		setBounds(100, 100, 1280, 720);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Presupuestos");
		lblNewLabel.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 196, 20);
		contentPanel.add(lblNewLabel);
		{
			pnlFiltros = new JPanel();
			pnlFiltros.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(128, 128, 128)));
			pnlFiltros.setBounds(10, 40, 1242, 157);
			contentPanel.add(pnlFiltros);
			pnlFiltros.setLayout(null);
			
			dateDesde = new JDateChooser();
			dateDesde.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			dateDesde.setDate(new Date());
			dateDesde.getCalendarButton().setIcon(new ImageIcon(BuscadorReportesGerencia.class.getResource("/crm/gui/imagenes/date.png")));
			dateDesde.setBounds(76, 20, 217, 20);
			pnlFiltros.add(dateDesde);
			
			JLabel lblNewLabel_1 = new JLabel("Desde");
			lblNewLabel_1.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblNewLabel_1.setBounds(28, 22, 47, 16);
			pnlFiltros.add(lblNewLabel_1);
			
			JLabel lblHasta = new JLabel("Hasta");
			lblHasta.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblHasta.setBounds(334, 22, 47, 16);
			pnlFiltros.add(lblHasta);
			
			dateHasta = new JDateChooser();
			dateHasta.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			dateHasta.setDate(new Date());
			dateHasta.getCalendarButton().setIcon(new ImageIcon(BuscadorReportesGerencia.class.getResource("/crm/gui/imagenes/date.png")));
			dateHasta.setBounds(378, 20, 217, 20);
			pnlFiltros.add(dateHasta);
			
			JLabel lblVendedor = new JLabel("Vendedor");
			lblVendedor.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblVendedor.setBounds(28, 56, 64, 16);
			pnlFiltros.add(lblVendedor);
			
			JLabel lblTipoDeEvento = new JLabel("Tipo de evento");
			lblTipoDeEvento.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblTipoDeEvento.setBounds(463, 56, 96, 16);
			pnlFiltros.add(lblTipoDeEvento);
			
			JLabel lblEstado = new JLabel("Estado");
			lblEstado.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblEstado.setBounds(704, 56, 47, 16);
			pnlFiltros.add(lblEstado);
			
			cmbEstados = new JComboBox();
			cmbEstados.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			cmbEstados.setModel(new DefaultComboBoxModel(new String[] {"Seleccione un estado", "Confirmado", "Pendiente", "Orden de Servicio", "Orden de Facturacion", "Facturado", "Cobrado", "Cancelado", "Rechazado"}));
			cmbEstados.setBounds(754, 52, 217, 25);
			pnlFiltros.add(cmbEstados);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(1006, 49, 224, 56);
			pnlFiltros.add(panel_1);
			panel_1.setLayout(null);
			
			btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new BuscarActionListener());
			btnBuscar.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnBuscar.setHorizontalTextPosition(SwingConstants.CENTER);
			btnBuscar.setIcon(new ImageIcon(BuscadorReportesGerencia.class.getResource("/crm/gui/imagenes/magnifier.png")));
			btnBuscar.setFont(new Font("Gill Sans MT", Font.BOLD, 16));
			btnBuscar.setBounds(72, 0, 100, 56);
			panel_1.add(btnBuscar);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(561, 56, 112, 85);
			pnlFiltros.add(scrollPane_1);
			
			lst_tipos = new ABMTiposEventoMultibox();
			lst_tipos.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			lst_tipos.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			lst_tipos.loadItems();
			lst_tipos.setSelectedIndex(0);
			scrollPane_1.setViewportView(lst_tipos);
			
			JScrollPane scrollPane_2 = new JScrollPane();
			scrollPane_2.setBounds(97, 56, 348, 89);
			pnlFiltros.add(scrollPane_2);
			
			lst_vendedores = new ABMVendedoresMultiBox();
			lst_vendedores.setVisibleRowCount(4);
			lst_vendedores.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			lst_vendedores.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			lst_vendedores.loadItemsAllVendedores();
			lst_vendedores.setSelectedIndex(0);
			scrollPane_2.setViewportView(lst_vendedores);
			
			JLabel lblUnidadDeNegocio = new JLabel("Unidad de negocio");
			lblUnidadDeNegocio.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			lblUnidadDeNegocio.setBounds(704, 109, 112, 16);
			pnlFiltros.add(lblUnidadDeNegocio);
			
			cmb_unidades = new JComboBox();
			cmb_unidades.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			cmb_unidades.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una unidad", "Baires", "Rural"}));
			cmb_unidades.setBounds(828, 105, 165, 25);
			pnlFiltros.add(cmb_unidades);
		}
		
		JPanel pnlTabla = new JPanel();
		pnlTabla.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "Resultados", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		pnlTabla.setBounds(10, 197, 1242, 409);
		contentPanel.add(pnlTabla);
		pnlTabla.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 29, 1218, 368);
		pnlTabla.add(scrollPane);
		
		tblResultados = new BuscadorReportesTableRender();
		scrollPane.setColumnHeaderView(tblResultados);
		
		lblCant = new JLabel("Cantidad de presupuestos");
		lblCant.setFont(new Font("Gill Sans MT", Font.PLAIN, 16));
		lblCant.setBounds(20, 616, 250, 16);
		contentPanel.add(lblCant);
		
		lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Gill Sans MT", Font.BOLD, 18));
		lblTotal.setBounds(1025, 618, 227, 16);
		contentPanel.add(lblTotal);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnExportar = new JButton("Exportar");
			btnExportar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tblResultados.openSavePopUp();
				}
			});
			btnExportar.setIcon(new ImageIcon(BuscadorReportesGerencia.class.getResource("/crm/gui/imagenes/page_excel.png")));
			btnExportar.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
			buttonPane.add(btnExportar);
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnSalir.setIcon(new ImageIcon(BuscadorReportesGerencia.class.getResource("/crm/gui/imagenes/cross.png")));
				btnSalir.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
				btnSalir.setActionCommand("OK");
				buttonPane.add(btnSalir);
				getRootPane().setDefaultButton(btnSalir);
			}
		}
	}
	
	private final class BuscarActionListener implements ActionListener {

		private String fechaDesde;
		private String fechaHasta;
		private String codVendedores;
		private String estado;
		private String codTipoEvento;
		private String codLugar;
		
		public void actionPerformed(ActionEvent arg0) {
			scrollPane.setViewportView(tblResultados.getTable());
			buscarReportes();
		}
		
		private void setCriteria(){

			fechaDesde = DateConverter.convertDateToString(dateDesde.getDate(), "yyyy-MM-dd");
			fechaHasta = DateConverter.convertDateToString(dateHasta.getDate(), "yyyy-MM-dd");

			if(cmbEstados.getSelectedIndex()>0){
				estado= (String)cmbEstados.getSelectedItem();
			}
			else
				estado=null;
			
			if(lst_vendedores.getSelectedIndices().length>0){
				codVendedores=lst_vendedores.searchForeigns();
			}
			else
				codVendedores =null;
			
			if(lst_tipos.getSelectedIndices().length>0){
				codTipoEvento= lst_tipos.searchForeigns();
			}
			else
				codTipoEvento=null;		

			if(cmb_unidades.getSelectedIndex()==2)
				codLugar="21";			
			else if(cmb_unidades.getSelectedIndex()==1)
				codLugar="0";
			else codLugar=null;
			
			
		}


		private void buscarReportes(){
			try {
				Object[] presupuestos = null;
				BuscadorReportesTableModel model = new BuscadorReportesTableModel();		 

				setCriteria();				
				presupuestos = PresupuestosManager.instance().buscarParaReportesGerencia(null,fechaDesde,fechaHasta,codLugar,estado,codTipoEvento,codVendedores);

				double tot=0;
				int cant=0;
				if(presupuestos != null){
					for(int i=0; i<presupuestos.length; i++){
						BuscadorReportesItem item = new BuscadorReportesItem();

						Object[] p = (Object[]) presupuestos;
						Object[] presupuestoDato = (Object[])p[i];

						cant++;
						item.setNumeroPpto((Long)(presupuestoDato[0]));
						item.setEstado((String)presupuestoDato[5]);
						item.setVendedor((String)presupuestoDato[1]);
						item.setCliente((String)presupuestoDato[2]);				
						item.setNombreEvento((String)presupuestoDato[3]);
						item.setFechaInicio((String)presupuestoDato[4]);
						item.setTotal(((Double)presupuestoDato[6]).doubleValue());
						item.setLugar((String)presupuestoDato[7]);
						item.setFechaFinal((String)presupuestoDato[8]);
						item.setTipoEvento((String)presupuestoDato[9]);
						tot=tot+(Double)presupuestoDato[6];
						model.addRow(item);
						lblCant.setText("Cantidad de presupuestos "+cant);
						lblTotal.setText("Total "+getTotalFormateado(tot));
					}	

				}
				tblResultados.getTable().setModel(model);
				tblResultados.refreshTable();
				if(model.getRowCount() <= 0){
					Util.alertMsg(Main.getVentana(), "No se encontraron presupuestos");					
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

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
