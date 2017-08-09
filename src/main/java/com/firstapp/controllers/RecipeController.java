package com.firstapp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firstapp.dto.RecipeDto;
import com.firstapp.models.Recipe;
import com.firstapp.repositories.RecipeJpaRepository;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	private RecipeJpaRepository recipeJpaRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@CrossOrigin
	@GetMapping(value = "/all",
	           produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<RecipeDto> getRecipes(){
		Function<Recipe,RecipeDto> lambdaConvert =  elt -> convertToDto(elt);
		List<Recipe> recipes = recipeJpaRepository.findAll();
		logger.debug("get all recipes");
		return recipes.stream().map(lambdaConvert).collect(Collectors.toList());
				
	}
	
	@GetMapping(value = "/{name}")
	public RecipeDto getRecipeByName(@PathVariable String name){
		logger.debug("convert to dto");
		return convertToDto(recipeJpaRepository.findByName(name));
	}
	
	@PostMapping(value="/load")
	public RecipeDto addRecipe(@RequestBody Recipe recipe){
		logger.debug("saving recipe "+ recipe.getName());
		recipeJpaRepository.save(recipe);
		return getRecipeByName(recipe.getName());
	}

	public RecipeJpaRepository getRecipeJpaRepository() {
		return recipeJpaRepository;
	}

	public void setRecipeJpaRepository(RecipeJpaRepository recipeJpaRepository) {
		this.recipeJpaRepository = recipeJpaRepository;
	}
	
	public RecipeDto convertToDto(Recipe recipe){
		return modelMapper.map(recipe, RecipeDto.class);
	}
	/*
	public static void main(String [] args){
		List<Recipe> recipes = new ArrayList<Recipe>();
		recipes.add(new Recipe(1l,"hh","kk","ll"));
		//Recipe recipe = new Recipe(1l,"hh","kk","ll");
	    //System.out.println(RecipeController.convertToDto(recipe).getName());
		//List<RecipeDto> recips = recipes.stream().map(elt -> RecipeController.convertToDto(elt)).collect(Collectors.toList());
		//System.out.println(recips.get(0).getName());
	}*/

}
