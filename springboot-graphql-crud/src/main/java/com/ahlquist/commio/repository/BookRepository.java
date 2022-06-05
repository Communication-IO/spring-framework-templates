package com.ahlquist.commio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahlquist.commio.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
