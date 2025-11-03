package br.edu.infnet.caiquereimbergapi;

import br.edu.infnet.caiquereimbergapi.exceptions.NotFoundComicException;
import br.edu.infnet.caiquereimbergapi.exceptions.NotFoundStoreException;
import br.edu.infnet.caiquereimbergapi.model.domain.Comic;
import br.edu.infnet.caiquereimbergapi.model.domain.Store;
import br.edu.infnet.caiquereimbergapi.model.service.ComicService;
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

@Order(2)
@Component
public class ComicLoader implements ApplicationRunner {
    private final ComicService comicService;
    private final StoreService storeService;

    public ComicLoader(ComicService comicService, StoreService storeService) {
        this.comicService = comicService;
        this.storeService = storeService;
    }

    private final Map<Integer, Comic> comics = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Running Comic Loader");

        FileReader file = new FileReader("comics.csv");
        BufferedReader bufferedReader = new BufferedReader(file);
        String line = bufferedReader.readLine();
        String[] fields = null;

        while (line != null) {
            fields = line.split(";");
            String storeCnpj = fields[9];
            try {
                Store store = storeService.findByCnpj(storeCnpj);
                Comic comic = new Comic();
                comic.setTitle(fields[0]);
                comic.setAuthor(fields[1]);
                comic.setPublisher(fields[2]);
                comic.setCategory(fields[3]);
                comic.setPrice(new BigDecimal(fields[4]));
                comic.setIllustrator(fields[6]);
                comic.setIssueNumber(Integer.parseInt(fields[7]));
                comic.setUniverse(fields[8]);
                comic.setStore(store);

                comicService.create(comic);
            } catch (NotFoundComicException e) {
                System.err.println("[ERROR] " + e.getMessage());
            }

            line = bufferedReader.readLine();
        }

        comicService.getAll().forEach(System.out::println);

        bufferedReader.close();
    }
}
