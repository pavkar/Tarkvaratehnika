package allrecipes;

import java.util.List;

public interface RecipeTemplate {

    String getTitle() throws Exception;
    String getServings() throws Exception;
    String getPrepTime() throws Exception;
    String getIngredients() throws Exception;
    String getInstructions() throws Exception;
}
