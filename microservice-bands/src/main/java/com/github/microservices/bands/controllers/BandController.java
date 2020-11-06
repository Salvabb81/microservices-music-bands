package com.github.microservices.bands.controllers;

import java.util.List;

import javax.persistence.Entity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.microservices.band.model.Record;
import com.github.microservices.musician.model.Musician;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/band")
public class BandController {

	@GetMapping
	public ResponseEntity<?> listBands() {
		return null;
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getBandById(@PathVariable Long id) {
		return null;
	}

	@PostMapping
	public ResponseEntity<?> createBand(@RequestBody Entity entity) {
		return null;
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> editBand(@RequestBody Entity entity, @PathVariable Long id) {
		return null;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBand(@PathVariable Long id) {
		return null;
	}

	@PutMapping("/{id}/assign-musicians")
	public ResponseEntity<?> assignMusicians(@RequestBody List<Musician> musicians, @PathVariable Long id) {
		return null;
	}

	@PutMapping("/{id}/create-record")
	public ResponseEntity<?> createRecord(@RequestBody Record record, @PathVariable Long id) {
		return null;
	}
}
