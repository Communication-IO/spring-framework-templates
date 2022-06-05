package com.ahlquist.commio.datajdbccrud.repository;

import java.util.List;

import com.ahlquist.commio.datajdbccrud.model.User;

public interface UserRepository {

	public User findOne(Long id);

	public List<User> findAll();

	public void save(User user);

	public Long saveAndReturnId(User user);

	public void update(User user);

	public Boolean delete(Long id);

}