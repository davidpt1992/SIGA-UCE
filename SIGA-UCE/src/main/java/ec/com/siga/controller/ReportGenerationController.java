package ec.com.siga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ec.com.siga.service.ReportGenerationService;

@Controller
public class ReportGenerationController {

	@Autowired
	@Qualifier("reportGenerationService")
	private ReportGenerationService reportGenerationService;
	
	@GetMapping("/genCertificateSecond")
	@ResponseBody
	public String showForm(Integer id, String usuario) {
		return reportGenerationService.reportGeneration(id);
	}
	


}
