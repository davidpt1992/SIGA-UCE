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
import ec.com.siga.entity.Cliente;
import ec.com.siga.entity.Informe;
import ec.com.siga.service.AuditService;
import ec.com.siga.service.AuditorService;
import ec.com.siga.service.BackOfficeService;
import ec.com.siga.service.CustService;
import ec.com.siga.service.InformeService;

@Controller
public class TableAuditorAuditsHistoryController {

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

	@GetMapping("/tableAuditHistory")
	@ResponseBody
	public ModelAndView showForm(String usuario) {
		ModelAndView mav = new ModelAndView("tableAssignedAudits");
		mav.addObject("contacts", auditorService.findAllAssignedAudits(usuario));
		mav.addObject("usuario", usuario);
		return mav;
	}

	

}
