package com.firstapp.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstapp.dto.RecipeDto;
import com.firstapp.exceptions.EntityNotFoundException;
import com.firstapp.models.Recipe;
import com.firstapp.repositories.RecipeJpaRepository;

@Service
public class RecipeServiceImp implements RecipeService{
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	private RecipeJpaRepository recipeJpaRepository;

	@Override
	public List<RecipeDto> getRecipes(){
		
		Function<Recipe,RecipeDto> lambdaConvert =  elt -> convertToDto(elt);
		List<Recipe> recipes = recipeJpaRepository.findAll();
		logger.debug("getRecipes()");
		return recipes.stream().map(lambdaConvert).collect(Collectors.toList());
	}
	
	public RecipeDto getRecipeByName(String name) throws EntityNotFoundException{
		
		    logger.debug("findByEmail({})", name);

	        Optional<Recipe> recipe = Optional.ofNullable(recipeJpaRepository.findByName(name));

	        if(!recipe.isPresent()){
	            throw new EntityNotFoundException(String.format("Recipe with userName %s does not exist", recipe));
	        }
	        
	        return convertToDto(recipe.get());
	}
	
	
	public RecipeDto convertToDto(Recipe recipe){
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(recipe, RecipeDto.class);
	}

	@Override
	public RecipeDto addRecipe(RecipeDto recipeDto) {
		logger.debug("addRecipe() from service");
		Recipe recipe = convertFromDto(recipeDto);
		return convertToDto((Recipe)recipeJpaRepository.save(recipe));
	}
	
	public Recipe convertFromDto(RecipeDto recipe){
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(recipe, Recipe.class);
	}

	@Override
	public void deleteRecipe(Long id) {
		logger.debug("deleteRecipe() from service");
		recipeJpaRepository.delete(id);
		
	}


	@Override
	public RecipeDto getRecipeById(Long id) throws EntityNotFoundException {
		logger.debug("getRecipeById({})", id);

        Optional<Recipe> recipe = Optional.ofNullable(recipeJpaRepository.getOne(id));

        if(!recipe.isPresent()){
            throw new EntityNotFoundException(String.format("Recipe with id %s does not exist", recipe));
        }
        
        return convertToDto(recipe.get());
	}

}
