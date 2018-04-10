package com.example.demo.parsers;

import com.example.demo.allrecipes.AllRecipesParser;
import com.example.demo.foodnetwork.FoodNetworkParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        FoodNetworkParser foodParser = new FoodNetworkParser("https://www.foodnetwork.com/recipes/ina-garten/coq-au-vin-recipe4-2011654");
        AllRecipesParser allRecipesParser = new AllRecipesParser("https://www.allrecipes.com/recipe/88123/apple-cider-sauce-and-pork-loin-chops/?internalSource=hub%20recipe&referringContentType=search%20results&clickId=cardslot%2010");

        List<String> words = new ArrayList<>();
        //words.addAll(Arrays.asList("2", "small", "tablespoon of good sugar"));
        words.addAll(Arrays.asList("1", "to", "2 cups of sugar"));
        GeneralIngredientsParser parser = new GeneralIngredientsParser();
        //System.out.println(words);
        //System.out.println(parser.getIngredientsAsJson(words));
        List<String> listOfWords = new ArrayList<>();
        //listOfWords.add("2 small tablespoons of good sugar");
        //listOfWords.add("1 to 2 cups of sugar");
        //listOfWords.add("Kosher salt");
        listOfWords.add("1 apple");
        System.out.println(parser.getIngredients(listOfWords));
        System.out.println(allRecipesParser.getIngredients());
        System.out.println(foodParser.getIngredients());
//        System.out.println(foodParser.getDocument());
//        System.out.println(foodParser.getTitle());
//        System.out.println(foodParser.getOriginalSource());
//        System.out.println(foodParser.getOriginalAuthor());
//        System.out.println(foodParser.getPrepTime());
//        System.out.println(foodParser.getServings());
//        System.out.println(foodParser.getIngredients());
//        System.out.println(foodParser.getInstructions());

        //System.out.println(allRecipesParser.getDocument());
//        System.out.println(allRecipesParser.getTitle());
//        System.out.println(allRecipesParser.getOriginalSource());
//        System.out.println(allRecipesParser.getOriginalAuthor());
//        System.out.println(allRecipesParser.getPrepTime());
//        System.out.println(allRecipesParser.getServings());
//        System.out.println(allRecipesParser.getIngredients());
//        System.out.println(allRecipesParser.getInstructions());

//        GeneralIngredientsParser parser = new GeneralIngredientsParser();
//        parser.getIngredients(Arrays.asList("1 1/4 cups of milk"));
//        System.out.println(allRecipesParser.getDocument());
//        allRecipesParser.setDocument("C:\\Users\\korph\\Documents\\Tarkvaratehnika\\Grupitöö\\Projekt\\FridgeTest2\\src\\test\\java\\com.example.demo.allrecipes\\AllRecipesResultExample.html");
//        System.out.println(allRecipesParser.getServings());
//        AllRecipesParser parser2 = new AllRecipesParser("");
//        parser2.setDocument("");
//        System.out.println(parser2.getTitle());
//        parser2.setDocument("C:\\Users\\korph\\Documents\\Tarkvaratehnika\\Grupitöö\\Projekt\\FridgeTest2\\src\\test\\java\\com\\example\\demo\\allrecipes\\AllRecipesResultExample.html");
//        System.out.println(parser2.getTitle());
//        System.out.println(parser2.getServings());

    }
}
