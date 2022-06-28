package crm.gui.components;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JScrollPane;

import org.apache.commons.lang.StringUtils;

public abstract class ABMMultiBox extends JList{
	protected Vector m_codigoForeign;
	
	public ABMMultiBox (int width, int height){
		super();
		m_codigoForeign = new Vector();		
	}
	
	public ABMMultiBox(){
		super();		
	}

	public JComponent getComponent(){
		return new JScrollPane(this);
	}
	
	public String searchForeigns(){
		int indices[] = getSelectedIndices();		
		
		String accesoIds = new String();
		for(int i = 0;i < indices.length;i++){
			if(i > 0){
				accesoIds += ",";
			}
			accesoIds += ((String)m_codigoForeign.get(indices[i]));			
		}	

		return accesoIds;
	}
	
	public String[] searchForeignsArray(){
		int indices[] = getSelectedIndices();		
		
		String[] foreigns = new String[indices.length];
		for(int i = 0;i < indices.length;i++){
			foreigns[i] = ((String)m_codigoForeign.get(indices[i]));			
		}	

		return foreigns;
	}
	
	public void setForeigns(String[] foreigns){
		if(foreigns == null){
			setSelectedIndices(new int[0]);
		}else{
		List selectedIndices = new ArrayList();
		for(int i = 0; i < m_codigoForeign.size(); i++){
			String id = (String)m_codigoForeign.get(i);
			for(int j = 0; j < foreigns.length;j++){
				if(foreigns[j].equals(id)){
					selectedIndices.add(new Integer(i));
				}
			}			
		}		
		if(selectedIndices.size() > 0){
			int[] indices = new int[selectedIndices.size()];
			for(int i = 0;i < selectedIndices.size();i++){
				indices[i] = ((Integer)selectedIndices.get(i)).intValue();
			}
			setSelectedIndices(indices);
		}
		}
	}
	
	
	
	public void removeForeign(String foreign){
		if(!StringUtils.isBlank(foreign)){					
			for(int i = 0;i < m_codigoForeign.size();i++ ){
				if(foreign.equals(((String)m_codigoForeign.get(i)))){
					setSelectedIndex(i);
					remove(i);
				}	
			}
		}
	}
	
	/*
	public String[] searchForeigns(){
		int indices[] = getSelectedIndices();		
		
		String[] accesoIds = new String[indices.length];
		for(int i = 0;i < accesoIds.length;i++){
			accesoIds[i] = ((String)m_codigoForeign.get(indices[i]));
		}	

		return accesoIds;
	}
	*/

	

}
