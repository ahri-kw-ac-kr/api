package local.project.api.model;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RawdataRepository extends PagingAndSortingRepository<RawdataEntity, Long> {
    Page<RawdataEntity> findAllByIsDelFalse(Pageable pageable);
    Page<RawdataEntity> findAllByUserId(Long userId, Pageable pageable);
    
    @Query(value="select * from (select * from rawdata where user_id = :userId) R "
    		+ "where R.created_at between :cre_lt and :cre_gt", nativeQuery=true)
    Page<RawdataEntity> findPeriodByUserId(@Param("userId")Long userId, Pageable pageable, @Param("cre_lt")Date created_at_lt, @Param("cre_gt")Date created_at_gt);
} 