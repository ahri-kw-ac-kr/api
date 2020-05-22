package local.project.api.service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import local.project.api.model.DefaultEntity;
import local.project.api.model.UserEntity;

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

	// left = right, right의 field중 null이 아닌 값을 left에 덮어씌움
	protected T merge(T left, T right) {
		Class clazz = left.getClass();

		for (final Field field : clazz.getDeclaredFields()) {
			try {
				String getterString = String.format("get%s", StringUtils.capitalize(field.getName()));
				Method getter = clazz.getMethod(getterString);
				Object value = getter.invoke(right);

				if (value != null) {
					String setterString = String.format("set%s", StringUtils.capitalize(field.getName()));
					Method setter = clazz.getMethod(setterString, field.getType());
					setter.invoke(left, value);
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

		return left;
	}

	public T update(T entity, Long id) {
		Optional<T> origin = repository.findById(id);
		if (!origin.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}
		T originEntity = origin.get();

		this.merge(originEntity, entity);
		return repository.save(originEntity);
	}

	public Iterable<T> dumpUpdate(Iterable<T> entity) {
		List<T> origins = new LinkedList<T>();
		for (final T element : entity) {
			Optional<T> origin = repository.findById(element.getId());
			if (!origin.isPresent()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
			}
			T originEntity = origin.get();
			this.merge(originEntity, element);
			origins.add(originEntity);
		}
		return repository.saveAll(origins);
	}

	public Boolean delete(Long id) {
		Optional<T> optionalEntity = repository.findById(id);
		if (!optionalEntity.isPresent()) { return false; }
		T entity = optionalEntity.get();
		entity.setDel(true);
		if(entity instanceof UserEntity) {
			/*Random rand = new Random();
			for(int i=0; i<10; i++) {
				String randomStr = String.valueOf((char) ((int) (rand.nextInt(26)) + 97));
				str = str+randomStr;
			}*/
			String str = UUID.randomUUID().toString();
			((UserEntity) entity).setUsername(((UserEntity) entity).getUsername()+str);
		}
		repository.save(entity);
		//repository.delete(entity);
		return true;
	}
}