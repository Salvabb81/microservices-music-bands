package com.github.microservices.bands.model;

import java.time.LocalDate;
import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Band {

	private String name;
	private LocalDate start;
	private Set<Record> record;
	private Set<Genre> genres;

	// TODO Set<Musician> musicians;
	// TODO Country country;

}
