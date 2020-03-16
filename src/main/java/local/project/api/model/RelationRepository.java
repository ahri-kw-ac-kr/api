package local.project.api.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationRepository extends PagingAndSortingRepository<RelationEntity, Long> {
    Page<RelationEntity> findAllByIsDelFalse(Pageable pageable);
} 