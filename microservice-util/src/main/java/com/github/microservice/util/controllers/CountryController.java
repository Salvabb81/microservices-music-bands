package com.github.microservice.util.controllers;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.microservice.util.services.CountryService;
import com.github.microservices.country.model.Country;

@RestController
@RequestMapping("/api/country")
public class CountryController {

	private CountryService countryService;

	public CountryController(CountryService countryService) {
		this.countryService = countryService;
	}

	@Cacheable("countries")
	@GetMapping
	public ResponseEntity<Iterable<Country>> listEntities() {
		return ResponseEntity.ok().body(countryService.findAll());
	}
}
