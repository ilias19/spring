package com.firstapp.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.firstapp.controllers.RecipeController;
import com.firstapp.dto.RecipeDto;
import com.firstapp.models.Recipe;

public class TestRecipeController {
	
	@Test
	public void testDtoConverter(){
		Recipe recipe =  new Recipe(1l,"name","description","imagePath");
		RecipeController controller =  new RecipeController();
		RecipeDto dto = controller.convertToDto(recipe);
		assertEquals(recipe.getName(),dto.getName());
		assertEquals(recipe.getDescription(),dto.getDescription());
		assertEquals(recipe.getImagePath(),dto.getImagePath());
	}

}
