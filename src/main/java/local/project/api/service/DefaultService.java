package local.project.api.service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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

	public T update(T entity, Long id) {
		Class clazz = entity.getClass();
		Optional<T> origin = repository.findById(id);

		if (!origin.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}

		T originEntity = origin.get();

		for (final Field field : clazz.getDeclaredFields()) {
			try {
				String getterString = String.format("get%s", StringUtils.capitalize(field.getName()));
				Method getter = clazz.getMethod(getterString);
				Object value = getter.invoke(entity);

				if (value != null) {
					String setterString = String.format("set%s", StringUtils.capitalize(field.getName()));
					Method setter = clazz.getMethod(setterString, field.getType());
					setter.invoke(originEntity, value);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return repository.save(originEntity);
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