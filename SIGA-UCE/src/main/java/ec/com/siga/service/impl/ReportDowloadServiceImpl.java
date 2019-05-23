package ec.com.siga.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ec.com.siga.entity.Informe;
import ec.com.siga.repository.CheckListRepository;
import ec.com.siga.repository.EntregableRepository;
import ec.com.siga.repository.InformeRepository;
import ec.com.siga.service.ReportDowloadService;

@Service("reportDowloadService")
public class ReportDowloadServiceImpl implements ReportDowloadService {

	@Autowired
	@Qualifier("informeRepository")
	private InformeRepository informeRepository;

	@Autowired
	@Qualifier("checkListRepository")
	private CheckListRepository checkListRepository;

	@Autowired
	@Qualifier("entregableRepository")
	private EntregableRepository entregableRepository;

	@Override
	public byte[] reportDowloar(Integer informeId) {
		Informe inf = informeRepository.findById(informeId).get();
		return inf.getEntregableId().getInforme();
	}
	
	@Override
	public byte[] reportDowloarCertificate(Integer informeId) {
		Informe inf = informeRepository.findById(informeId).get();
		return inf.getEntregableId().getCertificado();
	}

}
