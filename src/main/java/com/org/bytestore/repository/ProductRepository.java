package com.org.bytestore.repository;


import com.org.bytestore.model.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product,String> {

    @Query("{ 'orgTokenId' : ?0 }")
    Optional<List<Product>> findByOrgTokenId(String orgTokenId);
}
