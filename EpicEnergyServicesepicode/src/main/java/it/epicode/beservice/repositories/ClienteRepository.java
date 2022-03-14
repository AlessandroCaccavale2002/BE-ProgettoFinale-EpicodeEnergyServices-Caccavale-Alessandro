package it.epicode.beservice.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import it.epicode.beservice.model.Cliente;

@Component
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	@Query("SELECT c FROM Cliente c WHERE c.fatturatoAnnuale=:fatturatoAnnuale")
	public List<Cliente> getClientiByFatturatoAnnuale(Double fatturatoAnnuale, Pageable pageable);
	
	@Query("SELECT c FROM Cliente c WHERE c.dataInserimento=:dataInserimento")
	public List<Cliente> getClientiByDataInserimento(LocalDate dataInserimento, Pageable pageable);
	
	@Query("SELECT c FROM Cliente c WHERE c.dataUltimoContatto=:dataUltimoContatto")
    public List<Cliente> getClienteByDataUltimoContatto(LocalDate dataUltimoContatto, Pageable pageable);
	
	@Query("SELECT c FROM Cliente c WHERE c.ragioneSociale LIKE %:ragioneSociale%")
	public List<Cliente> getClienteByRagioneSocialeParziale(String ragioneSociale, Pageable pageable);
	
	public Cliente getByRagioneSociale(String ragioneSociale);
	
	@Query("SELECT c FROM Cliente c WHERE c.fatturatoAnnuale BETWEEN :importoMin AND :importoMax")
	public List<Cliente> getClienteByRangeFatturatoAnnuale(Double importoMin, Double importoMax, Pageable pageable);
	
	@Query("SELECT c FROM Cliente c WHERE c.indirizzoSedeOperativa.comune.provincia.regione.nome LIKE %:nomeRegione%")
	public List<Cliente> getClienteByRegione(String nomeRegione);

	@Query("SELECT c FROM Cliente c WHERE c.indirizzoSedeOperativa.comune.provincia.nome LIKE %:nomeProvincia%")
	public List<Cliente> getClienteByProvincia(String nomeProvincia);
}


