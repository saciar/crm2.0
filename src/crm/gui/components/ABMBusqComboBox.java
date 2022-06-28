package crm.gui.components;

import java.util.Vector;

public class ABMBusqComboBox extends ABMComboBox{
	
	public ABMBusqComboBox(Vector crit){
		super(150, 20);
		for (int i=0; i<crit.size();i++){
			this.addItem(crit.get(i));
		}
	}
	
}
