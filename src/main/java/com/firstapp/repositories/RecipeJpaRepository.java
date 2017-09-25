package com.firstapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.firstapp.models.Recipe;

@Component
public interface RecipeJpaRepository extends JpaRepository<Recipe,Long>{
	public Recipe findByName(String name);
	//public void deleteById(Long id);

}
