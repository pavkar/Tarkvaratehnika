package allrecipes;

import parsers.GeneralIngredientsParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parsers.RecipeTemplate;

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


    private List<String> getUnParsedIngreds() throws IOException {
        List<String> ingredients = new ArrayList<>();
        for (Element ingredient : getDocument().select("ul.checklist.dropdownwrapper.list-ingredients-1 li")) {
            ingredients.add(ingredient.text().replace(" ADVERTISEMENT", ""));
        }
        return ingredients;
    }

    @Override
    public String getTitle() throws IOException {
        return getDocument().select("h1.recipe-summary__h1").text();
    }

    @Override
    public String getPrepTime() throws IOException {
        return getDocument().select("span.ready-in-time").text();
    }

    @Override
    public String getInstructions() throws IOException {
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
    public String getOriginalAuthor() throws IOException {
        return getDocument().select("span.submitter__name").text();
    }

    @Override
    public int getServings() throws IOException {
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
    public String getIngredients() throws IOException {
        return ingredientParser.getIngredients(getUnParsedIngreds());
    }
}
