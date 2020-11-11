package com.github.microservices.musicicians.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.microservices.commons.controllers.CommonController;
import com.github.microservices.musician.model.Musician;
import com.github.microservices.musicicians.services.MusicianService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/musician")
public class MusicianController extends CommonController<Musician, MusicianService> {

	@PostMapping("/create-with-photo")
	public ResponseEntity<?> createWithPhoto(@RequestBody Musician musician, @RequestParam MultipartFile file)
			throws IOException {
		if (!file.isEmpty()) {
			musician.setPhoto(file.getBytes());
		}
		return super.createEntity(musician);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Musician> editMusician(@RequestBody Musician musician, @PathVariable Long id,
			@RequestParam MultipartFile file) throws IOException {
		Optional<Musician> o = service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Musician dbMusician = o.get();

		dbMusician.setFirstName(musician.getFirstName());
		dbMusician.setLastName(musician.getLastName());
		dbMusician.setAge(musician.getAge());
		dbMusician.setActive(musician.getActive());
		dbMusician.setInstrument(musician.getInstrument());
		if (!file.isEmpty()) {
			dbMusician.setPhoto(file.getBytes());
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dbMusician));
	}

}
