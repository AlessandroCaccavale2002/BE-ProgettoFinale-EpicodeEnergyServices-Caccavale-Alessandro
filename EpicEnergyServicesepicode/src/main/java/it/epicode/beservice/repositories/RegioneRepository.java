package it.epicode.beservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import it.epicode.beservice.model.Regione;

@Component
public interface RegioneRepository  extends JpaRepository<Regione, Long> {
	public Regione getRegioneByNome(String nome);

	public boolean existsByNome(String nome);
}
