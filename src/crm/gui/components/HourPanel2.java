package crm.gui.components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import crm.gui.components.listener.CambioHoraListener;

public class HourPanel2 extends JComboBox{
	//private List<FocusListener> focusListeners;
	
	//private List<CambioHoraListener> cambioHoraListeners;
	
	private DefaultComboBoxModel lista_horas = new DefaultComboBoxModel();
	
	public HourPanel2(){
		super();
		this.setModel(lista_horas);
		/*this.setPreferredSize(new Dimension(60, 22));
		this.setMaximumSize(new Dimension(60, 22));
		this.setSize(new Dimension(60, 22));*/
		cargarCombo();
		
		//focusListeners = new ArrayList<FocusListener>();
		//MyFocusListener fl = new MyFocusListener();
		//this.addFocusListener(fl);
		
		//cambioHoraListeners = new ArrayList<CambioHoraListener>();
		//MyActionListener al = new MyActionListener();
		//this.addActionListener(al);
	}
	
	private void cargarCombo(){
		lista_horas.addElement("00:00");
		lista_horas.addElement("00:30");
		lista_horas.addElement("01:00");
		lista_horas.addElement("01:30");
		lista_horas.addElement("02:00");
		lista_horas.addElement("02:30");
		lista_horas.addElement("03:00");
		lista_horas.addElement("03:30");
		lista_horas.addElement("04:00");
		lista_horas.addElement("04:30");
		lista_horas.addElement("05:00");
		lista_horas.addElement("05:30");
		lista_horas.addElement("06:00");
		lista_horas.addElement("06:30");
		lista_horas.addElement("07:00");
		lista_horas.addElement("07:30");
		lista_horas.addElement("08:00");
		lista_horas.addElement("08:30");
		lista_horas.addElement("09:00");
		lista_horas.addElement("09:30");
		lista_horas.addElement("10:00");
		lista_horas.addElement("10:30");
		lista_horas.addElement("11:00");
		lista_horas.addElement("11:30");
		lista_horas.addElement("12:00");
		lista_horas.addElement("12:30");
		lista_horas.addElement("13:00");
		lista_horas.addElement("13:30");
		lista_horas.addElement("14:00");
		lista_horas.addElement("14:30");
		lista_horas.addElement("15:00");
		lista_horas.addElement("15:30");
		lista_horas.addElement("16:00");
		lista_horas.addElement("16:30");
		lista_horas.addElement("17:00");
		lista_horas.addElement("17:30");
		lista_horas.addElement("18:00");
		lista_horas.addElement("18:30");
		lista_horas.addElement("19:00");
		lista_horas.addElement("19:30");
		lista_horas.addElement("20:00");
		lista_horas.addElement("20:30");
		lista_horas.addElement("21:00");
		lista_horas.addElement("21:30");
		lista_horas.addElement("22:00");
		lista_horas.addElement("22:30");
		lista_horas.addElement("23:00");
		lista_horas.addElement("23:30");		
	}
	
	/*public void addFocusListener(FocusListener listener){
		focusListeners.add(listener);
	}
	
	public void removeFocusListener(FocusListener listener){
		focusListeners.remove(listener);
	}
	
	public void addCambioHoraListener(CambioHoraListener listener){
		cambioHoraListeners.add(listener);
	}
	
	public void removeCambioHoraListener(CambioHoraListener listener){
		cambioHoraListeners.remove(listener);
	}*/
	
	public void setHourWithDate(String hour){
		if (hour == null)
			hour = "00:00";
		
		//int idx = hour.indexOf(" ");
		//if(idx >= 0){
			//hour = hour.substring(idx + 1,idx + 6);
		//}
		
		this.setSelectedItem(hour);
	}
	
	public String getDate(){
		return (String)this.getSelectedItem();
	}
	
	/*private class MyFocusListener implements FocusListener {

		public void focusGained(FocusEvent e) {
			for (FocusListener listener : focusListeners) {
				listener.focusGained(e);
			}
		}

		public void focusLost(FocusEvent e) {
			for (FocusListener listener : focusListeners) {
				listener.focusLost(e);
			}
		}
		
	}
	
	private class MyActionListener implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
			/*String hora((JComboBox)arg0.getSource()).getSelectedItem().toString().s;
			for (CambioHoraListener listener : cambioHoraListeners) {
				int hour = Integer.parseInt();
				int min = Integer.parseInt(ftfMinutos.getText());
				int seg = Integer.parseInt("00"); 
				listener.cambioHora(hour, min, seg);
			} *
		}
	}*/
}
