package it.epicode.beservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import it.epicode.beservice.model.Provincia;

@Component
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {

	@Query("SELECT p FROM Provincia p WHERE nome=:nome")
	public Provincia getProvinciaByNome(String nome);

	@Query("SELECT p FROM Provincia p WHERE p.regione.nome=:nomeRegione")
	public List<Provincia> getProvinceByNomeRegione(String nomeRegione);
	
}
