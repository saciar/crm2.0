package crm.gui.pantalla;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;

import crm.gui.tablerenderer.horarios.HorariosItem;
import crm.gui.tablerenderer.horarios.HorariosTableModel;
import crm.gui.tablerenderer.horarios.TableRenderHorarios;

public class PantallaHorariosOperaciones extends JDialog{
	
	private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private TableRenderHorarios jTable1;
    private boolean cancel;
    
    public PantallaHorariosOperaciones(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        createListeners();
        updatePosition();
    }
    
	private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new TableRenderHorarios();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Seleccione los horarios de operacion para cada dia");
        
        //jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Aceptar");

        jButton2.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTable1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTable1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }

	
	public void setHorarios(List<HorariosItem> hor){
		HorariosTableModel model = (HorariosTableModel)jTable1.getTable().getModel();
		model.setRows(hor);
	}
	
	private void createListeners(){
		jButton1.addActionListener(new AceptarListener());
		jButton2.addActionListener(new CancelarListener());
	}
	
	public List<HorariosItem> getAllRows(){
		HorariosTableModel model = (HorariosTableModel)jTable1.getTable().getModel();

		return model.getRows();
	}
	
	public int getTablaSize(){
		HorariosTableModel model = (HorariosTableModel)jTable1.getTable().getModel();
		return model.getRowCount();
	}
	
	private class AceptarListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			cancel = false;
			dispose();			
		}
		
	}
	
	private class CancelarListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			cancel = true;
			dispose();			
		}
		
	}
	public boolean isCancel() {
        return cancel;
    }
	
public void updatePosition(){
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setLocation((screenSize.width - this.getWidth())/2,
				(screenSize.height - this.getHeight())/2);
		
	}

}
