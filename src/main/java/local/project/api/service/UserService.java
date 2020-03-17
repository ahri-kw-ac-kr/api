package local.project.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import local.project.api.model.UserEntity;
import local.project.api.model.UserRepository;

@Service
public class UserService extends DefaultService<UserEntity> {

    @Autowired
    private UserRepository userRepository;

    public Page<UserEntity> getAll(int page) {
        return userRepository.findAllByIsDelFalse(PageRequest.of(page, 20));
	}
	
}