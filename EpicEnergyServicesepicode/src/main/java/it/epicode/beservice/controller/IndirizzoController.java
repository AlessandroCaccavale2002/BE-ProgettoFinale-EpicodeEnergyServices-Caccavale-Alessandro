package it.epicode.beservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.beservice.model.Indirizzo;
import it.epicode.beservice.service.IndirizzoService;

@RestController
@RequestMapping("/indirizzocontroller")
public class IndirizzoController {

	@Autowired
	IndirizzoService indirizzoService;
	
	@PostMapping("/salvaindirizzo")
	public void salvaIndirizzo(@RequestBody Indirizzo i) {
		indirizzoService.salvaIndirizzo(i);
	}
	
	@PostMapping("/aggiornaindirizzo/{id}")
	public void aggiornaIndirizzo(@PathVariable(required = true) Long id, @RequestBody Indirizzo i) {
		indirizzoService.updateIndirizzo(id, i);
	}
	
	@GetMapping("/eliminaindirizzo/{id}")
	public void eliminaIndirizzo(@PathVariable(required = true) Long id) {
		indirizzoService.eliminaIndirizzo(id);
	}
	
}
