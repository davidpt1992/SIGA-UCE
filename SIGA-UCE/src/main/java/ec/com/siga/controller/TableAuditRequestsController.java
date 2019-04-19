package ec.com.siga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ec.com.siga.entity.DatoComun;
import ec.com.siga.entity.Informe;
import ec.com.siga.model.SolucitudAuditoriaString;
import ec.com.siga.service.AuditService;
import ec.com.siga.service.BackOfficeService;
import ec.com.siga.service.InformeService;

@Controller
public class TableAuditRequestsController {
	
	@Autowired
	@Qualifier("informeServicio")
	private InformeService informeServicio;
	
	@Autowired
	@Qualifier("auditService")
	private AuditService auditService;
	
	@Autowired
	@Qualifier("backOfficeService")
	private BackOfficeService backOfficeService;
	
	@GetMapping("/tableAuditsRequests")
	@ResponseBody
	public ModelAndView showForm(String usuario) {
		ModelAndView mav = new ModelAndView("tableAuditsRequests");
		mav.addObject("contacts", informeServicio.findAllReport());
		mav.addObject("usuario", usuario);
		return mav;
	}
	
	@GetMapping("/editAuditsRequests")
	@ResponseBody
	public ModelAndView showEditAdminForm(Integer id, String usuario) {
		ModelAndView mav = new ModelAndView("editAuditsRequests");
		mav.addObject("reporte", informeServicio.findReport(id));
		mav.addObject("auditores", backOfficeService.findAllAudit());
		mav.addObject("usuario", usuario);
		return mav;
	}

	@PostMapping("/saveAuditsRequests")
	public ModelAndView saveAdmin(int informeId, String usuario, String auditorId) throws Exception {
		ModelAndView mav = new ModelAndView("/dashboardBack");
		backOfficeService.saveInforme(informeId, auditorId);
		mav.addObject("username", usuario);
		return mav;
	}

	@GetMapping("/findAuditsRequests")
	@ResponseBody
	public Informe findOne(Integer id) {
		return informeServicio.findReport(id);
	}

	@GetMapping("/cancelAuditsRequests")
	public String cancel() {
		return "redirect:/dashboardAdmin";
	}

	@GetMapping("/deleteAuditsRequests")
	public void deleteCountry(Integer adminId) {
		informeServicio.deleteReport(adminId);;
	}

}
