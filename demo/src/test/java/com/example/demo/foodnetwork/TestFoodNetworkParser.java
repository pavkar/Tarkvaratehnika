package com.example.demo.foodnetwork;

import com.example.demo.allrecipes.AllRecipesParser;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestFoodNetworkParser {

    FoodNetworkParser parser;

    @Before
    public void createAllRecipeParser() {
        parser = new FoodNetworkParser("https://www.foodnetwork.com/recipes/ina-garten/coq-au-vin-recipe4-2011654");
        parser.setDocument("C:\\Users\\korph\\Documents\\Tarkvaratehnika\\Grupitöö\\Projekt\\FridgeTest2\\src\\test\\java\\com\\example\\demo\\foodnetwork\\FoodNetworkResultExample.html");
    }

    @Test
    public void testFoodNetworkTitle() {
        assertEquals("Coq Au Vin", parser.getTitle());
    }

    @Test
    public void testFoodNetworkPrepTime() {
        assertEquals("1 hr 30 min", parser.getPrepTime());
    }

    @Test
    public void testFoodNetworkServings() {
        assertEquals(3, parser.getServings());
    }

    @Test
    public void testFoodNetworkGetOriginalSource() {
        assertEquals("foodnetwork.com", parser.getOriginalSource());
    }

    @Test
    public void testFoodNetworkGetOriginalAuthor() {
        assertEquals("Ina Garten Barefoot Contessa Anniversary", parser.getOriginalAuthor());
    }

    @Test
    public void testFoodNetworkIngredients() {
        String ingredients = "good olive oil:2:tablespoons; good bacon or pancetta, diced:4:ounces; (3 to 4-pound) " +
                "chicken, cut in 8ths:1; Kosher salt and freshly ground black pepper; carrots, cut diagonally in " +
                "1-inch pieces:1/2:pound; yellow onion, sliced:1; chopped garlic:1:teaspoon; Cognac or good " +
                "brandy:1/4:cup; (375 ml) good dry red wine such as Burgundy:1/2:bottle; good chicken stock, " +
                "preferably homemade:1:cup; fresh thyme sprigs:10; unsalted butter, at room temperature, " +
                "divided:2:tablespoons; all-purpose flour:1 1/2:tablespoons; frozen small whole onions:1/2:pound; " +
                "cremini mushrooms, stems removed and thickly sliced:1/2:pound";
        assertEquals(ingredients, parser.getIngredients());
    }

    @Test
    public void testFoodNetworkUnparsedIngedients() {
        List<String> ingreds = Arrays.asList("2 tablespoons good olive oil", "4 ounces good bacon or pancetta, diced",
                "1 (3 to 4-pound) chicken, cut in 8ths", "Kosher salt and freshly ground black pepper", "1/2 pound " +
                        "carrots, cut diagonally in 1-inch pieces", "1 yellow onion, sliced", "1 teaspoon chopped garlic",
                "1/4 cup Cognac or good brandy", "1/2 bottle (375 ml) good dry red wine such as Burgundy", "1 cup good " +
                        "chicken stock, preferably homemade", "10 fresh thyme sprigs", "2 tablespoons unsalted butter, at room " +
                        "temperature, divided", "1 1/2 tablespoons all-purpose flour", "1/2 pound frozen small whole " +
                        "onions", "1/2 pound cremini mushrooms, stems removed and thickly sliced");
        assertEquals(ingreds, parser.getUnParsedIngreds());
    }

    @Test
    public void testFoodNetworkInstructions() {
        String instructions = "Preheat the oven to 250 degrees F.\nHeat the olive oil in a large Dutch oven. Add the " +
                "bacon and cook over medium heat for 8 to 10 minutes, until lightly browned. Remove the bacon to a " +
                "plate with a slotted spoon.\nMeanwhile, lay the chicken out on paper towels and pat dry. Liberally " +
                "sprinkle the chicken on both sides with salt and pepper. When the bacon is removed, brown the chicken " +
                "pieces in batches in a single layer for about 5 minutes, turning to brown evenly. Remove the chicken " +
                "to the plate with the bacon and continue to brown until all the chicken is done. Set aside.\nAdd the " +
                "carrots, onions, 2 teaspoons salt, and 1 teaspoon pepper to the pan and cook over medium heat for 10 " +
                "to 12 minutes, stirring occasionally, until the onions are lightly browned. Add the garlic and cook " +
                "for 1 more minute. Add the Cognac and put the bacon, chicken, and any juices that collected on the " +
                "plate into the pot. Add the wine, chicken stock, and thyme and bring to a simmer. Cover the pot with " +
                "a tight fitting lid and place in the oven for 30 to 40 minutes, until the chicken is just not pink. " +
                "Remove from the oven and place on top of the stove.\nMash 1 tablespoon of butter and the flour " +
                "together and stir into the stew. Add the frozen onions. In a medium saute pan, add the remaining 1 " +
                "tablespoon of butter and cook the mushrooms over medium-low heat for 5 to 10 minutes, until browned. " +
                "Add to the stew. Bring the stew to a simmer and cook for another 10 minutes. Season to taste. Serve hot.";
        assertEquals(instructions, parser.getInstructions());
    }

    @Test
    public void testFoodNetworkUrlEmpty() {
        FoodNetworkParser parser1 = new FoodNetworkParser("");
        assertTrue(!parser1.getDocument().hasText());
    }

    @Test
    public void testFoodNetworkUrlBroken() {
        FoodNetworkParser parser1 = new FoodNetworkParser("garbage");
        assertTrue(!parser1.getDocument().hasText());
    }

    @Test
    public void testFoodNetworkAuthenticationError() {
        FoodNetworkParser parser1 = new FoodNetworkParser("garbage");
        assertTrue(!parser1.getDocument().hasText());
    }

    @Test
    public void testFoodNetworkPathBroken() {
        FoodNetworkParser parser1 = new FoodNetworkParser("");
        parser1.setDocument("jik");
        assertTrue(!parser1.getDocument().hasText());
    }

    @Test
    public void testFoodNetworkFilePathNull() {
        FoodNetworkParser parser1 = new FoodNetworkParser("");
        String path = "C:\\Users\\korph\\Documents\\Tarkvaratehnika\\Grupitöö\\Projekt\\FridgeTest2\\src\\test\\java" +
                "\\com\\example\\demo\\foodnetwork\\exampleFood.txt";
        try {
            final RandomAccessFile file = new RandomAccessFile(path, "rw");
            file.getChannel().lock();
            parser1.setDocument(path);
        } catch (FileNotFoundException e){
            System.out.println("Couldn't find the specified file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(!parser1.getDocument().hasText());
    }
}
