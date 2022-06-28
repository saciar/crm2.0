package crm.gui.tablerenderer.salas.cellsEditors;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class BuscaClientesCellEditor extends JTextField implements TableCellRenderer {
	
	private String ocurrencia;
	
	final Highlighter hilit;
    final Highlighter.HighlightPainter painter;
	
	public BuscaClientesCellEditor(String o){
    	ocurrencia =o;
    	hilit = new DefaultHighlighter();
		painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW.brighter());//new Color(252,192,49));
		setHighlighter(hilit);
		setBorder(null);
    }
	
	public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int col) {
		
		if(value != null){
			setToolTipText(table.getColumnName(col)+": "+value.toString());
			setText(value.toString());
			if(value.toString().toLowerCase().contains(ocurrencia)){			
				search(value.toString().toLowerCase());			
			}
			if(isSelected){
				setBackground(table.getSelectionBackground());
				setForeground(table.getSelectionForeground());
			}
			else{
				setBackground(Color.WHITE);	
				setForeground(Color.BLACK);
			}
		}
		
		return this;
	}
	
	public void search(String value) {
        hilit.removeAllHighlights();        
        
        if (ocurrencia.length() <= 0) {
            return;
        }
        
        int index = value.indexOf(ocurrencia, 0);
        if (index >= 0) {
            try {
                int end = index + ocurrencia.length();
                hilit.addHighlight(index, end, painter);
                this.setCaretPosition(end);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
	}

}
