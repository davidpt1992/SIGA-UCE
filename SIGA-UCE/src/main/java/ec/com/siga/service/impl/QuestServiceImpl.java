package ec.com.siga.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ec.com.siga.entity.Preguntas;
import ec.com.siga.repository.QuestJpaRepository;
import ec.com.siga.repository.TipoAudiRepository;
import ec.com.siga.service.QuestService;

@Service("questServicio")
public class QuestServiceImpl implements QuestService {

	@Autowired
	@Qualifier("questRepository")
	private QuestJpaRepository questRepository;

	@Autowired
	@Qualifier("tipoAudiRepository")
	private TipoAudiRepository tipoAudiRepository;

	@Override
	public List<Preguntas> findAllQuest() {
		// int tipoAuditoriaId=1;
		// questRepository.findAllByTipoAuditoriaId(tipoAudiRepository.findById(tipoAuditoriaId).get())
		return questRepository.findAll();
	}

	@Override
	public Preguntas findQuestion(int idQuestion) {
		return questRepository.findById(idQuestion).get();
	}

	@Override
	public void deleteQuestion(int idQuestion) {
		questRepository.deleteById(idQuestion);
	}

}
