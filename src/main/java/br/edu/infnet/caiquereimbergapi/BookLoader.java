package br.edu.infnet.caiquereimbergapi;

import br.edu.infnet.caiquereimbergapi.exceptions.NotFoundStoreException;
import br.edu.infnet.caiquereimbergapi.model.domain.Book;
import br.edu.infnet.caiquereimbergapi.model.domain.Store;
import br.edu.infnet.caiquereimbergapi.model.service.BookService;
import br.edu.infnet.caiquereimbergapi.model.service.StoreService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Order(3)
@Component
public class BookLoader implements ApplicationRunner {
    private final BookService bookService;
    private final StoreService storeService;

    public BookLoader(BookService bookService, StoreService storeService) {
        this.bookService = bookService;
        this.storeService = storeService;
    }

    private final Map<Integer, Book> books = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Running Book Loader");

        FileReader file = new FileReader("books.csv");
        BufferedReader bufferedReader = new BufferedReader(file);
        String line = bufferedReader.readLine();
        String[] fields = null;

        while (line != null) {
            fields = line.split(";");

            String storeCnpj = fields[8];
            try {
                Store store = storeService.findByCnpj(storeCnpj);

                // The Shining;Stephen King;Doubleday;Horror;79.90;true
                Book book = new Book();
                book.setTitle(fields[0]);
                book.setAuthor(fields[1]);
                book.setPublisher(fields[2]);
                book.setCategory(fields[3]);
                book.setPrice(new BigDecimal(fields[4]));
                book.setLanguage(fields[6]);
                book.setEdition(Integer.parseInt(fields[7]));
                book.setStore(store);

                bookService.create(book);

            } catch (NotFoundStoreException e) {
                System.err.println("[ERROR] " + e.getMessage());
            }

            line = bufferedReader.readLine();
        }

        bookService.getAll().forEach(System.out::println);

        bufferedReader.close();
    }
}
