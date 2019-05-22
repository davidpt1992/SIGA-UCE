package ec.com.siga.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import ec.com.siga.entity.CheckList;
import ec.com.siga.entity.DatoComun;
import ec.com.siga.entity.DatoEspecifico;
import ec.com.siga.entity.Entregable;
import ec.com.siga.entity.Foto;
import ec.com.siga.entity.Informe;
import ec.com.siga.entity.Preguntas;
import ec.com.siga.entity.SolicitudAuditoria;
import ec.com.siga.entity.TipoAuditoria;
import ec.com.siga.model.GeneratePdfReport;
import ec.com.siga.repository.AuditorRepository;
import ec.com.siga.repository.CheckListRepository;
import ec.com.siga.repository.DatoComunRepository;
import ec.com.siga.repository.DatoEspecificoRepository;
import ec.com.siga.repository.EntregableRepository;
import ec.com.siga.repository.EstadoAuditRepository;
import ec.com.siga.repository.FotoRepository;
import ec.com.siga.repository.InformeRepository;
import ec.com.siga.repository.PreguntasRepository;
import ec.com.siga.repository.SolicitudAuditoriaRepository;
import ec.com.siga.repository.TipoAudiRepository;
import ec.com.siga.repository.TipoCustRepository;
import ec.com.siga.repository.UserJpaRepository;
import ec.com.siga.service.AuditorService;
import ec.com.siga.service.ReportGenerationService;

@Service("reportGenerationService")
public class ReportGenerationServiceImpl implements ReportGenerationService {

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

	@Autowired
	@Qualifier("entregableRepository")
	private EntregableRepository entregableRepository;

	@Override
	public String reportGeneration(Integer informeId) {
		Informe inf = informeRepository.findById(informeId).get();
		List<CheckList> cl = checkListRepository
				.findAllBySolicitudAuditoriaId(inf.getDatoComunId().getSolicitudAuditoriaId());
		Entregable ent = new Entregable();
		ent.setInforme(GeneratePdfReport.auditoriesReport(cl));
		entregableRepository.save(ent);

		inf.setEntregableId(ent);
		informeRepository.save(inf);

		return "Report Generate";
	}

	@Override
	public String certificationGeneration(Integer informeId) {
		// TODO Auto-generated method stub
		return null;
	}

}
