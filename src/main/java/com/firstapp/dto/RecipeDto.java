package com.firstapp.dto;

import java.util.List;

import com.firstapp.models.Ingredient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RecipeDto {
	
	private Long id;
	private String name;
	private String description;
	private String imagePath;
	private List<Ingredient> ingredients;
	


}
