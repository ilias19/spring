package com.firstapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.firstapp.models.Ingredient;
import com.firstapp.models.Recipe;
import com.firstapp.repositories.IngredientJpaRepository;
import com.firstapp.repositories.RecipeJpaRepository;

@Component
public class Init {
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	private RecipeJpaRepository recipeRepository;
	
	@Autowired
	private IngredientJpaRepository ingredientRepository;
	
	@PostConstruct
	public void init(){
		
		logger.debug("init saving recipes in db");
		Recipe recipe1 = Recipe.builder().name("Tasty Schnitzel").description("A super-tasty Schnitzel - just awesome!").imagePath("https://upload.wikimedia.org/wikipedia/commons/7/72/Schnitzel.JPG").build();
		Ingredient ing1 = Ingredient.builder().name("Meat").amount(1).recipe(recipe1).build();
		Ingredient ing2 = Ingredient.builder().name("French Fries").amount(20).recipe(recipe1).build();
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredients.add(ing1);
		ingredients.add(ing2);
		
		Recipe recipe2 = Recipe.builder().name("Big Fat Burger").description("What else you need to say?").imagePath("https://upload.wikimedia.org/wikipedia/commons/b/be/Burger_King_Angus_Bacon_%26_Cheese_Steak_Burger.jpg").build();
		Ingredient ing3 = Ingredient.builder().name("Meat").amount(1).recipe(recipe2).build();
		Ingredient ing4 = Ingredient.builder().name("French Fries").amount(20).recipe(recipe2).build();
		List<Ingredient> ingredients1 = new ArrayList<Ingredient>();
		ingredients1.add(ing3);
		ingredients1.add(ing4);
		recipe1.setIngredients(ingredients);
		recipe2.setIngredients(ingredients1);
		recipeRepository.save(Arrays.asList(recipe1,recipe2));
		//recipeRepository.save(recipe1);
		//recipeRepository.save(recipe2);
	}

}
