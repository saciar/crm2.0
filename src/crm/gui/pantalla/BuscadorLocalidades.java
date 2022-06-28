package crm.gui.pantalla;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.BoxLayout;
import javax.swing.JDialog;

import org.apache.commons.lang.StringUtils;

import crm.client.managers.LocalidadManager;
import crm.client.managers.PaisManager;
import crm.client.managers.PartidoManager;
import crm.client.managers.ProvinciaManager;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.PanelImagen;
import crm.gui.tablerenderer.localidades.BuscadorLocalidadesItem;
import crm.gui.tablerenderer.localidades.BuscadorLocalidadesTableModel;
import crm.gui.tablerenderer.localidades.BuscadorLocalidadesTableRender;
import crm.libraries.abm.entities.Localidad;
import crm.libraries.abm.entities.Pais;
import crm.libraries.abm.entities.Partido;
import crm.libraries.abm.entities.Provincia;
import crm.libraries.util.MessageUtil;

public class BuscadorLocalidades extends PantallaEmergente{
	
	private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private BuscadorLocalidadesTableRender jTable1;
    private javax.swing.JTextField jTextField1;
	
	private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    
    private BuscadorLocalidadesItem itemSeleccionado;
	
	public BuscadorLocalidades (Frame owner){
		
		super("Buscador de localidades", owner);
		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setModal(true);      
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.setEnabled(true);
        createMenuBar();
	}
    
    private void createMenuBar(){
    	jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
		
        jMenuBar1.setSize(this.getWidth(),this.getHeight());
		jMenu1.setMnemonic('A');
        jMenu1.setText("Archivo");
        jMenu1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        
        jMenuItem1.setMnemonic('R');
        jMenuItem1.setText("Refrescar esta pantalla");
        jMenuItem1.addActionListener(new RefreshAction());
        jMenu1.add(jMenuItem1);
        jMenuItem2.setMnemonic('C');
        jMenuItem2.setText("Cerrar");
        jMenuItem2.addActionListener(new SalirAction());
        jMenu1.add(jMenuItem2);
        
        jMenuBar1.add(jMenu1);
		
        this.setJMenuBar(jMenuBar1);   
    }
	
	public void initComponents() {
		
		PanelImagen panel = null;
		try{
			panel = new PanelImagen("WorldLight.jpg");
		}
		catch(Exception e){
			System.out.println("Error");
			panel = new PanelImagen();
		}
		
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel38 = new javax.swing.JLabel();
        jTable1 = new BuscadorLocalidadesTableRender();
        jLabel39 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("B\u00fasqueda avanzada de localidades");

        jLabel2.setText("Ingrese el nombre de la localidad a buscar");

        jLabel3.setText("*");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel38.setText("Resultados de la busqueda");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel39.setText("* Nota: para realizar la busqueda es necesario ingresar por lo menos las 3 primeras letras correctamente.");

        jButton1.setText("Seleccionar");
        jButton1.setIcon(new javax.swing.ImageIcon(getUrlImagen("tick.png")));

        jButton2.setText("Salir");
        jButton2.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
        
        jButton3.setText("Buscar");
        jButton3.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                            .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                            .add(jLabel1)
                            .add(layout.createSequentialGroup()
                                .add(jLabel2)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 474, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel3)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jButton3))
                            .add(jLabel38)
                            .add(jTable1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                            .add(jLabel39)))
                    .add(layout.createSequentialGroup()
                        .add(309, 309, 309)
                        .add(jButton1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jLabel3)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel38)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTable1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 220, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel39)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton2)
                    .add(jButton1))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        this.getContentPane().add(panel);
        this.pack();
        
        createListener();        
        JDialog.setDefaultLookAndFeelDecorated(true);
        updatePosition();
    }
	
	public void updatePosition(){
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setLocation((screenSize.width - this.getWidth())/2,
				(screenSize.height - this.getHeight())/2);
		
	}
	
	private void createListener(){
		jButton1.addActionListener(new SelectActionListener());
		jButton2.addActionListener(new SalirAction());
		jButton3.addActionListener(new BuscarAction());
	}
	
	private void buscarLocalidad(){
		if(jTextField1.getText().length() >=3){
			//ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_DATA);
			//ProgressDialogUtil.launchProcessDialog(Main.getVentana());
			//new Thread(new Runnable() {	
				//public void run() {	
					try {						
						BuscadorLocalidadesTableModel model = new BuscadorLocalidadesTableModel(); 
						Localidad[] l = LocalidadManager.instance().findByField("descripcion",jTextField1.getText());
						for (int i=0; i<l.length; i++){
							BuscadorLocalidadesItem item = new BuscadorLocalidadesItem(); 
							item.setLocalidad(l[i].getDescripcion());
							item.setIdLocalidad(l[i].getCodigoLocalidad());
							Partido part = PartidoManager.instance().getPartidoByCodPartido(l[i].getCodigoPartido());
							item.setPartido(part.getDescripcion());
							item.setIdPartido(part.getCodigoPartido());
							Provincia prov = ProvinciaManager.instance().getProvinciaByCodProvincia(part.getCodigoProvincia());
							item.setProvincia(prov.getDescripcion());
							item.setIdProvincia(prov.getCodigoProvincia());
							item.setPais(PaisManager.instance().getNombrePaisById(prov.getCodigoPais()));
							item.setIdPais(prov.getCodigoPais());
							model.addRow(item);
						}
						if(model.getRowCount() > 0){
							jTable1.getTable().setModel(model);
							jTable1.refreshTable();
						}
						///ProgressDialogUtil.closeProcessDialog();
						
					} catch (RemoteException e) {
						Util.errMsg(BuscadorLocalidades.this,"Error al cargar datos externos.",e);
						//ProgressDialogUtil.closeProcessDialog();
					}
				//}
			//}).start(); 
			
		}
		else
			Util.errMsg(this,"Como mínimo debe ingresar 3 carácteres.",null);
	}
	
	/**
	 * @return Returns the itemSeleccionado.
	 */
	public BuscadorLocalidadesItem getItemSeleccionado() {
		return itemSeleccionado;
	}

	/**
	 * @param itemSeleccionado The itemSeleccionado to set.
	 */
	public void setItemSeleccionado(BuscadorLocalidadesItem itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}
	
	//*****************************ACCIONES**********************************************
	
	private class SalirAction implements ActionListener {
		public void actionPerformed (ActionEvent evt) {
			if (MessageUtil.showYesNoMessage(BuscadorLocalidades.this, "¿Desea salir sin grabar el cliente?", "Salir")){
				setVisible(false);				
			}
		}
	}
	
	private class RefreshAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
						
		}
	} 
	
	private class BuscarAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			buscarLocalidad();			
		}
	} 
	
	private class SelectActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {			
			itemSeleccionado = jTable1.getSelectedItem();
			setVisible(false);
		}
		
	}
    
}
