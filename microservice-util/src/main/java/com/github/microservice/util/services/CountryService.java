package com.github.microservice.util.services;

import com.github.microservices.country.model.Country;

public interface CountryService {

	public Iterable<Country> findAll();
	
}
