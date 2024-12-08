package com.inglab.user_auth_system.repository;

import java.util.Optional;

import com.inglab.user_auth_system.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}
