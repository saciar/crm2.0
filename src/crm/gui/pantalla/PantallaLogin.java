package crm.gui.pantalla;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.text.html.HTMLEditorKit;

import crm.client.helper.UserRolesUtil;
import crm.client.managers.PerfilManager;
import crm.client.managers.UsuarioManager;
import crm.client.managers.VendedorUsuarioManager;
import crm.client.util.ClockDinamico;
import crm.client.util.ProgressDialogUtil;
import crm.client.util.ProgressDialogUtil2;
import crm.client.util.UsuariosUtil;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.components.CustomTextField;
import crm.gui.components.GradientButton;
import crm.gui.components.PanelImagen;
import crm.libraries.abm.entities.Usuario;
import crm.libraries.util.MessageUtil;

/**
 * Esta clase representa la pantalla de login de usuario
 */
public class PantallaLogin extends Pantalla{
	
//	 Variables declaration - do not modify
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
	private JFormattedTextField m_txtUser;

	private JPasswordField m_txtPass;
	
	private GradientButton salir;
	
	private JEditorPane editor;	
	
	private PanelImagen middlePanel;
	private GradientButton login;
	private GradientButton olvido;
	
	public String getPath(String p)
    {
        String dir="";
        char d=((char)92);
        for(int x=0;x<p.length();x++)
        {
            char l=p.charAt(x);
            if(d==l)
            {
                dir+="\\";
            }
            else
            {
                dir+=l+"";
            }
        }
        return dir;
    }
	
	public PantallaLogin(Ventana parent) {		
		parent.setMyMenu(createMenu());
		String sSistemaOperativo = System.getProperty("os.name");
		String direccion=getPath(System.getProperty("user.home"));
		String separador = System.getProperty("file.separator");
		System.out.println(direccion);
		if(sSistemaOperativo.startsWith("Win")){
		File directorio2 = new File(direccion+"/Mis documentos/presupuestosCRM");
		   if (directorio2.mkdirs()){			   
		     System.out.println("Se ha creado directorio");
		   } else
		     System.out.println("No se ha podido crear el directorio");
		}
		else if(sSistemaOperativo.startsWith("Mac")){
			File directorio2 = new File(direccion+"/Documents/presupuestosCRM");
			if (directorio2.mkdirs()){			   
			     System.out.println("Se ha creado directorio");
			   } else
			     System.out.println("No se ha podido crear el directorio");
		}
	}
	
	public void initComponents(){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		try{			
			middlePanel = new PanelImagen("stahlmantelnet-wall.jpg");
		}
		catch(Exception e){
			System.out.println("Error");
			middlePanel = new PanelImagen();
		}
		
		m_txtUser = CustomTextField.getEmailInstance(16);
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        login = new GradientButton("Entrar", Color.blue);

        login.setIcon(new javax.swing.ImageIcon(getUrlImagen("icon_key2.gif")));
        
        jLabel3 = new javax.swing.JLabel();
        m_txtPass = CustomTextField.getPasswordInstance(16);
        salir = new GradientButton("Salir", Color.blue);

        salir.setIcon(new javax.swing.ImageIcon(getUrlImagen("ArreterSZ.png")));
        
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        olvido = new GradientButton("aquí", Color.blue);

        m_txtUser.setToolTipText("Ingrese el usuario");
        m_txtUser.setNextFocusableComponent(m_txtPass);
        m_txtUser.addFocusListener(new PassFocusListener());
        //m_txtUser.setText("vendedor1");
        //m_txtPass.setText("vendedor1");
        
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Usuario:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Password:");

        login.setMnemonic('e');
        login.setText("Entrar");
        login.setNextFocusableComponent(olvido);
        login.setToolTipText("Click para comenzar");
        
        login.addActionListener(new LoginActionListener());
        login.addKeyListener(new LoginKeyListener());
        
       /* editor = new JEditorPane();	
       try{
			HTMLEditorKit kit = new HTMLEditorKit( );
			
			editor = new JEditorPane();			
			editor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
			editor.setBorder(new EtchedBorder(1));
			editor.setEditorKitForContentType("text/html", kit );
			editor.setEditable( false );
			editor.setPage ("http://crm.congressrental.com:8888");
			
			kit.install( editor );

		 }
		 catch(IOException e){
			 Util.errMsg(Main.getVentana(), "Error al cargar HTML.",e);
		 }*/
        
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel3.setText("Esta aplicaci\u00f3n necesita una resoluci\u00f3n m\u00ednima de 1024 x 768.");

        m_txtPass.setToolTipText("Ingrese el password");
        m_txtPass.setNextFocusableComponent(login);
        m_txtPass.addKeyListener(new PassKeyListener());
        salir.setMnemonic('s');
        salir.setText("Salir");
        salir.setToolTipText("Click para salir");
        salir.addActionListener(new SalirActionListener());
        
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLabel4.setText("Copyright \u00ae 2006 Congress Net Argentina S.A. Todos los derechos reservados.");

        jLabel5.setText("Si olvido su clave indique el usuario y presione");

        olvido.setText("aqu\u00ed");
        olvido.addActionListener(new OlvidoActionListener());
        olvido.addFocusListener(new OlvidoFocusListener());
	}
	
	private JMenu[] createMenu(){
		JMenu jMenu2 = new javax.swing.JMenu();
        
        jMenu2.setText("Bienvenidos a CRM Congress Rental");
        jMenu2.setFont(new java.awt.Font("Tahoma", 1, 10));
        
        jMenu2.setEnabled(false);
        jMenu2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu2.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        
        JMenu[] list = new JMenu[1];
        list[0] = jMenu2;
        return list;
	}
	
	/**
	 * Crea y agrega a la pantalla de login los componentes para cargar un usuario
	 * con su password y deja un lugar en blanco para agregar noticias de la empresa
	 */
	public void showComponent() {       
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(middlePanel);
        middlePanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        /*.add(layout.createSequentialGroup()
                            .add(editor)
                            .addContainerGap())*/
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                    .add(jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE)
                                    .add(76, 76, 76))
                                .add(layout.createSequentialGroup()
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(jLabel2)
                                        .add(jLabel1))
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                        .add(m_txtPass)
                                        .add(m_txtUser, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                                    .add(17, 17, 17)
                                    .add(login)
                                    .add(657, 657, 657)))
                            .addContainerGap())
                        .add(layout.createSequentialGroup()
                            .add(jLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(salir)
                            .add(27, 27, 27))
                        .add(layout.createSequentialGroup()
                            .add(jLabel5)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(olvido)
                            .add(203, 203, 203))))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(layout.createSequentialGroup()
                            .add(11, 11, 11)//21
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(jLabel1)
                                .add(m_txtUser, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(jLabel2)
                                .add(m_txtPass, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(0, 0, 0))//6
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                            .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(login)
                            .add(19, 19, 19)))
                    .add(0, 0, Short.MAX_VALUE)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel5)
                        .add(olvido))
                    //.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                   // .add(editor, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 550, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .add(jLabel3)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jLabel4))
                        .add(salir))
                    .addContainerGap())
        );
        this.add(middlePanel);
        
    }// </editor-fold>		

	/**
	 * Respuesta al evento disparado al presionar el boton entrar del login
	 * @param evt evento disparado
	 */
	private void btnEntrarActionPerformed(ActionEvent evt) {		
		/*ProgressDialogUtil.setType(ProgressDialogUtil.LOGIN_TYPE);
		ProgressDialogUtil.launchProcessDialog(Main.getVentana());
		new Thread(new Runnable() {	
			public void run() {
				cargarPantalla();
				ProgressDialogUtil.closeProcessDialog();
			}
		}).start();*/
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				Main.getVentana().getGlassPane().start();
				Thread performer = new Thread(new Runnable(){
					public void run(){
						cargarPantalla();
						Main.getVentana().getGlassPane().stop();
					}
				},"Performer");
				performer.start();
			}
		});
	}
	
	/**
	 * Respuesta al evento disparado al presionar enter en la contraseña o en el boton del
	 * login
	 * @param evt evento disparado
	 */
	private void btnLoginSalasKeyPressed(KeyEvent evt){
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			/*ProgressDialogUtil.setType(ProgressDialogUtil.LOGIN_TYPE);
			ProgressDialogUtil.launchProcessDialog(Main.getVentana());
			new Thread(new Runnable() {	
				public void run() {								
					cargarPantalla();					
					ProgressDialogUtil.closeProcessDialog();					
				}
			}).start(); */
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					Main.getVentana().getGlassPane().start();
					Thread performer = new Thread(new Runnable(){
						public void run(){
							cargarPantalla();
							Main.getVentana().getGlassPane().stop();
						}
					},"Login");
					performer.start();
				}
			});
		}
	}
	
	/**
	 * Carga la pantalla de Cobranzas, Facturacio o ambas en caso de que el usuario tenga acceso a la misma
	 */
	private void cargarPantalla(){		
		if (isValidUser()){	
			
			//if(getFechaBD())
			
			if(UserRolesUtil.isVendedor(getUsuario()) || UserRolesUtil.isSupervisor(getUsuario())){	
				long l=System.currentTimeMillis();
				PantallaBienvenidaComerciales p = new PantallaBienvenidaComerciales();		
				
				p.initComponent();				
				p.createMenuBAr();				
				p.createLayout();				
				cambiarPantallaA(p);
				p.completePantalla();						
				setPantallaBienvenida(p);	
				System.out.println("Tiempo:"+((System.currentTimeMillis()-l)/1000)+" seg.");
				
			}
			else if(UserRolesUtil.isCold(getUsuario())){
				PantallaBienvenidaCold p = new PantallaBienvenidaCold();				
				p.initComponents();
				p.createMenuBAr();
				p.createLayout();
				cambiarPantallaA(p);		
				setPantallaBienvenida(p);
				
			}
			else if(UserRolesUtil.isGerenciaComercial(getUsuario())){
				PantallaBienvenidaGerencia p = new PantallaBienvenidaGerencia();				
				p.initComponents();
				p.createMenuBAr();
				p.createLayout();
				cambiarPantallaA(p);		
				setPantallaBienvenida(p);
			}			
			else if (UserRolesUtil.isFacturacionUser(getUsuario())){
				PantallaBienvenidaFacturacion p = new PantallaBienvenidaFacturacion();				
				p.initComponents();
				p.createMenuBAr();
				p.createLayout();
				cambiarPantallaA(p);		
				setPantallaBienvenida(p);
			}
			else if (UserRolesUtil.isCobranzasUser(getUsuario())){
				PantallaBienvenidaCobranzas p = new PantallaBienvenidaCobranzas();
				p.initComponents();
				p.createMenuBAr();
				p.createLayout();
				cambiarPantallaA(p);
				setPantallaBienvenida(p);
			} 
			else if(UserRolesUtil.isDepositosUser(getUsuario())){
				cambiarPantallaA(new PantallaBienvenidaDeposito());
				Main.getVentana().setExtendedState(JFrame.MAXIMIZED_BOTH);
			}
			else if (UserRolesUtil.isGerenciaAdm(getUsuario())){
				PantallaBienvenidaAdministracion p = new PantallaBienvenidaAdministracion();
				p.initComponents();
				p.createMenuBAr();
				p.initLayout();
				cambiarPantallaA(p);
				setPantallaBienvenida(p);
			}
			else if(UserRolesUtil.isAdmin(getUsuario())){
				PantallaBienvenidaAdmin p =new PantallaBienvenidaAdmin();
				p.initComponents();
				p.createLayout();
				cambiarPantallaA(p);
				setPantallaBienvenida(p);
			}
			else if(UserRolesUtil.isGastosUser(getUsuario())){
				PantallaBienvenidaGastos p = new PantallaBienvenidaGastos();				
				p.initComponents();				
				p.createMenuBAr();				
				p.createLayout();				
				cambiarPantallaA(p);					
				setPantallaBienvenida(p);	
			}
			else {
				//this.cargarPantallaBienvenida();
			}	
		}else {						
			Util.errMsg(Main.getVentana(),"Usuario o password inválido. Verifique los datos ingresados",null);
		}
	}
	
	
	
	/**
	 * Verifica si se trata de un usuario y una contraseña valida
	 * @return true si es valido y false si no es valido el usuario
	 */
	private boolean isValidUser(){
		
		try {	
			long l=System.currentTimeMillis();
			/*UsuarioManager manager =UsuarioManager.instance();
			String codigoUsuario = manager.getUserCodeByUsername(m_txtUser.getText(),m_txtPass.getText());
			if (codigoUsuario!=null){
				Usuario usuario = manager.getUsuarioById(codigoUsuario);
				setUsuario(usuario);
				setCodigoVendedor(VendedorUsuarioManager.instance().getCodigoVendedor(codigoUsuario));	*/
			Usuario usuario = UsuarioManager.instance().getUsuarioById2(m_txtUser.getText(),new String(m_txtPass.getPassword()));
			if(usuario != null){
				setUsuario(usuario);
				setCodigoVendedor(VendedorUsuarioManager.instance().getCodigoVendedor(usuario.getCodigo()));
				System.out.println("Usuario en:"+((System.currentTimeMillis()-l)/1000)+" seg.");
				return true;
			}
			
		} catch (RemoteException e) {
			return false;
		}
		return false;
	}


	//**********************ACCIONES*********************************************************
	
	private class PassFocusListener implements FocusListener{

		public void focusGained(FocusEvent arg0) {
			m_txtPass.selectAll();
		}

		public void focusLost(FocusEvent arg0) {
		}
		
	}
	
	private class OlvidoActionListener implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {			
			/*ProgressDialogUtil.setType(ProgressDialogUtil.SEND_PASS_TYPE);
			ProgressDialogUtil.launchProcessDialog(Main.getVentana());
			Thread hilo = new Thread(new Runnable() {	
				public void run() {
					try{
						String code = UsuarioManager.instance().getUserCodeByUsername(m_txtUser.getText(), null);
						if (code != null){
							UsuarioManager.instance().sendPasswordByEmail(code);							
							Util.alertMsg(Main.getVentana(), "Se le ha enviado su password \n a su casilla de mail");
						}					
						else {					
							JOptionPane.showMessageDialog(
				                    Main.getVentana(), 
				                    "Usuario inválido",
				                    "Error", 
				                    JOptionPane.ERROR_MESSAGE);
						}
					}
					catch (RemoteException e){
						Util.errMsg(Main.getVentana(), "Error al cargar datos externos", e);
					}
					finally{
						ProgressDialogUtil.closeProcessDialog();
					}
				}				
			});
			
			hilo.start();**/
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					Main.getVentana().getGlassPane().start();
					Thread performer = new Thread(new Runnable(){
						public void run(){
							try{
								//String code = UsuarioManager.instance().getUserCodeByUsername(m_txtUser.getText(), null);
								Usuario[] u = UsuarioManager.instance().findByField("loginName", m_txtUser.getText());
								if (u.length>0 && u[0] != null){
									
									UsuarioManager.instance().sendPasswordByEmail(u[0].getEmail(),u[0].getPassword(),u[0].getLoginName(),u[0].getApellidoYNombre());							
									Util.alertMsg(Main.getVentana(), "Se le ha enviado su password \n a su casilla de mail");
								}					
								else {					
									JOptionPane.showMessageDialog(
						                    Main.getVentana(), 
						                    "Usuario inválido",
						                    "Error", 
						                    JOptionPane.ERROR_MESSAGE);
								}
							}
							catch (RemoteException e){
								Util.errMsg(Main.getVentana(), "Error al cargar datos externos", e);
							}
							finally{
								Main.getVentana().getGlassPane().stop();
							}								
						}
					},"Login");
					performer.start();
				}
			});
		}
	}
	
	private class SalirActionListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {			
			if (Util.confirm(Main.getVentana(),"Seguro que desea salir de CRM Congress Rental"))
				Main.closeWindow();	
			
		}

	}
	
	private class LoginActionListener implements ActionListener{
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			btnEntrarActionPerformed(evt);
		}
	}
	
	private class LoginKeyListener implements KeyListener{
		public void keyPressed(KeyEvent evt) {
			
		}
		public void keyTyped(KeyEvent arg0) {
			
		}

		public void keyReleased(KeyEvent arg0) {
			btnLoginSalasKeyPressed(arg0);
		}
		
	}
	
	private class PassKeyListener implements KeyListener{
		public void keyPressed(KeyEvent evt) {
			
		}
		public void keyTyped(KeyEvent arg0) {
			
		}

		public void keyReleased(KeyEvent arg0) {
			btnLoginSalasKeyPressed(arg0);
		}
		
	}
	
	private class OlvidoFocusListener implements FocusListener{

		public void focusGained(FocusEvent arg0) {
			
		}

		public void focusLost(FocusEvent arg0) {
			salir.requestFocus();
		}
		
	}

}
