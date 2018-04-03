package parsers;

import allrecipes.AllRecipesParser;
import foodnetwork.FoodNetworkParser;

public class Main {

    public static void main(String[] args) throws Exception {
        FoodNetworkParser foodParser = new FoodNetworkParser("https://www.foodnetwork.com/recipes/ina-garten/coq-au-vin-recipe4-2011654");
        AllRecipesParser allRecipesParser = new AllRecipesParser("https://www.allrecipes.com/recipe/88123/apple-cider-sauce-and-pork-loin-chops/?internalSource=staff%20pick&referringId=80&referringContentType=recipe%20hub");
//        System.out.println(foodParser.getTitle());
//        System.out.println(foodParser.getOriginalSource());
//        System.out.println(foodParser.getOriginalAuthor());
//        System.out.println(foodParser.getPrepTime());
//        System.out.println(foodParser.getServings());
//        System.out.println(foodParser.getIngredients());
//        System.out.println(foodParser.getInstructions());

        System.out.println(allRecipesParser.getTitle());
        System.out.println(allRecipesParser.getOriginalSource());
        System.out.println(allRecipesParser.getOriginalAuthor());
        System.out.println(allRecipesParser.getPrepTime());
        System.out.println(allRecipesParser.getServings());
        System.out.println(allRecipesParser.getIngredients());
        System.out.println(allRecipesParser.getInstructions());

        RecipeTemplate food = new FoodNetworkParser("FRGERG");


    }
}
