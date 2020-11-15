package com.github.microservices.labels.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.microservices.commons.controllers.CommonController;
import com.github.microservices.labels.model.Label;
import com.github.microservices.labels.services.LabelService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LabelController extends CommonController<Label, LabelService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> editLabel(@Valid Label label, BindingResult result, @PathVariable Long id) {
		if (result.hasErrors()) {
			return this.validar(result);
		}

		Optional<Label> o = service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Label dbLabel = o.get();

		dbLabel.setName(label.getName());
		dbLabel.setCountry(label.getCountry());
		dbLabel.setRecords(label.getRecords());

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dbLabel));
	}

}
