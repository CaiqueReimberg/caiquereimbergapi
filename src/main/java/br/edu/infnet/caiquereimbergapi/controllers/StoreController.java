package br.edu.infnet.caiquereimbergapi.controllers;

import br.edu.infnet.caiquereimbergapi.model.domain.Store;
import br.edu.infnet.caiquereimbergapi.model.service.StoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping
    public Store add(@RequestBody Store store) {
        return storeService.create(store);
    }

    @PutMapping("/{id}")
    public Store update(@PathVariable Integer id, @RequestBody Store store) {
        return storeService.update(id, store);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        storeService.delete(id);
    }

    @PatchMapping("/{id}/disable")
    public Store disable(@PathVariable Integer id) {
        return storeService.disable(id);
    }

    @GetMapping
    public List<Store> getStores() {
        return storeService.getAll();
    }

    @GetMapping("/{id}")
    public Store getStore(@PathVariable Integer id) {
        return storeService.findById(id);
    }
}
