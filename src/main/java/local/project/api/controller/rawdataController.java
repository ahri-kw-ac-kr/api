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

import local.project.api.model.rawdataEntity;
import local.project.api.service.rawdataService;

@RestController
@RequestMapping(value = "/rawdata")
public class rawdataController {

	
	@Autowired
	private rawdataService rawdataService;


	@RequestMapping(method = RequestMethod.POST)
	public Iterable<rawdataEntity> getAll(@RequestParam(value = "page", defaultValue = "0") String page) {
		int p = Integer.parseInt(page);
		return rawdataService.getAll(p);
	}


	@RequestMapping(value = "/{id}")
	public rawdataEntity get(@PathVariable Long id) {
		Optional<rawdataEntity> entity = rawdataService.get(id);
		if (!entity.isPresent()) {
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "entity not found"
			);
		}
		return entity.get();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public rawdataEntity patch(@PathVariable Long id, rawdataEntity rawdataEntity) {
		return rawdataService.update(rawdataEntity);
	}

	@RequestMapping(method = RequestMethod.PATCH)
	public Iterable<rawdataEntity> dumpPatch(List<rawdataEntity> rawdataEntity) {
		return rawdataService.dumpUpdate(rawdataEntity);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Boolean delete(@PathVariable Long id) {
		return rawdataService.delete(id);
	}

}