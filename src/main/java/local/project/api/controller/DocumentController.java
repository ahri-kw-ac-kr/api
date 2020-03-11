package local.project.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import local.project.api.dto.PageDTO;
import local.project.api.model.DocumentEntity;
import local.project.api.service.DocumentService;

@RestController
@RequestMapping(value = "/document")
public class DocumentController {

	
	@Autowired
	private DocumentService documentService;


	@RequestMapping(method = RequestMethod.POST)
	public Iterable<DocumentEntity> getAll(@RequestBody PageDTO page) {
		return documentService.getAll(page);
	}

	@RequestMapping(value = "/{id}")
	public DocumentEntity get(@PathVariable Long id) {
		Optional<DocumentEntity> entity = documentService.get(id);
		if (!entity.isPresent()) {
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "entity not found"
			);
		}
		return entity.get();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public DocumentEntity patch(@PathVariable Long id, DocumentEntity documentEntity) {
		return documentService.update(documentEntity);
	}

	@RequestMapping(method = RequestMethod.PATCH)
	public Iterable<DocumentEntity> dumpPatch(List<DocumentEntity> documentEntity) {
		return documentService.dumpUpdate(documentEntity);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Boolean delete(@PathVariable Long id) {
		return documentService.delete(id);
	}

}