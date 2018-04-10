package com.example.demo.parsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeneralIngredientsParser {

    private MeasurementUnits units;

    public GeneralIngredientsParser() {
        units = new MeasurementUnits();
    }

    private boolean validateIngredients(String ingredient) {
        for (String unit : units.getMeasurementUnits()) {
            if (unit.contains(ingredient)) { return true; }
        }
        return false;
    }

    private void secondElementMeasureUnit(StringBuilder allIngredients, List<String> words) {
        if (validateIngredients(words.get(1))) {
            allIngredients.append(words.get(2)).append(":").append(words.get(0)).append(":").append(words.get(1));
        } else if (words.size() == 2){
            allIngredients.append(words.get(1)).append(":").append(words.get(0));
        } else {
            allIngredients.append(words.get(1)).append(" ").append(words.get(2)).append(":").append(words.get(0));
        }
    }

    private void secondElementIsToOr(List<String> words) {
        if (words.get(1).equals("to") | words.get(1).equals("or")) {
            List<String> newWords = new ArrayList<>();
            newWords.add(words.get(0));
            newWords.add(words.get(1));
            newWords.addAll(Arrays.asList(words.get(2).split(" ")));
            String quantity = newWords.get(0) + " " + newWords.get(1) + " " + newWords.get(2);
            words.clear();
            words.add(quantity);
            words.add(newWords.get(3));
            words.add(newWords.get(4));
        }
    }

    private void secondElementDigit(List<String> words) {
        if (Character.isDigit(words.get(1).substring(0, 1).toCharArray()[0])) {
            String quantity = words.get(0) + " " + words.get(1);
            List<String> unitAndName = Arrays.asList(words.get(2).split(" ", 2));
            words.clear();
            words.add(quantity);
            words.addAll(unitAndName);
        }
    }

    public String getIngredients(List<String> getUnParsedIngreds) {

        StringBuilder allIngredients = new StringBuilder();

        for (String ingredient : getUnParsedIngreds) {

            // If the first element starts with a digit
            if (Character.isDigit(ingredient.substring(0, 1).toCharArray()[0])) {
                List<String> words = new ArrayList<>(Arrays.asList(ingredient.split(" ", 3)));

                // If the second elements starts with a digit
                secondElementDigit(words);

                // If the second element is "to"
                secondElementIsToOr(words);
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
        return allIngredients.toString();
    }

}
