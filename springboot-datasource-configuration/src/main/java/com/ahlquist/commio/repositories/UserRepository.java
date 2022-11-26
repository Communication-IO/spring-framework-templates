package com.ahlquist.commio.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ahlquist.commio.models.User;

@Repository("UserRepository")
public interface UserRepository extends CrudRepository<User, Long> {
}