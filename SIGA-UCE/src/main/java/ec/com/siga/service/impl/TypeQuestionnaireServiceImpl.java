package ec.com.siga.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ec.com.siga.entity.TipoAuditoria;
import ec.com.siga.repository.QuestJpaRepository;
import ec.com.siga.repository.TipoAudiRepository;
import ec.com.siga.service.TypeQuestionnaireService;

@Service("typeQuestionnaireService")
public class TypeQuestionnaireServiceImpl implements TypeQuestionnaireService {

	@Autowired
	@Qualifier("questRepository")
	private QuestJpaRepository questRepository;

	@Autowired
	@Qualifier("tipoAudiRepository")
	private TipoAudiRepository tipoAudiRepository;

	@Override
	public List<TipoAuditoria> findAllTypeQuestionnaire() {
		return tipoAudiRepository.findAll();
	}

	@Override
	public TipoAuditoria findTypeQuestionnaire(Integer id) {
		return tipoAudiRepository.findById(id).get();
	}

	@Override
	public void deleteTypeQuestionnaire(Integer id) {
		tipoAudiRepository.deleteById(id);
	}

	@Override
	public void saveTypeQuestionnaire(TipoAuditoria typeQuestionnaire) {
		tipoAudiRepository.save(typeQuestionnaire);
		
	}

	

}
