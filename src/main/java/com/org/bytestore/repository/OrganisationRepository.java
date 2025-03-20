package com.org.bytestore.repository;

import com.org.bytestore.model.entity.Organisation;
import com.org.bytestore.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface OrganisationRepository extends MongoRepository<Organisation,String> {

}
