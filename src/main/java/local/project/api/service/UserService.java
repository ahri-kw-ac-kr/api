package local.project.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import local.project.api.model.DocumentEntity;
import local.project.api.model.DocumentRepository;

@Service
public class UserService extends DefaultService<DocumentEntity> {

    @Autowired
    private DocumentRepository documentRepository;

    public Page<DocumentEntity> getAll(int page) {
        return documentRepository.findAllByIsDelFalse(PageRequest.of(page, 20));
	}
	
	
=======
import local.project.api.model.UserEntity;
import local.project.api.model.UserRepository;

@Service
public class UserService extends DefaultService<UserEntity> {

    @Autowired
    private UserRepository userRepository;

    public Page<UserEntity> getAll(int page) {
        return userRepository.findAllByIsDelFalse(PageRequest.of(page, 20));
	}
	
>>>>>>> 208343ec5143bfca9ed50a15b975ac208c9703ac
}