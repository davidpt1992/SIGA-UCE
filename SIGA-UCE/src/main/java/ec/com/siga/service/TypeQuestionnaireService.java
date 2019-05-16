package ec.com.siga.service;

import java.util.List;

import ec.com.siga.entity.TipoAuditoria;

public interface TypeQuestionnaireService {
	public abstract List<TipoAuditoria> findAllTypeQuestionnaire();
	public abstract TipoAuditoria findTypeQuestionnaire(Integer idTypeQuestionnaire);
	public abstract void saveTypeQuestionnaire(TipoAuditoria typeQuestionnaire);
	public abstract void deleteTypeQuestionnaire(Integer idTypeQuestionnaire);
}
