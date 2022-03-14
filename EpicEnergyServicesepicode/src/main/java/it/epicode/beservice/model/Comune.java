package it.epicode.beservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comune")
public class Comune {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@ManyToOne
	private Provincia provincia;

	public Comune() {
	}

	public Comune(String nome, Provincia provincia) {
		this.nome = nome;
		this.provincia = provincia;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	@Override
	public String toString() {
		return "Comune [id=" + id + ", nome=" + nome + ", provincia=" + provincia + "]";
	}

}
