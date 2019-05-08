package ec.com.siga.service;


import java.util.List;

import ec.com.siga.entity.Auditor;
import ec.com.siga.entity.CheckList;
import ec.com.siga.entity.SolicitudAuditoria;
import ec.com.siga.model.SolucitudAuditoriaString;

public interface BackOfficeService {
	public abstract List<Auditor> findAllAudit();
	public abstract List<CheckList> findAllCheckList(SolicitudAuditoria sa);
	public abstract void saveInforme(int informeId, String auditorId, SolucitudAuditoriaString sa)throws Exception;
}
