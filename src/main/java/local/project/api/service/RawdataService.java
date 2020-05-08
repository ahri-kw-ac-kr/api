package local.project.api.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import local.project.api.model.RawdataEntity;
import local.project.api.model.RawdataRepository;

@Service
public class RawdataService extends DefaultService<RawdataEntity> {

    @Autowired
    private RawdataRepository rawdataRepository;

    public Page<RawdataEntity> getAll(int page) {
        return rawdataRepository.findAllByIsDelFalse(PageRequest.of(page, 20));
    }
    
    public Page<RawdataEntity> getAllByUserId(Long userId, int page) {
        return rawdataRepository.findAllByUserId(userId, PageRequest.of(page, 20));
	}
    
    public Page<RawdataEntity> getPeroidByUserId(Long userId, int page, int created_at_lt, int created_at_gt) {
        return rawdataRepository.findPeriodByUserId(userId, PageRequest.of(page, 4500), created_at_lt, created_at_gt);
	}
}