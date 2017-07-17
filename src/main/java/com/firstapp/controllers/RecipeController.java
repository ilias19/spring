package com.firstapp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firstapp.Models.Recipe;
import com.firstapp.dto.RecipeDto;
import com.firstapp.repositories.RecipeJpaRepository;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
	
	@Autowired
	private RecipeJpaRepository recipeJpaRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@CrossOrigin
	@GetMapping(value = "/all")
	public List<RecipeDto> getRecipes(){
		List<Recipe> recipes = recipeJpaRepository.findAll();
		return recipes.stream().map(elt -> convertToDto(elt)).collect(Collectors.toList());
				
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
	
	private  RecipeDto convertToDto(Recipe recipe){
		RecipeDto recipeDto = modelMapper.map(recipe, RecipeDto.class);
		return recipeDto;
	}
	
	public static void main(String [] args){
		/*List<Recipe> recipes = new ArrayList<Recipe>();
		recipes.add(new Recipe(1l,"hh","kk","ll"));
		//Recipe recipe = new Recipe(1l,"hh","kk","ll");
	    //System.out.println(RecipeController.convertToDto(recipe).getName());
		List<RecipeDto> recips = recipes.stream().map(elt -> RecipeController.convertToDto(elt)).collect(Collectors.toList());
		System.out.println(recips.get(0).getName());*/
	}

}
