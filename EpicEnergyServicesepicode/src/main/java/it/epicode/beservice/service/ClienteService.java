package it.epicode.beservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Cliente;
import it.epicode.beservice.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	public void salvaCliente(Cliente c) {
		clienteRepository.save(c);
	}
	
	public void updateCliente(Long id, Cliente c) {
		Cliente clienteDaAggiornare = clienteRepository.getById(id);
		clienteDaAggiornare.setRagioneSociale(c.getRagioneSociale());
		clienteDaAggiornare.setPartitaIva(c.getPartitaIva());
		clienteDaAggiornare.setTipoCliente(c.getTipoCliente());
		clienteDaAggiornare.setEmail(c.getEmail());
		clienteDaAggiornare.setPec(c.getPec());
		clienteDaAggiornare.setTelefono(c.getTelefono());
		clienteDaAggiornare.setNomeContatto(c.getNomeContatto());
		clienteDaAggiornare.setCognomeContatto(c.getCognomeContatto());
		clienteDaAggiornare.setTelefonoContatto(c.getTelefonoContatto());
		clienteDaAggiornare.setEmailContatto(c.getEmailContatto());
		clienteDaAggiornare.setIndirizzoSedeOperativa(c.getIndirizzoSedeOperativa());
		clienteDaAggiornare.setIndirizzoSedeLegale(c.getIndirizzoSedeLegale());
		clienteDaAggiornare.setDataInserimento(c.getDataInserimento());
		clienteDaAggiornare.setDataUltimoContatto(c.getDataUltimoContatto());
		clienteDaAggiornare.setFatturatoAnnuale(c.getFatturatoAnnuale());
		clienteRepository.save(clienteDaAggiornare);
	}
	
	public void eliminaCliente(Long id) {
		clienteRepository.deleteById(id);;
	}
	
	public List<Cliente> findAllClientePageSizeSort(Integer page, Integer size, String sort) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sort));
        Page<Cliente> pagedResult = clienteRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Cliente>();
        }}
	
	public List<Cliente> getClientiByFatturatoAnnuale(Double fatturatoAnnuale, Pageable pageable){
		return clienteRepository.getClientiByFatturatoAnnuale(fatturatoAnnuale, pageable);
	}
	
	public List<Cliente> getClientiByDataInserimento(LocalDate dataInserimento, Pageable pageable){
		return clienteRepository.getClientiByDataInserimento(dataInserimento, pageable);
	}
	
	public List<Cliente> getClienteByDataUltimoContatto(LocalDate dataUltimoContatto,Pageable pageable){
        return clienteRepository.getClienteByDataUltimoContatto(dataUltimoContatto,pageable);
    }
	
	public List<Cliente> getClienteByRagioneSocialeParziale(String ragioneSociale, Pageable pageable){
		return clienteRepository.getClienteByRagioneSocialeParziale(ragioneSociale, pageable);
	}
	
	public Cliente getByRagioneSociale(String ragioneSociale) {
		return clienteRepository.getByRagioneSociale(ragioneSociale);
	}
	
	public List<Cliente> getClienteByRangeFatturatoAnnuale(Double importoMin, Double importoMax, Pageable pageable) {
		return clienteRepository.getClienteByRangeFatturatoAnnuale(importoMin, importoMax, pageable);
	}
	
	public Cliente getById(Long id) {
		return clienteRepository.getById(id);
	}
	
	public List<Cliente> getClienteByRegione(String nomeRegione) {
		return clienteRepository.getClienteByRegione(nomeRegione);
	}

	public List<Cliente> getClienteByProvincia(String nomeProvincia) {
		return clienteRepository.getClienteByProvincia(nomeProvincia);
	}
}
