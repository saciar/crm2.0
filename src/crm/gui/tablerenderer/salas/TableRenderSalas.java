package crm.gui.tablerenderer.salas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import crm.gui.tablerenderer.TableRender;
import crm.gui.tablerenderer.salas.cellsEditors.EnterosCellRenderer;
import crm.gui.tablerenderer.salas.cellsEditors.OpcionalCellRenderer;
import crm.gui.tablerenderer.salas.cellsEditors.PorcentajeCellRenderer;
import crm.gui.tablerenderer.salas.cellsEditors.ServicioCellRenderer;
import crm.gui.tablerenderer.salas.cellsEditors.SubcontratadoCellRenderer;
import crm.gui.tablerenderer.salas.cellsEditors.TotalesCellRenderer;

public class TableRenderSalas extends TableRender {
	
	public TableRenderSalas(int porcentaje) {
		this(new ArrayList<SalaServicioItem>(), porcentaje);
	}
	
	public TableRenderSalas(List<SalaServicioItem> rows, int porcentaje) {
		
		super(new SalaServiciosTableModel(rows, porcentaje));		
		
		//m_interface = new Pantalla();
		
		initialize();
	
		JScrollPane scrollPane = new JScrollPane(table);
		
		add(scrollPane);	
		
	}
	
	private void initialize(){
		int i = 0;
		
		setUpCantidadColumn(table.getColumnModel().getColumn(i++));
		
		setUpCodigoColumn(table.getColumnModel().getColumn(i++));
		
		setUpFamiliaColumn(table.getColumnModel().getColumn(i++));
		
		setUpServicioColumn(table.getColumnModel().getColumn(i++));
		
		setUpDiasColumn(table.getColumnModel().getColumn(i++));
		
		setUpSubcontratadoColumn(table.getColumnModel().getColumn(i++));
		
		setUpOpcionalColumn(table.getColumnModel().getColumn(i++));
		
		setUpDescuentoColumn(table.getColumnModel().getColumn(i++));

		setUpPrecioListaColumn(table.getColumnModel().getColumn(i++));
		
		setUpTotalColumn(table.getColumnModel().getColumn(i++));

	}
	
	
	public void setUpCantidadColumn(TableColumn cantidadColumn) {

		cantidadColumn.setPreferredWidth(40);
		cantidadColumn.setMaxWidth(40);
		cantidadColumn.setWidth(40);
		cantidadColumn.setCellRenderer(new EnterosCellRenderer());
	}
	
	public void setUpCodigoColumn(TableColumn codigoColumn) {

		codigoColumn.setPreferredWidth(40);
		codigoColumn.setMaxWidth(40);
		codigoColumn.setWidth(40);
		codigoColumn.setCellRenderer(new EnterosCellRenderer());
	}
	
	public void setUpServicioColumn(TableColumn servColumn) {
		servColumn.setCellRenderer(new ServicioCellRenderer());
		/*servColumn.setPreferredWidth(80);
		servColumn.setMaxWidth(80);
		servColumn.setWidth(80);*/
	}
	
	public void setUpFamiliaColumn(TableColumn familiaColumn) {
		familiaColumn.setCellRenderer(new ServicioCellRenderer());
		/*familiaColumn.setPreferredWidth(80);
		familiaColumn.setMaxWidth(80);
		familiaColumn.setWidth(80);*/
	}
	
	public void setUpDiasColumn(TableColumn diasColumn) {

		diasColumn.setPreferredWidth(40);
		diasColumn.setMaxWidth(40);
		diasColumn.setWidth(40);
		diasColumn.setCellRenderer(new EnterosCellRenderer());
	}
	
	public void setUpDescuentoColumn(TableColumn diasColumn) {

		diasColumn.setPreferredWidth(60);
		diasColumn.setMaxWidth(60);
		diasColumn.setWidth(60);
		diasColumn.setCellRenderer(new PorcentajeCellRenderer());
	}
	
	public void setUpSubcontratadoColumn(TableColumn subcontratColumn) {

		subcontratColumn.setPreferredWidth(40);
		subcontratColumn.setMaxWidth(40);
		subcontratColumn.setWidth(40);
		subcontratColumn.setCellRenderer(new SubcontratadoCellRenderer());
	}
	
	public void setUpOpcionalColumn(TableColumn subcontratColumn) {

		subcontratColumn.setPreferredWidth(40);
		subcontratColumn.setMaxWidth(40);
		subcontratColumn.setWidth(40);
		subcontratColumn.setCellRenderer(new OpcionalCellRenderer());
	}

	public void setUpTotalColumn(TableColumn totalColumn) {

		totalColumn.setPreferredWidth(100);
		totalColumn.setMaxWidth(100);
		totalColumn.setWidth(100);
		totalColumn.setCellRenderer(new TotalesCellRenderer());
	}
	
	public void setUpPrecioListaColumn(TableColumn totalColumn) {

		totalColumn.setPreferredWidth(100);
		totalColumn.setMaxWidth(100);
		totalColumn.setWidth(100);
		totalColumn.setCellRenderer(new TotalesCellRenderer());
	}

	public void refreshTable(){
		JTable t = getTable();
		t.updateUI();
		//t.revalidate();
		initialize();
		
	}

}
