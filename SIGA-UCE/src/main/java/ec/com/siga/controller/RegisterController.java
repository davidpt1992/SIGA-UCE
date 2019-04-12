package ec.com.siga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ec.com.siga.entity.Cliente;
import ec.com.siga.entity.User;
import ec.com.siga.model.SolucitudAuditoriaString;
import ec.com.siga.service.AuditService;
import ec.com.siga.service.CustService;
import ec.com.siga.service.UserServicio;

@Controller
public class RegisterController {

	@Autowired
	@Qualifier("userServicio")
	private UserServicio userServicio;
	
	@Autowired
	@Qualifier("custService")
	private CustService custService;
	
		@Autowired
	@Qualifier("auditService")
	private AuditService auditService;
	
	
	@GetMapping("/register")
	public ModelAndView showForm() {
		ModelAndView mav = new ModelAndView("register");
		mav.addObject("tipCust", auditService.findAllTipoCliente());
		return mav;
	}
	
	@PostMapping("/registerCust")
	public String saveUser(User user, Cliente cliente, SolucitudAuditoriaString sas) {
		user.setEnabled(true);
		user.setRoleId(userServicio.findRoleById(1));
		userServicio.saveUser(user);
		cliente.setUserId(userServicio.findAdmin(user.getUserId()));
		cliente.setClienteTipoId(custService.findCustTipe(Integer.parseInt(sas.getAuxClienteTipoId())));
		custService.custSave(cliente);
		return "redirect:/login";
	}
	
	@GetMapping("/cancelRegister")
	public String cancel() {
		return "redirect:/login";
	}


}
