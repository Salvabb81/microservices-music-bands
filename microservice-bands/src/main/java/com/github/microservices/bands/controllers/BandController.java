package com.github.microservices.bands.controllers;

import java.util.Optional;

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

import com.github.microservices.band.model.Band;
import com.github.microservices.bands.services.BandService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/band")
public class BandController {

	private BandService bandService;

	public BandController(BandService bandService) {
		this.bandService = bandService;
	}

	@GetMapping
	public ResponseEntity<Page<Band>> listBands(Pageable pageable) {
		return ResponseEntity.ok().body(bandService.findAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Band> getBandById(@PathVariable Long id) {
		Optional<Band> o = bandService.findById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(o.get());
	}

	@PostMapping
	public ResponseEntity<Band> createBand(@RequestBody Band band) {
		return ResponseEntity.status(HttpStatus.CREATED).body(bandService.save(band));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Band> editBand(@RequestBody Band band, @PathVariable Long id) {
		Optional<Band> o = bandService.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Band dbBand = o.get();
		dbBand.setName(band.getName());
		dbBand.setStart(band.getStart());

		return ResponseEntity.status(HttpStatus.CREATED).body(this.bandService.save(dbBand));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBand(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(bandService.deleteById(id));
	}

}
