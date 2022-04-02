package it.epicode.beservice.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import it.epicode.beservice.model.Comune;
import it.epicode.beservice.service.ComuneService;
import it.epicode.beservice.service.ProvinciaService;

@RestController
@RequestMapping("/comunecontroller")
public class ComuneController {

	@Autowired
	ComuneService comuneService;
	@Autowired
	ProvinciaService provinciaService;
	
	
	@Operation(summary = "SALVA COMUNE")
	@PostMapping("/salvacomune")
	public void salvaComune(@RequestBody Comune c) {
		comuneService.salvaComune(c);
	}
	

	@Operation(summary = "AGGIORNA COMUNE")
	@PutMapping("/aggiornacomune/{id}")
	public void aggiornaComune(@PathVariable(required = true) Long id, @RequestBody Comune c) {
		comuneService.updateComune(id, c);
	}
	
	
	@Operation(summary = "ELIMINA COMUNE")
	@DeleteMapping("/eliminacomune/{id}")
	public void eliminaCliente(@PathVariable(required = true) Long id) {
		comuneService.eliminaComune(id);
	}
	
	@GetMapping("/caricamentocsvcomuni")
	public void caricamentoCsvComuni() {
		File csvFile = new File("src/main/resources/csv/comuni-italiani.csv");
		
		try {
			String fileStringa = FileUtils.readFileToString(csvFile, "UTF-8");
			List<String> listStringPerRiga = Arrays.asList(fileStringa.split("\\r?\\n"));
			for(String comune : listStringPerRiga) {
				String[] arrayComune = comune.split(";");
				Comune c = new Comune(arrayComune[2], provinciaService.getProvinciaByNome(arrayComune[3]));
				comuneService.salvaComune(c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	@Operation(summary = "CERCA COMUNE PER NOME")
	@GetMapping("/getcomunebynome")
	public Comune getComuneByNome(@RequestParam String nome) {
		return comuneService.getComuneByNome(nome);
	}
}
