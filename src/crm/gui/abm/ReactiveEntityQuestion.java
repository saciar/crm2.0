package crm.gui.abm;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import crm.client.util.Util;

public class ReactiveEntityQuestion{
	private Component openner;
	private int value; 
	private String message;
	private String[] headers;
	private String[][] data;
	
	
	public ReactiveEntityQuestion(Component openner,String message,String[] headers,String[][] data) {   
        this.openner = openner;		       
        this.message = message;
        this.headers = headers;
        this.data = data;
        this.value = 0;        
    }
	
	
	public int showDialog(){
		final JDialog dialog = new JDialog();		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());		
		
				
		JLabel messageLabel = new JLabel(this.message);
		final JTable table = new JTable(this.data,this.headers);
		table.setAutoscrolls(true);
		JScrollPane tablePane = new JScrollPane(table);  		
		JPanel questionPanel = new JPanel();
		String question = "Desea reactivar este registro?";
		JLabel questionLabel = new JLabel(question);		
		final JButton button_yes = new JButton("Si");		
		final JButton button_no = new JButton("No");		
		questionPanel.add(questionLabel);
		questionPanel.add(button_yes);
		questionPanel.add(button_no);
				
		
		int rowCount = table.getRowCount();		
		if(rowCount > 1){
			button_yes.setEnabled(false);
		}
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				int selrow = table.getSelectedRow();
				if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win"))
					selrow = table.convertRowIndexToModel(table.getSelectedRow());
				value = selrow + 1;			
				button_yes.setEnabled((value >= 0));
			}		
		});	
		
		button_yes.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				if(value <= 0){
					value = 1;
				}
				dialog.dispose();
			}		
		});
		button_no.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				value = 0;
				dialog.dispose();
			}		
		});
		
		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				value = 0;
			}
		
		});
		
		panel.add(messageLabel, BorderLayout.NORTH);
		panel.add(tablePane, BorderLayout.CENTER);
		panel.add(questionPanel, BorderLayout.SOUTH);
             
		
        dialog.getContentPane().add(panel);
		dialog.setSize(640,150);				
		dialog.setResizable(false);
		dialog.setModal(true);
		Util.center(this.openner,dialog);
		dialog.setVisible(true);

		return this.value;
	}
	

}
