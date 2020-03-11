package local.project.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import local.project.api.model.DocumentEntity;
import local.project.api.service.DocumentService;

@RestController
public class DocumentController {

	
	@Autowired
	private DocumentService documentService;


	@RequestMapping({ "/document" })
	public Iterable<DocumentEntity> list() {
		return documentService.list();
	}

}