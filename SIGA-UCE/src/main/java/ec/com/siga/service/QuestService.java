package ec.com.siga.service;

import java.util.List;

import ec.com.siga.entity.Preguntas;

public interface QuestService {
	public abstract List<Preguntas> findAllQuest();
	public abstract Preguntas findQuestion(int idQuestion);
	public abstract void deleteQuestion(int idQuestion);
}
