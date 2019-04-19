package ec.com.siga.service;


import java.util.List;

import ec.com.siga.entity.Auditor;

public interface BackOfficeService {
	public abstract List<Auditor> findAllAudit();
	public abstract void saveInforme(int informeId, String auditorId);
}
