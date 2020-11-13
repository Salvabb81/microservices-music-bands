package com.github.microservices.musicicians;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@EntityScan({"com.github.microservices.musician.model"})
public class MicroserviceMusiciansApplication {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceMusiciansApplication.class, args);
	}

//	@PostConstruct
//	private void initDb() {
//	    String sqlStatements[] = {
//	      "drop table musicians if exists",
//	      "create table musicians(id serial,first_name varchar(255),last_name varchar(255))",
//	      "insert into employees(active, age, create_at, first_name, last_name, photo) values(,'2000/07/05')"
////	      "insert into employees(first_name, last_name) values('Scott','Tiger')"
//	    };
//	 
//	    Arrays.asList(sqlStatements).forEach(sql -> {
//	        jdbcTemplate.execute(sql);
//	    });
//	}
	
}
