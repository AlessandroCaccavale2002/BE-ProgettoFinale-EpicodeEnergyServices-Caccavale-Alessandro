package it.epicode.beservice.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.beservice.model.Regione;
import it.epicode.beservice.service.RegioneService;

@RestController
@RequestMapping("/regionecontroller")
public class RegioneController {
	
	@Autowired
	RegioneService regioneService;
	
	@GetMapping("/caricamentocsvregioni")
	public void caricamentoCsvRegioni() {
		File csvFile = new File("src/main/resources/csv/province-italiane.csv");
		
		try {
			String fileStringa = FileUtils.readFileToString(csvFile, "UTF-8");
			List<String> listStringPerRiga = Arrays.asList(fileStringa.split("\\r?\\n"));
			for(String regione : listStringPerRiga) {
				String[] arrayProv = regione.split(";");
				if(!regioneService.existsByNome(arrayProv[2])) {
					Regione r = new Regione(arrayProv[2]);
					regioneService.salvaRegione(r);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
