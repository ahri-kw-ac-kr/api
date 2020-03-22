package local.project.api.service;

import java.util.Optional;
import java.util.stream.Stream;

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
    public boolean isFriend(long id1, long id2) {
		Optional<UserEntity> optionalRequestUser = userRepository.findById(id1);
		if (optionalRequestUser.isPresent()==false) { return false; }
		UserEntity requestUser = optionalRequestUser.get();
		Stream<UserEntity> filteredFriend = requestUser.getFriend().stream().filter(e -> e.getId() == id2);
		if (filteredFriend.count() == 0) {
		  // Ä£±¸¾Æ´Ô
		  return false;
		}
		return true;
    }
    public UserEntity getByUsername(String username) {
    	return userRepository.findByUsername(username);
    }
}