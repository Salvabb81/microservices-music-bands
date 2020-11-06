package com.github.microservices.musicicians.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.github.microservices.musician.model.Musician;

public interface MusicianRepository extends PagingAndSortingRepository<Musician, Long>{

}
