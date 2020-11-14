package com.github.microservice.util.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.github.microservices.country.model.Country;

public interface CountryRepository extends PagingAndSortingRepository<Country, Long> {

}
