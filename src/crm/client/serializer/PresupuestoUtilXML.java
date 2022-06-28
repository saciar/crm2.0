package crm.client.serializer;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashSet;

import crm.client.managers.ClienteManager;
import crm.client.managers.EstadoEventoManager;
import crm.client.managers.LugarEventoManager;
import crm.client.managers.ModalidadContratManager;
import crm.client.managers.MonedaExtranjeraManager;
import crm.client.managers.PresupuestosManager;
import crm.client.managers.PrtPptoCancelacionManager;
import crm.client.managers.PrtPptoCondPagoManager;
import crm.client.managers.PrtPptoFPagoManager;
import crm.client.managers.PrtPptoHeaderManager;
import crm.client.managers.PrtPptoPeriodoManager;
import crm.client.managers.PrtPptoTipoPresupuestoManager;
import crm.client.managers.PrtPptoValidezManager;
import crm.client.managers.SalaLugarManager;
import crm.client.managers.ServicioManager;
import crm.client.managers.TipoArmadoManager;
import crm.client.managers.TipoEventoManager;
import crm.client.managers.TipoLugarEventoManager;
import crm.client.managers.TipoUniformeManager;
import crm.client.managers.UsuarioManager;
import crm.client.managers.VendedorManager;
import crm.client.util.DateConverter;
import crm.libraries.abm.entities.ABMEntity;
import crm.libraries.abm.entities.Cliente;
import crm.libraries.abm.entities.LugarEvento;
import crm.libraries.abm.entities.PptoCambioEstado;
import crm.libraries.abm.entities.PptoEstadoActual;
import crm.libraries.abm.entities.Ppto_Adelanto;
import crm.libraries.abm.entities.Ppto_Agregado;
import crm.libraries.abm.entities.Ppto_Contacto;
import crm.libraries.abm.entities.Ppto_Contacto_Lugar;
import crm.libraries.abm.entities.Ppto_Facturacion;
import crm.libraries.abm.entities.Ppto_GastoSC;
import crm.libraries.abm.entities.Ppto_Pago;
import crm.libraries.abm.entities.Ppto_Rentabilidad;
import crm.libraries.abm.entities.Ppto_Sala;
import crm.libraries.abm.entities.Ppto_Sala_Servicio;
import crm.libraries.abm.entities.Ppto_Sala_Servicio_Desc_Detallada;
import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.abm.entities.Servicio;
import crm.libraries.abm.entities.Usuario;
import crm.libraries.abm.entities.VendedorPpto;

import crm.services.sei.EstadoEventoManagerSEI;

public class PresupuestoUtilXML {
	
	private PresupuestoUtilXML(){
	}
	
	public static PresupuestoUtilXML instance(){
		return new PresupuestoUtilXML();
	}
	
	/**
	 * Convierte un PresupuestoHelper a Presupuesto
	 * 
	 * @param pa el presupuestoHelper que viene del cliente
	 *  
	 */
	public Presupuesto toPresupuesto(PresupuestoXML pa) {

		Presupuesto p=null;
		
		try {	
			// si el presupuesto es null salgo
			if (pa == null) {
				return null;
			}
			
			if (pa.getCodigoUsuario() == null) {
				return null;
			}
			
			
			
			// traigo el presupuesto de la base si es que existe.
			if (!pa.getNumeroDePresupuesto().equals("0")){
				
				p = PresupuestosManager.instance().buscarPresupuesto(Long.parseLong(pa.getNumeroDePresupuesto()));
				
			}
			
			if (p==null){
				p = Presupuesto.createDefault();
			}
			
			// Grabar el estado actual y agregar los cambios de estados
			procesarCambiosDeEstado(p,pa);
			
			// Guardar el vendedor que realizo la operacion
			updateVendedor(pa, p);
			
			// Guardo el cliente
			updateCliente(pa, p);
			
			// otros datos varios
			p.setObservacionesDelCliente(pa.getObservacionesDelCliente());
			p.setFechaDeInicio(pa.getFechaDeInicio());
			p.setFechaDeFinalizacion(pa.getFechaDeFinalizacion());
			p.setFechaDeInstalacion(pa.getFechaDeInstalacion());
			p.setTotalDePersonas(pa.getTotalDePersonas());
			p.setNombreDelEvento(pa.getNombreDelEvento());
			p.setObservacionesDelEvento(pa.getObservacionesDelEvento());
			p.setCodigoReferencia(Integer.parseInt(pa.getCodigoReferencia()));
			p.setResponsableEvento(pa.getResponsableEvento());
			p.setResponsableTel(pa.getResponsableTel());
			p.setResponsableEmail(pa.getResponsableEmail());
			p.setResponsableNextelFlota(pa.getResponsableNextelFlota());
			p.setResponsableNextelId(pa.getResponsableNextelId());
			p.setActivo(pa.getActivo());

			p.setCotizacion(pa.getCotizacion());
			// cargar el lugar del evento
			updateLugarDelEvento(pa, p);
			
			// grabo el tipo de evento
			if (compareEntities(p.getTipoDeEvento(),pa.getCodigoTipoDeEvento())){			
				p.setTipoDeEvento(TipoEventoManager.instance().getTipoEventoById(pa.getCodigoTipoDeEvento()));
			}
			
			// grabo el header en el reporte de presupuesto
			if (compareEntities(p.getEncabezadoPpto(),pa.getCodigoEncabezado())){			
				p.setEncabezadoPpto(PrtPptoHeaderManager.instance().getById(pa.getCodigoEncabezado()));
			}
			
//			grabo la forma de pago en el reporte de presupuesto
			if (compareEntities(p.getFormaPagoPpto(),pa.getCodigoFormaPago())){			
				p.setFormaPagoPpto(PrtPptoFPagoManager.instance().getById(pa.getCodigoFormaPago()));
			}
			
//			grabo la forma de pago en el reporte de presupuesto
			if (compareEntities(p.getCondPagoPpto(),pa.getCodigoCondPago())){			
				p.setCondPagoPpto(PrtPptoCondPagoManager.instance().getById(pa.getCodigoCondPago()));
			}
			
//			grabo la validez en el reporte de presupuesto
			if (compareEntities(p.getValidezPpto(),pa.getCodigoValidez())){			
				p.setValidezPpto(PrtPptoValidezManager.instance().getById(pa.getCodigoValidez()));
			}		
			
//			grabo el tipo de presupuesto en el reporte de presupuesto
			if (compareEntities(p.getTipoPpto(),pa.getCodigoTipoPpto())){			
				p.setTipoPpto(PrtPptoTipoPresupuestoManager.instance().getById(pa.getCodigoTipoPpto()));
			}	
			
//			grabo la cancelacion de presupuesto en el reporte de presupuesto
			if (compareEntities(p.getCancelacionPpto(),pa.getCodigoCancelacion())){
				p.setCancelacionPpto(PrtPptoCancelacionManager.instance().getById(pa.getCodigoCancelacion()));
			}	
			
//			grabo el periodo de presupuesto en el reporte de presupuesto
			if (compareEntities(p.getPeriodoPpto(),pa.getCodigoPeriodo())){
				p.setPeriodoPpto(PrtPptoPeriodoManager.instance().getById(pa.getCodigoPeriodo()));
			}
			
//			grabo la moneda de presupuesto en el reporte de presupuesto
			if (compareEntities(p.getMoneda(),pa.getCodigoMoneda())){	
				p.setMoneda(MonedaExtranjeraManager.instance().getById(pa.getCodigoMoneda()));
			}	
			
			// graba el tipo del lugar
			if (compareEntities(p.getTipoDeLugarDelEvento(),pa.getCodigoTipoDeLugarDelEvento())){
				p.setTipoDeLugarDelEvento(TipoLugarEventoManager.instance().getTipoLugarEventoById(pa.getCodigoTipoDeLugarDelEvento()));
			}
			
			if (compareEntities(p.getUniforme(),pa.getCodigoUniforme())){
				p.setUniforme(TipoUniformeManager.instance().getTipoUniformeById(pa.getCodigoUniforme()));
			}
			
			// elimino las salas anteriores si es que las tiene
			//borrarSalasAnteriores(p);
			
			// creo las salas nuevas
			crearSalas(p, pa);
			
			// -----------gastos----------
			crearGastosSC(p, pa);
			/*crearGastosOperador(p, pa);
			 crearGastosAsistentes(p, pa);
			 crearGastosViaticos(p, pa);
			 crearGastosHoteleria(p, pa);
			 crearGastosRepresentacion(p, pa);
			 crearGastosVarios(p, pa);*/
			// -----------------------------
			
			updateContacto(p,pa);
			
			updateContactoLugar(p,pa);
			
			updateFacturacion(p,pa);
			
			updateRentabilidad(p,pa);
			
			updatePago(p,pa);
			
			updateAgregado(p,pa);
			
			updateAdelanto(p,pa);
		} catch (RemoteException e) {}
		return p;
	}


	/**
	 * Actualizo el lugar del evento solo si este cambio.
	 * 
	 * @param pa
	 * @param p
	 * @return
	 */
	private void updateLugarDelEvento(PresupuestoXML pa, Presupuesto p) throws RemoteException {
		
		LugarEvento lugarDelEvento = null;
		
		if (compareEntities(p.getLugarDelEvento(),pa.getCodigoLugarDelEvento())){
			
			lugarDelEvento = LugarEventoManager.instance().getLugarEventoById(pa.getCodigoLugarDelEvento());

			p.setLugarDelEvento(lugarDelEvento);
		}
		
	}

	/**
	 * Graba el cliente, solo si este cambio
	 * @param pa
	 * @param p
	 */
	private void updateCliente(PresupuestoXML pa, Presupuesto p) throws RemoteException {
		// jamas deberia ser null porque es obligatorio
		if (pa.getCodigoCliente() != null) {

			String id = pa.getCodigoCliente();
			Cliente cliente = p.getCliente();
			
			if (cliente == null || !id.equals(cliente.getCodigo())){
				// Cargar el cliente
				p.setCliente(ClienteManager.instance().getClienteById(id));
			}
		}
	}

	/**
	 * Asigna el vendedor si este cambio.
	 * @param pa
	 * @param p
	 */
	private void updateVendedor(PresupuestoXML pa, Presupuesto p) throws RemoteException{
		if (pa.getCodigoVendedor() != null) {

			String id = pa.getCodigoVendedor();
			
			VendedorPpto vppto = p.getVendedor();
			if (vppto == null)
				vppto = new VendedorPpto();
			
			if ((vppto.getVendedor() == null)){
				vppto.setFecha(DateConverter.convertDateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
				vppto.setVendedor(VendedorManager.instance().getVendedorById(id));
				p.setVendedor(vppto);
				vppto.setPresupuesto(p);
			}
		}
	}
	
	/**
	 * Guarda los datos de facturacion
	 * 
	 * @param p
	 * @param pa
	 * @return
	 */
	private void updateFacturacion (Presupuesto p, PresupuestoXML pa){
		ClienteFacturacionXML factHelper = pa.getClienteFacturacion();
		
		Ppto_Facturacion facturacion = p.getFacturacion();
		if (facturacion == null)
			facturacion = new Ppto_Facturacion();
		
		if (!String.valueOf(factHelper.getCodigoClienteFacturacion()).equals(facturacion.getCodCliente())){
			facturacion.setCodCliente(String.valueOf(factHelper.getCodigoClienteFacturacion()));
			facturacion.setPresupuesto(p);
			p.setFacturacion(facturacion);
		}
	}
	
	/**
	 * Guarda los datos de rentabilidad
	 * 
	 * @param p
	 * @param pa
	 * @return
	 */
	private void updateRentabilidad (Presupuesto p, PresupuestoXML pa){
		RentabilidadXML rentHelper = pa.getRentabilidad();
		
		Ppto_Rentabilidad rentabilidad = p.getRentabilidad();
		if (rentabilidad == null)
			rentabilidad = new Ppto_Rentabilidad();
		
		if (rentHelper != null && !String.valueOf(rentHelper.getFacturacionOriginal()).equals(rentabilidad.getFacturacionOriginal()) ||
				!String.valueOf(rentHelper.getFacturacionExtra()).equals(rentabilidad.getFacturacionExtra()) ||
				!String.valueOf(rentHelper.getCostoOperativo()).equals(rentabilidad.getCostoOperativo()) ||
				!String.valueOf(rentHelper.getGastosAsistentes()).equals(rentabilidad.getGastosAsistentes()) ||
				!String.valueOf(rentHelper.getGastosContrataciones()).equals(rentabilidad.getGastosContrataciones()) ||
				!String.valueOf(rentHelper.getGastosOperadores()).equals(rentabilidad.getGastosOperadores()) ||
				!String.valueOf(rentHelper.getGastosOtros()).equals(rentabilidad.getGastosOtros()) ||
				!String.valueOf(rentHelper.getComisionesLugar()).equals(rentabilidad.getComisionesLugar()) ||
				!String.valueOf(rentHelper.getComisionesTerceros()).equals(rentabilidad.getComisionesTerceros())){
			rentabilidad.setFacturacionOriginal(String.valueOf(rentHelper.getFacturacionOriginal()));
			rentabilidad.setFacturacionExtra(String.valueOf(rentHelper.getFacturacionExtra()));
			rentabilidad.setCostoOperativo(String.valueOf(rentHelper.getCostoOperativo()));
			rentabilidad.setGastosAsistentes(String.valueOf(rentHelper.getGastosAsistentes()));
			rentabilidad.setGastosContrataciones(String.valueOf(rentHelper.getGastosContrataciones()));
			rentabilidad.setGastosOperadores(String.valueOf(rentHelper.getGastosOperadores()));
			rentabilidad.setGastosOtros(String.valueOf(rentHelper.getGastosOtros()));
			rentabilidad.setComisionesLugar(String.valueOf(rentHelper.getComisionesLugar()));
			rentabilidad.setComisionesTerceros(String.valueOf(rentHelper.getComisionesTerceros()));
			
			rentabilidad.setPresupuesto(p);
			p.setRentabilidad(rentabilidad);
		}
	}
	
	/**
	 * Guarda los datos agrgados como modo de ingreso de equipo y seguridad del mismo
	 * 
	 * @param p
	 * @param pa
	 * @return
	 */
	private void updateAgregado (Presupuesto p, PresupuestoXML pa){
		AgregadosXML agregadoHelper = pa.getAgregados();
		
		Ppto_Agregado agregado = p.getAgregado();
		
		if (agregado == null)
			agregado = new Ppto_Agregado();
		
		if (!String.valueOf(agregadoHelper.getCodigoModoIngreso()).equals(agregado.getModoIngreso()) ||
				!String.valueOf(agregadoHelper.getCodigoSeguridadIngreso()).equals(agregado.getSeguridadIngreso()) ||
				!String.valueOf(agregadoHelper.getCodigoCategoriaEvento()).equals(agregado.getCategoriaEvento())){
			agregado.setModoIngreso(String.valueOf(agregadoHelper.getCodigoModoIngreso()));
			agregado.setCategoriaEvento(String.valueOf(agregadoHelper.getCodigoCategoriaEvento()));
			agregado.setSeguridadIngreso(String.valueOf(agregadoHelper.getCodigoSeguridadIngreso()));
			agregado.setPresupuesto(p);
			p.setAgregado(agregado);
		}
		
	}
	
	/**
	 * Guarda los datos de adelanto, valor y porcentaje
	 * 
	 * @param p
	 * @param pa
	 * @return
	 */
	private void updateAdelanto (Presupuesto p, PresupuestoXML pa){
		AdelantoXML adelantoHelper = pa.getAdelanto();
		
		Ppto_Adelanto adelanto = p.getAdelanto();
		
		if (adelanto == null)
			adelanto = new Ppto_Adelanto();
		
		if (!String.valueOf(adelantoHelper.getValor()).equals(adelanto.getValor()) ||
				!String.valueOf(adelantoHelper.getPorcentaje()).equals(adelanto.getPorcentaje())){
			adelanto.setValor(String.valueOf(adelantoHelper.getValor()));
			adelanto.setPorcentaje(String.valueOf(adelantoHelper.getPorcentaje()));			
			adelanto.setPresupuesto(p);
			p.setAdelanto(adelanto);
		}
		
	}

	/**
	 * Guarda los datos del contacto
	 * 
	 * @param p
	 * @param pa
	 * @return
	 */
	private void updateContacto (Presupuesto p, PresupuestoXML pa){
		
		ContactoClienteXML ch = pa.getContactoCliente();
		
		Ppto_Contacto pc = p.getContacto();
		if (pc == null)
			pc = new Ppto_Contacto();
		
		if (!String.valueOf(ch.getCodigoContactoCliente()).equals(pc.getCodContacto())){
			pc.setCodContacto(String.valueOf(ch.getCodigoContactoCliente()));
			pc.setPresupuesto(p);
			p.setContacto(pc);
		}
	}
	
	private void updateContactoLugar (Presupuesto p, PresupuestoXML pa){
		
		ContactoLugarXML ch = pa.getContactoLugar();
		
		Ppto_Contacto_Lugar pc = p.getContactoLugar();
		if (pc == null)
			pc = new Ppto_Contacto_Lugar();
		
//		(Ppto_Contacto) session.load(Ppto_Contacto.class, pa.getContacto().getCodContacto());
		
		if (!String.valueOf(ch.getCodigoContactoLugar()).equals(pc.getCodContacto())){
			pc.setCodContacto(String.valueOf(ch.getCodigoContactoLugar()));
			pc.setPresupuesto(p);
			p.setContactoLugar(pc);
		}
	}
	
	/**
	 * Guarda un Pago
	 * 
	 * @param entity
	 * @param codigo
	 * @return
	 */
	private void updatePago(Presupuesto p, PresupuestoXML pa){
		PagoXML pagoHelper = pa.getPago();
		
		Ppto_Pago pago = p.getPago();
		if (pago == null)
			pago = new Ppto_Pago();
		
		if (!String.valueOf(pagoHelper.getCodigoMedioPago()).equals(pago.getCodMedioPago()) ||
				!String.valueOf(pagoHelper.getCodigoCondicionPago()).equals(pago.getCodCondicionPago())){
			pago.setCodMedioPago(String.valueOf(pagoHelper.getCodigoMedioPago()));
			pago.setCodCondicionPago(String.valueOf(pagoHelper.getCodigoCondicionPago()));
			pago.setPresupuesto(p);
			p.setPago(pago);
		}
	}
	
	/**
	 * Indica si el codigo es igual o no al codigo de la entidad
	 * 
	 * @param entity
	 * @param codigo
	 * @return
	 */
	private boolean compareEntities(ABMEntity entity, String codigo){
		return (codigo != null && (entity == null || !entity.getCodigo().equals(codigo)));
	}
	
	/**
	 * Convierte un EstadoActualHelper a PptoEstadoActual
	 * @param p
	 * @return
	 */
	private PptoEstadoActual toEstadoActual(EstadoXML estado) {
		PptoEstadoActual ea = new PptoEstadoActual();
		
		ea.setActualizado(Integer.parseInt(estado.getActualizado()));
		ea.setCancelado(Integer.parseInt(estado.getCancelado()));
		ea.setCobrado(Integer.parseInt(estado.getCobrado()));
		ea.setConfirmado(Integer.parseInt(estado.getConfirmado()));
		ea.setFacturado(Integer.parseInt(estado.getFacturado()));
		ea.setNuevo(Integer.parseInt(estado.getNuevo()));
		ea.setOf(Integer.parseInt(estado.getOrdenDeFacturacion()));
		ea.setOs(Integer.parseInt(estado.getOrdenDeServicio()));
		ea.setRechazado(Integer.parseInt(estado.getRechazado()));
		ea.setOc(Integer.parseInt(estado.getOrdenDeCompra()));
		ea.setAdelantado(Integer.parseInt(estado.getAdelantado()));
		ea.setAdelanto(Integer.parseInt(estado.getAdelanto()));
		
		return ea;
	}
	
	/**
	 * Verifica si hubo un cambio de estado y lo agrega a la lista si es necesario
	 * @param p presupuesto
	 * @param estadoActual estado actual del presupuesto p
	 */
	private void procesarCambiosDeEstado(Presupuesto p, PresupuestoXML pa) throws RemoteException{

		// obtengo el estado actual
		PptoEstadoActual nuevoEstado = toEstadoActual(pa.getEstado());
		
		// traigo el usuario
		Usuario user = UsuarioManager.instance().getUsuarioById(pa.getCodigoUsuario());;
		
		// si es nuevo agrego el estado nuevo
		if (p.isNew()){
			agregarCambioEstado(p, EstadoEventoManagerSEI.CODIGO_ESTADO_NUEVO, user);
            nuevoEstado.setNuevo(1);
		}
		
		// sino lo hago modificado
		else {
			agregarCambioEstado(p, EstadoEventoManagerSEI.CODIGO_ESTADO_ACTUALIZADO, user);
            nuevoEstado.setActualizado(1);
		}

		// ahora veo que estados cambiaron
		PptoEstadoActual estadoActual = p.getEstadoActual();
		
		// si es null, entonces le doy new para que tenga todo en 0.
		if (estadoActual == null)
			estadoActual = new PptoEstadoActual();
	
		if (estadoActual.getCancelado() == 0 && nuevoEstado.getCancelado() == 1)
			agregarCambioEstado(p, EstadoEventoManagerSEI.CODIGO_ESTADO_CANCELADO, user);
		
		if (estadoActual.getConfirmado() == 0 && nuevoEstado.getConfirmado() == 1)
			agregarCambioEstado(p, EstadoEventoManagerSEI.CODIGO_ESTADO_CONFIRMADO, user);
		
		if (estadoActual.getRechazado() == 0 && nuevoEstado.getRechazado() == 1)
			agregarCambioEstado(p, EstadoEventoManagerSEI.CODIGO_ESTADO_RECHAZADO, user);
		
		if (estadoActual.getOs() == 0 && nuevoEstado.getOs() == 1)
			agregarCambioEstado(p, EstadoEventoManagerSEI.CODIGO_ESTADO_ORDEN_SERVICIO, user);
		
		if (estadoActual.getOf() == 0 && nuevoEstado.getOf() == 1)
			agregarCambioEstado(p, EstadoEventoManagerSEI.CODIGO_ESTADO_ORDEN_FACTURACION, user);
		
		if (estadoActual.getOc() == 0 && nuevoEstado.getOc() == 1)
			agregarCambioEstado(p, EstadoEventoManagerSEI.CODIGO_ESTADO_ORDEN_COMPRA, user);
		
		if (estadoActual.getFacturado() == 0 && nuevoEstado.getFacturado() == 1)
			agregarCambioEstado(p, EstadoEventoManagerSEI.CODIGO_ESTADO_FACTURADO, user);
		
		if (estadoActual.getCobrado() == 0 && nuevoEstado.getCobrado() == 1)
			agregarCambioEstado(p, EstadoEventoManagerSEI.CODIGO_ESTADO_COBRADO, user);
		
		if (estadoActual.getAdelanto() == 0 && nuevoEstado.getAdelanto() == 1)
			agregarCambioEstado(p, EstadoEventoManagerSEI.CODIGO_ESTADO_ADELANTO, user);
		
		if (estadoActual.getAdelantado() == 0 && nuevoEstado.getAdelantado() == 1)
			agregarCambioEstado(p, EstadoEventoManagerSEI.CODIGO_ESTADO_ADELANTADO, user);
		
		// Grabar estado actual
		estadoActual.setEstadoActual(nuevoEstado);
		estadoActual.setPresupuesto(p);
		p.setEstadoActual(estadoActual);
	}
	
	/**
	 * Crea un cambio de estado segun el codigo indicado
	 * @param codigoEstado codigo del cambio de estado, 
	 * @see EstadoEventoManagerSEI para las constantes
	 * @return
	 */
	private void agregarCambioEstado(Presupuesto p, String codigoEstado, Usuario user) throws RemoteException{

		PptoCambioEstado ca = new PptoCambioEstado();
		ca.setEstado(EstadoEventoManager.instance().getEstadoEventoById(codigoEstado));
		ca.setFecha(DateConverter.convertDateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
		ca.setUsuario(user);
		
		// Guardar el cambio de estado
		ca.setPresupuesto(p);
		p.addCambioEstado(ca);
	}
	
	/**
	 * Crea las salas para asignar a este presupuesto
	 * 
	 * @param p
	 * @param pa
	 * @param lugarDelEvento
	 */
	private void crearSalas(Presupuesto p, PresupuestoXML pa) throws RemoteException {

		if (p.getSalas() == null)
			p.setSalas(new HashSet<Ppto_Sala>());
		else
			p.getSalas().clear();
		
		SalasXML[] salas2 = pa.getSalas();
		SalasXML[] salas = orderSalasArray(salas2);
		if (salas != null) {
			for (SalasXML salaAbs : salas) {
				
				Ppto_Sala sala = new Ppto_Sala();
				
				sala.setOrden(salaAbs.getOrden());

				sala.setSala(SalaLugarManager.instance().getSalaLugarById(salaAbs.getCodigoSalaLugar()));
				
				sala.setFechaDeInicio(salaAbs.getFechaDeInicio());
				sala.setFechaDeFinalizacion(salaAbs.getFechaDeFinalizacion());
				sala.setFechaDeInstalacion(salaAbs.getFechaDeInstalacion());
				sala.setTotalDePersonas(salaAbs.getTotalPersonas());
				sala.setPresupuesto(p);
				sala.setObservaciones(salaAbs.getObservaciones());
				
				if (compareEntities(sala.getTipoArmado(),String.valueOf(salaAbs.getTipoArmado()))){			

					sala.setTipoArmado(TipoArmadoManager.instance().getById(String.valueOf(salaAbs.getTipoArmado())));
				}
		
				sala.setFechaDesarme(salaAbs.getFechaDesarme());
				sala.setFechaPrueba(salaAbs.getFechaPrueba());
				
				crearServicios(pa, salaAbs, sala);
				//crearAgregados(salaAbs, sala);
				p.addSala(sala);				
			}
		}
		System.out.println("Error parseando descuento PresupuestoManager");		
	}
	
	public SalasXML[] orderSalasArray(SalasXML[] array){
    	int i, j;
    	SalasXML temp;

        for (i=1; i<array.length; i++)
             for (j=0; j<array.length - i; j++)
                  if (Integer.parseInt(((SalasXML)array[j]).getOrden()) > Integer.parseInt(((SalasXML)array[j+1]).getOrden()))
                       {
                       /* Intercambiamos */
                       temp = array[j];
                       array[j] = array[j+1];
                       array[j+1] = temp;
                       }
        return array;
    }
	
	/**
	 * Crea los agregado como son fechas de desarme y pruebas y el tipo de armado de equipos
	 * @param salaAbs
	 * @param sala
	 *
	private void crearAgregados(SalasXML salaAbs, Ppto_Sala sala){
		AgregadosSalaXML agrega = salaAbs.getAgregados();
		if(agrega != null) {

			Ppto_Sala_Agregado accesorio = new Ppto_Sala_Agregado();

			accesorio.setTipoArmado(agrega.getCodigoTipoArmado());
			accesorio.setFechaDesarme(agrega.getFechaDesarme());
			accesorio.setFechaPrueba(agrega.getFechaPrueba());
			accesorio.setSala(sala);
				
			//sala.setAgregados(accesorio);
		}
	}/*
	
	/**
	 * Crea los servicios
	 * 
	 * @param pa
	 * @param salaAbs
	 * @param sala
	 * @param lugarDelEvento
	 */
	private void crearServicios(PresupuestoXML pa, SalasXML salaAbs, Ppto_Sala sala) throws RemoteException {

		ServiciosXML[] servicios = salaAbs.getServicios();
		//ServiciosXML[] servicios = orderServiciosArray(servicios2);
		if (servicios != null) {
			for (ServiciosXML servicioAbs : servicios) {				

				Ppto_Sala_Servicio servicio = new Ppto_Sala_Servicio();
				servicio.setOrden(servicioAbs.getOrden());
				servicio.setCantidad(servicioAbs.getCantidad());
				servicio.setSala(sala);

				Servicio _serv = ServicioManager.instance().getServicioById(servicioAbs.getCodigoServicio());

				servicio.setServicio(_serv);
				servicio.setModalidad(ModalidadContratManager.instance().getModalidadContratById(servicioAbs.getModalidadContratacion()));
				servicio.setDias(servicioAbs.getDias());
				
				servicio.setFechaCreacion(servicioAbs.getFechaAlta());

				servicio.setPrecioDeLista(servicioAbs.getPrecioSinDescuento());
				servicio.setDescuento(servicioAbs.getDescuento());
				servicio.setDetalle(servicioAbs.getDetalle());
				servicio.setModificado(servicioAbs.getModificado());
				try {
					Double d = Double.valueOf(servicioAbs.getPrecioSinDescuento()) * ((Double.valueOf(servicioAbs.getDescuento())+100) / 100);
					servicio.setPrecioDescuento(Double.toString(d));
				} catch (Exception e) {
					System.out.println("Error parseando descuento PresupuestoManager " + e.getMessage());
				}

				crearDescDetallada(servicioAbs, servicio);
				sala.addServicio(servicio);

				// le agrego el servicio al gasto indicado
				/*Ppto_GastoSC gasto = getGastoSCForServicio(pa, servicioAbs);
				if (gasto != null) {
					gasto.setPpto_Sala_Servicio(servicio);
				}*/
			}
		}
	}
	
	/*public static ServiciosXML[] orderServiciosArray(ServiciosXML[] array){
    	int i, j;
    	ServiciosXML temp;

        for (i=1; i<array.length; i++)
             for (j=0; j<array.length - i; j++)
                  if (Integer.parseInt(((ServiciosXML)array[j]).getOrden()) > Integer.parseInt(((ServiciosXML)array[j+1]).getOrden()))
                       {

                       temp = array[j];
                       array[j] = array[j+1];
                       array[j+1] = temp;
                       }
        return array;
    }*/
	
	/**
	 * Crea las descripciones detalladas
	 * @param salaAbs
	 * @param sala
	 */
	private void crearDescDetallada(ServiciosXML servicioAbs, Ppto_Sala_Servicio servicio){
		if (servicioAbs.getDescripcionDetallada() != null) {

			for (DescripcionDetalladaXML descAbs : servicioAbs.getDescripcionDetallada()) {

				if (!descAbs.getDescripcion().equals("")) {

					Ppto_Sala_Servicio_Desc_Detallada descripcion = new Ppto_Sala_Servicio_Desc_Detallada();
					
					descripcion.setDescripcion(descAbs.getDescripcion());
					descripcion.setServicio(servicio);

					servicio.addDescDetallada(descripcion);
				} else {

				}
			}
		}
	}
	
	/**
	 * Crea los gastos por servicio
	 * 
	 * @param ps
	 * @param servicio
	 * @return
	 */
	private Ppto_GastoSC getGastoSCForServicio(PresupuestoXML ps, ServiciosXML servicio) {	

		GastosContratacionXML[] gastos = ps.getGastosContratacion();
		if (gastos != null) {

			for (int i = 0; i < gastos.length; i++) {
				/*if (gastos[i].getSalaServicioTableItemId() > 0 && gastos[i].getSalaServicioTableItemId() == servicio.getTableItemId()) {
					return gastos[i].getPpto_GastoSC();
				}*/
				Ppto_GastoSC gasto = new Ppto_GastoSC();
				
				gasto.setCosto(gastos[i].getCosto());
				gasto.setDetalle(servicio.getDetalle());
				gasto.setPrecio(gastos[i].getPrecio());
				gasto.setProveedor(gastos[i].getProveedor());
			}
		}
		return null;
	}

	/**
	 * Crea los accesorios de los servicios
	 * 
	 * @param servicioAbs
	 * @param servicio
	 */
	/*private void crearAccesorios(ServicioHelper servicioAbs, Ppto_Sala_Servicio servicio) {

		if (servicioAbs.getAccesorios() != null) {

			for (AccesorioHelper accAbs : servicioAbs.getAccesorios()) {

				if (accAbs.getCantidad() > 0) {

					Ppto_Sala_Servicio_Accesorio accesorio = new Ppto_Sala_Servicio_Accesorio();

					accesorio.setAccesorio((AccInstalacion) session.load(AccInstalacion.class, Integer.toString(accAbs.getIdAccesorio())));
					accesorio.setCantidad(accAbs.getCantidad());
					accesorio.setServicioDeSala(servicio);

					servicio.addAccesorio(accesorio);
				} else {

				}
			}
		}

	}*/

	/**
	 * Agrega al presupuesto los gastos subcontratados.
	 * 
	 * @param p
	 * @param pa
	 */
	private void crearGastosSC(Presupuesto p, PresupuestoXML pa) {		
		// inicializo el set
		if (p.getGastosSC() == null){
			p.setGastosSC(new HashSet<Ppto_GastoSC>());
		}else{
			p.getGastosSC().clear();
		}
		GastosContratacionXML[] gastos = pa.getGastosContratacion();

		if (gastos != null) {
			for (GastosContratacionXML gastoHelper : gastos) {
				
				Ppto_GastoSC gastoSC = new Ppto_GastoSC();

				gastoSC.setCosto(gastoHelper.getCosto());
				gastoSC.setDetalle(gastoHelper.getDetalle());
				gastoSC.setPrecio(gastoHelper.getPrecio());
				gastoSC.setProveedor(gastoHelper.getProveedor());
				gastoSC.setCantidad(gastoHelper.getCantidad());
				gastoSC.setSala(gastoHelper.getSala());
				gastoSC.setProveedor(gastoHelper.getCodigoProveedor());
				
				p.addGastoSC(gastoSC);
			}
		}
	}

	/**
	 * Crea los gastos del Operador
	 * 
	 * @param p
	 * @param pa
	 */
	/*private void crearGastosOperador(Presupuesto p, PresupuestoHelper pa) {
		// inicializo el set
		if (p.getGastosOperador() == null){
			p.setGastosOperador(new HashSet<Ppto_GastoOperador>());
		}else{
			p.getGastosOperador().clear();
		}
		if (pa.getGastosOperador() != null) {
			Ppto_GastoOperador[] gastos = pa.getGastosOperador();

			for (int i = 0; i < gastos.length; i++) {

				p.addGastoOperador(gastos[i]);
			}
		}
	}

	/**
	 * Crea los gastos por la representacion
	 * 
	 * @param p
	 * @param pa
	 *
	private void crearGastosRepresentacion(Presupuesto p, PresupuestoHelper pa) {
		// inicializo el set
		if (p.getGastosRepresentacion() == null){
			p.setGastosRepresentacion(new HashSet<Ppto_GastoRepresentacion>());
		}else{
			p.getGastosRepresentacion().clear();
		}
		if (pa.getGastosRepresentacion() != null) {
			Ppto_GastoRepresentacion[] gastos = pa.getGastosRepresentacion();

			for (int i = 0; i < gastos.length; i++) {

				p.addGastoRepresentacion(gastos[i]);
			}
		}
	}

	/**
	 * Creando gastos de asistentes
	 * 
	 * @param p
	 * @param pa
	 *
	private void crearGastosAsistentes(Presupuesto p, PresupuestoHelper pa) {
		// inicializo el set
		if (p.getGastosAsistentes() == null){
			p.setGastosAsistentes(new HashSet<Ppto_GastoAsistentes>());
		}else{
			p.getGastosAsistentes().clear();
		}
		if (pa.getGastosAsistentes() != null) {
			Ppto_GastoAsistentes[] gastos = pa.getGastosAsistentes();

			for (int i = 0; i < gastos.length; i++) {

				p.addGastoAsistentes(gastos[i]);
			}
		}
	}

	/**
	 * Crea los gastos por viaticos
	 * 
	 * @param p
	 * @param pa
	 *
	private void crearGastosViaticos(Presupuesto p, PresupuestoHelper pa) {
		// inicializo el set
		if (p.getGastosViaticos() == null){
			p.setGastosViaticos(new HashSet<Ppto_GastoViaticos>());
		}else{
			p.getGastosViaticos().clear();
		}
		if (pa.getGastosViaticos() != null) {
			Ppto_GastoViaticos[] gastos = pa.getGastosViaticos();

			for (int i = 0; i < gastos.length; i++) {

				p.addGastoViaticos(gastos[i]);
			}
		}
	}

	/**
	 * Creando gastos de Hoteleria
	 * 
	 * @param p
	 * @param pa
	 *
	private void crearGastosHoteleria(Presupuesto p, PresupuestoHelper pa) {
		// inicializo el set
		if (p.getGastosHoteleria() == null){
			p.setGastosHoteleria(new HashSet<Ppto_GastoHoteleria>());
		}else{
			p.getGastosHoteleria().clear();
		}
		if (pa.getGastosHoteleria() != null) {
			Ppto_GastoHoteleria[] gastos = pa.getGastosHoteleria();

			for (int i = 0; i < gastos.length; i++) {

				p.addGastoHoteleria(gastos[i]);
			}
		}
	}

	/**
	 * Creando gastos varios
	 * 
	 * @param p
	 * @param pa
	 *
	private void crearGastosVarios(Presupuesto p, PresupuestoHelper pa) {
		// inicializo el set
		if (p.getGastosVarios() == null){
			p.setGastosVarios(new HashSet<Ppto_GastoVarios>());
		}else{
			p.getGastosVarios().clear();
		}
		if (pa.getGastosVarios() != null) {
			Ppto_GastoVarios[] gastos = pa.getGastosVarios();

			for (int i = 0; i < gastos.length; i++) {

				p.addGastoVarios(gastos[i]);
			}
		}
	}	*/

}
