package com.ahlquist.commio.record.repsitory;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ahlquist.commio.record.entity.User;

@Repository
public interface UserRepository
        extends CrudRepository<User, Long> {
	List<User> findAll();
} 