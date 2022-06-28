package crm.client.serializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import crm.client.helper.PresupuestoBuilder;
import crm.client.helper.PresupuestoBuilderXML;
import crm.client.managers.ClienteManager;
import crm.client.managers.PresupuestosManager;
import crm.client.managers.VendedorManager;
import crm.libraries.abm.entities.Cliente;
import crm.libraries.abm.entities.LugarEvento;
import crm.libraries.abm.entities.MonedaExtranjera;
import crm.libraries.abm.entities.Ppto_GastoSC;
import crm.libraries.abm.entities.Presupuesto;
import crm.libraries.abm.entities.PrtPptoCancelacion;
import crm.libraries.abm.entities.PrtPptoCondPago;
import crm.libraries.abm.entities.PrtPptoFPago;
import crm.libraries.abm.entities.PrtPptoHeader;
import crm.libraries.abm.entities.PrtPptoPeriodo;
import crm.libraries.abm.entities.PrtPptoTipoPresupuesto;
import crm.libraries.abm.entities.PrtPptoValidez;
import crm.libraries.abm.entities.TipoEvento;
import crm.libraries.abm.entities.TipoLugarEvento;
import crm.libraries.abm.entities.TipoUniforme;
import crm.libraries.abm.entities.Vendedor;
import crm.libraries.abm.entities.VendedorPpto;
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
import crm.libraries.abm.helper.RentabilidadHelper;
import crm.libraries.abm.helper.SalaHelper;
import crm.libraries.abm.helper.ServicioHelper;
import crm.services.util.DateConverter;



public class SerializerManager {
	
	private Document document;
	private Presupuesto presupuesto;
	
	public void guardarXML(){
		try{
			XMLOutputter outp = new XMLOutputter(Format.getPrettyFormat());	
			FileWriter writer=null;
			if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win"))
				writer = new FileWriter("c:/temp.xml");
			else{
				
				writer = new FileWriter("/crm/temp.xml");
			}
				
			outp.output(document, writer);			
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void createXML(PresupuestoBuilderXML p){
		Element root=new Element("Presupuesto");
	    document = new Document(root);
	    
	    Element raiz=document.getRootElement();		
		
		PresupuestoXML xml = new PresupuestoXML();
		xml.setActivo(p.getPresupuesto().getActivo());
		
		xml.setCodigoCliente(p.getPresupuesto().getCodigoCliente()); 
		xml.setObservacionesDelCliente(p.getPresupuesto().getObservacionesDelCliente());
		xml.setCodigoReferencia(p.getPresupuesto().getCodigoReferencia());		
		xml.setResponsableEvento(p.getPresupuesto().getResponsableEvento());
		xml.setResponsableEmail(p.getPresupuesto().getResponsableEmail());
		xml.setResponsableTel(p.getPresupuesto().getResponsableTel());		
		xml.setResponsableNextelFlota(p.getPresupuesto().getResponsableNextelFlota());
		xml.setResponsableNextelId(p.getPresupuesto().getResponsableNextelId());				
		xml.setFechaDeInstalacion(p.getPresupuesto().getFechaDeInstalacion());
		xml.setCodigoTipoDeEvento(p.getPresupuesto().getCodigoTipoDeEvento());
		xml.setCodigoTipoDeLugarDelEvento(p.getPresupuesto().getCodigoTipoDeLugarDelEvento());
		xml.setCodigoUniforme(p.getPresupuesto().getCodigoUniforme());
		xml.setTotalDePersonas(p.getPresupuesto().getTotalDePersonas());
		xml.setNombreDelEvento(p.getPresupuesto().getNombreDelEvento());
		xml.setObservacionesDelEvento(p.getPresupuesto().getObservacionesDelEvento());		
		xml.setCodigoLugarDelEvento(p.getPresupuesto().getCodigoLugarDelEvento());	
		if(p.getPresupuesto().getCotizacion()!=null)
			xml.setCotizacion(p.getPresupuesto().getCotizacion());
		xml.setCodigoEncabezado(p.getPresupuesto().getCodigoEncabezado());
		xml.setCodigoFormaPago(p.getPresupuesto().getCodigoFormaPago());
		xml.setCodigoPeriodo(p.getPresupuesto().getCodigoPeriodo());
		xml.setCodigoTipoPpto(p.getPresupuesto().getCodigoTipoPpto());
		xml.setCodigoValidez(p.getPresupuesto().getCodigoValidez());
		xml.setCodigoMoneda(p.getPresupuesto().getCodigoMoneda());
		xml.setCodigoCancelacion(p.getPresupuesto().getCodigoCancelacion());
		xml.setCodigoCondPago(p.getPresupuesto().getCodigoCondPago());			
		xml.setFechaDeInicio(p.getPresupuesto().getFechaDeInicio());
		xml.setFechaDeFinalizacion(p.getPresupuesto().getFechaDeFinalizacion());		
		xml.setCodigoUsuario(p.getPresupuesto().getCodigoUsuario());
		xml.setCodigoVendedor(p.getPresupuesto().getCodigoVendedor());
		xml.setNumeroDePresupuesto(String.valueOf(p.getPresupuesto().getNumeroDePresupuesto()));
		
		xml.setAgregados(createAgregados(p.getPresupuesto().getAgregado()));	
		xml.setAdelanto(createAdelanto(p.getPresupuesto().getAdelanto()));
		xml.setClienteFacturacion(createClienteFacturacion(p.getPresupuesto().getFacturacion()));
		xml.setContactoCliente(createContactoCliente(p.getPresupuesto().getContacto()));
		xml.setContactoLugar(createContactoLugar(p.getPresupuesto().getContactoLugar()));
		xml.setEstado(createEstado(p.getPresupuesto().getEstado()));
		xml.setPago(createPago(p.getPresupuesto().getPago()));
		xml.setRentabilidad(createRentabilidad(p.getPresupuesto().getRentabilidad()));		
		
		//for(int i=0; i<p.getPresupuesto().getSalas().length; i++){
			xml.setSalas(createSalas(p.getPresupuesto().getSalas()));
		//}
		
		//for(int i=0; i<p.getPresupuesto().getGastosContratHelper().length; i++){
			xml.setGastosContratacion(createGastoContratacion(p.getPresupuesto().getGastosSubcontratados()));
		//}
		
		raiz.addContent(xml);

		PresupuestoOutput salida = new PresupuestoOutput();
		
		try {
			salida.open();
			salida.save(xml);
			salida.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private AgregadosXML createAgregados(AgregadoHelper helper){
		AgregadosXML agregadoXML = new AgregadosXML();
		if(helper != null){
			agregadoXML.setCodigoCategoriaEvento(String.valueOf(helper.getCategoriaEvento()));
			agregadoXML.setCodigoModoIngreso(String.valueOf(helper.getModoIngreso()));
			agregadoXML.setCodigoSeguridadIngreso(String.valueOf(helper.getSeguridadIngreso()));			
		}
		return agregadoXML;
	}
	
	private ContactoClienteXML createContactoCliente(ContactoHelper helper){
		ContactoClienteXML contactoClienteXML = new ContactoClienteXML();
		
		if(helper != null){
			contactoClienteXML.setCodigoContactoCliente(String.valueOf(helper.getCodContacto()));
		}
		
		return contactoClienteXML;
	}
	
	private ContactoLugarXML createContactoLugar(ContactoLugarHelper helper){
		ContactoLugarXML contactoLugarXML = new ContactoLugarXML();
		
		if(helper != null){
			contactoLugarXML.setCodigoContactoLugar(String.valueOf(helper.getCodContacto()));
		}
		
		return contactoLugarXML;
	}
	
	private AdelantoXML createAdelanto(AdelantoHelper helper){
		AdelantoXML adelanto = new AdelantoXML();
		if(helper != null){
			adelanto.setPorcentaje(String.valueOf(helper.getPorcentaje()));
			adelanto.setValor(String.valueOf(helper.getValor()));
		}
		
		return adelanto;
	}
	
	private ClienteFacturacionXML createClienteFacturacion(FacturacionHelper helper){
		ClienteFacturacionXML clienteFacturacionXML = new ClienteFacturacionXML();
		
		if(helper != null){
			clienteFacturacionXML.setCodigoClienteFacturacion(String.valueOf(helper.getCodCliente()));
		}
		
		return clienteFacturacionXML;
	}
	private EstadoXML createEstado(EstadoActualHelper helper){
		EstadoXML estadoXML = new EstadoXML();
		if(helper != null){
			estadoXML.setActualizado(String.valueOf(helper.getActualizado()));
			estadoXML.setAdelantado(String.valueOf(helper.getAdelantado()));
			estadoXML.setAdelanto(String.valueOf(helper.getAdelanto()));
			estadoXML.setCancelado(String.valueOf(helper.getCancelado()));
			estadoXML.setCobrado(String.valueOf(helper.getCobrado()));
			estadoXML.setConfirmado(String.valueOf(helper.getConfirmado()));
			estadoXML.setFacturado(String.valueOf(helper.getFacturado()));
			estadoXML.setNuevo(String.valueOf(helper.getNuevo()));
			estadoXML.setOrdenDeCompra(String.valueOf(helper.getOrdenDeCompra()));
			estadoXML.setOrdenDeFacturacion(String.valueOf(helper.getOrdenDeFacturacion()));
			estadoXML.setOrdenDeServicio(String.valueOf(helper.getOrdenDeServicio()));
			estadoXML.setRechazado(String.valueOf(helper.getRechazado()));			
		}
		
		return estadoXML;
	}
	
	private PagoXML createPago(PagoHelper helper){
		PagoXML pagoXML = new PagoXML();
		
		if(helper != null){
			pagoXML.setCodigoCondicionPago(String.valueOf(helper.getCodCondicionPago()));
			pagoXML.setCodigoMedioPago(String.valueOf(helper.getCodMedioPago()));
		}
		
		return pagoXML;
	}
	
	private RentabilidadXML createRentabilidad(RentabilidadHelper helper){
		RentabilidadXML rentabilidadXML = new RentabilidadXML();
		
		if(helper != null){
			rentabilidadXML.setComisionesLugar(String.valueOf(helper.getComisionesLugar()));
			rentabilidadXML.setComisionesTerceros(String.valueOf(helper.getComisionesTerceros()));
			rentabilidadXML.setCostoOperativo(String.valueOf(helper.getCostoOperativo()));
			rentabilidadXML.setFacturacionExtra(String.valueOf(helper.getFacturacionExtra()));
			rentabilidadXML.setFacturacionOriginal(String.valueOf(helper.getFacturacionOriginal()));
			rentabilidadXML.setGastosAsistentes(String.valueOf(helper.getGastosAsistentes()));
			rentabilidadXML.setGastosContrataciones(String.valueOf(helper.getGastosContrataciones()));
			rentabilidadXML.setGastosOperadores(String.valueOf(helper.getGastosOperadores()));
			rentabilidadXML.setGastosOtros(String.valueOf(helper.getGastosOtros()));
		}
		
		return rentabilidadXML;
	}
	
	private SalasXML[] createSalas(SalaHelper[] helper){
		
		SalasXML[] salaXML = new SalasXML[helper.length];
		
		for(int i = 0; i<helper.length;i++){
			if(helper != null){
				SalasXML sala = new SalasXML();
				sala.setCodigoSalaLugar(helper[i].getCodigoSalaLugar());
				sala.setFechaDeFinalizacion(helper[i].getFechaDeFinalizacion());
				sala.setFechaDeInicio(helper[i].getFechaDeInicio());
				sala.setFechaDeInstalacion(helper[i].getFechaDeInstalacion());
				sala.setObservaciones(helper[i].getObservaciones());
				sala.setOrden(String.valueOf(helper[i].getOrden()));
				sala.setTotalPersonas(helper[i].getTotalDePersonas());
				sala.setServicios(createServicios(helper[i].getServicios()));
				if(helper[i].getTipoArmado() != null)
					sala.setTipoArmado(helper[i].getTipoArmado());
				sala.setFechaDesarme(helper[i].getFechaDesarme());
				sala.setFechaPrueba(helper[i].getFechaPrueba());
				
				//sala.setAgregados(createAgregadosSala(helper[i].getAgregado()));
				salaXML[i] = sala;
			}
		}
		return salaXML;
	}
	
	private GastosContratacionXML[] createGastoContratacion2(GastoContratHelper[] helper){
		GastosContratacionXML[] gastosXML = new GastosContratacionXML[helper.length];
		for(int i = 0; i<helper.length;i++){
		if(helper != null){
			GastosContratacionXML gastos = new GastosContratacionXML();
			gastos.setCodigoProveedor(helper[i].getPpto_GastoSC().getProveedor());
			gastos.setCosto(helper[i].getPpto_GastoSC().getCosto());
			gastos.setPrecio(helper[i].getPpto_GastoSC().getPrecio());
			gastos.setDetalle(helper[i].getPpto_GastoSC().getDetalle());
			//gastos.setServicio(helper[i].getPpto_GastoSC().getPpto_Sala_Servicio().getServicio().getCodigo());
			gastosXML[i] = gastos;
		}
		}
		return gastosXML;
	}
	
	private GastosContratacionXML[] createGastoContratacion(Ppto_GastoSC[] helper){
		GastosContratacionXML[] gastosXML = new GastosContratacionXML[helper.length];
		for(int i = 0; i<helper.length;i++){
		if(helper != null){
			GastosContratacionXML gastos = new GastosContratacionXML();
			gastos.setCodigoProveedor(helper[i].getProveedor());
			gastos.setCosto(helper[i].getCosto());
			gastos.setPrecio(helper[i].getPrecio());
			gastos.setDetalle(helper[i].getDetalle());
			gastos.setCantidad(helper[i].getCantidad());
			gastos.setSala(helper[i].getSala());
			//gastos.setServicio(helper[i].getPpto_GastoSC().getPpto_Sala_Servicio().getServicio().getCodigo());
			gastosXML[i] = gastos;
		}
		}
		return gastosXML;
	}
	
	private AgregadosSalaXML createAgregadosSala(AgregadoSalaHelper helper){
		AgregadosSalaXML agregadoSalaXML = new AgregadosSalaXML();
		
		if(helper != null){
			agregadoSalaXML.setCodigoTipoArmado(helper.getCodigoTipoArmado());
			agregadoSalaXML.setFechaDesarme(helper.getFechaDesarme());
			agregadoSalaXML.setFechaPrueba(helper.getFechaPrueba());
		}
		
		return agregadoSalaXML;
	}
	
	private ServiciosXML[] createServicios(ServicioHelper[] helper){
		ServiciosXML[] servicioXML = new ServiciosXML[helper.length];
		
		for(int i = 0; i<helper.length;i++){
		if(helper != null){
			ServiciosXML servicio = new ServiciosXML();
			
			servicio.setCantidad(String.valueOf(helper[i].getCantidad()));
			servicio.setCodigoServicio(String.valueOf(helper[i].getCodigoServicio()));
			servicio.setDetalle(helper[i].getDetalle());
			servicio.setDias(String.valueOf(helper[i].getDias()));
			servicio.setDescuento(String.valueOf(helper[i].getDescuento()));
			servicio.setFechaAlta(helper[i].getFechaAlta());
			servicio.setOrden(String.valueOf(helper[i].getOrden()));
			servicio.setModalidadContratacion(String.valueOf(helper[i].getCodigoModalidadContratacion()));
			servicio.setPrecioConDescuento(String.valueOf(helper[i].getPrecioDescuento()));
			servicio.setPrecioSinDescuento(String.valueOf(helper[i].getPrecioDeLista()));
			if(helper[i].getDescDetallada() != null){
				//for(int j=0; j<helper[i].getDescDetallada().length; j++){
					//DescripcionDetalladaXML desc = new DescripcionDetalladaXML();
					//desc.setDescripcion(helper[i].getDescDetallada()[j].getDescripcion());
					servicio.setDescripcionDetallada(createDescripcionDetallada(helper[i].getDescDetallada()));
				//}
			}
			servicioXML[i] = servicio;
		}
		}
		return servicioXML;
	}
	
	private DescripcionDetalladaXML[] createDescripcionDetallada(DescDetalladaServicioHelper[] helper){
		DescripcionDetalladaXML[] descXML = new DescripcionDetalladaXML[helper.length];
		
		for(int i=0; i<helper.length; i++){
			DescripcionDetalladaXML desc = new DescripcionDetalladaXML();
			desc.setDescripcion(helper[i].getDescripcion());
			descXML[i] = desc;
		}
		return descXML;
	}

	public Presupuesto openXML(){

		try{
			PresupuestoXML p; 
			PresupuestoInput input = new PresupuestoInput();
			input.open();
		
			p = input.recover();
			
			Presupuesto presupuesto = PresupuestoUtilXML.instance().toPresupuesto(p);		
			
			input.close();
			
			return presupuesto;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	}
	
	/**
	 * @return Returns the presuXML.
	 */
	public Presupuesto getPresupuesto() {
		return presupuesto;
	}
}
