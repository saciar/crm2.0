package crm.client.validacion;

import java.awt.Component;

import crm.client.util.Util;

public class ErrorMessageBuilder {
	public static void createErrorMessage(Component owner, String title, ErrorList errors){
		String msg = title+":\n\n";
		for (ErrorMessage error : errors) {
			msg+="* "+error+"\n";
		}
		
		Util.alertMsg(owner, msg);
	}
}
