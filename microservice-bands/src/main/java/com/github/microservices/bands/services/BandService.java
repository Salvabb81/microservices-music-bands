package com.github.microservices.bands.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.microservices.band.model.Band;

public interface BandService {

	public Page<Band> findAll(Pageable pageable);

	public Optional<Band> findById(Long id);

	public Band save(Band band);

	public void deleteById(Long id);

}
