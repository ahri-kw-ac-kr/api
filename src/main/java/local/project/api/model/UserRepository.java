package local.project.api.model;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	Page<UserEntity> findAllByIsDelFalse(Pageable pageable);
	UserEntity findByUsername(String username);
	Optional<UserEntity> findById(long id);
	
	@Modifying
	@Transactional
	@Query(value="insert into user_friend values(:frId,:userId)", nativeQuery=true)
	void plusFriend(@Param("frId")long frId, @Param("userId")long userId);
	
	@Query(value="select * from user, user_friend where user_friend.friend_id=:userId and user.id=user_friend.user_entity_id", nativeQuery=true)
	Page<UserEntity> myDoctor(@Param("userId")long id, Pageable pageable);
}