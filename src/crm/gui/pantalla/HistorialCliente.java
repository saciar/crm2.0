package crm.gui.pantalla;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.BoxLayout;
import javax.swing.JDialog;

import crm.client.managers.ClienteManager;
import crm.gui.components.PanelImagen;
import crm.gui.tablerenderer.historialCliente.HistorialClienteItem;
import crm.gui.tablerenderer.historialCliente.HistorialClienteTableModel;
import crm.gui.tablerenderer.historialCliente.TableRenderHistorialCliente;
import crm.libraries.util.MessageUtil;

public class HistorialCliente extends PantallaEmergente{
	
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton salir;
    private TableRenderHistorialCliente tableBuscador;
    private javax.swing.JLabel tipoPptos;
	
	public HistorialCliente (Frame owner){		
		super("Información del cliente",owner);
	}
	
	public void initComponents(String cliente) {
		
		PanelImagen panel = null;
		try{
			panel = new PanelImagen("WorldLight.jpg");
		}
		catch(Exception e){
			System.out.println("Error");
			panel = new PanelImagen();
		}
		
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        tableBuscador = new TableRenderHistorialCliente();
        salir = new javax.swing.JButton();
        tipoPptos = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Presupuestos no cobrados del cliente");

        tableBuscador.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tableBuscador.setToolTipText("Resultado de la busqueda");
        
        salir.setIcon(new javax.swing.ImageIcon(getUrlImagen("cross.png")));
        salir.setMnemonic('s');
        salir.setText("Salir");
        salir.setToolTipText("Click para salir");
        salir.addActionListener(new SalirAction());

        tipoPptos.setFont(new java.awt.Font("Tahoma", 1, 14));
        
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel2.setForeground(Color.RED);
        jLabel2.setText(cliente);
        
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
                                    .add(jLabel2)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(tipoPptos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 328, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, tableBuscador, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)))
                        .add(layout.createSequentialGroup()
                            .add(281, 281, 281)
                            .add(salir)))
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel1)
                        .add(tipoPptos)
                        .add(jLabel2))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(tableBuscador, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(salir)
                    .addContainerGap())
            );
        this.getContentPane().add(panel);
        this.pack();
        
    }
	
	public void loadData(String codCliente){
		try {
			Object[] clientes = ClienteManager.instance().getClienteNoCobrado(codCliente);
			HistorialClienteTableModel model = new HistorialClienteTableModel();
			for (int i = 0; i < clientes.length; i++) {
				Object[] data = (Object[]) clientes[i];
				HistorialClienteItem item = new HistorialClienteItem();
				if(data[0] !=null)
					item.setNumeroPresupuesto(Long.parseLong(data[0].toString()));
				if(data[1] !=null)
					item.setVendedor(data[1].toString());
				if(data[2] !=null)
					item.setEvento(data[2].toString());
				if(data[3] !=null)
					item.setFechaEvento(data[3].toString());
				if(data[4] !=null)
					item.setNumeroFacturas(data[4].toString());
				if(data[5] !=null)
					item.setNumeroFacturas2(data[5].toString());
				if(data[6] !=null)
					item.setImporteTotal(data[6].toString());
				model.addRow(item);
				
			}
			if(model.getRowCount() > 0){
				tableBuscador.getTable().setModel(model);
				tableBuscador.refreshTable();						
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//------------------------------------------------------------------------------------------------------------------------
	
	private class SalirAction implements ActionListener {
		public void actionPerformed (ActionEvent evt) {
			if (MessageUtil.showYesNoMessage(HistorialCliente.this, "¿Desea salir de la información del cliente?", "Salir")){
				setVisible(false);				
			}
		}
	}
	
}
