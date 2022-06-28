package crm.gui.tablerenderer.salas.cellsEditors;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesRentabilidadItem;
import crm.gui.pantalla.reportes.tablerenderer.BuscadorReportesRentabilidadTableModel;

public class MargenesTableCellRederer extends DefaultTableCellRenderer{

    public MargenesTableCellRederer()
    {

    }

    @Override
    public Component getTableCellRendererComponent ( JTable table, Object value, boolean selected, boolean focused, int row, int column )
    {   
    	super.getTableCellRendererComponent(table, value, selected, focused, row, column);     
        
        /*BuscadorReportesRentabilidadTableModel model = (BuscadorReportesRentabilidadTableModel)table.getModel();
        
        BuscadorReportesRentabilidadItem item = model.getRow(row);
     
        if(item !=null){
        	if(item.getMargen()*100<=15 && item.getComisionTerceros()>0 && item.getComisionLugar()>0)
        	{
        		setOpaque(true);
        		setForeground(Color.red);
        	}
        	else if(item.getMargen()*100<=30 && (item.getComisionTerceros()>0 || item.getComisionLugar()>0)){
        		setOpaque(true);
        		setForeground(Color.green);
        	}
        	else if(item.getMargen()*100<=45 && item.getComisionTerceros()==0 && item.getComisionLugar()==0){
        		setOpaque(true);
        		setForeground(Color.blue);
        	} 
        } */     
    	Double total = (Double)table.getModel().getValueAt(row, 12);
    	    	
    	Double comisionHotel = (Double)table.getModel().getValueAt(row, 6);
    	Double comisionOrg = (Double)table.getModel().getValueAt(row, 7);
    	if(comisionHotel!=null && comisionOrg!=null && total !=null){
    	if (total <= 15 && (comisionHotel>0 && comisionOrg>0)){
    		setForeground(new Color(100,0,0));
    	}
    	else if(total <= 30 && (comisionHotel>0 || comisionOrg>0)){
    		setForeground(new Color(175,0,0));
    	}
    	else if(total <= 45 && comisionHotel==0 && comisionOrg==0){
    		//setForeground(new Color(100,0,0));
    		setForeground(new Color(0,0,175));
    	}
    	else setForeground(new Color(44,158,63));
    	}
    	    	
        return this;
 }
    
}
