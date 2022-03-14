package it.epicode.beservice.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fattura")
public class Fattura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate data;
	private Long numero;
	private Integer anno;
	private Double importo;
	@ManyToOne
	private StatoFattura stato;
	@ManyToOne
	private Cliente cliente;

	public Fattura() {
	}

	public Fattura(LocalDate data, Long numero, Double importo, StatoFattura stato, Cliente cliente) {
		this.data = data;
		this.numero = numero;
		this.anno = data.getYear();
		this.importo = importo;
		this.stato = stato;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public LocalDate getData() {
		return data;
	}

	public Long getNumero() {
		return numero;
	}

	public Integer getAnno() {
		return anno;
	}

	public Double getImporto() {
		return importo;
	}

	public StatoFattura getStato() {
		return stato;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public void setImporto(Double importo) {
		this.importo = importo;
	}

	public void setStato(StatoFattura stato) {
		this.stato = stato;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Fattura [id=" + id + ", data=" + data + ", numero=" + numero + ", anno=" + anno + ", importo=" + importo
				+ ", stato=" + stato + ", cliente=" + cliente + "]";
	}

}
