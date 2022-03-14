package it.epicode.beservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Regione;
import it.epicode.beservice.repositories.RegioneRepository;

@Service
public class RegioneService {

	@Autowired
	RegioneRepository regioneRepository;
	
	public Regione getRegioneByNome(String nome) {
		return regioneRepository.getRegioneByNome(nome);
	}
	
	public void salvaRegione(Regione r) {
		regioneRepository.save(r);
	}

	public boolean existsByNome(String nome) {
		return regioneRepository.existsByNome(nome);
	}

	public List<Regione> findRegioneAll() {
		return regioneRepository.findAll();
	}
	
}
