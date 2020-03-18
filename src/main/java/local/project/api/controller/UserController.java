package local.project.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import local.project.api.model.RawdataEntity;
import local.project.api.service.RawdataService;
import local.project.api.model.UserEntity;
import local.project.api.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RawdataService rawdataService;


	@RequestMapping(method = RequestMethod.POST)
	public Iterable<UserEntity> getAll(@RequestParam(value = "page", defaultValue = "0") String page) {
		int p = Integer.parseInt(page);
		return userService.getAll(p);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public UserEntity insert(@RequestBody UserEntity entity) {
		System.out.println(entity.getId());
		return userService.insert(entity);
	}

	@RequestMapping(value = "/{id}")
	public UserEntity get(@PathVariable Long id) {
		Optional<UserEntity> entity = userService.get(id);
		if (!entity.isPresent()) {
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "entity not found"
			);
		}
		return entity.get();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public UserEntity patch(@PathVariable Long id, UserEntity userEntity) {
		return userService.update(userEntity);
	}

	@RequestMapping(method = RequestMethod.PATCH)
	public Iterable<UserEntity> dumpPatch(List<UserEntity> userEntity) {
		return userService.dumpUpdate(userEntity);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Boolean delete(@PathVariable Long id) {
		return userService.delete(id);
	}


	@RequestMapping(value = "/{id}/rawdata")
	public Iterable<RawdataEntity> getRawdata(
		@PathVariable Long id,
		@RequestParam(value = "page", defaultValue = "0") String page) {
		int p = Integer.parseInt(page);
		return rawdataService.getAllByUserId(id, p);
	}

}