package com.ahlquist.commio.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.ahlquist.commio.model.User;

@Repository
public
interface UserRepository extends ReactiveCrudRepository<User, Long> {
}
