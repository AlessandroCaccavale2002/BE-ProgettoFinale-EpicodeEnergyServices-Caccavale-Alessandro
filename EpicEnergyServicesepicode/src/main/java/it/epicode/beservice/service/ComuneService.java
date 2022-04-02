package it.epicode.beservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Comune;
import it.epicode.beservice.repositories.ComuneRepository;

@Service
public class ComuneService {

	@Autowired
	ComuneRepository comuneRepository;
	
	public void salvaComune(Comune c) {
		comuneRepository.save(c);
	}
	
	public void updateComune(Long id, Comune c) {
		Comune comuneDaAggiornare = comuneRepository.getById(id);
		comuneDaAggiornare.setNome(c.getNome());
		comuneDaAggiornare.setProvincia(c.getProvincia());
		comuneRepository.save(comuneDaAggiornare);
	}
	
	public void eliminaComune(Long id) {
		comuneRepository.deleteById(id);
	}
	
	public Comune getComuneByNome(String nome) {
		return comuneRepository.getComuneByNome(nome);
	}
	
	public Page<Comune> findAllComunePageSize(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Comune> pagedResult = comuneRepository.findAll(paging);
        if(pagedResult.hasContent()) {
      return pagedResult;
      } else {
          return null;
      }
    }
}
