package crm.gui.components;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JToolTip;

public class DiaComboBox extends JComboBox{
	
	private Color colorToolTip;
	
	 public DiaComboBox(DefaultComboBoxModel lista_dia) {
		 super(lista_dia);
	}

	public JToolTip createToolTip() {
		    JToolTip tip = super.createToolTip();
		    tip.setForeground(colorToolTip);
		    return tip;
	}	
	
	public void setToolTipText(String s, Color c){
		colorToolTip = c;		
		super.setToolTipText(s);
	}
	
	

}

