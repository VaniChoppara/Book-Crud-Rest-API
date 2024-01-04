package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.binding.Book;
import com.app.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Override
	public Optional<Book> getBook(Integer id) {
		return bookRepository.findById(id);
	}
	@Override
	public List<Book> getBooks() {
		return bookRepository.findAll();

	}
	@Override
	public Book upsertBook(Book book) {		
		return bookRepository.save(book);
	}
	@Override
	public String deleteBook(Integer id) {
		// TODO Auto-generated method stub
		if(bookRepository.existsById(id)) {
			bookRepository.deleteById(id);
			return "Book Deleted";
		}else
			return "Book not Found";
		
			
	}
	


}
