package com.ahlquist.commio.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ahlquist.commio.models.ERole;
import com.ahlquist.commio.models.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
	Optional<Role> findByName(ERole name);
}
