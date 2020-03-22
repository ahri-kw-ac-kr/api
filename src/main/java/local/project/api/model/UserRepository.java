package local.project.api.model;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>, CrudRepository<UserEntity, Long> {
	Page<UserEntity> findAllByIsDelFalse(Pageable pageable);
	UserEntity findByUsername(String username);
	Optional<UserEntity> findById(long id);
}