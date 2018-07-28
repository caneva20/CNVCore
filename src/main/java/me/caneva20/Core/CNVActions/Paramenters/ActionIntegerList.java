package me.caneva20.Core.CNVActions.Paramenters;

import java.util.ArrayList;
import java.util.List;

public class ActionIntegerList extends ActionParameter {
    private Integer[] defaultValue = new Integer[0];

    public ActionIntegerList() {
    }

    public ActionIntegerList(boolean isRequired) {
        super(isRequired);
    }

    public ActionIntegerList(boolean isRequired, Integer[] defaultValue) {

        super(isRequired);
        this.defaultValue = defaultValue;
    }

    public ActionIntegerList(Integer[] defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public Integer[] getValue(String in) {
        String[] split = in.split(",,");
        List<Integer> integers = new ArrayList<>();

        for (String s : split) {
            try {
                integers.add(Integer.parseInt(s));
            } catch (NumberFormatException ignored) {
            }
        }

        return integers.toArray(new Integer[0]);
    }

    @Override
    public Integer[] getDefaultValue() {
        return defaultValue;
    }
}
