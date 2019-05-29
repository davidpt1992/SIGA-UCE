package ec.com.siga.serviceMovil;

import java.util.List;

import ec.com.siga.model.AuditDTO;
import ec.com.siga.model.QuestionDTO;

public interface AuditServiceMovil {
	public abstract List<AuditDTO> findAllAudits();
	public abstract QuestionDTO replyMovil(Integer id);
	public abstract QuestionDTO replyPostMovil(String codigo);
	
}
