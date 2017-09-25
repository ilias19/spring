package com.firstapp.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firstapp.dto.RecipeDto;
import com.firstapp.exceptions.EntityNotFoundException;
import com.firstapp.services.RecipeService;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	private RecipeService recipeService;
	 
	
	@CrossOrigin
	@GetMapping(value = "/get/all",
	           produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<RecipeDto>> getRecipes(){
		
		logger.debug("getRecipes() from controller");
		return ResponseEntity.ok().body(recipeService.getRecipes());
	
				
	}
	
	@GetMapping(value = "/getByName/{name}",
			   produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<RecipeDto> getRecipeByName(@PathVariable String name){
		logger.debug("getRecipeByName("+name+")");
		try{
			return ResponseEntity.ok().body(recipeService.getRecipeByName(name));
		}catch(EntityNotFoundException e){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value="/add")
	public ResponseEntity  addRecipe(@RequestBody RecipeDto recipe){
		logger.debug("addRecipe() controller");
		recipeService.addRecipe(recipe);
		return new ResponseEntity("Recipe saved successfully", HttpStatus.OK);
	}
	
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<RecipeDto> deleteRecipeById(@PathVariable Long id){
		logger.debug("deleteRecipeById("+id+")");
		recipeService.deleteRecipe(id);
		return new ResponseEntity("Recipe removed successfully", HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById/{id}",
			   produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<RecipeDto> getRecipeById(@PathVariable Long id){
		logger.debug("getRecipeById("+id+")");
		try{
			return ResponseEntity.ok().body(recipeService.getRecipeById(id));
		}catch(EntityNotFoundException e){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	
	public static void main(String [] args){ 
		//Recipe recipe = new Recipe(1l,"hh","kk","ll");
	    //System.out.println(RecipeController.convertToDto(recipe).getName());
		//List<RecipeDto> recips = recipes.stream().map(elt -> RecipeController.convertToDto(elt)).collect(Collectors.toList());
		//System.out.println(recips.get(0).getName());
	}

}
