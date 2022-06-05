package com.ahlquist.commio.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahlquist.commio.dto.AuthorDto;
import com.ahlquist.commio.dto.BookDto;
import com.ahlquist.commio.model.Author;
import com.ahlquist.commio.model.Book;
import com.ahlquist.commio.repository.BookRepository;

@RestController
@RequestMapping("books")
public class HomeController {
	@Autowired
	BookRepository bookRepository;

	@GetMapping
	public List<BookDto> findAll() {

		return bookMapper(bookRepository.findAll());

	}

	@PostMapping
	public Boolean save(@RequestBody Book book) {
		try {
			bookRepository.save(book);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	private List<BookDto> bookMapper(List<Book> book) {
		List<BookDto> bookDto = new ArrayList<>();

		for (Book bk : book) {
			BookDto dto = new BookDto();
			dto.setId(bk.getId());
			dto.setIsbn(bk.getIsbn());
			dto.setPublisher(bk.getPublisher());
			dto.setTitle(bk.getTitle());
			Set<AuthorDto> dtoAuthor = new HashSet<>();
			for (Author auth : bk.getAuthors()) {
				AuthorDto author = new AuthorDto(auth.getId(), auth.getFirstName(), auth.getLastName());
				dtoAuthor.add(author);
			}
			dto.setAuthors(dtoAuthor);
			bookDto.add(dto);
		}
		return bookDto;
	}
}