package ec.com.siga.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ec.com.siga.entity.Preguntas;
import ec.com.siga.entity.Seccion;
import ec.com.siga.entity.User;
import ec.com.siga.service.QuestService;
import ec.com.siga.service.SectionService;
import ec.com.siga.service.TypeQuestionnaireService;

@Controller
public class TableQuestController {

	@Autowired
	@Qualifier("questServicio")
	private QuestService questServicio;

	@Autowired
	@Qualifier("typeQuestionnaireService")
	private TypeQuestionnaireService typeQuestionnaireService;

	@Autowired
	@Qualifier("sectionService")
	private SectionService sectionService;

	@GetMapping("/tableQuest")
	@ResponseBody
	public ModelAndView showForm(String username) {
		ModelAndView mav = new ModelAndView("tableQuest");
		mav.addObject("tQuests", typeQuestionnaireService.findAllTypeQuestionnaire());
		mav.addObject("sections", sectionService.findAllSections());
		mav.addObject("usuario",username);
		return mav;
	}

	@GetMapping("/sectionQuest")
	@ResponseBody
	public List<Seccion> showSections(String tipoAuditoria) {
		List<Seccion> lsection = sectionService.findAllSections();
		List<Seccion> listSection = new ArrayList<Seccion>();
		switch (tipoAuditoria) {
		case "1":
			for (Seccion sec : lsection) {
				if (sec.getSeccionId() <= 11) {
					listSection.add(sec);
				}
			}

			break;
		case "2":
			for (Seccion sec : lsection) {
				if (sec.getSeccionId() <= 12) {
					listSection.add(sec);
				}
			}
			break;
		case "3":
			for (Seccion sec : lsection) {
				if (sec.getSeccionId() <= 13) {
					listSection.add(sec);
				}
			}
			break;
		case "4":
			for (Seccion sec : lsection) {
				if (sec.getSeccionId() <= 14) {
					listSection.add(sec);
				}
			}
			break;
		default:
		}
		return listSection;
	}
	
	@GetMapping("/questions")
	@ResponseBody
	public List<Preguntas> showQuestions(String tipoAuditoria, String seccion) {
		List<Preguntas> lPreguntas = questServicio.findAllByTipoAuditoriaAndSeccion(Integer.valueOf(tipoAuditoria), Integer.valueOf(seccion));
		return lPreguntas;
	}

	@GetMapping("/editQuest")
	public String showEditAdminForm() {
		return "editQuest";
	}

	@PostMapping("/saveQuest")
	public String saveAdmin(String preguntas, String taId, String secId) {
		questServicio.saveQuest(preguntas, taId, secId);
		return "dashboardAdmin";
	}

	@GetMapping("/findQuest")
	@ResponseBody
	public Preguntas findOne(Integer id) {
		return questServicio.findQuestion(id);
	}

	@GetMapping("/cancelQuest")
	public String cancel() {
		return "redirect:/dashboardAdmin";
	}

	@GetMapping("/deleteQuest")
	public void deleteCountry(Integer adminId) {
		questServicio.deleteQuestion(adminId);
	}

}
