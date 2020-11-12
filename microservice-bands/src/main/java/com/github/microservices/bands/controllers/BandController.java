package com.github.microservices.bands.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.microservices.band.model.Band;
import com.github.microservices.bands.services.BandService;
import com.github.microservices.commons.controllers.CommonController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/band")
public class BandController extends CommonController<Band, BandService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> editBand(@RequestBody Band band, BindingResult result, @PathVariable Long id) {
		if (result.hasErrors()) {
			return this.validar(result);
		}

		Optional<Band> o = service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Band dbBand = o.get();
		dbBand.setName(band.getName());
		dbBand.setStart(band.getStart());

		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbBand));
	}

}
