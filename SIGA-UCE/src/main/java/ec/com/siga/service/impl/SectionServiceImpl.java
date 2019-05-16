package ec.com.siga.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ec.com.siga.entity.Seccion;
import ec.com.siga.repository.SectionRepository;
import ec.com.siga.service.SectionService;

@Service("sectionService")
public class SectionServiceImpl implements SectionService {

	@Autowired
	@Qualifier("sectionRepository")
	private SectionRepository sectionRepository;

	@Override
	public List<Seccion> findAllSections() {
		return sectionRepository.findAll();
	}

	@Override
	public Seccion findSection(Integer id) {
		return sectionRepository.findById(id).get();
	}

	@Override
	public void saveSection(Seccion section) {
		sectionRepository.save(section);
		
	}

	@Override
	public void deleteSection(Integer id) {
		sectionRepository.deleteById(id);
		
	}

	
	

}
