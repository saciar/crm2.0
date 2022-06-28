package crm.gui.components;

import java.util.Vector;

import crm.client.managers.FamiliaServManager;
import crm.libraries.abm.entities.FamiliaServ;

public class ABMFamiliasComboBox extends ABMComboBox {
	public ABMFamiliasComboBox() {
		super(300, 20);
		m_codigoForeign = new Vector();
	}

	/*public void loadItems() {

		try {

			FamiliaServ[] familias = FamiliaServManager.instance()
					.getAllFamiliaServs();

			resetFields();

			for (int i = 0; i < familias.length; i++) {
				this.addItem(familias[i].getDescripcion());
				m_codigoForeign.add(familias[i].getCodigo());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	*/
	public void loadItems() {

		try {

			Object[] familias = FamiliaServManager.instance()
					.getFamiliaReport();

			resetFields();

			for (int i = 0; i < familias.length; i++) {
				Object[] familia = (Object[])familias[i];
				this.addItem(familia[1].toString());
				m_codigoForeign.add(familia[0].toString());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void resetFields() {
		this.m_codigoForeign.clear();
		this.removeAllItems();
		if (this.getItemCount() >= 0) {
			this.addItem(new String("Seleccione una Familia"));
		}
	}
}
