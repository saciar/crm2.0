package crm.gui.tablerenderer.asignarSalas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import crm.gui.pantalla.solapa.SalaModel;
import crm.gui.tablerenderer.salas.SalaServiciosTableModel;
import crm.libraries.abm.entities.LugarEvento;

public class AsignarSalasTableModel implements TableModel,Comparable {
	public static final int COLUMNA_SALA_ORIGEN= 0;
	public static final int COLUMNA_SALA_DESTINO = 1;
	
	private LugarEvento lugarEventoOrigen;
	private LugarEvento lugarEventoDestino;
	private List<SalaModel> salasOrigen;
	private List<SalaModel> salasDestino;
	
	public AsignarSalasTableModel(LugarEvento lugarEventoOrigen,LugarEvento lugarEventoDestino,
			List<SalaModel> salasOrigen){
		super();
		this.salasOrigen = salasOrigen;
		this.lugarEventoDestino = lugarEventoDestino;
		this.lugarEventoOrigen = lugarEventoOrigen;
		
		initDestino(salasOrigen.size());
	}
	
	private void initDestino(int size){
		salasDestino = new ArrayList<SalaModel>(size);
		
		for(int i=0;i< size;i++){
			SalaModel def = new SalaModel();
			def.setNombreSala("(Elija una Sala)");
			def.setCodigoSala(null);
			salasDestino.add(def);
		}
	}
	
	public int getRowCount() {
		if (salasOrigen == null)
			return 0;
		return salasOrigen.size();
	}
	
	public int getColumnCount() {
		return 2;
	}

	public String getColumnName(int columnIndex) {
		String lugar = "";
		if (columnIndex == COLUMNA_SALA_ORIGEN)
			lugar = lugarEventoOrigen.getNombreLugar();
		else
			lugar = lugarEventoDestino.getNombreLugar();
		
		return "Salas de " + lugar;
	}

	public Class<?> getColumnClass(int columnIndex) {
		return SalaServiciosTableModel.class;
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex){
		case COLUMNA_SALA_ORIGEN:
			return false;
		case COLUMNA_SALA_DESTINO:
			return true;
		}
		return false;
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex){
		case COLUMNA_SALA_ORIGEN:
			return salasOrigen.get(rowIndex);
		case COLUMNA_SALA_DESTINO:
			return salasDestino.get(rowIndex);
		}
		return null;
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		switch (columnIndex){
		case COLUMNA_SALA_DESTINO:
			salasDestino.set(rowIndex, (SalaModel)aValue);
		}
	}
	
	public List<SalaModel> getSalasDestino() {
		return salasDestino;
	}
	
	public void addTableModelListener(TableModelListener l) {
	}

	public void removeTableModelListener(TableModelListener l) {
	}
	
	public int compareTo(Object arg0) {
		return 0;
	}

}
