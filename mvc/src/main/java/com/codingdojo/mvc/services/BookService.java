package com.codingdojo.mvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.mvc.models.Book;
import com.codingdojo.mvc.repositories.BookRepository;

@Service
public class BookService {
	private final BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	 public Book updateBook(Long id, Book updatedBook) {
	        Optional<Book> optionalBook = bookRepository.findById(id);
	        
	        if (optionalBook.isPresent()) {
	            Book updateBook = optionalBook.get();
	            updateBook.setId(updatedBook.getId());
	            updateBook.setTitle(updatedBook.getTitle());
	            updateBook.setDescription(updatedBook.getDescription());
	            updateBook.setLanguage(updatedBook.getLanguage());
	            updateBook.setNumberOfPages(updatedBook.getNumberOfPages());
	            return bookRepository.save(updateBook);
	        } else {
	            throw new RuntimeException("El libro con ID " + id + " no fue encontrado");
	        }
	    }

	    public void deleteBook(Long id) {
	        bookRepository.deleteById(id);
	    }
	
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }
    public Book createBook(Book b) {
        return bookRepository.save(b);
    }
    public Book findBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }
}
