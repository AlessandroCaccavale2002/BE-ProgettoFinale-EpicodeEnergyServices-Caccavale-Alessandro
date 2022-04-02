package it.epicode.beservice.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
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
import it.epicode.beservice.model.Fattura;
import it.epicode.beservice.service.ClienteService;
import it.epicode.beservice.service.FatturaService;
import it.epicode.beservice.service.StatoFatturaService;

@RestController
@RequestMapping("/fatturacontroller")
public class FatturaController {

	@Autowired
	FatturaService fatturaService;
	@Autowired
	StatoFatturaService statoFatturaService;
	@Autowired
	ClienteService clienteService;

	
	@Operation(summary = "SALVA FATTURA")
	@PostMapping("/salvafattura")
	public void salvaFattura(@RequestBody Fattura f) {
		fatturaService.salvaFattura(f);
	}
	
	
	@Operation(summary = "AGGIORNA FATTURA")
	@PutMapping("/aggiornafattura/{id}")
	public void aggiornaFattura(@PathVariable(required = true) Long id, @RequestBody Fattura f) {
		fatturaService.updateFattura(id, f);
	}

	
	@Operation(summary = "ELIMINA FATTURA")
	@DeleteMapping("/eliminafattura/{id}")
	public void eliminaFattura(@PathVariable(required = true) Long id) {
		fatturaService.eliminaFattura(id);
	}

	
	@Operation(summary = "CERCA FATTURA PER NOME CLIENTE")
	@GetMapping("/getfatturabycliente")
	public List<Fattura> getFatturaByCliente(@RequestParam String ragioneSociale,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "4") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		return fatturaService.getFatturaByCliente(ragioneSociale, pag);
	}

	
	@Operation(summary = "CERCA FATTURA PER STATO")
	@GetMapping("/getfatturabystatofattura")
	public List<Fattura> getFatturaByStatoFattura(@RequestParam String statoFattura,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "4") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		return fatturaService.getFatturaByStatoFattura(statoFattura, pag);
	}


	@Operation(summary = "CERCA FATTURA PER DATA")
	@GetMapping("/getfatturabydata")
	public List<Fattura> getFatturaByData(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data, @RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "4") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		return fatturaService.getFatturaByData(data, pag);
	}


	@Operation(summary = "CERCA FATTURA PER ANNO")
	@GetMapping("/getfatturabyanno")
	public List<Fattura> getFatturaByAnno(@RequestParam Integer anno, @RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "4") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		return fatturaService.getFatturaByAnno(anno, pag);
	}

	
	@Operation(summary = "CERCA FATTURA PER IMPORTO")
	@GetMapping("/getfatturabyrangeimporto")
	public List<Fattura> getFatturaByRangeImporto(@RequestParam(defaultValue = "0") Double importoMin,
			@RequestParam(defaultValue = "10000000000") Double importoMax,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "4") Integer size) {
		Pageable pag = PageRequest.of(page, size);
		return fatturaService.getFatturaByRangeImporto(importoMin, importoMax, pag);
	}
	
			

	}


	

	

