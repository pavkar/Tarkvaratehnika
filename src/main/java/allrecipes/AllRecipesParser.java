package allrecipes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllRecipesParser implements allrecipes.RecipeTemplate {

    private static String url;

    public AllRecipesParser(String url) {
        this.url = url;
    }

    private Document getDocument() throws Exception {
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
    public String getServings() throws Exception {
        String servings = "1";
        Elements metaTags = getDocument().getElementsByTag("meta");
        for (Element metaTag : metaTags) {
            if (metaTag.attr("id").equals("metaRecipeServings")) {
                servings = metaTag.attr("content");
            }
        }
        return servings;
    }

    @Override
    public String getIngredients() throws Exception {
        getUnParsedIngreds();
        StringBuilder allIngredients = new StringBuilder();

        for (String ingredient : getUnParsedIngreds()) {
            List<String> words = Arrays.asList(ingredient.split(" ", 3));
            allIngredients.append(words.get(2)).append(":").append(words.get(0)).append(":").append(words.get(1));
            if (getUnParsedIngreds().indexOf(ingredient) != getUnParsedIngreds().size() - 1) {
                allIngredients.append("; ");
            }
        }
        return allIngredients.toString();
    }

    public static void main(String[] args) throws Exception {
        AllRecipesParser parser = new AllRecipesParser("https://www.allrecipes.com/recipe/201849/mongolian-" +
                "beef-and-spring-onions/?internalSource=popular&referringContentType=home%20page&clickId=cardslot%205%2F");
        parser.getUnParsedIngreds();
        System.out.println(parser.getTitle());
        System.out.println(parser.getPrepTime());
        System.out.println(parser.getServings());
        System.out.println(parser.getIngredients());
        System.out.println(parser.getInstructions());
    }
}
