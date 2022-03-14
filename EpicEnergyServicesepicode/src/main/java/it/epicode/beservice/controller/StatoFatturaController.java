package it.epicode.beservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.beservice.model.StatoFattura;
import it.epicode.beservice.service.StatoFatturaService;

@RestController
@RequestMapping("/statofatturacontroller")
public class StatoFatturaController {

	@Autowired
	StatoFatturaService statoFatturaService;
	
	@PostMapping("/salvastatofattura")
	public void salvaStatoFattura(@RequestBody StatoFattura s) {
		statoFatturaService.salvaStatoFattura(s);
	}
	
	@PostMapping("/aggiornastatofattura/{id}")
	public void aggiornaStatoFattura(@PathVariable(required = true) Long id, @RequestBody StatoFattura s) {
		statoFatturaService.updateStatoFattura(id, s);
	}
	
	@GetMapping("/eliminastatofattura/{id}")
	public void eliminaStatoFattura(@PathVariable(required = true) Long id) {
		statoFatturaService.eliminaStatoFattura(id);
	}
	
}
