package com.example.demo.Recipe;

import org.springframework.stereotype.Service;

@Service
public class RecipeService {
	RecipeRepository recipeRepository;
	
	RecipeService(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	Recipe addRecipe(Recipe recipe) {
		return recipeRepository.save(recipe);
	}

}
