package me.caneva20.CNVCore.CNVActions.Paramenters;

import java.util.ArrayList;
import java.util.List;

public class ActionBooleanList extends ActionParameter {
    private Boolean[] defaultValue = new Boolean[0];

    public ActionBooleanList() {
    }

    public ActionBooleanList(boolean isRequired) {
        super(isRequired);
    }

    public ActionBooleanList(boolean isRequired, Boolean[] defaultValue) {

        super(isRequired);
        this.defaultValue = defaultValue;
    }

    public ActionBooleanList(Boolean[] defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public Boolean[] getValue(String in) {
        String[] split = in.split(",,");

        List<Boolean> booleans = new ArrayList<>();

        for (String s : split) {
            try {
                booleans.add(Boolean.parseBoolean(s));
            } catch (Exception ignored) {
            }
        }

        return booleans.toArray(new Boolean[0]);
    }

    @Override
    public Boolean[] getDefaultValue() {
        return defaultValue;
    }
}
