package local.project.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import local.project.api.model.RawdataEntity;
import local.project.api.service.RawdataService;

@RestController
@RequestMapping(value = "/rawdata")
public class RawdataController {


	@Autowired
	private RawdataService RawdataService;


	@RequestMapping(method = RequestMethod.POST)
	public Iterable<RawdataEntity> getAll(@RequestParam(value = "page", defaultValue = "0") String page) {
		int p = Integer.parseInt(page);
		return RawdataService.getAll(p);
	}


	@RequestMapping(value = "/{id}")
	public RawdataEntity get(@PathVariable Long id) {
		Optional<RawdataEntity> entity = RawdataService.get(id);
		if (!entity.isPresent()) {
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "entity not found"
			);
		}
		return entity.get();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public RawdataEntity patch(@PathVariable Long id, RawdataEntity rawdataEntity) {
		return RawdataService.update(rawdataEntity);
	}

	@RequestMapping(method = RequestMethod.PATCH)
	public Iterable<RawdataEntity> dumpPatch(List<RawdataEntity> rawdataEntity) {
		return RawdataService.dumpUpdate(rawdataEntity);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Boolean delete(@PathVariable Long id) {
		return RawdataService.delete(id);
	}

}
