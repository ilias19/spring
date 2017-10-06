package com.firstapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@EntityScan("com.firstapp.models")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public @Data class Ingredient {
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private int amount;
	
	 @ManyToOne
	 @JoinColumn(name = "recipe_id")
	 //@JsonManagedReference
	 private Recipe recipe;

}
