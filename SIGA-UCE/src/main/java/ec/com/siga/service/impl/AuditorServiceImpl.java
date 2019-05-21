package ec.com.siga.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ec.com.siga.entity.CheckList;
import ec.com.siga.entity.DatoComun;
import ec.com.siga.entity.DatoEspecifico;
import ec.com.siga.entity.Foto;
import ec.com.siga.entity.Informe;
import ec.com.siga.entity.Preguntas;
import ec.com.siga.entity.SolicitudAuditoria;
import ec.com.siga.entity.TipoAuditoria;
import ec.com.siga.repository.AuditorRepository;
import ec.com.siga.repository.CheckListRepository;
import ec.com.siga.repository.DatoComunRepository;
import ec.com.siga.repository.DatoEspecificoRepository;
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
	@Qualifier("dEspecificoRepository")
	private DatoEspecificoRepository dEspecificoRepository;

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
		List<Informe> list, auxList = new ArrayList<Informe>();
		list = informeRepository.findByAuditorId(auditorRepository.findByUserId(userRepository.findByUsuario(auditor)));
		for (Informe info : list) {
			if (info.getDatoComunId().getSolicitudAuditoriaId().getEstadoAuditoriaId().getEstadoAuditoriaId() == 2) {
				auxList.add(info);
			}
		}
		return auxList;
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
			int j = 0;
			for (Preguntas i : pre) {
				j = j + 1;
				CheckList cl = new CheckList();
				if (j > 9) {
					bId = String.valueOf(j);
				} else {
					bId = "0" + String.valueOf(j);
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

		if (accion.equals("+")) {
			return checkListRepository.findByCodigo(cod + 1);
		} else {
			return checkListRepository.findByCodigo(cod - 1);
		}
	}

	@Override
	public void saveReply(MultipartFile f, String evidencia, boolean respuesta, String codigo) {

		Foto foto = new Foto();
		try {
			foto.setFoto(f.getBytes());
			//foto.setFileName(Base64.encodeBase64String(f.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		fotoRepository.save(foto);
		DatoEspecifico de = new DatoEspecifico();
		de.setFotoId(foto);
		de.setEvidencia(evidencia);
		de.setRespuesta(respuesta);
		dEspecificoRepository.save(de);
		CheckList cl = checkListRepository.findByCodigo(Integer.parseInt(codigo));
		cl.setDatoEspecificoId(de);
		checkListRepository.save(cl);
	}

	@Override
	public List<Informe> findAllAuditsHistory(String auditor) {
		List<Informe> list, auxList = new ArrayList<Informe>();
		list = informeRepository.findByAuditorId(auditorRepository.findByUserId(userRepository.findByUsuario(auditor)));
		for (Informe info : list) {
			if (info.getDatoComunId().getSolicitudAuditoriaId().getEstadoAuditoriaId().getEstadoAuditoriaId() > 2) {
				auxList.add(info);
			}
		}
		return auxList;
	}

	@Override
	public String sendNonConformities(Integer informeId) {
		Informe inf = informeRepository.findById(informeId).get();
		DatoComun dc = inf.getDatoComunId();
		SolicitudAuditoria sa = dc.getSolicitudAuditoriaId();
		
		List<CheckList> listChck = checkListRepository.findAllBySolicitudAuditoriaId(inf.getDatoComunId().getSolicitudAuditoriaId());
		String finish = null;
		int finishAux = 0;
		for (CheckList lChck : listChck) 
		{ 
		    if (lChck.getDatoEspecificoId()== null) {
		    	finishAux = 1;
		    	System.out.println("si hay nulo"+finishAux);
			}
		}
		if (finishAux == 1) {
	    	finish = "Have questions whitout reply";
		}else {
			finish = "Request for evidence sent";
			sa.setEstadoAuditoriaId(estadoAuditRepository.findById(3).get());
			dc.setSolicitudAuditoriaId(sa);
			inf.setDatoComunId(dc);
			informeRepository.save(inf);
		}
		return finish;
	}

}
