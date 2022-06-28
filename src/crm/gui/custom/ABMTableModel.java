package crm.gui.custom;

import javax.swing.table.DefaultTableModel;

public class ABMTableModel extends DefaultTableModel{
    
    
    public ABMTableModel(Object[][] data, String [] columnNames) {
        super(data, columnNames);
    }
    
    public boolean isCellEditable(int row, int col){
        return false;
    }
    
    public String [] getColumnNames(){
        String [] columnNames = new String[columnIdentifiers.size()];
        
        for(int i = 0; i < columnIdentifiers.size(); i++){
            columnNames[i] = (String) columnIdentifiers.get(i);
        }
        
        return columnNames;
    }
}
