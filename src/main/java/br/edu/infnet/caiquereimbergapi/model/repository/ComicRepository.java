package br.edu.infnet.caiquereimbergapi.model.repository;

import br.edu.infnet.caiquereimbergapi.model.domain.Comic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Integer> {
    List<Comic> findByUniverseAndPriceGreaterThanOrderByPriceAsc(String universe, Double price);
}
