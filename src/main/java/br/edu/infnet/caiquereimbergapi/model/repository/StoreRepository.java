package br.edu.infnet.caiquereimbergapi.model.repository;

import br.edu.infnet.caiquereimbergapi.model.domain.Book;
import br.edu.infnet.caiquereimbergapi.model.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
    Optional<Store> findByCnpj(String cnpj);
}
