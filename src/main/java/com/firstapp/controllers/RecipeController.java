package com.firstapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firstapp.Models.Recipe;
import com.firstapp.repositories.RecipeJpaRepository;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
	
	@Autowired
	private RecipeJpaRepository recipeJpaRepository;
	
	@CrossOrigin
	@GetMapping(value = "/all")
	public List<Recipe> getRecipes(){
		return recipeJpaRepository.findAll();
	}
	
	@GetMapping(value = "/{name}")
	public Recipe getRecipeByName(@PathVariable String name){
		return recipeJpaRepository.findByName(name);
	}
	
	@PostMapping(value="/load")
	public Recipe addRecipe(@RequestBody Recipe recipe){
		recipeJpaRepository.save(recipe);
		return recipeJpaRepository.findByName(recipe.getName());
	}

	public RecipeJpaRepository getRecipeJpaRepository() {
		return recipeJpaRepository;
	}

	public void setRecipeJpaRepository(RecipeJpaRepository recipeJpaRepository) {
		this.recipeJpaRepository = recipeJpaRepository;
	}

}
