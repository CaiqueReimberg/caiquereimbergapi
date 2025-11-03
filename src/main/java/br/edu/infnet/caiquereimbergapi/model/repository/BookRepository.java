package br.edu.infnet.caiquereimbergapi.model.repository;

import br.edu.infnet.caiquereimbergapi.model.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByActive(boolean active);
    List<Book> findByPriceGreaterThanOrderByPriceAsc(Double price);
}
