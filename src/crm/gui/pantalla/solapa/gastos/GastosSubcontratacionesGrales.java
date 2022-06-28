package crm.gui.pantalla.solapa.gastos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.apache.commons.lang.StringUtils;

import crm.client.managers.ProveedorManager;
import crm.gui.Main;
import crm.gui.components.ABMProveedoresComboBox;
import crm.gui.components.CustomTextField;
import crm.gui.pantalla.PantallaBienvenidaVerdadera;
import crm.gui.tablerenderer.gastos.GastosSubContratacionesGeneralItem;
import crm.gui.tablerenderer.gastos.GastosSubContratacionesGeneralTableModel;
import crm.gui.tablerenderer.gastos.GastosSubContratacionesSalasItem;
import crm.gui.tablerenderer.gastos.TableRenderGastosSubContratacionesGeneral;
import crm.gui.tablerenderer.salas.SalaServicioItem;
import crm.libraries.abm.entities.Ppto_GastoSC;
import crm.libraries.abm.entities.Ppto_Sala_Servicio;
import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.abm.helper.GastoContratHelper;
import crm.libraries.util.MessageUtil;

public class GastosSubcontratacionesGrales {
	private javax.swing.JButton add;
    private javax.swing.JFormattedTextField costo;
    private javax.swing.JFormattedTextField detalle;
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
    private javax.swing.JFormattedTextField precio;
    private ABMProveedoresComboBox proveedores;
    private javax.swing.JButton remove;
    private TableRenderGastosSubContratacionesGeneral subContratacionesGeneral;
    
    private JPanel panel;
	private GastosSubContratacionesGeneralItem item;
	private boolean editMode;
    
    public GastosSubcontratacionesGrales(JPanel pan){
    	panel = pan;
    }
    
    public void init(){
    	name = new javax.swing.JLabel();
        subContratacionesGeneral = new TableRenderGastosSubContratacionesGeneral();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        proveedores = new ABMProveedoresComboBox();		
        jLabel2 = new javax.swing.JLabel();
        detalle = CustomTextField.getRegularStringInstance(50);
        jLabel3 = new javax.swing.JLabel();
        precio = CustomTextField.getDecimalInstance(8,2);
        jLabel4 = new javax.swing.JLabel();
        costo = CustomTextField.getDecimalInstance(8,2);
        add = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        neto = new javax.swing.JLabel();

        name.setFont(new java.awt.Font("Tahoma", 1, 11));
        name.setText("Subcontrataciones generales");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Nombre del servicio");

        this.proveedores.loadItems();

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Proveedor");

        detalle.setText("Ingrese el nombre del servicio a subcontratar");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Precio");
        
        precio.setText("0.0");
        
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel4.setText("Costo");
        
        costo.setText("0.0");
        
        add.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("http://200.80.201.51:8888/app_files/add.png")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        add.setText("Agregar");

        remove.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("http://200.80.201.51:8888/app_files/cross.png")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
        remove.setText("Eliminar");

        edit.setIcon(new javax.swing.JLabel() {
            public javax.swing.Icon getIcon() {
                try {
                    return new javax.swing.ImageIcon(
                        new java.net.URL("http://200.80.201.51:8888/app_files/arrow_refresh.png")
                    );
                } catch (java.net.MalformedURLException e) {
                }
                return null;
            }
        }.getIcon());
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
                        .add(add)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(edit)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(remove))
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
                    .add(add)
                    .add(edit)
                    .add(remove))
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
		
		add.addActionListener(new AddActionListener());		
		edit.addActionListener(new EditActionListener());		
		remove.addActionListener(new RemoveActionListener());
		
		detalle.addFocusListener(new DetalleFocusListener());
		precio.addFocusListener(new PrecioFocusListener());
		costo.addFocusListener(new CostoFocusListener());
		
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
		this.detalle.setText(this.item.getDetalle());
		this.costo.setText(Double.toString(this.item.getCosto()));
		this.precio.setText(Double.toString(this.item.getPrecio()));
		this.proveedores.setForeign(this.item.getProveedorId());
		this.neto.setText(Double.toString(this.item.getNeto()));
	}		
	
	private void makeItem(){
		this.item.setDetalle(this.detalle.getText());
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
		this.item = new GastosSubContratacionesGeneralItem();
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
	
	private void save(){
		makeItem();					
		if(!isEditMode()){		
			((GastosSubContratacionesGeneralTableModel)this.subContratacionesGeneral.getTable().getModel()).addRow(this.item);
		}
		this.subContratacionesGeneral.getTable().updateUI();
		clearItem();
		resetFields();
	}
	
	public GastosSubContratacionesGeneralItem getItem() {
		return item;
	}

	public void setItem(GastosSubContratacionesGeneralItem item) {
		this.item = item;
		//this.neto.setText(Double.toString(item.getPrecio() - item.getCosto()));
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	
	public List getRowGenerales(){
		return ((GastosSubContratacionesGeneralTableModel)subContratacionesGeneral.getTable().getModel()).getRows();
	}
	
	public void setPresupuesto(Presupuesto presupuesto){
		//-------------------------------------------------------------
		//----SIEMPRE EJECUTAR DESPUES DE setPresupuesto() DE SALAS----
		//-------------------------------------------------------------
		try {
			((GastosSubContratacionesGeneralTableModel)this.subContratacionesGeneral.getTable().getModel()).clear();			
			Set gastos = presupuesto.getGastosSC();
			if(gastos != null){
				Iterator it = gastos.iterator();
				while (it.hasNext()) {
					Ppto_GastoSC gasto = (Ppto_GastoSC) it.next();
					Ppto_Sala_Servicio servicio = gasto.getPpto_Sala_Servicio();
				
					if(servicio == null){
						GastosSubContratacionesGeneralItem item = new GastosSubContratacionesGeneralItem();
						item.setDetalle(gasto.getDetalle());
						item.setProveedorId(gasto.getProveedor());
						item.setProveedor(ProveedorManager.instance().getDescrpcion(gasto.getProveedor()));
						item.setCosto(Double.parseDouble(gasto.getCosto()));
						item.setPrecio(Double.parseDouble(gasto.getPrecio()));
						item.setNeto(item.getPrecio() - item.getCosto());
					
						((GastosSubContratacionesGeneralTableModel)this.subContratacionesGeneral.getTable().getModel()).addRow(item);
						this.subContratacionesGeneral.getTable().updateUI();
					}
				
				}
			}
		} catch (RemoteException e) {			
			e.printStackTrace();
		}
	}
	
	public GastoContratHelper[] getGastos(){
		List itemsGenerales = ((GastosSubContratacionesGeneralTableModel)subContratacionesGeneral.getTable().getModel()).getRows();		
		
		int countGenerales = itemsGenerales.size();	
		GastoContratHelper[] gastoHelper = new GastoContratHelper[countGenerales];
		
		for(int i = 0;i< countGenerales;i++){
			GastosSubContratacionesGeneralItem item = (GastosSubContratacionesGeneralItem)itemsGenerales.get(i);
			gastoHelper[i] = new GastoContratHelper();
			Ppto_GastoSC  gasto = new Ppto_GastoSC();
			gasto.setDetalle(item.getDetalle());
			gasto.setProveedor(item.getProveedorId());
			gasto.setCosto(Double.toString(item.getCosto()));
			gasto.setPrecio(Double.toString(item.getPrecio()));
			
			gastoHelper[i].setPpto_GastoSC(gasto);			
		}	
		return gastoHelper;
		
	}
		
	//*************************************ACCIONES********************************************
	
	private class AddActionListener implements ActionListener {		
		public void actionPerformed(ActionEvent e) {
			setEditMode(false);
			setItem(new GastosSubContratacionesGeneralItem());
			makeItem();
			if(!isValid()){
				MessageUtil.showWarningMessage(Main.getVentana(),"Debe completar todos los campos");
				return;
			}
			save();
			//resetFields();
		}		
	}
	
	private class EditActionListener implements ActionListener {		
		public void actionPerformed(ActionEvent e) {
			setEditMode(true);
			setItem(subContratacionesGeneral.getSelectedItem());
			resetFields();
		}		
	}
	
	private class RemoveActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			subContratacionesGeneral.removeSelection();
			clearItem();
			resetFields();
		}
	}
	
	private class DetalleFocusListener implements FocusListener{

		public void focusGained(FocusEvent arg0) {
			detalle.selectAll();			
		}

		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class PrecioFocusListener implements FocusListener{

		public void focusGained(FocusEvent arg0) {
			precio.selectAll();			
		}

		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class CostoFocusListener implements FocusListener{

		public void focusGained(FocusEvent arg0) {
			costo.selectAll();			
		}

		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
}
