package it.epicode.beservice.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.beservice.model.Provincia;
import it.epicode.beservice.service.ProvinciaService;
import it.epicode.beservice.service.RegioneService;

@RestController
@RequestMapping("/provinciacontroller")
public class ProvinciaController {

	@Autowired
	ProvinciaService provinciaService;
	@Autowired
	RegioneService regioneService;
	
	@PostMapping("/salvaprovincia")
	public void salvaProvincia(@RequestBody Provincia p) {
		provinciaService.salvaProvincia(p);
	}
	
	@PostMapping("/aggiornaprovincia/{id}")
	public void aggiornaProvincia(@PathVariable(required = true) Long id, @RequestBody Provincia p) {
		provinciaService.updateProvincia(id, p);
	}
	
	@GetMapping("/eliminaprovincia/{id}")
	public void eliminaProvincia(@PathVariable(required = true) Long id) {
		provinciaService.eliminaProvincia(id);
	}
	
	@GetMapping("/caricamentocsvprovince")
	public void caricamentoCsvProvince() {
		File csvFile = new File("src/main/resources/csv/province-italiane.csv");
		
		try {
			String fileStringa = FileUtils.readFileToString(csvFile, "UTF-8");
			List<String> listStringPerRiga = Arrays.asList(fileStringa.split("\\r?\\n"));
			for(String provincia : listStringPerRiga) {
				String[] arrayProv = provincia.split(";");
				Provincia p = new Provincia(arrayProv[1], arrayProv[0], regioneService.getRegioneByNome(arrayProv[2]));
				provinciaService.salvaProvincia(p);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/getprovinciabynome")
	public Provincia getProvinciaByNome(@RequestParam String nome) {
		return provinciaService.getProvinciaByNome(nome);		
	}
	
}
