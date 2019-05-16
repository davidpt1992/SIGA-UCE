package ec.com.siga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ec.com.siga.entity.Seccion;
import ec.com.siga.service.SectionService;

@Controller
public class TableSectionController {

	@Autowired
	@Qualifier("sectionService")
	private SectionService sectionService;

	@GetMapping("/tableSection")
	public ModelAndView showForm() {
		ModelAndView mav = new ModelAndView("tableSection");
		mav.addObject("contacts", sectionService.findAllSections());
		return mav;
	}

	@GetMapping("/editSection")
	public String showEditAdminForm() {
		return "editSection";
	}

	@PostMapping("/saveSection")
	public String saveAdmin(Seccion section) {
		sectionService.saveSection(section);
		return "dashboardAdmin";
	}

	@GetMapping("/findSection")
	@ResponseBody
	public Seccion findOne(Integer id) {
		return sectionService.findSection(id);
	}

	@GetMapping("/cancelSection")
	public String cancel() {
		return "dashboardAdmin";
	}

	@GetMapping("/deleteSection")
	public void deleteCountry(Integer id) {
		sectionService.deleteSection(id);
	}

}
