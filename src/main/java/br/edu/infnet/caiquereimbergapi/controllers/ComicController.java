package br.edu.infnet.caiquereimbergapi.controllers;

import br.edu.infnet.caiquereimbergapi.model.domain.Comic;
import br.edu.infnet.caiquereimbergapi.model.domain.service.ComicService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comics")
public class ComicController {
    private final ComicService comicService;

    public ComicController(ComicService comicService) {
        this.comicService = comicService;
    }

    @PostMapping
    public Comic add(@RequestBody Comic comic) {
        return comicService.create(comic);
    }

    @PutMapping("/{id}")
    public Comic update(@PathVariable Integer id, @RequestBody Comic comic) {
        return comicService.update(id, comic);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        comicService.delete(id);
    }

    @PatchMapping("/{id}/disable")
    public Comic disable(@PathVariable Integer id) {
        return comicService.disable(id);
    }

    @GetMapping
    public List<Comic> getComics() {
        return comicService.getAll();
    }

    @GetMapping("/{id}")
    public Comic getComic(@PathVariable Integer id) {
        return comicService.findById(id);
    }
}
