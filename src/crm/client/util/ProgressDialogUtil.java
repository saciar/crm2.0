package crm.client.util;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.wsif.WSIFException;

import crm.client.managers.PresupuestosManager;
import crm.gui.Main;
import crm.libraries.util.MessageUtil;

public class ProgressDialogUtil extends Observable{	 
	
	public static final int NEW_PPTO_TYPE = 0;
	public static final int LOGIN_TYPE = 1;
	public static final int LOAD_REPORT_TYPE = 2;
	public static final int SEND_REPORT_TYPE = 3;
	public static final int SEARCH_PPTO_TYPE = 4;
	public static final int SEND_PASS_TYPE = 5;
	public static final int LOAD_PPTO_TYPE = 6;
	public static final int LOAD_PPTO_ABIERTOS_TYPE = 7;
	public static final int CLOSE_PPTO_TYPE = 8;
	public static final int LOAD_SERVICE_WINDOW = 9;
	public static final int LOAD_NEW_ENTITY = 10;
	public static final int LOAD_DATA = 11;
	
	private static final String URL_ENGRANAJE = "http://200.80.201.51:8888/index_files/engranes5.gif";
	
	private static JDialog processDialog;
	private static String title;
	private static String imageURL;
	private static int imageTab;
	private static javax.swing.JLabel jLabel1;
    private static javax.swing.JLabel jLabel2;
    private static javax.swing.JSeparator jSeparator1;
    private static javax.swing.JSeparator jSeparator2;
    private static javax.swing.JButton jButton1;
    private static boolean isVisible;
    
    /**
	 * @return Returns the processDialog.
	 */
	public static JDialog getProcessDialog() {
		return processDialog;
	}

	public static void setType(int t){	
		switch(t){
		case 0:
			title = "Creando formulario nuevo, por favor espere...";
			imageURL = "http://200.80.201.51:8888/app_files/ENGRANAJES2.gif";
			imageTab = 124;
			break;
		case 1:
			title = "Verificando datos, por favor espere...";
			imageURL = "http://200.80.201.51:8888/app_files/llave.gif";
			imageTab = 105;
			break;
		case 2:
			title = "Armando reporte, por favor espere...";
			imageURL = "http://200.80.201.51:8888/app_files/reporte.gif";
			imageTab = 105;
			break;
		case 3:
			title = "Enviando reporte vía mail, por favor espere...";
			imageURL = "http://200.80.201.51:8888/app_files/mail.gif";
			imageTab = 124;
			break;
		case 4:
			title = "Buscando presupuesto, por favor espere...";
			imageURL = "http://200.80.201.51:8888/app_files/lupa.gif";
			imageTab = 124;
			break;
		case 5:
			title = "Enviando clave vía mail, por favor espere...";
			imageURL = "http://200.80.201.51:8888/app_files/mail.gif";
			imageTab = 124;
			break;
		case 6:
			title = "Cargando presupuesto, por favor espere...";
			imageURL = "http://200.80.201.51:8888/app_files/ENGRANAJES2.gif";
			imageTab = 112;
			break;
		case 7:
			title = "Buscando presupuestos abiertos, por favor espere...";
			imageURL = "http://200.80.201.51:8888/app_files/lupa.gif";
			imageTab = 157;
			break;
		case 8:
			title = "Cerrando presupuesto, por favor espere...";
			imageURL = "http://200.80.201.51:8888/app_files/cerrando.gif";
			imageTab = 122;
			break;
		case 9:
			title = "Creando servicio nuevo, por favor espere...";
			imageURL = "http://200.80.201.51:8888/app_files/ENGRANAJES2.gif";
			imageTab = 122;
			break;		
		case 10:
			title = "Creando formulario, por favor espere...";
			imageURL = "http://200.80.201.51:8888/app_files/ENGRANAJES2.gif";
			imageTab = 112;
			break;
		case 11:
			title = "Cargando datos, por favor espere...";
			imageURL = "http://200.80.201.51:8888/app_files/ENGRANAJES2.gif";
			imageTab = 112;
			break;
		}
	
	}
	
	public static void setMensaje(String msg){
		jLabel1.setText(msg);
	}
	
	public static void setCancelVisible(boolean b){
		jButton1.setVisible(true);	
	}
	
	private static void initComponents() {
		JPanel middlePanel = new JPanel();

		jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText(title);

        /*jLabel2.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(new java.net.URL(imageURL));
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());*/
        
        jLabel2.setText("Procesando....");
        
        jButton1.setText("Cancelar");

        jButton1.setVisible(false);
        
        jButton1.addActionListener(new CancelarActionListener());
        
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(middlePanel);
        middlePanel.setLayout(layout);
        JLabel jLabel3 = new javax.swing.JLabel("CRM");
        middlePanel.add(jLabel3);
        processDialog.getContentPane().add(middlePanel);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jLabel1))
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
                        .add(layout.createSequentialGroup()
                            .add(123, 123, 123)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jButton1)
                                .add(jLabel2))))
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .add(jLabel1)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 87, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jButton1)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            );
        processDialog.pack();
    }
	
	public static void launchProcessDialog(Frame frame){
    	final Frame owner = frame;
    	
    	new Thread(new Runnable() {		
    		
			public void run() {
				processDialog = new JDialog(owner);
		    	processDialog.setTitle("CRM Congress Rental");
		    	processDialog.setModal(true);
		    	processDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		    	initComponents();
		    	
		    	Util.center(owner,processDialog);
		    	processDialog.setVisible(true);	  				
			}		
		}).start();

    }
    
    public static void closeProcessDialog(){
    	new Thread(new Runnable() {		
			public void run() {
				boolean closed = false;				
				while(!closed){
					if(processDialog.isVisible()){
						processDialog.dispose();
						closed = true;						
					}
				}
			}
    	}).start();
    }
    
    public void modificarObservable(){
    	setChanged();
	    notifyObservers();
    }
    
    private static ProgressDialogUtil instance;
    
    public static ProgressDialogUtil instance() {
			if (instance == null) {
				instance = new ProgressDialogUtil();
			}
		return instance;
	}
    
    private static class CancelarActionListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			if (MessageUtil.showYesNoMessage(Main.getVentana(),"¿Seguro que desea cancelar la operación?","Cancelar")){
				if (instance != null) {
					instance.modificarObservable();
				}
				closeProcessDialog();
			}
		}
    	
    }
}
