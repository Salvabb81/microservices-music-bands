package com.github.microservices.musicicians.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Musician {

	private String firstName;
	private String lastName;
	private Integer age;
	private Boolean active;
	// TODO private Set<Band> bands;

}
