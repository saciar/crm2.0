package crm.gui.components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.lang.StringUtils;

import crm.client.formatter.EmptyNumberFormatter;
import crm.gui.components.listener.CambioFechaListener;
import crm.gui.components.listener.CambioHoraListener;

public class HourPanel extends JPanel{
	
	private JFormattedTextField ftfHora;
	
	private JFormattedTextField ftfMinutos;
	
	private List<FocusListener> focusListeners;
	
	private List<CambioHoraListener> cambioHoraListeners;
	
	public HourPanel(){

		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setPreferredSize(new Dimension(60, 22));
		this.setMaximumSize(new Dimension(60, 22));
		this.setSize(new Dimension(60, 22));
		
		NumberFormat numbeHoraFormat = NumberFormat.getInstance(new Locale("ES", "es"));		
		
    	numbeHoraFormat.setMaximumIntegerDigits(2);
    	numbeHoraFormat.setMinimumIntegerDigits(2);
    	numbeHoraFormat.setParseIntegerOnly(true);    	
    	
    	EmptyNumberFormatter numberHoraFormatter = new EmptyNumberFormatter(numbeHoraFormat);
    	numberHoraFormatter.setValueClass(Integer.class);
    	numberHoraFormatter.setMinimum(new Integer(0));
    	numberHoraFormatter.setMaximum(new Integer(23));    	
    	numberHoraFormatter.setAllowsInvalid(false);
    	
    	ftfHora = new JFormattedTextField(numberHoraFormatter);    	
    	ftfHora.setFocusLostBehavior(JFormattedTextField.COMMIT);
    	ftfHora.setMaximumSize(new Dimension(10*2, 20));    	
    	
    	this.add(ftfHora);
    	
    	this.add(Box.createRigidArea(new Dimension(5, 0)));
    	this.add(new JLabel(":"));
    	this.add(Box.createRigidArea(new Dimension(5, 0)));
    	
    	NumberFormat numberMinutosFormat = NumberFormat.getInstance(new Locale("ES", "es"));		

    	numberMinutosFormat.setMaximumIntegerDigits(2);
    	numberMinutosFormat.setMinimumIntegerDigits(2);
    	numberMinutosFormat.setParseIntegerOnly(true);    	
    	
    	EmptyNumberFormatter numberMinutosFormatter = new EmptyNumberFormatter(numberMinutosFormat);
    	numberMinutosFormatter.setValueClass(Integer.class);
    	numberMinutosFormatter.setMinimum(new Integer(0));
    	numberMinutosFormatter.setMaximum(new Integer(59));    	
    	numberMinutosFormatter.setAllowsInvalid(false);
    	
    	ftfMinutos = new JFormattedTextField(numberMinutosFormatter);    	
    	ftfMinutos.setFocusLostBehavior(JFormattedTextField.COMMIT);
    	ftfMinutos.setMaximumSize(new Dimension(10*2, 20));
    	
    	this.add(ftfMinutos);
    	
    	focusListeners = new ArrayList<FocusListener>();
		MyFocusListener fl = new MyFocusListener();
		ftfHora.addFocusListener(fl);
		ftfMinutos.addFocusListener(fl);		
		
		cambioHoraListeners = new ArrayList<CambioHoraListener>();
		MyActionListener al = new MyActionListener();
		ftfHora.addActionListener(al);
		ftfMinutos.addActionListener(al);
	}

	public void addFocusListener(FocusListener listener){
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
	}
	
	public String getHour() {
		return (ftfHora.getText()+":"+ftfMinutos.getText()+":00");
	}
	
	public void setDate(String date){
		if (date == null)
			date = "00:00:00";
		
		int idx = date.indexOf(" ");
		if(idx >= 0){
			date = date.substring(idx + 1,date.length());
		}
		
		StringTokenizer st = new StringTokenizer(date,":");
		
		
		//Integer horaStr = Integer.parseInt(st.nextToken());//date.subSequence(0,4).toString();
		//Integer minStr = Integer.parseInt(st.nextToken());//date.substring(4,6).toString();
		//Integer segStr = Integer.parseInt(st.nextToken());//date.substring(6,8).toString();
		
		this.ftfHora.setText(st.nextToken());
		this.ftfMinutos.setText(st.nextToken());
		//this.dia.setSelectedItem(st.nextToken());
	}
	
	public String getDate(){
		String hora = this.ftfHora.getText();
		String minuto = this.ftfMinutos.getText();
		if(StringUtils.isBlank(hora)){
			hora = "00";
		}
		if(StringUtils.isBlank(minuto)){
			minuto = "00";
		}
		/*if(hora.length() < 2){
			hora = "0" + hora;
		}
		if(minuto.length() < 2){
			minuto = "0" + minuto;
		}*/
		
		return hora + ":" + minuto + ":" + "00";
	}
	
	public void setEnabled(boolean b){
		ftfHora.setEnabled(b);
		ftfMinutos.setEnabled(b);
	}
	
	private class MyFocusListener implements FocusListener {

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
			((JFormattedTextField)arg0.getSource()).selectAll();
			for (CambioHoraListener listener : cambioHoraListeners) {
				int hour = Integer.parseInt(ftfHora.getText());
				int min = Integer.parseInt(ftfMinutos.getText());
				int seg = Integer.parseInt("00"); 
				listener.cambioHora(hour, min, seg);
			} 
		}
	}
}
