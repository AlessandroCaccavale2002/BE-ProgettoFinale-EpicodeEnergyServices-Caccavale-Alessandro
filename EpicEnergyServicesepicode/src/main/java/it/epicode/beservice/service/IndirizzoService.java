package it.epicode.beservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Indirizzo;
import it.epicode.beservice.repositories.IndirizzoRepository;

@Service
public class IndirizzoService {

	@Autowired
	IndirizzoRepository indirizzoRepository;
	
	public void salvaIndirizzo(Indirizzo i) {
		indirizzoRepository.save(i);
	}
	
	public void updateIndirizzo(Long id, Indirizzo i) {
		Indirizzo IndirizzoDaAggiornare = indirizzoRepository.getById(id);
		IndirizzoDaAggiornare.setVia(i.getVia());
		IndirizzoDaAggiornare.setCivico(i.getCivico());
		IndirizzoDaAggiornare.setCap(i.getCap());
		IndirizzoDaAggiornare.setLocalita(i.getLocalita());
		IndirizzoDaAggiornare.setComune(i.getComune());
		indirizzoRepository.save(IndirizzoDaAggiornare);
	}
	
	public void eliminaIndirizzo(Long id) {
		indirizzoRepository.deleteById(id);
	}
	
	public boolean existsByVia(String via) {
		return indirizzoRepository.existsByVia(via);
	}
	
	public Indirizzo getByVia(String via) {
		return indirizzoRepository.getByVia(via);
	}
}
