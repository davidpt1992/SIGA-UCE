package ec.com.siga.controller;

//import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;





//import ec.com.siga.SIGA.repository.UserJpaRepository;

@Controller
public class LoginController {

	/**
	 * @Autowired @Qualifier("userRepository") private UserJpaRepository
	 *            userRepository;
	 **/

	@GetMapping("/login")
	public String showLoginForm(Model model,
			@RequestParam(name="error",required=false)String error,
			@RequestParam(name = "logout", required = false) String logout){
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return "login";
	}

	
	@GetMapping({ "/loginsuccess"})
	public String loginCheck() {
		return "/dashboardAdmin";
		
	}

}
