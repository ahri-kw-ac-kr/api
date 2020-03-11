package local.project.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public class DefaultService<T> {
	
	@Autowired
	private CrudRepository<T, Integer> repository;
	
	public Iterable<T> list() {
		return repository.findAll();
    }
    
    public T insert(T entity) {
		return repository.save(entity);
	}
}