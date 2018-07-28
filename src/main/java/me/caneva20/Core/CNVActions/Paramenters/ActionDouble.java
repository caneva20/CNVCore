package me.caneva20.Core.CNVActions.Paramenters;

public class ActionDouble extends ActionParameter {
    private double defaultValue;

    public ActionDouble() {
    }

    public ActionDouble(boolean isRequired) {
        super(isRequired);
    }

    public ActionDouble(boolean isRequired, double defaultValue) {
        super(isRequired);
        this.defaultValue = defaultValue;
    }

    public ActionDouble(double defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public Double getValue(String in) {
        try {
            return Double.parseDouble(in);
        } catch (NumberFormatException e) {
            return 0D;
        }
    }

    @Override
    public Double getDefaultValue() {
        return defaultValue;
    }
}
