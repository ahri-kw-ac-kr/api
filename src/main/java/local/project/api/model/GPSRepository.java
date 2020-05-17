package local.project.api.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GPSRepository extends PagingAndSortingRepository<GPSEntity, Long> {
    Page<GPSEntity> findAllByIsDelFalse(Pageable pageable);
    Page<GPSEntity> findAllByUserId(Long userId, PageRequest of);
    
    @Query(value="select * from gps where user_id = :userId order by created_at desc", nativeQuery=true)
	Page<GPSEntity> findTopByUserId(@Param("userId")Long userId, Pageable pageable);
} 