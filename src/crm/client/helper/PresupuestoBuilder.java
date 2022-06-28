package crm.client.helper;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.lang.math.NumberUtils;

import crm.client.util.DateConverter;
import crm.client.validacion.ErrorList;
import crm.client.validacion.ErrorMessageBuilder;
import crm.gui.pantalla.PantallaBienvenidaVerdadera;
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
import crm.gui.pantalla.solapa.gastos.GastosSubcontratacionesGrales;
import crm.gui.pantalla.solapa.gastos.GastosVarios;
import crm.gui.pantalla.solapa.gastos.GastosViaticos;
import crm.gui.tablerenderer.gastos.GastosOperadoresItem;
import crm.gui.tablerenderer.gastos.GastosRepresentacionItem;
import crm.gui.tablerenderer.horarios.HorariosItem;
import crm.gui.tablerenderer.salas.SalaServicioItem;
import crm.gui.tablerenderer.salas.SalaServiciosTableModel;
import crm.libraries.abm.entities.Cliente;
import crm.libraries.abm.entities.ClienteFacturacion;
import crm.libraries.abm.entities.PptoEstadoActual;
import crm.libraries.abm.entities.Ppto_Adelanto;
import crm.libraries.abm.entities.Ppto_Agregado;
import crm.libraries.abm.entities.Ppto_Contacto;
import crm.libraries.abm.entities.Ppto_Contacto_Lugar;
import crm.libraries.abm.entities.Ppto_GastoAsistentes;
import crm.libraries.abm.entities.Ppto_GastoHoteleria;
import crm.libraries.abm.entities.Ppto_GastoOperador;
import crm.libraries.abm.entities.Ppto_GastoRepresentacion;
import crm.libraries.abm.entities.Ppto_GastoSC;
import crm.libraries.abm.entities.Ppto_GastoVarios;
import crm.libraries.abm.entities.Ppto_GastoViaticos;
import crm.libraries.abm.entities.Ppto_Sala;
import crm.libraries.abm.entities.Ppto_Sala_Agregado;
import crm.libraries.abm.entities.Ppto_Sala_Horario;
import crm.libraries.abm.entities.Ppto_Sala_Servicio;
import crm.libraries.abm.entities.Ppto_Sala_Servicio_Desc_Detallada;
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
import crm.libraries.abm.helper.HorariosHelper;
import crm.libraries.abm.helper.PagoHelper;
import crm.libraries.abm.helper.PresupuestoHelper;
import crm.libraries.abm.helper.RentabilidadHelper;
import crm.libraries.abm.helper.SalaHelper;
import crm.libraries.abm.helper.SeguimientoHelper;
import crm.libraries.abm.helper.ServicioHelper;

public class PresupuestoBuilder {//implements Runnable {
	
	private static final String PRESUPUESTO_INACTIVO = "N";
	
	private PresupuestoHelper p;
	//private PantallaNuevo pn;
	private ClientePanel clientePanel;
	private EventoPanel eventoPanel;
	private LugarEventoPanel lugarEventoPanel;
	private FacturacionPanel facturacionPanel;
	private RentabilidadPanel rentabilidadPanel;
	private ReportesPanel reportesPanel;
	private MainPanelComercial mainPanel;
	//private GastosSubcontratacionServiciosSalas subcontSalasPanel;
	private SubcontratadosPanel subcontGralesPanel;
	private GastosAsistentes gastosAsistentesPanel;
	private GastosOperadores gastosOperadoresPanel;
	private GastosHoteleria gastosHoteleriaPanel;
	private GastosRepresentacion gastosRepresentacionPanel;
	private GastosVarios gastosVariosPanel;
	private GastosViaticos gastosViaticosPanel;
	
	private Presupuesto pptoAnterior;

	private boolean valid;
	
	public PresupuestoBuilder(MainPanelComercial pantallaPpal){
		//pn = pantallaPpal.getPantallaNuevo();
		clientePanel = pantallaPpal.getPanelCliente();
		eventoPanel = pantallaPpal.getPanelEvento();
		lugarEventoPanel = pantallaPpal.getPanelLugarEvento();
		facturacionPanel = pantallaPpal.getPanelFacturacion();
		rentabilidadPanel = pantallaPpal.getPanelRentabilidad();
		reportesPanel = pantallaPpal.getPanelReportes();
		mainPanel = pantallaPpal;
		//subcontSalasPanel = pantallaPpal.getPanelGastosSubcontSalas();
		subcontGralesPanel = pantallaPpal.getPanelGastosSubcontGral();
		gastosAsistentesPanel = pantallaPpal.getPanelGastosAsistentes();
		gastosOperadoresPanel = pantallaPpal.getPanelGastosOperadores();
		gastosHoteleriaPanel = pantallaPpal.getPanelGastosHoteleria();
		gastosRepresentacionPanel = pantallaPpal.getPanelGastosRepresentacion();
		gastosVariosPanel = pantallaPpal.getPanelGastosVarios();
		gastosViaticosPanel = pantallaPpal.getPanelGastosViaticos(); 
        
	}
	
	public PresupuestoBuilder(MainPanelComercial pantallaPpal, Presupuesto presupAnterior){
		pptoAnterior = presupAnterior;
		clientePanel = pantallaPpal.getPanelCliente();
		eventoPanel = pantallaPpal.getPanelEvento();
		lugarEventoPanel = pantallaPpal.getPanelLugarEvento();
		facturacionPanel = pantallaPpal.getPanelFacturacion();
		rentabilidadPanel = pantallaPpal.getPanelRentabilidad();
		reportesPanel = pantallaPpal.getPanelReportes();
		mainPanel = pantallaPpal;
		//subcontSalasPanel = pantallaPpal.getPanelGastosSubcontSalas();
		subcontGralesPanel = pantallaPpal.getPanelGastosSubcontGral();
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
		//valid = true;
		valid = validatePresupuesto();
		if (valid){
		p = new PresupuestoHelper();
			buildCliente();
		
		buildMainPanel();
		buildEvento();
		buildLugar();
		buildReportes();
		
		p.setActivo(PRESUPUESTO_INACTIVO);			
		
		p.setEstado(getEstado());
		p.setContacto(getContacto());
		p.setContactoLugar(getContactoLugar());
		p.setSalas(buildSalas2());
		
		//----GASTOS----------------------------------------------------------------------

		//buildSubcontratados();
		//buildGastosAsistentes();
		//buildGastosHoteleria();
		buildGastosVarios();
		//buildGastosViaticos();
		buildGastosRepresentacion();
		
		//-----------------------------------------------------------------------------------
		p.setAgregado(getAgregados());
		//----FACTURACION----------------------------------------------------------------------
		p.setFacturacion(getFacturacion());
		p.setPago(buildPago());
		//----RENTABILIDAD----------------------------------------------------------------------
		p.setRentabilidad(getRentabilidad());
		//----ADELANTO------------------------------------------------------------------------
		p.setAdelanto(getAdelanto());}
	}
	
	private void buildSubcontratados(){
		if(subcontGralesPanel != null){
			//p.setGastosSubcontratados(subcontGralesPanel.getGastos());
			p.setGastosContratHelper(subcontGralesPanel.getGastos2());
		}
		else if(pptoAnterior.getNumeroDePresupuesto()>0){
			Set gastos = pptoAnterior.getGastosSC();
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
					gastosArray[i].setEstado(gasto.getEstado());
					gastosArray[i].setPpto_Sala_Servicio(gasto.getPpto_Sala_Servicio());
					i++;
				}
			}
			p.setGastosSubcontratados(gastosArray);
		}
	}
	
	public void buildGastosOperadores(){
		if(gastosOperadoresPanel != null)
			p.setGastosOperador(gastosOperadoresPanel.getGastos());
		else if(pptoAnterior.getNumeroDePresupuesto()>0){			
			Set gastos = pptoAnterior.getGastosOperador();
			Ppto_GastoOperador[] gastosArray = new Ppto_GastoOperador[gastos.size()];
			if(gastos != null){
				Iterator it = gastos.iterator();
				int i =0;
				while (it.hasNext()) {
					Ppto_GastoOperador gasto = (Ppto_GastoOperador) it.next();					
					gastosArray[i].setOperador(gasto.getOperador());
					gastosArray[i].setCargo(gasto.getCargo());
					gastosArray[i].setHorario(gasto.getHorario());
					gastosArray[i].setCosto(gasto.getCosto());
					i++;
				}
			}
			p.setGastosOperador(gastosArray);
		}
	}
	
	public void buildGastosRepresentacion(){
		if(gastosRepresentacionPanel != null)
			p.setGastosRepresentacion(gastosRepresentacionPanel.getGastos());
		else if(pptoAnterior.getNumeroDePresupuesto()>0){			
			Set gastos = pptoAnterior.getGastosRepresentacion();
			Ppto_GastoRepresentacion[] gastosArray = new Ppto_GastoRepresentacion[gastos.size()];
			if(gastos != null){
				Iterator it = gastos.iterator();
				int i =0;
				while (it.hasNext()) {
					Ppto_GastoRepresentacion gasto = (Ppto_GastoRepresentacion) it.next();
					gastosArray[i].setDetalle(gasto.getDetalle());
					gastosArray[i].setCosto(gasto.getCosto());
					i++;
				}
			}
			p.setGastosRepresentacion(gastosArray);
		}
	}
	
	public void buildGastosAsistentes(){
		if(gastosAsistentesPanel != null)
			p.setGastosAsistentes(gastosAsistentesPanel.getGastos());
		else if(pptoAnterior.getNumeroDePresupuesto()>0){			
			Set gastos = pptoAnterior.getGastosAsistentes();
			Ppto_GastoAsistentes[] gastosArray = new Ppto_GastoAsistentes[gastos.size()];
			if(gastos != null){
				Iterator it = gastos.iterator();
				int i =0;
				while (it.hasNext()) {
					Ppto_GastoAsistentes gasto = (Ppto_GastoAsistentes) it.next();
					gastosArray[i].setAsistente(gasto.getAsistente());
					gastosArray[i].setCargo(gasto.getCargo());
					gastosArray[i].setJornada(gasto.getJornada());
					gastosArray[i].setCosto(gasto.getCosto());
					i++;
				}
			}
			p.setGastosAsistentes(gastosArray);
		}
	}
	
	public void buildGastosViaticos(){
		if(gastosViaticosPanel != null)
			p.setGastosViaticos(gastosViaticosPanel.getGastos());
		else if(pptoAnterior.getNumeroDePresupuesto()>0){			
			Set gastos = pptoAnterior.getGastosViaticos();
			Ppto_GastoViaticos[] gastosArray = new Ppto_GastoViaticos[gastos.size()];
			if(gastos != null){
				Iterator it = gastos.iterator();
				int i =0;
				while (it.hasNext()) {
					Ppto_GastoViaticos gasto = (Ppto_GastoViaticos) it.next();
					gastosArray[i].setDetalle(gasto.getDetalle());					
					gastosArray[i].setCosto(gasto.getCosto());
					i++;
				}
			}
			p.setGastosViaticos(gastosArray);
		}
	}
	
	public void buildGastosHoteleria(){
		if(gastosHoteleriaPanel != null)
			p.setGastosHoteleria(gastosHoteleriaPanel.getGastos());
		else if(pptoAnterior.getNumeroDePresupuesto()>0){			
			Set gastos = pptoAnterior.getGastosHoteleria();
			Ppto_GastoHoteleria[] gastosArray = new Ppto_GastoHoteleria[gastos.size()];
			if(gastos != null){
				Iterator it = gastos.iterator();
				int i =0;
				while (it.hasNext()) {
					Ppto_GastoHoteleria gasto = (Ppto_GastoHoteleria) it.next();
					gastosArray[i].setDetalle(gasto.getDetalle());					
					gastosArray[i].setCosto(gasto.getCosto());
					i++;
				}
			}
			p.setGastosHoteleria(gastosArray);
		}
	}
	
	public void buildGastosVarios(){
		if(gastosVariosPanel != null)
			p.setGastosVarios(gastosVariosPanel.getGastos());
		else if(pptoAnterior.getNumeroDePresupuesto()>0){			
			Set gastos = pptoAnterior.getGastosVarios();
			Ppto_GastoVarios[] gastosArray = new Ppto_GastoVarios[gastos.size()];
			if(gastos != null){
				Iterator it = gastos.iterator();
				int i =0;
				while (it.hasNext()) {
					Ppto_GastoVarios gasto = (Ppto_GastoVarios) it.next();
					gastosArray[i].setDetalle(gasto.getDetalle());					
					gastosArray[i].setCosto(gasto.getCosto());
					i++;
				}
			}
			p.setGastosVarios(gastosArray);
		}
	}
	
	public void buildReportes(){
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
		else if(pptoAnterior.getNumeroDePresupuesto()>0){
			if(pptoAnterior.getCotizacion()!= null)
				p.setCotizacion(Double.valueOf(pptoAnterior.getCotizacion()));
			else 
				p.setCotizacion(1.00d);
			if(pptoAnterior.getEncabezadoPpto() != null)
				p.setCodigoEncabezado(pptoAnterior.getEncabezadoPpto().getCodigo());
			if(pptoAnterior.getFormaPagoPpto() != null)
				p.setCodigoFormaPago(pptoAnterior.getFormaPagoPpto().getCodigo());
			if(pptoAnterior.getPeriodoPpto() != null)
				p.setCodigoPeriodo(pptoAnterior.getPeriodoPpto().getCodigo());
			if(pptoAnterior.getTipoPpto() != null)
				p.setCodigoTipoPpto(pptoAnterior.getTipoPpto().getCodigo());
			if(pptoAnterior.getValidezPpto() != null)
				p.setCodigoValidez(pptoAnterior.getValidezPpto().getCodigo());
			if(pptoAnterior.getMoneda() != null)
				p.setCodigoMoneda(pptoAnterior.getMoneda().getCodigo());
			else
				p.setCodigoMoneda("2");
			if(pptoAnterior.getCancelacionPpto() != null)
				p.setCodigoCancelacion(pptoAnterior.getCancelacionPpto().getCodigo());
			if(pptoAnterior.getCondPagoPpto() != null)
				p.setCodigoCondPago(pptoAnterior.getCondPagoPpto().getCodigo());
		}
	}
	
	public void buildLugar(){
		if(lugarEventoPanel != null)
			p.setCodigoLugarDelEvento(lugarEventoPanel.getCodLugar());
		else if(pptoAnterior.getNumeroDePresupuesto()>0){
			p.setCodigoLugarDelEvento(pptoAnterior.getLugarDelEvento().getCodigo());
		}
	}
	
	public void buildMainPanel(){
		
		p.setNumeroDePresupuesto(mainPanel.getNumeroPpto());
		p.setCodigoUsuario(mainPanel.getCodigoUsuario());
		if(UserRolesUtil.isVendedor(mainPanel.getUsuario()) || UserRolesUtil.isSupervisor(mainPanel.getUsuario()))
			p.setCodigoVendedor(mainPanel.getCodigoVendedor());
		p.setFechaDeInicio(mainPanel.getFechaInicio());
		p.setFechaDeFinalizacion(mainPanel.getFechaFinalizacion());
		p.setCodigoReferencia(mainPanel.getCodigoReferencia());	
	}
	
	public void buildCliente(){
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
		else if(pptoAnterior.getNumeroDePresupuesto()>0){
			/*clientePanel = new ClientePanel();
			clientePanel.init();
			clientePanel.setMainPanel(mainPanel);
			clientePanel.setPresupuesto(mainPanel.getPresupuesto());
			buildCliente();*/
			p.setCodigoCliente(pptoAnterior.getCliente().getCodigo());
			p.setObservacionesDelCliente(pptoAnterior.getObservacionesDelCliente());				
			p.setResponsableEvento(pptoAnterior.getResponsableEvento());
			p.setResponsableEmail(pptoAnterior.getResponsableEmail());
			p.setResponsableTel(pptoAnterior.getResponsableTel());
			if(pptoAnterior.getResponsableNextelFlota() != null)
				p.setResponsableNextelFlota(pptoAnterior.getResponsableNextelFlota());
			else p.setResponsableNextelFlota(null);
			if(pptoAnterior.getResponsableNextelId() != null)
				p.setResponsableNextelId(pptoAnterior.getResponsableNextelId());
			else p.setResponsableNextelId(null);
		}
	}
	
	public void buildEvento(){
		if (eventoPanel != null){
			p.setFechaDeInstalacion(eventoPanel.getFechaInstalacion());
			if(!eventoPanel.getCantPersonasEvento().equals(""))
				p.setTotalDePersonas(eventoPanel.getCantPersonasEvento());
			else p.setTotalDePersonas(null);
			p.setNombreDelEvento(eventoPanel.getNombreDelEvento());
			p.setObservacionesDelEvento(eventoPanel.getObservacionesDelEvento());
			p.setCodigoTipoDeEvento(eventoPanel.getCodigoTipoEvento());
			
			p.setCodigoTipoDeLugarDelEvento(eventoPanel.getCodigoTipoLugar());
			
			p.setCodigoUniforme(eventoPanel.getCodigoUniforme());
		}	
		else if(pptoAnterior.getNumeroDePresupuesto()>0){
			p.setFechaDeInstalacion(pptoAnterior.getFechaDeInstalacion());
			if(!pptoAnterior.getTotalDePersonas().equals(""))
				p.setTotalDePersonas(pptoAnterior.getTotalDePersonas());
			else p.setTotalDePersonas(null);
			p.setNombreDelEvento(pptoAnterior.getNombreDelEvento());
			p.setObservacionesDelEvento(pptoAnterior.getObservacionesDelEvento());
			if(pptoAnterior.getTipoDeEvento() != null)
				p.setCodigoTipoDeEvento(pptoAnterior.getTipoDeEvento().getCodigo());
			if(pptoAnterior.getTipoDeLugarDelEvento() != null)
				p.setCodigoTipoDeLugarDelEvento(pptoAnterior.getTipoDeLugarDelEvento().getCodigo());
			if(pptoAnterior.getUniforme() != null)
				p.setCodigoUniforme(pptoAnterior.getUniforme().getCodigo());
		}
	}
	
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
		if(clientePanel != null){
			contacto.setCodContacto(Integer.parseInt(clientePanel.getCodigoContacto()));
		}
		else if(pptoAnterior.getNumeroDePresupuesto()>0){
			contacto.setCodContacto(Integer.parseInt(pptoAnterior.getContacto().getCodContacto()));
		}
		
		return contacto;
	}
	
	private AgregadoHelper getAgregados(){
		AgregadoHelper agreg = new AgregadoHelper();
		if(eventoPanel != null){
			if (eventoPanel.getCodigoModoIngreso() != null)
				agreg.setModoIngreso(Integer.valueOf(eventoPanel.getCodigoModoIngreso()));
			if (eventoPanel.getCodigoSeguridadIngreso() != null)
				agreg.setSeguridadIngreso(Integer.valueOf(eventoPanel.getCodigoSeguridadIngreso()));
			if(eventoPanel.getCodigoCategoriaEvento() != null)
				agreg.setCategoriaEvento(Integer.valueOf(eventoPanel.getCodigoCategoriaEvento()));
		}
		else if(pptoAnterior.getNumeroDePresupuesto()>0){
			if (pptoAnterior.getAgregado() != null){
				if (pptoAnterior.getAgregado().getModoIngreso() != null)
					agreg.setModoIngreso(Integer.valueOf(pptoAnterior.getAgregado().getModoIngreso()));
				if (pptoAnterior.getAgregado().getSeguridadIngreso() != null)
					agreg.setSeguridadIngreso(Integer.valueOf(pptoAnterior.getAgregado().getSeguridadIngreso()));
				if(pptoAnterior.getAgregado().getCategoriaEvento() != null)
					agreg.setCategoriaEvento(Integer.valueOf(pptoAnterior.getAgregado().getCategoriaEvento()));
			}
		}
		return agreg;
	}
	
	private AdelantoHelper getAdelanto(){
		AdelantoHelper adelanto = new AdelantoHelper();
		if(facturacionPanel != null){
			if (facturacionPanel.getAdelantoValor() != null)
				adelanto.setValor(Double.valueOf(facturacionPanel.getAdelantoValor()));
			if (facturacionPanel.getAdelantoPorcentaje() != null)
				adelanto.setPorcentaje(Integer.valueOf(facturacionPanel.getAdelantoPorcentaje()));	
		}
		else if(pptoAnterior.getNumeroDePresupuesto()>0){
			if (pptoAnterior.getAdelanto().getValor() != null)
				adelanto.setValor(Double.valueOf(pptoAnterior.getAdelanto().getValor()));
			if (pptoAnterior.getAdelanto().getPorcentaje() != null)
				adelanto.setPorcentaje(Integer.valueOf(pptoAnterior.getAdelanto().getPorcentaje()));
		}
		return adelanto;
	}
	
	private ContactoLugarHelper getContactoLugar(){
		ContactoLugarHelper contacto = new ContactoLugarHelper();
		
		if(lugarEventoPanel != null){
			if(lugarEventoPanel.getCodigoContacto() != null){
				contacto.setCodContacto(Integer.parseInt(lugarEventoPanel.getCodigoContacto()));
			}
		}
		else if(pptoAnterior.getNumeroDePresupuesto()>0){
			contacto.setCodContacto(Integer.parseInt(pptoAnterior.getContactoLugar().getCodContacto()));
		}
		
		return contacto;
	}
	
	private FacturacionHelper getFacturacion(){
		FacturacionHelper fact = new FacturacionHelper();
		if(facturacionPanel != null){
			if (facturacionPanel.getNroCliente()!= null)
				fact.setCodCliente(Integer.parseInt(facturacionPanel.getNroCliente()));
		}
		else if(pptoAnterior.getNumeroDePresupuesto()>0){
			if (pptoAnterior.getFacturacion().getCodCliente()!= null)
				fact.setCodCliente(Integer.parseInt(pptoAnterior.getFacturacion().getCodCliente()));
		}
		else if(clientePanel != null)
			fact.setCodCliente(Integer.parseInt(clientePanel.getCodigoCliente()));
		return fact;
	}
	
	private PagoHelper buildPago(){
		PagoHelper pago = new PagoHelper();
		if(facturacionPanel != null){
			if(facturacionPanel.getMedioPago()!=null)
				pago.setCodMedioPago(Integer.parseInt(facturacionPanel.getMedioPago()));
			if(facturacionPanel.getCondicionPago()!=null)
				pago.setCodCondicionPago(Integer.parseInt(facturacionPanel.getCondicionPago()));
		}
		else if(pptoAnterior.getNumeroDePresupuesto()>0){
			if(pptoAnterior.getPago().getCodMedioPago()!=null)
				pago.setCodMedioPago(Integer.parseInt(pptoAnterior.getPago().getCodMedioPago()));
			if(pptoAnterior.getPago().getCodCondicionPago()!=null)
				pago.setCodCondicionPago(Integer.parseInt(pptoAnterior.getPago().getCodCondicionPago()));
		}
		else if(pptoAnterior.getNumeroDePresupuesto() == 0){
			pago.setCodMedioPago(100);
			pago.setCodCondicionPago(100);
		}
		return pago;
	}
	
	private RentabilidadHelper getRentabilidad(){
		RentabilidadHelper rent = new RentabilidadHelper();
		if(rentabilidadPanel!=null){
			rentabilidadPanel.calculateData();
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
		else if(pptoAnterior.getNumeroDePresupuesto()>0){
			rent.setFacturacionOriginal(Double.parseDouble(pptoAnterior.getRentabilidad().getFacturacionOriginal()));
			rent.setFacturacionExtra(Double.parseDouble(pptoAnterior.getRentabilidad().getFacturacionExtra()));
			rent.setCostoOperativo(Double.parseDouble(pptoAnterior.getRentabilidad().getCostoOperativo()));
			rent.setGastosAsistentes(Double.parseDouble(pptoAnterior.getRentabilidad().getGastosAsistentes()));
			rent.setGastosContrataciones(Double.parseDouble(pptoAnterior.getRentabilidad().getGastosContrataciones()));
			rent.setGastosOperadores(Double.parseDouble(pptoAnterior.getRentabilidad().getGastosOperadores()));
			rent.setGastosOtros(Double.parseDouble(pptoAnterior.getRentabilidad().getGastosOtros()));
			rent.setComisionesLugar(Double.parseDouble(pptoAnterior.getRentabilidad().getComisionesLugar()));
			rent.setComisionesTerceros(Double.parseDouble(pptoAnterior.getRentabilidad().getComisionesTerceros()));
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
		if(mainPanel.isPresupACobrar()){
			estado.setAcobrar(1);
		}
		if(mainPanel.isPresupAdelantoACobrar()){
			estado.setAdelantoacobrar(1);
		}
		if(mainPanel.isPresupAdelantoCobrado()){
			estado.setAdelantocobrado(1);
		}
		if(mainPanel.isPresupAdicionalesFacturados()){
			estado.setAdicionalesFacturados(1);
		}
		if(facturacionPanel !=null){
			estado.setAdelanto(facturacionPanel.getAdelanto());
			estado.setAdelantado(facturacionPanel.getAdelantado());
			if(facturacionPanel.isHaveAdicionales())
				estado.setAdicionales(1);
		}
		else if(pptoAnterior.getNumeroDePresupuesto()>0){
			estado.setAdelanto(pptoAnterior.getEstadoActual().getAdelanto());
			estado.setAdelantado(pptoAnterior.getEstadoActual().getAdelantado());
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
					sh.setObservaciones(sala.getModel().getObservaciones());
					sh.setServicios(buildServicios(sala.getTableModel()));
					sh.setHorarios(buildHorarios(sala.getModel().getHorarios()));
					if (sala.getModel().getFechaDesarme() != null)
						sh.setFechaDesarme(sala.getModel().getFechaDesarme()+" "+sala.getModel().getHoraDesarme());
					else sh.setFechaDesarme(null);
					if(sala.getModel().getCodigoTipoArmado() != null)	
						sh.setTipoArmado(sala.getModel().getCodigoTipoArmado());

					if(sala.getModel().getFechaPrueba() != null)
						sh.setFechaPrueba(sala.getModel().getFechaPrueba()+" "+sala.getModel().getHoraPrueba());
					else sh.setFechaPrueba(null);
					System.out.println("nombre de sala :"+sala.getModel().getNombreSala());
					System.out.println("nombre de sala unico en sistema:"+sala.getModel().getNombreSalaUnica());
					sh.setNombreSalaUnico(sala.getModel().getNombreSalaUnica());
					
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
				sh.setHorarios(buildHorariosFromPpto(salaPpto.getHorario()));
			//	sh.setAgregado(buildAgregadosFromPpto(salaPpto.getAgregados()));
				
				if (salaPpto.getFechaDesarme() != null)
					sh.setFechaDesarme(salaPpto.getFechaDesarme());
				else sh.setFechaDesarme(null);
				if(salaPpto.getTipoArmado() != null){
					if(salaPpto.getTipoArmado().getCodigo() != null)	
						sh.setTipoArmado(salaPpto.getTipoArmado().getCodigo());
				}
				if(salaPpto.getFechaPrueba() != null)
					sh.setFechaPrueba(salaPpto.getFechaPrueba());
				else sh.setFechaPrueba(null);
				
				System.out.println("nombre de sala unico en ppto :"+salaPpto.getNombreSalaUnico());
				sh.setNombreSalaUnico(salaPpto.getNombreSalaUnico());
				
				salashelpers[i++] = sh;
			}
		}
		return salashelpers;
	}
	
	private HorariosHelper[] buildHorariosFromPpto(Set <Ppto_Sala_Horario> horarios){
		HorariosHelper[] helper = new HorariosHelper[horarios.size()];
		int i = 0;
		for(Ppto_Sala_Horario horario:horarios){
			HorariosHelper hh = new HorariosHelper();
			hh.setFecha(horario.getFecha());
			hh.setHoraDesde(horario.getHoraDesde());
			hh.setHoraHasta(horario.getHoraHasta());
			
			helper[i++]=hh;
		}
		return helper;
	}
	
	private ServicioHelper[] buildServiciosFromPpto(Set <Ppto_Sala_Servicio> servicios) {

		ServicioHelper[] servicioshelpers = new ServicioHelper[servicios.size()];
		int i = 0;
		for (Ppto_Sala_Servicio servicio : servicios) {

			ServicioHelper sh = new ServicioHelper();
			
			//sh.setTableItemId(servicio.getTableItemId());

			sh.setOrden(Integer.parseInt(servicio.getOrden()));
			sh.setCantidad(Integer.parseInt(servicio.getCantidad()));
			sh.setCodigoServicio(Integer.parseInt(servicio.getServicio().getCodigo()));
			
				sh.setFechaAlta(DateConverter.convertDateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			sh.setPrecioDeLista(Double.parseDouble(servicio.getPrecioDeLista()));

			sh.setPrecioDescuento(Double.parseDouble(servicio.getPrecioDeLista())+(Double.parseDouble(servicio.getPrecioDeLista())*Double.parseDouble(servicio.getDescuento())/100));
			
			sh.setCodigoModalidadContratacion(Integer.parseInt(servicio.getModalidad().getCodigo()));
			sh.setDetalle(servicio.getDetalle());
			sh.setDescDetallada(buildDescripcionesDetalladasFromPpto(servicio));
			
			
			sh.setDias(Integer.parseInt(servicio.getDias()));
			sh.setDescuento(Integer.parseInt(servicio.getDescuento()));
			
			sh.setModificado("N");
			//sh.setAccesorios(buildAccesorios(servicio));
						
			servicioshelpers[i++] = sh;
		}
		
		return servicioshelpers;
	}
	
	private HorariosHelper[] buildHorarios(List<HorariosItem> horarios){
		int cant = 0;
		if(horarios != null)
			cant=horarios.size();
		HorariosHelper[] helper = new HorariosHelper[cant];
		for(int i=0;i<cant;i++){
			HorariosHelper hh = new HorariosHelper();
			HorariosItem item = (HorariosItem)horarios.get(i);
			hh.setFecha(item.getFecha());
			hh.setHoraDesde(item.getHoraDesde());
			hh.setHoraHasta(item.getHoraHasta());
			
			helper[i]=hh;
		}
		return helper;
	}
	
	private double redondear(double val){
		return Math.ceil(val/10)*10;
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
			sh.setPrecioDescuento(servicio.getPrecioLista()+(servicio.getPrecioLista()*servicio.getDescuento()/100));
			//sh.setPrecioDescuento(servicio.getTotal());
			
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
			sh.setModificado("S");
			//sh.setAccesorios(buildAccesorios(servicio));
						
			servicioshelpers[i++] = sh;
		}
		
		return servicioshelpers;
	}
	
	private boolean serviciosEquals(Ppto_Sala_Servicio pptoServicio, SalaServicioItem servicio){		

			//if(verificarIgualdad(pptoServicio.getOrden(), servicio.getNroOrden())){
					if(verificarIgualdad(pptoServicio.getServicio().getCodigo(), String.valueOf(servicio.getServicioCodigo())) &&
							verificarIgualdad(pptoServicio.getDetalle(), servicio.getServicio())){						
						boolean isvalid = false;
						if (servicio.isSubContratado() && !servicio.isOpcional()){
							if(verificarIgualdad(pptoServicio.getModalidad().getCodigo(),Integer.toString(Servicio.MODALIDAD_CONTRATACION_EXTERNA))
									&& verificarIgualdad(pptoServicio.getDetalle(), servicio.getServicio()))
								isvalid = true;
								
						}
						else if(!servicio.isSubContratado() && !servicio.isOpcional()){
							if(verificarIgualdad(pptoServicio.getModalidad().getCodigo(),Integer.toString(Servicio.MODALIDAD_CONTRATACION_INTERNA))
									&& verificarIgualdad(pptoServicio.getDetalle(),null))
								isvalid = true;;
							
						}
						else if(servicio.isSubContratado() && servicio.isOpcional()){
							if(verificarIgualdad(pptoServicio.getModalidad().getCodigo(),Integer.toString(Servicio.MODALIDAD_CONTRATACION_EXTERNA_OPCIONAL))
									&& verificarIgualdad(pptoServicio.getDetalle(),servicio.getServicio()))
								isvalid = true;
						}
						else if(!servicio.isSubContratado() && servicio.isOpcional()){
							if(verificarIgualdad(pptoServicio.getModalidad().getCodigo(),Integer.toString(Servicio.MODALIDAD_CONTRATACION_INTERNA_OPCIONAL))
									&& verificarIgualdad(pptoServicio.getDetalle(),null))
								isvalid = true;
						}
						
						if(!(isvalid && verificarIgualdad(pptoServicio.getCantidad(), String.valueOf(servicio.getCantidad())) &&
								//verificarIgualdad(pptoServicio.getDescuento(), String.valueOf(servicio.getDescuento())) &&
								verificarIgualdad(pptoServicio.getDias(), String.valueOf(servicio.getDias())) /*&&
								verificarIgualdad(pptoServicio.getServicio().getCodigo(), String.valueOf(servicio.getServicioCodigo()))*/)
						){
							return false;
						}
					}


			return true;

	}
	
	//Este metodo sirve para los campos de tx_ppto que admiten null, porque no se puede usar equals 
	private boolean verificarIgualdad(String st1, String st2){
		if(st1 ==null && st2==null)
			return true;
		else if((st1==null && st2!=null) || (st1!=null && st2==null))
			return false;		
		else if(st1.equals(st2))
			return true;
		return false;
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
	
	private DescDetalladaServicioHelper[] buildDescripcionesDetalladasFromPpto(Ppto_Sala_Servicio servicio){
		Set descripciones = servicio.getDescripcionDetallada();
		DescDetalladaServicioHelper[] descripcioneshelpers = null;
		int i=0;
		if(descripciones != null){
			descripcioneshelpers = new DescDetalladaServicioHelper[descripciones.size()];
			Iterator it = descripciones.iterator();
			while (it.hasNext()) {

				DescDetalladaServicioHelper ah = new DescDetalladaServicioHelper();
				Ppto_Sala_Servicio_Desc_Detallada d =(Ppto_Sala_Servicio_Desc_Detallada)it.next();
				ah.setDescripcion(d.getDescripcion());
				
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
