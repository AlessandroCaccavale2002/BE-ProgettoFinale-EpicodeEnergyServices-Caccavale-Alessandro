package it.epicode.beservice.controller;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import it.epicode.beservice.model.Cliente;
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

	
	@Operation(summary = "SALVA CLIENTE")
	@PostMapping("/salvacliente")
	public void salvaCliente(@RequestBody Cliente c) {
		clienteService.salvaCliente(c);
	}

	
	@Operation(summary = "AGGIORNA CLIENTE")
	@PutMapping("/updatecliente/{id}")
	public void updateCliente(@PathVariable(required = true) Long id, @RequestBody Cliente c) {
		clienteService.updateCliente(id, c);
	}
	
	
	@Operation(summary = "ELIMINA CLIENTE")
	@DeleteMapping("/eliminacliente/{id}")
	public void eliminaCliente(@PathVariable(required = true) Long id) {
		clienteService.eliminaCliente(id);
	}


	//cliente con paginazione cerca
	@GetMapping(value = "/clientepagesort", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cliente>> myGetAllEdificioPageSizeSort(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "2") Integer size, @RequestParam(defaultValue = "id") String sort) {
		List<Cliente> list = clienteService.findAllClientePageSizeSort(page, size, sort);
		return new ResponseEntity<List<Cliente>>(list, new HttpHeaders(), HttpStatus.OK);
	}


	@Operation(summary = "CERCA CLIENTE PER FATTURATO ANNUALE")
    @GetMapping("/getclientibyfatturatoannuale")
	public List<Cliente> getClientiByFatturatoAnnuale(@RequestParam Double fatturatoAnnuale,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "4") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		return clienteService.getClientiByFatturatoAnnuale(fatturatoAnnuale, pag);
	}


	@Operation(summary = "CERCA CLIENTE PER DATA INSERIMENTO")
	@GetMapping("/getclientibydatainserimento")
	public List<Cliente> getClientiByDataInserimento(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInserimento,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "4") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		return clienteService.getClientiByDataInserimento(dataInserimento, pag);
	}


	@Operation(summary = "CERCA CLIENTE PER DATA 2020-10-03 ES..")
	@GetMapping("/getclientebydataultimocontatto")
	public List<Cliente> getClienteByDataUltimoContatto(
			@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataUltimoContatto,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "4") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		return clienteService.getClienteByDataUltimoContatto(dataUltimoContatto, pag);
	}


	@Operation(summary = "CERCA CLIENTE PER RAGIONE SOCIALE")
	@GetMapping("/getclientebyragionesocialeparziale")
	public List<Cliente> getClienteByRagioneSocialeParziale(@RequestParam String ragioneSociale,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "4") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		return clienteService.getClienteByRagioneSocialeParziale(ragioneSociale, pag);
	}
	
	

}
