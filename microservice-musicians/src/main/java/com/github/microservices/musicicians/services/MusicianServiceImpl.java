package com.github.microservices.musicicians.services;

import org.springframework.stereotype.Service;

import com.github.microservices.commons.services.CommonServiceImpl;
import com.github.microservices.musician.model.Musician;
import com.github.microservices.musicicians.repository.MusicianRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MusicianServiceImpl extends CommonServiceImpl<Musician, MusicianRepository> implements MusicianService {

}
