package local.project.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import local.project.api.model.DefaultEntity;

public class DefaultService<T extends DefaultEntity> {
	
	@Autowired
	private CrudRepository<T, Integer> repository;
	
	public Iterable<T> list() {
		return repository.findAll();
	}
	
	public Optional<T> get(Integer id) {
		return repository.findById(id);
	}
    
    public T insert(T entity) {
		return repository.save(entity);
	}

	public T update(T entity) {
		return repository.save(entity);
	}

	public T delete(T entity) {
		entity.setDel(true);
		return repository.save(entity);
	}
}