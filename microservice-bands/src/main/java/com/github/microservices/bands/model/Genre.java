package com.github.microservices.bands.model;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Genre {

	private String name;
	private Genre supergenre;
	private Set<Genre> subgenres;

}
