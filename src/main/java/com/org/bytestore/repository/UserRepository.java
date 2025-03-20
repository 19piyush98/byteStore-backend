package com.org.bytestore.repository;

import com.org.bytestore.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    @Query("{ 'email' : ?0 }")
    Optional<User> findByEmail(String email);

    @Query("{ 'orgTokenId' : ?0 }")
    Optional<List<User>> findAllByOrgTokenId(String orgTokenId);
}
