package ec.com.siga.controllerMovil;


import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ec.com.siga.entity.CheckList;
import ec.com.siga.model.AuditDTO;
import ec.com.siga.model.CredsDTO;
import ec.com.siga.model.QuestionDTO;
import ec.com.siga.service.AuditorService;
import ec.com.siga.serviceMovil.AuditServiceMovil;
import ec.com.siga.serviceMovil.AutenticacionServiceMovil;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/movil")
public class AppMovilController {

	@Autowired
	@Qualifier("auditServiceMovil")
	private AuditServiceMovil auditServiceMovil;
	
	@Autowired
	@Qualifier("autenticacionServiceMovil")
	private AutenticacionServiceMovil autenticacionServiceMovil;
	
	@Autowired
	@Qualifier("auditorService")
	private AuditorService auditorService;
		
	@GetMapping("/listAuditsMovil")
	public ResponseEntity<List<AuditDTO>> listAuditsMovil() {
		return ResponseEntity.ok().body(auditServiceMovil.findAllAudits());
	}
	
	@PostMapping("/loginMovil")
	@ResponseBody
	public ResponseEntity<Boolean> loginMovil(@Valid @RequestBody CredsDTO creds) {
		System.out.println(creds.getUsername() + creds.getPassword());
		Boolean boo= autenticacionServiceMovil.autenticacion(creds.getUsername(), creds.getPassword());
		System.out.println(boo);
		return ResponseEntity.ok().body(boo);
	}
	
	@PostMapping("/questionnaireMovil")
	@ResponseBody
	public ResponseEntity<QuestionDTO> questionnaireMovil(@Valid @RequestBody QuestionDTO ques) {
		auditorService.createCkeckList(Integer.valueOf(ques.getId_solicitud()));  //crea las preguntas del cuestionario solicitado con el id de la solicitud
		QuestionDTO cl = auditServiceMovil.replyMovil(Integer.valueOf(ques.getId_solicitud()));   //consulta la primer pregunta del cuestionario 
		return ResponseEntity.ok().body(cl);
	}
	
	@PostMapping("/questionnairePostMovil")
	@ResponseBody
	public ResponseEntity<QuestionDTO> questionnairePostMovil(@Valid @RequestBody String codigo) {
		QuestionDTO cl = auditServiceMovil.replyPostMovil(codigo);
		return ResponseEntity.ok().body(cl);
	}

}
