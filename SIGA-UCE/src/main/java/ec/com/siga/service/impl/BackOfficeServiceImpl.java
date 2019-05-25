package ec.com.siga.service.impl;


import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ec.com.siga.entity.Auditor;
import ec.com.siga.entity.BackOffice;
import ec.com.siga.entity.CheckList;
import ec.com.siga.entity.DatoComun;
import ec.com.siga.entity.Informe;
import ec.com.siga.entity.SolicitudAuditoria;
import ec.com.siga.model.SolucitudAuditoriaString;
import ec.com.siga.repository.AuditorRepository;
import ec.com.siga.repository.BackRepository;
import ec.com.siga.repository.CheckListRepository;
import ec.com.siga.repository.DatoComunRepository;
import ec.com.siga.repository.EstadoAuditRepository;
import ec.com.siga.repository.InformeRepository;
import ec.com.siga.repository.RoleSysJpaRepository;
import ec.com.siga.repository.SolicitudAuditoriaRepository;
import ec.com.siga.service.BackOfficeService;
import ec.com.siga.service.InformeService;

@Service("backOfficeService")
public class BackOfficeServiceImpl implements BackOfficeService {

	@Autowired
	@Qualifier("auditorRepository")
	private AuditorRepository auditorRepository;
	
	@Autowired
	@Qualifier("backRepository")
	private BackRepository backRepository;
	
	@Autowired
	@Qualifier("roleSysRepository")
	private RoleSysJpaRepository roleSysRepository;
	
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
	@Qualifier("checkListRepository")
	private CheckListRepository checkListRepository;
	
	@Autowired
	@Qualifier("informeServicio")
	private InformeService informeServicio;

	@Override
	public List<Auditor> findAllAudit() {
		return auditorRepository.findAll();
	}

	@Override
	public void saveInforme(int informeId, String auditorId, SolucitudAuditoriaString saAux) throws Exception {
		Informe informe = informeServicio.findReport(informeId);
		Auditor auditor = auditorRepository.findById(Integer.parseInt(auditorId)).get();
		DatoComun dc = informe.getDatoComunId();
		SolicitudAuditoria sa = dc.getSolicitudAuditoriaId();
		sa.setEstadoAuditoriaId(estadoAuditRepository.findById(2).get());
		sa.setFechaInicio(new SimpleDateFormat("yyyy-MM-dd").parse(saAux.getAuxfechaInicio()));
		sa.setFechaFinal(new SimpleDateFormat("yyyy-MM-dd").parse(saAux.getAuxfechaFinal()));
		solicitudAuditoriaRepository.save(sa);
		dc.setHoraInicio(new SimpleDateFormat("HH:mm").parse(saAux.getAuxhoraInicio()));
		dc.setHoraFin(new SimpleDateFormat("HH:mm").parse(saAux.getAuxhoraFin()));
		dComunRepository.save(dc);
		
		informe.setAuditorId(auditor);
		
		informeRepository.save(informe);
	}

	@Override
	public List<CheckList> findAllCheckList(SolicitudAuditoria sa) {
		return checkListRepository.findAllBySolicitudAuditoriaId(sa);
	}

	@Override
	public List<BackOffice> findAllBack() {
		return backRepository.findAll();
	}

	@Override
	public void saveBack(BackOffice back) {
		backRepository.save(back);
		
	}

	@Override
	public BackOffice findBack(Integer id) {
		return backRepository.findById(id).get();
	}
	
		
}
