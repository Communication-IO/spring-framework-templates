package com.ahlquist.commio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahlquist.commio.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
