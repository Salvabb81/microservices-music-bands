package com.github.microservices.bands.model;

import java.time.LocalDate;
import java.util.Set;

import com.github.microservices.country.model.Country;
import com.github.microservices.musician.model.Musician;
import com.github.microservices.record.model.Record;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Band {

	private String name;
	private LocalDate start;
	private Set<Record> record;
	private Set<Genre> genres;

	private Set<Musician> musicians;
	private Country country;

}
