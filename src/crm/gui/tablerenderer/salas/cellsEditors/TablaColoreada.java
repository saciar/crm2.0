package crm.gui.tablerenderer.salas.cellsEditors;

import java.awt.Color;

public interface TablaColoreada {

	public Color getColorFondo(int row, int col);
	public Object getValueAt(int row, int col);
}