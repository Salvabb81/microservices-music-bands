package com.github.microservices.country.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "countries")
public class Country {

	@Id
	private String iso;

	private String name;
}
