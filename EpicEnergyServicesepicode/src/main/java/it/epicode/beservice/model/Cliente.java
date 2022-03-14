package it.epicode.beservice.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ragioneSociale;
	private Long partitaIva;
	@Enumerated(EnumType.STRING)
	private TipoCliente tipoCliente;
	private String email;
	private String pec;
	private String telefono;
	
	private String nomeContatto;
	private String cognomeContatto;
	private String telefonoContatto;
	private String emailContatto;
	
	@OneToOne
	private Indirizzo indirizzoSedeOperativa;
	@OneToOne
	private Indirizzo indirizzoSedeLegale;
	
	private LocalDate dataInserimento;
	private LocalDate dataUltimoContatto;
	private Double fatturatoAnnuale;
	
	public Cliente() {
	}

	public Cliente(String ragioneSociale, Long partitaIva, TipoCliente tipoCliente, String email, String pec,
			String telefono, String nomeContatto, String cognomeContatto, String telefonoContatto, String emailContatto,
			Indirizzo indirizzoSedeOperativa, Indirizzo indirizzoSedeLegale, LocalDate dataInserimento,
			LocalDate dataUltimoContatto, Double fatturatoAnnuale) {
		this.ragioneSociale = ragioneSociale;
		this.partitaIva = partitaIva;
		this.tipoCliente = tipoCliente;
		this.email = email;
		this.pec = pec;
		this.telefono = telefono;
		this.nomeContatto = nomeContatto;
		this.cognomeContatto = cognomeContatto;
		this.telefonoContatto = telefonoContatto;
		this.emailContatto = emailContatto;
		this.indirizzoSedeOperativa = indirizzoSedeOperativa;
		this.indirizzoSedeLegale = indirizzoSedeLegale;
		this.dataInserimento = dataInserimento;
		this.dataUltimoContatto = dataUltimoContatto;
		this.fatturatoAnnuale = fatturatoAnnuale;
	}

	public Long getId() {
		return id;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public Long getPartitaIva() {
		return partitaIva;
	}

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public String getEmail() {
		return email;
	}

	public String getPec() {
		return pec;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getNomeContatto() {
		return nomeContatto;
	}

	public String getCognomeContatto() {
		return cognomeContatto;
	}

	public String getTelefonoContatto() {
		return telefonoContatto;
	}

	public String getEmailContatto() {
		return emailContatto;
	}

	public Indirizzo getIndirizzoSedeOperativa() {
		return indirizzoSedeOperativa;
	}

	public Indirizzo getIndirizzoSedeLegale() {
		return indirizzoSedeLegale;
	}

	public LocalDate getDataInserimento() {
		return dataInserimento;
	}

	public LocalDate getDataUltimoContatto() {
		return dataUltimoContatto;
	}

	public Double getFatturatoAnnuale() {
		return fatturatoAnnuale;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public void setPartitaIva(Long partitaIva) {
		this.partitaIva = partitaIva;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPec(String pec) {
		this.pec = pec;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setNomeContatto(String nomeContatto) {
		this.nomeContatto = nomeContatto;
	}

	public void setCognomeContatto(String cognomeContatto) {
		this.cognomeContatto = cognomeContatto;
	}

	public void setTelefonoContatto(String telefonoContatto) {
		this.telefonoContatto = telefonoContatto;
	}

	public void setEmailContatto(String emailContatto) {
		this.emailContatto = emailContatto;
	}

	public void setIndirizzoSedeOperativa(Indirizzo indirizzoSedeOperativa) {
		this.indirizzoSedeOperativa = indirizzoSedeOperativa;
	}

	public void setIndirizzoSedeLegale(Indirizzo indirizzoSedeLegale) {
		this.indirizzoSedeLegale = indirizzoSedeLegale;
	}

	public void setDataInserimento(LocalDate datInserimento) {
		this.dataInserimento = datInserimento;
	}

	public void setDataUltimoContatto(LocalDate dataUltimoContatto) {
		this.dataUltimoContatto = dataUltimoContatto;
	}

	public void setFatturatoAnnuale(Double fatturatoAnnuale) {
		this.fatturatoAnnuale = fatturatoAnnuale;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", ragioneSociale=" + ragioneSociale + ", partitaIva=" + partitaIva
				+ ", tipoCliente=" + tipoCliente + ", email=" + email + ", pec=" + pec + ", telefono=" + telefono
				+ ", nomeContatto=" + nomeContatto + ", cognomeContatto=" + cognomeContatto + ", telefonoContatto="
				+ telefonoContatto + ", emailContatto=" + emailContatto + ", indirizzoSedeOperativa="
				+ indirizzoSedeOperativa + ", indirizzoSedeLegale=" + indirizzoSedeLegale + ", dataInserimento="
				+ dataInserimento + ", dataUltimoContatto=" + dataUltimoContatto + ", fatturatoAnnuale="
				+ fatturatoAnnuale + "]";
	}
	
	
}
