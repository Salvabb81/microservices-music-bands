package com.github.microservices.musicicians.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.microservices.musician.model.Musician;

public interface MusicianService {

	public Page<Musician> findAll(Pageable pageable);

	public Optional<Musician> findById(Long id);

	public Musician save(Musician musician);

	public String deleteById(Long id);

}
