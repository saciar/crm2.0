package crm.gui.components;

import java.util.Vector;

import crm.client.managers.MarcosLiquidacionManager;
import crm.libraries.abm.entities.MarcosLiquidacion;

public class ABMMarcoLiquidacionesComboBox extends ABMComboBox {
	public ABMMarcoLiquidacionesComboBox() {
		super(200, 20);
		m_codigoForeign = new Vector();
	}

	public void loadItems() {
		try {
			loadItems(MarcosLiquidacionManager.instance()
					.getAllMarcosLiquidaciones());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void loadItems(MarcosLiquidacion[] marcosLiquidaciones) {
		resetFields();

		for (int i = 0; i < marcosLiquidaciones.length; i++) {
			this.addItem(marcosLiquidaciones[i].getDescripcion());
			m_codigoForeign.add(marcosLiquidaciones[i].getCodigo());
		}
	}

	public void resetFields() {
		this.m_codigoForeign.clear();
		this.removeAllItems();
		if (this.getItemCount() >= 0) {
			this.addItem(new String("Seleccione un Marcos Liquidacion"));
		}
	}
}
