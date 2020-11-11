package com.github.microservices.labels.services;

import org.springframework.stereotype.Service;

import com.github.microservices.commons.services.CommonServiceImpl;
import com.github.microservices.labels.model.Label;
import com.github.microservices.labels.repository.LabelRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LabelServiceImpl extends CommonServiceImpl<Label, LabelRepository> implements LabelService {

}
