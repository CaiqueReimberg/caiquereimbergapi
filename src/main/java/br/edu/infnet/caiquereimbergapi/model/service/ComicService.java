package br.edu.infnet.caiquereimbergapi.model.service;

import br.edu.infnet.caiquereimbergapi.exceptions.NotFoundComicException;
import br.edu.infnet.caiquereimbergapi.interfaces.CrudService;
import br.edu.infnet.caiquereimbergapi.model.domain.Book;
import br.edu.infnet.caiquereimbergapi.model.domain.Comic;
import br.edu.infnet.caiquereimbergapi.model.repository.ComicRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComicService implements CrudService<Comic, Integer> {
    private final ComicRepository comicRepository;

    public ComicService(ComicRepository comicRepository) {
        this.comicRepository = comicRepository;
    }

    public List<Comic> findComicsByUniverseAndByPrice(String universe, Double price) {
        return comicRepository.findByUniverseAndPriceGreaterThanOrderByPriceAsc(universe, price);
    }

    @Override
    public Comic create(Comic comic) {
        if (comic == null) {
            throw new IllegalArgumentException("Comic cannot be null");
        }

        return comicRepository.save(comic);
    }

    public void validate(Comic comic) {
        if (comic.getId() != null && comic.getId() > 0) {
            throw new IllegalArgumentException("ID cannot already exist");
        }
    }

    @Override
    public Comic update(Integer id, Comic comic) {
        validate(comic);
        comic.setId(id);

        return comicRepository.save(comic);
    }

    @Override
    public void delete(Integer id) {
        Comic comic = findById(id);

        comicRepository.delete(comic);
    }

    @Override
    public List<Comic> getAll() {
        return comicRepository.findAll();
    }

    @Override
    public Comic findById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        return comicRepository.findById(id).orElseThrow(() -> new NotFoundComicException("Comic with id [" + id + "] not found"));
    }

    @Override
    public Comic disable(Integer id) {
        Comic comic = findById(id);

        comic.setActive(false);

        return comicRepository.save(comic);
    }
}
