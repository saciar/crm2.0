package crm.test;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BlockedTextField {

	
	public void main(String[] s){
		JFrame frame = new JFrame();		
		JPanel panel = new JPanel();
		
		JTextField text = new JTextField();
		text.setText("Test");
	
		
		panel.add(text);		
		
		frame.getContentPane().add(panel);
		frame.setSize(640,480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
