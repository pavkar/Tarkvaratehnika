package com.example.demo.Recipe;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
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
	
	List<Long> getRecipeIdsByName(String name) {
		List<Long> ids = new ArrayList<Long>();
		
		recipeRepository.findAll().forEach(r -> {
			if (r.name.contains(name)) ids.add(r.id);	
		});
		
		return ids;
	}
	
	String getRecipesByName(String name) {
		List<Long> ids = getRecipeIdsByName(name);
		JSONObject infoJSON = new JSONObject();
		
		for (long l : ids) {
			infoJSON.put(String.valueOf(l), getRecipe(l).toString());	
		}
		return infoJSON.toString();
	}
	
	
	
	
	

}
