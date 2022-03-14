package it.epicode.beservice.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.beservice.model.Cliente;
import it.epicode.beservice.model.Indirizzo;
import it.epicode.beservice.model.Provincia;
import it.epicode.beservice.model.Regione;
import it.epicode.beservice.model.TipoCliente;
import it.epicode.beservice.service.ClienteService;
import it.epicode.beservice.service.ComuneService;
import it.epicode.beservice.service.IndirizzoService;
import it.epicode.beservice.service.ProvinciaService;
import it.epicode.beservice.service.RegioneService;

@RestController
@RequestMapping("/clientecontroller")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	@Autowired
	IndirizzoService indirizzoService;
	@Autowired
	ComuneService comuneService;
	@Autowired
	RegioneService regioneService;
	@Autowired
	ProvinciaService provinciaService;

	@PostMapping("/salvacliente")
	public void salvaCliente(@RequestBody Cliente c) {
		clienteService.salvaCliente(c);
	}

	@PostMapping("/updatecliente/{id}")
	public void updateCliente(@PathVariable(required = true) Long id, @RequestBody Cliente c) {
		clienteService.updateCliente(id, c);
	}

	@GetMapping("/eliminacliente/{id}")
	public void eliminaCliente(@PathVariable(required = true) Long id) {
		clienteService.eliminaCliente(id);
	}

	@GetMapping(value = "/clientepagesort", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cliente>> myGetAllEdificioPageSizeSort(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "2") Integer size, @RequestParam(defaultValue = "id") String sort) {
		List<Cliente> list = clienteService.findAllClientePageSizeSort(page, size, sort);
		return new ResponseEntity<List<Cliente>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/getclientibyfatturatoannuale")
	public List<Cliente> getClientiByFatturatoAnnuale(@RequestParam Double fatturatoAnnuale,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "4") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		return clienteService.getClientiByFatturatoAnnuale(fatturatoAnnuale, pag);
	}

	@GetMapping("/getclientibydatainserimento")
	public List<Cliente> getClientiByDataInserimento(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInserimento,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "4") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		return clienteService.getClientiByDataInserimento(dataInserimento, pag);
	}

	@GetMapping("/getclientebydataultimocontatto")
	public List<Cliente> getClienteByDataUltimoContatto(
			@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataUltimoContatto,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "4") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		return clienteService.getClienteByDataUltimoContatto(dataUltimoContatto, pag);
	}

	@GetMapping("/getclientebyragionesocialeparziale")
	public List<Cliente> getClienteByRagioneSocialeParziale(@RequestParam String ragioneSociale,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "4") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		return clienteService.getClienteByRagioneSocialeParziale(ragioneSociale, pag);
	}
	
	@GetMapping("/salvaclienteform")
	public void salvaClienteForm(@RequestParam String ragioneSociale, Long partitaIva, TipoCliente tipoCliente,
			String email, String pec, String telefono, String nomeContatto, String cognomeContatto,
			String telefonoContatto, String emailContatto, String via, Integer civico, String cap, String localita,
			String nomeComune, String viaSD, Integer civicoSD, String capSD, String localitaSD, String nomeComuneSD,
			@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataUltimoContatto, Double fatturatoAnnuale) {
		Cliente c = new Cliente();
		if (!indirizzoService.existsByVia(via)) {
			Indirizzo iSedeOperativa = new Indirizzo(via, civico, cap, localita,
					comuneService.getComuneByNome(nomeComune));
			indirizzoService.salvaIndirizzo(iSedeOperativa);
		}
		if (!indirizzoService.existsByVia(viaSD)) {
			Indirizzo iSedeLocale = new Indirizzo(viaSD, civicoSD, capSD, localitaSD,
					comuneService.getComuneByNome(nomeComuneSD));
			indirizzoService.salvaIndirizzo(iSedeLocale);
		}
		c.setRagioneSociale(ragioneSociale);
		c.setPartitaIva(partitaIva);
		c.setTipoCliente(tipoCliente);
		c.setEmail(email);
		c.setPec(pec);
		c.setTelefono(telefono);
		c.setNomeContatto(nomeContatto);
		c.setCognomeContatto(cognomeContatto);
		c.setTelefonoContatto(telefonoContatto);
		c.setEmailContatto(emailContatto);
		c.setIndirizzoSedeOperativa(indirizzoService.getByVia(via));
		c.setIndirizzoSedeLegale(indirizzoService.getByVia(viaSD));
		c.setDataInserimento(LocalDate.now());
		c.setDataUltimoContatto(dataUltimoContatto);
		c.setFatturatoAnnuale(fatturatoAnnuale);
		clienteService.salvaCliente(c);
	}
	
	@GetMapping("/eliminaclienteform")
	public void eliminaClienteForm(@RequestParam Long id) {
		clienteService.eliminaCliente(id);
	}
	
	@GetMapping("/dettaglicliente/{id}")
	public ModelAndView dettagliCliente(@PathVariable Long id) {
		ModelAndView model = new ModelAndView();
		model.addObject("cliente", clienteService.getById(id));
		model.setViewName("dettaglicliente");
		return model;
	}
	
	
	@GetMapping("/getclientibyfatturatoannualeform")
	public ModelAndView getClientiByFatturatoAnnualeForm(@RequestParam Double fatturatoAnnuale,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "1000") Integer size) {
		ModelAndView model = new ModelAndView();
		Pageable pag = PageRequest.of(page, size);
		List<Cliente> list = clienteService.getClientiByFatturatoAnnuale(fatturatoAnnuale, pag);
		model.addObject("listaClienti", list);
		model.setViewName("ritornochiamataclienti");
		return model;
	}
	
	@GetMapping("/getclientibydatainserimentoform")
	public ModelAndView getClientiByDataInserimentoForm(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInserimento,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "1000") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		ModelAndView model = new ModelAndView();
		List<Cliente> list = clienteService.getClientiByDataInserimento(dataInserimento, pag);
		model.addObject("listaClienti", list);
		model.setViewName("ritornochiamataclienti");
		return model;
	}
	
	@GetMapping("/getclientebydataultimocontattoform")
	public ModelAndView getClienteByDataUltimoContattoForm(
			@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataUltimoContatto,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "1000") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		ModelAndView model = new ModelAndView();
		List<Cliente> list = clienteService.getClienteByDataUltimoContatto(dataUltimoContatto, pag);
		model.addObject("listaClienti", list);
		model.setViewName("ritornochiamataclienti");
		return model;
	}

	@GetMapping("/getclientebyragionesocialeparzialeform")
	public ModelAndView getClienteByRagioneSocialeParzialeForm(@RequestParam String ragioneSociale,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "1000") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		ModelAndView model = new ModelAndView();
		List<Cliente> list = clienteService.getClienteByRagioneSocialeParziale(ragioneSociale, pag);
		model.addObject("listaClienti", list);
		model.setViewName("ritornochiamataclienti");
		return model;
	}
	
	@GetMapping("/dashboard")
	public ModelAndView dashboard(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "1000") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		ModelAndView model = new ModelAndView();
		List<Cliente> lowrange = clienteService.getClienteByRangeFatturatoAnnuale(0.00, 20000.00, pag);
		List<Cliente> midrange = clienteService.getClienteByRangeFatturatoAnnuale(20000.01, 50000.00, pag);
		List<Cliente> highrange = clienteService.getClienteByRangeFatturatoAnnuale(50000.01, 1000000.00, pag);
		model.addObject("lowrange", lowrange);
		model.addObject("midrange", midrange);
		model.addObject("highrange", highrange);
		
		List<Regione> listaRegioni = regioneService.findRegioneAll();
		List<Cliente> clientiByRegione = new ArrayList<Cliente>();
		Map<Regione, Integer> mappaRegioniSize = new HashMap<>();
		for(Regione r : listaRegioni) {
			String nome = r.getNome();
			clientiByRegione = clienteService.getClienteByRegione(nome);
			mappaRegioniSize.put(r, clientiByRegione.size());
		}
		model.addObject("mappa", mappaRegioniSize);
		model.setViewName("dashboard");
		return model;
	}
	
	@GetMapping("/selectlista/{lista}")
	public ModelAndView selectListaRange(@PathVariable String lista, @RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "1000") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		ModelAndView model = new ModelAndView();
		if(lista.equals("lowrange")) {
			List<Cliente> lowrange = clienteService.getClienteByRangeFatturatoAnnuale(0.00, 20000.00, pag);
			model.addObject("listaClienti", lowrange);
		}
		if(lista.equals("midrange")) {
			List<Cliente> midrange = clienteService.getClienteByRangeFatturatoAnnuale(20000.01, 50000.00, pag);
			model.addObject("listaClienti", midrange);
		}
		if(lista.equals("highrange")) {
			List<Cliente> highrange = clienteService.getClienteByRangeFatturatoAnnuale(50000.01, 1000000.00, pag);
			model.addObject("listaClienti", highrange);
		}
		model.setViewName("ritornochiamataclienti");
		return model;
	}
	
	@GetMapping("/selectregione/{nome}")
	public ModelAndView selectRegione(@PathVariable String nome) {
		ModelAndView model = new ModelAndView();
		List<Provincia> listaProvince = provinciaService.getProvinceByNomeRegione(nome);
		List<Cliente> clientiByProvincia = new ArrayList<Cliente>();
		Map<Provincia, Integer> mappaProvinceSize = new HashMap<>();
		for(Provincia p : listaProvince) {
			String nomeProvincia = p.getNome();
			clientiByProvincia = clienteService.getClienteByProvincia(nomeProvincia);
			mappaProvinceSize.put(p, clientiByProvincia.size());
		}
		model.addObject("mappaprov", mappaProvinceSize);
		
		model.setViewName("listaprovince");
		return model;
	}
	
	@GetMapping("/selectprovincia/{nome}")
	public ModelAndView selectProvincia(@PathVariable String nome) {
		ModelAndView model = new ModelAndView();
		List<Cliente> clientiByProvincia = clienteService.getClienteByProvincia(nome);
		model.addObject("listaClienti", clientiByProvincia);
		model.setViewName("ritornochiamataclienti");
		return model;
	}
	

}
