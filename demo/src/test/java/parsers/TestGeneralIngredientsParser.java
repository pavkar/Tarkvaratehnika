package parsers;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
        builder = new StringBuilder();
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
        ingredient = Arrays.asList("1", "cup", "white sugar");
        parser.secondElementMeasureUnit(builder, ingredient);
        assertEquals("white sugar:1:cup", builder.toString());
    }

    @Test
    public void testTwoElementsInIngredient() {
        ingredient = Arrays.asList("1", "apple");
        parser.secondElementMeasureUnit(builder, ingredient);
        assertEquals("apple:1", builder.toString());
    }

    @Test
    public void testSecondElementNotMeasurementUnit() {
        ingredient = Arrays.asList("1", "big", "onion");
        parser.secondElementMeasureUnit(builder, ingredient);
        assertEquals("big onion:1", builder.toString());
    }

    @Test
    public void testSecondElementIsTo() {
        ingredient = Arrays.asList("1", "to", "2 tablespoons of sugar");
        assertEquals(Arrays.asList("1 to 2", "tablespoons", "of sugar"), parser.secondElementIsToOr(ingredient));
    }

    @Test
    public void testSecondElementOr() {
        ingredient = Arrays.asList("1", "or", "2 teaspoons of coffee");
        assertEquals(Arrays.asList("1 or 2", "teaspoons", "of coffee"), parser.secondElementIsToOr(ingredient));
    }

    @Test
    public void testSecondElementIsDigit() {
        ingredient = Arrays.asList("1", "1/4", "cups of milk");
        assertEquals(Arrays.asList("1 1/4", "cups", "of milk"), parser.secondElementDigit(ingredient));
    }

    @Test
    public void testAddSemicolonAndSpaceBetweenIngredsAndExtractAmounts() {
        assertEquals("of milk:1:cup; of coffee:2 to 3:teaspoons",
                parser.getIngredients(Arrays.asList("1 cup of milk", "2 to 3 teaspoons of coffee")));
    }

    @Test
    public void testAddSemicolonEndOfIngredNoExtraction() {
        assertEquals("Kosher salt; cinnamon to taste;",
                parser.getIngredients(Arrays.asList("Kosher salt", "cinnamon to taste")));
    }

    @Test
    public void testAddingMeasurementUnit() {
        MeasurementUnits units = new MeasurementUnits();
        units.addUnit("pinch");
        assertEquals("pinch", units.getMeasurementUnits().get(units.getMeasurementUnits().size() - 1));
    }
}
