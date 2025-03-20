package com.org.bytestore.repository;

import com.org.bytestore.model.entity.Audit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuditRepository extends MongoRepository<Audit, String> {

    @Query("{ 'orgTokenId' : ?0 }")
    Optional<List<Audit>> findByOrgTokenId(String orgTokenId);
}
