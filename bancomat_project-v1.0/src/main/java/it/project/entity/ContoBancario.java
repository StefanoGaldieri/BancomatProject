package it.project.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("BANK_ACCOUNT_DATA")
public class ContoBancario {

	@Id
    @Column("ID_CONTO") // Assumi sia la PK del conto
    private int id;

    @Column("ID_CLIENTE") // Chiave esterna al cliente
    private int clienteId;

    @Column("NUMERO_CONTO")
    private String numeroConto;

    @Column("SALDO")
    private double saldo;

    @Column("LAST_OPERATION_DATE")
    private LocalDateTime lastOperationDate;

    @Transient
    private Cliente cliente;

    // Costruttore per la creazione di un nuovo conto (saldo iniziale 0)
    public ContoBancario(int clienteId, String numeroConto) {
        this.clienteId = clienteId;
        this.numeroConto = numeroConto;
        this.saldo = 0.0; // Saldo iniziale
        this.lastOperationDate = LocalDateTime.now();
    }

    @SuppressWarnings("removal")
	@PersistenceConstructor // Aggiungi questa annotazione!
    public ContoBancario(int id, int clienteId, String numeroConto, double saldo, LocalDateTime lastOperationDate) {
        this.id = id;
        this.clienteId = clienteId;
        this.numeroConto = numeroConto;
        this.saldo = saldo;
        this.lastOperationDate = lastOperationDate;
    }
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the clienteId
	 */
	public int getClienteId() {
		return clienteId;
	}

	/**
	 * @return the numeroConto
	 */
	public String getNumeroConto() {
		return numeroConto;
	}

	/**
	 * @return the saldo
	 */
	public double getSaldo() {
		return saldo;
	}

	/**
	 * @return the lastOperationDate
	 */
	public LocalDateTime getLastOperationDate() {
		return lastOperationDate;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param clienteId the clienteId to set
	 */
	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	/**
	 * @param numeroConto the numeroConto to set
	 */
	public void setNumeroConto(String numeroConto) {
		this.numeroConto = numeroConto;
	}

	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	/**
	 * @param lastOperationDate the lastOperationDate to set
	 */
	public void setLastOperationDate(LocalDateTime lastOperationDate) {
		this.lastOperationDate = lastOperationDate;
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


}
