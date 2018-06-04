package com.texis.Spring.controller;

import com.texis.Spring.domain.Product;
import com.texis.Spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product")
    public Flux<Product> getAllProducts() {
        return productRepository.findAllByQuantityGreaterThan(0);
    }

    @GetMapping("/product/{id}")
    public Mono<ResponseEntity<Product>> getProductById(@PathVariable String id) {
        return productRepository.findById(id).map(ResponseEntity::ok).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/product")
    public Mono<Product> createProduct(@Valid @RequestBody Product product){
        return productRepository.save(product);
    }

    @PutMapping("/product/{id}")
    public Mono<ResponseEntity<Product>> updateClient(@PathVariable(value = "id") String id, @Valid @RequestBody Product product) {
        return productRepository.findById(id).flatMap(existingProduct -> {
            existingProduct.setName(product.getName());
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setValue(product.getValue());
            existingProduct.setSize(product.getSize());
            return productRepository.save(existingProduct);
        }).map(updatedProduct -> new ResponseEntity<>(updatedProduct, HttpStatus.OK))
            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/product/{id}")
    public Mono<ResponseEntity<Void>> deleteClient(@PathVariable(value = "id") String id){
        return productRepository.findById(id).flatMap(existingClient ->
            productRepository.delete(existingClient).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
        ).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
