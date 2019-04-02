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
public class TableRoleController {
	
	@Autowired
	@Qualifier("userServicio")
	private UserServicio userServicio;

	@GetMapping("/tableRole")
	public ModelAndView showForm() {
		ModelAndView mav = new ModelAndView("tableRole");
		mav.addObject("contacts", userServicio.findAllRole());
		return mav;
	}
	
	@GetMapping("/editRole")
	public String showEditAdminForm() {
		return "editAdmin";
	}
	
	@PostMapping("/saveRole")
	public String saveAdmin(User admin) {
		userServicio.saveAdmin(admin);
		return "redirect:/dashboardAdmin";
	}

	@GetMapping("/findRole")
	@ResponseBody
	public User findOne(Integer id) {
	return userServicio.findAdmin(id);
	}
	
	@GetMapping("/cancelRole")
	public String cancel() {
		return "redirect:/dashboardAdmin";
	}
	
	@GetMapping("/deleteRole")
    public String deleteCountry(Integer adminId) {
		userServicio.deletAdmin(userServicio.findAdmin(adminId));
        return "redirect:/dashboardAdmin";
    }

}
