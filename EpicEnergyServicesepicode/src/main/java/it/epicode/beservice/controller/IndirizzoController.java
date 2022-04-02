package it.epicode.beservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import it.epicode.beservice.model.Indirizzo;
import it.epicode.beservice.service.IndirizzoService;

@RestController
@RequestMapping("/indirizzocontroller")
public class IndirizzoController {

	@Autowired
	IndirizzoService indirizzoService;
	

	@Operation(summary = "SALVA INDIRIZZO")
	@PostMapping("/salvaindirizzo")
	public void salvaIndirizzo(@RequestBody Indirizzo i) {
		indirizzoService.salvaIndirizzo(i);
	}
	

	@Operation(summary = "AGGIORNA INDIRIZZO")
	@PutMapping("/aggiornaindirizzo/{id}")
	public void aggiornaIndirizzo(@PathVariable(required = true) Long id, @RequestBody Indirizzo i) {
		indirizzoService.updateIndirizzo(id, i);
	}
	

	@Operation(summary = "ELIMINA INDIRIZZO")
	@DeleteMapping("/eliminaindirizzo/{id}")
	public void eliminaIndirizzo(@PathVariable(required = true) Long id) {
		indirizzoService.eliminaIndirizzo(id);
	}
	
}
