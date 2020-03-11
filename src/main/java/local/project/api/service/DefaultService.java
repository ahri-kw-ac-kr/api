package local.project.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import local.project.api.model.DefaultEntity;

public class DefaultService<T extends DefaultEntity> {
	
	@Autowired
	protected CrudRepository<T, Long> repository;
	
	public Iterable<T> getAll() {
		return repository.findAll();
	}
	
	public Optional<T> get(Long id) {
		return repository.findById(id);
	}
    
    public T insert(T entity) {
		return repository.save(entity);
	}

	public T update(T entity) {
		return repository.save(entity);
	}

	public Iterable<T> dumpUpdate(Iterable<T> entity) {
		return repository.saveAll(entity);
	}

	public Boolean delete(Long id) {
		Optional<T> optionalEntity = repository.findById(id);
		if (!optionalEntity.isPresent()) { return false; }
		T entity = optionalEntity.get();
		entity.setDel(true);
		repository.save(entity);
		return true;
	}
}