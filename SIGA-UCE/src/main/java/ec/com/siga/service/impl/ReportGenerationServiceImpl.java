package ec.com.siga.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ec.com.siga.entity.CheckList;
import ec.com.siga.entity.DatoComun;
import ec.com.siga.entity.Entregable;
import ec.com.siga.entity.Informe;
import ec.com.siga.entity.SolicitudAuditoria;
import ec.com.siga.model.GeneratePdfCertificado;
import ec.com.siga.model.GeneratePdfReport;
import ec.com.siga.repository.CheckListRepository;
import ec.com.siga.repository.EntregableRepository;
import ec.com.siga.repository.EstadoAuditRepository;
import ec.com.siga.repository.InformeRepository;
import ec.com.siga.service.ReportGenerationService;

@Service("reportGenerationService")
public class ReportGenerationServiceImpl implements ReportGenerationService {

	@Autowired
	@Qualifier("informeRepository")
	private InformeRepository informeRepository;

	@Autowired
	@Qualifier("checkListRepository")
	private CheckListRepository checkListRepository;

	@Autowired
	@Qualifier("entregableRepository")
	private EntregableRepository entregableRepository;
	
	/**
	 * 
	 */
	@Autowired
	@Qualifier("estadoAuditRepository")
	private EstadoAuditRepository estadoAuditRepository;

	/**
	 * @param informeId id informe
	 */	
	@Override
	public String reportGeneration(Integer informeId) {
		Informe inf = informeRepository.findById(informeId).get();
		DatoComun dc = inf.getDatoComunId();
		dc.setCalificacionFinal(dc.getCalificacion());
		SolicitudAuditoria sa = dc.getSolicitudAuditoriaId();
		sa.setEstadoAuditoriaId(estadoAuditRepository.findById(5).get());
		dc.setSolicitudAuditoriaId(sa);
		inf.setDatoComunId(dc);
		
		List<CheckList> cl = checkListRepository
				.findAllBySolicitudAuditoriaId(inf.getDatoComunId().getSolicitudAuditoriaId());
		Entregable en = entregableRepository.findById(1).get();

		Entregable ent = new Entregable();
		ent.setInforme(GeneratePdfReport.auditoriesReport(inf, cl, en));
		ent.setCertificado(GeneratePdfCertificado.auditoriesCertificate(inf, en));
		entregableRepository.save(ent);

		inf.setEntregableId(ent);
		informeRepository.save(inf);

		return "Report Generate";
	}

	@Override
	public String reportGenerationPost(Integer informeId) {
		Informe inf = informeRepository.findById(informeId).get();
		DatoComun dc = inf.getDatoComunId();
		SolicitudAuditoria sa = dc.getSolicitudAuditoriaId();
		sa.setEstadoAuditoriaId(estadoAuditRepository.findById(5).get());
		dc.setSolicitudAuditoriaId(sa);
		inf.setDatoComunId(dc);
		
		List<CheckList> cl = checkListRepository
				.findAllBySolicitudAuditoriaId(inf.getDatoComunId().getSolicitudAuditoriaId());
		Entregable en = entregableRepository.findById(1).get();

		Entregable ent = new Entregable();
		ent.setInforme(GeneratePdfReport.auditoriesReport(inf, cl, en));
		ent.setCertificado(GeneratePdfCertificado.auditoriesCertificate(inf, en));
		entregableRepository.save(ent);

		inf.setEntregableId(ent);
		informeRepository.save(inf);

		return "Report Generate";
	}

}
