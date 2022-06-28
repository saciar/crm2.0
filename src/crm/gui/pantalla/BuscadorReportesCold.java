package crm.gui.pantalla;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.lang.StringUtils;

import crm.client.managers.PresupuestosManager;
import crm.client.report.ReportBuilder;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.ABMLugarEvtComboBox;
import crm.gui.components.CustomTextField;
import crm.gui.components.DatePanel;
import crm.gui.components.GradientButton;
import crm.gui.components.JXDatePicker;
import crm.gui.components.PanelImagen;

public class BuscadorReportesCold extends PantallaEmergente{
    
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton diaryButton;
    private javax.swing.JButton exit;
    private JXDatePicker fecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField nroOrden;
    private javax.swing.JRadioButton osButton;
    private javax.swing.JButton viewReport;
    
	public BuscadorReportesCold (Frame owner){
		super("Buscador de reportes",owner);
		this.setModal(false);
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setEnabled(true);
    }
	
	public void init(){
		//**************Crecion de panel contenedor con imagen de fondo**********************
		PanelImagen panel = null;
		try{
			panel = new PanelImagen("WorldLight.jpg");
		}
		catch(Exception e){
			System.out.println("Error");
			panel = new PanelImagen();
		}
		
		//**************Crecion de componentes de pantalla***********************************                
        jRadioButton1 = new javax.swing.JRadioButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        exit = new GradientButton("",Color.blue);
        viewReport = new GradientButton("",Color.blue);
        fecha = new JXDatePicker();
        diaryButton = new javax.swing.JRadioButton();
        osButton = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        nroOrden = CustomTextField.getBigIntInstance();//new javax.swing.JTextField();
        
        //**************Seteos de los componentes********************************************
        jRadioButton1.setText("jRadioButton1");
        jRadioButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Reportes");

        viewReport.setText("Ver reporte");
        viewReport.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));
        viewReport.setMnemonic('v');
        viewReport.setToolTipText("Click para ver el reporte");        
              
        exit.setText("Salir");
        exit.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
        exit.setMnemonic('s');
        exit.setToolTipText("Click para salir");

        buttonGroup1.add(diaryButton);
        diaryButton.setText("Diary Planner");
        diaryButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        diaryButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        diaryButton.setSelected(true);

        buttonGroup1.add(osButton);
        osButton.setText("Orden de servicio");
        osButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        osButton.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabel2.setText("Indique Nro de orden");
        
        nroOrden.setEnabled(false);
        
        //**************Crecion de layout GroupLayout de Netbeans IDE 5.0 con los componentes
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                            .addContainerGap())
                        .add(layout.createSequentialGroup()
                            .add(jLabel1)
                            .addContainerGap(255, Short.MAX_VALUE))
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 57, Short.MAX_VALUE)
                            .add(viewReport)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(exit)
                            .add(71, 71, 71))))
                .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                    .add(53, 53, 53)
                    .add(fecha, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(28, 28, 28))
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(diaryButton)
                    .addContainerGap(237, Short.MAX_VALUE))
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(osButton)
                    .addContainerGap(217, Short.MAX_VALUE))
                .add(layout.createSequentialGroup()
                    .add(40, 40, 40)
                    .add(jLabel2)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(nroOrden, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 123, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(59, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jLabel1)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(diaryButton)
                    .add(15, 15, 15)
                    .add(fecha, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(osButton)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel2)
                        .add(nroOrden, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(viewReport)
                        .add(exit))
                    .addContainerGap())
            );
        
        this.getContentPane().add(panel);
        this.pack();
        
        JDialog.setDefaultLookAndFeelDecorated(true);
	    createListeners();
        updatePosition();
	}
	
	private void createListeners(){
		exit.addActionListener(new ExitAction());
		viewReport.addActionListener(new ViewReportAction()); 
		osButton.addChangeListener(new OSChangeListener());
	}
	
	public void updatePosition(){
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setLocation((screenSize.width - this.getWidth())/2,
				(screenSize.height - this.getHeight())/2);
		
	}
	
	//**********************************ACCIONES******************************************
	
	private class ViewReportAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_REPORT_TYPE);
			ProgressDialogUtil.launchProcessDialog(Main.getVentana());
			new Thread(new Runnable() {	
				public void run() {
					if(diaryButton.isSelected()){
						Calendar c = new GregorianCalendar();
						c.setTime(fecha.getDate());							
						int dia = c.get(Calendar.DATE);
						int mes = c.get(Calendar.MONTH)+1;
						int anio = c.get(Calendar.YEAR);
						ReportBuilder.previewDiaryPlannerReport(dia, mes, anio, "todas");
						//dispose();
						ProgressDialogUtil.closeProcessDialog();
					}
					else if(osButton.isSelected() && !StringUtils.isBlank(nroOrden.getText())){						
						try {
							boolean isOS = false;
							Object[] estado = PresupuestosManager.instance().buscarEstadoActual(Long.parseLong(nroOrden.getText()));
							
							for(int j=0; j<estado.length; j++){				
								Object[] estados = (Object[])estado[j];
								if((Integer)estados[5] == 1)
									isOS =true;
							}
							if(isOS){
								ReportBuilder.previewOSReport(Long.parseLong(nroOrden.getText()));
								//dispose();
								ProgressDialogUtil.closeProcessDialog();
							}
							else{
								Util.errMsg(Main.getVentana(), "La orden solicitada no tiene orden de servicio seleccionado.", null);
								ProgressDialogUtil.closeProcessDialog();
							}
						} catch (NumberFormatException e) {
							Util.errMsg(Main.getVentana(), "Error, la orden debe ser un numero v�lido.", e);
							ProgressDialogUtil.closeProcessDialog();
						} catch (RemoteException e) {
							Util.errMsg(Main.getVentana(), "Error al cargar datos externos.", e);
							ProgressDialogUtil.closeProcessDialog();
						}
					}					
			}
			}).start(); 
		}
	}
	
	private class ExitAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
		
	}
	
	private class OSChangeListener implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(osButton.isSelected()){
				nroOrden.setEnabled(true);
				fecha.setEnabled(false);
			}
			else{
				nroOrden.setEnabled(false);
				fecha.setEnabled(true);
			}
				
		}
		
	}
}
