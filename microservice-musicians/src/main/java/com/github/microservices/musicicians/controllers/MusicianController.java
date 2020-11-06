package com.github.microservices.musicicians.controllers;

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
	public ResponseEntity<Musician> listMusicians() {
		return null;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Musician> getMusicianById(@PathVariable Long id) {
		return null;
	}

	@PostMapping
	public ResponseEntity<Musician> createMusician(@RequestBody Musician musician) {
		return null;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Musician> editMusician(@RequestBody Musician musician, @PathVariable Long id) {
		return null;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Musician> deleteMusician(@PathVariable Long id) {
		return null;
	}
}
