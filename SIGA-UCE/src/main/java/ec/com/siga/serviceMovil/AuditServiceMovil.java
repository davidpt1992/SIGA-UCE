package ec.com.siga.serviceMovil;

import java.util.List;

import ec.com.siga.model.AuditDTO;

public interface AuditServiceMovil {
	public abstract List<AuditDTO> findAllAudits();
	
}
