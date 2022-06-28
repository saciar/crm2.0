package crm.client.formatter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.text.NumberFormatter;

import crm.client.util.Util;

/**
 * In addition to its superclass NumberFormatter, this class converts 
 * to/from the empty string. Therefore it holds an <em>empty value</em> 
 * that is the counterpart of the empty string. 
 * The Method <code>#valueToString</code> converts the empty value to the
 * empty string. And <code>#stringToValue</code> converts blank strings
 * to the empty value. In all other cases the conversion is delegated
 * to its superclass.<p>
 * 
 * Often the empty value is <code>null</code> or <code>-1</code>.  
 * 
 * <strong>Examples:</strong><pre>
 * new EmptyNumberFormatter();
 * new EmptyNumberFormatter(-1);
 * </pre> 
 *
 * @author Karsten Lentzsch
 * @version $Revision: 1.1 $
 * 
 * @see java.text.Format 
 */
public class EmptyNumberFormatter extends NumberFormatter {
        
    /**
     * Holds the Number that is converted to an empty string and
     * that is the result of converting blank strings to a value.
     * 
     * @see #stringToValue(String)
     * @see #valueToString(Object)
     */
    private final Number emptyValue;
    
    
    // Instance Creation ****************************************************
    
    /**
     * Constructs an EmptyNumberFormatter that converts <code>null</code> 
     * to the empty string and vice versa.
     */
    public EmptyNumberFormatter() {
        this((Number) null);
    }
    

    /**
     * Constructs an EmptyNumberFormatter configured with the specified
     * Format; converts <code>null</code> to the empty string and vice versa.
     *
     * @param format       Format used to dictate legal values
     */
    public EmptyNumberFormatter(NumberFormat format) {
        this(format, null);
    }

    public EmptyNumberFormatter(DecimalFormat format) {    	
        this(format, null);
    }
    
    /**
     * Constructs an EmptyNumberFormatter that converts the given 
     * <code>emptyValue</code> to the empty string and vice versa. 
     * 
     * @param emptyValue   the representation of the empty string
     */
    public EmptyNumberFormatter(Number emptyValue) {
        this.emptyValue = emptyValue;
    }
    
    
    /**
     * Constructs an EmptyNumberFormatter configured with the specified
     * Format; converts <code>null</code> to the given <code>emptyValue</code>
     * and vice versa.
     *
     * @param format       Format used to dictate legal values
     * @param emptyValue   the representation of the empty string
     */
    public EmptyNumberFormatter(NumberFormat format, Number emptyValue) {
        super(format);
        this.emptyValue = emptyValue;
    }

    public EmptyNumberFormatter(DecimalFormat format, Number emptyValue) {
        super(format);
        this.emptyValue = emptyValue;
    }
    
    // Overriding Superclass Behavior *****************************************
    
    /**
     * Returns the <code>Object</code> representation of the
     * <code>String</code> <code>text</code>.<p>
     *
     * Unlike its superclass, this class converts blank strings
     * to the empty value.
     * 
     * @param text <code>String</code> to convert
     * @return <code>Object</code> representation of text
     * @throws ParseException if there is an error in the conversion
     */
    public Object stringToValue(String text) throws ParseException {
        return Util.isBlank(text)
            ? emptyValue
            : super.stringToValue(text);
    }


    /**
     * Returns a String representation of the Object <code>value</code>.
     * This invokes <code>format</code> on the current <code>Format</code>.<p>
     * 
     * Unlike its superclass, this class converts the empty value
     * to the empty string.
     *
     * @param value   the value to convert
     * @return a String representation of value
     * @throws ParseException if there is an error in the conversion
     */
    public String valueToString(Object value) throws ParseException {
        return equals(value, emptyValue)
            ? ""
            : super.valueToString(value);
    }

    /**
     * Checks and answers if the two objects are 
     * both <code>null</code> or equal.
     * 
     * <pre>
     * ValidationUtils.equals(null, null)  == true
     * ValidationUtils.equals("Hi", "Hi")  == true
     * ValidationUtils.equals("Hi", null)  == false
     * ValidationUtils.equals(null, "Hi")  == false
     * ValidationUtils.equals("Hi", "Ho")  == false
     * </pre>
     * 
     * @param o1        the first object to compare
     * @param o2        the second object to compare
     * @return boolean  <code>true</code> if and only if 
     *    both objects are <code>null</code> or equal
     */
    public static boolean equals(Object o1, Object o2) {
        return    (o1 != null && o2 != null && o1.equals(o2))
               || (o1 == null && o2 == null);
    }
}
