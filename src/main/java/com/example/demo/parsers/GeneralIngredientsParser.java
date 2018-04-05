package main.java.com.example.demo.parsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneralIngredientsParser {

    private MeasurementUnits units;

    public GeneralIngredientsParser() {
        units = new MeasurementUnits();
    }

    boolean validateIngredients(String ingredientUnit, List<String> units) {
        for (String unit : units) {
            if (unit.contains(ingredientUnit)) { return true; }
        }
        return false;
    }

    void secondElementMeasureUnit(StringBuilder allIngredients, List<String> words) {
        if (validateIngredients(words.get(1), units.getMeasurementUnits())) {
            allIngredients.append(words.get(2)).append(":").append(words.get(0)).append(":").append(words.get(1));
        } else if (words.size() == 2){
            allIngredients.append(words.get(1)).append(":").append(words.get(0));
        } else {
            allIngredients.append(words.get(1)).append(" ").append(words.get(2)).append(":").append(words.get(0));
        }
    }

    List<String> secondElementIsToOr(List<String> words) {
        if (words.get(1).equals("to") | words.get(1).equals("or")) {
            List<String> newWords = new ArrayList<>();
            newWords.add(words.get(0));
            newWords.add(words.get(1));
            newWords.addAll(Arrays.asList(words.get(2).split(" ", 3)));
            //System.out.println(newWords);
            String quantity = newWords.get(0) + " " + newWords.get(1) + " " + newWords.get(2);
            List<String> newList = Arrays.asList(quantity, newWords.get(3), newWords.get(4));
            //System.out.println(newList);
            words = newList;
            //System.out.println(words);
        }
        return words;
    }

    List<String> secondElementDigit(List<String> words) {
        if (Character.isDigit(words.get(1).substring(0, 1).toCharArray()[0])) {
            String quantity = words.get(0) + " " + words.get(1);
            List<String> unitAndName = Arrays.asList(words.get(2).split(" ", 2));
            List<String> newWords = new ArrayList<>();
            newWords.add(quantity);
            newWords.addAll(unitAndName);
            words = newWords;
        }
        return words;
    }

    public String getIngredients(List<String> getUnParsedIngreds) {

        StringBuilder allIngredients = new StringBuilder();

        for (String ingredient : getUnParsedIngreds) {

            // If the first element starts with a digit
            if (Character.isDigit(ingredient.substring(0, 1).toCharArray()[0])) {
                List<String> words = new ArrayList<>(Arrays.asList(ingredient.split(" ", 3)));

                // If the second elements starts with a digit
                words = secondElementDigit(words);

                // If the second element is "to"
                words = secondElementIsToOr(words);
//
                // If the second element is in the list of measurement units
                secondElementMeasureUnit(allIngredients, words);

                // If it is not the last ingredient
                if (getUnParsedIngreds.indexOf(ingredient) != getUnParsedIngreds.size() - 1) {
                    allIngredients.append("; ");
                }
            } else {
                allIngredients.append(ingredient).append("; ");
            }
        }
        return allIngredients.toString().trim();
    }

}
