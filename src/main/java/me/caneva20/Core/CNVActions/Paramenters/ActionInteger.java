package me.caneva20.Core.CNVActions.Paramenters;

public class ActionInteger extends ActionParameter {
    private int defaultValue;

    public ActionInteger() {
    }

    public ActionInteger(boolean isRequired) {
        super(isRequired);
    }

    public ActionInteger(boolean isRequired, int defaultValue) {

        super(isRequired);
        this.defaultValue = defaultValue;
    }

    public ActionInteger(int defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public Integer getValue(String in) {
        if ("".equals(in)) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(in);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    @Override
    public Integer getDefaultValue() {
        return defaultValue;
    }
}
