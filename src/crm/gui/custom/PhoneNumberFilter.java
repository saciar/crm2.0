package crm.gui.custom;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class PhoneNumberFilter extends DocumentSizeFilter {
	public PhoneNumberFilter() {
		super(20);
	}

	public void insertString(FilterBypass fb, int offs, String str,
			AttributeSet a) throws BadLocationException {

		// This rejects the entire insertion if it would make
		// the contents too long. Another option would be
		// to truncate the inserted string so the contents
		// would be exactly maxCharacters in length.
		if ((fb.getDocument().getLength() + str.length()) <= maxCharacters &&
				validateString(str))
			super.insertString(fb, offs, str, a);

	}

	public void replace(FilterBypass fb, int offs, int length, String str,
			AttributeSet a) throws BadLocationException {

		// This rejects the entire replacement if it would make
		// the contents too long. Another option would be
		// to truncate the replacement string so the contents
		// would be exactly maxCharacters in length.
		if (str == null) {
			super.replace(fb, offs, length, str, a);
		} else {
			if ((fb.getDocument().getLength() + str.length() - length) <= maxCharacters &&
					validateString(str))
				super.replace(fb, offs, length, str, a);
		}
	}
	
	private boolean validateString(String s){
		
		boolean result = true;
				
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);		
			result = result && ( isADigit(c) || isAValidCharacter(c) );
		}
		
		return result;
	}
	
	private static boolean isADigit(char c){
		return Character.isDigit(c);
	}
	
	private static boolean isAValidCharacter(char c){
		return c == '(' || c == ')' || c == '-' || c == ' ' || c == '/' || c == '.';
	}
}