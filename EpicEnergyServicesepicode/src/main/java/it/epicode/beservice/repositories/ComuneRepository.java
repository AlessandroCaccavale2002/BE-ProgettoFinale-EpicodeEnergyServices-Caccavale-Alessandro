package it.epicode.beservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import it.epicode.beservice.model.Comune;

@Component
public interface ComuneRepository extends JpaRepository<Comune, Long> {

	public Comune getComuneByNome(String nome);
	
}
