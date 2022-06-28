package crm.gui.pantalla;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import crm.client.helper.UserRolesUtil;
import crm.client.report.ReportBuilder;
import crm.client.util.DateConverter;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.ABMLugarEvtComboBox;
import crm.gui.components.CustomTextField;
import crm.gui.components.DatePanel;
import crm.gui.components.PanelImagen;

public class BuscadorReportesAdmin extends PantallaEmergente{
	
	public static final int REPORT_TYPE_WEEK = 1;
	public static final int REPORT_TYPE_DAILY = 2;
	public static final int REPORT_TYPE_LISTA = 3;
	public static final int REPORT_TYPE_MES= 4;
	
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.JRadioButton dailyButton;
	private DatePanel dteFechaMesAnio;
	private javax.swing.JButton exit;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JSeparator jSeparator2;
	private javax.swing.JSeparator jSeparator3;
	private javax.swing.JLabel lblIngrese;
	private javax.swing.JRadioButton mesButton;
	private javax.swing.JLabel tipoPptos;
	private javax.swing.JButton viewReport;
	private javax.swing.JRadioButton weekButton;
	private javax.swing.JSeparator jSeparator4;
	private javax.swing.JRadioButton listaButton;
	private javax.swing.JLabel jLabel3;	
	private JTextField txtWeek;	
	private DatePanel dteFecha;
	private ABMLugarEvtComboBox cmbLugares;
	
	private int currentTypeReport;	
	
	public BuscadorReportesAdmin (Frame owner){
		super("Buscador de reportes",owner);
		//this.setTitle("Buscador de reportes");
		this.setModal(true);
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setEnabled(true);
        currentTypeReport = REPORT_TYPE_WEEK;
    }
	
	public void init(){
		//**************Crecion de panel contenedor con imagen de fondo**********************
		PanelImagen panel = null;
		try{
			panel = new PanelImagen("http://200.80.201.51:8888/app_files/WorldLight.jpg", false);
		}
		catch(Exception e){
			System.out.println("Error");
			panel = new PanelImagen();
		}
		
		//**************Crecion de componentes de pantalla************************************
		buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        exit = new javax.swing.JButton();
        tipoPptos = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        weekButton = new javax.swing.JRadioButton();
        lblIngrese = new javax.swing.JLabel();
        txtWeek = new javax.swing.JFormattedTextField();
        jSeparator2 = new javax.swing.JSeparator();
        mesButton = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        dteFechaMesAnio = CustomTextField.getDateInstance();
        jSeparator3 = new javax.swing.JSeparator();
        dailyButton = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        dteFecha = CustomTextField.getDateInstance();
        viewReport = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        listaButton = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        //cmbLugares = new ABMLugarEvtComboBox();        
        
        //**************Seteos de los componentes********************************************
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Reportes");

        exit.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
        exit.setMnemonic('s');
        exit.setText("Salir");
        exit.setToolTipText("Click para salir");        

        tipoPptos.setFont(new java.awt.Font("Tahoma", 1, 14));

        jLabel2.setText("Seleccione un tipo de reporte a visualizar");

        buttonGroup1.add(weekButton);
        weekButton.setText("Informaci\u00f3n semanal");
        weekButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        weekButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        weekButton.setBackground(new Color(205,205,205));
        weekButton.setSelected(true);

        lblIngrese.setText("Ingrese nro de week:");

        buttonGroup1.add(mesButton);
        mesButton.setText("Informaci\u00f3n mensual");
        mesButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        mesButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        mesButton.setBackground(new Color(218,215,208));
        
        jLabel4.setText("Ingrese mes y a\u00f1o:");       
        
        buttonGroup1.add(dailyButton);
        dailyButton.setText("Diary planner");
        dailyButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        dailyButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        dailyButton.setBackground(new Color(218,215,208));
        
        jLabel5.setText("Ingrese fecha:");
        
        buttonGroup1.add(listaButton);
        listaButton.setText("Lista de precios");
        listaButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        listaButton.setMargin(new java.awt.Insets(0, 0, 0, 0));        
        
        jLabel3.setText("Seleccione un lugar");
        
        viewReport.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));
        viewReport.setMnemonic('v');
        viewReport.setText("Ver Reporte");
        viewReport.setToolTipText("Click para ver reporte");       
        
        //**************Crecion de layout GroupLayout de Netbeans IDE 5.0 con los componentes
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(layout.createSequentialGroup()
                                    .add(jLabel1)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(tipoPptos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 328, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
                                .add(layout.createSequentialGroup()
                                    .add(10, 10, 10)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(layout.createSequentialGroup()
                                            .add(10, 10, 10)
                                            .add(weekButton)
                                            .add(55, 55, 55)
                                            .add(lblIngrese)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(txtWeek, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 37, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .add(jLabel2))
                                    .add(96, 96, 96))))
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE))
                        .add(layout.createSequentialGroup()
                            .add(30, 30, 30)
                            .add(mesButton)
                            .add(56, 56, 56)
                            .add(jLabel4)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(dteFechaMesAnio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jSeparator3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE))
                        .add(layout.createSequentialGroup()
                            .add(29, 29, 29)
                            .add(dailyButton)
                            .add(94, 94, 94)
                            .add(jLabel5)
                            .add(47, 47, 47)
                            .add(dteFecha, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jSeparator4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE))
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                    .add(28, 28, 28)
                                    .add(listaButton)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .add(jLabel3))
                                .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                    .add(185, 185, 185)
                                    .add(viewReport)))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                /*.add(cmbLugares, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 163, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)*/
                                .add(exit))))
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel1)
                        .add(tipoPptos))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jLabel2)
                    .add(15, 15, 15)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(weekButton)
                        .add(lblIngrese)
                        .add(txtWeek, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(mesButton)
                            .add(jLabel4))
                        .add(dteFechaMesAnio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(dailyButton)
                            .add(jLabel5))
                        .add(dteFecha, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(listaButton)
                        .add(jLabel3)
                        /*.add(cmbLugares, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)*/)
                    .add(28, 28, 28)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(viewReport)
                        .add(exit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(35, Short.MAX_VALUE))
        );
            
	    this.getContentPane().add(panel);
        this.pack();
        
        JDialog.setDefaultLookAndFeelDecorated(true);
	    createListeners();
	    loadAndInitComponents();
        updatePosition();
	}
	
	private void createListeners(){
		exit.addActionListener(new ExitAction());
		viewReport.addActionListener(new ViewReportAction()); 
		weekButton.addChangeListener(new weekReportAction());
		dailyButton.addChangeListener(new dailyReportAction());
		mesButton.addChangeListener(new MesReportAction());
		listaButton.addChangeListener(new ListaReportAction());
		dteFecha.addFocusListener(new DteFechaFocusListener());
		dteFechaMesAnio.addFocusListener(new DteFechaMesAnioFocusListener());
	}
	
	private void loadAndInitComponents(){
		dteFecha.setEnabled(false);
		dteFechaMesAnio.setEnabled(false);
		//cmbLugares.setEnabled(false);
		
		//cmbLugares.loadItems();
	}
	
	public void updatePosition(){
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setLocation((screenSize.width - this.getWidth())/2,
				(screenSize.height - this.getHeight())/2);
		
	}
	
	private class weekReportAction implements ChangeListener {

		public void stateChanged(ChangeEvent arg0) {
			currentTypeReport = REPORT_TYPE_WEEK;
			if(weekButton.isSelected()){
				txtWeek.setEnabled(true);
				txtWeek.setBackground(Color.WHITE);
			}
			else{
				txtWeek.setEnabled(false);
				txtWeek.setBackground(getBackground());				
			}
		}
		
	}
	
	private class dailyReportAction implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			currentTypeReport = REPORT_TYPE_DAILY;
			if(dailyButton.isSelected()){
				dteFecha.setEnabled(true);
			}
			else{
				dteFecha.setEnabled(false);
			}			
		}
		
	}
	
	private class MesReportAction implements ChangeListener{
		
		public void stateChanged(ChangeEvent arg0) {
			currentTypeReport = REPORT_TYPE_MES;
			if(mesButton.isSelected()){
				dteFechaMesAnio.setEnabled(true);
				dteFechaMesAnio.setEnabledDay(false);
			}
			else{
				dteFechaMesAnio.setEnabled(false);
			}
		}
		
	}
	
	private class ListaReportAction implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			currentTypeReport = REPORT_TYPE_LISTA;
			if(listaButton.isSelected()){
				//cmbLugares.setEnabled(true);
			}
			else{
				//cmbLugares.setEnabled(false);
			}
		}
		
	}
	
	private class ViewReportAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (currentTypeReport == REPORT_TYPE_WEEK){
				ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_REPORT_TYPE);
				ProgressDialogUtil.launchProcessDialog(Main.getVentana());
				new Thread(new Runnable() {	
					public void run() {
						int nroWeek = Integer.parseInt(txtWeek.getText());
						Calendar cal = Calendar.getInstance();
						ReportBuilder.previewInfoSemanalReport(nroWeek,cal.get(Calendar.YEAR));
						dispose();
						ProgressDialogUtil.closeProcessDialog();
				}
				}).start(); 		
			}
			else if(currentTypeReport == REPORT_TYPE_DAILY){
				ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_REPORT_TYPE);
				ProgressDialogUtil.launchProcessDialog(Main.getVentana());
				new Thread(new Runnable() {	
					public void run() {
						int dia = Integer.parseInt(dteFecha.getDay());
						int mes = Integer.parseInt(dteFecha.getMonth());
						int anio = Integer.parseInt(dteFecha.getYear());
						ReportBuilder.previewDiaryPlannerReport(dia, mes, anio, "todas");
						dispose();
						ProgressDialogUtil.closeProcessDialog();
				}
				}).start(); 
			}
			else if(currentTypeReport == REPORT_TYPE_MES){
				ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_REPORT_TYPE);
				ProgressDialogUtil.launchProcessDialog(Main.getVentana());
				new Thread(new Runnable() {	
					public void run() {
						int mes = Integer.parseInt(dteFechaMesAnio.getMonth());
						int anio = Integer.parseInt(dteFechaMesAnio.getYear());
						ReportBuilder.previewInfoMensualReport(mes, anio);
						dispose();
						ProgressDialogUtil.closeProcessDialog();
				}
				}).start(); 
			}
			else if(currentTypeReport == REPORT_TYPE_LISTA){
				ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_REPORT_TYPE);
				ProgressDialogUtil.launchProcessDialog(Main.getVentana());
				new Thread(new Runnable() {	
					public void run() {
						Calendar c = Calendar.getInstance();
						c.setTime(new Date());
						//String codLugar = cmbLugares.searchForeign();
						//if(!codLugar.equals("0") || codLugar != null){
							ReportBuilder.previewListaPreciosReport(DateConverter.convertDateToString(new Date(), "yyyy-MM-dd"));
							//dispose();
						//}
						ProgressDialogUtil.closeProcessDialog();
				}
				}).start(); 
				
			}
		}
		
	}
	
	private class ExitAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
		
	}
	
	private class DteFechaFocusListener implements FocusListener{

		public void focusGained(FocusEvent arg0) {
			
		}

		public void focusLost(FocusEvent arg0) {
			listaButton.requestFocus();
		}
		
	}
	
	private class DteFechaMesAnioFocusListener implements FocusListener{

		public void focusGained(FocusEvent arg0) {
			
		}

		public void focusLost(FocusEvent arg0) {
			dailyButton.requestFocus();
		}
		
	}
}
