package crm.gui.custom;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;

public class DoubleVerifier extends InputVerifier
{
	public boolean verify(JComponent comp)
	{
		String txt;
		double valor;
		
		txt=((JFormattedTextField)comp).getText();
		if(!txt.equals(""))
		{
			try
			{
				valor=Double.parseDouble(txt);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,"No se permiten caracteres Alfanuméricos","Formato Incorrecto",JOptionPane.ERROR_MESSAGE);
				((JFormattedTextField)comp).selectAll();
				return false;
			}
			if(valor<=0)
			{
				JOptionPane.showMessageDialog(null,"El valor debe de ser positivo","Valor Incorrecto",JOptionPane.ERROR_MESSAGE);
				((JFormattedTextField)comp).selectAll();
				return false;
			}
			else
			{
				((JFormattedTextField)comp).setText(String.valueOf(Math.rint(valor*100)/100));
				return true;
			}
		}
		else
		{
			return true;
		}
	}
	
}
