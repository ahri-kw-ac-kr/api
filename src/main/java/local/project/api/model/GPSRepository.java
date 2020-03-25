package local.project.api.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GPSRepository extends PagingAndSortingRepository<GPSEntity, Long> {
    Page<GPSEntity> findAllByIsDelFalse(Pageable pageable);
	Page<GPSEntity> findAllByUserId(Long userId, PageRequest of);
} 