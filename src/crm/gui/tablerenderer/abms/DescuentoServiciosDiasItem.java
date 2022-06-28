package crm.gui.tablerenderer.abms;

public class DescuentoServiciosDiasItem {
	private String codigo;
	private String servicio;
	private String dias;
	private String porcentaje;
	private String id;
	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return Returns the codigo.
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo The codigo to set.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return Returns the dias.
	 */
	public String getDias() {
		return dias;
	}
	/**
	 * @param dias The dias to set.
	 */
	public void setDias(String dias) {
		this.dias = dias;
	}
	/**
	 * @return Returns the porcentaje.
	 */
	public String getPorcentaje() {
		return porcentaje;
	}
	/**
	 * @param porcentaje The porcentaje to set.
	 */
	public void setPorcentaje(String porcentaje) {
		this.porcentaje = porcentaje;
	}
	/**
	 * @return Returns the servicio.
	 */
	public String getServicio() {
		return servicio;
	}
	/**
	 * @param servicio The servicio to set.
	 */
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	
	public boolean equals(Object o){
		DescuentoServiciosDiasItem item = (DescuentoServiciosDiasItem)o;
		return item.codigo.equals(this.codigo) && 
			item.dias.equals(this.dias) &&
			item.porcentaje.equals(this.porcentaje) &&
			item.servicio.equals(this.servicio);
		
	}
}
