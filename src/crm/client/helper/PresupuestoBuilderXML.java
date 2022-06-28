package crm.client.helper;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.math.NumberUtils;

import crm.client.util.DateConverter;
import crm.client.validacion.ErrorList;
import crm.client.validacion.ErrorMessageBuilder;
import crm.gui.pantalla.solapa.ClientePanel;
import crm.gui.pantalla.solapa.EventoPanel;
import crm.gui.pantalla.solapa.FacturacionPanel;
import crm.gui.pantalla.solapa.LugarEventoPanel;
import crm.gui.pantalla.solapa.MainPanelComercial;
import crm.gui.pantalla.solapa.RentabilidadPanel;
import crm.gui.pantalla.solapa.ReportesPanel;
import crm.gui.pantalla.solapa.SalaPanel;
import crm.gui.pantalla.solapa.SubcontratadosPanel;
import crm.gui.pantalla.solapa.gastos.GastosAsistentes;
import crm.gui.pantalla.solapa.gastos.GastosHoteleria;
import crm.gui.pantalla.solapa.gastos.GastosOperadores;
import crm.gui.pantalla.solapa.gastos.GastosRepresentacion;
import crm.gui.pantalla.solapa.gastos.GastosVarios;
import crm.gui.pantalla.solapa.gastos.GastosViaticos;
import crm.gui.tablerenderer.salas.SalaServicioItem;
import crm.gui.tablerenderer.salas.SalaServiciosTableModel;
import crm.libraries.abm.entities.Cliente;
import crm.libraries.abm.entities.ClienteFacturacion;
import crm.libraries.abm.entities.PptoEstadoActual;
import crm.libraries.abm.entities.Ppto_Adelanto;
import crm.libraries.abm.entities.Ppto_Agregado;
import crm.libraries.abm.entities.Ppto_Contacto_Lugar;
import crm.libraries.abm.entities.Ppto_GastoSC;
import crm.libraries.abm.entities.Ppto_Sala;
import crm.libraries.abm.entities.Ppto_Sala_Agregado;
import crm.libraries.abm.entities.Ppto_Sala_Servicio;
import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.abm.entities.PrtPptoFPago;
import crm.libraries.abm.entities.PrtPptoHeader;
import crm.libraries.abm.entities.PrtPptoPeriodo;
import crm.libraries.abm.entities.PrtPptoTipoPresupuesto;
import crm.libraries.abm.entities.PrtPptoValidez;
import crm.libraries.abm.entities.Servicio;
import crm.libraries.abm.entities.TipoLugarEvento;
import crm.libraries.abm.entities.TipoUniforme;
import crm.libraries.abm.helper.AdelantoHelper;
import crm.libraries.abm.helper.AgregadoHelper;
import crm.libraries.abm.helper.AgregadoSalaHelper;
import crm.libraries.abm.helper.ContactoHelper;
import crm.libraries.abm.helper.ContactoLugarHelper;
import crm.libraries.abm.helper.DescDetalladaServicioHelper;
import crm.libraries.abm.helper.EstadoActualHelper;
import crm.libraries.abm.helper.FacturacionHelper;
import crm.libraries.abm.helper.GastoContratHelper;
import crm.libraries.abm.helper.PagoHelper;
import crm.libraries.abm.helper.PresupuestoHelper;
import crm.libraries.abm.helper.RentabilidadHelper;
import crm.libraries.abm.helper.SalaHelper;
import crm.libraries.abm.helper.ServicioHelper;

public class PresupuestoBuilderXML {
private static final String PRESUPUESTO_INACTIVO = "N";
private static final String PRESUPUESTO_ACTIVO = "S";
	
	private PresupuestoHelper p;
	//private PantallaNuevo pn;
	private ClientePanel clientePanel;
	private EventoPanel eventoPanel;
	private LugarEventoPanel lugarEventoPanel;
	private FacturacionPanel facturacionPanel;
	private RentabilidadPanel rentabilidadPanel;
	private ReportesPanel reportesPanel;
	private MainPanelComercial mainPanel;
	private SubcontratadosPanel subcontSalasPanel;
	//private GastosSubcontratacionServiciosSalas subcontSalasPanel;
	//private GastosSubcontratacionesGrales subcontGralesPanel;
	private GastosAsistentes gastosAsistentesPanel;
	private GastosOperadores gastosOperadoresPanel;
	private GastosHoteleria gastosHoteleriaPanel;
	private GastosRepresentacion gastosRepresentacionPanel;
	private GastosVarios gastosVariosPanel;
	private GastosViaticos gastosViaticosPanel;

	private boolean valid;
	
	public PresupuestoBuilderXML(MainPanelComercial pantallaPpal){
		clientePanel = pantallaPpal.getPanelCliente();
		eventoPanel = pantallaPpal.getPanelEvento();
		lugarEventoPanel = pantallaPpal.getPanelLugarEvento();
		facturacionPanel = pantallaPpal.getPanelFacturacion();
		rentabilidadPanel = pantallaPpal.getPanelRentabilidad();
		reportesPanel = pantallaPpal.getPanelReportes();
		mainPanel = pantallaPpal;
		subcontSalasPanel = pantallaPpal.getPanelGastosSubcontGral();
		gastosAsistentesPanel = pantallaPpal.getPanelGastosAsistentes();
		gastosOperadoresPanel = pantallaPpal.getPanelGastosOperadores();
		gastosHoteleriaPanel = pantallaPpal.getPanelGastosHoteleria();
		gastosRepresentacionPanel = pantallaPpal.getPanelGastosRepresentacion();
		gastosVariosPanel = pantallaPpal.getPanelGastosVarios();
		gastosViaticosPanel = pantallaPpal.getPanelGastosViaticos(); 
        
	}

	public void run() {
		buildPresupuesto();
	}
	
	private void buildPresupuesto(){
		
		p = new PresupuestoHelper();
		p.setNumeroDePresupuesto(mainPanel.getNumeroPpto());
		p.setCodigoUsuario(mainPanel.getCodigoUsuario());
		if(UserRolesUtil.isVendedor(mainPanel.getUsuario()) || UserRolesUtil.isSupervisor(mainPanel.getUsuario()))
			p.setCodigoVendedor(mainPanel.getCodigoVendedor());
		p.setFechaDeInicio(mainPanel.getFechaInicio());
		p.setFechaDeFinalizacion(mainPanel.getFechaFinalizacion());
		p.setCodigoReferencia(mainPanel.getCodigoReferencia());
		
		if(clientePanel != null){
			p.setCodigoCliente(clientePanel.getCodigoCliente());
			p.setObservacionesDelCliente(clientePanel.getObservacionesClientes());
			p.setResponsableEvento(clientePanel.getResponsableEvento());
			p.setResponsableEmail(clientePanel.getResponsableEmail());
			p.setResponsableTel(clientePanel.getResponsableTel());
			if(!clientePanel.getResponsableNextelFlota().equals(""))
				p.setResponsableNextelFlota(clientePanel.getResponsableNextelFlota());
			else p.setResponsableNextelFlota(null);
			if(!clientePanel.getResponsableNextelId().equals(""))
				p.setResponsableNextelId(clientePanel.getResponsableNextelId());
			else p.setResponsableNextelId(null);
		}
		else if(mainPanel.getPresupuesto() != null && !mainPanel.getPresupuesto().isNew()){
			p.setCodigoCliente(mainPanel.getPresupuesto().getCliente().getCodigo());
			p.setObservacionesDelCliente(mainPanel.getPresupuesto().getObservacionesDelCliente());
			p.setResponsableEvento(mainPanel.getPresupuesto().getResponsableEvento());
			p.setResponsableEmail(mainPanel.getPresupuesto().getResponsableEmail());
			p.setResponsableTel(mainPanel.getPresupuesto().getResponsableTel());
			p.setResponsableNextelFlota(mainPanel.getPresupuesto().getResponsableNextelFlota());
			p.setResponsableNextelId(mainPanel.getPresupuesto().getResponsableNextelId());
		}
		
		if(eventoPanel != null){
			p.setFechaDeInstalacion(eventoPanel.getFechaInstalacion());
			p.setCodigoTipoDeEvento(eventoPanel.getCodigoTipoEvento());
			p.setCodigoTipoDeLugarDelEvento(eventoPanel.getCodigoTipoLugar());
			p.setCodigoUniforme(eventoPanel.getCodigoUniforme());
			p.setTotalDePersonas(eventoPanel.getCantPersonasEvento());
			p.setNombreDelEvento(eventoPanel.getNombreDelEvento());
			p.setObservacionesDelEvento(eventoPanel.getObservacionesDelEvento());
		}
		else if(mainPanel.getPresupuesto() != null && !mainPanel.getPresupuesto().isNew()){
			p.setFechaDeInstalacion(mainPanel.getPresupuesto().getFechaDeInstalacion());
			if(mainPanel.getPresupuesto().getTipoDeEvento() != null)
				p.setCodigoTipoDeEvento(mainPanel.getPresupuesto().getTipoDeEvento().getCodigo());
			if(mainPanel.getPresupuesto().getTipoDeLugarDelEvento() != null)
				p.setCodigoTipoDeLugarDelEvento(mainPanel.getPresupuesto().getTipoDeLugarDelEvento().getCodigo());
			if(mainPanel.getPresupuesto().getUniforme() != null)
				p.setCodigoUniforme(mainPanel.getPresupuesto().getUniforme().getCodigo());			
			p.setTotalDePersonas(mainPanel.getPresupuesto().getTotalDePersonas());
			p.setNombreDelEvento(mainPanel.getPresupuesto().getNombreDelEvento());
			p.setObservacionesDelEvento(mainPanel.getPresupuesto().getObservacionesDelEvento());
		}
		
		p.setActivo(PRESUPUESTO_ACTIVO);
		
		if(lugarEventoPanel != null){
			p.setCodigoLugarDelEvento(lugarEventoPanel.getCodLugar());
		}
		else if(mainPanel.getPresupuesto() != null && !mainPanel.getPresupuesto().isNew()){
			p.setCodigoLugarDelEvento(mainPanel.getPresupuesto().getLugarDelEvento().getCodigo());
		}
		
		if(mainPanel.getPresupuesto().getCotizacion() != null)
			p.setCotizacion(mainPanel.getPresupuesto().getCotizacion());
		
		if(reportesPanel != null){
			p.setCotizacion(Double.valueOf(reportesPanel.getCotizacion()));
			p.setCodigoEncabezado(reportesPanel.getCodigoEncabezado());
			p.setCodigoFormaPago(reportesPanel.getCodigoFPago());
			p.setCodigoPeriodo(reportesPanel.getCodigoPeriodo());
			p.setCodigoTipoPpto(reportesPanel.getCodigoTipoPpto());
			p.setCodigoValidez(reportesPanel.getCodigoValidez());
			p.setCodigoMoneda(reportesPanel.getCodigoMoneda());
			p.setCodigoCancelacion(reportesPanel.getCodigoCancelacion());
			p.setCodigoCondPago(reportesPanel.getCodigoCondPago());		
		}
		else if(mainPanel.getPresupuesto() != null && !mainPanel.getPresupuesto().isNew()){			
			if(mainPanel.getPresupuesto().getEncabezadoPpto() != null)
				p.setCodigoEncabezado(mainPanel.getPresupuesto().getEncabezadoPpto().getCodigo());
			if(mainPanel.getPresupuesto().getFormaPagoPpto() != null)
				p.setCodigoFormaPago(mainPanel.getPresupuesto().getFormaPagoPpto().getCodigo());
			if(mainPanel.getPresupuesto().getPeriodoPpto() != null)
				p.setCodigoPeriodo(mainPanel.getPresupuesto().getPeriodoPpto().getCodigo());
			if(mainPanel.getPresupuesto().getTipoPpto() != null)
				p.setCodigoTipoPpto(mainPanel.getPresupuesto().getTipoPpto().getCodigo());
			if(mainPanel.getPresupuesto().getValidezPpto() != null)
				p.setCodigoValidez(mainPanel.getPresupuesto().getValidezPpto().getCodigo());
			if(mainPanel.getPresupuesto().getMoneda() != null)
				p.setCodigoMoneda(mainPanel.getPresupuesto().getMoneda().getCodigo());
			if(mainPanel.getPresupuesto().getCancelacionPpto() != null)
				p.setCodigoCancelacion(mainPanel.getPresupuesto().getCancelacionPpto().getCodigo());
			if(mainPanel.getPresupuesto().getCondPagoPpto() != null)
				p.setCodigoCondPago(mainPanel.getPresupuesto().getCondPagoPpto().getCodigo());	
			if(mainPanel.getPresupuesto().getCotizacion() != null)
				p.setCotizacion(mainPanel.getPresupuesto().getCotizacion());
		}
			
		//----GASTOS----------------------------------------------------------------------
		
		 /*if(subcontSalasPanel != null)
			p.setGastosContratHelper(subcontSalasPanel.getGastos());
		else if(mainPanel.getPresupuesto() != null && !mainPanel.getPresupuesto().isNew()){
			
			Set gastos = mainPanel.getPresupuesto().getGastosSC();
			if(gastos != null){
				Iterator it = gastos.iterator();
				GastoContratHelper[] gastoHelper = new GastoContratHelper[gastos.size()];
				int i=0;
				while (it.hasNext()) {
					Ppto_GastoSC gasto = (Ppto_GastoSC) it.next();
					gastoHelper[i] = new GastoContratHelper();
					gastoHelper[i].setPpto_GastoSC(gasto);
				}
				p.setGastosContratHelper(gastoHelper);
			}
				
			
		}*/if(subcontSalasPanel != null){
			p.setGastosSubcontratados(subcontSalasPanel.getGastos());
		}
		else if(mainPanel.getPresupuesto().getNumeroDePresupuesto()>0){
			Set gastos = mainPanel.getPresupuesto().getGastosSC();
			Ppto_GastoSC[] gastosArray = new Ppto_GastoSC[gastos.size()];
			if(gastos != null){
				Iterator it = gastos.iterator();
				int i =0;
				while (it.hasNext()) {
					Ppto_GastoSC gasto = (Ppto_GastoSC) it.next();					
					gastosArray[i].setDetalle(gasto.getDetalle());
					gastosArray[i].setPrecio(gasto.getPrecio());
					gastosArray[i].setProveedor(gasto.getProveedor());
					gastosArray[i].setCosto(gasto.getCosto());
					gastosArray[i].setSala(gasto.getSala());
					gastosArray[i].setCantidad(gasto.getCantidad());
					i++;
				}
			}
			p.setGastosSubcontratados(gastosArray);
		}
		if(gastosOperadoresPanel != null)
			p.setGastosOperador(gastosOperadoresPanel.getGastos());	
		if(gastosRepresentacionPanel != null)
			p.setGastosRepresentacion(gastosRepresentacionPanel.getGastos());
		if(gastosAsistentesPanel != null) 
			p.setGastosAsistentes(gastosAsistentesPanel.getGastos());
		if(gastosViaticosPanel != null) 
			p.setGastosViaticos(gastosViaticosPanel.getGastos());
		if(gastosHoteleriaPanel != null)
			p.setGastosHoteleria(gastosHoteleriaPanel.getGastos());
		if(gastosVariosPanel != null)
			p.setGastosVarios(gastosVariosPanel.getGastos());
		 
		p.setAgregado(getAgregados());
		//----FACTURACION----------------------------------------------------------------------
		p.setFacturacion(getFacturacion());
		p.setPago(buildPago());
		//----RENTABILIDAD----------------------------------------------------------------------
		p.setRentabilidad(getRentabilidad());
		//----ADELANTO------------------------------------------------------------------------
		p.setAdelanto(getAdelanto());
		p.setEstado(getEstado());
		p.setContacto(getContacto());
		p.setContactoLugar(getContactoLugar());
		p.setSalas(buildSalas2());
		
	}
	
	/*public void buildSeguimiento(){
		if (p != null)
			p.setSeguimiento(getSeguimiento());
	}*/
	
    /**
     * Valida los datos ingresados en el formulario
     * @return
     */
	private boolean validatePresupuesto(){
	    
	    ErrorList errors = mainPanel.validateRequiredFields();
	    
	    if (errors != null && !errors.isEmpty()){
	        String msg = "Los siguientes errores deben ser corregidos para poder crear el Presupuesto";
	        ErrorMessageBuilder.createErrorMessage(mainPanel, msg, errors);
	    }
	    
	    return errors.isEmpty();
	}
    
	private ContactoHelper getContacto(){
		ContactoHelper contacto = new ContactoHelper();
		
		if(clientePanel!=null){
			if(clientePanel.getCodigoContacto() != null)
				contacto.setCodContacto(Integer.parseInt(clientePanel.getCodigoContacto()));		
		}
		else if(mainPanel.getPresupuesto().getContacto() != null && !mainPanel.getPresupuesto().isNew()){
			if(mainPanel.getPresupuesto().getContacto().getCodContacto() != null)
				contacto.setCodContacto(Integer.parseInt(mainPanel.getPresupuesto().getContacto().getCodContacto()));	
		}
		return contacto;
	}
	
	private AgregadoHelper getAgregados(){
		AgregadoHelper agreg = new AgregadoHelper();
		
		if(eventoPanel!=null){
			
			if (eventoPanel.getCodigoModoIngreso() != null)
				agreg.setModoIngreso(Integer.valueOf(eventoPanel.getCodigoModoIngreso()));
			if (eventoPanel.getCodigoSeguridadIngreso() != null)
				agreg.setSeguridadIngreso(Integer.valueOf(eventoPanel.getCodigoSeguridadIngreso()));
			if(eventoPanel.getCodigoCategoriaEvento() != null)
				agreg.setCategoriaEvento(Integer.valueOf(eventoPanel.getCodigoCategoriaEvento()));
		}
		else if(mainPanel.getPresupuesto().getAgregado() != null && !mainPanel.getPresupuesto().isNew()){
			if (mainPanel.getPresupuesto().getAgregado().getModoIngreso() != null)
				agreg.setModoIngreso(Integer.valueOf(mainPanel.getPresupuesto().getAgregado().getModoIngreso()));
			if (mainPanel.getPresupuesto().getAgregado().getSeguridadIngreso() != null)
				agreg.setSeguridadIngreso(Integer.valueOf(mainPanel.getPresupuesto().getAgregado().getSeguridadIngreso()));
			if(mainPanel.getPresupuesto().getAgregado().getCategoriaEvento() != null)
				agreg.setCategoriaEvento(Integer.valueOf(mainPanel.getPresupuesto().getAgregado().getCategoriaEvento()));
		}
		return agreg;
	}
	
	private AdelantoHelper getAdelanto(){
		AdelantoHelper adelanto = new AdelantoHelper();
		
		if(facturacionPanel!=null){
			if (facturacionPanel.getAdelantoValor() != null)
				adelanto.setValor(Double.valueOf(facturacionPanel.getAdelantoValor()));
			if (facturacionPanel.getAdelantoPorcentaje() != null)
				adelanto.setPorcentaje(Integer.valueOf(facturacionPanel.getAdelantoPorcentaje()));
		
		}
		else if(mainPanel.getPresupuesto().getAdelanto() != null && !mainPanel.getPresupuesto().isNew()){
			if (mainPanel.getPresupuesto().getAdelanto().getValor() != null)
				adelanto.setValor(Double.valueOf(mainPanel.getPresupuesto().getAdelanto().getValor()));
			if (mainPanel.getPresupuesto().getAdelanto().getPorcentaje() != null)
				adelanto.setPorcentaje(Integer.valueOf(mainPanel.getPresupuesto().getAdelanto().getPorcentaje()));
		}
		return adelanto;
	}
	
	private ContactoLugarHelper getContactoLugar(){
		ContactoLugarHelper contacto = new ContactoLugarHelper();
		
		if(lugarEventoPanel!=null){
			if(lugarEventoPanel.getCodigoContacto() != null)
				contacto.setCodContacto(Integer.parseInt(lugarEventoPanel.getCodigoContacto()));
		}
		else if(mainPanel.getPresupuesto().getContactoLugar() != null && !mainPanel.getPresupuesto().isNew()){
			if(mainPanel.getPresupuesto().getContactoLugar().getCodContacto() != null)
				contacto.setCodContacto(Integer.parseInt(mainPanel.getPresupuesto().getContactoLugar().getCodContacto()));
		}
		
		return contacto;
	}
	
	private FacturacionHelper getFacturacion(){
		FacturacionHelper fact = new FacturacionHelper();
		
		if(facturacionPanel!=null){
			if (facturacionPanel.getClienteFactElegido() != null)
				fact.setCodCliente(Integer.parseInt(facturacionPanel.getClienteFactElegido().getCodigo()));
		}
		else if(mainPanel.getPresupuesto().getFacturacion() != null && !mainPanel.getPresupuesto().isNew()){
			if (mainPanel.getPresupuesto().getFacturacion().getCodCliente() != null)
				fact.setCodCliente(Integer.parseInt(mainPanel.getPresupuesto().getFacturacion().getCodCliente()));
		}
		return fact;
	}
	
	private PagoHelper buildPago(){
		PagoHelper pago = new PagoHelper();
		
		if(facturacionPanel!=null){
			if (facturacionPanel.getMedioPago() != null)
				pago.setCodMedioPago(Integer.parseInt(facturacionPanel.getMedioPago()));
			if (facturacionPanel.getCondicionPago() != null)
				pago.setCodCondicionPago(Integer.parseInt(facturacionPanel.getCondicionPago()));
		}
		else if(mainPanel.getPresupuesto().getPago() != null && !mainPanel.getPresupuesto().isNew()){
			if (mainPanel.getPresupuesto().getPago().getCodMedioPago() != null)
				pago.setCodMedioPago(Integer.parseInt(mainPanel.getPresupuesto().getPago().getCodMedioPago()));
			if (mainPanel.getPresupuesto().getPago().getCodCondicionPago() != null)
				pago.setCodCondicionPago(Integer.parseInt(mainPanel.getPresupuesto().getPago().getCodCondicionPago()));
		}
		return pago;
	}
	
	private RentabilidadHelper getRentabilidad(){
		RentabilidadHelper rent = new RentabilidadHelper();
		
		if(rentabilidadPanel!=null){
			rent.setFacturacionOriginal(rentabilidadPanel.getTotalesPorSalaBefore());
			rent.setFacturacionExtra(rentabilidadPanel.getTotalesPorSalaAfter());
			rent.setCostoOperativo(rentabilidadPanel.getGastoOperativo());
			rent.setGastosAsistentes(rentabilidadPanel.getGastoAsistentes());
			rent.setGastosContrataciones(rentabilidadPanel.getGastoSubContrataciones());
			rent.setGastosOperadores(rentabilidadPanel.getGastoOperadores());
			rent.setGastosOtros(rentabilidadPanel.getGastoOtros());
			rent.setComisionesLugar(rentabilidadPanel.getComisionesLugar());
			rent.setComisionesTerceros(rentabilidadPanel.getComisionesReferencia());
		}
		else if(mainPanel.getPresupuesto().getRentabilidad() != null && !mainPanel.getPresupuesto().isNew()){
			rent.setFacturacionOriginal(Double.valueOf(mainPanel.getPresupuesto().getRentabilidad().getFacturacionOriginal()));
			rent.setFacturacionExtra(Double.valueOf(mainPanel.getPresupuesto().getRentabilidad().getFacturacionExtra()));
			rent.setCostoOperativo(Double.valueOf(mainPanel.getPresupuesto().getRentabilidad().getCostoOperativo()));
			rent.setGastosAsistentes(Double.valueOf(mainPanel.getPresupuesto().getRentabilidad().getGastosAsistentes()));
			rent.setGastosContrataciones(Double.valueOf(mainPanel.getPresupuesto().getRentabilidad().getGastosContrataciones()));
			rent.setGastosOperadores(Double.valueOf(mainPanel.getPresupuesto().getRentabilidad().getGastosOperadores()));
			rent.setGastosOtros(Double.valueOf(mainPanel.getPresupuesto().getRentabilidad().getGastosOtros()));
			rent.setComisionesLugar(Double.valueOf(mainPanel.getPresupuesto().getRentabilidad().getComisionesLugar()));
			rent.setComisionesTerceros(Double.valueOf(mainPanel.getPresupuesto().getRentabilidad().getComisionesTerceros()));
		}
		return rent;
	}
	
	/**
	 * Crea un objeto representando el estado de la orden.
	 * @return
	 */
	private EstadoActualHelper getEstado(){
		EstadoActualHelper estado = new EstadoActualHelper();
		
		estado.setCancelado(mainPanel.getCancelado());
		estado.setCobrado(mainPanel.getCobrado());
		estado.setConfirmado(mainPanel.getConfirmado());
		estado.setFacturado(mainPanel.getFacturado());
		estado.setOrdenDeCompra(mainPanel.getOrdenDeCompra());
		estado.setOrdenDeFacturacion(mainPanel.getOrdenDeFacturacion());
		estado.setOrdenDeServicio(mainPanel.getOrdenDeServicio());
		estado.setRechazado(mainPanel.getRechazado());
		if(facturacionPanel!=null){
			estado.setAdelanto(facturacionPanel.getAdelanto());
			estado.setAdelantado(facturacionPanel.getAdelantado());
		}
		else if(mainPanel.getPresupuesto().getEstadoActual() != null && !mainPanel.getPresupuesto().isNew()){
			estado.setAdelanto(mainPanel.getPresupuesto().getEstadoActual().getAdelanto());
			estado.setAdelantado(mainPanel.getPresupuesto().getEstadoActual().getAdelantado());
		}
		return estado;
	}
	
	private int getMaxOrden(List<SalaPanel> rows){
		int max=0;
		Iterator<SalaPanel> it = rows.iterator();
		while(it.hasNext()){
			String nroOrden = it.next().getNroOrden() ;
			if((nroOrden != null) && (Integer.parseInt(nroOrden) > max)){
				max = Integer.parseInt(nroOrden);
			}
		}
		return max;
	}
	
	private SalaHelper[] buildSalas2(){	

		List<SalaPanel> salasPanels = mainPanel.getPanelesSala();
		SalaHelper[] salashelpers = new SalaHelper[mainPanel.getSalasTree().getChildCount()];	
		
		int i=0;
		boolean esta=false;
		int max = getMaxOrden(salasPanels);
		for(int j=0;j<mainPanel.getSalasTree().getChildCount();j++){
			for (SalaPanel sala : salasPanels) {
				if(sala.getModel().getNombreSala().equals(mainPanel.getSalasTree().getChildAt(j).toString())){
					esta=true;
					SalaHelper sh = new SalaHelper();
					
					if(sala.getNroOrden() == null){
						sh.setOrden(max+1);
						max++;
					}
					else
						sh.setOrden(Integer.parseInt(sala.getNroOrden()));			
					
					sh.setCodigoSalaLugar(sala.getModel().getCodigoSala());
					if(eventoPanel != null){
						sh.setFechaDeInstalacion(eventoPanel.getFechaInstalacion());
					}
					else if(mainPanel.getPresupuesto() != null && !mainPanel.getPresupuesto().isNew()){
						sh.setFechaDeInstalacion(mainPanel.getPresupuesto().getFechaDeInstalacion());
					}
					sh.setFechaDeInicio(sala.getModel().getFechaDeInicio()+" "+sala.getModel().getHoraDeInicio());
					sh.setFechaDeFinalizacion(sala.getModel().getFechaDeFinalizacion()+" "+sala.getModel().getHoraDeFinalizacion());
					if (!sala.getModel().getTotalDePersonas().equals(""))
						sh.setTotalDePersonas(sala.getModel().getTotalDePersonas());
					else sh.setTotalDePersonas(null); 
					sh.setObservaciones(sala.getObservaciones());
					sh.setServicios(buildServicios(sala.getTableModel()));
					
					if (sala.getModel().getFechaDesarme() != null)
						sh.setFechaDesarme(sala.getModel().getFechaDesarme()+" "+sala.getModel().getHoraDesarme());
					else sh.setFechaDesarme(null);
					if(sala.getModel().getCodigoTipoArmado() != null)	
						sh.setTipoArmado(sala.getModel().getCodigoTipoArmado());
					if(sala.getModel().getFechaPrueba() != null)
						sh.setFechaPrueba(sala.getModel().getFechaPrueba()+" "+sala.getModel().getHoraPrueba());
					else sh.setFechaPrueba(null);
					
					//sh.setAgregado(buildAgregados(sala));
					
					salashelpers[i++] = sh;
					break;
				}
				else{
					esta=false;
				}
					
			}
			if(!esta){
				SalaHelper sh = new SalaHelper();
				Ppto_Sala salaPpto = mainPanel.getSalaPptoByName(mainPanel.getSalasTree().getChildAt(j).toString());
				if(salaPpto.getOrden() == null){
					sh.setOrden(max+1);
					max++;
				}
				else
					sh.setOrden(Integer.parseInt(salaPpto.getOrden()));			
				
				sh.setCodigoSalaLugar(salaPpto.getSala().getCodigo());
				if(eventoPanel != null){
					sh.setFechaDeInstalacion(eventoPanel.getFechaInstalacion());
				}
				else if(mainPanel.getPresupuesto() != null && !mainPanel.getPresupuesto().isNew()){
					sh.setFechaDeInstalacion(mainPanel.getPresupuesto().getFechaDeInstalacion());
				}
				sh.setFechaDeInicio(salaPpto.getFechaDeInicio());
				sh.setFechaDeFinalizacion(salaPpto.getFechaDeFinalizacion());
				if (!salaPpto.getTotalDePersonas().equals(""))
					sh.setTotalDePersonas(salaPpto.getTotalDePersonas());
				else sh.setTotalDePersonas(null); 
				sh.setObservaciones(salaPpto.getObservaciones());
				sh.setServicios(buildServiciosFromPpto(salaPpto.getServicios()));
				//sh.setAgregado(buildAgregadosFromPpto(salaPpto.getAgregados()));
				
				if (salaPpto.getFechaDesarme() != null)
					sh.setFechaDesarme(salaPpto.getFechaDesarme());
				else sh.setFechaDesarme("2008-01-01 00:00:00");
				
				if(salaPpto.getTipoArmado()!= null && salaPpto.getTipoArmado().getCodigo() != null)	
					sh.setTipoArmado(salaPpto.getTipoArmado().getCodigo());
				else sh.setTipoArmado(null);
				if(salaPpto.getFechaPrueba() != null)
					sh.setFechaPrueba(salaPpto.getFechaPrueba());
				else sh.setFechaPrueba("2008-01-01 00:00:00");
				
				salashelpers[i++] = sh;
			}
		}
		return salashelpers;
	}
	
	private ServicioHelper[] buildServiciosFromPpto(Set <Ppto_Sala_Servicio> servicios) {

		ServicioHelper[] servicioshelpers = new ServicioHelper[servicios.size()];
		int i = 0;
		for (Ppto_Sala_Servicio servicio : servicios) {

			ServicioHelper sh = new ServicioHelper();
			

			sh.setOrden(Integer.parseInt(servicio.getOrden()));
			sh.setCantidad(Integer.parseInt(servicio.getCantidad()));
			sh.setCodigoServicio(Integer.parseInt(servicio.getServicio().getCodigo()));
			sh.setFechaAlta(DateConverter.convertDateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			sh.setPrecioDeLista(Double.parseDouble(servicio.getPrecioDeLista()));
			//sh.setPrecioDescuento(Double.parseDouble(servicio.getPrecioDescuento()));
			sh.setPrecioDescuento(Double.parseDouble(servicio.getPrecioDeLista())+(Double.parseDouble(servicio.getPrecioDeLista())*Double.parseDouble(servicio.getDescuento())/100));
			
			sh.setCodigoModalidadContratacion(Integer.parseInt(servicio.getModalidad().getCodigo()));
			sh.setDetalle(servicio.getDetalle());
			sh.setDescDetallada(null);
			
			
			sh.setDias(Integer.parseInt(servicio.getDias()));
			sh.setDescuento(Integer.parseInt(servicio.getDescuento()));
			
			//sh.setAccesorios(buildAccesorios(servicio));
						
			servicioshelpers[i++] = sh;
		}
		
		return servicioshelpers;
	}
	
	private ServicioHelper[] buildServicios(SalaServiciosTableModel sala) {
		List<SalaServicioItem> servicios = sala.getRows();
		ServicioHelper[] servicioshelpers = new ServicioHelper[servicios.size()];
		int i = 0;
		int max = sala.getMaxOrden(servicios);
		for (SalaServicioItem servicio : servicios) {

			ServicioHelper sh = new ServicioHelper();
			sh.setTableItemId(servicio.getTableItemId());
			
			if(servicio.getNroOrden() == null){
				sh.setOrden(max+1);
				max++;
			}
			else
				sh.setOrden(Integer.parseInt(servicio.getNroOrden()));
			sh.setCantidad(servicio.getCantidad());
			sh.setCodigoServicio(Integer.parseInt(servicio.getServicioCodigo()));
			if(servicio.getFechaAlta()==null)
				sh.setFechaAlta(DateConverter.convertDateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			else
				sh.setFechaAlta(servicio.getFechaAlta());
			sh.setPrecioDeLista(servicio.getPrecioLista());
			sh.setPrecioDescuento(servicio.getTotal());
			
			
			if (servicio.isSubContratado() && !servicio.isOpcional()){
				sh.setCodigoModalidadContratacion(Servicio.MODALIDAD_CONTRATACION_EXTERNA);
				sh.setDetalle(servicio.getServicio());
				sh.setDescDetallada(buildDescripcionesDetalladas(servicio));
			}
			else if(!servicio.isSubContratado() && !servicio.isOpcional()){
				sh.setCodigoModalidadContratacion(Servicio.MODALIDAD_CONTRATACION_INTERNA);
				sh.setDetalle(null);
				sh.setDescDetallada(null);
			}
			else if(servicio.isSubContratado() && servicio.isOpcional()){
				sh.setCodigoModalidadContratacion(Servicio.MODALIDAD_CONTRATACION_EXTERNA_OPCIONAL);
				sh.setDetalle(servicio.getServicio());
				sh.setDescDetallada(buildDescripcionesDetalladas(servicio));
			}
			else if(!servicio.isSubContratado() && servicio.isOpcional()){
				sh.setCodigoModalidadContratacion(Servicio.MODALIDAD_CONTRATACION_INTERNA_OPCIONAL);
				sh.setDetalle(null);
				sh.setDescDetallada(null);
			}
			
			sh.setDias(servicio.getDias());
			sh.setDescuento(servicio.getDescuento());
			
			//sh.setAccesorios(buildAccesorios(servicio));
						
			servicioshelpers[i++] = sh;
		}
		
		return servicioshelpers;
	}

	
	private DescDetalladaServicioHelper[] buildDescripcionesDetalladas(SalaServicioItem servicio){
		List descripciones = servicio.getDescDetallada();
		DescDetalladaServicioHelper[] descripcioneshelpers = null;
		int i=0;
		if(descripciones != null){
			descripcioneshelpers = new DescDetalladaServicioHelper[descripciones.size()];
			Iterator it = descripciones.iterator();
			while (it.hasNext()) {

				DescDetalladaServicioHelper ah = new DescDetalladaServicioHelper();
				
				ah.setDescripcion((String)it.next());
				
				descripcioneshelpers[i++] = ah;		
			
			}
		}
		return descripcioneshelpers;
	}
	
	public PresupuestoHelper getPresupuesto(){
		return p;
	}
	
	public boolean isValid() {
		return valid;
	}
}
