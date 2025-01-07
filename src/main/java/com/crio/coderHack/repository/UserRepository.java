package com.crio.coderHack.repository;

import com.crio.coderHack.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByUserId(String UserId);

    User deleteByUserId(String userId);
}
