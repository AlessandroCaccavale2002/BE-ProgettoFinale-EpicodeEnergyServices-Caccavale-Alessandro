package it.epicode.beservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "provincia")
public class Provincia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String sigla;
	@ManyToOne
	private Regione regione;

	public Provincia() {
	}

	public Provincia(String nome, String sigla, Regione regione) {
		this.nome = nome;
		this.sigla = sigla;
		this.regione = regione;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getSigla() {
		return sigla;
	}

	public Regione getRegione() {
		return regione;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public void setRegione(Regione regione) {
		this.regione = regione;
	}

	@Override
	public String toString() {
		return "Provincia [id=" + id + ", nome=" + nome + ", sigla=" + sigla + ", regione=" + regione + "]";
	}

}
