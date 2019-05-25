package ec.com.siga.service;

public interface ReportDowloadService {
	public abstract byte[] reportDowloar(Integer informeId);
	public abstract byte[] reportDowloarCertificate(Integer informeId);
	public abstract byte[] fileDowloarToCheck(Integer id);

}
