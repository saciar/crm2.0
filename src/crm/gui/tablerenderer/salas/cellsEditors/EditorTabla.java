package crm.gui.tablerenderer.salas.cellsEditors;

import java.awt.*;
import javax.swing.table.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.awt.event.*;

public class EditorTabla extends JComboBox implements TableCellEditor
{
    /**
     * Constructor por defecto.
     */
    public EditorTabla()
    {
        // Se le ponen las opciones al JComboBox
        super (new String [] {"00:00", "00:30", "01:00","01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30"});
        
        // Nos apuntamos a cuando se seleccione algo, para avisar a la tabla
        // de que hemos cambiado el dato.
        this.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent evento)
            {
                editado(true);
            }
        });
        
        // Nos apuntamos a la p?rdida de foco, que quiere decir que se ha
        // dejado de editar la celda, sin aceptar ninguna opci?n. Avisamos
        // a la tabla de la cancelaci?n de la edici?n.
        this.addFocusListener(new FocusListener() {
            public void focusGained (FocusEvent e) {;}
            public void focusLost (FocusEvent e)
            {
                editado (false);
            }
        });
    }
    
    /** Adds a listener to the list that's notified when the editor
     * stops, or cancels editing.
     *
     * @param	l		the CellEditorListener
     *
     */
    public void addCellEditorListener(CellEditorListener l) {
        // Se a?ade el suscriptor a la lista.
        suscriptores.add (l);
    }
    
    /** Tells the editor to cancel editing and not accept any partially
     * edited value.
     *
     */
    public void cancelCellEditing() {
        // No hay que hacer nada especial.
    }
    
    /** Returns the value contained in the editor.
     * @return the value contained in the editor
     *
     */
    public Object getCellEditorValue() {
        
        // Se obtiene la opci?n del combo box elegida y se devuelve un
        // Integer adecuado.
       /* switch (this.getSelectedIndex())
        {
            // joven
            case 0:
                return new Integer(15);
            // hombre
            case 1:
                return new Integer(33);
            // viejo
            case 2:
                return new Integer (99);
                
        }*/
        
        // Devolvemos null si no nos gusta la elecci?n del men?.
        return (String) this.getSelectedItem();
    }
    
    /**  Sets an initial <code>value</code> for the editor.  This will cause
     *  the editor to <code>stopEditing</code> and lose any partially
     *  edited value if the editor is editing when this method is called. <p>
     *
     *  Returns the component that should be added to the client's
     *  <code>Component</code> hierarchy.  Once installed in the client's
     *  hierarchy this component will then be able to draw and receive
     *  user input.
     *
     * @param	table		the <code>JTable</code> that is asking the
     * 				editor to edit; can be <code>null</code>
     * @param	value		the value of the cell to be edited; it is
     * 				up to the specific editor to interpret
     * 				and draw the value.  For example, if value is
     * 				the string "true", it could be rendered as a
     * 				string or it could be rendered as a check
     * 				box that is checked.  <code>null</code>
     * 				is a valid value
     * @param	isSelected	true if the cell is to be rendered with
     * 				highlighting
     * @param	row     	the row of the cell being edited
     * @param	column  	the column of the cell being edited
     * @return	the component for editing
     *
     */
    public Component getTableCellEditorComponent(JTable table, Object value, 
       boolean isSelected, int row, int column) {
           // Devolvemos el JComboBox del que heredamos.
           return this;
    }
    
    /** Asks the editor if it can start editing using <code>anEvent</code>.
     * <code>anEvent</code> is in the invoking component coordinate system.
     * The editor can not assume the Component returned by
     * <code>getCellEditorComponent</code> is installed.  This method
     * is intended for the use of client to avoid the cost of setting up
     * and installing the editor component if editing is not possible.
     * If editing can be started this method returns true.
     *
     * @param	anEvent		the event the editor should use to consider
     * 				whether to begin editing or not
     * @return	true if editing can be started
     * @see #shouldSelectCell
     *
     */
    public boolean isCellEditable(EventObject anEvent) {
        // La celda es editable ante cualquier evento.
        return true;
    }
    
    /** Removes a listener from the list that's notified
     *
     * @param	l		the CellEditorListener
     *
     */
    public void removeCellEditorListener(CellEditorListener l) {
        // Se elimina el suscriptor.
        suscriptores.remove(l);
    }
    
    /** Returns true if the editing cell should be selected, false otherwise.
     * Typically, the return value is true, because is most cases the editing
     * cell should be selected.  However, it is useful to return false to
     * keep the selection from changing for some types of edits.
     * eg. A table that contains a column of check boxes, the user might
     * want to be able to change those checkboxes without altering the
     * selection.  (See Netscape Communicator for just such an example)
     * Of course, it is up to the client of the editor to use the return
     * value, but it doesn't need to if it doesn't want to.
     *
     * @param	anEvent		the event the editor should use to start
     * 				editing
     * @return	true if the editor would like the editing cell to be selected;
     *    otherwise returns false
     * @see #isCellEditable
     *
     */
    public boolean shouldSelectCell(EventObject anEvent) {
        // Indica si al editar la celda, debemos seleccionar la fila que la
        // contiene.
        return true;
    }
    
    /** Tells the editor to stop editing and accept any partially edited
     * value as the value of the editor.  The editor returns false if
     * editing was not stopped; this is useful for editors that validate
     * and can not accept invalid entries.
     *
     * @return	true if editing was stopped; false otherwise
     *
     */
    public boolean stopCellEditing() {
        // Indica si se puede detener la edici?n.
        return true;
    }

    /**
     * Si cambiado es true, se avisa a los suscriptores de que se ha terminado
     * la edici?n. Si es false, se avisa de que se ha cancelado la edici?n.
     */
    protected void editado(boolean cambiado)
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
    
    /** Lista de suscriptores */
    private LinkedList suscriptores = new LinkedList();
}
