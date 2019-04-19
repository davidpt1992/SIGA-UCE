package ec.com.siga.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ec.com.siga.entity.Auditor;
import ec.com.siga.entity.DatoComun;
import ec.com.siga.entity.Informe;
import ec.com.siga.entity.SolicitudAuditoria;
import ec.com.siga.repository.AuditorRepository;
import ec.com.siga.repository.DatoComunRepository;
import ec.com.siga.repository.EstadoAuditRepository;
import ec.com.siga.repository.InformeRepository;
import ec.com.siga.repository.SolicitudAuditoriaRepository;
import ec.com.siga.service.BackOfficeService;
import ec.com.siga.service.InformeService;

@Service("backOfficeService")
public class BackOfficeServiceImpl implements BackOfficeService {

	@Autowired
	@Qualifier("auditorRepository")
	private AuditorRepository auditorRepository;
	
	@Autowired
	@Qualifier("estadoAuditRepository")
	private EstadoAuditRepository estadoAuditRepository;
	
	@Autowired
	@Qualifier("informeRepository")
	private InformeRepository informeRepository;
	
	@Autowired
	@Qualifier("dComunRepository")
	private DatoComunRepository dComunRepository;
	
	@Autowired
	@Qualifier("solicitudAuditoriaRepository")
	private SolicitudAuditoriaRepository solicitudAuditoriaRepository;
	
	@Autowired
	@Qualifier("informeServicio")
	private InformeService informeServicio;

	@Override
	public List<Auditor> findAllAudit() {
		return auditorRepository.findAll();
	}

	@Override
	public void saveInforme(int informeId, String auditorId) {
		Informe informe = informeServicio.findReport(informeId);
		Auditor auditor = auditorRepository.findById(Integer.parseInt(auditorId)).get();
		DatoComun dc = informe.getDatoComunId();
		SolicitudAuditoria sa = dc.getSolicitudAuditoriaId();
		sa.setEstadoAuditoriaId(estadoAuditRepository.findById(2).get());
		solicitudAuditoriaRepository.save(sa);
		
		informe.setAuditorId(auditor);
		
		informeRepository.save(informe);
	}
	
		
}
