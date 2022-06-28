package crm.gui.components;

import java.awt.Dimension;
import java.awt.Insets;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.NumberFormatter;

import crm.client.formatter.BigDecimalFormatter;
import crm.client.formatter.EmptyNumberFormatter;
import crm.gui.custom.BitsDocumentFilter;
import crm.gui.custom.BoolDocumentFilter;
import crm.gui.custom.CuitDocumentFilter;
import crm.gui.custom.DeptoDocumentFilter;
import crm.gui.custom.EmailDocumentFilter;
import crm.gui.custom.NameDocumentFilter;
import crm.gui.custom.PhoneNumberFilter;
import crm.gui.custom.PisoDocumentFilter;
import crm.gui.custom.RegularStringFilter;

public class CustomTextField {	
	
	public static JFormattedTextField getDecimalInstance(int ent, int dec){
				
		//DecimalFormatSymbols decimalfor = new DecimalFormatSymbols();
		//decimalfor.setGroupingSeparator('.');
		//decimalfor.setDecimalSeparator(',');
		//NumberFormat numberFormat = NumberFormat.getInstance(new Locale("es","AR"));
		NumberFormat numberFormat = NumberFormat.getInstance(new Locale("en", "US"));
		//((DecimalFormat) numberFormat).setDecimalFormatSymbols(decimalfor);
		
		((DecimalFormat) numberFormat).setDecimalSeparatorAlwaysShown(true);
		
		((DecimalFormat) numberFormat).setMaximumIntegerDigits(ent);
    	((DecimalFormat) numberFormat).setMinimumIntegerDigits(1);
    	((DecimalFormat) numberFormat).setMaximumFractionDigits(dec);
    	((DecimalFormat) numberFormat).setMinimumFractionDigits(2);
    	((DecimalFormat) numberFormat).setParseBigDecimal(true);    	
    	((DecimalFormat) numberFormat).setGroupingUsed(false);
    	
    	BigDecimalFormatter numberFormatter = new BigDecimalFormatter((DecimalFormat)numberFormat);
    	
    	numberFormatter.setMinimum(new Double(0.00));
    	numberFormatter.setMaximum(new Double(9999999999.99));
    	numberFormatter.setAllowsInvalid(false);
    	
    	JFormattedTextField ftf = new JFormattedTextField(numberFormatter);    	
    	ftf.setFocusLostBehavior(JFormattedTextField.COMMIT);
    	ftf.setMaximumSize(new Dimension((ent + dec)*10, 20));    	
    	
    	return ftf;
	}
	
	public static JFormattedTextField getVaricionIntInstance(){
		//NumberFormat numberFormat = NumberFormat.getInstance(new Locale("ES", "es"));
		NumberFormat numberFormat = NumberFormat.getInstance(new Locale("EN","en"));
		
    	numberFormat.setMaximumIntegerDigits(3);
    	numberFormat.setMinimumIntegerDigits(1);
    	numberFormat.setParseIntegerOnly(true); 
    	
    	EmptyNumberFormatter numberFormatter = new EmptyNumberFormatter(numberFormat);   	
    	numberFormatter.setMinimum(new Integer(0));
    	numberFormatter.setMaximum(new Integer(199));
    	numberFormatter.setAllowsInvalid(false);
    	
    	JFormattedTextField ftf = new JFormattedTextField(numberFormatter);    	
    	ftf.setFocusLostBehavior(JFormattedTextField.COMMIT);
    	ftf.setMaximumSize(new Dimension(10*3, 20));    	
    	
    	return ftf;		
	}
	
	public static HourPanel getHoursInstance(){
		HourPanel hour = new HourPanel();
		return hour;
	}
	
	public static HourPanel2 getHours2Instance(){
		HourPanel2 hour = new HourPanel2();
		return hour;
	}
	
	public static JFormattedTextField getTinyIntInstance(){
		NumberFormat numberFormat = NumberFormat.getInstance(new Locale("ES", "es"));		
		
    	numberFormat.setMaximumIntegerDigits(3);
    	numberFormat.setMinimumIntegerDigits(0);
    	numberFormat.setParseIntegerOnly(true);    	
    	
    	EmptyNumberFormatter numberFormatter = new EmptyNumberFormatter(numberFormat);    	
    	numberFormatter.setMinimum(new Integer(0));
    	numberFormatter.setMaximum(new Integer(255));
    	numberFormatter.setAllowsInvalid(false);
    	
    	JFormattedTextField ftf = new JFormattedTextField(numberFormatter);    	
    	ftf.setFocusLostBehavior(JFormattedTextField.COMMIT);
    	ftf.setMaximumSize(new Dimension(10*3, 20));    	
    	
    	return ftf;
	}
	
	public static JFormattedTextField getWeekInstance(){
		NumberFormat numberFormat = NumberFormat.getInstance(new Locale("ES", "es"));		
		
    	numberFormat.setMaximumIntegerDigits(2);
    	numberFormat.setMinimumIntegerDigits(0);
    	numberFormat.setParseIntegerOnly(true);    	
    	
    	EmptyNumberFormatter numberFormatter = new EmptyNumberFormatter(numberFormat);    	
    	numberFormatter.setMinimum(new Integer(0));
    	numberFormatter.setMaximum(new Integer(53));
    	numberFormatter.setAllowsInvalid(false);
    	
    	JFormattedTextField ftf = new JFormattedTextField(numberFormatter);    	
    	ftf.setFocusLostBehavior(JFormattedTextField.COMMIT);
    	ftf.setMaximumSize(new Dimension(10*3, 20));    	
    	
    	return ftf;
	}
	
	public static JFormattedTextField getSmallIntInstance(){
		NumberFormat numberFormat = NumberFormat.getIntegerInstance(new Locale("ES", "es"));		
				
    	numberFormat.setMaximumIntegerDigits(5);
    	numberFormat.setMinimumIntegerDigits(0);
    	numberFormat.setParseIntegerOnly(true); 
    	numberFormat.setGroupingUsed(false);
    	
    	EmptyNumberFormatter numberFormatter = new EmptyNumberFormatter(numberFormat);    	
    	numberFormatter.setMinimum(new Integer(0));
    	numberFormatter.setMaximum(new Integer(65535));
    	numberFormatter.setAllowsInvalid(false);
    	
    	JFormattedTextField ftf = new JFormattedTextField(numberFormatter);    	
    	ftf.setFocusLostBehavior(JFormattedTextField.COMMIT);
    	ftf.setMaximumSize(new Dimension(10*5, 20));    	
    	
    	return ftf;
	}
	
	public static JFormattedTextField getMediumIntInstance(){
		NumberFormat numberFormat = NumberFormat.getIntegerInstance(new Locale("ES", "es"));		
				
    	numberFormat.setMaximumIntegerDigits(8);
    	numberFormat.setMinimumIntegerDigits(0);
    	numberFormat.setParseIntegerOnly(true);    	
    	numberFormat.setGroupingUsed(false);
    	
    	EmptyNumberFormatter numberFormatter = new EmptyNumberFormatter(numberFormat);    	
    	numberFormatter.setMinimum(new Integer(0));
    	numberFormatter.setMaximum(new Integer(16777215));
    	numberFormatter.setAllowsInvalid(false);
    	
    	JFormattedTextField ftf = new JFormattedTextField(numberFormatter);    	
    	ftf.setFocusLostBehavior(JFormattedTextField.COMMIT);
    	ftf.setMaximumSize(new Dimension(10*7, 20));    	
    	
    	return ftf;
	}
	
	public static JFormattedTextField getIntInstance(){
		NumberFormat numberFormat = NumberFormat.getIntegerInstance(new Locale("ES", "es"));		
			
    	numberFormat.setMaximumIntegerDigits(10);
    	numberFormat.setMinimumIntegerDigits(0);
    	numberFormat.setParseIntegerOnly(true);    	
    	numberFormat.setGroupingUsed(false);
    	
    	EmptyNumberFormatter numberFormatter = new EmptyNumberFormatter(numberFormat);
    	//NumberFormatter numberFormatter = new NumberFormatter(numberFormat);    	
    	numberFormatter.setMinimum(new Long(0));
    	numberFormatter.setMaximum(new Long(4294967295L));
    	numberFormatter.setAllowsInvalid(false);
    	
    	JFormattedTextField ftf = new JFormattedTextField(numberFormatter);    	
    	ftf.setFocusLostBehavior(JFormattedTextField.COMMIT);
    	ftf.setMaximumSize(new Dimension(10*8, 20));

    	return ftf;
	}
	
	public static JFormattedTextField getBigIntInstance(){
		NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("ES", "es"));		
				
    	numberFormat.setMaximumIntegerDigits(20);
    	numberFormat.setMinimumIntegerDigits(0);
    	numberFormat.setParseIntegerOnly(true);
    	numberFormat.setGroupingUsed(false);
    	
    	EmptyNumberFormatter numberFormatter = new EmptyNumberFormatter(numberFormat);    	
    	numberFormatter.setMinimum(new Double(0));
    	numberFormatter.setMaximum(new Double(18446744073709551615D));    										  
    										
    	numberFormatter.setAllowsInvalid(false);
    	
    	JFormattedTextField ftf = new JFormattedTextField(numberFormatter);    	
    	ftf.setFocusLostBehavior(JFormattedTextField.COMMIT);
    	ftf.setMaximumSize(new Dimension(17*10, 20));    	
    	
    	return ftf;
	}
	
	public static DatePanel getDateInstance(){
		DatePanel date = new DatePanel();
		return date;
	}
	
	// nros ( ) - 
	public static JFormattedTextField getPhoneNumberInstance(){
		JFormattedTextField ftf = new JFormattedTextField();
		
		((AbstractDocument)ftf.getDocument()).setDocumentFilter(new PhoneNumberFilter());
		ftf.setMaximumSize(new Dimension(10*13, 20));
		return ftf;
	}
    
	// letras y .
	public static JFormattedTextField getNameInstance(int len){
		JFormattedTextField ftf = new JFormattedTextField();
				
		((AbstractDocument)ftf.getDocument()).setDocumentFilter(new NameDocumentFilter(len));
		ftf.setMaximumSize(new Dimension((len-1) * 10, 20));		
		
		return ftf;
	}
	
//	 letras y .
	public static JPasswordField getPasswordInstance(int len){
		JPasswordField ftf = new JPasswordField();
				
		((AbstractDocument)ftf.getDocument()).setDocumentFilter(new NameDocumentFilter(len));
		ftf.setMaximumSize(new Dimension((len-1) * 10, 20));		
		
		return ftf;
	}
	
	// letras nros - _ @ .
	public static JFormattedTextField getEmailInstance(int len){
		JFormattedTextField ftf = new JFormattedTextField();
		
		((AbstractDocument)ftf.getDocument()).setDocumentFilter(new EmailDocumentFilter(len));
		ftf.setPreferredSize(new Dimension((len-1) *10, 20));
		ftf.setMinimumSize(new Dimension((len-1) *10, 20));
		ftf.setMaximumSize(new Dimension((len-1) *10, 20));		
		
		return ftf;
	}
	
	public static JFormattedTextField getInscripcionCuitInstance(){
		JFormattedTextField ftf = new JFormattedTextField();
		
		((AbstractDocument)ftf.getDocument()).setDocumentFilter(new CuitDocumentFilter());
		ftf.setMaximumSize(new Dimension(10*7, 20));	
		
		return ftf;
	}
	
	public static JFormattedTextField getPrefijoCuitInstance(){
		NumberFormat numberFormat = NumberFormat.getInstance(new Locale("ES", "es"));		
		
    	numberFormat.setMaximumIntegerDigits(2);
    	numberFormat.setMinimumIntegerDigits(0);
    	numberFormat.setParseIntegerOnly(true);    	
    	
    	EmptyNumberFormatter numberFormatter = new EmptyNumberFormatter(numberFormat);    	
    	numberFormatter.setMinimum(new Integer(0));
    	numberFormatter.setMaximum(new Integer(99));
    	numberFormatter.setAllowsInvalid(false);
    	
    	JFormattedTextField ftf = new JFormattedTextField(numberFormatter);    	
    	ftf.setFocusLostBehavior(JFormattedTextField.COMMIT);
    	ftf.setMaximumSize(new Dimension(10*3, 20));    	
    	
    	return ftf;
	}
	
	/*public static JFormattedTextField getInscripcionCuitInstance(){
		NumberFormat numberFormat = NumberFormat.getIntegerInstance(new Locale("ES", "es"));		
		
    	numberFormat.setMaximumIntegerDigits(8);
    	numberFormat.setMinimumIntegerDigits(0);
    	numberFormat.setParseIntegerOnly(true);    	
    	numberFormat.setGroupingUsed(false);
    	
    	EmptyNumberFormatter numberFormatter = new EmptyNumberFormatter(numberFormat);
    	//NumberFormatter numberFormatter = new NumberFormatter(numberFormat);    	
    	numberFormatter.setMinimum(new Long(0));
    	numberFormatter.setMaximum(new Long(99999999));
    	numberFormatter.setAllowsInvalid(false);
    	
    	JFormattedTextField ftf = new JFormattedTextField(numberFormatter);    	
    	ftf.setFocusLostBehavior(JFormattedTextField.COMMIT);
    	ftf.setMaximumSize(new Dimension(10*7, 20));

    	return ftf;
	}*/
	
	public static JFormattedTextField getDigerificadorInstance(){
		NumberFormat numberFormat = NumberFormat.getInstance(new Locale("ES", "es"));		
		
    	numberFormat.setMaximumIntegerDigits(1);
    	numberFormat.setMinimumIntegerDigits(0);
    	numberFormat.setParseIntegerOnly(true);    	
    	
    	EmptyNumberFormatter numberFormatter = new EmptyNumberFormatter(numberFormat);    	
    	numberFormatter.setMinimum(new Integer(0));
    	numberFormatter.setMaximum(new Integer(9));
    	numberFormatter.setAllowsInvalid(false);
    	
    	JFormattedTextField ftf = new JFormattedTextField(numberFormatter);    	
    	ftf.setFocusLostBehavior(JFormattedTextField.COMMIT);
    	ftf.setMaximumSize(new Dimension(10*2, 20));    	
    	
    	return ftf;
	}
	
	public static JFormattedTextField getRegularStringInstance(int len){
		JFormattedTextField ftf = new JFormattedTextField();
		
		((AbstractDocument)ftf.getDocument()).setDocumentFilter(new RegularStringFilter(len));
		ftf.setMaximumSize(new Dimension((len-1) * 10, 20));		
		
		return ftf;
	}
	
	public static JFormattedTextField getBooleanInstance(){
		JFormattedTextField ftf = new JFormattedTextField();
		
		((AbstractDocument)ftf.getDocument()).setDocumentFilter(new BoolDocumentFilter());
		ftf.setMaximumSize(new Dimension(15, 20));		
		
		return ftf;
	}
	
	public static JFormattedTextField getBitsInstance(){
		JFormattedTextField ftf = new JFormattedTextField();
		
		((AbstractDocument)ftf.getDocument()).setDocumentFilter(new BitsDocumentFilter());
		ftf.setMaximumSize(new Dimension(15, 20));		
		
		return ftf;
	}
	
	public static JFormattedTextField getPisoStringInstance(){
		JFormattedTextField ftf = new JFormattedTextField();
				
		((AbstractDocument)ftf.getDocument()).setDocumentFilter(new PisoDocumentFilter());
		ftf.setMaximumSize(new Dimension(20, 20));		
		
		return ftf;
	}
	
	public static JFormattedTextField getDeptoStringInstance(){
		JFormattedTextField ftf = new JFormattedTextField();
		
		((AbstractDocument)ftf.getDocument()).setDocumentFilter(new DeptoDocumentFilter());
		ftf.setMaximumSize(new Dimension(35, 35));		
		
		return ftf;
	}
	
	public static JTextArea getAreaInstance(int len){
		JTextArea area = new JTextArea();
		
		area.setBorder(new EtchedBorder());
		area.setLineWrap(true);
		area.setAutoscrolls(true);
		
		((AbstractDocument)area.getDocument()).setDocumentFilter(new RegularStringFilter(len));
		area.setMaximumSize(new Dimension(500, len /2 ));
		area.setPreferredSize(new Dimension(500, len /2));
		area.setSize(new Dimension(500, len /2));
		return area;
	}
}
