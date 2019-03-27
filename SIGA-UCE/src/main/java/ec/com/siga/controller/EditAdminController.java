package ec.com.siga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ec.com.siga.entity.User;
import ec.com.siga.service.UserServicio;

@Controller
public class EditAdminController {
	@Autowired 
	@Qualifier("userServicio") 
	private UserServicio userServicio;
	
	@PostMapping("/saveAdmin")
	public String saveAdmin(User admin) {
		userServicio.saveAdmin(admin);
		return "redirect:/dashboardAdmin";
	}

	@GetMapping("/findAdmin")
	@ResponseBody
	public User findOne(Integer id) {
	return userServicio.findAdmin(id);
	}
	
	@GetMapping("/cancel")
	public String cancel() {
		return "redirect:/dashboardAdmin";
	}
	
	@GetMapping("/delete")
    public String deleteCountry(Integer adminId) {
		userServicio.deletAdmin(userServicio.findAdmin(adminId));
        return "redirect:/dashboardAdmin";
    }
	
	@PostMapping("/addcontact")
	public String addContact(@ModelAttribute(name = "contactModel") User contactModel, Model model) {
		userServicio.saveAdmin(contactModel);
		return "/dashboardAdmin";
	}
}
