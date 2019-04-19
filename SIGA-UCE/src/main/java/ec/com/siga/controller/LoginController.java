package ec.com.siga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ec.com.siga.service.UserServicio;

@Controller
public class LoginController {

	@Autowired
	@Qualifier("userServicio")
	private UserServicio userServicio;
	
	@GetMapping("/login")
	public String showLoginForm(Model model, @RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return "login";
	}

	@GetMapping({ "/loginsuccess" })
	public ModelAndView loginCheck() {
		ModelAndView mav = new ModelAndView();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int role = userServicio.findUserRole(user.getUsername()).getRoleId().getRoleId();
				
		switch (role) {
		case 1:
			mav.setViewName("dashboardCust");
			break;
		case 2:
			mav.setViewName("dashboardAdmin");
			break;
		case 3:
			mav.setViewName("dashboardBack");
			break;
		case 4:
			mav.setViewName("dashboardAdmin");
			break;
		default:
			mav.setViewName("home");
		}
		mav.addObject("username", user.getUsername());
		mav.addObject("role", role);
		return mav;
	}

}
