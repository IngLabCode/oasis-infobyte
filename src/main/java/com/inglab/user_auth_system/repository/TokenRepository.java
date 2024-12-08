package com.inglab.user_auth_system.repository;

import com.inglab.user_auth_system.token.Token;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends MongoRepository<Token, String> {

    @Query("{ 'user.id': ?0, $or: [ { 'expired': false }, { 'revoked': false } ] }")
    List<Token> findAllValidTokenByUser(String userId);

    Optional<Token> findByToken(String token);
}
