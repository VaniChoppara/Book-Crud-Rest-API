package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.binding.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
