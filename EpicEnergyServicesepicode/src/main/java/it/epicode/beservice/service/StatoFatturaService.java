package it.epicode.beservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.StatoFattura;
import it.epicode.beservice.repositories.StatoFatturaRepository;

@Service
public class StatoFatturaService {

	@Autowired
	StatoFatturaRepository statoFatturaRepository;
	
	public void salvaStatoFattura(StatoFattura s) {
		statoFatturaRepository.save(s);
	}
	
	public void updateStatoFattura(Long id, StatoFattura s) {
		StatoFattura statoFatturaDaAggiornare = statoFatturaRepository.getById(id);
		statoFatturaDaAggiornare.setNome(s.getNome());
		statoFatturaRepository.save(statoFatturaDaAggiornare);
	}
	
	public void eliminaStatoFattura(Long id) {
		statoFatturaRepository.deleteById(id);
	}
	
	public Boolean existsByNome(String nome) {
		return statoFatturaRepository.existsByNome(nome);
	}
	
	public StatoFattura getByNome(String nome) {
		return statoFatturaRepository.getByNome(nome);
	}
}
