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
import it.epicode.beservice.model.StatoFattura;
import it.epicode.beservice.service.StatoFatturaService;

@RestController
@RequestMapping("/statofatturacontroller")
public class StatoFatturaController {

	@Autowired
	StatoFatturaService statoFatturaService;
	
	
	@Operation(summary = "SALVA FATTURA")
	@PostMapping("/salvastatofattura")
	public void salvaStatoFattura(@RequestBody StatoFattura s) {
		statoFatturaService.salvaStatoFattura(s);
	}
	
	
	@Operation(summary = "AGGIORNA FATTURA")
	@PutMapping("/aggiornastatofattura/{id}")
	public void aggiornaStatoFattura(@PathVariable(required = true) Long id, @RequestBody StatoFattura s) {
		statoFatturaService.updateStatoFattura(id, s);
	}
	

	@Operation(summary = "ELIMINA FATTURA")
	@DeleteMapping("/eliminastatofattura/{id}")
	public void eliminaStatoFattura(@PathVariable(required = true) Long id) {
		statoFatturaService.eliminaStatoFattura(id);
	}
	
}
