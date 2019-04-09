package ec.com.siga.service;

import java.util.List;

import ec.com.siga.entity.Cliente;
import ec.com.siga.entity.ClienteTipo;
import ec.com.siga.entity.DatoComun;
import ec.com.siga.entity.TipoAuditoria;
import ec.com.siga.model.SolucitudAuditoriaString;

public interface AuditService {
	public abstract List<TipoAuditoria> findAllTipoAuditoria();
	public abstract List<ClienteTipo> findAllTipoCliente();
	public abstract void solicitudAuditoria(Cliente cliente, DatoComun datoComun, SolucitudAuditoriaString sas);
	
}
