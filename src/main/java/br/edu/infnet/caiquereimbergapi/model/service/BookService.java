package br.edu.infnet.caiquereimbergapi.model.service;

import br.edu.infnet.caiquereimbergapi.exceptions.NotFoundBookException;
import br.edu.infnet.caiquereimbergapi.interfaces.CrudService;
import br.edu.infnet.caiquereimbergapi.model.domain.Book;
import br.edu.infnet.caiquereimbergapi.model.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements CrudService<Book, Integer> {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findBooksByPrice(Double price) {
        return bookRepository.findByPriceGreaterThanOrderByPriceAsc(price);
    }

    public List<Book> findBooksByActive() {
        return bookRepository.findAllByActive(true);
    }

    @Override
    public Book create(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }

        return bookRepository.save(book);
    }

    public void validate(Book book) {
        if (book.getId() != null && book.getId() > 0) {
            throw new IllegalArgumentException("ID cannot already exist");
        }
    }

    @Override
    public Book update(Integer id, Book book) {
        validate(book);
        book.setId(id);

        return bookRepository.save(book);
    }

    @Override
    public void delete(Integer id) {
        Book book = findById(id);

        bookRepository.delete(book);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        return bookRepository.findById(id).orElseThrow(() -> new NotFoundBookException("Book with id [" + id + "] not found"));
    }

    @Override
    public Book disable(Integer id) {
        Book book = findById(id);

        book.setActive(false);

        return bookRepository.save(book);
    }
}
