package local.project.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import local.project.api.model.rawdataEntity;
import local.project.api.model.rawdataRepository;

@Service
public class rawdataService extends DefaultService<rawdataEntity> {

    @Autowired
    private rawdataRepository rawdataRepository;

    public Page<rawdataEntity> getAll(int page) {
        return rawdataRepository.findAllByIsDelFalse(PageRequest.of(page, 20));
	}
	
	
}