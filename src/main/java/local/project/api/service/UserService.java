package local.project.api.service;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import local.project.api.model.UserEntity;
import local.project.api.model.UserRepository;

@Service
public class UserService extends DefaultService<UserEntity> {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
	private PasswordEncoder bcryptEncoder;

    public Page<UserEntity> getAll(int page) {
        return userRepository.findAllByIsDelFalse(PageRequest.of(page, 20));
	}
    public boolean isFriend(long id1, long id2) {
		Optional<UserEntity> optionalRequestUser = userRepository.findById(id1);
		if (optionalRequestUser.isPresent()==false) { return false; }
		UserEntity requestUser = optionalRequestUser.get();
		Stream<UserEntity> filteredFriend = requestUser.getFriend().stream().filter(e -> e.getId() == id2);
		if (filteredFriend.count() == 0) {
		  // 친구가 아닐 경우
		  return false;
		}
		return true;
    }
    public UserEntity getByUsername(String username) {
    	return userRepository.findByUsername(username);
    }
    
    @Override
    public UserEntity update(UserEntity entity, Long id) {
    	Optional<UserEntity> origin = repository.findById(id);
		if (!origin.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}
		UserEntity originEntity = origin.get();
		if(originEntity.getPassword() == entity.getNewPassword() && entity.getNewPassword()!=null) {
			entity.setPassword(originEntity.getPassword());
		}
		else if(originEntity.getPassword() != entity.getNewPassword() && entity.getNewPassword()!=null) {
			entity.setPassword(bcryptEncoder.encode(entity.getNewPassword()));
		}

		this.merge(originEntity, entity);
		return repository.save(originEntity);
    	
    }
}