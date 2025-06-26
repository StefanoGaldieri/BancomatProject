package it.project.controller;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.project.dto.BonificoRequest;
import it.project.entity.Cliente;
import it.project.entity.ContoBancario;
import it.project.service.BancomatService;

@RestController
@RequestMapping("/api/bancomat")
public class BancomatController {

	 private final BancomatService bancomatService;

	    public BancomatController(BancomatService bancomatService) {
	        this.bancomatService = bancomatService;
	    }

	    // Endpoint per creare un nuovo cliente
	    @PostMapping("/clienti")
	    public ResponseEntity<Cliente> creaCliente(@RequestBody Cliente cliente) {
	        Cliente nuovoCliente = bancomatService.creaCliente(cliente);
	        return new ResponseEntity<>(nuovoCliente, HttpStatus.CREATED);
	    }

	    // Endpoint per creare un nuovo conto (necessita di ID cliente e numero conto)
	    // Potresti anche passare un JSON con clienteId e un numeroConto, o generarlo lato server
	    @PostMapping("/conti")
	    public ResponseEntity<?> creaConto(@RequestBody Map<String, Object> payload) {
	        try {
	            int clienteId = (Integer) payload.get("clienteId");
	            String numeroConto = (String) payload.get("numeroConto"); // O genera qui
	            ContoBancario nuovoConto = bancomatService.creaConto(clienteId, numeroConto);
	            return new ResponseEntity<>(nuovoConto, HttpStatus.CREATED);
	        } catch (IllegalArgumentException e) {
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Errore interno del server", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    // Endpoint per ottenere i dettagli di un conto (con cliente)
	    @GetMapping("/conti/{numeroConto}")
	    public ResponseEntity<ContoBancario> getDettagliConto(@PathVariable String numeroConto) {
	        Optional<ContoBancario> conto = bancomatService.getContoConCliente(numeroConto);
	        return conto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
	                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }

	    @PutMapping("/conti/{numeroConto}/deposito")
	    public ResponseEntity<Map<String, String>> deposita(@PathVariable String numeroConto, @RequestBody Map<String, Double> request) {
	        Double importo = request.get("importo");
	        if (importo == null) {
	            return new ResponseEntity<>(Collections.singletonMap("message", "Importo mancante"), HttpStatus.BAD_REQUEST);
	        }
	        try {
	            if (bancomatService.deposita(numeroConto, importo)) {
	                // Restituisci un oggetto JSON di successo
	                return new ResponseEntity<>(Collections.singletonMap("message", "Deposito effettuato con successo."), HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>(Collections.singletonMap("message", "Operazione di deposito fallita."), HttpStatus.BAD_REQUEST);
	            }
	        } catch (IllegalArgumentException e) {
	            return new ResponseEntity<>(Collections.singletonMap("message", e.getMessage()), HttpStatus.BAD_REQUEST);
	        }
	    }

	    // Endpoint per il prelievo
	    @PutMapping("/conti/{numeroConto}/prelievo")
	    public ResponseEntity<Map<String, String>> preleva(@PathVariable String numeroConto, @RequestBody Map<String, Double> request) {
	        Double importo = request.get("importo");
	        if (importo == null) {
	            return new ResponseEntity<>(Collections.singletonMap("message", "Importo mancante"), HttpStatus.BAD_REQUEST);
	        }
	        try {
	            if (bancomatService.preleva(numeroConto, importo)) {
	                // Restituisci un oggetto JSON di successo
	                return new ResponseEntity<>(Collections.singletonMap("message", "Prelievo effettuato con successo."), HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>(Collections.singletonMap("message", "Operazione di prelievo fallita."), HttpStatus.BAD_REQUEST);
	            }
	        } catch (IllegalArgumentException e) {
	            return new ResponseEntity<>(Collections.singletonMap("message", e.getMessage()), HttpStatus.BAD_REQUEST);
	        }
	    }

	    // Endpoint per il bonifico
	    @PutMapping("/bonifico")
	    public ResponseEntity<Map<String, String>> bonifico(@RequestBody BonificoRequest request) {
	        try {
	            if (bancomatService.bonificoBancario(request.getMittente(), request.getDestinatario(), request.getImporto())) {
	                // Restituisci un oggetto JSON di successo
	                return new ResponseEntity<>(Collections.singletonMap("message", "Bonifico effettuato con successo."), HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>(Collections.singletonMap("message", "Operazione di bonifico fallita."), HttpStatus.BAD_REQUEST);
	            }
	        } catch (IllegalArgumentException e) {
	            return new ResponseEntity<>(Collections.singletonMap("message", e.getMessage()), HttpStatus.BAD_REQUEST);
	        }
	    }
}
