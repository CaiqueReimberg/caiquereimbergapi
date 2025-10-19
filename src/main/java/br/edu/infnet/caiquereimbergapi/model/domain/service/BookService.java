package br.edu.infnet.caiquereimbergapi.model.domain.service;

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
        book.setId(nextId.getAndIncrement());

        books.put(book.getId(), book);
        return book;
    }

    @Override
    public Book update(Integer id, Book book) {
        return null;
    }

    @Override
    public void delete(Book book) {
        books.remove(book.getId());
    }

    @Override
    public List<Book> getAll() {
        return new ArrayList<>(books.values());
    }
}
