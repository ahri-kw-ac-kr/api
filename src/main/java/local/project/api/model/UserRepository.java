package local.project.api.model;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	Page<UserEntity> findAllByIsDelFalse(Pageable pageable);
	UserEntity findByUsername(String username);
	static UserEntity getById(String loginid) {
		// TODO Auto-generated method stub
		return null;
	}
}