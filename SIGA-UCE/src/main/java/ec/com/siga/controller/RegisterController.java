package ec.com.siga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ec.com.siga.entity.User;
import ec.com.siga.service.UserServicio;

@Controller
public class RegisterController {

	@Autowired
	@Qualifier("userServicio")
	private UserServicio userServicio;
	
	
	@GetMapping("/register")
	public String showForm() {
		return "register";
	}
	
	@PostMapping("/registerCust")
	public String saveUser(User user) {
		user.setEnabled(true);
		user.setRoleId(userServicio.findRoleById(1));
		userServicio.saveUser(user);
		return "redirect:/login";
	}
	
	@GetMapping("/cancelRegister")
	public String cancel() {
		return "redirect:/login";
	}


}
