package com.github.microservices.musicicians.controllers;

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

import com.github.microservices.musician.model.Musician;
import com.github.microservices.musicicians.services.MusicianService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/musician")
public class MusicianController {

	private MusicianService musicianService;

	public MusicianController(MusicianService musicianService) {
		this.musicianService = musicianService;
	}

	@GetMapping
	public ResponseEntity<Page<Musician>> listMusicians(Pageable pageable) {
		return ResponseEntity.ok().body(musicianService.findAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Musician> getMusicianById(@PathVariable Long id) {
		Optional<Musician> o = musicianService.findById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(o.get());
	}

	@PostMapping
	public ResponseEntity<Musician> createMusician(@RequestBody Musician musician) {
		return ResponseEntity.status(HttpStatus.CREATED).body(musicianService.save(musician));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Musician> editMusician(@RequestBody Musician musician, @PathVariable Long id) {
		Optional<Musician> o = musicianService.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Musician dbMusician = o.get();

		dbMusician.setFirstName(musician.getFirstName());
		dbMusician.setLastName(musician.getLastName());
		dbMusician.setAge(musician.getAge());
		dbMusician.setActive(musician.getActive());
		dbMusician.setInstrument(musician.getInstrument());

		return ResponseEntity.status(HttpStatus.CREATED).body(musicianService.save(dbMusician));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteMusician(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(musicianService.deleteById(id));
	}
}
