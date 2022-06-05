package com.ahlquist.commio;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ahlquist.commio.model.Author;
import com.ahlquist.commio.model.Book;
import com.ahlquist.commio.repository.BookRepository;

@SpringBootApplication
public class ManyToManyTemplateApplication implements CommandLineRunner {
	@Autowired
	BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(ManyToManyTemplateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Book book = new Book();
		Set<Author> authorList = new HashSet<>();
		Author author1 = new Author();
		author1.setFirstName("Douglas");
		author1.setLastName("Ahlquist");
		authorList.add(author1);
		book.setAuthors(authorList);
		book.setIsbn("23423dfd");
		book.setPublisher("SolidRock");
		book.setTitle("Java");
		bookRepository.save(book);

	}
}
