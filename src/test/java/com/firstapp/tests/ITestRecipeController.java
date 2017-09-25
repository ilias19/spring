package com.firstapp.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.firstapp.controllers.RecipeController;
import com.firstapp.dto.RecipeDto;
import com.firstapp.models.Recipe;
import com.firstapp.services.RecipeServiceImp;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITestRecipeController {
	
	@Autowired
	RecipeServiceImp service;
	
	@Autowired
	RecipeController controller;

	
	@Test
	public void testDtoConverter(){
		Recipe recipe =  new Recipe(1l,"name","description","imagePath");
		//RecipeServiceImp recipeService = new RecipeServiceImp();
		RecipeDto dto = service.convertToDto(recipe);
		assertEquals(recipe.getName(),dto.getName());
		assertEquals(recipe.getDescription(),dto.getDescription());
		assertEquals(recipe.getImagePath(),dto.getImagePath());
	}
	
	@Test
	public void testFindRecipeByName(){
		ResponseEntity<RecipeDto> response = controller.getRecipeByName("name1");
		assertEquals(response.getStatusCode(),HttpStatus.OK);
	}
	
	@Test
	public void testAddRecipe(){
		Recipe recipe =  Recipe.builder().name("name1992").description("description").imagePath("imagePath").build();
		RecipeDto dto = service.convertToDto(recipe);
		controller.addRecipe(dto);
		ResponseEntity<RecipeDto> response = controller.getRecipeByName("name1992");
		assertEquals(response.getStatusCode(),HttpStatus.OK);
	}
	
	@Test
	public void testDeleteRecipe(){
		controller.deleteRecipeById(2L);
		ResponseEntity<RecipeDto> response = controller.getRecipeById(2L);
		assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
	}

}
