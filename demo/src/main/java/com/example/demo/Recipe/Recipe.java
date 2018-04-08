package com.example.demo.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.json.JSONObject;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Recipe {
	@Id
	@GeneratedValue
	long id;
	String name;

	String ingredients;
	String instructions;
	String time;
	int size;

	private static final double LITRES_IN_CUPS = 20;
	private static final double DETSILITRES_IN_CUPS = 2;
	private static final double GRAMS_IN_CUPS = 1.5;
	private static final double KILOGRAMS_IN_CUPS = 1500;

	@Override
	public String toString() {
		String recipeJSON = new JSONObject().put("name", name).put("ingredients", ingredients)
				.put("instructions", instructions).put("time", time).put("size", size).toString();
		return recipeJSON;
	}

	private List<String> getAllIngredientsOnlyName() {
		List<String> onlyNameIngredients = new ArrayList<>();
		for (String entireData : ingredients.split(";")) {
			onlyNameIngredients.add(entireData.split(":")[0]);
		}
		return onlyNameIngredients;
	}

	public JSONObject getIngredientsAsJson() {
		return new JSONObject(this.ingredients);
	}

	// output: [number, unit]
	// returns null if does not exist
	private List<String> getIngredientAmount(String ingredient) {
		String[] splitAllData = ingredients.split(";");
		List<String> result = new ArrayList<>();
		for (String entireData : splitAllData) {
			if (entireData.contains(ingredient)) {
				String[] splitIngredientData = entireData.split(":");
				result.add(splitIngredientData[1]);
				result.add(splitIngredientData[2]);
				return result;
			}
		}
		return null;
	}
	
	private static void replaceAmount(JSONObject object, Double newAmount, String newUnit) {
		object.remove("amount");
		object.put("amount", newAmount);
		object.remove("unit");
		object.put("unit", newUnit);
	}
	
	private static void calculateAndReplaceAmountToCups(JSONObject object, Double conversionVariable) {
		System.out.println("Object: " + object.toString());
		Double oldAmount = object.getDouble("amount");
		Double newAmount = oldAmount * conversionVariable;
		replaceAmount(object, newAmount, "cups");
	}

	public static JSONObject converterToCups(JSONObject ingredients) {
		for (Object object : ingredients.keySet().toArray()) {
			String key = String.valueOf(object);
			if (ingredients.getJSONObject(key).getString("unit").equals("l")) {
				calculateAndReplaceAmountToCups(ingredients.getJSONObject(key), LITRES_IN_CUPS);
			} else if (ingredients.getJSONObject(key).getString("unit").equals("dl")) {
				calculateAndReplaceAmountToCups(ingredients.getJSONObject(key), DETSILITRES_IN_CUPS);
			} else if (ingredients.getJSONObject(key).getString("unit").equals("g")) {
				calculateAndReplaceAmountToCups(ingredients.getJSONObject(key), GRAMS_IN_CUPS);
			} else if (ingredients.getJSONObject(key).getString("unit").equals("kg")) {
				calculateAndReplaceAmountToCups(ingredients.getJSONObject(key), KILOGRAMS_IN_CUPS);
			}
		}
		return ingredients;
	}

	/*
	 * Argumendi formaat: {ingredient1:{amount:*number*, unit:*unit*},
	 * ingredient2:{jne}} Kui kogust pole m2rgitud, siis peab amount -1 olema.
	 * yhikute teisendust on vaja muuta, sest see ei arvesta sellega, kui yks asi on g ja teine on kg
	 * 
	 */
	public boolean checkIfMatchesIngredients(JSONObject ingredients) {
		JSONObject searchedIngredients = converterToCups(ingredients);
		JSONObject recipeIngredients = converterToCups(this.getIngredientsAsJson());
		
		/*
		 * for each ingredient if not in recipe return false 
		 * else if searched ingredient amount is -1 (i.e. not specified) continue;
		 * else if searched amount < needed amount return false 
		 * after loop return true
		 */
		for (Object object : searchedIngredients.keySet().toArray()) {
			String key = String.valueOf(object);
			if (!recipeIngredients.keySet().contains(key)) return false;
			else if (searchedIngredients.getJSONObject(key).getDouble("amount") == -1) continue;
			else if (searchedIngredients.getJSONObject(key).getDouble("amount") < 
					recipeIngredients.getJSONObject(key).getDouble("amount")){
				return false;
			}
		}
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
