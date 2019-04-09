package ec.com.siga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ec.com.siga.entity.Cliente;
import ec.com.siga.entity.DatoComun;
import ec.com.siga.entity.Informe;
import ec.com.siga.entity.User;
import ec.com.siga.model.SolucitudAuditoriaString;
import ec.com.siga.service.AuditService;
import ec.com.siga.service.InformeService;

@Controller
public class TableCustAuditsController {
	
	@Autowired
	@Qualifier("informeServicio")
	private InformeService informeServicio;
	
	@Autowired
	@Qualifier("auditService")
	private AuditService auditService;
	
	@GetMapping("/tableCustAudits")
	@ResponseBody
	public ModelAndView showForm(String usuario) {
		ModelAndView mav = new ModelAndView("tableCustAudits");
		mav.addObject("contacts", informeServicio.findAllCustAudits(usuario));
		mav.addObject("username", usuario);
		
		return mav;
	}
	
	@GetMapping("/editCustAudits")
	@ResponseBody
	public ModelAndView showEditAdminForm(String usuario) {
		ModelAndView mav = new ModelAndView("editCustAudits");
		mav.addObject("tipAudit", auditService.findAllTipoAuditoria());
		mav.addObject("tipCust", auditService.findAllTipoCliente());
		System.out.println(usuario+" este es el usuario que quiere crear");
		return mav;
	}

	@PostMapping("/saveCustAudits")
	public String saveAdmin(Cliente cliente, DatoComun datoComun, SolucitudAuditoriaString sas, User user) {
		//User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//System.out.println(user1.getUsuario()+"coge del dash");
		//auditService.solicitudAuditoria(cliente, datoComun, sas);
		System.out.println(sas.getAuxfechaInicio());
		System.out.println(sas.getAuxhoraInicio());
		System.out.println(sas.getAuxfechaFinal());
		System.out.println(sas.getAuxhoraFin());
		System.out.println(user.getUsuario()+"coge de la tabla");
		return "/dashboardCust";
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
