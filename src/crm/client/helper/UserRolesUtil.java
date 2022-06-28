package crm.client.helper;

import crm.libraries.abm.entities.Usuario;
import crm.services.sei.PerfilManagerSEI;

public class UserRolesUtil {

	
	public static boolean canAddPresupuesto(Usuario user){
		if(user.getPerfil().equals(PerfilManagerSEI.PERFIL_ADMIN)){
			return false;
		}else if(user.getPerfil().equals(PerfilManagerSEI.PERFIL_VENDEDOR)){
			return true;
		}else if(user.getPerfil().equals(PerfilManagerSEI.PERFIL_SUPERVISOR)){
			return true;
		}		
		return false;
	}
	
	public static boolean canEditPresupuesto(Usuario user){
		if(user.getPerfil().equals(PerfilManagerSEI.PERFIL_ADMIN)){
			return false;
		}else if(user.getPerfil().equals(PerfilManagerSEI.PERFIL_VENDEDOR)){
			return true;
		}else if(user.getPerfil().equals(PerfilManagerSEI.PERFIL_SUPERVISOR)){
			return true;
		}else if(user.getPerfil().equals(PerfilManagerSEI.PERFIL_GERENCIA_COMERCIAL)){
			return true;
		}else if(user.getPerfil().equals(PerfilManagerSEI.PERFIL_COLD)){
			return true;
		}			
		return false;
	}
	
	public static boolean canSeeMisPresupuestos(Usuario user){
		if(user.getPerfil().equals(PerfilManagerSEI.PERFIL_VENDEDOR)){
			return true;
		}else if(user.getPerfil().equals(PerfilManagerSEI.PERFIL_SUPERVISOR)){
			return true;
		}		
		return false;
	}
	
	public static boolean isCold(Usuario user){
		return (user.getPerfil().equals(PerfilManagerSEI.PERFIL_COLD));
	}
	
	public static boolean isAdmin(Usuario user){
		return (user.getPerfil().equals(PerfilManagerSEI.PERFIL_ADMIN));
	}
	
	public static boolean isGerenciaComercial(Usuario user){
		return (user.getPerfil().equals(PerfilManagerSEI.PERFIL_GERENCIA_COMERCIAL));
	}
	
	public static boolean isGerenciaAdm(Usuario user){
		return (user.getPerfil().equals(PerfilManagerSEI.PERFIL_GERENCIA_ADMINISTRATIVA));
	}
	
	public static boolean isSupervisor(Usuario user){
		return (user.getPerfil().equals(PerfilManagerSEI.PERFIL_SUPERVISOR));
	}
	
	public static boolean isVendedor(Usuario user){
		return (user.getPerfil().equals(PerfilManagerSEI.PERFIL_VENDEDOR));
	}
	
	public static boolean isFacturacionUser(Usuario user){
		return (user.getPerfil().equals(PerfilManagerSEI.PERFIL_FACTURACION));
	}
	
	public static boolean isCobranzasUser(Usuario user){
		return (user.getPerfil().equals(PerfilManagerSEI.PERFIL_COBRANZAS));
	}
	
	public static boolean isDepositosUser(Usuario user){
		return (user.getPerfil().equals(PerfilManagerSEI.PERFIL_DEPOSITOS));
	}
	
	public static boolean isGastosUser(Usuario user){
		return (user.getPerfil().equals(PerfilManagerSEI.PERFIL_GASTOS));
	}
	
}
