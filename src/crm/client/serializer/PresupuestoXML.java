package crm.client.serializer;

import java.io.Serializable;

import org.jdom.Element;

public class PresupuestoXML extends Element implements Serializable{
	
	private String numeroDePresupuesto;
	private String codigoUsuario;
	private String codigoVendedor;
	
	private String codigoCliente;
	private String observacionesDelCliente;
	private String codigoReferencia;
	
	private String fechaDeInicio;
	private String fechaDeFinalizacion;
	private String fechaDeInstalacion;
	private String totalDePersonas;
	private String nombreDelEvento;
	private String observacionesDelEvento;
	private String codigoTipoDeEvento;
	private String codigoTipoDeLugarDelEvento;
	private String codigoUniforme;
	
	private String codigoLugarDelEvento;
		
	private String codigoEncabezado;
	private String codigoFormaPago;
	private String codigoCondPago;
	private String codigoValidez;
	private String codigoTipoPpto;
	private String codigoPeriodo;
	private String codigoMoneda;
	private String codigoCancelacion;
	private double cotizacion;
	
	private String responsableEvento;
	private String responsableTel;
	private String responsableEmail;
	private String responsableNextelFlota;
	private String responsableNextelId;
	
	private String activo;
	
	private AgregadosXML agregados;
	private AdelantoXML adelanto;
	private ClienteFacturacionXML clienteFacturacion;
	private ContactoClienteXML contactoCliente;
	private ContactoLugarXML contactoLugar;
	private EstadoXML estado;
	private PagoXML pago;
	private RentabilidadXML rentabilidad; 
	private SalasXML[] salas;
	private GastosContratacionXML[] gastosContratacion;
	
	public PresupuestoXML() {
		super("Presupuesto");
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
		addContent(new Element("Activo").setText(this.activo));
	}

	public String getCodigoCancelacion() {
		return codigoCancelacion;
	}

	public void setCodigoCancelacion(String codigoCancelacion) {
		this.codigoCancelacion = codigoCancelacion;
		addContent(new Element("CodigoCancelacion").setText(this.codigoCancelacion));
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
		addContent(new Element("CodigoCliente").setText(this.codigoCliente));
	}

	public String getCodigoCondPago() {
		return codigoCondPago;
	}

	public void setCodigoCondPago(String codigoCondPago) {
		this.codigoCondPago = codigoCondPago;
		addContent(new Element("CodigoCondPago").setText(this.codigoCondPago));
	}

	public String getCodigoEncabezado() {
		return codigoEncabezado;
	}

	public void setCodigoEncabezado(String codigoEncabezado) {
		this.codigoEncabezado = codigoEncabezado;
		addContent(new Element("CodigoEncabezado").setText(this.codigoEncabezado));
	}

	public String getCodigoFormaPago() {
		return codigoFormaPago;
	}

	public void setCodigoFormaPago(String codigoFormaPago) {
		this.codigoFormaPago = codigoFormaPago;
		addContent(new Element("CodigoFormaPago").setText(this.codigoFormaPago));
	}

	public String getCodigoLugarDelEvento() {
		return codigoLugarDelEvento;
	}

	public void setCodigoLugarDelEvento(String codigoLugarDelEvento) {
		this.codigoLugarDelEvento = codigoLugarDelEvento;
		addContent(new Element("CodigoLugarEvento").setText(this.codigoLugarDelEvento));
	}

	public String getCodigoMoneda() {
		return codigoMoneda;
	}

	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
		addContent(new Element("CodigoMoneda").setText(this.codigoMoneda));
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
		addContent(new Element("CodigoPeriodo").setText(this.codigoPeriodo));
	}

	public String getCodigoReferencia() {
		return codigoReferencia;
	}

	public void setCodigoReferencia(String codigoReferencia) {
		this.codigoReferencia = codigoReferencia;
		addContent(new Element("CodigoReferencia").setText(this.codigoReferencia));
	}

	public String getCodigoTipoDeEvento() {
		return codigoTipoDeEvento;
	}

	public void setCodigoTipoDeEvento(String codigoTipoDeEvento) {
		this.codigoTipoDeEvento = codigoTipoDeEvento;
		addContent(new Element("CodigoTipoEvento").setText(this.codigoTipoDeEvento));
	}

	public String getCodigoTipoDeLugarDelEvento() {
		return codigoTipoDeLugarDelEvento;
	}

	public void setCodigoTipoDeLugarDelEvento(String codigoTipoDeLugarDelEvento) {
		this.codigoTipoDeLugarDelEvento = codigoTipoDeLugarDelEvento;
		addContent(new Element("CodigoTipoLugar").setText(this.codigoTipoDeLugarDelEvento));
	}

	public String getCodigoTipoPpto() {
		return codigoTipoPpto;
	}

	public void setCodigoTipoPpto(String codigoTipoPpto) {
		this.codigoTipoPpto = codigoTipoPpto;
		addContent(new Element("CodigoTipoPpto").setText(this.codigoTipoPpto));
	}

	public String getCodigoUniforme() {
		return codigoUniforme;
	}

	public void setCodigoUniforme(String codigoUniforme) {
		this.codigoUniforme = codigoUniforme;
		addContent(new Element("CodigoUniforme").setText(this.codigoUniforme));
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
		addContent(new Element("CodigoUsuario").setText(this.codigoUsuario));
	}

	public String getCodigoValidez() {
		return codigoValidez;
	}

	public void setCodigoValidez(String codigoValidez) {
		this.codigoValidez = codigoValidez;
		addContent(new Element("CodigoValidez").setText(this.codigoValidez));
	}

	public String getCodigoVendedor() {
		return codigoVendedor;
	}

	public void setCodigoVendedor(String codigoVendedor) {
		this.codigoVendedor = codigoVendedor;
		addContent(new Element("CodigoVendedor").setText(this.codigoVendedor));
	}

	public String getFechaDeFinalizacion() {
		return fechaDeFinalizacion;
	}

	public void setFechaDeFinalizacion(String fechaDeFinalizacion) {
		this.fechaDeFinalizacion = fechaDeFinalizacion;
		addContent(new Element("FechaFinalizacion").setText(this.fechaDeFinalizacion));
	}

	public String getFechaDeInicio() {
		return fechaDeInicio;
	}

	public void setFechaDeInicio(String fechaDeInicio) {
		this.fechaDeInicio = fechaDeInicio;
		addContent(new Element("FechaInicio").setText(this.fechaDeInicio));
	}

	public String getFechaDeInstalacion() {
		return fechaDeInstalacion;
	}

	public void setFechaDeInstalacion(String fechaDeInstalacion) {
		this.fechaDeInstalacion = fechaDeInstalacion;
		addContent(new Element("FechaInstalacion").setText(this.fechaDeInstalacion));
	}

	public String getNombreDelEvento() {
		return nombreDelEvento;
	}

	public void setNombreDelEvento(String nombreDelEvento) {
		this.nombreDelEvento = nombreDelEvento;
		addContent(new Element("NombreEvento").setText(this.nombreDelEvento));
	}

	public double getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(double cotizacion) {
		this.cotizacion = cotizacion;
		//addContent(new Element("Cotizacion").setText(this.cotizacion));
	}

	public String getNumeroDePresupuesto() {
		return numeroDePresupuesto;
	}

	public void setNumeroDePresupuesto(String numeroDePresupuesto) {
		this.numeroDePresupuesto = numeroDePresupuesto;
		addContent(new Element("NumeroPpto").setText(this.numeroDePresupuesto));
	}

	public String getObservacionesDelCliente() {
		return observacionesDelCliente;
	}

	public void setObservacionesDelCliente(String observacionesDelCliente) {
		this.observacionesDelCliente = observacionesDelCliente;
		addContent(new Element("ObservacionCliente").setText(this.observacionesDelCliente));
	}

	public String getObservacionesDelEvento() {
		return observacionesDelEvento;
	}

	public void setObservacionesDelEvento(String observacionesDelEvento) {
		this.observacionesDelEvento = observacionesDelEvento;
		addContent(new Element("ObservacionEvento").setText(this.observacionesDelEvento));
	}

	public String getResponsableEmail() {
		return responsableEmail;
	}

	public void setResponsableEmail(String responsableEmail) {
		this.responsableEmail = responsableEmail;
		addContent(new Element("ResponsableEmail").setText(this.responsableEmail));
	}

	public String getResponsableEvento() {
		return responsableEvento;
	}

	public void setResponsableEvento(String responsableEvento) {
		this.responsableEvento = responsableEvento;
		addContent(new Element("ResponsableEvento").setText(this.responsableEvento));
	}

	public String getResponsableNextelFlota() {
		return responsableNextelFlota;
	}

	public void setResponsableNextelFlota(String responsableNextelFlota) {
		this.responsableNextelFlota = responsableNextelFlota;
		addContent(new Element("ResponsableNextelFlota").setText(this.responsableNextelFlota));
	}

	public String getResponsableNextelId() {
		return responsableNextelId;
	}

	public void setResponsableNextelId(String responsableNextelId) {
		this.responsableNextelId = responsableNextelId;
		addContent(new Element("ResponsableNextelId").setText(this.responsableNextelId));
	}

	public String getResponsableTel() {
		return responsableTel;
	}

	public void setResponsableTel(String responsableTel) {
		this.responsableTel = responsableTel;
		addContent(new Element("ResponsableTel").setText(this.responsableTel));
	}

	public String getTotalDePersonas() {
		return totalDePersonas;
	}

	public void setTotalDePersonas(String totalDePersonas) {
		this.totalDePersonas = totalDePersonas;
		addContent(new Element("TotalPersonas").setText(this.totalDePersonas));
	}

	public AgregadosXML getAgregados() {
		return agregados;
	}

	public void setAgregados(AgregadosXML agregados) {
		this.agregados = agregados;		
		addContent(agregados);
	}

	public SalasXML[] getSalas() {
		return salas;
	}

	public void setSalas(SalasXML[] salas) {
		this.salas = salas;
	}

	public AdelantoXML getAdelanto() {
		return adelanto;
	}

	public void setAdelanto(AdelantoXML adelanto) {
		this.adelanto = adelanto;
		addContent(adelanto);
	}

	public ClienteFacturacionXML getClienteFacturacion() {
		return clienteFacturacion;
	}

	public void setClienteFacturacion(ClienteFacturacionXML clienteFacturacion) {
		this.clienteFacturacion = clienteFacturacion;
		addContent(clienteFacturacion);
	}

	public ContactoClienteXML getContactoCliente() {
		return contactoCliente;
	}

	public void setContactoCliente(ContactoClienteXML contactoCliente) {
		this.contactoCliente = contactoCliente;
		addContent(contactoCliente);
	}

	public ContactoLugarXML getContactoLugar() {
		return contactoLugar;
	}

	public void setContactoLugar(ContactoLugarXML contactoLugar) {
		this.contactoLugar = contactoLugar;
		addContent(contactoLugar);
	}

	public EstadoXML getEstado() {
		return estado;
	}

	public void setEstado(EstadoXML estado) {
		this.estado = estado;
		addContent(estado);
	}

	public PagoXML getPago() {
		return pago;
	}

	public void setPago(PagoXML pago) {
		this.pago = pago;
		addContent(pago);
	}

	public RentabilidadXML getRentabilidad() {
		return rentabilidad;
	}

	public void setRentabilidad(RentabilidadXML rentabilidad) {
		this.rentabilidad = rentabilidad;
		addContent(rentabilidad);
	}

	public GastosContratacionXML[] getGastosContratacion() {
		return gastosContratacion;
	}

	public void setGastosContratacion(GastosContratacionXML[] gastosContratacion) {
		this.gastosContratacion = gastosContratacion;
		//addContent(gastosContratacion);
	}
	
}
