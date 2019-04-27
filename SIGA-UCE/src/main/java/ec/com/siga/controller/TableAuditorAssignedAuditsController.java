package ec.com.siga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ec.com.siga.entity.CheckList;
import ec.com.siga.entity.DatoComun;
import ec.com.siga.entity.Informe;
import ec.com.siga.model.SolucitudAuditoriaString;
import ec.com.siga.service.AuditService;
import ec.com.siga.service.AuditorService;
import ec.com.siga.service.BackOfficeService;
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
	
	@GetMapping("/tableAssignedAudits")
	@ResponseBody
	public ModelAndView showForm(String usuario) {
		ModelAndView mav = new ModelAndView("tableAssignedAudits");
		mav.addObject("contacts", auditorService.findAllAssignedAudits(usuario));
		mav.addObject("usuario", usuario);
		return mav;
	}
	
	@GetMapping("/startQuestionnaire1")
	@ResponseBody
	public ModelAndView startquestionnaire1(int id, String usuario) {
		ModelAndView mav = new ModelAndView("questionnaire1");
		auditorService.createCkeckList(id);
		CheckList cl= auditorService.reply(id);
		mav.addObject("pregunta", cl);
		mav.addObject("id", id);
		mav.addObject("usuario", usuario);
		mav.addObject("codigoString", String.valueOf(cl.getCodigo()));
		return mav;
	}
	
	@GetMapping("/continueQuestionnaire1")
	@ResponseBody
	public ModelAndView continueQuest(int id, String usuario, String codigo)  {
		ModelAndView mav = new ModelAndView("questionnaire1");
		String accion="+";
		CheckList cl= auditorService.replyPost(id, codigo, accion);
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
		backOfficeService.saveInforme(informeId, auditorId);
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
		informeServicio.deleteReport(adminId);;
	}

}
