package local.project.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import local.project.api.dto.PageDTO;
import local.project.api.model.DocumentEntity;
import local.project.api.model.DocumentRepository;

@Service
public class DocumentService extends DefaultService<DocumentEntity> {

    @Autowired
    private DocumentRepository documentRepository;

    public Page<DocumentEntity> getAll(int page) {
        return documentRepository.findAll(PageRequest.of(page, 20));
	}
	
	
}