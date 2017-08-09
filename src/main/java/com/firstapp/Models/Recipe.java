package com.firstapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;





@EntityScan("com.firstapp.models")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public @Data class  Recipe {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String description;
	private String imagePath;
	
	

}
