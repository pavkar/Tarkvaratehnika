package parsers;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public interface RecipeTemplate {

    Document getDocument() throws IOException;
    String getTitle() throws IOException;
    int getServings() throws IOException;
    String getPrepTime() throws IOException;
    String getIngredients() throws IOException;
    String getInstructions() throws IOException;
    String getOriginalSource();
    String getOriginalAuthor() throws IOException;
}
