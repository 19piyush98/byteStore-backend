package com.org.bytestore.repository;

import com.org.bytestore.model.entity.User;
import com.org.bytestore.model.entity.UserSession;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserSessionRepository extends MongoRepository<UserSession,String> {
}
