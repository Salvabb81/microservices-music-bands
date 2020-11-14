package com.github.microservices.bands.services;

import org.springframework.stereotype.Service;

import com.github.microservices.band.model.Band;
import com.github.microservices.bands.repository.BandRepository;
import com.github.microservices.commons.services.CommonServiceImpl;

@Service
public class BandServiceImpl extends CommonServiceImpl<Band, BandRepository> implements BandService {

}
