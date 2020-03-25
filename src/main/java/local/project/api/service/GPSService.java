package local.project.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import local.project.api.model.GPSEntity;
import local.project.api.model.GPSRepository;

@Service
public class GPSService extends DefaultService<GPSEntity> {

    @Autowired
    private GPSRepository gpsRepository;

    public Page<GPSEntity> getAll(int page) {
        return gpsRepository.findAllByIsDelFalse(PageRequest.of(page, 20));
	}
    
    public Page<GPSEntity> getAllByUserId(Long userId, int page) {
        return gpsRepository.findAllByUserId(userId, PageRequest.of(page, 20));
	}
	
}