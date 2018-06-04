package com.texis.Spring.controller;

import com.texis.Spring.domain.Client;
import com.texis.Spring.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@CrossOrigin(value = "http://localhost:3000")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/client")
    public Flux<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/client/{id}")
    public Mono<ResponseEntity<Client>> getClientById(@PathVariable(value = "id") String id) {
        return clientRepository.findById(id).map(ResponseEntity::ok).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/client")
    public Mono<Client> createClient(@Valid @RequestBody Client client) {
        return clientRepository.save(client);
    }

    @PutMapping("/client/{id}")
    public Mono<ResponseEntity<Client>> updateClient(@PathVariable(value = "id") String id, @Valid @RequestBody Client client) {
        return clientRepository.findById(id).flatMap(existingClient -> {
            existingClient.setName(client.getName());
            return clientRepository.save(existingClient);
        }).map(updatedClient -> new ResponseEntity<>(updatedClient, HttpStatus.OK))
            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/client/{id}")
    public Mono<ResponseEntity<Void>> deleteClient(@PathVariable(value = "id") String id){
        return clientRepository.findById(id).flatMap(existingClient ->
            clientRepository.delete(existingClient).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
        ).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
