package crm.gui.abms;

import javax.swing.JPanel;

public class ABMEnConstruccion extends ABMGeneral {
	
	private javax.swing.JLabel jLabel1;
	private JPanel panel;
	
	public ABMEnConstruccion(JPanel pan){
		panel = pan;
	}
	
	public void initComponents() {
        jLabel1 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setIcon(new javax.swing.ImageIcon(getUrlImagen("TgC_const04.gif")));
        jLabel1.setText(" ABM En construcci\u00f3n, disculpe las molestias");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
	}
	
	public void initLayout(){
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(261, 261, 261)
                .add(jLabel1)
                .addContainerGap(262, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(201, 201, 201)
                .add(jLabel1)
                .addContainerGap(258, Short.MAX_VALUE))
        );
    }// </editor-fold>
    
}
