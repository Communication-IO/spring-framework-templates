package com.ahlquist.repositories;

import java.util.Optional;
import java.util.UUID;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ahlquist.models.User;

@Repository(value = "UserRepository")
public interface UserRepository extends CrudRepository<User, UUID> {

	@Query(value = "SELECT * FROM user u WHERE u.username = :username", nativeQuery = true)
	public Optional<User> findByUsername(@NonNull @Param("username") String username);

	@Query(value = "SELECT * FROM user u WHERE u.token = :token", nativeQuery = true)
	public Optional<User> findByToken(@NonNull @Param("token") String token);
	
	@Query(value = "SELECT * FROM user u WHERE u.uuid = :uuid", nativeQuery = true)
	public Optional<User> findByUuid(@NonNull @Param("uuid") UUID uuid);

	@Query(value = "SELECT * FROM user u WHERE u.uuid = :uuid AND token = :token", nativeQuery = true)
	public Optional<User> findByUuidAndToken(
			@NonNull @Param("uuid") UUID uuid, 
			@NonNull @Param("token") String token);

	@Modifying
    @Query(value = "INSERT INTO user (uuid, username, password, token) VALUES (uuid, username, password, token);", 
    	nativeQuery = true)
    public User save(
    		@NonNull @Param("uuid") UUID uuid, 
    		@NonNull @Param("username") String username, 
    		@NonNull @Param("password") String password, 
    		@Nullable @Param("token") String token);

}
