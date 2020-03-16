package local.project.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import local.project.api.model.RelationEntity;
import local.project.api.model.RelationRepository;

@Service
public class RelationService extends DefaultService<RelationEntity> {

    @Autowired
    private RelationRepository RelationRepository;

    public Page<RelationEntity> getAll(int page) {
        return RelationRepository.findAllByIsDelFalse(PageRequest.of(page, 20));
	}
	
	
}