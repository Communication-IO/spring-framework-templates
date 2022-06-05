package com.ahlquist.commio.jpacrud.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ahlquist.commio.jpacrud.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

} 