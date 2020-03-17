package local.project.api.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/*그냥 docuemnt예제 유저 아니어도 되는건가..?*/
@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	Page<UserEntity> findAllByIsDelFalse(Pageable pageable);
	UserEntity findByUsername(String username);
	
}