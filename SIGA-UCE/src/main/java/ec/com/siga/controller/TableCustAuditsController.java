package ec.com.siga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ec.com.siga.entity.CheckList;
import ec.com.siga.entity.DatoComun;
import ec.com.siga.entity.Informe;
import ec.com.siga.model.SolucitudAuditoriaString;
import ec.com.siga.service.AuditService;
import ec.com.siga.service.AuditorService;
import ec.com.siga.service.CustService;
import ec.com.siga.service.InformeService;
import ec.com.siga.service.ReportDowloadService;
import ec.com.siga.service.UserServicio;

@Controller
public class TableCustAuditsController {

	@Autowired
	@Qualifier("informeServicio")
	private InformeService informeServicio;

	@Autowired
	@Qualifier("auditService")
	private AuditService auditService;

	@Autowired
	@Qualifier("userServicio")
	private UserServicio userServicio;

	@Autowired
	@Qualifier("custService")
	private CustService custService;

	@Autowired
	@Qualifier("auditorService")
	private AuditorService auditorService;

	@Autowired
	@Qualifier("reportDowloadService")
	private ReportDowloadService reportDowloadService;

	@GetMapping("/tableCustAudits")
	@ResponseBody
	public ModelAndView showForm(String usuario) {
		ModelAndView mav = new ModelAndView("tableCustAudits");
		mav.addObject("contacts", informeServicio.findAllCustAudits(usuario));
		mav.addObject("usuario", usuario);
		return mav;
	}

	@GetMapping("/tableCustAuditsNC")
	@ResponseBody
	public ModelAndView showFormToSend(String usuario) {
		ModelAndView mav = new ModelAndView("tableCustAuditsToSend");
		mav.addObject("contacts", informeServicio.findAllCustAuditsNC(usuario));
		mav.addObject("usuario", usuario);
		return mav;
	}

	@GetMapping("/startUploadFiles")
	@ResponseBody
	public ModelAndView startquestionnaire1(int id, String usuario) {
		ModelAndView mav = new ModelAndView("questionnaireUploadFile");
		CheckList cl = auditorService.replyUploadFile(id);
		mav.addObject("pregunta", cl);
		mav.addObject("id", id);
		mav.addObject("usuario", usuario);
		mav.addObject("codigoString", String.valueOf(cl.getCodigo()));
		return mav;
	}

	@PostMapping("/filesSave")
	public ModelAndView nextQuestionPost(int id, String usuario, String codigo, MultipartFile foto, String evidencia,
			boolean respuesta) {
		ModelAndView mav = new ModelAndView("save");
		auditorService.saveReply(foto, evidencia, respuesta, codigo);
		return mav;
	}

	@GetMapping("/sendToCheck")
	@ResponseBody
	public String sendNonConformities(Integer id, String usuario) {
		String msg = auditorService.sendToCheck(id);
		System.out.println(msg);
		return msg;
	}

	@PostMapping("/nextQuestionUploadFile")
	public ModelAndView nextQuest(int id, String usuario, String codigo) {
		ModelAndView mav = new ModelAndView("questionnaireUploadFile");
		String accion = "+";
		CheckList cl = auditorService.replyPostUploadFile(id, codigo, accion);
		mav.addObject("pregunta", cl);
		mav.addObject("id", id);
		mav.addObject("username", usuario);
		mav.addObject("codigoString", String.valueOf(cl.getCodigo()));
		return mav;
	}

	@PostMapping("/previousQuestionUploadFile")
	public ModelAndView preQuest(int id, String usuario, String codigo) {
		ModelAndView mav = new ModelAndView("questionnaireUploadFile");
		String accion = "-";
		CheckList cl = auditorService.replyPostUploadFile(id, codigo, accion);
		mav.addObject("pregunta", cl);
		mav.addObject("id", id);
		mav.addObject("username", usuario);
		mav.addObject("codigoString", String.valueOf(cl.getCodigo()));
		return mav;
	}

	@GetMapping("/editCustAudits")
	@ResponseBody
	public ModelAndView showEditAdminForm(String usuario) {
		ModelAndView mav = new ModelAndView("editCustAudits");
		mav.addObject("tipAudit", auditService.findAllTipoAuditoria());
		mav.addObject("usuario", usuario);
		return mav;
	}

	@PostMapping("/saveCustAudits")
	public ModelAndView saveAdmin(DatoComun datoComun, SolucitudAuditoriaString sas, String usuario, String latitud,
			String longitud) throws Exception {
		ModelAndView mav = new ModelAndView("/dashboardCust");
		mav.addObject("username", usuario);
		custService.custUpdate(usuario, longitud, latitud);
		auditService.solicitudAuditoria(datoComun, sas, usuario);
		return mav;
	}

	@GetMapping("/dowloadReportCertificate")
	@ResponseBody
	public String dowloadReportCertificate(Integer id) {
		return null;
	}

	@GetMapping("/downloadFile")
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(Integer id) {

		return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/pdf"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"reportePrueba.pdf\"")
				.body(new ByteArrayResource(reportDowloadService.reportDowloar(id)));
	}
	@GetMapping("/downloadFileCertificate")
	@ResponseBody
	public ResponseEntity<Resource> downloadFileCertificate(Integer id) {

		return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/pdf"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"certficadoPrueba.pdf\"")
				.body(new ByteArrayResource(reportDowloadService.reportDowloarCertificate(id)));
	}

	@GetMapping("/findCustAudits")
	@ResponseBody
	public Informe findOne(Integer id) {
		return informeServicio.findReport(id);
	}

	@GetMapping("/cancelCustAudits")
	public String cancel() {
		return "redirect:/dashboardAdmin";
	}

	@GetMapping("/deleteCustAudits")
	public void deleteCountry(Integer adminId) {
		informeServicio.deleteReport(adminId);
		;
	}

}
