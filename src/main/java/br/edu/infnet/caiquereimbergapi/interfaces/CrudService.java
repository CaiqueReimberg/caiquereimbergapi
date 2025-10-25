package br.edu.infnet.caiquereimbergapi.interfaces;

import java.util.List;

public interface CrudService<T, ID> {
    public T create(T entity);
    public T update(Integer id, T entity);
    public void delete(Integer id);
    public List<T> getAll();
    public T findById(Integer id);
    public T disable(Integer id);
}
