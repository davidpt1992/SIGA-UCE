package ec.com.siga.service;

import java.util.List;

import ec.com.siga.entity.Informe;

public interface InformeService {
	public abstract List<Informe> findAllReport();
	public abstract List<Informe> findAllReportRequests();
	public abstract List<Informe> findAllReportProsesing();
	public abstract List<Informe> findAllCustAudits(String username);
	public abstract List<Informe> findAllCustAuditsNC(String username);
	public abstract Informe findReport(int idReport);
	public abstract void deleteReport(int idReport);
}
