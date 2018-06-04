package com.texis.Spring.repository;


import com.texis.Spring.domain.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String>{

    Flux<Product> findAllByQuantityGreaterThan(int quantity);
}
