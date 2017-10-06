package com.firstapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstapp.models.Ingredient;


public interface IngredientJpaRepository extends JpaRepository<Ingredient,Long>{
	public Ingredient findByName(String name);
	//public void deleteById(Long id);

}