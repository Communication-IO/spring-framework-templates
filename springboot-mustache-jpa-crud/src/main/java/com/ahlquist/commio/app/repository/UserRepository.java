package com.ahlquist.commio.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ahlquist.commio.app.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}