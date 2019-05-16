package ec.com.siga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ec.com.siga.entity.TipoAuditoria;
import ec.com.siga.service.TypeQuestionnaireService;

@Controller
public class TableTypeQuestionnaireController {

	@Autowired
	@Qualifier("typeQuestionnaireService")
	private TypeQuestionnaireService typeQuestionnaireService;

	@GetMapping("/tableTypeQuestionnaire")
	public ModelAndView showForm() {
		ModelAndView mav = new ModelAndView("tableTypeQuestionnaire");
		mav.addObject("contacts", typeQuestionnaireService.findAllTypeQuestionnaire());
		return mav;
	}

	@GetMapping("/editTypeQuestionnaire")
	public ModelAndView showEditAdminForm() {
		ModelAndView mav = new ModelAndView("editTypeQuestionnaire");
		return mav;
	}

	@PostMapping("/saveTypeQuestionnaire")
	public String saveAdmin(TipoAuditoria tauditoria) {
		typeQuestionnaireService.saveTypeQuestionnaire(tauditoria);
		return "dashboardAdmin";
	}

	@GetMapping("/findTypeQuestionnaire")
	@ResponseBody
	public TipoAuditoria findOne(int id) {
		return typeQuestionnaireService.findTypeQuestionnaire(id);
	}

	@GetMapping("/cancelTypeQuestionnaire")
	public String cancel() {
		return "redirect:/dashboardAdmin";
	}

	@GetMapping("/deleteTypeQuestionnaire")
	public void deleteCountry(int id) {
		typeQuestionnaireService.deleteTypeQuestionnaire(id);;
	}

}
