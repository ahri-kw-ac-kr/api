package local.project.api.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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
import local.project.api.model.SleepEntity;
import local.project.api.model.UserEntity;
import local.project.api.service.RawdataService;
import local.project.api.service.SleepService;
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
	
	@Autowired
	private SleepService sleepService;
	

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
		if (userService.isFriend(userEntity.getId(), id) == false && userEntity.getId() != id) { 
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Not acceptable");
		}

		// 친구 맞음
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
		Date cre_lt_date = transFormat.parse(created_at_lt);
		Date cre_gt_date = transFormat.parse(created_at_gt);
		System.out.println(created_at_lt+" ㄱㄴㄷㄹ "+created_at_gt);
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(cre_lt_date);
		cal2.setTime(cre_gt_date);
		int cre_lt = (int) (cal1.getTimeInMillis()/1000);
		int cre_gt = (int) (cal2.getTimeInMillis()/1000);
		System.out.println(Integer.toString(cre_lt)+" 가나다라 "+Integer.toString(cre_gt));
		return rawdataService.getPeroidByUserId(id, p, cre_lt, cre_gt);
	}
	
	@RequestMapping(value = "/{id}/gps")
	public Iterable<GPSEntity> getGPS(
		@PathVariable Long id,
		@RequestParam(value = "page", defaultValue = "0") String page, Principal principal)
				throws ParseException {
					String username = principal.getName();
					UserEntity userEntity = userService.getByUsername(username);
					int p = Integer.parseInt(page);
					if (userService.isFriend(userEntity.getId(), id) == false && userEntity.getId() != id) { 
						throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Not acceptable");
					}
					
					return gpsService.getTopByUserId(id, p);
	}
	
	@RequestMapping(value = "/{id}/sleep")
	public Iterable<SleepEntity> getSleep(
		@PathVariable Long id,
		@RequestParam(value = "page", defaultValue = "0") String page, Principal principal)
				throws ParseException {
					String username = principal.getName();
					UserEntity userEntity = userService.getByUsername(username);
					int p = Integer.parseInt(page);
					if (userService.isFriend(userEntity.getId(), id) == false && userEntity.getId() != id) { 
						throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Not acceptable");
					}
					
					return sleepService.getTopByUserId(id, p);
	}
	
	@RequestMapping(value = "/forget", method = RequestMethod.PATCH)
	public UserEntity sendMail(@RequestParam String username) throws ParseException{
		UserEntity userEntity = userService.getByUsername(username);
		if(userEntity == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Username is not found");
		}
		
		Random rand = new Random();
		int randNumber = rand.nextInt(900000)+100000;
		userEntity.setNumber(String.valueOf(randNumber));
		userService.update(userEntity, userEntity.getId());
		
		Timer timer = new Timer();
		TimerTask timeTask = new TimerTask() {
			@Override public void run(){ 
				UserEntity user = new UserEntity();
				user.setNumber("000000");
				userService.update(user, userEntity.getId()); } };
			
		userService.sendMail(username, String.valueOf(randNumber));
		timer.schedule(timeTask, 300000); //5분 시간제한
		return userEntity;
	}
	
	@RequestMapping(value = "/initpassword", method = RequestMethod.PATCH)
	public UserEntity initPassword(
			@RequestParam String username,
			@RequestParam String number,
			@RequestParam String password) throws ParseException{
			UserEntity userEntity = userService.getByUsername(username);
			if(userEntity.getNumber().equals(number) != true || number.equals("000000")) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Time out");
			}
			//UserEntity changeUser = null;
			userEntity.setNewPassword(password);
			return userService.update(userEntity, userEntity.getId());
	}
	
	//친구 추가 : 내 정보를 보여줄 사람 추가
	@RequestMapping(value = "/{id}/plusfriend", method = RequestMethod.POST)
	public Optional<UserEntity> plusFriend(
		@PathVariable Long id, @RequestParam String frname)
				throws ParseException {
					UserEntity friend = userService.getByUsername(frname);
					if (friend == null) { 
						throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Username is not found");
					}
					userService.plusFriend(friend.getId(),id);
					Optional<UserEntity> user = userService.get(id);
					return user;
	}
	
	// 내 정보를 보고있는 사람목록 불러오기
	@RequestMapping(value = "/{id}/mydoctor", method = RequestMethod.GET)
	public Iterable<UserEntity> myDoctor(
		@PathVariable Long id, @RequestParam (value = "page", defaultValue = "0") String page) {
					int p = Integer.parseInt(page);	
					Optional<UserEntity> user = userService.get(id);
					return userService.myDoctor(id,p);
	}
	
	//친구 지우기
	@RequestMapping(value = "/delfriend", method = RequestMethod.DELETE)
	public UserEntity delFriend(@RequestParam long protectId, @RequestParam long patientId, Principal principal) {
		userService.delFriend(protectId, patientId);
		String userName = principal.getName();
		UserEntity user = userService.getByUsername(userName);
		return user;
	}
}