package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.binding.Book;
import com.app.service.BookService;

@RestController
public class BookController {

	@Autowired
	BookService bookService;

	Logger logger = LoggerFactory.getLogger(BookController.class);

	// @GetMapping(value = "/book/{id}", produces =
	// MediaType.APPLICATION_JSON_VALUE, consumes = "text/palin")
	@GetMapping(value = "/book/{id}")
	public ResponseEntity<Book> getBook(@PathVariable Integer id) {
		logger.info("getBook() execution started");
		Optional<Book> book = bookService.getBook(id);
		logger.info("getBook() execution Ended");
		if (book.isPresent())
			return new ResponseEntity<>(book.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Book>> getBooks() {
		logger.info("getBooks() execution started");
		List<Book> books = bookService.getBooks();
		logger.info("getBooks() execution Ended");
		if (!books.isEmpty())
			return new ResponseEntity<>(books, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/book")
	public ResponseEntity<String> insertBook(@RequestBody Book book) {
		logger.info("insertBook() execution started");
		String body = "Book Created";
		Book upsertBook = bookService.upsertBook(book);
		logger.info("insertBook() execution started");
		if(upsertBook.getId()!=null)
		return new ResponseEntity<>(body, HttpStatus.CREATED);
			else
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping(value = "/book")
	public ResponseEntity<String> updateBook(@RequestBody Book book) {
		logger.info("updateBook() execution started");
		String body = "Book Updated";
		Book upsertBook = bookService.upsertBook(book);
		logger.info("updatedBook() execution started");
		if(upsertBook.getId()!=null)
		return new ResponseEntity<>(body, HttpStatus.OK);
			else
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping(value = "/book/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable Integer id) {
		logger.info("deleteBook() execution started");
		String body = "Book Deleted";
		bookService.deleteBook(id);
		logger.info("deleteBook() execution started");
		return new ResponseEntity<>(body, HttpStatus.OK);
	}

}
