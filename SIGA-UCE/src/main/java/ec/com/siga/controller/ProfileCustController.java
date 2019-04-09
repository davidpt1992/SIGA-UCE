package ec.com.siga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ec.com.siga.entity.User;
import ec.com.siga.service.UserServicio;

@Controller
public class ProfileCustController {
	
	@Autowired
	@Qualifier("userServicio")
	private UserServicio userServicio;

	@GetMapping("/profileCust")
	public ModelAndView showForm() {
		ModelAndView mav = new ModelAndView("profileCust");
		mav.addObject("contacts", userServicio.findAllAdmin());
		return mav;
	}
	
}
