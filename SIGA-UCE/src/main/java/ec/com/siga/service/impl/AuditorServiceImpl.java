package ec.com.siga.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ec.com.siga.entity.CheckList;
import ec.com.siga.entity.DatoComun;
import ec.com.siga.entity.Informe;
import ec.com.siga.entity.Preguntas;
import ec.com.siga.entity.SolicitudAuditoria;
import ec.com.siga.entity.TipoAuditoria;
import ec.com.siga.repository.AuditorRepository;
import ec.com.siga.repository.CheckListRepository;
import ec.com.siga.repository.DatoComunRepository;
import ec.com.siga.repository.EstadoAuditRepository;
import ec.com.siga.repository.FotoRepository;
import ec.com.siga.repository.InformeRepository;
import ec.com.siga.repository.PreguntasRepository;
import ec.com.siga.repository.SolicitudAuditoriaRepository;
import ec.com.siga.repository.TipoAudiRepository;
import ec.com.siga.repository.TipoCustRepository;
import ec.com.siga.repository.UserJpaRepository;
import ec.com.siga.service.AuditorService;

@Service("auditorService")
public class AuditorServiceImpl implements AuditorService {

	@Autowired
	@Qualifier("tipoAudiRepository")
	private TipoAudiRepository tipoAudiRepository;

	@Autowired
	@Qualifier("tipoCustRepository")
	private TipoCustRepository tipoCustRepository;

	@Autowired
	@Qualifier("solicitudAuditoriaRepository")
	private SolicitudAuditoriaRepository solicitudAuditoriaRepository;

	@Autowired
	@Qualifier("userRepository")
	private UserJpaRepository userRepository;

	@Autowired
	@Qualifier("estadoAuditRepository")
	private EstadoAuditRepository estadoAuditRepository;

	@Autowired
	@Qualifier("dComunRepository")
	private DatoComunRepository dComunRepository;

	@Autowired
	@Qualifier("auditorRepository")
	private AuditorRepository auditorRepository;

	@Autowired
	@Qualifier("informeRepository")
	private InformeRepository informeRepository;

	@Autowired
	@Qualifier("preguntasRepository")
	private PreguntasRepository preguntasRepository;

	@Autowired
	@Qualifier("fotoRepository")
	private FotoRepository fotoRepository;

	@Autowired
	@Qualifier("checkListRepository")
	private CheckListRepository checkListRepository;

	@Override
	public List<Informe> findAllAssignedAudits(String auditor) {
		return informeRepository.findByAuditorId(auditorRepository.findByUserId(userRepository.findByUsuario(auditor)));
	}

	@Override
	public void createCkeckList(int informeId) {
		Informe informe = informeRepository.findById(informeId).get();
		DatoComun dc = informe.getDatoComunId();
		SolicitudAuditoria sa = dc.getSolicitudAuditoriaId();
		String aId = String.valueOf(sa.getSolicitudAuditoriaId());
		String bId, res;
		TipoAuditoria ta = sa.getTipoAuditoriaId();
		List<Preguntas> pre = preguntasRepository.findByTipoAuditoriaId(ta);
		
		
		if (checkListRepository.findAllBySolicitudAuditoriaId(sa).isEmpty()) {
			int j=0;
			for ( Preguntas i : pre ) { 
				j=j+1;
				CheckList cl = new CheckList();
					if (j > 9) {
						bId = String.valueOf(j);
					}else {
						bId = "0"+String.valueOf(j);
					}
					res = aId + bId; 

				cl.setCodigo(Integer.parseInt(res));
				cl.setSolicitudAuditoriaId(sa);
				cl.setPreguntasId(i);
				checkListRepository.save(cl);            
			}
		} 
				
	}

	@Override
	public CheckList reply(int informeId) {
		Informe informe = informeRepository.findById(informeId).get();
		DatoComun dc = informe.getDatoComunId();
		SolicitudAuditoria sa = dc.getSolicitudAuditoriaId();
		List<CheckList> preguntas = checkListRepository.findAllBySolicitudAuditoriaId(sa);
		return preguntas.get(0);
	}
	
	@Override
	public CheckList replyPost(int informeId, String codigo, String accion) {
		int cod = Integer.valueOf(codigo);
		Informe informe = informeRepository.findById(informeId).get();
		DatoComun dc = informe.getDatoComunId();
		SolicitudAuditoria sa = dc.getSolicitudAuditoriaId();
				
		if (accion.equals("+")) {
			return checkListRepository.findByCodigo(cod+1);
		}else {
			return checkListRepository.findByCodigo(cod-1);
		}
	}

}
