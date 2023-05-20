package tec.bd.catalog.repository;

import tec.bd.catalog.entity.Videogame;

import java.util.List;
import java.util.Optional;

public interface VideogameRepository {

    List<Videogame> findAll();

    Optional<Videogame> findById(int id);

    Videogame save(Videogame videogame);
}
