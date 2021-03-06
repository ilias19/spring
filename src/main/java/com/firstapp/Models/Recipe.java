package com.firstapp.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public @Data class  Recipe {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String description;
	private String imagePath;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "recipe", cascade = CascadeType.ALL)
	 //@JsonBackReference
	private List<Ingredient> ingredients;
	
	

}
