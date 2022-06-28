/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crm.gui.cold;

import java.util.LinkedList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author saciar
 */
public class TablaOperadoresModel implements TableModel {

    private LinkedList datos = new LinkedList();
    private LinkedList suscriptores = new LinkedList();

    public int getRowCount() {
        return datos.size();
    }

    public int getColumnCount() {
        return 29;
    }

    public String getColumnName(int columnIndex) {
        switch(columnIndex){
            case 0: return "Operadores";
            case 1: return "07:00";
            case 2: return "07:30";
            case 3: return "08:00";
            case 4: return "08:30";
            case 5: return "09:00";
            case 6: return "09:30";
            case 7: return "10:00";
            case 8: return "10:30";
            case 9: return "11:00";
            case 10: return "11:30";
            case 11: return "12:00";
            case 12: return "12:30";
            case 13: return "13:00";
            case 14: return "13:30";
            case 15: return "14:00";
            case 16: return "14:30";
            case 17: return "15:00";
            case 18: return "15:30";
            case 19: return "16:00";
            case 20: return "16:30";
            case 21: return "17:00";
            case 22: return "17:30";
            case 23: return "18:00";
            case 24: return "18:30";
            case 25: return "19:00";
            case 26: return "19:30";
            case 27: return "20:00";
            case 28: return "20:30";
            default:
                return null;
        }
    }

    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        
        
        TableModelEvent evento = new TableModelEvent (this, rowIndex, rowIndex,
            columnIndex);

        avisaSuscriptores (evento);
    }

    public void addTableModelListener(TableModelListener l) {
        suscriptores.add(l);
    }

    public void removeTableModelListener(TableModelListener l) {
        suscriptores.remove(l);
    }

    private void avisaSuscriptores (TableModelEvent evento)
    {
        int i;

        // Bucle para todos los suscriptores en la lista, se llama al metodo
        // tableChanged() de los mismos, pasándole el evento.
        for (i=0; i<suscriptores.size(); i++)
            ((TableModelListener)suscriptores.get(i)).tableChanged(evento);
    }

}
