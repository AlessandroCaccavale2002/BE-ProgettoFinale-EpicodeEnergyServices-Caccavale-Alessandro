package it.epicode.beservice.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Fattura;
import it.epicode.beservice.repositories.FatturaRepository;

@Service
public class FatturaService {

	@Autowired
	FatturaRepository fatturaRepository;
	
	public void salvaFattura(Fattura f) {
		fatturaRepository.save(f);
	}
	
	public void updateFattura(Long id, Fattura f) {
		Fattura fatturaDaAggiornare = fatturaRepository.getById(id);
		fatturaDaAggiornare.setData(f.getData());
		fatturaDaAggiornare.setNumero(f.getNumero());
		fatturaDaAggiornare.setAnno(f.getAnno());
		fatturaDaAggiornare.setImporto(f.getImporto());
		fatturaDaAggiornare.setStato(f.getStato());
		fatturaDaAggiornare.setCliente(f.getCliente());
		fatturaRepository.save(fatturaDaAggiornare);
	}
	
	public void eliminaFattura(Long id) {
		fatturaRepository.deleteById(id);
	}
	
	public List<Fattura> getFatturaByCliente(String ragioneSociale, Pageable pageable) {
		return fatturaRepository.getFatturaByCliente(ragioneSociale, pageable);
	}
	
	public List<Fattura> getFatturaByStatoFattura(String statoFattura, Pageable pageable) {
		return fatturaRepository.getFatturaByStatoFattura(statoFattura, pageable);
	}
	
	public List<Fattura> getFatturaByData(LocalDate data, Pageable pageable) {
		return fatturaRepository.getFatturaByData(data, pageable);
	}

	public List<Fattura> getFatturaByAnno(Integer anno, Pageable pageable) {
		return fatturaRepository.getFatturaByAnno(anno, pageable);
	}

	public List<Fattura> getFatturaByRangeImporto(Double importoMin, Double importoMax, Pageable pageable){
		return fatturaRepository.getFatturaByRangeImporto(importoMin, importoMax, pageable);
	}

}
