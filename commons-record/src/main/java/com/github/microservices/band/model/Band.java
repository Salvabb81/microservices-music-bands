package com.github.microservices.band.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.github.microservices.country.model.Country;
import com.github.microservices.musician.model.Musician;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "bands")
public class Band {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String name;
	
	@NotNull
	private LocalDate start;
	
	@NotNull
	@OneToOne(fetch = FetchType.LAZY)
	private Country country;
	
	@OneToMany(mappedBy = "band", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Record> record;
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<Genre> genres;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Musician> musicians;
	
}
