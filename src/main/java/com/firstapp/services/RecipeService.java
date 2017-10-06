package com.firstapp.services;

import java.util.List;

import com.firstapp.dto.RecipeDto;
import com.firstapp.exceptions.EntityNotFoundException;
import com.firstapp.models.Recipe;


public interface RecipeService {
	
    public List<Recipe> getRecipes();
    public Recipe getRecipeByName(String name)throws EntityNotFoundException;
    public Recipe addRecipe(Recipe recipe);
    public void deleteRecipe(Long id);
    public Recipe getRecipeById(Long id)throws EntityNotFoundException;
}
