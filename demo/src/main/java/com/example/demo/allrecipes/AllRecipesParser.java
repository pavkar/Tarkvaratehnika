package com.example.demo.allrecipes;

import com.example.demo.parsers.GeneralIngredientsParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.example.demo.parsers.RecipeTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllRecipesParser implements RecipeTemplate {

    private String url;
    private GeneralIngredientsParser ingredientParser;


    public AllRecipesParser(String url) {
        this.url = url;
        ingredientParser = new GeneralIngredientsParser();
    }

    @Override
    public Document getDocument() throws IOException {
        return Jsoup.connect(url).get();
    }


    private List<String> getUnParsedIngreds() throws Exception {
        List<String> ingredients = new ArrayList<>();
        for (Element ingredient : getDocument().select("ul.checklist.dropdownwrapper.list-ingredients-1 li")) {
            ingredients.add(ingredient.text().replace(" ADVERTISEMENT", ""));
        }
        return ingredients;
    }

    @Override
    public String getTitle() throws Exception {
        return getDocument().select("h1.recipe-summary__h1").text();
    }

    @Override
    public String getPrepTime() throws Exception {
        return getDocument().select("span.ready-in-time").text();
    }

    @Override
    public String getInstructions() throws Exception {
        StringBuilder instructions = new StringBuilder();
       for (Element el : getDocument().select("ol.list-numbers.recipe-directions__list li")) {
            instructions.append(el.text());
            instructions.append("\n");
        }
        return instructions.toString().trim();
    }

    @Override
    public String getOriginalSource() {
        return "allrecipes.com";
    }

    @Override
    public String getOriginalAuthor() throws Exception {
        return getDocument().select("span.submitter__name").text();
    }

    @Override
    public int getServings() throws Exception {
        String servings = "1";
        Elements metaTags = getDocument().getElementsByTag("meta");
        for (Element metaTag : metaTags) {
            if (metaTag.attr("id").equals("metaRecipeServings")) {
                servings = metaTag.attr("content");
            }
        }
        return Integer.parseInt(servings);
    }

    @Override
    public String getIngredients() throws Exception {
        return ingredientParser.getIngredients(getUnParsedIngreds());
    }
}
