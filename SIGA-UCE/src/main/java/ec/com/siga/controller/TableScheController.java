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
public class TableScheController {

	@Autowired
	@Qualifier("informeServicio")
	private InformeService informeServicio;

	@GetMapping("/tableSche")
	public ModelAndView showForm() {
		ModelAndView mav = new ModelAndView("tableSche");
		mav.addObject("contacts", informeServicio.findAllReport());
		return mav;
	}

	@GetMapping("/editSche")
	public String showEditAdminForm() {
		return "editAdmin";
	}

	@PostMapping("/saveSche")
	public String saveAdmin(User admin) {
		// questServicio.saveAdmin(admin);
		return "redirect:/dashboardAdmin";
	}

	@GetMapping("/findSche")
	@ResponseBody
	public Informe findOne(Integer id) {
		return informeServicio.findReport(id);
	}

	@GetMapping("/cancelSche")
	public String cancel() {
		return "redirect:/dashboardAdmin";
	}

	@GetMapping("/deleteSche")
	public void deleteCountry(Integer adminId) {
		informeServicio.deleteReport(adminId);;
	}

}
