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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.com.siga.model.AuditDTO;
import ec.com.siga.model.CredsDTO;
import ec.com.siga.serviceMovil.AuditServiceMovil;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/movil")
public class AppMovilController {

	@Autowired
	@Qualifier("auditServiceMovil")
	private AuditServiceMovil auditServiceMovil;
		
	@GetMapping("/listAuditsMovil")
	public ResponseEntity<List<AuditDTO>> listAuditsMovil() {
		return ResponseEntity.ok().body(auditServiceMovil.findAllAudits());
	}
	
	@PostMapping("/loginMovil")
	public ResponseEntity<Boolean> loginMovil(CredsDTO creds) {
		/*
		 * CredsDTO creds = new CredsDTO(); creds.setUsername(username);
		 * creds.setPassword(password);
		 */
		System.out.println("credenciales"+creds.getUsername());
		return ResponseEntity.ok().body(false);
	}

}
