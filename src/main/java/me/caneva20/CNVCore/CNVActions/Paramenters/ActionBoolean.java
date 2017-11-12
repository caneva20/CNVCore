package me.caneva20.CNVCore.CNVActions.Paramenters;

public class ActionBoolean extends ActionParameter {
    private boolean defaultValue;

    public ActionBoolean() {
    }

    public ActionBoolean(boolean isRequired) {
        super(isRequired);
    }

    public ActionBoolean(boolean isRequired, boolean defaultValue) {
        super(isRequired);
        this.defaultValue = defaultValue;
    }

    @Override
    public Boolean getValue(String in) {
        try {
            return Boolean.parseBoolean(in);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean getDefaultValue() {
        return defaultValue;
    }
}
