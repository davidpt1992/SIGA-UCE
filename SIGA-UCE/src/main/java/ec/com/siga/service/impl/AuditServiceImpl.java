package ec.com.siga.service.impl;

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
import ec.com.siga.repository.CustRepository;
import ec.com.siga.repository.DatoComunRepository;
import ec.com.siga.repository.EstadoAuditRepository;
import ec.com.siga.repository.InformeRepository;
import ec.com.siga.repository.SolicitudAuditoriaRepository;
import ec.com.siga.repository.TipoAudiRepository;
import ec.com.siga.repository.TipoCustRepository;
import ec.com.siga.repository.UserJpaRepository;
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

	@Autowired
	@Qualifier("userRepository")
	private UserJpaRepository userRepository;

	@Autowired
	@Qualifier("estadoAuditRepository")
	private EstadoAuditRepository estadoAuditRepository;
	
	@Autowired
	@Qualifier("dComunRepository")
	private DatoComunRepository dComunRepository;
	
	@Autowired
	@Qualifier("custRepository")
	private CustRepository custRepository;
	
	@Autowired
	@Qualifier("informeRepository")
	private InformeRepository informeRepository;

	@Override
	public List<TipoAuditoria> findAllTipoAuditoria() {
		return tipoAudiRepository.findAll();
	}

	@Override
	public List<ClienteTipo> findAllTipoCliente() {
		return tipoCustRepository.findAll();
	}

	@Override
	public void solicitudAuditoria(DatoComun datoComun, SolucitudAuditoriaString sas, String usuario)
			throws Exception {
		Cliente cliente = new Cliente();
		SolicitudAuditoria soliAud = new SolicitudAuditoria();
		Informe informe = new Informe();

		soliAud.setFechaInicio(new SimpleDateFormat("yyyy-MM-dd").parse(sas.getAuxfechaInicio()));
		soliAud.setFechaFinal(new SimpleDateFormat("yyyy-MM-dd").parse(sas.getAuxfechaFinal()));
		soliAud.setTipoAuditoriaId(tipoAudiRepository.findById(Integer.parseInt(sas.getAuxTipoAuditoriaId())).get());
		soliAud.setEstadoAuditoriaId(estadoAuditRepository.findById(1).get());
		solicitudAuditoriaRepository.save(soliAud);
		System.out.println("id solicitud: "+soliAud.getSolicitudAuditoriaId());
		
		datoComun.setHoraInicio(new SimpleDateFormat("HH:mm").parse(sas.getAuxhoraInicio()));
		datoComun.setHoraFin(new SimpleDateFormat("HH:mm").parse(sas.getAuxhoraFin()));
		datoComun.setSolicitudAuditoriaId(solicitudAuditoriaRepository.findById(soliAud.getSolicitudAuditoriaId()).get());
		dComunRepository.save(datoComun);
		System.out.println("id dato comun: "+datoComun.getDatoComunId());
		cliente=custRepository.findByUserId(userRepository.findByUsuario(usuario));
		custRepository.save(cliente);
		System.out.println("id cliente: "+cliente.getClienteId());
		
		informe.setDatoComunId(datoComun);
		informe.setClienteId(cliente);
		informeRepository.save(informe);
		System.out.println("id informe: "+informe.getInformeId());

	}

}
