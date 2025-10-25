package br.edu.infnet.caiquereimbergapi;

import br.edu.infnet.caiquereimbergapi.model.domain.Comic;
import br.edu.infnet.caiquereimbergapi.model.domain.service.ComicService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ComicLoader implements ApplicationRunner {
    private final ComicService comicService;

    public ComicLoader(ComicService comicService) {
        this.comicService = comicService;
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

            // The Shining;Stephen King;Doubleday;Horror;79.90;true
            Comic comic = new Comic();
            comic.setTitle(fields[0]);
            comic.setAuthor(fields[1]);
            comic.setPublisher(fields[2]);
            comic.setCategory(fields[3]);
            comic.setPrice(new BigDecimal(fields[4]));
            comic.setIllustrator(fields[6]);
            comic.setIssueNumber(Integer.parseInt(fields[7]));
            comic.setUniverse(fields[8]);

            comicService.create(comic);

            line = bufferedReader.readLine();
        }

        comicService.getAll().forEach(System.out::println);

        bufferedReader.close();
    }
}
