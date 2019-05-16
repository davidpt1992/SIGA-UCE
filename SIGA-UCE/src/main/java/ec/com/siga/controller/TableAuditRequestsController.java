package ec.com.siga.controller;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ec.com.siga.entity.CheckList;
import ec.com.siga.entity.DatoEspecifico;
import ec.com.siga.entity.Foto;
import ec.com.siga.entity.Informe;
import ec.com.siga.model.SolucitudAuditoriaString;
import ec.com.siga.repository.FotoRepository;
import ec.com.siga.service.AuditService;
import ec.com.siga.service.BackOfficeService;
import ec.com.siga.service.InformeService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

@Controller
public class TableAuditRequestsController {

	@Autowired
	@Qualifier("informeServicio")
	private InformeService informeServicio;

	@Autowired
	@Qualifier("auditService")
	private AuditService auditService;

	@Autowired
	@Qualifier("backOfficeService")
	private BackOfficeService backOfficeService;
	
	@Autowired
	@Qualifier("fotoRepository")
	private FotoRepository fotoRepository;

	@GetMapping("/tableAuditsRequests")
	@ResponseBody
	public ModelAndView tableAuditsRequests(String usuario) {
		ModelAndView mav = new ModelAndView("tableAuditsRequests");
		mav.addObject("contacts", informeServicio.findAllReportRequests());
		mav.addObject("usuario", usuario);
		return mav;
	}

	@GetMapping("/tableAuditsProsesing")
	@ResponseBody
	public ModelAndView tableAuditsProsesing(String usuario) {
		ModelAndView mav = new ModelAndView("tableAuditsProsesing");
		mav.addObject("contacts", informeServicio.findAllReportProsesing());
		mav.addObject("usuario", usuario);
		return mav;
	}

	@GetMapping("/editAuditsRequests")
	@ResponseBody
	public ModelAndView showEditAdminForm(Integer id, String usuario) {
		ModelAndView mav = new ModelAndView("editAuditsRequests");
		mav.addObject("reporte", informeServicio.findReport(id));
		mav.addObject("auditores", backOfficeService.findAllAudit());
		mav.addObject("usuario", usuario);
		return mav;
	}

	@PostMapping("/saveAuditsRequests")
	public ModelAndView saveAdmin(int informeId, String usuario, String auditorId, SolucitudAuditoriaString sa)
			throws Exception {
		ModelAndView mav = new ModelAndView("/dashboardBack");
		backOfficeService.saveInforme(informeId, auditorId, sa);
		mav.addObject("username", usuario);
		return mav;
	}

	@GetMapping("/findAuditsRequests")
	@ResponseBody
	public Informe findOne(Integer id) {
		return informeServicio.findReport(id);
	}

	@GetMapping("/cancelAuditsRequests")
	public String cancel() {
		return "redirect:/dashboardAdmin";
	}

	@GetMapping("/deleteAuditsRequests")
	public void deleteCountry(Integer adminId) {
		informeServicio.deleteReport(adminId);
		;
	}

	@GetMapping("/checkReports")
	@ResponseBody
	public ModelAndView showTableCheckReports(Integer id, String usuario) {
		ModelAndView mav = new ModelAndView("tableCheckReports");
		System.out.println(id);
		List<CheckList> ckl = backOfficeService.findAllCheckList(informeServicio.findReport(id).getDatoComunId().getSolicitudAuditoriaId());
		
		for(CheckList ck : ckl){
			DatoEspecifico des = ck.getDatoEspecificoId();
			Foto foto = des.getFotoId();
			String imagen64 =  Base64.encodeBase64String(foto.getFoto());
			foto.setFileName(imagen64);
			des.setFotoId(foto);
			ck.setDatoEspecificoId(des);
		}
		
		mav.addObject("checkList", ckl);
		//String encodedImage = Base64.encodeBase64String(ckl.get(0).getDatoEspecificoId().getFotoId().getFoto());
		// mav.addObject("auditores", backOfficeService.findAllAudit());
		//mav.addObject("imagen", encodedImage);
		mav.addObject("usuario", usuario);
		return mav;
	}

	/*
	 * @GetMapping("product/image") public byte[] imagen(@PathVariable Integer id,
	 * HttpServletResponse response) throws IOException {
	 * response.setContentType("image/jpeg"); // Or whatever format you wanna use
	 * 
	 * Foto product = fotoRepository.findById(70).get();
	 * 
	 * InputStream is = new ByteArrayInputStream(product.getFoto()); return
	 * IOUtils.toByteArray(is); }
	 */

}
