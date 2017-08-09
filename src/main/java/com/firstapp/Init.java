package com.firstapp;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.firstapp.models.Recipe;
import com.firstapp.repositories.RecipeJpaRepository;

@Component
public class Init {
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	private RecipeJpaRepository recipeRepository;
	
	@PostConstruct
	public void init(){
		
		logger.debug("init saving recipes in db");
		Recipe recipe1 = Recipe.builder().name("name1").description("description1").imagePath("imagePath1").build();
		Recipe recipe2 = Recipe.builder().name("name2").description("description2").imagePath("imagePath2").build();
		recipeRepository.save(Arrays.asList(recipe1,recipe2));
	}

}
