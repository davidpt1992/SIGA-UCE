package ec.com.siga.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ec.com.siga.entity.Cliente;
import ec.com.siga.entity.ClienteTipo;
import ec.com.siga.entity.DatoComun;
import ec.com.siga.entity.Informe;
import ec.com.siga.entity.SolicitudAuditoria;
import ec.com.siga.entity.TipoAuditoria;
import ec.com.siga.model.SolucitudAuditoriaString;
import ec.com.siga.repository.SolicitudAuditoriaRepository;
import ec.com.siga.repository.TipoAudiRepository;
import ec.com.siga.repository.TipoCustRepository;
import ec.com.siga.service.AuditService;

@Service("auditService")
public class AuditServiceImpl implements AuditService {

	@Autowired
	@Qualifier("tipoAudiRepository")
	private TipoAudiRepository tipoAudiRepository;
	
	@Autowired
	@Qualifier("tipoCustRepository")
	private TipoCustRepository tipoCustRepository;
	
	@Autowired
	@Qualifier("solicitudAuditoriaRepository")
	private SolicitudAuditoriaRepository solicitudAuditoriaRepository;
	
	@Override
	public List<TipoAuditoria> findAllTipoAuditoria() {
		return tipoAudiRepository.findAll();
	}

	@Override
	public List<ClienteTipo> findAllTipoCliente() {
		return tipoCustRepository.findAll();
	}

	@Override
	public void solicitudAuditoria(Cliente cliente, DatoComun datoComun, SolucitudAuditoriaString sas) {
		SolicitudAuditoria soliAud = new SolicitudAuditoria();
		DatoComun daCo = new DatoComun();
		Informe informe = new Informe();
		try {
			soliAud.setFechaInicio(new SimpleDateFormat("yyyy-MM-dd").parse(sas.getAuxfechaInicio()));
			soliAud.setFechaFinal(new SimpleDateFormat("yyyy-MM-dd").parse(sas.getAuxfechaFinal()));
			daCo.setHoraInicio(new SimpleDateFormat("HH:mm").parse(sas.getAuxhoraInicio()));
			daCo.setHoraFin(new SimpleDateFormat("HH:mm").parse(sas.getAuxhoraFin()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		soliAud.setTipoAuditoriaId(tipoAudiRepository.findById(Integer.parseInt(sas.getAuxTipoAuditoriaId())).get());
		cliente.setClienteTipoId(tipoCustRepository.findById(Integer.parseInt(sas.getAuxTipoAuditoriaId())).get());
		
		
		
	}

	
}
