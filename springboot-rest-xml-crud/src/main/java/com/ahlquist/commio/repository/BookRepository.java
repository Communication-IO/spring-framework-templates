package com.ahlquist.commio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahlquist.commio.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
   List<Book> findByTitleContaining(String title);
}
