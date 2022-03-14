package it.epicode.beservice.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import it.epicode.beservice.model.Fattura;

@Component
public interface FatturaRepository extends JpaRepository<Fattura, Long> {

	@Query("SELECT f FROM Fattura f WHERE f.cliente.ragioneSociale=:ragioneSociale")
	public List<Fattura> getFatturaByCliente(String ragioneSociale, Pageable pageable);
	
	@Query("SELECT f FROM Fattura f WHERE f.stato.nome=:statoFattura")
	public List<Fattura> getFatturaByStatoFattura(String statoFattura, Pageable pageable);
	
	@Query("SELECT f FROM Fattura f WHERE f.data=:data")
	public List<Fattura> getFatturaByData(LocalDate data, Pageable pageable);
	
	@Query("SELECT f FROM Fattura f WHERE f.anno=:anno")
	public List<Fattura> getFatturaByAnno(Integer anno, Pageable pageable);
	
	@Query("SELECT f FROM Fattura f WHERE f.importo BETWEEN :importoMin AND :importoMax")
	public List<Fattura> getFatturaByRangeImporto(Double importoMin, Double importoMax, Pageable pageable);
}
