package com.github.microservices.labels.controllers;

import java.util.Optional;

import javax.persistence.Entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.microservices.labels.model.Label;
import com.github.microservices.labels.services.LabelService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/label")
public class LabelController {

	private LabelService labelService;

	public LabelController(LabelService labelService) {
		this.labelService = labelService;
	}

	@GetMapping
	public ResponseEntity<Page<Label>> listLables(Pageable pageable) {
		return ResponseEntity.ok().body(labelService.findAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Label> getLabelById(@PathVariable Long id) {
		Optional<Label> o = labelService.findById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(o.get());
	}

	@PostMapping
	public ResponseEntity<Label> createLabel(@RequestBody Label label) {
		return ResponseEntity.status(HttpStatus.CREATED).body(labelService.save(label));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Label> editLabel(@RequestBody Label label, @PathVariable Long id) {
		Optional<Label> o = labelService.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Label dbLabel = o.get();
		
		dbLabel.setName(label.getName());
		dbLabel.setCountry(label.getCountry());
		dbLabel.setRecords(label.getRecords());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(labelService.save(dbLabel));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteLabel(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(labelService.deleteById(id));
	}

}
