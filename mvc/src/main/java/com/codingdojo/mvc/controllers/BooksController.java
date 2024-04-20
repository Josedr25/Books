package com.codingdojo.mvc.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codingdojo.mvc.models.Book;
import com.codingdojo.mvc.services.BookService;

import jakarta.validation.Valid;

@Controller
public class BooksController {
	private final BookService bookService;
    
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }
    
    @RequestMapping("/")
    public String index(Model model) {
        List<Book> books = bookService.allBooks();
        model.addAttribute("books", books);
        return "index.jsp";
    }
    
    @RequestMapping("/books/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "booksnew.jsp";
    }
    @RequestMapping(value="/books", method=RequestMethod.POST)
    public String create(@Valid @ModelAttribute("book") Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "booksnew.jsp";
        } else {
            bookService.createBook(book);
            return "redirect:/";
        }
        
      }
      @GetMapping("/books/{id}")
        public String showBook(@PathVariable Long id, Model model) {
            Book book = bookService.findBook(id);
            model.addAttribute("book", book);
            return "booksshow.jsp";
       }
        
      @RequestMapping("/books/{id}/edit")
      public String edit(@PathVariable("id") Long id, Model model) {
          Book book = bookService.findBook(id);
          model.addAttribute("book", book);
          return "booksedit.jsp";
      }
      
      @RequestMapping(value="/books/{id}", method=RequestMethod.PUT)
      public String update(@Valid @ModelAttribute("book") Book book, BindingResult result) {
          if (result.hasErrors()) {
              return "booksedit.jsp";
          } else {
              bookService.createBook(book);
              return "redirect:/";
          }
      }
      @RequestMapping(value="/books/{id}", method=RequestMethod.DELETE)
      public String destroy(@PathVariable("id") Long id) {
          bookService.deleteBook(id);
          return "redirect:/";
      }
}
