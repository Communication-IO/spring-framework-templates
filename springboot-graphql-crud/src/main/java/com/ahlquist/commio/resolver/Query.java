package com.ahlquist.commio.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ahlquist.commio.entity.Author;
import com.ahlquist.commio.entity.Book;
import com.ahlquist.commio.repository.AuthorRepository;
import com.ahlquist.commio.repository.BookRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class Query implements GraphQLQueryResolver {

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private BookRepository bookRepository;

	public Iterable<Book> allBooks() {
		return bookRepository.findAll();
	}

	public Book book(Long id) {
		return bookRepository.findById(id).orElseGet(null);
	}

	public Iterable<Author> allAuthors() {
		return authorRepository.findAll();
	}

	public Author author(Long id) {
		return authorRepository.findById(id).orElseGet(null);
	}
}
