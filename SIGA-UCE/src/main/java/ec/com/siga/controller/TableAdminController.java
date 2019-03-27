package ec.com.siga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.servlet.ModelAndView;

import ec.com.siga.service.UserServicio;

@Controller
public class TableAdminController {
	
	@Autowired
	@Qualifier("userServicio")
	private UserServicio userServicio;

	@GetMapping("/tableAdmin")
	public ModelAndView showForm() {
		ModelAndView mav = new ModelAndView("tableAdmin");
		mav.addObject("contacts", userServicio.findAll());
		return mav;
	}
	
	@GetMapping("/editAdmin")
	public String showEditAdminForm() {
		return "editAdmin";
	}

}
