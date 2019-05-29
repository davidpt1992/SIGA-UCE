package ec.com.siga.serviceMovil;

import java.util.List;

import ec.com.siga.model.AuditDTO;

public interface AutenticacionServiceMovil {
	public abstract boolean autenticacion(String usuario, String clave);
	
}
