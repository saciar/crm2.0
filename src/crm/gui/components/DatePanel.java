package crm.gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TimeZone;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import crm.client.editors.MyMainTreeEditor;
import crm.client.util.DateConverter;
import crm.client.util.DateDiff;
import crm.gui.components.listener.CambioDiaListener;
import crm.gui.components.listener.CambioFechaListener;
//import java.util.*;

public class DatePanel extends JPanel {

	private List<FocusListener> focusListeners;
	
	private List<ItemListener> itemListeners;
	
	private List<CambioFechaListener> cambioFechaListeners;
	
	private List<CambioDiaListener> cambioDiaListeners; 
	
	private DefaultComboBoxModel lista_dia = new DefaultComboBoxModel();
	private DefaultComboBoxModel lista_mes = new DefaultComboBoxModel();
	private DefaultComboBoxModel lista_ano = new DefaultComboBoxModel();
	
	private DiaComboBox dia = new DiaComboBox(lista_dia);
	private DiaComboBox mes = new DiaComboBox(lista_mes);
	private DiaComboBox ano = new DiaComboBox(lista_ano);
	
	//private Stack mutex;
	//private MyActionListener al;
	
	private String diaActual;
	
	public static final int startyear = 2007;
	
	public DatePanel() {
		setBackground(new Color(0,0,0,0));
		lista_dia.setSelectedItem("01");
		contiene31();
		initListaMes();
		initListaAno();
		ano.addActionListener(new AnoListener());
		mes.addActionListener(new MesListener());
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(new JLabel("Dia"));
		add(Box.createRigidArea(new Dimension(1, 0)));
		add(dia);
		add(Box.createRigidArea(new Dimension(3, 0)));
		add(new JLabel("Mes"));
		add(Box.createRigidArea(new Dimension(1, 0)));
		add(mes);
		add(Box.createRigidArea(new Dimension(3, 0)));
		add(new JLabel("Año"));
		add(Box.createRigidArea(new Dimension(1, 0)));
		add(ano);		
		add(Box.createRigidArea(new Dimension(3, 0)));
		this.setPreferredSize(new Dimension(225,20));
		this.setMaximumSize(new Dimension(225,20));
		this.setSize(new Dimension(225,20));
		dia.requestFocus();
		dia.setNextFocusableComponent(mes);
		mes.setNextFocusableComponent(ano);
		ano.setNextFocusableComponent(this);
		focusListeners = new ArrayList<FocusListener>();
		MyFocusListener fl = new MyFocusListener();
		dia.addFocusListener(fl);
		mes.addFocusListener(fl);
		ano.addFocusListener(fl);
		
		itemListeners = new ArrayList<ItemListener>();
		MyItemListener il = new MyItemListener();
		dia.addItemListener(il);
		mes.addItemListener(il);
		ano.addItemListener(il);
		
		cambioFechaListeners = new ArrayList<CambioFechaListener>();
		
		//mutex = new Stack();
		/*al = new MyActionListener();
		dia.addActionListener(al);
		mes.addActionListener(al);
		ano.addActionListener(al);*/
		
		cambioDiaListeners = new ArrayList <CambioDiaListener>();
		MyDayActionListener dl = new MyDayActionListener();
		dia.addActionListener(dl);		
	}
	
	public void addFocusListener(FocusListener listener){
		focusListeners.add(listener);
	}
	
	public void removeFocusListener(FocusListener listener){
		focusListeners.remove(listener);
	}
	
	public void addItemListener(ItemListener listener){
		itemListeners.add(listener);
	}
	
	public void removeItemListener(ItemListener listener){
		itemListeners.remove(listener);
	}
	
	public void addCambioFechaListener(CambioFechaListener listener){
		cambioFechaListeners.add(listener);
	}
	
	public void removeCambioFechaListener(CambioFechaListener listener){
		cambioFechaListeners.remove(listener);
	}
	
	public void addCambioDiaListener(CambioDiaListener listener){
		cambioDiaListeners.add(listener);
	}
	
	public void removeCambioDiaListener(CambioDiaListener listener){
		cambioDiaListeners.remove(listener);
	}
	
//	public GregorianCalendar getDate(){
	public String getDate() {
		if (dia.getSelectedItem() != null){
			return (ano.getSelectedItem()+"-"+mes.getSelectedItem()+"-"+dia.getSelectedItem());
		}
		return (ano.getSelectedItem()+"-"+mes.getSelectedItem()+"-01");
	}
	
	public void setDate(String date){
		if (date == null)
			date = Integer.toString(startyear)+"-01-01";
		
		int idx = date.indexOf(" ");
		if(idx >= 0){
			date = date.substring(0,idx);
		}
		StringTokenizer st = new StringTokenizer(date,"-");
		
		
//		Integer anoStr = Integer.parseInt(st.nextToken());
//		Integer mesStr = Integer.parseInt(st.nextToken());
//		Integer diaStr = Integer.parseInt(st.nextToken());
		
		String anoStr = st.nextToken();
		String mesStr = st.nextToken();
		String diaStr = st.nextToken();
		
		this.ano.setSelectedItem(anoStr);
		lista_mes.setSelectedItem(mesStr);
		lista_dia.setSelectedItem(diaStr);
	}
	
	public String getDay(){
		if (dia.getSelectedItem() != null)
			return dia.getSelectedItem().toString();
		else return null;
	}
	
	public String getMonth(){
		if (mes.getSelectedItem() != null)
			return mes.getSelectedItem().toString();
		else return null;
	}
	
	public String getYear(){
		if (ano.getSelectedItem() != null)
			return ano.getSelectedItem().toString();
		else return null;
	}
	
	private class AnoListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			//mutex.push(1);
			
			JComboBox cba = (JComboBox) e.getSource();
			String a = cba.getSelectedItem().toString();
			String m = mes.getSelectedItem().toString();			
			if ((Integer.parseInt(a) % 4 == 0) && (Integer.parseInt(m) == 2)){
				contiene29();
				if(Integer.parseInt(diaActual)<30)
					lista_dia.setSelectedItem(diaActual);
				else lista_dia.setSelectedItem("01");
			}
			else if (Integer.parseInt(m) == 2) {
				contiene28();
				if(Integer.parseInt(diaActual)<29)
					lista_dia.setSelectedItem(diaActual);
				else lista_dia.setSelectedItem("01");
			}
			
			//mutex.pop();
			//fireActionListener();
		}
	};
	
	/*private void fireActionListener(){
		if (mutex.isEmpty()){
			al.actionPerformed(null);
		}
	}*/
	private class MesListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//mutex.push(1);
			
			JComboBox cb = (JComboBox) e.getSource();
			String m = cb.getSelectedItem().toString();
			switch (Integer.parseInt(m)) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				contiene31();
				lista_dia.setSelectedItem(diaActual);
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				contiene30();
				if(Integer.parseInt(diaActual)<31)
					lista_dia.setSelectedItem(diaActual);
				else lista_dia.setSelectedItem("01");
				break;
			case 2:
				String anio = ano.getSelectedItem().toString();
				if (Integer.parseInt(anio) % 4 == 0 ){
					contiene29();
					if(Integer.parseInt(diaActual)<30)
						lista_dia.setSelectedItem(diaActual);
					else lista_dia.setSelectedItem("01");
				}
				else {
					contiene28();
					if(Integer.parseInt(diaActual)<29)
						lista_dia.setSelectedItem(diaActual);
					else lista_dia.setSelectedItem("01");
				}
				break;
			}
			
			//mutex.pop();
			//fireActionListener();
		}
	};
	
	private void contiene28() {
		diaActual = lista_dia.getSelectedItem().toString();
		lista_dia.removeAllElements();
//		for (int i = 1; i <= 28; i++) {
//			lista_dia.addElement(new Integer(i));
//		}
		lista_dia.addElement("01");
		lista_dia.addElement("02");
		lista_dia.addElement("03");
		lista_dia.addElement("04");
		lista_dia.addElement("05");
		lista_dia.addElement("06");
		lista_dia.addElement("07");
		lista_dia.addElement("08");
		lista_dia.addElement("09");
		lista_dia.addElement("10");
		lista_dia.addElement("11");
		lista_dia.addElement("12");
		lista_dia.addElement("13");
		lista_dia.addElement("14");
		lista_dia.addElement("15");
		lista_dia.addElement("16");
		lista_dia.addElement("17");
		lista_dia.addElement("18");
		lista_dia.addElement("19");
		lista_dia.addElement("20");
		lista_dia.addElement("21");
		lista_dia.addElement("22");
		lista_dia.addElement("23");
		lista_dia.addElement("24");
		lista_dia.addElement("25");
		lista_dia.addElement("26");
		lista_dia.addElement("27");
		lista_dia.addElement("28");	
		
	}
	
	private void contiene29(){
		contiene28();
		lista_dia.addElement("29");
	}
	
	private void contiene30() {
		contiene28();
		lista_dia.addElement("29");
		lista_dia.addElement("30");
	}

	private void contiene31() {
		contiene28();
		lista_dia.addElement("29");
		lista_dia.addElement("30");
		lista_dia.addElement("31");
	}

	private void initListaMes() {
		lista_mes.removeAllElements();
//		for (int i = 1; i <= 12; i++) {
//			lista_mes.addElement(new Integer(i));
//		}*/
		lista_mes.addElement("01");
		lista_mes.addElement("02");
		lista_mes.addElement("03");
		lista_mes.addElement("04");
		lista_mes.addElement("05");
		lista_mes.addElement("06");
		lista_mes.addElement("07");
		lista_mes.addElement("08");
		lista_mes.addElement("09");
		lista_mes.addElement("10");
		lista_mes.addElement("11");
		lista_mes.addElement("12");
	}
	
	private int getCurrentYear() {
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());

		String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

		sdf.setTimeZone(TimeZone.getDefault());

		return Integer.parseInt((sdf.format(cal.getTime())).substring(6, 10));
	}
	
	private void initListaAno() {
		lista_ano.removeAllElements();
		for (int i = 2007; i <= 2007 + 20; i++) {
			lista_ano.addElement(Integer.toString(i));
		}
		lista_ano.setSelectedItem(getCurrentYear());
	}
	
	public boolean isLessThan(String date){
		int result = DateDiff.calcularFechaMayor(this.getDate(), date);
		if (result<0)
			return true;
		else return false;
	}
	
	public boolean isGreaterThan(String date){
		int result = DateDiff.calcularFechaMayor(this.getDate(), date);
		if (result>0)
			return true;
		else return false;
	}
	
	public void setEnabled(boolean b){
		dia.setEnabled(b);
		mes.setEnabled(b);
		ano.setEnabled(b);
	}
	
	public void setEnabledDay(boolean b){
		dia.setEnabled(b);
		mes.requestFocus();
		mes.setNextFocusableComponent(ano);
		ano.setNextFocusableComponent(this);
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
	
	/*private class MyItemMouseListener implements MouseListener{

		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		private boolean isSundayOrSaturday(Date d){
			Calendar sDate = Calendar.getInstance();
			sDate.setTime(d);
			
			if(sDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || sDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
				return true;
			}
			else
				return false;
			
		}
		
		public void mouseEntered(MouseEvent arg0) {
			
			
		}

		public void mouseExited(MouseEvent arg0) {
			for(MouseListener listener : itemListeners){
				listener.mouseEntered(arg0);
				Date d;
				try {
					d = DateConverter.convertStringToDate(getDate()+" 00:00:00", "yyyy-MM-dd HH:mm:ss");			
					
					if(isSundayOrSaturday(d))
						DatePanel.this.setToolTipText(DateConverter.convertDateToString(d, "EEEEE dd MMMMM yyyy"), Color.RED);
					else
						DatePanel.this.setToolTipText(DateConverter.convertDateToString(d, "EEEEE dd MMMMM yyyy"), Color.BLACK);
					mes.setToolTipText(DateConverter.convertDateToString(d, "EEEEE dd MMMMM yyyy"));
					ano.setToolTipText(DateConverter.convertDateToString(d, "EEEEE dd MMMMM yyyy"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}*/
	
	private class MyItemListener implements ItemListener{
		
		private boolean isSundayOrSaturday(Date d){
			Calendar sDate = Calendar.getInstance();
			sDate.setTime(d);
			
			if(sDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || sDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
				return true;
			}
			else
				return false;
			
		}	
		
		public void itemStateChanged(ItemEvent arg0) {
			for(ItemListener listener : itemListeners){
				listener.itemStateChanged(arg0);
				Date d;
				try {
					d = DateConverter.convertStringToDate(getDate()+" 00:00:00", "yyyy-MM-dd HH:mm:ss");			
					
					if(isSundayOrSaturday(d))
						DatePanel.this.setToolTipText(DateConverter.convertDateToString(d, "EEEEE dd MMMMM yyyy"), Color.RED);
					else
						DatePanel.this.setToolTipText(DateConverter.convertDateToString(d, "EEEEE dd MMMMM yyyy"), Color.BLACK);
					mes.setToolTipText(DateConverter.convertDateToString(d, "EEEEE dd MMMMM yyyy"));
					ano.setToolTipText(DateConverter.convertDateToString(d, "EEEEE dd MMMMM yyyy"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	private Color colorToolTip;
	public JToolTip createToolTip() {
		JToolTip tip = super.createToolTip();
		tip.setForeground(colorToolTip);
		return tip;	
	}
	
	public void setToolTipText(String s, Color c){
		colorToolTip = c;
		this.setToolTipText(s);
		dia.setToolTipText(s,c);
		mes.setToolTipText(s,c);
		ano.setToolTipText(s,c);
	}
	private class MyDayActionListener implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
			//mutex.push(1);
			
			for (CambioDiaListener listener : cambioDiaListeners) {
				int day = 1;
				if (dia.getSelectedItem()!= null)
					day = Integer.parseInt(dia.getSelectedItem().toString()); 
				listener.cambioDia(day);
			}
			
			//mutex.pop();
			//fireActionListener();
		}
	}
}