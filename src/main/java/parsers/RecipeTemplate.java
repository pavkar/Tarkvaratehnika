package parsers;

import org.jsoup.nodes.Document;

import java.util.List;

public interface RecipeTemplate {

    Document getDocument() throws Exception;
    String getTitle() throws Exception;
    int getServings() throws Exception;
    String getPrepTime() throws Exception;
    String getIngredients() throws Exception;
    String getInstructions() throws Exception;
    String getOriginalSource();
    String getOriginalAuthor() throws Exception;
}
