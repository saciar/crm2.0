package crm.gui.custom;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class RegularStringFilter extends DocumentSizeFilter {
	public RegularStringFilter(int len) {
		super(len);
	}

	public void insertString(FilterBypass fb, int offs, String str,
			AttributeSet a) throws BadLocationException {

		// This rejects the entire insertion if it would make
		// the contents too long. Another option would be
		// to truncate the inserted string so the contents
		// would be exactly maxCharacters in length.
		if ((fb.getDocument().getLength() + str.length()) <= maxCharacters
				&& validateString(str))
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
	
	private static boolean validateString(String s){
		
		boolean result = true;
		
		for (int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			/*result = result && (Character.isLetter(c) || Character.isDigit(c) || c == ' ' || c == '.' || 
					c == '-' || c == '_' || c == '/' || c == '\\' || c == '(' ||
					c == ')' || c == '\n');*/
		}
		
		return result;
	}

}