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

import local.project.api.model.GPSEntity;
import local.project.api.service.GPSService;

@RestController
@RequestMapping(value = "/gps")
public class GPSController {

	
	@Autowired
	private GPSService gpsService;


	@RequestMapping(method = RequestMethod.GET)
	public Iterable<GPSEntity> getAll(@RequestParam(value = "page", defaultValue = "0") String page) {
		int p = Integer.parseInt(page);
		return gpsService.getAll(p).getContent();
	}

	@RequestMapping(method = RequestMethod.POST)
	public GPSEntity insert(@RequestBody GPSEntity entity) {
		System.out.println(entity.getLat()+","+entity.getLon());
		return gpsService.insert(entity);
	}

	@RequestMapping(value = "/{id}")
	public GPSEntity get(@PathVariable Long id) {
		Optional<GPSEntity> entity = gpsService.get(id);
		if (!entity.isPresent()) {
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "entity not found"
			);
		}
		return entity.get();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public GPSEntity patch(@PathVariable Long id, GPSEntity gpsEntity) {
		return gpsService.update(gpsEntity);
	}

	@RequestMapping(method = RequestMethod.PATCH)
	public Iterable<GPSEntity> dumpPatch(List<GPSEntity> gpsEntity) {
		return gpsService.dumpUpdate(gpsEntity);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Boolean delete(@PathVariable Long id) {
		return gpsService.delete(id);
	}


	
}