package crm.gui.tablerenderer.salas.cellsEditors;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.EventObject;
import java.util.LinkedList;

import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellEditor;

public class SpinEditor extends JSpinner implements TableCellEditor {
	
	private LinkedList suscriptores = new LinkedList();
	
	public SpinEditor() {
		super();
	}

	public SpinEditor(SpinnerModel model) {
		super(model);
		this.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent arg0) {
				editado(true);				
			}
            
        });
		
		this.addFocusListener(new FocusListener() {
            public void focusGained (FocusEvent e) {;}
            public void focusLost (FocusEvent e)
            {
                editado (false);
            }
        });

		setOpaque(true);
		
	}


	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		return this;
	}

	// CellEditor methods
	public void cancelCellEditing() {
	}

	public Object getCellEditorValue() {
		return getValue();
	}

	public boolean isCellEditable(EventObject eo) {
		return true;
	}

	public boolean shouldSelectCell(EventObject eo) {
		return false;
	}

	public boolean stopCellEditing() {
		return true;
	}

	public void addCellEditorListener(CellEditorListener cel) {
		suscriptores.add (cel);
	}

	public void removeCellEditorListener(CellEditorListener cel) {
		suscriptores.remove(cel);
	}
	
	private void editado(boolean cambiado)
    {
        ChangeEvent evento = new ChangeEvent (this);
        int i;
        for (i=0; i<suscriptores.size(); i++)
        {
            CellEditorListener aux = (CellEditorListener)suscriptores.get(i);
            if (cambiado)
               aux.editingStopped(evento);
            else
               aux.editingCanceled(evento);
        }
    }

	
	/*private SalaServicioTableModel22 tablemodel;
	private int rowIndex;
	private Vector listeners;
	private Object originalValue;
	private boolean editing;
	private JTable tabla;	
	
	public SpinEditor() {
		super();
		listeners = new Vector();
	}

	public SpinEditor(SpinnerModel model) {
		super(model);
		listeners = new Vector();
		// setSize(100, 15);

	}


	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		
//		 obtengo el numero de linea seleccionada de la vista
		rowIndex= table.getSelectedRow();
		
		//obtengo el numero de linea seleccionada del modelo y no de la vista
		if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win"))
			rowIndex = table.convertRowIndexToModel(table.getSelectedRow());
		
		tablemodel = (SalaServicioTableModel22)table.getModel();
		tabla = table;
		setValue(value);
		table.setRowSelectionInterval(row, row);
		table.setColumnSelectionInterval(column, column);
		originalValue = getValue();
		editing = true;

		return this;
	}

	// CellEditor methods
	public void cancelCellEditing() {
		fireEditingCanceled();
		editing = false;
	}

	public Object getCellEditorValue() {
		return getValue();
	}

	public boolean isCellEditable(EventObject eo) {
		return true;
	}

	public boolean shouldSelectCell(EventObject eo) {
		return true;
	}

	public boolean stopCellEditing() {
		fireEditingStopped();
		editing = false;
		return true;
	}

	public void addCellEditorListener(CellEditorListener cel) {
		listeners.addElement(cel);
	}

	public void removeCellEditorListener(CellEditorListener cel) {
		listeners.removeElement(cel);
	}

	protected void fireEditingCanceled() {
		setValue(originalValue);
		ChangeEvent ce = new ChangeEvent(this);
		for (int i = listeners.size() - 1; i >= 0; i--) {
			((CellEditorListener) listeners.elementAt(i)).editingCanceled(ce);
		}
	}

	protected void fireEditingStopped() {
		ChangeEvent ce = new ChangeEvent(this);
		for (int i = listeners.size() - 1; i >= 0; i--) {
			((CellEditorListener) listeners.elementAt(i)).editingStopped(ce);
		}
	}*/

}