package crm.gui.custom;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class ABMSearchTableModel extends DefaultTableModel{
    
    public ABMSearchTableModel() {
        super();
        m_rowsThatChanged = new Vector();
        m_editableCols = new Vector();
    }
    
    public boolean isCellEditable(int row, int col){
    	return (m_editableCols.contains(new Integer(col)) == true) 
    	&& row == m_curRow && col == m_curCol;    	
    }
    
    public void setCurrentCell(int row, int col){
    	m_curRow = row;
    	m_curCol = col;
    }
    
    public void setColumnEditable(int col){
    	if (m_editableCols.contains(new Integer(col)) == false){
    		m_editableCols.add(new Integer(col));
    	}
    }
    
    public void setColumnNonEditable(int col){
    	if (m_editableCols.contains(new Integer(col)) == true){
    		m_editableCols.remove(new Integer(col));
    	}
    }
    
    public void setEditableColumns(Vector v){
    	m_editableCols = v;
    }
    
    public String [] getColumnNames(){
        String [] columnNames = new String[columnIdentifiers.size()];
        
        for(int i = 0; i < columnIdentifiers.size(); i++){
            columnNames[i] = (String) columnIdentifiers.get(i);
        }
        
        return columnNames;
    }
    
    public void setValueAt(Object value, int row, int col){
    	super.setValueAt(value,row,col);
    	if (!m_rowsThatChanged.contains(new Integer(row))){
    		m_rowsThatChanged.add(new Integer(row));
    	}
    }
    
    public void clearChangedRowsVector(){
    	m_rowsThatChanged.clear();
    }
    
    public Vector getRowsThatChanged(){
    	return m_rowsThatChanged;
    }
    
    public Vector getColumnsEditable(){
    	return m_editableCols;
    }
    
    private int m_curRow;
    private int m_curCol;
    private Vector m_rowsThatChanged;
    private Vector m_editableCols;
}
