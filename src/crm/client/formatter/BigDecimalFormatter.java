package crm.client.formatter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Format;
import java.text.ParseException;

import javax.swing.text.NumberFormatter;

public class BigDecimalFormatter extends NumberFormatter {
	
	public BigDecimalFormatter(DecimalFormat format) {    	
        super(format);
    }

	
    public Object stringToValue(String text) throws ParseException {
        if("".equals(text.trim())) {
             return null;
         }
         char ds = getDefaultLocaleDecimalSeparator();

         try {
             String val = text;
             if(ds != '.') {
                 val = val.replace(".", "").replace(ds, '.');
             }
             return new BigDecimal(val);
         } catch(NumberFormatException e) {
             return null;
         }
    }

    public String valueToString(Object value) throws ParseException {    	
    	return super.valueToString(value);
    }

    private char getDefaultLocaleDecimalSeparator() {
          DecimalFormatSymbols symbols = new DecimalFormat("0").getDecimalFormatSymbols(); 
          symbols.setDecimalSeparator('.');
          char ds = symbols.getDecimalSeparator();
          return ds;
      }

}
