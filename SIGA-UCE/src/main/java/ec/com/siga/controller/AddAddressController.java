package ec.com.siga.controller;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ec.com.siga.entity.Canton;
import ec.com.siga.entity.Direccion;
import ec.com.siga.entity.Parroquia;
import ec.com.siga.entity.ProvinciaEstado;
import ec.com.siga.model.DireccionString;
import ec.com.siga.service.AddressService;

@Controller
public class AddAddressController {

	private static final Log LOG = LogFactory.getLog(AddAddressController.class);
	
	@Autowired
	@Qualifier("addressService")
	private AddressService addressService;

	@GetMapping("/addAddress")
	public ModelAndView showForm() {
		ModelAndView mav = new ModelAndView("addAddress");
		mav.addObject("addressTipes", addressService.findAllDireccionTipo());
		mav.addObject("paices", addressService.findAllPais());
		return mav;
	}

	@PostMapping("/saveAddress")
	public String addAddress(@RequestBody  DireccionString dir) {
		// addressService.saveDireccion(dir);
		LOG.info(dir.getDireccionTipoId());
		LOG.info(dir.getParroquiaId());
		LOG.info(dir.getPostalCode());
		LOG.info(dir.getAddress());
		
		addressService.saveDireccion(addressService.converterDireccionStringToDirection(dir));

		return "/dashboardAdmin";
	}
	
	@GetMapping("/selectProvincia")
	public @ResponseBody List<ProvinciaEstado> loginDisponible2(@ModelAttribute("login") String login) {
		return addressService.findAllByPaisId(Integer.parseInt(login));

	}

	@GetMapping("/selectCanton")
	public @ResponseBody List<Canton> loginDisponible3(@ModelAttribute("login") String login) {
		return addressService.findAllByPrpvinciaId(Integer.parseInt(login));

	}

	@GetMapping("/selectparish")
	public @ResponseBody List<Parroquia> loginDisponible4(@ModelAttribute("login") String login) {
		return addressService.findAllByCantonId(Integer.parseInt(login));

	}

	
	
}
