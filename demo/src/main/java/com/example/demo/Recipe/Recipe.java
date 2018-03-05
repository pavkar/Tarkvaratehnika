package com.example.demo.Recipe;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	//List<String> ingredients;
	String instructions;
	String time;
	int size;
	
}
