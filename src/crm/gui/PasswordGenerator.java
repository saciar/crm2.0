package crm.gui;

import javax.swing.JDialog;

import crm.gui.pantallas2019.gerencia.VendedoresReportesGerencia;

public class PasswordGenerator {
	 
	public static String NUMEROS = "0123456789";
 
	public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
 
	public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
 
	public static String ESPECIALES = "Ò—";
 
	public static void main(String[] args) {
		try {
			PasswordGenerator pg = new PasswordGenerator();
			System.out.println("password generada: "+pg.getPassword(8));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//
	public static String getPinNumber() {
		return getPassword(NUMEROS, 4);
	}
 
	public static String getPassword() {
		return getPassword(8);
	}
 
	public static String getPassword(int length) {
		return getPassword(NUMEROS + MAYUSCULAS + MINUSCULAS, length);
	}
 
	public static String getPassword(String key, int length) {
		String pswd = "";
 
		for (int i = 0; i < length; i++) {
			pswd+=(key.charAt((int)(Math.random() * key.length())));
		}
 
		return pswd;
	}
}
