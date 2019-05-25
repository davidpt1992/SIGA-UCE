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
	@ResponseBody
	public ModelAndView showForm(String username) {
		ModelAndView mav = new ModelAndView("tableAdmin");
		mav.addObject("contacts", userServicio.findAllAdmin());
		mav.addObject("username", username);		
		return mav;
	}
	
	@GetMapping("/editAdmin")
	@ResponseBody
	public ModelAndView showEditAdminForm(Integer id, String username) {
		ModelAndView mav = new ModelAndView("editAdmin");
		if (id != null) {
			mav.addObject("admin", userServicio.findAdmin(id));	
		}
		mav.addObject("username", username);
		return mav;
	}
	
	@PostMapping("/saveAdmin")
	public ModelAndView saveAdmin(User admin, String username) {
		ModelAndView mav = new ModelAndView("dashboardAdmin");
		admin.setRoleId(userServicio.findRoleById(4));
		userServicio.saveUser(admin);
		mav.addObject("username", username);
		return mav;
	}


	
	@GetMapping("/cancelAdmin")
	public String cancel() {
		return "dashboardAdmin";
	}
	
	@GetMapping("/deleteAdmin")
    public String deleteCountry(Integer adminId, String username) {
		userServicio.deletAdmin(adminId);
		return "dashboardAdmin";
    }
	
}
