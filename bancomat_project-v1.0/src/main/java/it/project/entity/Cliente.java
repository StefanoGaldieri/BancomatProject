package it.project.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("CUSTOMER")
public class Cliente {

	    @Id // Marca l'ID come chiave primaria
	    @Column("ID_CLIENTE") // Specifica il nome della colonna se diverso dal nome del campo
	    private int id;

	    @Column("NOME_CLIENTE")
	    private String nome;

	    @Column("COGNOME_CLIENTE")
	    private String cognome;

	    @Column("DATA_NASCITA")
	    private LocalDate dataNascita;

	    @Column("NOME_COMPLETO")
	    private String nomeCompleto; // Spring Data JDBC potrebbe non gestire bene campi calcolati, gestiscili tu

	    @Column("ACCOUNT_OPEN_DATE")
	    private LocalDate accountOpenDate;

	    @Column("LAST_ACCESS_DATE")
	    private LocalDate lastAccessDate;

	    public Cliente() {}
	    
	    // Costruttori, se necessari e non generati da Lombok
	    // Puoi avere un costruttore per la creazione di nuovi clienti senza ID
	    public Cliente(String nome, String cognome, LocalDate dataNascita) {
	        this.nome = nome;
	        this.cognome = cognome;
	        this.dataNascita = dataNascita;
	        this.nomeCompleto = nome + " " + cognome; // Popola qui o via un setter
	        this.accountOpenDate = LocalDate.now();
	        this.lastAccessDate = LocalDate.now();
	    }
	    @SuppressWarnings("removal")
		@PersistenceConstructor // Aggiungi questa annotazione!
	    public Cliente(int id, String nome, String cognome, LocalDate dataNascita, String nomeCompleto, LocalDate accountOpenDate, LocalDate lastAccessDate) {
	        this.id = id;
	        this.nome = nome;
	        this.cognome = cognome;
	        this.dataNascita = dataNascita;
	        this.nomeCompleto = nomeCompleto;
	        this.accountOpenDate = accountOpenDate;
	        this.lastAccessDate = lastAccessDate;
	    }
	 // Getter e Setter per TUTTI i campi (ID, nome, cognome, dataNascita, nomeCompleto, accountOpenDate, lastAccessDate)
	    public int getId() { return id; }
	    public void setId(int id) { this.id = id; }

	    public String getNome() { return nome; }
	    public void setNome(String nome) { this.nome = nome; }

	    public String getCognome() { return cognome; }
	    public void setCognome(String cognome) { this.cognome = cognome; }

	    public LocalDate getDataNascita() { return dataNascita; }
	    public void setDataNascita(LocalDate dataNascita) { this.dataNascita = dataNascita; }

	    public String getNomeCompleto() { return nomeCompleto; }
	    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }

	    public LocalDate getAccountOpenDate() { return accountOpenDate; }
	    public void setAccountOpenDate(LocalDate accountOpenDate) { this.accountOpenDate = accountOpenDate; }

	    public LocalDate getLastAccessDate() { return lastAccessDate; }
	    public void setLastAccessDate(LocalDate lastAccessDate) { this.lastAccessDate = lastAccessDate; }

	}
