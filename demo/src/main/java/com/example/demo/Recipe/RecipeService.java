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
	
	Recipe getRecipe(long id) {
		return recipeRepository.findOne(id);
	}
	
	String getRecipeIngredients(long id) {
		return getRecipe(id).ingredients;
	}
	
	String getRecipeInstructions(long id) {
		return getRecipe(id).instructions;
	}
	
	String getRecipeName(long id) {
		return getRecipe(id).name;
	}
	
	String getRecipeTime(long id) {
		return getRecipe(id).time;
	}
	
	int getRecipeSize(long id) {
		return getRecipe(id).size;
	}

}
