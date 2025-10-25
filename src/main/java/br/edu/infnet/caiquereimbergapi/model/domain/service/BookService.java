package br.edu.infnet.caiquereimbergapi.model.domain.service;

import br.edu.infnet.caiquereimbergapi.exceptions.NotFoundBookException;
import br.edu.infnet.caiquereimbergapi.interfaces.CrudService;
import br.edu.infnet.caiquereimbergapi.model.domain.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class BookService implements CrudService<Book, Integer> {

    private final Map<Integer, Book> books = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    @Override
    public Book create(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        book.setId(nextId.getAndIncrement());

        books.put(book.getId(), book);
        return book;
    }

    // TODO olhar com calma esse validate
    public void validate(Book book) {
        if (book.getId() != null && book.getId() > 0) {
            throw new IllegalArgumentException("ID cannot already exist");
        }
    }

    @Override
    public Book update(Integer id, Book book) {
        validate(book);
        book.setId(id);
        books.put(book.getId(), book);

        return book;
    }

    @Override
    public void delete(Integer id) {
        findById(id);

        books.remove(id);
    }

    @Override
    public List<Book> getAll() {
        return new ArrayList<>(books.values());
    }

    @Override
    public Book findById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        Book book = books.get(id);

        if (book == null) {
            throw new NotFoundBookException("Book not found");
        }
        
        return book;
    }

    @Override
    public Book disable(Integer id) {
        Book book = findById(id);

        book.setActive(false);

        return book;
    }
}
