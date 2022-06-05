package com.ahlquist.commio.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ahlquist.commio.entity.Author;
import com.ahlquist.commio.entity.Book;
import com.ahlquist.commio.repository.AuthorRepository;
import com.ahlquist.commio.repository.BookRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

@Component
public class Mutation implements GraphQLMutationResolver {
	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private BookRepository bookRepository;

	public Author addAuthor(String name) {
		Author author = new Author();
		author.setName(name);

		return authorRepository.saveAndFlush(author);
	}

	public Author updateAuthor(Long id, String name) {
		Author author = new Author();
		author.setId(id);
		author.setName(name);

		return authorRepository.saveAndFlush(author);
	}

	public Boolean deleteAuthor(Long id) {
		authorRepository.deleteById(id);
		return true;
	}

	public Book addBook(String name, Double price, Long author_id) {
		Author author = authorRepository.findById(author_id).
		orElseGet(null);
		Book book = new Book();
		book.setName(name);
		book.setPrice(price);
		book.setAuthor(author);
		return bookRepository.saveAndFlush(book);
	}

	public Book updateBook(Long id, String name, Double price) {
		Book book = new Book();
		book.setId(id);
		book.setName(name);
		book.setPrice(price);
		return bookRepository.saveAndFlush(book);
	}

	public Boolean deleteBook(Long id) {
		bookRepository.deleteById(id);
		return true;
	}

}
