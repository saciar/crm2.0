package crm.gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.text.NumberFormat;

import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.text.NumberFormatter;


public class DateTextField extends JPanel {
	public DateTextField () {
		super();
		this.setBackground(new Color(255,255,255));
		this.setBorder(new EtchedBorder());
		
		// initialize inner controls
		_day = CustomTextField.getTinyIntInstance();
		NumberFormatter dayNumberFormatter = (NumberFormatter)_day.getFormatter(); 
		NumberFormat dayNumberFormat = (NumberFormat)dayNumberFormatter.getFormat();
		dayNumberFormatter.setMaximum(new Integer(31));
		dayNumberFormat.setMinimumIntegerDigits(2);
		dayNumberFormat.setMaximumIntegerDigits(2);
		_day.setMaximumSize(new Dimension(20,20));
		_day.setBorder(null);
		
		_month = CustomTextField.getTinyIntInstance();
		NumberFormatter monthNumberFormatter = (NumberFormatter)_month.getFormatter();
		NumberFormat monthNumberFormat = (NumberFormat)monthNumberFormatter.getFormat();
		monthNumberFormat.setMaximumIntegerDigits(2);
		monthNumberFormat.setMinimumIntegerDigits(2);
		monthNumberFormatter.setMaximum(new Integer(12));
		_month.setMaximumSize(new Dimension(20,20));
		_month.setBorder(null);
		
		_year = CustomTextField.getSmallIntInstance();
		NumberFormatter yearNumberFormatter = (NumberFormatter)_year.getFormatter();
		NumberFormat yearNumberFormat = (NumberFormat)yearNumberFormatter.getFormat();
		yearNumberFormat.setMaximumIntegerDigits(4);
		yearNumberFormat.setMinimumIntegerDigits(4);
		_year.setMaximumSize(new Dimension(40,20));
		_year.setBorder(null);
		
		// initialize labels
		
		JLabel label = new JLabel("/");
		label.setBackground(new Color(255,255,255));
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(_day);
		this.add(label);
		this.add(_month);
		this.add(label);
		this.add(_year);
		
		this.add(new JLabel("/"));		
	}
	
	private JFormattedTextField _day;
	private JFormattedTextField _month;
	private JFormattedTextField _year;
}