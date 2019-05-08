package ec.com.siga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ec.com.siga.entity.Cliente;
import ec.com.siga.entity.User;
import ec.com.siga.service.CustService;
import ec.com.siga.service.UserServicio;

@Controller
public class TableCustController {
	
	@Autowired
	@Qualifier("userServicio")
	private UserServicio userServicio;
	
	@Autowired
	@Qualifier("custService")
	private CustService custService;

	@GetMapping("/tableCust")
	public ModelAndView showForm() {
		ModelAndView mav = new ModelAndView("tableCust");
		mav.addObject("contacts", userServicio.findAllCust());
		return mav;
	}
	
	@GetMapping("/editCust")
	public String showEditAdminForm() {
		return "editAdmin";
	}
	
	@PostMapping("/saveCust")
	public String saveAdmin(User admin) {
		userServicio.saveUser(admin);
		return "redirect:/dashboardAdmin";
	}

	@GetMapping("/findCust")
	@ResponseBody
	public Cliente findOne(Integer id) {
	return custService.findCustById(id);
	}
	
	@GetMapping("/cancelCust")
	public String cancel() {
		return "redirect:/dashboardAdmin";
	}
	
	@GetMapping("/deleteCust")
    public String deleteCountry(Integer adminId) {
		//userServicio.deletAdmin(userServicio.findAdmin(adminId));
        return "redirect:/dashboardAdmin";
    }

}
