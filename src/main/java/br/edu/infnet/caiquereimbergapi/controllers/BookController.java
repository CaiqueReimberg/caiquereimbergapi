package br.edu.infnet.caiquereimbergapi.controllers;

import br.edu.infnet.caiquereimbergapi.model.domain.Book;
import br.edu.infnet.caiquereimbergapi.model.domain.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Book add(@RequestBody Book book) {
        Book includedBook = bookService.create(book);

        return includedBook;
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getAll();
    }
}
