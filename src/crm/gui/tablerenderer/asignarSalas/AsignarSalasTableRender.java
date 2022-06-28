package crm.gui.tablerenderer.asignarSalas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import crm.gui.pantalla.solapa.SalaModel;
import crm.gui.tablerenderer.TableRender;
import crm.libraries.abm.entities.LugarEvento;

public class AsignarSalasTableRender extends TableRender{
	
	private List<SalaModel> salasDestino;
	
	public AsignarSalasTableRender(LugarEvento lugarEventoOrigen,LugarEvento lugarEventoDestino,
			List<SalaModel> salasOrigen,List<SalaModel> salasDestino){
		super(new AsignarSalasTableModel(lugarEventoOrigen, lugarEventoDestino, salasOrigen));
		this.salasDestino = salasDestino;
		initialize();
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
	
	private void initialize(){		
		setUpEstadoColumn(table.getColumnModel().getColumn(AsignarSalasTableModel.COLUMNA_SALA_ORIGEN));
		setUpNroPptoColumn(table.getColumnModel().getColumn(AsignarSalasTableModel.COLUMNA_SALA_DESTINO));		
	}
	
	public void setUpNroPptoColumn(TableColumn column) {
		column.setCellEditor(new DefaultCellEditor(createSalasCombo()));
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Click para ver las Salas");
		column.setCellRenderer(renderer);
	}
	
	private JComboBox createSalasCombo(){
		// Set up the editor for the sport cells.
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		
		// preparo el elemento seleccionado
		SalaModel def = new SalaModel();
		def.setNombreSala("(Elija una Sala)");
		def.setCodigoSala(null);
		
		model.addElement(def);
		
		for (SalaModel sala : salasDestino) {
			model.addElement(sala);
		}
		
		model.setSelectedItem(def);
		
		return new JComboBox(model);
	}
	
	public void setUpEstadoColumn(TableColumn column) {		
		
	}
	
	public void refreshTable(){
		JTable t = getTable();
		t.updateUI();
		//t.revalidate();
		initialize();
		
	}

}
