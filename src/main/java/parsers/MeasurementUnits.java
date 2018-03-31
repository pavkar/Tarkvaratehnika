package parsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeasurementUnits {

    public List<String> measurementUnits = new ArrayList<>();

    public MeasurementUnits() {
        this.measurementUnits.addAll(Arrays.asList("cups", "pints", "tablespoons", "ears", "teaspoons", "pounds", "ounces", "sticks", "tube"));
    }

    public void addUnit(String unit) { measurementUnits.add(unit); }

    public List<String> getMeasurementUnits() { return measurementUnits; }


}
