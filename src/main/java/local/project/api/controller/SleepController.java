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

import local.project.api.model.SleepEntity;
import local.project.api.service.SleepService;

@RestController
@RequestMapping(value = "/sleep")
public class SleepController {

	
	@Autowired
	private SleepService sleepService;


	@RequestMapping(method = RequestMethod.GET)
	public Iterable<SleepEntity> getAll(@RequestParam(value = "page", defaultValue = "0") String page) {
		int p = Integer.parseInt(page);
		return sleepService.getAll(p);
	}

	@RequestMapping(method = RequestMethod.POST)
	public SleepEntity insert(@RequestBody SleepEntity entity) {
		return sleepService.insert(entity);
	}

	@RequestMapping(value = "/{id}")
	public SleepEntity get(@PathVariable Long id) {
		Optional<SleepEntity> entity = sleepService.get(id);
		if (!entity.isPresent()) {
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "entity not found"
			);
		}
		return entity.get();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public SleepEntity patch(@PathVariable Long id, SleepEntity sleepEntity) {
		return sleepService.update(sleepEntity, id);
	}

	@RequestMapping(method = RequestMethod.PATCH)
	public Iterable<SleepEntity> dumpPatch(List<SleepEntity> sleepEntity) {
		return sleepService.dumpUpdate(sleepEntity);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Boolean delete(@PathVariable Long id) {
		return sleepService.delete(id);
	}


	
}