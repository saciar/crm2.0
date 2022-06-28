package crm.gui.components;

import java.awt.Color;
import java.util.Vector;

import crm.client.managers.VendedorManager;
import crm.libraries.abm.entities.Vendedor;

public class ABMVendedoresComboBox extends ABMComboBox{
	public ABMVendedoresComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
		this.resetFields();
		
	}
	
	public void loadItems(){	
		
		try{			
			Vendedor[] vendedores = VendedorManager.instance().getAllVendedores();
			
			resetFields();
			
			for (int i = 0; i < vendedores.length; i++) {
				if(vendedores[i].getCategoria().equals("1") || vendedores[i].getCategoria().equals("2")){
					this.addItem(vendedores[i].getApellidoYNombre());
					m_codigoForeign.add(vendedores[i].getCodigo());
				}
			}
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void loadItemsForComercialUnit(String codigoUC){		
		try{			

			VendedorManager manager = new VendedorManager();
			Object[] vendedores = manager.getVendedoresByComercialUnit(codigoUC);
			
			resetFields();
			
			for (int i = 0; i < vendedores.length; i++) {
				Object[] codigoYNombre = (Object[])vendedores[i];
				System.out.println("Codigo: " + codigoYNombre[0]);
				System.out.println("Nombre: " + codigoYNombre[1]);
				this.addItem(codigoYNombre[1]);
				m_codigoForeign.add(codigoYNombre[0]);
			}
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void resetFields(){
		this.m_codigoForeign.clear();
		this.removeAllItems();	
		if(this.getItemCount() >= 0){
			this.addItem(new String("Seleccione un Vendedor"));
		}
	}
}
