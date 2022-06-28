package crm.gui.pantallas2019.cold;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import com.toedter.calendar.JCalendar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

import crm.client.managers.PresupuestosManager;
import crm.client.util.DateConverter;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.ABMOperadoresMultiBox;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesItem;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesTableModel;
import crm.gui.pantallas2019.cold.componentes.OperadoresListModel;
import crm.gui.pantallas2019.gerencia.BuscadorReportesGerencia;
import crm.gui.tablerenderer.buscadorPptoOperador.BuscadorPptosOperadorItem;
import crm.gui.tablerenderer.buscadorPptoOperador.BuscadorPptosOperadorModel;
import crm.gui.tablerenderer.buscadorPptoOperador.TableRenderPptoOperador;
import crm.libraries.abm.entities.Operador;

import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OperadoresReporte extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private TableRenderPptoOperador table;
	private JList<Operador> lst_disponibles;
	private JList<Operador> lst_seleccionados;
	private JButton btn_agregar;
	private JButton btn_quitar;
	private JScrollPane scrollPane;
	private JDateChooser dateChooser;
	private JScrollPane scrollPane_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			OperadoresReporte dialog = new OperadoresReporte();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public OperadoresReporte() {
		setBounds(100, 100, 1280, 720);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblAsignacionDeOperadores = new JLabel("Asignacion de Operadores");
		lblAsignacionDeOperadores.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAsignacionDeOperadores.setBounds(10, 11, 301, 22);
		contentPanel.add(lblAsignacionDeOperadores);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 44, 1234, 2);
		contentPanel.add(separator);
		
		JPanel panel = new JPanel();
		panel.setBounds(725, 57, 529, 580);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 36, 200, 533);
		panel.add(scrollPane_1);
		
		lst_disponibles = new JList<Operador>();
		lst_disponibles.setEnabled(false);
		OperadoresListModel model = new OperadoresListModel();
		model.loadItems();
		lst_disponibles.setModel(model);
		scrollPane_1.setViewportView(lst_disponibles);
		
		JLabel lblOperadoresDisponibles = new JLabel("Operadores Disponibles");
		lblOperadoresDisponibles.setBounds(10, 11, 200, 14);
		panel.add(lblOperadoresDisponibles);
		
		btn_quitar = new JButton("<<");
		btn_quitar.addActionListener(new QuitarActionListener());
		btn_quitar.setBounds(220, 239, 89, 23);
		panel.add(btn_quitar);
		
		btn_agregar = new JButton(">>");
		btn_agregar.addActionListener(new AgregarActionListener());
		btn_agregar.setBounds(220, 207, 89, 23);
		panel.add(btn_agregar);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(319, 36, 200, 533);
		panel.add(scrollPane_2);
		
		lst_seleccionados = new JList<Operador>();
		OperadoresListModel modelSeleccionados = new OperadoresListModel();
		lst_seleccionados.setModel(modelSeleccionados);
		scrollPane_2.setViewportView(lst_seleccionados);
		
		
		JLabel lblNewLabel = new JLabel("Operadores seleccionados");
		lblNewLabel.setBounds(319, 11, 200, 14);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 57, 705, 580);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_1 = new JLabel("Seleccione una fecha");
		label_1.setBounds(10, 11, 177, 14);
		panel_1.add(label_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 68, 685, 501);
		panel_1.add(scrollPane);
		
		table = new TableRenderPptoOperador();
		table.getTable().addMouseListener(new TableMouseListener());
		
		scrollPane.setColumnHeaderView(table);
		
		dateChooser = new JDateChooser();
		dateChooser.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
		dateChooser.setDateFormatString("EEEEEEEE dd/MM/yyyy");
		dateChooser.setDate(new Date());
		dateChooser.getCalendarButton().setIcon(new ImageIcon(BuscadorReportesGerencia.class.getResource("/crm/gui/imagenes/date.png")));
		dateChooser.setBounds(10, 37, 193, 20);
		panel_1.add(dateChooser);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new BuscarActionListener());
		btnBuscar.setBounds(213, 34, 89, 23);
		panel_1.add(btnBuscar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.print("CAnt: "+lst_seleccionados.getModel().getSize());
					}
				});
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
	
	private final class TableMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {			
			OperadoresListModel modelSeleccionado = (OperadoresListModel)lst_seleccionados.getModel();
			modelSeleccionado.eliminarTodosOperador();
			
			//traer los operadores de un presupuesto y en una fecha
			
			if(!lst_disponibles.isEnabled())
				lst_disponibles.setEnabled(true);
		}
	}

	private final class BuscarActionListener implements ActionListener {
		
		private String fechaDesde;
		private String fechaHasta;
		
		public void actionPerformed(ActionEvent e) {
			scrollPane.setViewportView(table.getTable());
			buscarEventos();

		}
		
		private void setCriteria(){

			fechaDesde = DateConverter.convertDateToString(dateChooser.getDate(), "yyyy-MM-dd");
			fechaHasta = DateConverter.convertDateToString(dateChooser.getDate(), "yyyy-MM-dd");

		}
		
		private void buscarEventos(){
			try {
				Object[] presupuestos = null;
				BuscadorPptosOperadorModel model = new BuscadorPptosOperadorModel();		 

				setCriteria();				
				presupuestos = PresupuestosManager.instance().buscarPptosParaOperadores(fechaDesde, fechaHasta);

				if(presupuestos != null){
					for(int i=0; i<presupuestos.length; i++){
						BuscadorPptosOperadorItem item = new BuscadorPptosOperadorItem();

						Object[] p = (Object[]) presupuestos;
						Object[] presupuestoDato = (Object[])p[i];

						item.setNumeroPpto((Long)(presupuestoDato[0]));			
						item.setNombreEvento((String)presupuestoDato[1]);
						item.setLugarEvento((String)presupuestoDato[2]);

						model.addRow(item);

					}	
				}
				table.getTable().setModel(model);
				table.refreshTable();
				if(model.getRowCount() <= 0){
					Util.alertMsg(Main.getVentana(), "No se encontraron presupuestos");					
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}

	private final class QuitarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			OperadoresListModel modelSeleccionado = (OperadoresListModel)lst_seleccionados.getModel();
			if(lst_seleccionados.getSelectedIndices().length >0){
				for(int i=lst_seleccionados.getSelectedIndices().length-1;i>=0;i--){	
					modelSeleccionado.eliminarOperador(lst_seleccionados.getSelectedIndices()[i]);					
				}
			}
		}
	}

	private final class AgregarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			OperadoresListModel modelDispobible = (OperadoresListModel)lst_disponibles.getModel();
			OperadoresListModel modelSeleccionado = (OperadoresListModel)lst_seleccionados.getModel();
			if(lst_disponibles.getSelectedIndices().length >0){
				for(int i=0;i<lst_disponibles.getSelectedIndices().length;i++){					
					modelSeleccionado.addOperador(modelDispobible.getElementAt(lst_disponibles.getSelectedIndices()[i]));					
				}
			}
		}
	}
}
