package com.firstapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.firstapp.dto.RecipeDto;
import com.firstapp.exceptions.EntityNotFoundException;


public interface RecipeService {
	
    public List<RecipeDto> getRecipes();
    public RecipeDto getRecipeByName(String name)throws EntityNotFoundException;
    public RecipeDto addRecipe(RecipeDto recipe);
    public void deleteRecipe(Long id);
    public RecipeDto getRecipeById(Long id)throws EntityNotFoundException;
}
