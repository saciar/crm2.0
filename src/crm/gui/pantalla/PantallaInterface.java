package crm.gui.pantalla;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import crm.libraries.abm.entities.Usuario;


public interface PantallaInterface {

	abstract public JPanel createLineOfComponents(JComponent[] c, int []rigidsLenght, 
			Color color, Dimension dim);
	
	
	public Color getFieldLabelColor();
	
	abstract public JLabel createFieldLabel (String cont);
	
	public JScrollPane createPanelWithTextArea(JTextArea area, Dimension dim, boolean isEditable);
	
	public String getCurrentDate();
	
	public  Usuario getUsuario();
	
	public  String getCodigoUsuario();
}
