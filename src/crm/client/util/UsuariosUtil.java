package crm.client.util;

import java.rmi.RemoteException;
import crm.client.managers.UsuarioManager;

public class UsuariosUtil {
	
	public static final int ABM_CLIENTES = 1;
	public static final int ABM_LUGARES_EVENTOS = 2;
	public static final int ABM_SALAS = 3;
	public static final int ABM_FLIAS_SERVICIOS = 4;
	public static final int ABM_SERVICIOS = 5;
	public static final int ABM_FLIAS_ACCESORIOS = 6;
	public static final int ABM_ACC_INSTALACION = 7;
	public static final int ABM_PAISES = 8;
	public static final int ABM_PROVINCIAS = 9;
	public static final int ABM_PARTIDOS = 10;
	public static final int ABM_LOCALIDADES = 11;
	public static final int ABM_CODIGO_POSTAL = 12;
	public static final int ABM_PROVEEDORES = 13;
	public static final int ABM_OPERADORES = 14;
	public static final int ABM_ASISTENTES = 15;
	public static final int ABM_VENDEDORES = 16;
	public static final int ABM_CONTACTO_COMISIONABLE = 17;
	public static final int ABM_UNIDADES_COMERCIALES = 18;
	public static final int ABM_SUCURSALES = 19;
	public static final int ABM_IDIOMAS = 20;
	public static final int ABM_COND_IVA = 21;
	public static final int ABM_TITULOS = 22;
	public static final int ABM_COMISIONES = 23;
	public static final int ABM_USUARIOS = 24;
	public static final int ABM_ACCESOS = 25;
	public static final int ABM_CATEGORIA_VENDEDORES = 26;
	public static final int ABM_CATEGORIA_EVENTOS = 27;
	public static final int COBRANZAS = 28;
	public static final int ABM_COND_PAGO = 29;
	public static final int ABM_COSTO_OPERATIVO = 30;
	public static final int ABM_DESC_PRECIOS = 31;
	public static final int ABM_DIAS_VENCER = 32;
	public static final int ABM_ESTADO_EVENTO = 33;
	public static final int ABM_IDIOMAS_SERVICIOS = 34;
	public static final int ABM_LISTA_PRECIO = 35;
	public static final int ABM_MARCO_LIQUIDACION = 36;
	public static final int ABM_MOD_CONTRATACION = 37;
	public static final int ABM_PERFILES = 38;
	public static final int ABM_RES_SEGUIMIENTO = 39;
	public static final int ABM_SEGUIMIENTOS = 40;
	public static final int ABM_SUBSERVICIOS = 41;
	public static final int ABM_SUPERVISORES = 42;
	public static final int ABM_TIPO_CONTACTO = 43;
	public static final int ABM_TIPO_EVENTO = 44;
	public static final int ABM_TIPO_LUGAR_EVT = 45;
	public static final int ABM_TIPO_UNIFORME = 46;
	public static final int ABM_UNIDAD_NEGOCIO = 47;
	public static final int ABM_PPTO_HEADER = 48;
	public static final int ABM_PPTO_CANCELACION = 49;
	public static final int ABM_PTO_FOOTER = 50;
	public static final int ABM_PPTO_F_PAGO = 51;
	public static final int ABM_PPTO_INSTALACION = 52;
	public static final int ABM_PPTO_VALIDEZ = 53;
	public static final int ABM_PPTO_SIGNATURE = 54;
	public static final int ABM_MODO_INGRESO_EQUIPOS = 55;
	public static final int ABM_SEGURIDAD_EQUIPOS = 56;
	public static final int ABM_TIPO_ARMADO = 57;
	public static final int ABM_PPTO_SEGURIDAD = 58;
	public static final int ABM_PPTO_PERSONAL = 59;
	public static final int ABM_PPTO_COND_RESERVA = 60;
	public static final int ABM_PPTO_TIPO_PRESUPUESTO = 61;
	public static final int ABM_PPTO_PERIODO = 62;
	public static final int ABM_MONEDA_EXTRANJERA = 63;
	public static final int FACTURACION = 64;
	public static final int ABM_PPTO_COND_PAGO = 65;
	public static final int ABM_UNIDADES_ADMINISTRATIVAS = 66;
	
	/*public static final int CONFIRMAR_EVENTO = 25;
	public static final int CANCELAR_EVENTO = 26;
	public static final int CONSULTAR_COMISIONES_OTRAS_UC = 27;*/
	
	
	public static boolean chequearAcceso( long codigoUsuario, 
								  int tipoDeAcceso ){
		boolean resultado = false;
		
		try {
			
			resultado = UsuarioManager.instance().getAccessTo(codigoUsuario,tipoDeAcceso);
			
		} catch ( RemoteException e ) {
			
			System.out.println( "No se pudo chequear el acceso: " + e.getMessage() );
			
		}
		
		return resultado;
	}
	
}
