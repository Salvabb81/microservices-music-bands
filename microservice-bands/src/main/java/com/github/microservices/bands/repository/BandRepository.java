package com.github.microservices.bands.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.github.microservices.band.model.Band;

public interface BandRepository extends PagingAndSortingRepository<Band, Long>{

}
