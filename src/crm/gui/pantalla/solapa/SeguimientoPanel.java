package crm.gui.pantalla.solapa;

import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import crm.client.managers.ResultadoSeguimientoManager;
import crm.client.managers.SeguimientoManager;
import crm.client.managers.TxSeguimientoManager;
import crm.client.managers.UsuarioManager;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.tablerenderer.seguimiento.SeguimientoItem;
import crm.gui.tablerenderer.seguimiento.SeguimientoTableModel;
import crm.gui.tablerenderer.seguimiento.TableRenderSeguimiento;
import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.abm.entities.ResultadoSeguimiento;
import crm.libraries.abm.entities.Seguimiento;

public class SeguimientoPanel extends PanelGeneral implements PanelInterface{
	
	private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea m_txtObservacionesSeguimiento;
    private javax.swing.JLabel name;
    private TableRenderSeguimiento tableSeguimientoPanel;
    
    private String[] observaciones;
    private JPanel panel;
    
    public SeguimientoPanel(JPanel pan){
    	panel = pan;
    }
    
    public void init(){
    	name = new javax.swing.JLabel();
        tableSeguimientoPanel = new TableRenderSeguimiento();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        m_txtObservacionesSeguimiento = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

        name.setFont(new java.awt.Font("Tahoma", 1, 11));
        name.setText("Seguimiento del presupuesto");

        m_txtObservacionesSeguimiento.setLineWrap(true);
        m_txtObservacionesSeguimiento.setMargin(new Insets(0,10,0,10));
        m_txtObservacionesSeguimiento.setEditable(false);
        jScrollPane2.setViewportView(m_txtObservacionesSeguimiento);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Observaciones del seguimiento");
        
        createListeners();
    }
    
    public void initLayout(){
    	org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, tableSeguimientoPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                    .add(name)
                    .add(jLabel1)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE))
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
                .add(tableSeguimientoPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 214, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                .addContainerGap())
        );
    }
    
    private void createListeners(){
    	tableSeguimientoPanel.getTable().addMouseListener(new MyMouseListener());
    }
    
    public void setPresupuesto(final Presupuesto p) {
    	/*ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_NEW_ENTITY);
    	ProgressDialogUtil.launchProcessDialog(Main.getVentana());
    	new Thread(new Runnable() {	
    		public void run() {
    			if(p.getNumeroDePresupuesto() != 0)
    				setupTableSeguimiento(p.getNumeroDePresupuesto());
    			else m_txtObservacionesSeguimiento.setText("Este presupuesto no tiene seguimiento porque no se ha guardado aún en la base de datos");
    			ProgressDialogUtil.closeProcessDialog();
    		}
    	}).start();*/
    	
    	SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				Main.getVentana().getGlassPane().start();
				Thread performer = new Thread(new Runnable(){
					public void run(){
						if(p.getNumeroDePresupuesto() != 0)
		    				setupTableSeguimiento(p.getNumeroDePresupuesto());
		    			else m_txtObservacionesSeguimiento.setText("Este presupuesto no tiene seguimiento porque no se ha guardado aún en la base de datos");
						Main.getVentana().getGlassPane().stop();
					}
				},"cargar seguimiento");
				performer.start();
			}
		});
    }
    
    private void setupTableSeguimiento(long nroPpto){
    	try{
    		Object[] seguimientos = TxSeguimientoManager.instance().getSeguimientosByNroPpto(nroPpto);
    		observaciones = new String[seguimientos.length];
    		SeguimientoTableModel model = new SeguimientoTableModel();
    		for (int i = 0; i < seguimientos.length; i++) {	
    			SeguimientoItem seg = new SeguimientoItem();
    			Object[] seguimientoDato = (Object[])seguimientos[i];
    			/*TODO: verificar si esto esta bien*/
    			/*try{
    				Date fechaConfirmacion = DateConverter.convertStringToDate((String)seguimientoDato[0], "yyyy-MM-dd HH:mm:ss"); 				
    				seg.setFecha(DateConverter.convertDateToString(fechaConfirmacion, "yyyy-MM-dd HH:mm:ss"));
    			} catch (ParseException e) {
    				e.printStackTrace();
    			}*/
    			seg.setFecha((String)seguimientoDato[0]);
    			seg.setUsuario(UsuarioManager.instance().getNameByCode((String)seguimientoDato[1]));
    			
    			Seguimiento s = SeguimientoManager.instance().getSeguimientoById((String)seguimientoDato[2]);
    			ResultadoSeguimiento r = ResultadoSeguimientoManager.instance().getResultadoSeguimientoById((String)seguimientoDato[3]);  			
    			seg.setAccion(s.getDescripcion());   			
    			seg.setResultado(r.getDescripcion());
    			
    			observaciones[i] = (String)seguimientoDato[4];
    			
    			model.addRow(seg, i);
    			
    		}
    		tableSeguimientoPanel.getTable().setModel(model);
    		tableSeguimientoPanel.refreshTable();
    		
    		m_txtObservacionesSeguimiento.setText("Seleccione un item de la lista para ver sus observaciones");
    	}
    	catch (RemoteException e){
    		Util.errMsg(Main.getVentana(), "Error al cargar datos externos de seguimientos", e);
    	}
    	
    }
    
    //*************************************ACCIONES********************************************
    
    private class MyMouseListener implements MouseListener{

		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			//int row = tableSeguimientoPanel.getTable().getSelectedRow();
			
//			 obtengo el numero de linea seleccionada de la vista
			int row = tableSeguimientoPanel.getTable().getSelectedRow();
			
			//obtengo el numero de linea seleccionada del modelo y no de la vista
			if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win"))
				row = tableSeguimientoPanel.getTable().convertRowIndexToModel(tableSeguimientoPanel.getTable().getSelectedRow());
			
			
			m_txtObservacionesSeguimiento.setText(observaciones[row]);
		}

		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		
    	
    }
}
