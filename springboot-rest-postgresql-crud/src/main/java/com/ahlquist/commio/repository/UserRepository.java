package com.ahlquist.commio.repository;

import org.springframework.data.repository.CrudRepository;

import com.ahlquist.commio.entity.User;

public interface UserRepository 
   extends CrudRepository<User, Long> {

}