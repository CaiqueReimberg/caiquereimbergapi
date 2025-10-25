package br.edu.infnet.caiquereimbergapi.model.domain.service;

import br.edu.infnet.caiquereimbergapi.exceptions.NotFoundBookException;
import br.edu.infnet.caiquereimbergapi.interfaces.CrudService;
import br.edu.infnet.caiquereimbergapi.model.domain.Comic;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ComicService implements CrudService<Comic, Integer> {

    private final Map<Integer, Comic> comics = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    @Override
    public Comic create(Comic comic) {
        if (comic == null) {
            throw new IllegalArgumentException("Comic cannot be null");
        }
        comic.setId(nextId.getAndIncrement());

        comics.put(comic.getId(), comic);
        return comic;
    }

    // TODO olhar com calma esse validate
    public void validate(Comic comic) {
        if (comic.getId() != null && comic.getId() > 0) {
            throw new IllegalArgumentException("ID cannot already exist");
        }
    }

    @Override
    public Comic update(Integer id, Comic comic) {
        validate(comic);
        comic.setId(id);
        comics.put(comic.getId(), comic);

        return comic;
    }

    @Override
    public void delete(Integer id) {
        findById(id);

        comics.remove(id);
    }

    @Override
    public List<Comic> getAll() {
        return new ArrayList<>(comics.values());
    }

    @Override
    public Comic findById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        Comic comic = comics.get(id);

        if (comic == null) {
            throw new NotFoundBookException("Comic not found");
        }
        
        return comic;
    }

    @Override
    public Comic disable(Integer id) {
        Comic comic = findById(id);

        comic.setActive(false);

        return comic;
    }
}
