package br.edu.infnet.caiquereimbergapi.interfaces;

import java.util.List;

public interface CrudService<T, ID> {
    public T create(T entity);
    public T update(Integer id, T entity);
    public void delete(T entity);
    public List<T> getAll();
}
