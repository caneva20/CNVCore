package me.caneva20.CNVCore.CNVActions.Paramenters;

import java.util.ArrayList;
import java.util.List;

public class ActionDoubleList extends ActionParameter {
    private Double[] defaultValue = new Double[0];

    public ActionDoubleList() {
    }

    public ActionDoubleList(boolean isRequired) {
        super(isRequired);
    }

    public ActionDoubleList(boolean isRequired, Double[] defaultValue) {

        super(isRequired);
        this.defaultValue = defaultValue;
    }

    public ActionDoubleList(Double[] defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public Double[] getValue(String in) {
        String[] split = in.split(",,");

        List<Double> doubles = new ArrayList<>();

        for (String s : split) {
            try {
                doubles.add(Double.parseDouble(s));
            } catch (NumberFormatException ignored) {
            }
        }

        return doubles.toArray(new Double[0]);
    }

    @Override
    public Double[] getDefaultValue() {
        return defaultValue;
    }
}
