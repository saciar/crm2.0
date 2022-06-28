package crm.gui.abms.busquedas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.rmi.RemoteException;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.lang.StringUtils;

import crm.client.managers.PaisManager;
import crm.client.managers.PartidoManager;
import crm.client.managers.ProvinciaManager;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.ABMPaisesComboBox;
import crm.gui.components.ABMProvinciasComboBox;
import crm.gui.components.GradientButton;
import crm.gui.components.PanelImagen;
import crm.gui.tablerenderer.abms.BusquedaPartidosTableRender;
import crm.gui.tablerenderer.abms.BusquedaPartidosItem;
import crm.gui.tablerenderer.abms.BusquedaPartidosTableModel;
import crm.libraries.abm.entities.Partido;
import crm.libraries.abm.entities.Provincia;
import crm.libraries.util.MessageUtil;

public class ABMPartidosBusqueda extends JDialog{
	
	private javax.swing.ButtonGroup buttonGroup1;
    private GradientButton jButton1;
    private GradientButton jButton2;
    private GradientButton jButton3;
    private GradientButton jButton4;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private ABMProvinciasComboBox jComboBox2;
    private ABMPaisesComboBox jComboBox1;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private BusquedaPartidosTableRender jTable1;
    
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
	
    private String codEntidadElegido;
    
    public ABMPartidosBusqueda(Frame owner){
    	super(owner);
    	this.setTitle("Buscar Partidos");
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
        jMenuItem2.addActionListener(new SalirActionListener());
        jMenu1.add(jMenuItem2);
        
        jMenuBar1.add(jMenu1);
		
        this.setJMenuBar(jMenuBar1);   
    }
    
    public URL getUrlImagen(String imagen){
	    
    	URL url =Main.class.getResource("imagenes/"+imagen); 

    	return url;
    }
	
	public void initComponents() {
		
		PanelImagen panel = null;
		try{
			panel = new PanelImagen("http://200.80.201.51:8888/app_files/WorldLight.jpg", false);
		}
		catch(Exception e){
			System.out.println("Error");
			panel = new PanelImagen();
		}
		
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jTable1 = new BusquedaPartidosTableRender();
        jSeparator3 = new javax.swing.JSeparator();
        jButton2 = new GradientButton("", Color.blue);
        jButton3 = new GradientButton("", Color.blue);
        jButton4 = new GradientButton("", Color.blue);
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jComboBox2 = new ABMProvinciasComboBox();
        jComboBox1 = new ABMPaisesComboBox();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jButton1 = new GradientButton("", Color.blue);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setText("B\u00fasqueda de partidos");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Buscar por:");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Nombre de partido");
        jRadioButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jRadioButton1.setSelected(true);
        
        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("País y provincia");
        jRadioButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        
        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("C\u00f3digo de partido");
        jRadioButton3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton3.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setText("Resultados de la b\u00fasqueda");

        jButton2.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_form_edit.png")));
        jButton2.setText("Editar");

        jButton3.setText("Borrar");
        jButton3.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_form_delete.png")));

        jButton4.setText("Salir");
        jButton4.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));

        jButton1.setText("Buscar");
        jButton1.setIcon(new javax.swing.ImageIcon(getUrlImagen("magnifier.png")));

        jFormattedTextField1.setForeground(new java.awt.Color(153, 153, 153));
        jFormattedTextField1.setText("Ingrese al menos 3 caracteres para buscar ocurrencias");
        jFormattedTextField1.setFont(new java.awt.Font("SansSerif", 2, 12));        
        
        jFormattedTextField3.setForeground(new java.awt.Color(153, 153, 153));
        jFormattedTextField3.setEnabled(false);
        jFormattedTextField3.setFont(new java.awt.Font("SansSerif", 2, 12));
        
        jComboBox1.loadItems();
        jComboBox1.setEnabled(false);
        jComboBox2.setEnabled(false);
        
        createListeners();
        
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                                .add(jLabel1)
                                .add(layout.createSequentialGroup()
                                    .add(jLabel2)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(layout.createSequentialGroup()
                                            .add(jRadioButton2)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 242, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(16, 16, 16)
                                            .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 247, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .add(layout.createSequentialGroup()
                                            .add(jRadioButton1)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(jFormattedTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 467, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .add(layout.createSequentialGroup()
                                            .add(jRadioButton3)
                                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                            .add(jFormattedTextField3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)))
                                    .add(29, 29, 29)
                                    .add(jButton1)
                                    .add(48, 48, 48))
                                .add(jSeparator3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                                .add(jTable1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)))
                        .add(layout.createSequentialGroup()
                            .add(328, 328, 328)
                            .add(jButton2)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jButton3)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jButton4))
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE))
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jLabel3)))
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
                        .add(jRadioButton1)
                        .add(jFormattedTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jRadioButton2)
                        .add(jButton1)
                        .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jRadioButton3)
                        .add(jFormattedTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jLabel3)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jTable1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 264, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jButton2)
                        .add(jButton3)
                        .add(jButton4))
                    .addContainerGap(12, Short.MAX_VALUE))
            );
        
        this.getContentPane().add(panel);
        this.pack();
        updatePosition();
    }
	
	public void updatePosition(){
    	
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	
    	this.setLocation((screenSize.width - this.getWidth())/2,
    			(screenSize.height - this.getHeight())/2);
    	
    }
	
	 /**
	 * @return Returns the codClienteElegido.
	 */
	public String getCodEntidadElegido() {
		return codEntidadElegido;
	}
	
	private void createListeners(){
    	jButton1.addActionListener(new BuscarActionListener());
    	jButton2.addActionListener(new EditarActionListener());
    	jButton3.addActionListener(new BorrarActionListener());
    	jButton4.addActionListener(new SalirActionListener());
    	
    	jRadioButton1.addChangeListener(new NombreChangeListener());
    	jRadioButton2.addChangeListener(new CombosChangeListener());
    	jRadioButton3.addChangeListener(new CodigoChangeListener());
    	
    	jFormattedTextField1.addKeyListener(new NombreKeyListener());
    	jFormattedTextField1.addMouseListener(new FantasiaMouseListener());
    	jFormattedTextField3.addKeyListener(new CodigoKeyListener());
    	jFormattedTextField3.addMouseListener(new RazonMouseListener());
    	
    	jComboBox1.addActionListener(new PaisActionListener());
    }
    
    private void setNombreText(){
    	jFormattedTextField1.setEnabled(true);
    	jFormattedTextField1.setForeground(new java.awt.Color(153, 153, 153));
        jFormattedTextField1.setText("Ingrese al menos 3 caracteres para buscar coincidencias");
        jFormattedTextField1.setFont(new java.awt.Font("SansSerif", 2, 12));       
    }
    
    private void setCombos(){
    	jComboBox1.setEnabled(true);    	
    }
    
    private void setCodigoText(){
    	jFormattedTextField3.setEnabled(true);
    	jFormattedTextField3.setForeground(new java.awt.Color(153, 153, 153));
        jFormattedTextField3.setText("Ingrese al menos 3 caracteres para buscar coincidencias");
        jFormattedTextField3.setFont(new java.awt.Font("SansSerif", 2, 12));
    }
	
//*********************************ACCIONES*******************************************/
    
    private class SalirActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (MessageUtil.showYesNoMessage(ABMPartidosBusqueda.this, "¿Desea salir del formulario de busqueda?", "Salir")){
				codEntidadElegido = null;
				setVisible(false);
			}
		}
		
	}
    
    private class EditarActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			BusquedaPartidosItem it = jTable1.getSelectedItem();
			if(it != null){
				codEntidadElegido = it.getIdPartido();
				setVisible(false);
			}
			else Util.errMsg(Main.getVentana(),"Seleccione un item de la grilla para editar.",null);
		}
    	
    }
    
    private class BorrarActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			BusquedaPartidosItem it = jTable1.getSelectedItem();
			if(it != null){
				try {
					if (MessageUtil.showYesNoMessage(ABMPartidosBusqueda.this, "¿Seguro que desea borrar el partido seleccionado?", "Borrar")){
						PartidoManager.instance().remove(it.getCodigo());
						((BusquedaPartidosTableModel)jTable1.getTable().getModel()).removeRow(it);
						jTable1.refreshTable();	
					}
				} catch (RemoteException e) {
					Util.errMsg(Main.getVentana(), "Error al borrar el partido",e);
				}				
			}
					
		}
    	
    }
    
    private class BuscarActionListener implements ActionListener{

    	public void actionPerformed(ActionEvent arg0) {
    		try{
    			Partido[] partidos = null;
    			if(jRadioButton1.isSelected() && !StringUtils.isBlank(jFormattedTextField1.getText())){
    				partidos  = PartidoManager.instance().findByField("descripcion",jFormattedTextField1.getText());
    			}
    			else if(jRadioButton3.isSelected() && !StringUtils.isBlank(jFormattedTextField3.getText())){
    				Partido part = PartidoManager.instance().getPartidoByCodPartido(jFormattedTextField3.getText());
    				if(part != null){
    					partidos = new Partido[1];
    					partidos[0] = part; 
    				}
    			}
    			else if(jRadioButton2.isSelected() && !StringUtils.isBlank(jComboBox2.searchForeign()) && !StringUtils.isBlank(jComboBox1.searchForeign())){ 
    				partidos  = PartidoManager.instance().findByProvinciaId(jComboBox2.searchForeign());    				
    			}
    			else if(jRadioButton1.isSelected() && StringUtils.isBlank(jFormattedTextField1.getText())){
    				Util.errMsg(Main.getVentana(),"Ingrese un nombre del partido a buscar",null);
    			}
    			else if(jRadioButton3.isSelected() && StringUtils.isBlank(jFormattedTextField3.getText())){
    				Util.errMsg(Main.getVentana(),"Ingrese un codigo del partido a buscar",null);
    			}
    			else if(jRadioButton2.isSelected() && (StringUtils.isBlank(jComboBox2.searchForeign()) || StringUtils.isBlank(jComboBox1.searchForeign()))){ 
    				Util.errMsg(Main.getVentana(),"Seleccione un país y un provincia para buscar sus partidos",null);   				
    			}
    			
    			if(partidos != null && partidos.length >0){
    				BusquedaPartidosTableModel model = new BusquedaPartidosTableModel();
    				
    				for(int i=0; i<partidos.length;i++){
    					BusquedaPartidosItem item = new BusquedaPartidosItem();
    					item.setCodigo(partidos[i].getCodigo());
    					item.setIdPartido(partidos[i].getCodigoPartido());
    					item.setNombrePartido(partidos[i].getDescripcion());
    					Provincia prov = ProvinciaManager.instance().getProvinciaByCodProvincia(partidos[i].getCodigoProvincia());
    					item.setNombreProvincia(prov.getDescripcion());
    					item.setNombrePais(PaisManager.instance().getNombrePaisById(prov.getCodigoPais()));    					
    					
    					model.addRow(item);
    					
    				}
    				
    				if(model.getRowCount() > 0){
    					jTable1.getTable().setModel(model);
    					jTable1.refreshTable();						
					}
    				
    			}
    			else{
    				Util.alertMsg(Main.getVentana(), "No se encontraron datos con esta busqueda.");
    				((BusquedaPartidosTableModel)jTable1.getTable().getModel()).clear();
    				jTable1.refreshTable();	
    			}
    			
    		} catch (Exception e) {
    			Util.errMsg(Main.getVentana(), "Error al buscar la provincia",e);
    		}
    	}
    	
    }
    
    private class NombreKeyListener extends KeyAdapter{

		public void keyReleased(KeyEvent arg0) {
			if(jFormattedTextField1.getText().length() >0){				
				jFormattedTextField1.setForeground(new java.awt.Color(0, 0, 0));		        
		        jFormattedTextField1.setFont(new java.awt.Font("SansSerif", 0, 12)); 
			}
			
		}
    	
    }
    
    private class CodigoKeyListener extends KeyAdapter{

		public void keyReleased(KeyEvent arg0) {
			if(jFormattedTextField3.getText().length() >0){
				jFormattedTextField3.setForeground(new java.awt.Color(0, 0, 0));		        
		        jFormattedTextField3.setFont(new java.awt.Font("SansSerif", 0, 12)); 
			}
			
		}
    	
    }
    
    private class NombreChangeListener implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(jRadioButton1.isSelected()){				
				setNombreText();	
				jComboBox2.setEnabled(false);
				jComboBox2.setForeign("0");
				jComboBox1.setEnabled(false);
				jComboBox1.setForeign("0");
				jFormattedTextField3.setEnabled(false);
				jFormattedTextField3.setText("");
			}
		}
    	
    }
    
    private class CombosChangeListener implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(jRadioButton2.isSelected()){				
				setCombos();
				jFormattedTextField1.setEnabled(false);
				jFormattedTextField1.setText("");
				jFormattedTextField3.setEnabled(false);
				jFormattedTextField3.setText("");
			}
		}
    	
    }
    
    private class CodigoChangeListener implements ChangeListener{

		public void stateChanged(ChangeEvent arg0) {
			if(jRadioButton3.isSelected()){				
				setCodigoText();				
				jComboBox2.setEnabled(false);
				jComboBox2.setForeign("0");
				jComboBox1.setEnabled(false);
				jComboBox1.setForeign("0");
				jFormattedTextField1.setEnabled(false);
				jFormattedTextField1.setText("");
			}
		}
    	
    }
    
    private class FantasiaMouseListener extends MouseAdapter{

		public void mouseClicked(MouseEvent arg0) {
			jFormattedTextField1.selectAll();			
		}
    	
    }
    
    private class RazonMouseListener extends MouseAdapter{

		public void mouseClicked(MouseEvent arg0) {
			jFormattedTextField3.selectAll();			
		}
    	
    } 
    
   /* private class PaisActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			if(!StringUtils.isBlank(jComboBox1.searchForeign()))	{
				jComboBox2.setEnabled(true);
				jComboBox2.loadItems(jComboBox1.searchForeign());
			}
			else{
				jComboBox2.setEnabled(false);
				jComboBox2.setForeign("0");
			}
		}
    	
    }*/
    private String paisId;
    private class PaisActionListener implements ActionListener{
    	public void actionPerformed(ActionEvent arg0) {				
			if(jComboBox1.getSelectedIndex() > 0){		
				String newId = jComboBox1.searchForeign();
				if(paisId == null || !paisId.equals(newId)){
					jComboBox2.loadItems(newId);		
				}
				paisId = newId;
				jComboBox2.setEnabled(true);
			} else jComboBox2.setEnabled(false);
		}
    }
    
    private class RefreshAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			jComboBox1.loadItems();			
		}
    	
    }

}
