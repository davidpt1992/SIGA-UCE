package ec.com.siga.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ec.com.siga.entity.CheckList;
import ec.com.siga.entity.Informe;

public interface AuditorService {
	public abstract List<Informe> findAllAssignedAudits(String auditor);
	public abstract List<Informe> findAllAuditsHistory(String auditor);
	public abstract void createCkeckList(int informeId);
	public abstract CheckList reply(int informeId);
	public abstract CheckList replyPost(int informeId, String codigo, String accion);
	public abstract void saveReply(MultipartFile f, String evidencia, boolean respuesta, String codigo);
}
