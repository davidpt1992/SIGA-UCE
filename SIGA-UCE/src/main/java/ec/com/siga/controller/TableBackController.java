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
public class TableBackController {
	
	@Autowired
	@Qualifier("userServicio")
	private UserServicio userServicio;

	@GetMapping("/tableBack")
	public ModelAndView showForm() {
		ModelAndView mav = new ModelAndView("tableBack");
		mav.addObject("contacts", userServicio.findAllBack());
		return mav;
	}
	
	@GetMapping("/editBack")
	public String showEditAdminForm() {
		return "editAdmin";
	}
	
	@PostMapping("/saveBack")
	public String saveAdmin(User admin) {
		userServicio.saveUser(admin);
		return "redirect:/dashboardAdmin";
	}

	@GetMapping("/findBack")
	@ResponseBody
	public User findOne(Integer id) {
	return userServicio.findAdmin(id);
	}
	
	@GetMapping("/cancelBack")
	public String cancel() {
		return "redirect:/dashboardAdmin";
	}
	
	@GetMapping("/deleteBack")
    public String deleteCountry(Integer adminId) {
		userServicio.deletAdmin(userServicio.findAdmin(adminId));
        return "redirect:/dashboardAdmin";
    }


}
