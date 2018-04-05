package com.example.demo.foodnetwork;

import com.example.demo.parsers.GeneralIngredientsParser;
import com.example.demo.parsers.RecipeTemplate;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FoodNetworkParser implements RecipeTemplate {

    private String url;
    private GeneralIngredientsParser ingredientsParser;

    public FoodNetworkParser(String url) {
        this.url = url;
        ingredientsParser = new GeneralIngredientsParser();
    }

    @Override
    public Document getDocument() throws IOException {
        return Jsoup.connect(url).get();

    }

    @Override
    public String getTitle() throws IOException {
        return getDocument().select("span.o-AssetTitle__a-HeadlineText").text();
    }

    @Override
    public int getServings() throws IOException {
        String doubleServings = getDocument().select("section.o-RecipeInfo.o-Yield dl dd.o-RecipeInfo__a-Description").text();
        return Integer.parseInt(Arrays.asList(doubleServings.split(" ", 2)).get(0));
    }

    @Override
    public String getPrepTime() throws IOException {
        String doubleTime = getDocument().select("dd.o-RecipeInfo__a-Description--Total").text();
        return doubleTime.substring(0, doubleTime.length() / 2);
    }

    @Override
    public String getIngredients() throws IOException {
        return ingredientsParser.getIngredients(getUnParsedIngreds());
    }

    @Override
    public String getInstructions() throws IOException {
        StringBuilder instructions = new StringBuilder();
        for (Element el : getDocument().select("div.o-Method__m-Body p")) {
            instructions.append(el.text());
            instructions.append("\n");
        }
        return instructions.toString().trim();
    }

    @Override
    public String getOriginalSource() {
        return "foodnetwork.com";
    }

    @Override
    public String getOriginalAuthor() throws IOException {
        return getDocument().select("span.o-Attribution__a-Name").text();
    }

    private List<String> getUnParsedIngreds() throws IOException {
        List<String> ingredients = new ArrayList<>();
        for (Element ingredient : getDocument().select("div.o-Ingredients__m-Body ul li")) {
            ingredients.add(ingredient.text());
        }
        return ingredients;
    }
}
