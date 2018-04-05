package com.example.demo.Recipe;

import com.example.demo.allrecipes.AllRecipesParser;
import com.example.demo.foodnetwork.FoodNetworkParser;
import com.example.demo.parsers.RecipeTemplate;

import java.io.IOException;

public class ExternalRecipe extends Recipe {
	private RecipeTemplate parser;
	
	public ExternalRecipe(String url) throws IOException {
		if (url.contains("foodnetwork.com")) {
			this.parser = new FoodNetworkParser(url);
		} else if (url.contains("com.example.demo.allrecipes.com")) {
			this.parser = new AllRecipesParser(url);
		}
		super.name = parser.getTitle();
		super.ingredients = parser.getIngredients();
		super.instructions = parser.getInstructions();
		super.size = parser.getServings();
		super.time = parser.getPrepTime();
	}
	
	

}
