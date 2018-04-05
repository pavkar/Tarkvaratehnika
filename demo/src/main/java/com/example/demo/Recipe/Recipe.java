package com.example.demo.Recipe;

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
	
	@Override
	public String toString() {
		String recipeJSON = new JSONObject().put("name", name).put("ingredients", ingredients)
				.put("instructions", instructions).put("time", time).put("size", size)
				.toString();
		return recipeJSON;
	}
	
}
