package ec.com.siga.serviceMovil.iml;


import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ec.com.siga.entity.CheckList;
import ec.com.siga.entity.Informe;
import ec.com.siga.entity.SolicitudAuditoria;
import ec.com.siga.model.AuditDTO;
import ec.com.siga.model.QuestionDTO;
import ec.com.siga.repository.AuditorRepository;
import ec.com.siga.repository.CheckListRepository;
import ec.com.siga.repository.DatoComunRepository;
import ec.com.siga.repository.EstadoAuditRepository;
import ec.com.siga.repository.InformeRepository;
import ec.com.siga.repository.SolicitudAuditoriaRepository;
import ec.com.siga.repository.TipoAudiRepository;
import ec.com.siga.repository.TipoCustRepository;
import ec.com.siga.repository.UserJpaRepository;
import ec.com.siga.serviceMovil.AuditServiceMovil;

@Service("auditServiceMovil")
public class AuditServiceMovilImpl implements AuditServiceMovil {

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
	@Qualifier("checkListRepository")
	private CheckListRepository checkListRepository;

	@Override
	public List<AuditDTO> findAllAudits() {
		List<Informe> infs = informeRepository.findByAuditorId(auditorRepository.findByUserId(userRepository.findByUsuario("WCUASAPUD")));
		List<AuditDTO> audits = new ArrayList<AuditDTO>();
		
		for (Informe inf : infs) {
			AuditDTO audit = new AuditDTO();
			audit.setId_informe(String.valueOf(inf.getInformeId()));
			audit.setId_cliente(String.valueOf(inf.getClienteId().getClienteId()));
			audit.setLatitud(inf.getClienteId().getLatitud());
			audit.setLongitud(inf.getClienteId().getLongitud());
			audit.setEmpresa(inf.getClienteId().getNombreEmpresa());
			audit.setNombreApellido(inf.getClienteId().getUserId().getNombre()+" "+inf.getClienteId().getUserId().getApellido());
			audit.setCorreo(inf.getClienteId().getUserId().getCorreoElectronico());
			audit.setDireccion(inf.getClienteId().getUserId().getDireccion());
			audit.setTelf1(String.valueOf(inf.getClienteId().getUserId().getNumeroTelefono1()));
			audit.setTelf2(String.valueOf(inf.getClienteId().getUserId().getNumeroTelefono2()));
			audit.setFechaIni(String.valueOf(inf.getDatoComunId().getSolicitudAuditoriaId().getFechaInicio()));
			audit.setFechaFin(String.valueOf(inf.getDatoComunId().getSolicitudAuditoriaId().getFechaFinal()));
			audit.setHoraIni(String.valueOf(inf.getDatoComunId().getHoraInicio()));
			audit.setHoraFin(String.valueOf(inf.getDatoComunId().getHoraFin()));
			audit.setId_estado(String.valueOf(inf.getDatoComunId().getSolicitudAuditoriaId().getEstadoAuditoriaId().getEstadoAuditoria()));
			audit.setId_solicitud(String.valueOf(inf.getDatoComunId().getSolicitudAuditoriaId().getSolicitudAuditoriaId()));
			audits.add(audit);
		}
		
		return audits;
	}
	
	@Override
	public QuestionDTO replyMovil(Integer id) {
		SolicitudAuditoria sa = solicitudAuditoriaRepository.findById(id).get();
		List<CheckList> primerChekList = checkListRepository.findAllBySolicitudAuditoriaId(sa);
		CheckList ckl = primerChekList.get(0);
		QuestionDTO quest = new QuestionDTO();
		
		quest.setId_checklist(String.valueOf(ckl.getCheckListId()));
		quest.setCodigo(String.valueOf(ckl.getCodigo()));
		quest.setId_solicitud(String.valueOf(ckl.getSolicitudAuditoriaId().getSolicitudAuditoriaId()));
		quest.setId_pregunta(String.valueOf(ckl.getPreguntasId().getPreguntasId()));
		quest.setPregunta(ckl.getPreguntasId().getPreguntas());
		if (ckl.getDatoEspecificoId() != null) {
			quest.setId_despecifico(String.valueOf(ckl.getDatoEspecificoId().getDatoEspecificoId()));
			quest.setEvidencia(String.valueOf(ckl.getDatoEspecificoId().getEvidencia()));
			quest.setRespuesta(String.valueOf(ckl.getDatoEspecificoId().isRespuesta()));
			if (ckl.getDatoEspecificoId().getFotoId() != null) {
				quest.setId_foto(String.valueOf(ckl.getDatoEspecificoId().getFotoId().getFotoId()));
				quest.setFoto(Base64.encodeBase64String(ckl.getDatoEspecificoId().getFotoId().getFoto()));
			}
		}
		
		return quest;
	}
	
	@Override
	public QuestionDTO replyPostMovil(String codigo) {
		int cod = Integer.valueOf(codigo);
		//problema de null
		CheckList ckl = checkListRepository.findByCodigo(cod + 1);
		QuestionDTO quest = new QuestionDTO();
		
		quest.setId_checklist(String.valueOf(ckl.getCheckListId()));
		quest.setCodigo(String.valueOf(ckl.getCodigo()));
		quest.setId_solicitud(String.valueOf(ckl.getSolicitudAuditoriaId().getSolicitudAuditoriaId()));
		quest.setId_pregunta(String.valueOf(ckl.getPreguntasId().getPreguntasId()));
		quest.setPregunta(ckl.getPreguntasId().getPreguntas());
		if (ckl.getDatoEspecificoId() != null) {
			quest.setId_despecifico(String.valueOf(ckl.getDatoEspecificoId().getDatoEspecificoId()));
			quest.setEvidencia(String.valueOf(ckl.getDatoEspecificoId().getEvidencia()));
			quest.setRespuesta(String.valueOf(ckl.getDatoEspecificoId().isRespuesta()));
			if (ckl.getDatoEspecificoId().getFotoId() != null) {
				quest.setId_foto(String.valueOf(ckl.getDatoEspecificoId().getFotoId().getFotoId()));
				quest.setFoto(Base64.encodeBase64String(ckl.getDatoEspecificoId().getFotoId().getFoto()));
			}
		}
		
		return quest;
	}


	
}
