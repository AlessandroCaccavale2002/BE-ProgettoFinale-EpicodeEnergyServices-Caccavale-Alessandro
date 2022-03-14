package it.epicode.beservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "indirizzo")
public class Indirizzo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String via;
	private Integer civico;
	private String cap;
	private String localita;
	@ManyToOne
	private Comune comune;

	public Indirizzo() {
	}

	public Indirizzo(String via, Integer civico, String cap, String localita, Comune comune) {
		this.via = via;
		this.civico = civico;
		this.cap = cap;
		this.localita = localita;
		this.comune = comune;
	}

	public Long getId() {
		return id;
	}

	public String getVia() {
		return via;
	}

	public Integer getCivico() {
		return civico;
	}

	public String getCap() {
		return cap;
	}

	public String getLocalita() {
		return localita;
	}

	public Comune getComune() {
		return comune;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public void setCivico(Integer civico) {
		this.civico = civico;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public void setComune(Comune comune) {
		this.comune = comune;
	}

	@Override
	public String toString() {
		return via + ", " + civico + ", " + cap + ", " + localita
				+ ", " + comune.getNome() + " " + comune.getProvincia().getSigla();
		}

}
