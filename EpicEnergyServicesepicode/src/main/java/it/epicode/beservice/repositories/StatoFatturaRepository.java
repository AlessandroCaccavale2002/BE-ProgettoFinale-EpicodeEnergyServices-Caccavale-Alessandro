package it.epicode.beservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import it.epicode.beservice.model.StatoFattura;

@Component
public interface StatoFatturaRepository extends JpaRepository<StatoFattura, Long> {

	public Boolean existsByNome(String nome);
	
	@Query("SELECT s FROM StatoFattura s WHERE nome=:nome")
	public StatoFattura getByNome(String nome);
}
