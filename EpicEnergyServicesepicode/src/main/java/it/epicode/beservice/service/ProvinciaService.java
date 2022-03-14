package it.epicode.beservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Provincia;
import it.epicode.beservice.repositories.ProvinciaRepository;

@Service
public class ProvinciaService {

	@Autowired
	ProvinciaRepository provinciaRepository;
	
	public void salvaProvincia(Provincia p) {
		provinciaRepository.save(p);
	}
	
	public void updateProvincia(Long id, Provincia p) {
		Provincia provDaAggiornare = provinciaRepository.getById(id);
		provDaAggiornare.setNome(p.getNome());
		provDaAggiornare.setSigla(p.getSigla());
		provinciaRepository.save(provDaAggiornare);
	}
	
	public void eliminaProvincia(Long id) {
		provinciaRepository.deleteById(id);
	}
	
	public Provincia getProvinciaByNome(String nome) {
		return provinciaRepository.getProvinciaByNome(nome);
	}
	
	public List<Provincia> getProvinceByNomeRegione(String nomeRegione){
		return provinciaRepository.getProvinceByNomeRegione(nomeRegione);
	}
}
