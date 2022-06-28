package crm.gui.pantalla.solapa;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import org.apache.commons.lang.StringUtils;

import crm.client.managers.ProveedorManager;
import crm.client.validacion.ErrorList;
import crm.gui.Main;
import crm.gui.components.ABMProveedoresComboBox;
import crm.gui.components.CustomTextField;
import crm.gui.tablerenderer.gastos.GastosSubContratacionesSalasItem;
import crm.gui.tablerenderer.gastosSubcontratados.SubcontratadosTableRender;
import crm.gui.tablerenderer.gastosSubcontratados.SubcontratadosTableModel;
import crm.gui.tablerenderer.salas.SalaServicioItem;
import crm.gui.tablerenderer.salas.SalaServiciosTableModel;
import crm.libraries.abm.entities.Ppto_GastoSC;
import crm.libraries.abm.entities.Ppto_Sala_Servicio;
import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.abm.helper.GastoContratHelper;
import crm.libraries.util.MessageUtil;

public class SubcontratadosPanel extends PanelGeneral{
	private javax.swing.JButton save;
    private javax.swing.JFormattedTextField costo;
    private javax.swing.JLabel detalle;
    private javax.swing.JButton edit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel name;
    private javax.swing.JLabel neto;
    private javax.swing.JLabel precio;
    private ABMProveedoresComboBox proveedores;
    private SubcontratadosTableRender subContratacionesGeneral;
    
    private JPanel panel;
	private GastosSubContratacionesSalasItem item;
	private boolean editMode;
    private MainPanelComercial com;
	
	public SubcontratadosPanel(JPanel pan){
		panel=pan;
	}
	
	public void setMainPanel(MainPanelComercial p){
		com = p;
	}
	
    public void init(){
    	
    	name = new javax.swing.JLabel();    	
        subContratacionesGeneral = new SubcontratadosTableRender();        
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();        
        proveedores = new ABMProveedoresComboBox();	        
        jLabel2 = new javax.swing.JLabel();
        detalle = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        precio = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        costo = CustomTextField.getDecimalInstance(8,2);
        save = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        neto = new javax.swing.JLabel();

        name.setFont(new java.awt.Font("Tahoma", 1, 11));
        name.setText("Subcontrataciones generales");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Nombre del servicio");
        
        new Thread(){
        	public void run(){
        		proveedores.loadItems();
        		proveedores.setEnabled(false);
        	}
        }.start();
        

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Proveedor");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Precio");
        
        precio.setText("0.0");
        precio.setEnabled(false);
        
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Costo");
        
        costo.setText("0.0");
        costo.setEnabled(false);
        
        save.setIcon(new javax.swing.ImageIcon(getUrlImagen("tick.png")));
        save.setText("Listo");
        
        edit.setIcon(new javax.swing.ImageIcon(getUrlImagen("application_edit.png")));
        edit.setText("Editar");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("Neto");
        
        createListeners();
        
    }
    
    public void initLayout(){
    	org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, subContratacionesGeneral, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, name)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(save)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(edit))
                      //  .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                       // .add(remove))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel1)
                            .add(jLabel3)
                            .add(jLabel2)
                            .add(jLabel4))
                        .add(4, 4, 4)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(proveedores, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 243, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(detalle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 557, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, precio)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, costo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                                .add(160, 160, 160)
                                .add(jLabel5)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(neto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 138, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 141, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(name)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(detalle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(proveedores, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(precio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(costo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel5)
                    .add(neto))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(save)
                    .add(edit))
                    //.add(remove))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(subContratacionesGeneral, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                .addContainerGap())
        );
    }
    
    private void createListeners(){
		this.costo.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				calcularNeto();
			}		
		});
		this.precio.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				calcularNeto();
			}		
		});
		
		save.addActionListener(new AddActionListener());		
		edit.addActionListener(new EditActionListener());		
		name.addMouseListener(new RemoveActionListener());
    }
    
    private void calcularNeto(){    	
		double costo = 0.0;
		double precio = 0.0;
		double neto = 0.0;
		
		if(!StringUtils.isBlank(this.costo.getText()))
			costo = Double.parseDouble(this.costo.getText());
		if(!StringUtils.isBlank(this.precio.getText()))
			precio = Double.parseDouble(this.precio.getText());
		
		neto = (precio - costo);
		this.neto.setText(Double.toString(neto));
	}
    
    private void resetFields(){
		this.detalle.setText(this.item.getServicio());
		this.costo.setText(Double.toString(this.item.getCosto()));
		this.precio.setText(Double.toString(this.item.getPrecio()));
		this.proveedores.setForeign(this.item.getProveedorId());
		this.neto.setText(Double.toString(this.item.getNeto()));
	}		
	
	private void makeItem(){
		this.item.setServicio(this.detalle.getText());
		this.item.setCosto(Double.parseDouble(this.costo.getText()));
		this.item.setPrecio(Double.parseDouble(this.precio.getText()));
		this.item.setProveedor(this.proveedores.getSelectedItem().toString());
		this.item.setProveedorId(this.proveedores.searchForeign());	
		//try{
			this.item.setNeto(Double.parseDouble(this.neto.getText()));	
		//}catch(NumberFormatException e){
		//	this.item.setNeto(0.0);	
		//}
		
	}	
	
	private void clearItem(){		
		this.item = new GastosSubContratacionesSalasItem();
		setEditMode(false);
	}
    
	private boolean isValid(){
		String proveedor = this.proveedores.searchForeign();
		String detalle = this.detalle.getText();
		double costo = Double.parseDouble(this.costo.getText());
		double precio = Double.parseDouble(this.precio.getText());
		return ((!StringUtils.isEmpty(proveedor)) &&
				(!StringUtils.isEmpty(detalle)) &&
				(precio > 0.0) &&
				(costo > 0.0));
	}
	
	public void save(){
		//makeItem();					
		if(!isEditMode()){		
			((SubcontratadosTableModel)this.subContratacionesGeneral.getTable().getModel()).addRow(this.item);
		}
		this.subContratacionesGeneral.getTable().updateUI();
		clearItem();
		resetFields();
	}
	
	public GastosSubContratacionesSalasItem getItem() {
		return item;
	}

	public void setItem(GastosSubContratacionesSalasItem item) {
		this.item = item;
		//this.neto.setText(Double.toString(item.getPrecio() - item.getCosto()));
	}

	public boolean isEditMode() {		
		return editMode;		
	}

	public void setEditMode(boolean editMode) {
		costo.setEnabled(editMode);
		proveedores.setEnabled(editMode);
		precio.setEnabled(editMode);
		neto.setEnabled(editMode);
		this.editMode = editMode;
	}
	
	public List getRowGenerales(){
		return ((SubcontratadosTableModel)subContratacionesGeneral.getTable().getModel()).getRows();
	}
	
	public void removeItem(){
		((SubcontratadosTableModel)subContratacionesGeneral.getTable().getModel()).removeRow(item);
		//subContratacionesGeneral.removeSelection();
		clearItem();
		resetFields();
	}
	
	public GastosSubContratacionesSalasItem getItem(String name, String familia, String sala){
		List itemsGenerales = ((SubcontratadosTableModel)subContratacionesGeneral.getTable().getModel()).getRows();
		GastosSubContratacionesSalasItem result = null;
		for(int i = 0;i< itemsGenerales.size();i++){
			GastosSubContratacionesSalasItem item = (GastosSubContratacionesSalasItem)itemsGenerales.get(i);
			if(item.getServicio().equals(familia+" - "+name) && (sala.equals(item.getSala()) || item.getSala()==null)){
				result = item;
				break;
			}				
		}
		return result;
	}
	
	public ErrorList validateRequiredFields() {

		ErrorList errors = new ErrorList();

		/*if (StringUtils.isBlank(getNombreDelEvento())){
			errors.addError("Ingrese el nombre del evento.");
			eventName.setForeground(Color.RED);
		}*/
		List itemsGenerales = ((SubcontratadosTableModel)subContratacionesGeneral.getTable().getModel()).getRows();	
		for(int i=0;i<itemsGenerales.size();i++){
			GastosSubContratacionesSalasItem item=(GastosSubContratacionesSalasItem)itemsGenerales.get(i);
			if(StringUtils.isBlank(item.getProveedorId())){
				errors.addError("Seleccione proveedores para el gasto subcontratado "+ item.getServicio());
				break;
			}
			if(item.getCosto() == 0.0){
				errors.addError("Indique el costo para el gasto subcontratado "+ item.getServicio());
				break;
			}
		}
		return errors;
	}
	
	public void setPresupuesto(Presupuesto presupuesto){
		//-------------------------------------------------------------
		//----SIEMPRE EJECUTAR DESPUES DE setPresupuesto() DE SALAS----
		//-------------------------------------------------------------
		try {
			((SubcontratadosTableModel) this.subContratacionesGeneral.getTable().getModel()).clear();
			Set gastos = presupuesto.getGastosSC();
			if (gastos != null) {
				Iterator it = gastos.iterator();
				while (it.hasNext()) {
					Ppto_GastoSC gasto = (Ppto_GastoSC) it.next();
					Ppto_Sala_Servicio servicio = gasto.getPpto_Sala_Servicio();
					GastosSubContratacionesSalasItem item = new GastosSubContratacionesSalasItem();
					if(servicio != null){
						SalaServicioItem servicioItem = com.getItemByEditingId(servicio.getId());
						if(servicioItem != null){
							item.setSalaServicioTableItemId(servicioItem.getTableItemId());
							item.setSala(servicio.getSala().getSala().getDescripcion());
							item.setCantidad((Integer.parseInt(servicio.getCantidad())));
						}
						else{
							
						}
					}					
					item.setServicio(gasto.getDetalle());					
					item.setProveedorId(gasto.getProveedor());
					item.setProveedor(ProveedorManager.instance().getDescrpcion(gasto.getProveedor()));
					item.setCosto(Double.parseDouble(gasto.getCosto()));
					item.setPrecio(Double.parseDouble(gasto.getPrecio()));
					item.setNeto(item.getPrecio() - item.getCosto());
					item.setEstado(gasto.getEstado());
					
					((SubcontratadosTableModel) this.subContratacionesGeneral.getTable().getModel()).addRow(item);
					this.subContratacionesGeneral.getTable().updateUI();
				}

			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/*public GastoContratHelper[] getGastos(){
		List itemsGenerales = ((SubcontratadosTableModel)subContratacionesGeneral.getTable().getModel()).getRows();		
		
		int countGenerales = itemsGenerales.size();	
		GastoContratHelper[] gastoHelper = new GastoContratHelper[countGenerales];
		
		for(int i = 0;i< countGenerales;i++){
			GastosSubContratacionesSalasItem item = (GastosSubContratacionesSalasItem)itemsGenerales.get(i);
			gastoHelper[i] = new GastoContratHelper();
			Ppto_GastoSC  gasto = new Ppto_GastoSC();
			gasto.setDetalle(item.getServicio());
			gasto.setProveedor(item.getProveedorId());
			gasto.setCosto(Double.toString(item.getCosto()));
			gasto.setPrecio(Double.toString(item.getPrecio()));
			
			gastoHelper[i].setPpto_GastoSC(gasto);			
		}	
		return gastoHelper;
		
	}*/
	
	public Ppto_GastoSC[] getGastos(){
		List itemsGenerales = ((SubcontratadosTableModel)subContratacionesGeneral.getTable().getModel()).getRows();		
		Ppto_GastoSC[] array= new Ppto_GastoSC[itemsGenerales.size()];
		System.out.println("CANTIDAD DE SUBCONTRATADOS EN GRILLA: "+itemsGenerales.size());
		for(int i = 0;i< itemsGenerales.size();i++){
			GastosSubContratacionesSalasItem item = (GastosSubContratacionesSalasItem)itemsGenerales.get(i);

			Ppto_GastoSC  gasto = new Ppto_GastoSC();
			gasto.setDetalle(item.getServicio());
			gasto.setProveedor(item.getProveedorId());
			gasto.setCosto(Double.toString(item.getCosto()));
			gasto.setPrecio(Double.toString(item.getPrecio()));
			gasto.setSala(item.getSala());
			gasto.setCantidad(item.getCantidad());
			gasto.setEstado("1"); 
			array[i]=gasto;			
		}	
		return array;
		
	}
	
	public GastoContratHelper[] getGastos2(){
		List itemsSalas = ((SubcontratadosTableModel)subContratacionesGeneral.getTable().getModel()).getRows();	
				
		int countSalas = itemsSalas.size();	
		GastoContratHelper[] gastoHelper = new GastoContratHelper[countSalas];
		
		for(int i=0;i<countSalas;i++){
			GastosSubContratacionesSalasItem item = (GastosSubContratacionesSalasItem)itemsSalas.get(i);
			gastoHelper[i] = new GastoContratHelper();
			gastoHelper[i].setSalaServicioTableItemId(item.getSalaServicioTableItemId());
			
			Ppto_GastoSC  gasto = new Ppto_GastoSC();			
			gasto.setDetalle(item.getServicio());
			gasto.setProveedor(item.getProveedorId());
			gasto.setCosto(Double.toString(item.getCosto()));
			gasto.setPrecio(Double.toString(item.getPrecio()));			
			gasto.setSala(item.getSala());
			if(item.getEstado() !=null)
				gasto.setEstado(item.getEstado());
			else
				gasto.setEstado("1");
			gastoHelper[i].setPpto_GastoSC(gasto);
		}				

		return gastoHelper;
	}
		
	//*************************************ACCIONES********************************************
	
	private class AddActionListener implements ActionListener {		
		public void actionPerformed(ActionEvent e) {
			setEditMode(false);
			//setItem(new GastosSubContratacionesSalasItem());
			makeItem();
			if(!isValid()){
				MessageUtil.showWarningMessage(Main.getVentana(),"Debe completar todos los campos");
				return;
			}
			save();
		}		
	}
	
	private class EditActionListener implements ActionListener {		
		public void actionPerformed(ActionEvent e) {
			if(subContratacionesGeneral.getSelectedItem() != null){
			setEditMode(true);
			setItem(subContratacionesGeneral.getSelectedItem());
			resetFields();
			}
			
		}		
	}
	
	private class RemoveActionListener implements MouseListener{

		public void mouseClicked(MouseEvent arg0) {
			/*((SubcontratadosTableModel)subContratacionesGeneral.getTable().getModel()).removeRow(subContratacionesGeneral.getSelectedItem());
			
			clearItem();
			resetFields();
			subContratacionesGeneral.getTable().updateUI();*/
		}

		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}

}
