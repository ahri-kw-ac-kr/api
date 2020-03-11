package local.project.api.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import local.project.api.model.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
	
	UserEntity findByUsername(String username);
	
}