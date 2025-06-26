package it.project.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.project.entity.Cliente;
import it.project.entity.ContoBancario;
import it.project.repository.ClienteRepository;
import it.project.repository.ContoBancarioRepository;

@Service
public class BancomatService {

	 private final ClienteRepository clienteRepository;
	    private final ContoBancarioRepository contoBancarioRepository;

	    // Spring inietta automaticamente i repository grazie a @Autowired implicito nel costruttore
	    public BancomatService(ClienteRepository clienteRepository, ContoBancarioRepository contoBancarioRepository) {
	        this.clienteRepository = clienteRepository;
	        this.contoBancarioRepository = contoBancarioRepository;
	    }

	    @Transactional // Ogni operazione qui è una transazione
	    public Cliente creaCliente(Cliente cliente) {
	        // Puoi aggiungere logica qui, es. generare nomeCompleto se non già fatto
	        cliente.setNomeCompleto(cliente.getNome() + " " + cliente.getCognome());
	        cliente.setAccountOpenDate(LocalDate.now());
	        cliente.setLastAccessDate(LocalDate.now());
	        return clienteRepository.save(cliente); // save() restituisce l'entità con l'ID generato
	    }

	    @Transactional
	    public ContoBancario creaConto(int clienteId, String numeroConto) {
	        // Qui dovresti PRIMA verificare l'esistenza del numeroConto, come hai fatto tu
	        if (contoBancarioRepository.existsByNumeroConto(numeroConto)) {
	            throw new IllegalArgumentException("Numero conto già esistente: " + numeroConto);
	        }
	        ContoBancario nuovoConto = new ContoBancario(clienteId, numeroConto);
	        nuovoConto.setLastOperationDate(LocalDateTime.now());
	        return contoBancarioRepository.save(nuovoConto);
	    }

	    public Optional<ContoBancario> getContoByNumero(String numeroConto) {
	        return contoBancarioRepository.findByNumeroConto(numeroConto);
	    }

	    // Metodo per recuperare conto e cliente (simile a recoveryInformationToDB)
	    public Optional<ContoBancario> getContoConCliente(String numeroConto) {
	        Optional<ContoBancario> contoOpt = contoBancarioRepository.findByNumeroConto(numeroConto);
	        if (contoOpt.isPresent()) {
	            ContoBancario conto = contoOpt.get();
	            clienteRepository.findById(conto.getClienteId()).ifPresent(conto::setCliente); // Popola l'oggetto Cliente
	        }
	        return contoOpt;
	    }

	    @Transactional // Metodo transazionale
	    public boolean deposita(String numeroConto, double importo) {
	        if (importo <= 0) {
	            throw new IllegalArgumentException("L'importo del deposito deve essere positivo.");
	        }
	        return getContoByNumero(numeroConto).map(conto -> {
	            conto.setSaldo(conto.getSaldo() + importo);
	            conto.setLastOperationDate(LocalDateTime.now());
	            contoBancarioRepository.save(conto); // Spring Data JDBC farà l'UPDATE
	            return true;
	        }).orElseThrow(() -> new IllegalArgumentException("Conto non trovato: " + numeroConto));
	    }

	    @Transactional // Metodo transazionale
	    public boolean preleva(String numeroConto, double importo) {
	        if (importo <= 0) {
	            throw new IllegalArgumentException("L'importo del prelievo deve essere positivo.");
	        }
	        return getContoByNumero(numeroConto).map(conto -> {
	            if (conto.getSaldo() < importo) {
	                throw new IllegalArgumentException("Saldo insufficiente sul conto: " + numeroConto);
	            }
	            conto.setSaldo(conto.getSaldo() - importo);
	            conto.setLastOperationDate(LocalDateTime.now());
	            contoBancarioRepository.save(conto);
	            return true;
	        }).orElseThrow(() -> new IllegalArgumentException("Conto non trovato: " + numeroConto));
	    }

	    @Transactional // Il bonifico DEVE essere transazionale
	    public boolean bonificoBancario(String numeroContoMittente, String numeroContoDestinatario, double importo) {
	        if (importo <= 0) {
	            throw new IllegalArgumentException("L'importo del bonifico deve essere positivo.");
	        }
	        // Il prelievo e il deposito lanceranno eccezioni se i conti non esistono o saldo insufficiente
	        // Spring @Transactional garantirà il rollback se una delle due fallisce
	        preleva(numeroContoMittente, importo);
	        deposita(numeroContoDestinatario, importo);
	        return true; // Se arriviamo qui, entrambe le operazioni sono riuscite
	    }

	    // Potresti voler generare il numero di conto qui o passarlo
	    public String generateNumeroConto(Cliente cliente) {
	        return "IT" + cliente.getNome().charAt(0) + cliente.getCognome().charAt(0) + Math.abs(cliente.hashCode());
	    }


}
