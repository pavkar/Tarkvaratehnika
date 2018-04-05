package main.java.com.example.demo.parsers;

import main.java.com.example.demo.allrecipes.AllRecipesParser;
import main.java.com.example.demo.foodnetwork.FoodNetworkParser;

public class Main {

    public static void main(String[] args) throws Exception {
        FoodNetworkParser foodParser = new FoodNetworkParser("https://www.foodnetwork.com/recipes/ina-garten/coq-au-vin-recipe4-2011654");
        AllRecipesParser allRecipesParser = new AllRecipesParser("https://www.allrecipes.com/recipe/88123/apple-cider-sauce-and-pork-loin-chops/?internalSource=hub%20recipe&referringContentType=search%20results&clickId=cardslot%2010");
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
        AllRecipesParser parser2 = new AllRecipesParser("");
        parser2.setDocument("");
        System.out.println(parser2.getTitle());
//        parser2.setDocument("C:\\Users\\korph\\Documents\\Tarkvaratehnika\\Grupitöö\\Projekt\\FridgeTest2\\src\\test\\java\\com\\example\\demo\\allrecipes\\AllRecipesResultExample.html");
//        System.out.println(parser2.getTitle());
//        System.out.println(parser2.getServings());

    }
}
