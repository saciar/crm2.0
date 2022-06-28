package crm.gui.pantalla;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import crm.gui.components.PanelImagen;
import crm.gui.pantalla.solapa.SalaModel;
import crm.gui.tablerenderer.asignarSalas.AsignarSalasTableModel;
import crm.gui.tablerenderer.asignarSalas.AsignarSalasTableRender;
import crm.libraries.abm.entities.LugarEvento;
import crm.libraries.util.MessageUtil;

public class ReasignarSalas extends PantallaEmergente{
	
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton guardar;
    private javax.swing.JButton salir2;
    private AsignarSalasTableRender tableBuscador;
    private javax.swing.JLabel tipoPptos; 
    
    private LugarEvento lugarEventoOrigen;
	private LugarEvento lugarEventoDestino;
    private List<SalaModel> salasOrigen;
	private List<SalaModel> salasDestino;
	private List<SalaModel> result;
	
	public ReasignarSalas (JFrame owner,LugarEvento lugarEventoOrigen,LugarEvento lugarEventoDestino,List<SalaModel> salasOrigen,List<SalaModel> salasDestino){		
		super("Reasignar salas",owner);
		this.salasOrigen = salasOrigen;
		this.salasDestino = salasDestino;
		this.lugarEventoDestino = lugarEventoDestino;
		this.lugarEventoOrigen = lugarEventoOrigen;
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
        tableBuscador = new AsignarSalasTableRender(lugarEventoOrigen,lugarEventoDestino,salasOrigen,salasDestino);
        guardar = new javax.swing.JButton();
        salir2 = new javax.swing.JButton();
        tipoPptos = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Asignacion de salas");

        tableBuscador.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tableBuscador.setToolTipText("Resultado de la busqueda");
        
        guardar.setIcon(new javax.swing.ImageIcon(getUrlImagen("disk.png")));
        guardar.setMnemonic('g');
        guardar.setText("Guardar");
        guardar.setToolTipText("Click para guardar la asignacion de salas");
        guardar.addActionListener(new SalirAction());

        salir2.setIcon(new javax.swing.ImageIcon(getUrlImagen("arrow_down.png")));
        salir2.setMnemonic('s');
        salir2.setText("Salir");
        salir2.setToolTipText("Click para salir");
        salir2.addActionListener(new Salir2Action());
        
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
                            .add(guardar))
                            .add(layout.createSequentialGroup()
                            .add(285, 285, 285)
                            .add(salir2)))
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
                    .add(guardar)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(salir2)
                    .addContainerGap())
            );
        this.getContentPane().add(panel);
        this.pack();
        
    }
	
	//------------------------------------------------------------------------------------------------------------------------
	
	private class SalirAction implements ActionListener {
		public void actionPerformed (ActionEvent evt) {
			if (MessageUtil.showYesNoMessage(ReasignarSalas.this, "¿Desea guardar la reasignacion de salas?", "Guardar")){
				result = new ArrayList<SalaModel>();
				for (int i=0;i<salasOrigen.size();i++){
					SalaModel sala = salasOrigen.get(i);
					
					sala.setCodigoSala(((AsignarSalasTableModel)(tableBuscador.getTable().getModel())).getSalasDestino().get(i).getCodigoSala());
					sala.setNombreSala(((AsignarSalasTableModel)(tableBuscador.getTable().getModel())).getSalasDestino().get(i).getNombreSala());
					
					
					
					result.add(sala);
				}
				setVisible(false);				
			}
		}
	}
	
	private class Salir2Action implements ActionListener {
		public void actionPerformed (ActionEvent evt) {
			if (MessageUtil.showYesNoMessage(ReasignarSalas.this, "¿Desea salir sin guradar cambios?", "Salir")){
				
				setVisible(false);				
			}
		}
	}

	public List<SalaModel> getResult() {
		return result;
	}

	public void setResult(List<SalaModel> result) {
		this.result = result;
	}

}
