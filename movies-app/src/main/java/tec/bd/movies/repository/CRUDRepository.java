package tec.bd.movies.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CRUDRepository<T, ID extends Serializable> {

    List<T> findAll();

    Optional<T> findById(ID entityId);

    T save(T entity);

    void delete(ID entityId);

    T update(T entity);
}
