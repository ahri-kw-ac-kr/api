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

import local.project.api.model.RelationEntity;
import local.project.api.service.RelationService;

@RestController
@RequestMapping(value = "/relation")
public class RelationController {

	
	@Autowired
	private RelationService relationService;


	@RequestMapping(method = RequestMethod.POST)
	public Iterable<RelationEntity> getAll(@RequestParam(value = "page", defaultValue = "0") String page) {
		int p = Integer.parseInt(page);
		return relationService.getAll(p);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public RelationEntity insert(@RequestBody RelationEntity entity) {
		System.out.println(entity.getUserA());
		return relationService.insert(entity);
	}

	@RequestMapping(value = "/{id}")
	public RelationEntity get(@PathVariable Long id) {
		Optional<RelationEntity> entity = relationService.get(id);
		if (!entity.isPresent()) {
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "entity not found"
			);
		}
		return entity.get();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public RelationEntity patch(@PathVariable Long id, RelationEntity relationEntity) {
		return relationService.update(relationEntity);
	}

	@RequestMapping(method = RequestMethod.PATCH)
	public Iterable<RelationEntity> dumpPatch(List<RelationEntity> relationEntity) {
		return relationService.dumpUpdate(relationEntity);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Boolean delete(@PathVariable Long id) {
		return relationService.delete(id);
	}

}