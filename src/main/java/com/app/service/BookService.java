package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.binding.Book;

public interface BookService {
	Optional<Book> getBook(Integer id);
	List<Book> getBooks();
	Book upsertBook(Book book);
	String deleteBook(Integer id);
}
