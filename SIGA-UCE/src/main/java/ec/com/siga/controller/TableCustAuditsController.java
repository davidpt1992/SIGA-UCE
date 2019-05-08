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
import ec.com.siga.service.CustService;
import ec.com.siga.service.InformeService;
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
	
	@GetMapping("/tableCustAudits")
	@ResponseBody
	public ModelAndView showForm(String usuario) {
		ModelAndView mav = new ModelAndView("tableCustAudits");
		mav.addObject("contacts", informeServicio.findAllCustAudits(usuario));
		mav.addObject("usuario", usuario);
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
	public ModelAndView saveAdmin(DatoComun datoComun, SolucitudAuditoriaString sas, String usuario, String latitud, String longitud) throws Exception {
		ModelAndView mav = new ModelAndView("/dashboardCust");
		mav.addObject("username", usuario);
		custService.custUpdate(usuario, longitud, latitud);
		auditService.solicitudAuditoria(datoComun, sas, usuario);
		return mav;
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
		informeServicio.deleteReport(adminId);;
	}

}
