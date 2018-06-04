package com.texis.Spring.repository;

import com.texis.Spring.domain.Sale;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SalesRepository extends ReactiveMongoRepository<Sale, String> {

    Flux<Sale> findAllByClientId(String clientId);

    Flux<Sale> findAllByProductId(String productId);
}
