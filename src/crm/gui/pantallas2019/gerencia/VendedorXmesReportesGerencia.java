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
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import crm.client.deserializer.ArrayOfString;
import crm.client.managers.PresupuestosManager;
import crm.gui.components.ABMTiposEventoMultibox;
import crm.gui.components.ABMVendedoresMultiBox;
import sun.print.resources.serviceui;

import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JRadioButton;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ButtonGroup;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class VendedorXmesReportesGerencia extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JList lst_meses;
	private JList lst_anio;
	private ABMVendedoresMultiBox lst_vendedores;
	private JRadioButton rd_meses;
	private JRadioButton rd_anio;
	private JButton btn_ver;
	private ChartPanel chartPanel;
	private JComboBox cmbEstados;
	private ABMTiposEventoMultibox lstTipoEvt;
	private JCheckBox chkAnioCompleto;
	private JYearChooser yearChooser;
	private JMonthChooser monthChooser;
	private JButton btnSalir;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VendedorXmesReportesGerencia dialog = new VendedorXmesReportesGerencia(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VendedorXmesReportesGerencia(JFrame padre) {
		super(padre);
		setBounds(100, 100, 1280, 720);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("Facturacion por vendedores");
			label.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
			label.setBounds(10, 11, 431, 20);
			contentPanel.add(label);
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "Comparaciones", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 42, 1244, 595);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.setBounds(10, 27, 338, 517);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblVendedores = new JLabel("Vendedores");
		lblVendedores.setBounds(12, 347, 296, 14);
		panel_1.add(lblVendedores);
		
		JLabel label = new JLabel("Estado");
		label.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		label.setBounds(12, 16, 47, 16);
		panel_1.add(label);
		
		cmbEstados = new JComboBox();
		cmbEstados.setModel(new DefaultComboBoxModel(new String[] {"Seleccione un estado", "Confirmado", "Pendiente", "Orden de Servicio", "Orden de Facturacion", "Facturado", "Cobrado", "Cancelado", "Rechazado"}));
		cmbEstados.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		cmbEstados.setBounds(62, 12, 217, 25);
		panel_1.add(cmbEstados);
		
		
		lst_vendedores = new ABMVendedoresMultiBox();
		lst_vendedores.setFixedCellWidth(170);
		lst_vendedores.setVisibleRowCount(-1);
		lst_vendedores.setLayoutOrientation(JList.VERTICAL_WRAP);
		lst_vendedores.setBounds(12, 66, 298, 166);
		lst_vendedores.loadItemsAllVendedores();
		lst_vendedores.setSelectedIndices(new int[] {0});
		//panel_1.add(lst_vendedores);
		
		JScrollPane scrollPane = new JScrollPane(lst_vendedores);
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(12, 368, 316, 137);
		panel_1.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(12, 153, 316, 182);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		rd_meses = new JRadioButton("Meses");
		
		rd_meses.setToolTipText("Comparacion mes vs mes en un mismo a\u00F1o");
		rd_meses.setSelected(true);
		rd_meses.setBounds(6, 7, 109, 23);
		panel_2.add(rd_meses);
		
		
		
		lst_meses = new JList();
		lst_meses.setModel(new AbstractListModel() {
			String[] values = new String[] {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		lst_meses.setSelectedIndices(new int[] {0});
		lst_meses.setBounds(16, 37, 141, 72);
		//panel_2.add(lst_meses);
		
		scrollPane_1 = new JScrollPane(lst_meses);
		scrollPane_1.setBounds(16, 65, 141, 105);
		panel_2.add(scrollPane_1);		
		
		lst_anio = new JList();
		lst_anio.setEnabled(false);
		lst_anio.setModel(new AbstractListModel() {
			String[] values = new String[] {"2015", "2016", "2017", "2018", "2019", "2020"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		lst_anio.setSelectedIndices(new int[] {0});
		lst_anio.setBounds(167, 37, 141, 72);
		//panel_2.add(lst_anio);
		
		scrollPane_2 = new JScrollPane(lst_anio);
		scrollPane_2.setBounds(169, 98, 141, 72);
		panel_2.add(scrollPane_2);
		
		rd_anio = new JRadioButton("A\u00F1os");
		rd_anio.setToolTipText("Comparacion a\u00F1o vs a\u00F1o completo o en un mes en particular");
		rd_anio.setBounds(167, 7, 109, 23);
		panel_2.add(rd_anio);
		
		ButtonGroup bt = new ButtonGroup();
		
		bt.add(rd_anio);
		bt.add(rd_meses);
		
		monthChooser = new JMonthChooser();
		monthChooser.setEnabled(false);
		monthChooser.setMonth(0);
		monthChooser.setBounds(167, 37, 143, 20);
		panel_2.add(monthChooser);
		
		yearChooser = new JYearChooser();
		yearChooser.setBounds(16, 37, 66, 20);
		panel_2.add(yearChooser);
		
		chkAnioCompleto = new JCheckBox("A\u00F1o completo");
		chkAnioCompleto.setEnabled(false);
		chkAnioCompleto.addItemListener(new AnioCompletoChangedListener());
		chkAnioCompleto.setBounds(165, 63, 145, 24);
		panel_2.add(chkAnioCompleto);
		
		JLabel lblTipoEvento = new JLabel("Tipo Evento");
		lblTipoEvento.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		lblTipoEvento.setBounds(12, 55, 70, 16);
		panel_1.add(lblTipoEvento);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_3.setBounds(100, 55, 159, 86);
		panel_1.add(scrollPane_3);
		
		lstTipoEvt = new ABMTiposEventoMultibox();
		lstTipoEvt.loadItems();
		lstTipoEvt.setSelectedIndices(new int[] {0});
		scrollPane_3.setViewportView(lstTipoEvt);
		
		chartPanel = new ChartPanel((JFreeChart) null);
		chartPanel.setBounds(356, 27, 878, 557);
		panel.add(chartPanel);
		
		btn_ver = new JButton("Ver");
		btn_ver.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		btn_ver.setIcon(new ImageIcon(VendedorXmesReportesGerencia.class.getResource("/crm/gui/imagenes/chart_bar.png")));
		btn_ver.addActionListener(new VerActionListener());
		btn_ver.setBounds(120, 556, 98, 26);
		panel.add(btn_ver);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSalir = new JButton("Salir");
				btnSalir.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
				btnSalir.addActionListener(new SalirActionListener());
				btnSalir.setIcon(new ImageIcon(VendedorXmesReportesGerencia.class.getResource("/crm/gui/imagenes/cross.png")));
				btnSalir.setActionCommand("OK");
				buttonPane.add(btnSalir);
				getRootPane().setDefaultButton(btnSalir);
			}
		}
		rd_meses.addChangeListener(new RadioStateChange());
	}
	
	private final class RadioStateChange implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			if(rd_meses.isSelected()){
				lst_meses.setEnabled(true);
				yearChooser.setEnabled(true);
				lst_anio.setEnabled(false);
				monthChooser.setEnabled(false);
				chkAnioCompleto.setEnabled(false);
			}
			else{
				lst_meses.setEnabled(false);
				yearChooser.setEnabled(false);
				lst_anio.setEnabled(true);
				monthChooser.setEnabled(true);
				chkAnioCompleto.setEnabled(true);
			}
		}
	}

	private final class SalirActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

	private final class AnioCompletoChangedListener implements ItemListener {
		public void itemStateChanged(ItemEvent arg0) {
			if( chkAnioCompleto.isSelected()){
				monthChooser.setEnabled(false);
			}
			else monthChooser.setEnabled(true);
		}
	}

	private final class VerActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {		
			try {
				if(isValido()){
					Object[] presupuestos = null;
					String vendedores = convertStringArrayToString(lst_vendedores.searchForeignsArray());
					String anios = convertListToString(lst_anio.getSelectedValuesList());
					String meses = convertIntArrayToString(lst_meses.getSelectedIndices());
					String codTipoEvento = convertStringArrayToString(lstTipoEvt.searchForeignsArray());

					if(rd_anio.isSelected()){				
						if(chkAnioCompleto.isSelected())
							presupuestos = PresupuestosManager.instance().buscarComparacionesGerenciaAnio(vendedores, anios, (String)cmbEstados.getSelectedItem(), codTipoEvento,-1);
						else
							presupuestos = PresupuestosManager.instance().buscarComparacionesGerenciaAnio(vendedores, anios, (String)cmbEstados.getSelectedItem(), codTipoEvento,monthChooser.getMonth());
					}
					else{
						presupuestos = PresupuestosManager.instance().buscarComparacionesGerenciaMeses(vendedores, meses, (String)cmbEstados.getSelectedItem(), codTipoEvento,yearChooser.getYear());
					}

					DefaultCategoryDataset dataset = new DefaultCategoryDataset();

					if(presupuestos != null){
						System.out.println("cant de reg:"+presupuestos.length);
						for(int i=0; i<presupuestos.length; i++){
							Object[] p = (Object[]) presupuestos;
							Object[] presupuestoDato = (Object[])p[i];
							dataset.setValue(((Double)presupuestoDato[1]).doubleValue(), (String)presupuestoDato[3],  (String)presupuestoDato[0]);

						}
					}

					chartPanel.setChart(createBar(dataset));

				}
				else{
					JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un Vendedor de la lista", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		private boolean isValido() {
			if(lst_vendedores.searchForeignsArray().length>0)
				return true;
			return false;
		}

		private JFreeChart createBar( DefaultCategoryDataset dataset){
			JFreeChart chart = ChartFactory.createBarChart(
					"Comparación Ventas",
	                "Vendedores", 
	                "Ventas", 
	                dataset, 
	                PlotOrientation.HORIZONTAL,
	                true, 
	                true, 
	                false
			);
			return chart;
		}

		private String convertStringArrayToString(String[] s){
			StringBuffer result= new StringBuffer();
			for(int i=0;i<s.length;i++){
				result.append(s[i]);
				if(i<=s.length-1){
					result.append(",");
				}
			}
			return result.toString();
		}
		
		//toma un array con los meses del 0 al 11 y lo convierte en un string del tipo
		private String convertIntArrayToString(int[] s){
			StringBuffer result= new StringBuffer();
			for(int i=0;i<s.length;i++){
				if((s[i]+1)<10)
					result.append("0");
				result.append((s[i]+1));
				if(i<=s.length-1){
					result.append(",");
				}
			}
			return result.toString();
		}
		
		private String convertListToString(List<String> s){
			StringBuffer result= new StringBuffer();
			for(int i=0;i<s.size();i++){
				result.append(s.get(i));
				if(i<=s.size()-1){
					result.append(",");
				}
			}
			return result.toString();
		}
	}
}
