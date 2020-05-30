package local.project.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import local.project.api.model.SleepEntity;
import local.project.api.model.SleepRepository;

@Service
public class SleepService extends DefaultService<SleepEntity> {

    @Autowired
    private SleepRepository sleepRepository;

    public Page<SleepEntity> getAll(int page) {
        return sleepRepository.findAllByIsDelFalse(PageRequest.of(page, 20));
	}
    
    public Page<SleepEntity> getAllByUserId(Long userId, int page) {
        return sleepRepository.findAllByUserId(userId, PageRequest.of(page, 20));
	}
    
    public Page<SleepEntity> getTopByUserId(Long userId, int page) {
        return sleepRepository.findTopByUserId(userId, PageRequest.of(page, 100));
	}
	
}