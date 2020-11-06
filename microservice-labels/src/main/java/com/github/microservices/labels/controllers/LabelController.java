package com.github.microservices.labels.controllers;

import javax.persistence.Entity;

import org.springframework.data.domain.Pageable;
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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/label")
public class LabelController {

	@GetMapping
	public ResponseEntity<Label> listLables(Pageable pageable) {
		return null;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Label> getLableById(@PathVariable Long id) {
		return null;
	}

	@PostMapping
	public ResponseEntity<Label> createLabel(@RequestBody Entity entity) {
		return null;
	}

	@PutMapping("{/id}")
	public ResponseEntity<Label> editLabel(@RequestBody Entity entity, @PathVariable Long id) {
		return null;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Label> deleteLabel(@PathVariable Long id) {
		return null;
	}

	@PutMapping("/{id")
	public ResponseEntity<Label> assignRecord(@RequestBody Entity entity, @PathVariable Long id) {
		return null;
	}

}
