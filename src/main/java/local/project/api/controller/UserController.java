package local.project.api.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import local.project.api.model.UserEntity;
import local.project.api.service.RawdataService;
import local.project.api.service.UserService;
import local.project.api.model.GPSEntity;
import local.project.api.service.GPSService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RawdataService rawdataService;

	@Autowired
	private GPSService gpsService;
	

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<UserEntity> getAll(@RequestParam(value = "page", defaultValue = "0") String page, Principal principal) {
		int p = Integer.parseInt(page);
		System.out.println(principal.getName());
		return userService.getAll(p);
	}

	@RequestMapping(method = RequestMethod.POST)
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
	public UserEntity patch(@PathVariable Long id, @RequestBody UserEntity userEntity, Principal principal) {
		String username = principal.getName();
		UserEntity nowUser = userService.getByUsername(username);
		if (nowUser.getId()!= id) { //본인이 아닌경우
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Not acceptable");
		}
		return userService.update(userEntity, id);
	}

	@RequestMapping(method = RequestMethod.PATCH)
	public Iterable<UserEntity> dumpPatch(List<UserEntity> userEntity) {
		return userService.dumpUpdate(userEntity);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Boolean delete(@PathVariable Long id) {
		return userService.delete(id);
	}

	// http://localhost:8080/user/{id}/rawdata/?page=0&created_at_lt=00&created_at_gt=00

	
	
	@RequestMapping(value = "/{id}/rawdata")
	public Iterable<RawdataEntity> getRawdata(
		@PathVariable Long id,
		@RequestParam(defaultValue = "0") String page,
		@RequestParam(defaultValue = "0") String created_at_lt,
		@RequestParam(defaultValue = "0") String created_at_gt,
		Principal principal )
		throws ParseException {

		String username = principal.getName();
		UserEntity userEntity = userService.getByUsername(username);
		int p = Integer.parseInt(page);	

		// 친구 아닐 경우
		if (userService.isFriend(userEntity.getId(), id) == false) { 
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Not acceptable");
		}

		// 친구 맞음
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date cre_lt = transFormat.parse(created_at_lt);
		Date cre_gt = transFormat.parse(created_at_gt);
		return rawdataService.getPeroidByUserId(id, p, cre_lt, cre_gt);
	}
	
	@RequestMapping(value = "/{id}/gps")
	public Iterable<GPSEntity> getAll(
		@PathVariable Long id,
		@RequestParam(value = "page", defaultValue = "0") String page, Principal principal)
				throws ParseException {
					String username = principal.getName();
					UserEntity userEntity = userService.getByUsername(username);
					int p = Integer.parseInt(page);
					if (userService.isFriend(userEntity.getId(), id) == false) { 
						throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Not acceptable");
					}
					
					return gpsService.getAllByUserId(id, p);
				}
}