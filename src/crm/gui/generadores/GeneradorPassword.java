package crm.gui.generadores;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import crm.client.managers.UsuarioManager;
import crm.gui.PasswordGenerator;
import crm.gui.components.ABMUsuariosMultiBox;
import crm.libraries.abm.entities.Usuario;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GeneradorPassword extends JFrame {
	
	private JPanel contentPane;
	private ABMUsuariosMultiBox list;
	private JLabel lblUsuario;
	private JButton btnGenerar;
	private JLabel lblNombre;
	private JLabel lblMail;
	private JLabel lblPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GeneradorPassword frame = new GeneradorPassword();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GeneradorPassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 68, 246, 258);
		contentPane.add(scrollPane);
		
		lblUsuario = new JLabel("usuario");
		lblUsuario.setBounds(277, 85, 227, 14);
		contentPane.add(lblUsuario);
		
		lblNombre = new JLabel("nombre");
		lblNombre.setBounds(277, 105, 227, 14);
		contentPane.add(lblNombre);
		
		lblMail = new JLabel("mail");
		lblMail.setBounds(277, 125, 227, 14);
		contentPane.add(lblMail);
		
		lblPass = new JLabel("pass");
		lblPass.setBounds(277, 145, 227, 14);
		contentPane.add(lblPass);
		
		list = new ABMUsuariosMultiBox();
		list.loadItems();
		list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                	try {
						Usuario s = UsuarioManager.instance().getUsuarioById(list.searchForeigns());
						lblUsuario.setText(s.getLoginName());
						lblNombre.setText(s.getApellidoYNombre());
						lblMail.setText(s.getEmail());
						lblPass.setText(s.getPassword());
					
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	
                }
            }
        });
		scrollPane.setViewportView(list);
		
		JLabel lblNewLabel = new JLabel("Generador de claves CRM");
		lblNewLabel.setBounds(10, 11, 414, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setBounds(44, 43, 46, 14);
		contentPane.add(lblUsuarios);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 36, 494, 1);
		contentPane.add(separator);
		
		btnGenerar = new JButton("Generar");
		btnGenerar.addActionListener(new GenerarActionListener());
		btnGenerar.setBounds(277, 178, 89, 23);
		contentPane.add(btnGenerar);	
		
	}
	
	private final class GenerarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String[] codigos = list.searchForeignsArray();
 			for(int i=0;i<codigos.length;i++){
				try {
					Usuario s = UsuarioManager.instance().getUsuarioById(codigos[i]);
					s.setPassword(PasswordGenerator.getPassword(8));
					UsuarioManager.instance().update(s);
					enviarMail(s.getEmail(),s.getPassword(),s.getLoginName(),s.getApellidoYNombre());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
		}

		private void enviarMail(String email,String pass, String userName, String name) {
			try {
				UsuarioManager.instance().sendPasswordByEmail(email,pass,userName,name);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}
}
