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
public class TableAdminController {
	
	@Autowired
	@Qualifier("userServicio")
	private UserServicio userServicio;

	@GetMapping("/tableAdmin")
	public ModelAndView showForm() {
		ModelAndView mav = new ModelAndView("tableAdmin");
		mav.addObject("contacts", userServicio.findAllAdmin());
		return mav;
	}
	
	@GetMapping("/editAdmin")
	public String showEditAdminForm() {
		return "editAdmin";
	}
	
	@PostMapping("/saveAdmin")
	public String saveAdmin(User admin) {
		admin.setRoleId(userServicio.findRoleById(4));
		userServicio.saveUser(admin);
		return "dashboardAdmin";
	}

	@GetMapping("/findAdmin")
	@ResponseBody
	public User findOne(Integer id) {
	return userServicio.findAdmin(id);
	}
	
	@GetMapping("/cancelAdmin")
	public String cancel() {
		return "/dashboardAdmin";
	}
	
	@GetMapping("/delete")
    public String deleteCountry(Integer adminId) {
		userServicio.deletAdmin(userServicio.findAdmin(adminId));
        return "dashboardAdmin";
    }

}
