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
		recipe.setIngredients("{ingredient1:{amount:1, unit:l}, ingredient2:{amount:1, unit:pieces},"
				+ "ingredient3:{amount:2, unit:dl}, ingredient4:{amount:300, unit:g},"
				+ "ingredient5:{amount:20, unit:cups}}");
	}
	
	@Test
	void testCheckIfIngredientsMatchIncompleteIngredientName() {
		JSONObject searchedIngredients = new JSONObject("{ingredie:{amount:20.0, unit:cups}, ingredient2:{amount:1, unit:pieces}}");
		assertTrue(recipe.checkIfMatchesIngredientsPartly(searchedIngredients));
	}
	
	@Test
	void testCheckIfIngredientsMatchSearchedAmountIsBiggerThanNeeded() {
		JSONObject searchedIngredients = new JSONObject("{ingredient1:{amount:40.0, unit:cups}, ingredient2:{amount:1, unit:pieces}}");
		assertTrue(recipe.checkIfMatchesIngredients(searchedIngredients));
	}
	
	@Test
	void testCheckIfIngredientsMatchSearchedAmountIsLessThanNeeded() {
		JSONObject searchedIngredients = new JSONObject("{ingredient1:{amount:2.0, unit:cups}, ingredient2:{amount:1, unit:pieces}}");
		assertFalse(recipe.checkIfMatchesIngredients(searchedIngredients));
	}
	
	@Test
	void testCheckIfIngredientsMatchAmountIsNotSpecified() {
		JSONObject searchedIngredients = new JSONObject("{ingredient1:{amount:23.5, unit:cups}, ingredient2:{amount:-1, unit:pieces}}");
		assertTrue(recipe.checkIfMatchesIngredients(searchedIngredients));
	}
	
	@Test
	void testConvertToCups() {
		JSONObject convertedIngredients = Recipe.converterToCups(recipe.getIngredientsAsJson());
		JSONObject expectedIngredients = new JSONObject("{ingredient1:{amount:20.0, unit:cups}, ingredient2:{amount:1, unit:pieces},"
				+ "ingredient3:{amount:4.0, unit:cups}, ingredient4:{amount:450.0, unit:cups},"
				+ "ingredient5:{amount:20, unit:cups}}");
		
		for (Object object : convertedIngredients.keySet().toArray()) {
			String key = String.valueOf(object);
			assertEquals(expectedIngredients.getJSONObject(key).get("amount"), convertedIngredients.getJSONObject(key).get("amount"));
			assertEquals(expectedIngredients.getJSONObject(key).get("unit"), convertedIngredients.getJSONObject(key).get("unit"));
		}
	}
	
	/*
	@Test
	void test() {
		JSONObject o = new JSONObject().put("eh", "meh").put("yo", 2);
		System.out.println(o.toString());
		System.out.println(o.get("eh") == "meh");
		JSONObject e = new JSONObject(recipe.getIngredients());
		System.out.println(e.getJSONObject("ingredient1").get("amount"));
	}
	*/
	

}
