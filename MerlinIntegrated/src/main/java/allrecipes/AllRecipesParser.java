package allrecipes;

import parsers.GeneralIngredientsParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parsers.RecipeTemplate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllRecipesParser implements RecipeTemplate {

    private String url;
    private GeneralIngredientsParser ingredientParser;
    private Document document;


    public AllRecipesParser(String url) {
        this.url = url;
        ingredientParser = new GeneralIngredientsParser();
    }

    @Override
    public Document getDocument() {
        document = new Document("");
        try {
            if (url.isEmpty()) {
                System.out.println("Please provide an URL");
            } else {
                document = Jsoup.connect(url).get();
            }
        } catch (IOException e) {
            System.out.println("Please provide correct URL");
        }
        return document;
    }

    public void setDocument(String path) {
        try {
            this.document = Jsoup.parse(new FileInputStream(path).toString());
        } catch (FileNotFoundException e) {
            System.out.println("Could not find a file to read!");
        }
    }


    private List<String> getUnParsedIngreds() {
        List<String> ingredients = new ArrayList<>();
        for (Element ingredient : document.select("ul.checklist.dropdownwrapper.list-ingredients-1 li")) {
            ingredients.add(ingredient.text().replace(" ADVERTISEMENT", ""));
        }
        return ingredients;
    }

    @Override
    public String getTitle() {
        return document.select("h1.recipe-summary__h1").text();
    }

    @Override
    public String getPrepTime() {
        return document.select("span.ready-in-time").text();
    }

    @Override
    public String getInstructions() {
        StringBuilder instructions = new StringBuilder();
        for (Element el : document.select("ol.list-numbers.recipe-directions__list li")) {
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
    public String getOriginalAuthor() {
        return document.select("span.submitter__name").text();
    }

    @Override
    public int getServings() {
        String servings = "1";
        Elements metaTags = document.getElementsByTag("meta");
        for (Element metaTag : metaTags) {
            if (metaTag.attr("id").equals("metaRecipeServings")) {
                servings = metaTag.attr("content");
            }
        }
        return Integer.parseInt(servings);
    }

    @Override
    public String getIngredients() {
        return ingredientParser.getIngredients(getUnParsedIngreds());
    }
}
