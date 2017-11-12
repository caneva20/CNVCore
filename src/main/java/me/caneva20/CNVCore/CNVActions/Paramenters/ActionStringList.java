package me.caneva20.CNVCore.CNVActions.Paramenters;

public class ActionStringList extends ActionParameter {
    private String[] defaultValue = new String[0];

    public ActionStringList() {
    }

    public ActionStringList(boolean isRequired) {
        super(isRequired);
    }

    public ActionStringList(boolean isRequired, String[] defaultValue) {

        super(isRequired);
        this.defaultValue = defaultValue;
    }

    public ActionStringList(String[] defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public String[] getValue(String in) {
        return in.split(",,");
    }

    @Override
    public String[] getDefaultValue() {
        return defaultValue;
    }
}
