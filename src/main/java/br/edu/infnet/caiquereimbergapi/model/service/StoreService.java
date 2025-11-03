package br.edu.infnet.caiquereimbergapi.model.service;

import br.edu.infnet.caiquereimbergapi.exceptions.NotFoundStoreException;
import br.edu.infnet.caiquereimbergapi.interfaces.CrudService;
import br.edu.infnet.caiquereimbergapi.model.domain.Store;
import br.edu.infnet.caiquereimbergapi.model.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService implements CrudService<Store, Integer> {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Store create(Store store) {
        if (store == null) {
            throw new IllegalArgumentException("Store cannot be null");
        }

        return storeRepository.save(store);
    }

    public void validate(Store store) {
        if (store.getId() != null && store.getId() > 0) {
            throw new IllegalArgumentException("ID cannot already exist");
        }
    }

    @Override
    public Store update(Integer id, Store store) {
        validate(store);
        store.setId(id);

        return storeRepository.save(store);
    }

    @Override
    public void delete(Integer id) {
        Store store = findById(id);

        storeRepository.delete(store);
    }

    @Override
    public List<Store> getAll() {
        return storeRepository.findAll();
    }

    @Override
    public Store findById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        return storeRepository.findById(id).orElseThrow(() -> new NotFoundStoreException("Store with id [" + id + "] not found"));
    }

    @Override
    public Store disable(Integer id) {
        Store store = findById(id);

        store.setActive(false);

        return storeRepository.save(store);
    }

    public Store findByCnpj(String cnpj) {
        return storeRepository.findByCnpj(cnpj).orElseThrow(() -> new NotFoundStoreException("Store with cnpj [" + cnpj + "] not found"));
    }
}
