package ec.com.siga.service;

import java.util.List;

import ec.com.siga.entity.Seccion;

public interface SectionService {
	public abstract List<Seccion> findAllSections();
	public abstract Seccion findSection(Integer id);
	public abstract void saveSection(Seccion section);
	public abstract void deleteSection(Integer id);
}
