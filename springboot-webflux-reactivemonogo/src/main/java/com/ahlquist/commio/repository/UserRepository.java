package com.ahlquist.commio.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.ahlquist.commio.domain.User;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    
}
