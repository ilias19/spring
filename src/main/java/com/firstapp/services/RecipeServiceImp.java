package com.firstapp.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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
	public List<Recipe> getRecipes(){
		
		Function<Recipe,RecipeDto> lambdaConvert =  elt -> convertToDto(elt);
		List<Recipe> recipes = recipeJpaRepository.findAll();
		logger.debug("getRecipes()");
		//return recipes.stream().map(lambdaConvert).collect(Collectors.toList());
		return recipes;
	}
	
	public Recipe getRecipeByName(String name) throws EntityNotFoundException{
		
		    logger.debug("findByEmail({})", name);

	        Optional<Recipe> recipe = Optional.ofNullable(recipeJpaRepository.findByName(name));

	        if(!recipe.isPresent()){
	            throw new EntityNotFoundException(String.format("Recipe with userName %s does not exist", recipe));
	        }
	        
	       // return convertToDto(recipe.get());
	        return recipe.get();
	}
	
	
	public RecipeDto convertToDto(Recipe recipe){
		/*ModelMapper modelMapper = new ModelMapper();
		RecipeDto recipeDto = modelMapper.map(recipe, RecipeDto.class);*/
		RecipeDto recipeDto = new RecipeDto(recipe.getId(),recipe.getName(),recipe.getDescription(),recipe.getImagePath(),recipe.getIngredients());
		//recipeDto.setIngredients(recipe.getIngredients());
		return recipeDto;
	}

	@Override
	public Recipe addRecipe(Recipe recipe) {
		logger.debug("addRecipe() from service");
		//Recipe recipe = convertFromDto(recipeDto);
		//return convertToDto((Recipe)recipeJpaRepository.save(recipe));
		return recipeJpaRepository.save(recipe);
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
	public Recipe getRecipeById(Long id) throws EntityNotFoundException {
		logger.debug("getRecipeById({})", id);

        Optional<Recipe> recipe = Optional.ofNullable(recipeJpaRepository.getOne(id));

        if(!recipe.isPresent()){
            throw new EntityNotFoundException(String.format("Recipe with id %s does not exist", recipe));
        }
        
        //return convertToDto(recipe.get());
       return recipe.get();
	}


}
