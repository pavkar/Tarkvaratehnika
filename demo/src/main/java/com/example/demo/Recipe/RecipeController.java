package com.example.demo.Recipe;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeController {
	RecipeService recipeService;
	
	RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@CrossOrigin
	@RequestMapping(value = "/recipes/add", method = RequestMethod.POST, consumes = "application/json")
	public Recipe addRecipe(@RequestBody Recipe recipe) {
		return recipeService.addRecipe(recipe);
		
	}
}
