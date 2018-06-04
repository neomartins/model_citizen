package com.texis.Spring.controller;

import com.texis.Spring.domain.Sale;
import com.texis.Spring.repository.ClientRepository;
import com.texis.Spring.repository.ProductRepository;
import com.texis.Spring.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class SalesController {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/sales")
    public Flux<Sale> getAllSales() {
        return salesRepository.findAll();
    }

    @GetMapping("/sales/{id}")
    public Mono<ResponseEntity<Sale>> getSaleById(@PathVariable String id) {
        return salesRepository.findById(id).map(ResponseEntity::ok).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/client/{clientId}/sales")
    public Flux<ResponseEntity<Sale>> getAllSaleByClientId(@PathVariable String clientId) {
        return salesRepository.findAllByClientId(clientId).map(ResponseEntity::ok).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/product/{productId}/sales")
    public Flux<ResponseEntity<Sale>> getAllSaleByProductId(@PathVariable String productId) {
        return salesRepository.findAllByProductId(productId).map(ResponseEntity::ok)
            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/sales")
    public Mono<Sale> createSale(@Valid @RequestBody Sale sale) {
        return Mono.zip(clientRepository.existsById(sale.getClientId()),
            productRepository.existsById(sale.getProductId()))
            .filter(validation -> validation.getT1() && validation.getT2()).flatMap(validation -> salesRepository.save(sale));
    }
}
