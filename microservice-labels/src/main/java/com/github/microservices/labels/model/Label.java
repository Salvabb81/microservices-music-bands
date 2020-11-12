package com.github.microservices.labels.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.github.microservices.band.model.Record;
import com.github.microservices.country.model.Country;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "labels")
public class Label {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY)
	private Set<Record> records;
	
	@NotEmpty
	private Country country;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@NotEmpty
	@Email
	private String email;

	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}

}
