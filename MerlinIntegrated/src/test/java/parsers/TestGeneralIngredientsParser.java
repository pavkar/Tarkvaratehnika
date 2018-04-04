package parsers;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestGeneralIngredientsParser {

    GeneralIngredientsParser parser;
    private List<String> ingredient;
    private StringBuilder builder;

    @Before
    public void createGeneralParserObject() {
        parser = new GeneralIngredientsParser();
    }


    @Test
    public void testValidateIngredientsExistsInList() {
        List<String> units = Arrays.asList("cup", "teaspoon");
        assertTrue(parser.validateIngredients("teaspoon", units));
    }

    @Test
    public void testValidateIngredientUnitNotExists() {
        List<String> units = Arrays.asList("cup", "teaspoon");
        assertFalse(parser.validateIngredients("big", units));
    }

    @Test
    public void testValidateIngredientUnitNoUnits() {
        List<String> units = new ArrayList<>();
        assertFalse(parser.validateIngredients("cup", units));
    }


    @Test
    public void testSecondElementIsMeasurementUnit() {
        builder = new StringBuilder();
        ingredient = Arrays.asList("1", "cup", "white sugar");
        parser.secondElementMeasureUnit(builder, ingredient);
        assertEquals("white sugar:1:cup", builder.toString());
    }

    @Test
    public void testTwoElementsInIngredient() {
        builder = new StringBuilder();
        ingredient = Arrays.asList("1", "apple");
        parser.secondElementMeasureUnit(builder, ingredient);
        assertEquals("apple:1", builder.toString());
    }

    @Test
    public void testSecondElementNotMeasurementUnit() {
        builder = new StringBuilder();
        ingredient = Arrays.asList("1", "big", "onion");
        parser.secondElementMeasureUnit(builder, ingredient);
        assertEquals("big onion:1", builder.toString());
    }
}
