package crm.client.editors;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTree;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellEditor;

import crm.gui.components.ABMSalasComboBox;
import crm.gui.pantalla.solapa.MainPanelComercial;
import crm.gui.pantalla.solapa.SalaPanel;
import crm.gui.tablerenderer.salas.SalaServicioItem;
import crm.libraries.abm.entities.Ppto_Sala_Servicio;

public class MyMainTreeEditor extends ABMSalasComboBox implements TreeCellEditor{	  
	  /** 
	     * Lista de suscriptores a eventos de edición  
	     */
	    private LinkedList<CellEditorListener> observadores = new LinkedList<CellEditorListener>();
	    private SalaPanel salaPanel;
	    private MainPanelComercial mainPanel;
		
	    public void setMainPanel(MainPanelComercial mainPanel) {
			this.mainPanel = mainPanel;
		}
	    /**
	     * Crea un nuevo objeto MiTreeEditor.
	     */
	    public MyMainTreeEditor(String lugarId, SalaPanel salaPanel){
	    	if(lugarId != null){
	    		this.setSalasComboBox(lugarId);
	    		this.salaPanel = salaPanel;
	    		this.addActionListener(new SelectLugarAction());
	    	}
	    }

	    /**
	     * Devuelve el Component que se usará para editar el dato.
	     *
	     * @param tree JTree en el que está el dato que se va a editar
	     * @param value El dato a editar. Este valor debe meterse en el Component
	     * que se usa para editar, de forma que sea el que se muestre.
	     * @param isSelected Si el dato está seleccionado en el JTree
	     * @param expanded Si el nodo del dato está expandido
	     * @param leaf Si el nodo del dato es hoja (no tiene hijos)
	     * @param row Fila del JTree en el que está el dato.
	     *
	     * @return El Component que hace de editor, mostrando value.
	     */
	    public Component getTreeCellEditorComponent(
	        JTree tree, Object value, boolean isSelected, boolean expanded,
	        boolean leaf, int row)
	    {
	      // Se marca el contenido de value como dato a mostrar en el JComboBox
	        setSelectedItem(
	            ((DefaultMutableTreeNode) value).getUserObject().toString());

	        return this;
	    }

	    /**
	     * Añade un nuevo suscriptor a cambios en el editor.
	     *
	     * @param l Un suscriptor
	     */
	    public void addCellEditorListener(CellEditorListener l)
	    {
	        observadores.add(l);
	    }

	    /**
	     * El JTree nos avisa de que se ha cancelado la edición, por ejemplo, el usuario
	     * ha pinchado con el ratón en otro nodo sin terminar de editar el nuestro.
	     */
	    public void cancelCellEditing()
	    {
	      // No necesitamos hacer ninguna acción especial si se cancela la edición
	    }

	    /**
	     * Debemos devolver el dato que ha recogido el editor
	     *
	     * @return El dato.
	     */
	    public Object getCellEditorValue()
	    {
	        return getSelectedItem();
	    }

	    /**
	     * Nos pasan el evento que ha sucedido sobre el nodo y debemos decidir si es
	     * un evento para empezar a editar o no.
	     * En este ejemplo se comprobará si es un triple click de ratón. El doble click
	     * ya lo tiene reservado el JTree para expandir/contraer nodos.
	     *
	     * @param anEvent Evento.
	     *
	     * @return true si se debe empezar a editar el nodo.
	     */
	    public boolean isCellEditable(EventObject anEvent)
	    {
	      // Se comprueba si el evento es un evento de ratón
	        if (anEvent instanceof MouseEvent)
	        {
	          // Y si es doble click
	            if (((MouseEvent) anEvent).getClickCount() == 2)
	            {
	                return true;
	            }
	        }

	        // En caso contrario no hay que editar.
	        return false;
	    }

	    /**
	     * Borra al suscriptor de la lista de suscriptores
	     *
	     * @param l Suscriptores
	     */
	    public void removeCellEditorListener(CellEditorListener l)
	    {
	        observadores.remove(l);
	    }

	    /**
	     * Para decidir si cuando se edita una celda, debe a su vez seleccionarse.
	     * Habitualmente se devuelve true, ya que al editar se suele querer que se
	     * seleccione.
	     * Se pude devolver false si se quiere editar una celda sin que se deseleccionen
	     * otras posibles celdas del JTree que estuvieran seleccionadas y sin que se
	     * seleccione la celda que estamos editando.
	     *
	     * @param anEvent Evento que ha ocurrido sobre la celda.
	     *
	     * @return true si queremos que se seleccione.
	     */
	    public boolean shouldSelectCell(EventObject anEvent)
	    {
	        return true;
	    }

	    /**
	     * Nos avisan que debemos detener la edición y aceptar el valor que haya en
	     * el editor.
	     * Debemos devolver true si el valor del editor es valido y lo aceptamos. false
	     * en caso de que ese valor no sea correcto y no debamos aceptar la edicion.
	     *
	     * @return true
	     */
	    public boolean stopCellEditing()
	    {
	      // No necesitamos hacer nada especial si se para la edición
	      return true;
	    }
	    
	    /**
		 * @return Returns the salaPanel.
		 */
		public SalaPanel getSalaPanel() {
			return salaPanel;
		}
	    
	    private class SelectLugarAction implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				SalaPanel newSalaPanel = salaPanel;
				newSalaPanel.getModel().setCodigoSala(searchForeign());
				newSalaPanel.getModel().setNombreSala((String)getSelectedItem());

				
				//mainPanel.modifySala(salaPanel,newSalaPanel);			
				
				Iterator<CellEditorListener> iterador = observadores.iterator();
				
				while (iterador.hasNext())
				{
					iterador.next().editingStopped(new ChangeEvent(MyMainTreeEditor.this));
				}
			}
	    	
	    }		
}
