package br.edu.infnet.caiquereimbergapi.controllers;

import br.edu.infnet.caiquereimbergapi.model.domain.Book;
import br.edu.infnet.caiquereimbergapi.model.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Book> add(@Valid @RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Integer id, @RequestBody Book book) {
        return ResponseEntity.ok(bookService.update(id, book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        bookService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/disable")
    public Book disable(@PathVariable Integer id) {
        return bookService.disable(id);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = bookService.getAll();

        if (books.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(books);
    }

    @GetMapping("/byPrice/{price}")
    public List<Book> getBookByPrice(@PathVariable Double price) {
        return bookService.findBooksByPrice(price);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Integer id) {
        return bookService.findById(id);
    }


}
