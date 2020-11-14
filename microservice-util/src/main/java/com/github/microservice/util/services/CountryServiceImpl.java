package com.github.microservice.util.services;

import org.springframework.stereotype.Service;

import com.github.microservice.util.repository.CountryRepository;
import com.github.microservices.country.model.Country;

@Service
public class CountryServiceImpl implements CountryService {

	private CountryRepository countryRepository;
	
	public CountryServiceImpl(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@Override
	public Iterable<Country> findAll() {
		return countryRepository.findAll();
	}
	
}
