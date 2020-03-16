package local.project.api.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface rawdataRepository extends PagingAndSortingRepository<rawdataEntity, Long> {
    Page<rawdataEntity> findAllByIsDelFalse(Pageable pageable);
} 