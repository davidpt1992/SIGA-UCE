package ec.com.siga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ec.com.siga.entity.CheckList;
import ec.com.siga.entity.Informe;
import ec.com.siga.service.AuditService;
import ec.com.siga.service.AuditorService;
import ec.com.siga.service.BackOfficeService;
import ec.com.siga.service.CustService;
import ec.com.siga.service.InformeService;

@Controller
public class TableAuditorAssignedAuditsController {

	@Autowired
	@Qualifier("informeServicio")
	private InformeService informeServicio;

	@Autowired
	@Qualifier("auditService")
	private AuditService auditService;

	@Autowired
	@Qualifier("auditorService")
	private AuditorService auditorService;

	@Autowired
	@Qualifier("backOfficeService")
	private BackOfficeService backOfficeService;

	@Autowired
	@Qualifier("custService")
	private CustService custService;
	
	

	@GetMapping("/tableAssignedAudits")
	@ResponseBody
	public ModelAndView showForm(String usuario) {
		ModelAndView mav = new ModelAndView("tableAssignedAudits");
		mav.addObject("contacts", auditorService.findAllAssignedAudits(usuario));
		mav.addObject("usuario", usuario);
		return mav;
	}
	
	@GetMapping("/tableAssignedAuditsH")
	@ResponseBody
	public ModelAndView showFormH(String usuario) {
		ModelAndView mav = new ModelAndView("tableAssignedAudits");
		mav.addObject("contacts", auditorService.findAllAuditsHistory(usuario));
		mav.addObject("usuario", usuario);
		return mav;
	}

	@GetMapping("/startQuestionnaire1")
	@ResponseBody
	public ModelAndView startquestionnaire1(int id, String usuario) {
		ModelAndView mav = new ModelAndView("questionnaireForm");
		auditorService.createCkeckList(id);  //crea las preguntas del cuestionario solicitado con el id de la solicitud
		CheckList cl = auditorService.reply(id);   //consulta la primera pregunta del cuestionario 
		mav.addObject("pregunta", cl);
		mav.addObject("id", id);
		mav.addObject("usuario", usuario);
		mav.addObject("codigoString", String.valueOf(cl.getCodigo()));
		return mav;
	}

	@PostMapping("/QuestionSave")
	public ModelAndView nextQuestionPost(int id, String usuario, String codigo, MultipartFile foto, String evidencia,
			boolean respuesta) {
		ModelAndView mav = new ModelAndView("save");
		String accion = "+";
		System.out.println("ingreso al post " + evidencia);
		System.out.println("ingreso al post " + id);
		System.out.println("ingreso al post " + usuario);
		System.out.println("ingreso al post " + codigo);
		System.out.println("ingreso al post " + foto);
		System.out.println("ingreso al post " + respuesta);
		auditorService.saveReply(foto, evidencia, respuesta, codigo);
		CheckList cl = auditorService.replyPost(id, codigo, accion);
		mav.addObject("pregunta", cl);
		mav.addObject("id", id);
		mav.addObject("usuario", usuario);
		mav.addObject("codigoString", String.valueOf(cl.getCodigo()));
		return mav;
	}
	
	@PostMapping("/nextQuestionPostNext")
	public ModelAndView nextQuestionPostNext(int id, String usuario, String codigo, MultipartFile foto, String evidencia,
			boolean respuesta) {
		ModelAndView mav = new ModelAndView("questionnaireForm");
		String accion = "+";
		System.out.println("ingreso al post " + evidencia);
		System.out.println("ingreso al post " + id);
		System.out.println("ingreso al post " + usuario);
		System.out.println("ingreso al post " + codigo);
		System.out.println("ingreso al post " + foto);
		System.out.println("ingreso al post " + respuesta);
		//auditorService.saveReply(foto, evidencia, respuesta, codigo);
		CheckList cl = auditorService.replyPost(id, codigo, accion);
		mav.addObject("pregunta", cl);
		mav.addObject("id", id);
		mav.addObject("usuario", usuario);
		mav.addObject("codigoString", String.valueOf(cl.getCodigo()));
		return mav;
	}
	
	@PostMapping("/previousQuestionPost")
	public ModelAndView previousQuestionPost(int id, String usuario, String codigo, MultipartFile foto,
			String evidencia, boolean respuesta) {
		ModelAndView mav = new ModelAndView("questionnaireForm");
		String accion = "-";
		// auditorService.saveReply(foto, evidencia, respuesta, codigo);
		CheckList cl = auditorService.replyPost(id, codigo, accion);
		mav.addObject("pregunta", cl);
		mav.addObject("id", id);
		mav.addObject("usuario", usuario);
		mav.addObject("codigoString", String.valueOf(cl.getCodigo()));
		return mav;
	}

	@GetMapping("/nextQuestion")
	@ResponseBody
	public ModelAndView nextQuest(int id, String usuario, String codigo, MultipartFile foto, String evidencia,
			boolean respuesta) {
		ModelAndView mav = new ModelAndView("questionnaireForm");
		String accion = "+";
		auditorService.saveReply(foto, evidencia, respuesta, codigo);
		CheckList cl = auditorService.replyPost(id, codigo, accion);
		mav.addObject("pregunta", cl);
		mav.addObject("id", id);
		mav.addObject("username", usuario);
		mav.addObject("codigoString", String.valueOf(cl.getCodigo()));
		return mav;
	}

	@GetMapping("/previousQuestion")
	@ResponseBody
	public ModelAndView preQuest(int id, String usuario, String codigo, MultipartFile foto, String evidencia,
			boolean respuesta) {
		ModelAndView mav = new ModelAndView("questionnaireForm");
		String accion = "-";
		auditorService.saveReply(foto, evidencia, respuesta, codigo);
		CheckList cl = auditorService.replyPost(id, codigo, accion);
		mav.addObject("pregunta", cl);
		mav.addObject("id", id);
		mav.addObject("username", usuario);
		mav.addObject("codigoString", String.valueOf(cl.getCodigo()));
		return mav;
	}

	@GetMapping("/editAssignedAudits")
	@ResponseBody
	public ModelAndView showEditAdminForm(Integer id, String usuario) {
		ModelAndView mav = new ModelAndView("editAuditsRequests");
		mav.addObject("reporte", informeServicio.findReport(id));
		mav.addObject("auditores", backOfficeService.findAllAudit());
		mav.addObject("usuario", usuario);
		return mav;
	}

	@PostMapping("/saveAssignedAudits")
	public ModelAndView saveAdmin(int informeId, String usuario, String auditorId) throws Exception {
		ModelAndView mav = new ModelAndView("/dashboardBack");
		//backOfficeService.saveInforme(informeId, auditorId, sa);
		mav.addObject("username", usuario);
		return mav;
	}

	@GetMapping("/findAssignedAudits")
	@ResponseBody
	public Informe findOne(Integer id) {
		return informeServicio.findReport(id);
	}

	@GetMapping("/cancelAssignedAudits")
	public String cancel() {
		return "redirect:/dashboardAdmin";
	}

	@GetMapping("/deleteAssignedAudits")
	public void deleteCountry(Integer adminId) {
		informeServicio.deleteReport(adminId);
		
	}

	@GetMapping("/viewCust")
	@ResponseBody
	public ModelAndView viewCust(int id) {
		ModelAndView mav = new ModelAndView("viewCustomer");
		mav.addObject("cliente", custService.findCustById(id));
		return mav;
	}
	

}
