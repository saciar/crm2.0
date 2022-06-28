package crm.gui.tablerenderer.horarios;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import crm.gui.tablerenderer.TableRender;
import crm.gui.tablerenderer.salas.cellsEditors.ComboBoxCellRenderer;
import crm.gui.tablerenderer.salas.cellsEditors.EditorTabla;
import crm.gui.tablerenderer.salas.cellsEditors.ServicioCellRenderer;

public class TableRenderHorarios extends TableRender {
	
	private String[] values = new String[] { "1", "2", "3" };
	
	public TableRenderHorarios() {
		this(new ArrayList());
	}
	
	public TableRenderHorarios(List rows) {
		
		super(new HorariosTableModel(rows));		
		
		//m_interface = new Pantalla();
		
		initialize();
	
		JScrollPane scrollPane = new JScrollPane(table);
		
		add(scrollPane);	
		table.setDefaultEditor (Integer.class, new EditorTabla());
	}
	
	private void initialize(){
		int i = 0;
		
		setUpCantidadColumn(table.getColumnModel().getColumn(i++));
		
		setUpCodigoColumn(table.getColumnModel().getColumn(i++));
		
		setUpFamiliaColumn(table.getColumnModel().getColumn(i++));

	}
	
	
	public void setUpCantidadColumn(TableColumn cantidadColumn) {
		cantidadColumn.setCellRenderer(new ServicioCellRenderer());
		cantidadColumn.setPreferredWidth(100);
		cantidadColumn.setMaxWidth(100);
		cantidadColumn.setWidth(100);
	}
	
	public void setUpCodigoColumn(TableColumn codigoColumn) {

		/*odigoColumn.setPreferredWidth(40);
		codigoColumn.setMaxWidth(40);
		codigoColumn.setWidth(40);*/
		//codigoColumn.setCellRenderer(new ComboBoxCellRenderer());
	}
	
	public void setUpFamiliaColumn(TableColumn familiaColumn) {
		//familiaColumn.setCellRenderer(new ComboBoxCellRenderer());
		//familiaColumn.setPreferredWidth(80);
		//familiaColumn.setMaxWidth(80);
		//familiaColumn.setWidth(80);
	}

	public void refreshTable(){
		JTable t = getTable();
		t.updateUI();
		//t.revalidate();
		initialize();
		
	}

}
