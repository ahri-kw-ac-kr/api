package local.project.api.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawdataRepository extends PagingAndSortingRepository<RawdataEntity, Long> {
    Page<RawdataEntity> findAllByIsDelFalse(Pageable pageable);
    Page<RawdataEntity> findAllByUserId(Long userId, Pageable pageable);
} 