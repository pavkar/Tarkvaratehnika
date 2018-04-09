package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.Recipe.Recipe;

class RecipeTests {
	Recipe recipe = new Recipe();
	
	@BeforeEach
	void createRecipe() {
		recipe.setName("Retsepti nimi");
		recipe.setInstructions("Kypseta");
		recipe.setSize(2);
		recipe.setTime("2:40");
		recipe.setIngredients("{abc:{amount:1, unit:l}, d:{amount:1, unit:pieces},"
				+ "e:{amount:2, unit:dl}, f:{amount:300, unit:g},"
				+ "g:{amount:20, unit:cups}}");
	}
	
	@Test
	void testCheckIfIngredientsMatchIncompleteIngredientName() {
		JSONObject searchedIngredients = new JSONObject("{ab:{amount:20.0, unit:cups}, d:{amount:1, unit:pieces}}");
		assertTrue(recipe.checkIfMatchesIngredientsPartly(searchedIngredients));
	}
	
	@Test
	void testCheckIfIngredientsMatchSearchedAmountIsBiggerThanNeeded() {
		JSONObject searchedIngredients = new JSONObject("{abc:{amount:40.0, unit:cups}, d:{amount:1, unit:pieces}}");
		assertTrue(recipe.checkIfMatchesIngredientsPartly(searchedIngredients));
	}
	
	@Test
	void testCheckIfIngredientsMatchSearchedAmountIsLessThanNeeded() {
		JSONObject searchedIngredients = new JSONObject("{abc:{amount:2.0, unit:cups}, d:{amount:1, unit:pieces}}");
		assertFalse(recipe.checkIfMatchesIngredientsPartly(searchedIngredients));
	}
	
	@Test
	void testCheckIfIngredientsMatchAmountIsNotSpecified() {
		JSONObject searchedIngredients = new JSONObject("{d:{amount:-1, unit:pieces}}");
		assertTrue(recipe.checkIfMatchesIngredientsPartly(searchedIngredients));
	}
	
	@Test
	void testConvertToCups() {
		JSONObject convertedIngredients = Recipe.converterToCups(recipe.getIngredientsAsJson());
		JSONObject expectedIngredients = new JSONObject("{abc:{amount:20.0, unit:cups}, d:{amount:1, unit:pieces},"
				+ "e:{amount:4.0, unit:cups}, f:{amount:450.0, unit:cups},"
				+ "g:{amount:20, unit:cups}}");
		
		for (Object object : convertedIngredients.keySet().toArray()) {
			String key = String.valueOf(object);
			assertEquals(expectedIngredients.getJSONObject(key).get("amount"), convertedIngredients.getJSONObject(key).get("amount"));
			assertEquals(expectedIngredients.getJSONObject(key).get("unit"), convertedIngredients.getJSONObject(key).get("unit"));
		}
	}
	
	@Test
	void testCheckUnitIsEmpty() {
		JSONObject searchedIngredients = new JSONObject("{abc:{amount:23.5, unit:cups}, d:{amount:1, unit:NU}}");
		assertTrue(recipe.checkIfMatchesIngredientsPartly(searchedIngredients));
	}
	
	@Test 
	void test() {
		System.out.println(recipeService.searchByName("egg"));
	}

}
