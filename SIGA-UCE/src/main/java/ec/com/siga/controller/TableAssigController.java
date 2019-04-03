package ec.com.siga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ec.com.siga.entity.Informe;
import ec.com.siga.entity.User;
import ec.com.siga.service.InformeService;


@Controller
public class TableAssigController {

	@Autowired
	@Qualifier("informeServicio")
	private InformeService informeServicio;

	@GetMapping("/tableAssig")
	public ModelAndView showForm() {
		ModelAndView mav = new ModelAndView("tableAssig");
		mav.addObject("contacts", informeServicio.findAllReport());
		return mav;
	}

	@GetMapping("/editAssig")
	public String showEditAdminForm() {
		return "editAdmin";
	}

	@PostMapping("/saveAssig")
	public String saveAdmin(User admin) {
		// questServicio.saveAdmin(admin);
		return "redirect:/dashboardAdmin";
	}

	@GetMapping("/findAssig")
	@ResponseBody
	public Informe findOne(Integer id) {
		return informeServicio.findReport(id);
	}

	@GetMapping("/cancelAssig")
	public String cancel() {
		return "redirect:/dashboardAdmin";
	}

	@GetMapping("/deleteAssig")
	public void deleteCountry(Integer adminId) {
		informeServicio.deleteReport(adminId);;
	}

}
