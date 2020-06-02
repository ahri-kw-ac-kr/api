package local.project.api.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SleepRepository extends PagingAndSortingRepository<SleepEntity, Long> {
    Page<SleepEntity> findAllByIsDelFalse(Pageable pageable);
    Page<SleepEntity> findAllByUserId(Long userId, PageRequest of);
    
    @Query(value="select * from sleep where user_id = :userId order by created_at desc", nativeQuery=true)
	Page<SleepEntity> findTopByUserId(@Param("userId")Long userId, Pageable pageable);
    
    @Query(value="select * from (select * from sleep where user_id = :userId) R "
    		+ "where R.wake_time between :cre_lt and :cre_gt order by created_at desc", nativeQuery=true)
    Page<SleepEntity> findPeriodByUserId(@Param("userId")Long userId, Pageable pageable, @Param("cre_lt")String created_at_lt, @Param("cre_gt")String created_at_gt);

} 