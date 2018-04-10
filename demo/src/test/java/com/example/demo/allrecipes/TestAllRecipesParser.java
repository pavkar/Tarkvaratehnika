package allrecipes;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestAllRecipesParser {

    AllRecipesParser parser;

    @Before
    public void createAllRecipeParser() {
        parser = new AllRecipesParser("https://www.allrecipes.com/recipe/88123/apple-cider-sauce-and-pork-loin-chops/?internalSource=staff%20pick&referringId=80&referringContentType=recipe%20hub");
    }

    @Test
    public void testNothing() throws IOException {
        assertEquals(4, parser.returnInt());
    }
}
