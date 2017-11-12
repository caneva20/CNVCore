package me.caneva20.CNVCore.CNVActions.Paramenters;

public class ActionString extends ActionParameter {
    private String defaultValue = "";

    public ActionString() {
    }

    public ActionString(boolean isRequired) {
        super(isRequired);
    }

    public ActionString(boolean isRequired, String defaultValue) {

        super(isRequired);
        this.defaultValue = defaultValue;
    }

    public ActionString(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public String getValue(String in) {
        return String.valueOf(in);
    }

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }
}
